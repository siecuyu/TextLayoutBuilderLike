package android.text.proxy;

import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 80088966 on 2017/12/19.
 */

public class LayoutProxy {
    private static String className = "android.text.Layout";

    public static <T> T[] getParagraphSpans(Spanned spanned, int i, int i1, java.lang.Class<T> aClass) {
        try {
            String key = className + "." + "getParagraphSpans";
            Method method = StaticMethodMap.getMethod(key);
            if (null == method) {
                Class c = Class.forName(className);
                Class[] paramType = new Class[]{Spanned.class, int.class, int.class, java.lang.Class.class};
                method = ReflectHelp.getMethod(c, "getParagraphSpans", paramType);
                StaticMethodMap.putMethod(key, method);
            }

            Object[] paramValue = new Object[]{spanned, i, i1, aClass};
            return (T[]) ReflectHelp.invokeStatic(method, paramValue);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    /*    static final android.text.Layout.Directions DIRS_ALL_LEFT_TO_RIGHT;*/
    public static Layout.Directions getDIRS_ALL_LEFT_TO_RIGHT() {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "DIRS_ALL_LEFT_TO_RIGHT";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "DIRS_ALL_LEFT_TO_RIGHT");
                StaticMethodMap.putField(key, field);
            }

            Object object = ReflectHelp.getFieldValue(c, field);
            if (object instanceof Layout.Directions) {
                return (Layout.Directions) object;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    //    TextPaint mWorkPaint;
    public static TextPaint getmWorkPaint(Object object, TextPaint defaultPaint) {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "mWorkPaint";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "mWorkPaint");
                StaticMethodMap.putField(key, field);
            }

            Object ret = ReflectHelp.getFieldValue(object, field);
            if (ret instanceof TextPaint) {
                return (TextPaint) ret;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return defaultPaint;
    }
}
