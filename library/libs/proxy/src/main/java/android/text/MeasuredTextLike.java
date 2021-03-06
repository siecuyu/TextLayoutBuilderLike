/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.text;

import android.graphics.Paint;
import android.os.Build;
import android.text.proxy.AndroidBidiProxy;
import android.text.proxy.ArrayUtilsProxy;
import android.text.proxy.PaintProxy;
import android.text.proxy.TextUtilsProxy;
import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;
import android.util.Log;

import com.android.internal.util.ArrayUtils;

class MeasuredTextLike {
    private static final boolean localLOGV = false;
    CharSequence mText;
    int mTextStart;
    float[] mWidths;
    char[] mChars;
    byte[] mLevels;
    int mDir;
    boolean mEasy;
    int mLen;

    private int mPos;
    private TextPaint mWorkPaint;
    private StaticLayoutLike.Builder mBuilder;

    private MeasuredTextLike() {
        mWorkPaint = new TextPaint();
    }

    private static final Object[] sLock = new Object[0];
    private static final MeasuredTextLike[] sCached = new MeasuredTextLike[3];

    static MeasuredTextLike obtain() {
        MeasuredTextLike mt;
        synchronized (sLock) {
            for (int i = sCached.length; --i >= 0;) {
                if (sCached[i] != null) {
                    mt = sCached[i];
                    sCached[i] = null;
                    return mt;
                }
            }
        }
        mt = new MeasuredTextLike();
        if (localLOGV) {
            Log.v("MEAS", "new: " + mt);
        }
        return mt;
    }

    static MeasuredTextLike recycle(MeasuredTextLike mt) {
        mt.finish();
        synchronized(sLock) {
            for (int i = 0; i < sCached.length; ++i) {
                if (sCached[i] == null) {
                    sCached[i] = mt;
                    mt.mText = null;
                    break;
                }
            }
        }
        return null;
    }

    void finish() {
        mText = null;
        mBuilder = null;
        if (mLen > 1000) {
            mWidths = null;
            mChars = null;
            mLevels = null;
        }
    }

    void setPos(int pos) {
        mPos = pos - mTextStart;
    }

    /**
     * Analyzes text for bidirectional runs.  Allocates working buffers.
     */
    void setPara(CharSequence text, int start, int end, TextDirectionHeuristic textDir,
            StaticLayoutLike.Builder builder) {
        mBuilder = builder;
        mText = text;
        mTextStart = start;

        int len = end - start;
        mLen = len;
        mPos = 0;

        if (mWidths == null || mWidths.length < len) {
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT <= 20) {
                mWidths = new float[ArrayUtilsProxy.idealFloatArraySize(len)];
            } else {
                mWidths = ArrayUtils.newUnpaddedFloatArray(len);
            }
        }
        if (mChars == null || mChars.length < len) {
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT <= 20) {
                mChars = new char[ArrayUtilsProxy.idealCharArraySize(len)];
            } else {
                mChars = ArrayUtils.newUnpaddedCharArray(len);
            }
        }

        TextUtils.getChars(text, start, end, mChars, 0);

        if (text instanceof Spanned) {
            Spanned spanned = (Spanned) text;
            ReplacementSpan[] spans = spanned.getSpans(start, end,
                    ReplacementSpan.class);

            for (int i = 0; i < spans.length; i++) {
                int startInPara = spanned.getSpanStart(spans[i]) - start;
                int endInPara = spanned.getSpanEnd(spans[i]) - start;
                // The span interval may be larger and must be restricted to [start, end[
                if (startInPara < 0) startInPara = 0;
                if (endInPara > len) endInPara = len;
                for (int j = startInPara; j < endInPara; j++) {
                    mChars[j] = '\uFFFC'; // object replacement character
                }
            }
        }

        if ((textDir == TextDirectionHeuristics.LTR ||
                textDir == TextDirectionHeuristics.FIRSTSTRONG_LTR ||
                textDir == TextDirectionHeuristics.ANYRTL_LTR) &&
                TextUtilsProxy.doesNotNeedBidi(mChars, 0, len)) {
            mDir = Layout.DIR_LEFT_TO_RIGHT;
            mEasy = true;
        } else {
            if (mLevels == null || mLevels.length < len) {
                if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT <= 20) {
                    mLevels = new byte[ArrayUtilsProxy.idealByteArraySize(len)];
                } else {
                    mLevels = ArrayUtils.newUnpaddedByteArray(len);
                }
            }
            int bidiRequest;
            if (textDir == TextDirectionHeuristics.LTR) {
                bidiRequest = 1;
            } else if (textDir == TextDirectionHeuristics.RTL) {
                bidiRequest = -1;
            } else if (textDir == TextDirectionHeuristics.FIRSTSTRONG_LTR) {
                bidiRequest = 2;
            } else if (textDir == TextDirectionHeuristics.FIRSTSTRONG_RTL) {
                bidiRequest = -2;
            } else {
                boolean isRtl = textDir.isRtl(mChars, 0, len);
                bidiRequest = isRtl ? -1 : 1;
            }
            mDir = AndroidBidiProxy.bidi(bidiRequest, mChars, mLevels, len, false);
            mEasy = false;
        }
    }

    float addStyleRun(TextPaint paint, int len, Paint.FontMetricsInt fm) {
        if (fm != null) {
            paint.getFontMetricsInt(fm);
        }

        int p = mPos;
        mPos = p + len;

        // try to do widths measurement in native code, but use Java if paint has been subclassed
        // FIXME: may want to eliminate special case for subclass
        float[] widths = null;
        if (mBuilder == null || paint.getClass() != TextPaint.class) {
            widths = mWidths;
        }
        if (mEasy) {
            boolean isRtl = mDir != Layout.DIR_LEFT_TO_RIGHT;
            float width = 0;
            if (widths != null) {
                width = paint.getTextRunAdvances(mChars, p, len, p, len, isRtl, widths, p);
                if (mBuilder != null) {
                    mBuilder.addMeasuredRun(p, p + len, widths);
                }
            } else {
                width = mBuilder.addStyleRun(paint, p, p + len, isRtl);
            }
            return width;
        }

        float totalAdvance = 0;
        int level = mLevels[p];
        for (int q = p, i = p + 1, e = p + len;; ++i) {
            if (i == e || mLevels[i] != level) {
                boolean isRtl = (level & 0x1) != 0;
                if (widths != null) {
                    totalAdvance +=
                            paint.getTextRunAdvances(mChars, q, i - q, q, i - q, isRtl, widths, q);
                    if (mBuilder != null) {
                        mBuilder.addMeasuredRun(q, i, widths);
                    }
                } else {
                    totalAdvance += mBuilder.addStyleRun(paint, q, i, isRtl);
                }
                if (i == e) {
                    break;
                }
                q = i;
                level = mLevels[i];
            }
        }
        return totalAdvance;
    }

    float addStyleRun4_4(TextPaint paint, int len, Paint.FontMetricsInt fm) {
        if (fm != null) {
            paint.getFontMetricsInt(fm);
        }

        int p = mPos;
        mPos = p + len;

        if (mEasy) {
            int flags = mDir == Layout.DIR_LEFT_TO_RIGHT
                    ? 0 : 1;
            return PaintProxy.getTextRunAdvances(paint, mChars, p, len, p, len, flags, mWidths, p);
        }

        float totalAdvance = 0;
        int level = mLevels[p];
        for (int q = p, i = p + 1, e = p + len;; ++i) {
            if (i == e || mLevels[i] != level) {
                int flags = (level & 0x1) == 0 ? 0 : 1;
                totalAdvance +=
                        PaintProxy.getTextRunAdvances(paint, mChars, q, i - q, q, i - q, flags, mWidths, q);
                if (i == e) {
                    break;
                }
                q = i;
                level = mLevels[i];
            }
        }
        return totalAdvance;
    }

    float addStyleRun(TextPaint paint, MetricAffectingSpan[] spans, int len,
            Paint.FontMetricsInt fm) {

        TextPaint workPaint = mWorkPaint;
        workPaint.set(paint);
        // XXX paint should not have a baseline shift, but...
        workPaint.baselineShift = 0;

        ReplacementSpan replacement = null;
        for (int i = 0; i < spans.length; i++) {
            MetricAffectingSpan span = spans[i];
            if (span instanceof ReplacementSpan) {
                replacement = (ReplacementSpan)span;
            } else {
                span.updateMeasureState(workPaint);
            }
        }

        float wid;
        if (replacement == null) {
            wid = addStyleRun(workPaint, len, fm);
        } else {
            // Use original text.  Shouldn't matter.
            wid = replacement.getSize(workPaint, mText, mTextStart + mPos,
                    mTextStart + mPos + len, fm);
            if (mBuilder == null) {
                float[] w = mWidths;
                w[mPos] = wid;
                for (int i = mPos + 1, e = mPos + len; i < e; i++)
                    w[i] = 0;
            } else {
                mBuilder.addReplacementRun(mPos, mPos + len, wid);
            }
            mPos += len;
        }

        if (fm != null) {
            if (workPaint.baselineShift < 0) {
                fm.ascent += workPaint.baselineShift;
                fm.top += workPaint.baselineShift;
            } else {
                fm.descent += workPaint.baselineShift;
                fm.bottom += workPaint.baselineShift;
            }
        }

        return wid;
    }

    float addStyleRun4_4(TextPaint paint, MetricAffectingSpan[] spans, int len,
                      Paint.FontMetricsInt fm) {

        TextPaint workPaint = mWorkPaint;
        workPaint.set(paint);
        // XXX paint should not have a baseline shift, but...
        workPaint.baselineShift = 0;

        ReplacementSpan replacement = null;
        for (int i = 0; i < spans.length; i++) {
            MetricAffectingSpan span = spans[i];
            if (span instanceof ReplacementSpan) {
                replacement = (ReplacementSpan)span;
            } else {
                span.updateMeasureState(workPaint);
            }
        }

        float wid;
        if (replacement == null) {
            wid = addStyleRun4_4(workPaint, len, fm);
        } else {
            // Use original text.  Shouldn't matter.
            wid = replacement.getSize(workPaint, mText, mTextStart + mPos,
                    mTextStart + mPos + len, fm);
            float[] w = mWidths;
            w[mPos] = wid;
            for (int i = mPos + 1, e = mPos + len; i < e; i++)
                w[i] = 0;
            mPos += len;
        }

        if (fm != null) {
            if (workPaint.baselineShift < 0) {
                fm.ascent += workPaint.baselineShift;
                fm.top += workPaint.baselineShift;
            } else {
                fm.descent += workPaint.baselineShift;
                fm.bottom += workPaint.baselineShift;
            }
        }

        return wid;
    }

    int breakText(int limit, boolean forwards, float width) {
        float[] w = mWidths;
        if (forwards) {
            int i = 0;
            while (i < limit) {
                width -= w[i];
                if (width < 0.0f) break;
                i++;
            }
            while (i > 0 && mChars[i - 1] == ' ') i--;
            return i;
        } else {
            int i = limit - 1;
            while (i >= 0) {
                width -= w[i];
                if (width < 0.0f) break;
                i--;
            }
            while (i < limit - 1 && mChars[i + 1] == ' ') i++;
            return limit - i - 1;
        }
    }

    float measure(int start, int limit) {
        float width = 0;
        float[] w = mWidths;
        for (int i = start; i < limit; ++i) {
            width += w[i];
        }
        return width;
    }
}
