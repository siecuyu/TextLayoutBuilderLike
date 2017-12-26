package android.text.proxy;

import android.text.StaticLayout;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

import java.lang.reflect.Method;

/**
 * Created by 80088966 on 2017/12/21.
 */

public class NativeProxy {
    private static String className = "android.text.StaticLayout";

    public static long nNewBuilder() {
        try {
            String key = className + "." + "nNewBuilder";
            Method method = StaticMethodMap.getMethod(key);
            if (null == method) {
                method = ReflectHelp.getMethod(StaticLayout.class, "nNewBuilder", null);
                StaticMethodMap.putMethod(key, method);
            }
            return (long) ReflectHelp.invokeStatic(method, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void nFreeBuilder(long nativePtr) {
        String key = className + "." + "nFreeBuilder";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{long.class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nFreeBuilder", paramType);
            StaticMethodMap.putMethod(key, method);
        }

        Object[] paramValue = new Object[]{nativePtr};
        ReflectHelp.invokeStatic(method, paramValue);
    }

    public static void nFinishBuilder(long nativePtr) {
        String key = className + "." + "nFinishBuilder";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{long.class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nFinishBuilder", paramType);
            StaticMethodMap.putMethod(key, method);
        }

        Object[] paramValue = new Object[]{nativePtr};
        ReflectHelp.invokeStatic(method, paramValue);
    }

//    /* package */ static native long nLoadHyphenator(ByteBuffer buf, int offset);

    public static void nSetLocale(long nativePtr, String locale, long nativeHyphenator) {
        String key = className + "." + "nSetLocale";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{long.class, String.class, long.class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nSetLocale", paramType);
            StaticMethodMap.putMethod(key, method);
        }

        Object[] paramValue = new Object[]{nativePtr, locale, nativeHyphenator};
        ReflectHelp.invokeStatic(method, paramValue);
    }

    public static void nSetIndents(long nativePtr, int[] indents) {
        String key = className + "." + "nSetIndents";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{long.class, int[].class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nSetIndents", paramType);
            StaticMethodMap.putMethod(key, method);
        }

        Object[] paramValue = new Object[]{nativePtr, indents};
        ReflectHelp.invokeStatic(method, paramValue);
    }

    // Set up paragraph text and settings; done as one big method to minimize jni crossings
    public static void nSetupParagraph(long nativePtr, char[] text, int length,
                                       float firstWidth, int firstWidthLineCount, float restWidth,
                                       int[] variableTabStops, int defaultTabStop, int breakStrategy,
                                       int hyphenationFrequency) {
        String key = className + "." + "nSetupParagraph";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{long.class, char[].class, int.class, float.class,
                    int.class, float.class, int[].class, int.class, int.class, int.class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nSetupParagraph", paramType);
            StaticMethodMap.putMethod(key, method);
        }

        Object[] paramValue = new Object[]{nativePtr, text, length, firstWidth, firstWidthLineCount,
                restWidth, variableTabStops, defaultTabStop, breakStrategy, hyphenationFrequency};
        ReflectHelp.invokeStatic(method, paramValue);
    }

    public static float nAddStyleRun(long nativePtr, long nativePaint,
                                     long nativeTypeface, int start, int end, boolean isRtl) {
        try {
            String key = className + "." + "nAddStyleRun";
            Method method = StaticMethodMap.getMethod(key);
            if (null == method) {
                Class[] paramType = new Class[]{long.class, long.class, long.class, int.class,
                        int.class, boolean.class};
                method = ReflectHelp.getMethod(StaticLayout.class, "nAddStyleRun", paramType);
                StaticMethodMap.putMethod(key, method);
            }

            Object[] paramValue = new Object[]{nativePtr, nativePaint, nativeTypeface, start, end, isRtl};
            return (float) ReflectHelp.invokeStatic(method, paramValue);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void nAddMeasuredRun(long nativePtr,
                                       int start, int end, float[] widths) {
        String key = className + "." + "nAddMeasuredRun";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{long.class, int.class, int.class, float[].class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nAddMeasuredRun", paramType);
            StaticMethodMap.putMethod(key, method);
        }
        Object[] paramValue = new Object[]{nativePtr, start, end, widths};

        ReflectHelp.invokeStatic(method, paramValue);

    }

    public static void nAddReplacementRun(long nativePtr, int start, int end, float width) {
        String key = className + "." + "nAddReplacementRun";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{long.class, int.class, int.class, float.class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nAddReplacementRun", paramType);
            StaticMethodMap.putMethod(key, method);
        }
        Object[] paramValue = new Object[]{nativePtr, start, end, width};

        ReflectHelp.invokeStatic(method, paramValue);
    }

    public static void nGetWidths(long nativePtr, float[] widths) {
        String key = className + "." + "nGetWidths";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{long.class, float[].class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nGetWidths", paramType);
            StaticMethodMap.putMethod(key, method);
        }
        Object[] paramValue = new Object[]{nativePtr, widths};

        ReflectHelp.invokeStatic(method, paramValue);
    }

    // populates LineBreaks and returns the number of breaks found
    //
    // the arrays inside the LineBreaks objects are passed in as well
    // to reduce the number of JNI calls in the common case where the
    // arrays do not have to be resized
    public static int nComputeLineBreaks(long nativePtr, Object recycle,
                                         int[] recycleBreaks, float[] recycleWidths, int[] recycleFlags,
                                         int recycleLength) {
        String key = className + "." + "nComputeLineBreaks";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{long.class, LineBreaksProxy.getLineBreaksClass(), int[].class,
                    float[].class, int[].class, int.class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nComputeLineBreaks", paramType);
            StaticMethodMap.putMethod(key, method);
        }

        Object[] paramValue = new Object[]{nativePtr, recycle, recycleBreaks, recycleWidths,
                recycleFlags, recycleLength};

        Object object = ReflectHelp.invokeStatic(method, paramValue);

        if (object != null && object instanceof Integer) {
            return (int) object;
        }

        return 0;
    }

    public static int[] nLineBreakOpportunities(String locale, char[] text, int length, int[] recycle) {
        String key = className + "." + "nLineBreakOpportunities";
        Method method = StaticMethodMap.getMethod(key);
        if (null == method) {
            Class[] paramType = new Class[]{String.class, char[].class, int.class, int[].class};
            method = ReflectHelp.getMethod(StaticLayout.class, "nLineBreakOpportunities", paramType);
            StaticMethodMap.putMethod(key, method);
        }

        Object[] paramValue = new Object[]{locale, text, length, recycle};

        Object object = ReflectHelp.invokeStatic(method, paramValue);

        if (object != null && object instanceof int[]) {
            return (int[]) object;
        }

        return null;
    }

}
