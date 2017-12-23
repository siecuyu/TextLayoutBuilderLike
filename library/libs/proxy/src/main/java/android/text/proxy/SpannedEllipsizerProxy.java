package android.text.proxy;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

/**
 * Created by 80088966 on 2017/12/22.
 */

public class SpannedEllipsizerProxy {
    private static String className = "android.text.Layout$SpannedEllipsizer";

    public static java.lang.CharSequence getObjectFromInnerClass(java.lang.CharSequence charSequence) {
        Class[] paramType = new Class[]{java.lang.CharSequence.class};
        Object[] paramValue = new Object[]{charSequence};
        return (CharSequence) ReflectHelp.getObjectFromStaticInnerClass(className, paramType, paramValue);
    }
}
