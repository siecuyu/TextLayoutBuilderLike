package android.text.proxy;

import android.text.Layout;
import android.text.TextUtils;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

/**
 * Created by 80088966 on 2017/12/22.
 */

public class EllipsizerProxy {
    private static String className = "android.text.Layout$Ellipsizer";

    public static Class getEllipsizerClass() {
        return ReflectHelp.getClassFromName(className);
    }

    public static java.lang.CharSequence getObjectFromInnerClass(java.lang.CharSequence charSequence) {
        Class[] paramType = new Class[]{java.lang.CharSequence.class};
        Object[] paramValue = new Object[]{charSequence};
        return (CharSequence) ReflectHelp.getObjectFromStaticInnerClass(className, paramType, paramValue);
    }

//    e.mLayout = this;
//    e.mWidth = ellipsizedWidth;
//    e.mMethod = ellipsize;
    public static void setmLayout(Object object, Layout layout) {
        Class c;
        try {
            c = Class.forName(className);
            ReflectHelp.setFieldValue(c, object, "mLayout", layout);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void setmWidth(Object object, int ellipsizedWidth) {
        Class c;
        try {
            c = Class.forName(className);
            ReflectHelp.setFieldValue(c, object, "mWidth", ellipsizedWidth);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void setmMethod(Object object, TextUtils.TruncateAt truncateAt) {
        Class c;
        try {
            c = Class.forName(className);
            ReflectHelp.setFieldValue(c, object, "mMethod", truncateAt);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
