package android.text.proxy;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

/**
 * Created by 80088966 on 2017/12/20.
 */

public class LineBreaksProxy {
    private static String className = "android.text.StaticLayout$LineBreaks";

    public static Class getLineBreaksClass() {
        return ReflectHelp.getClassFromName(className);
    }

    public static Object getObjectFromInnerClass() {
        return ReflectHelp.getObjectFromStaticInnerClass(className, null, null);
    }

    public static int[] breaks(Object object) {
        Object ret = ReflectHelp.getFieldValue(object, "breaks");
        if(ret instanceof Integer[]) {
            return (int[]) ret;
        } else {
            return new int[16];
        }
    }

    public static int[] flags(Object object) {
        Object ret = ReflectHelp.getFieldValue(object, "flags");
        if(ret instanceof Integer[]) {
            return (int[]) ret;
        } else {
            return new int[16];
        }
    }

    public static float[] widths(Object object) {
        Object ret = ReflectHelp.getFieldValue(object, "widths");
        if(ret instanceof float[]) {
            return (float[]) ret;
        } else {
            return new float[16];
        }
    }
}
