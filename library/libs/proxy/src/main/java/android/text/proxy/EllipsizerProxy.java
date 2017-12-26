package android.text.proxy;

import android.text.Layout;
import android.text.TextUtils;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by 80088966 on 2017/12/22.
 */

public class EllipsizerProxy {
    private static String className = "android.text.Layout$Ellipsizer";

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
            e.printStackTrace();
        }

        return null;
    }

    public static void setmLayout(Object object, Layout layout) {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "mLayout";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "mLayout");
                StaticMethodMap.putField(key, field);
            }

            ReflectHelp.setFieldValue(object, field, layout);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void setmWidth(Object object, int ellipsizedWidth) {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "mWidth";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "mWidth");
                StaticMethodMap.putField(key, field);
            }

            ReflectHelp.setFieldValue(object, field, ellipsizedWidth);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void setmMethod(Object object, TextUtils.TruncateAt truncateAt) {
        try {
            Class c = Class.forName(className);
            String key = className + "." + "mMethod";
            Field field = StaticMethodMap.getField(key);
            if (null == field) {
                field = ReflectHelp.getField(c, "mMethod");
                StaticMethodMap.putField(key, field);
            }

            ReflectHelp.setFieldValue(object, field, truncateAt);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
