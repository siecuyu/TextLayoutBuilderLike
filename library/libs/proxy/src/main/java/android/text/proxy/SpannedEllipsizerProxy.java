package android.text.proxy;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

import java.lang.reflect.Constructor;

/**
 * Created by 80088966 on 2017/12/22.
 */

public class SpannedEllipsizerProxy {
    private static String className = "android.text.Layout$SpannedEllipsizer";

    public static java.lang.CharSequence getObjectFromInnerClass(java.lang.CharSequence charSequence) {

        try {
            String key = className;
            Constructor con = StaticMethodMap.getConstructor(key);
            if (null == con) {
                Class[] paramType = new Class[]{java.lang.CharSequence.class};
                con = ReflectHelp.getConstructorForStaticInnerClass(className, paramType);
                StaticMethodMap.putConstructor(key, con);
            }

            Object[] paramValue = new Object[]{charSequence};
            return (CharSequence) ReflectHelp.getObjectFromStaticInnerClass(con, paramValue);

        } catch (Exception e) {
        }

        return null;
    }
}
