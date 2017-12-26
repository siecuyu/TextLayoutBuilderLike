package android.text.proxy;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 80088966 on 2017/12/20.
 */

public class TextUtilsProxy {
    private static String className = "android.text.TextUtils";

    public static boolean doesNotNeedBidi(char[] chars, int i, int i1) {
        try {
            String key = className + "." + "doesNotNeedBidi";
            Method method = StaticMethodMap.getMethod(key);
            if (null == method) {
                Class c = Class.forName(className);
                Class[] paramType = new Class[]{char[].class, int.class, int.class};
                method = ReflectHelp.getMethod(c, "doesNotNeedBidi", paramType);
                StaticMethodMap.putMethod(key, method);
            }
            Object[] paramValue = new Object[]{chars, i, i1};

            return (boolean) ReflectHelp.invokeStatic(method, paramValue);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return false;
    }

    public static char[] getELLIPSIS_TWO_DOTS() {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "ELLIPSIS_TWO_DOTS";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "ELLIPSIS_TWO_DOTS");
                StaticMethodMap.putField(key, field);
            }

            Object object = ReflectHelp.getFieldValue(c, field);
            if (object instanceof char[]) {
                return (char[]) object;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    public static char[] getELLIPSIS_NORMAL() {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "ELLIPSIS_NORMAL";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "ELLIPSIS_NORMAL");
                StaticMethodMap.putField(key, field);
            }
            Object object = ReflectHelp.getFieldValue(c, field);
            if (object instanceof char[]) {
                return (char[]) object;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }
}
