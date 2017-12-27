/**
 * Copyright (c) 2016-present, Facebook, Inc.
 * All rights reserved.
 * <p>
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.facebook.fbui.textlayoutbuilder;

import android.graphics.Paint;
import android.os.Build;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.StaticLayout4_4;
import android.text.StaticLayout5;
import android.text.StaticLayoutLike;
import android.text.TextPaint;
import android.text.TextUtils;

import com.facebook.fbui.textlayoutbuilder.proxy.StaticLayout4_4Proxy;
import com.facebook.fbui.textlayoutbuilder.proxy.StaticLayout5Proxy;
import com.facebook.fbui.textlayoutbuilder.proxy.StaticLayoutLikeProxy;
import com.facebook.fbui.textlayoutbuilder.proxy.StaticLayoutProxy;

/**
 * Helper class to get around the {@link Layout} constructor limitation in ICS.
 */
public class StaticLayoutHelper extends BaseHelper {

    public static boolean USER_SRC = false;

    private StaticLayoutHelper() {
        if (getStaticLayout().getHeight() == 17) {
            USER_SRC = true;
        } else {
            USER_SRC = false;
        }
    }

    private static StaticLayoutHelper helper;

    public static StaticLayoutHelper getInstance() {
        if (helper == null) {
            helper = new StaticLayoutHelper();
        }

        return helper;
    }

    @Override
    Layout createStaticLayout(CharSequence text,
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
            if (USER_SRC || Build.VERSION.SDK_INT >= 26 || Build.VERSION.SDK_INT <= 18) { // 8.0 和4.3以下使用原生代码
                return StaticLayoutProxy.create(text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth,
                        maxLines,
                        textDirection);
            } else if (Build.VERSION.SDK_INT >= 23) { // 6.0
                return StaticLayoutLikeProxy.create(text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth,
                        maxLines,
                        textDirection);
            } else if (Build.VERSION.SDK_INT >= 21) { // 5.0
                return StaticLayout5Proxy.create(text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth,
                        maxLines,
                        textDirection);
            } else if (Build.VERSION.SDK_INT == 19 || Build.VERSION.SDK_INT == 20) { // 4.4
                return StaticLayout4_4Proxy.create(text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth,
                        maxLines,
                        textDirection);
            } else {
                return StaticLayoutProxy.create(text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth,
                        maxLines,
                        textDirection);
            }
        } catch (Throwable e) {
            // 出现异常时，使用原生代码
            return StaticLayoutProxy.create(text,
                    start,
                    end,
                    paint,
                    width,
                    alignment,
                    spacingMult,
                    spacingAdd,
                    includePadding,
                    ellipsize,
                    ellipsisWidth,
                    maxLines,
                    textDirection);
        }
    }

    @Override
    Layout getStaticLayout(CharSequence text,
                           int start,
                           int end,
                           TextPaint paint,
                           int width,
                           Layout.Alignment alignment,
                           float spacingMult,
                           float spacingAdd,
                           boolean includePadding,
                           TextUtils.TruncateAt ellipsize,
                           int ellipsisWidth) {
        try {
            if (USER_SRC || Build.VERSION.SDK_INT >= 26 || Build.VERSION.SDK_INT <= 18) { // android 8.0 和 android 4.3以下
                return new StaticLayout(
                        text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth);
            } else if (Build.VERSION.SDK_INT >= 23) { // android 6.0
                return new StaticLayoutLike(
                        text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth);
            } else if (Build.VERSION.SDK_INT >= 21) { //android 5.0
                return new StaticLayout5(
                        text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth);
            } else if (Build.VERSION.SDK_INT == 19 || Build.VERSION.SDK_INT == 20) {// android 4.4
                return new StaticLayout4_4(
                        text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth);
            } else {
                return new StaticLayout(
                        text,
                        start,
                        end,
                        paint,
                        width,
                        alignment,
                        spacingMult,
                        spacingAdd,
                        includePadding,
                        ellipsize,
                        ellipsisWidth);
            }
        } catch (Throwable e) {
            // 出现异常时，使用原生代码
            return new StaticLayout(
                    text,
                    start,
                    end,
                    paint,
                    width,
                    alignment,
                    spacingMult,
                    spacingAdd,
                    includePadding,
                    ellipsize,
                    ellipsisWidth);
        }
    }

    private StaticLayout getStaticLayout() {
        TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        return new StaticLayout(
                "height",
                0,
                2,
                paint,
                64,
                StaticLayout.Alignment.ALIGN_NORMAL,
                1.0f,
                68.8f,
                true,
                null,
                64
        );
    }
}
