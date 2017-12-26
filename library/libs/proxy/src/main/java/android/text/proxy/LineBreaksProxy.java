package android.text.proxy;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by 80088966 on 2017/12/20.
 */

public class LineBreaksProxy {
    private static String className = "android.text.StaticLayout$LineBreaks";

    public static Class getLineBreaksClass() {
        return ReflectHelp.getClassFromName(className);
    }

    public static Object getObjectFromInnerClass() {
        try {
            String key = className;
            Constructor con = StaticMethodMap.getConstructor(key);
            if (null == con) {
                con = ReflectHelp.getConstructorForStaticInnerClass(className, null);
                StaticMethodMap.putConstructor(key, con);
            }

            return ReflectHelp.getObjectFromStaticInnerClass(con, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int[] breaks(Object object) {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "breaks";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "breaks");
                StaticMethodMap.putField(key, field);
            }

            Object ret = ReflectHelp.getFieldValue(object, field);
            if (ret instanceof int[]) {
                return (int[]) ret;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return new int[16];
    }

    public static int[] flags(Object object) {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "flags";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "flags");
                StaticMethodMap.putField(key, field);
            }

            Object ret = ReflectHelp.getFieldValue(object, field);
            if (ret instanceof int[]) {
                return (int[]) ret;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return new int[16];
    }

    public static float[] widths(Object object) {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "widths";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "widths");
                StaticMethodMap.putField(key, field);
            }

            Object ret = ReflectHelp.getFieldValue(object, field);
            if (ret instanceof float[]) {
                return (float[]) ret;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return new float[16];
    }
}
