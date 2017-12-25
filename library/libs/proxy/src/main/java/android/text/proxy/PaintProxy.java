package android.text.proxy;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

/**
 * Created by 80088966 on 2017/12/25.
 */

public class PaintProxy {
    private static String className = "android.graphics.Paint";

    public static float getTextRunAdvances(Object object, char[] chars, int index, int count,
                                           int contextIndex, int contextCount, int flags, float[] advances,
                                           int advancesIndex) {
        Class[] paramType = new Class[]{char[].class, int.class, int.class, int.class, int.class, int.class,
                float[].class, int.class};
        Object[] paramValue = new Object[]{chars, index, count, contextIndex, contextCount, flags, advances,
                advancesIndex};
        return (float) ReflectHelp.invoke(object, "getTextRunAdvances", paramType, paramValue);
    }
}
