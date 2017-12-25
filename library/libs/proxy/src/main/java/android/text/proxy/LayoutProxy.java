package android.text.proxy;

import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

/**
 * Created by 80088966 on 2017/12/19.
 */

public class LayoutProxy {
    private static String className = "android.text.Layout";

    public static <T> T[] getParagraphSpans(Spanned spanned, int i, int i1, java.lang.Class<T> aClass) {
        Class c;
        try {
            c = Class.forName(className);
            Class[] paramType = new Class[]{Spanned.class, int.class, int.class, java.lang.Class.class};
            Object[] paramValue = new Object[]{spanned, i, i1, aClass};
            Object object = ReflectHelp.invokeStatic(c, "getParagraphSpans", paramType, paramValue);
            return (T[]) object;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    /*    static final android.text.Layout.Directions DIRS_ALL_LEFT_TO_RIGHT;*/
    public static Layout.Directions getDIRS_ALL_LEFT_TO_RIGHT() {
        Class c;
        try {
            c = Class.forName(className);
            Object object = ReflectHelp.getFieldValue(c, "DIRS_ALL_LEFT_TO_RIGHT");
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
        Object ret = ReflectHelp.getFieldValue(object, "mWorkPaint");
        if(ret instanceof TextPaint) {
            return (TextPaint) ret;
        } else {
            return defaultPaint;
        }
    }
}
