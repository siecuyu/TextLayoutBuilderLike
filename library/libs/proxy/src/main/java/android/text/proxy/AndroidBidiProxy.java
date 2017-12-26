package android.text.proxy;

import android.text.Layout;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

import java.lang.reflect.Method;

/**
 * Created by 80088966 on 2017/12/20.
 */

public class AndroidBidiProxy {
    private static String className = "android.text.AndroidBidi";

    public static int bidi(int var0, char[] var1, byte[] var2, int var3, boolean var4) {
        try {
            String key = className + "." + "bidi";
            Method method = StaticMethodMap.getMethod(key);
            if (null == method) {
                Class c = Class.forName(className);
                Class[] paramType = new Class[]{int.class, char[].class, byte[].class, int.class, boolean.class};
                method = ReflectHelp.getMethod(c, "bidi", paramType);
                StaticMethodMap.putMethod(key, method);
            }

            Object[] paramValue = new Object[]{var0, var1, var2, var3, var4};
            return (int) ReflectHelp.invokeStatic(method, paramValue);
        } catch (Throwable e) {
        }

        return 0;
    }

    public static Layout.Directions directions(int var0, byte[] var1, int var2, char[] var3, int var4, int var5) {
        try {
            String key = className + "." + "directions";
            Method method = StaticMethodMap.getMethod(key);
            if (null == method) {
                Class c = Class.forName(className);
                Class[] paramType = new Class[]{int.class, byte[].class, int.class, char[].class, int.class, int.class};
                method = ReflectHelp.getMethod(c, "directions", paramType);
                StaticMethodMap.putMethod(key, method);
            }

            Object[] paramValue = new Object[]{var0, var1, var2, var3, var4, var5};
            return (Layout.Directions) ReflectHelp.invokeStatic(method, paramValue);
        } catch (Throwable e) {
        }

        return null;
    }
}
