package com.facebook.fbui.textlayoutbuilder.proxy;

import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.text.Layout;
import android.text.StaticLayout4_4;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;

/**
 * Created by 80088966 on 2017/12/25.
 */

public class StaticLayout4_4Proxy {
    public static StaticLayout4_4 create(
            CharSequence text,
            int start,
            int end,
            TextPaint paint,
            int width,
            Layout.Alignment alignment,
            float spacingMult,
            float spacingAdd,
            boolean includePadding,
            TextUtils.TruncateAt ellipsize,
            int ellipsisWidth,
            int maxLines,
            TextDirectionHeuristicCompat textDirection) {
        try {
            return new StaticLayout4_4(
                    text,
                    start,
                    end,
                    paint,
                    width,
                    alignment,
                    fromTextDirectionHeuristicCompat(textDirection),
                    spacingMult,
                    spacingAdd,
                    includePadding,
                    ellipsize,
                    ellipsisWidth,
                    maxLines);
        } catch (IllegalArgumentException e) {
            // Retry creating the layout if the first attempt failed due to a race condition.
            // See https://code.google.com/p/android/issues/detail?id=188163
            if (e.getMessage().contains("utext_close")) {
                return new StaticLayout4_4(
                        text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        fromTextDirectionHeuristicCompat(textDirection),
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth,
                        maxLines);
            }
            throw e;
        }
    }

    private static TextDirectionHeuristic fromTextDirectionHeuristicCompat(
            TextDirectionHeuristicCompat textDirection) {
        if (textDirection == TextDirectionHeuristicsCompat.LTR) {
            return TextDirectionHeuristics.LTR;
        } else if (textDirection == TextDirectionHeuristicsCompat.RTL) {
            return TextDirectionHeuristics.RTL;
        } else if (textDirection == TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR) {
            return TextDirectionHeuristics.FIRSTSTRONG_LTR;
        } else if (textDirection == TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL) {
            return TextDirectionHeuristics.FIRSTSTRONG_RTL;
        } else if (textDirection == TextDirectionHeuristicsCompat.ANYRTL_LTR) {
            return TextDirectionHeuristics.ANYRTL_LTR;
        } else if (textDirection == TextDirectionHeuristicsCompat.LOCALE) {
            return TextDirectionHeuristics.LOCALE;
        } else {
            return TextDirectionHeuristics.FIRSTSTRONG_LTR;
        }
    }
}
