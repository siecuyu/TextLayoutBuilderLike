package android.text.proxy;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

/**
 * Created by 80088966 on 2017/12/20.
 */

public class TextUtilsProxy {
    private static String className = "android.text.TextUtils";

    public static boolean doesNotNeedBidi(char[] chars, int i, int i1) {
        Class c;
        try {
            c = Class.forName(className);
            Class[] paramType = new Class[]{char[].class, int.class, int.class};
            Object[] paramValue = new Object[]{chars, i, i1};

            return (boolean) ReflectHelp.invokeStatic(c, "doesNotNeedBidi", paramType, paramValue);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return false;
    }

    public static char[] getELLIPSIS_TWO_DOTS() {
        Class c;
        try {
            c = Class.forName(className);
            Object object = ReflectHelp.getFieldValue(c, "ELLIPSIS_TWO_DOTS");
            if (object instanceof char[]) {
                return (char[]) object;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    public static char[] getELLIPSIS_NORMAL() {
        Class c;
        try {
            c = Class.forName(className);
            Object object = ReflectHelp.getFieldValue(c, "ELLIPSIS_NORMAL");
            if (object instanceof char[]) {
                return (char[]) object;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }
}
