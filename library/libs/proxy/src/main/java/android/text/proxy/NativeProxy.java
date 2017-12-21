package android.text.proxy;

import android.text.StaticLayout;

import com.facebook.fbui.textlayoutbuilder.proxy.ReflectHelp;

/**
 * Created by 80088966 on 2017/12/21.
 */

public class NativeProxy {
    public static long nNewBuilder() {
        try {
            return (long) ReflectHelp.invokeStatic(StaticLayout.class, "nNewBuilder", null, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void nFreeBuilder(long nativePtr) {
        Class[] paramType = new Class[]{long.class};
        Object[] paramValue = new Object[]{nativePtr};
        ReflectHelp.invokeStatic(StaticLayout.class, "nFreeBuilder", paramType, paramValue);
    }

    public static void nFinishBuilder(long nativePtr) {
        Class[] paramType = new Class[]{long.class};
        Object[] paramValue = new Object[]{nativePtr};
        ReflectHelp.invokeStatic(StaticLayout.class, "nFinishBuilder", paramType, paramValue);
    }

//    /* package */ static native long nLoadHyphenator(ByteBuffer buf, int offset);

    public static void nSetLocale(long nativePtr, String locale, long nativeHyphenator) {
        Class[] paramType = new Class[]{long.class, String.class, long.class};
        Object[] paramValue = new Object[]{nativePtr, locale, nativeHyphenator};
        ReflectHelp.invokeStatic(StaticLayout.class, "nSetLocale", paramType, paramValue);
    }

    public static void nSetIndents(long nativePtr, int[] indents) {
        Class[] paramType = new Class[]{long.class, int[].class};
        Object[] paramValue = new Object[]{nativePtr, indents};
        ReflectHelp.invokeStatic(StaticLayout.class, "nSetIndents", paramType, paramValue);
    }

    // Set up paragraph text and settings; done as one big method to minimize jni crossings
    public static void nSetupParagraph(long nativePtr, char[] text, int length,
                                       float firstWidth, int firstWidthLineCount, float restWidth,
                                       int[] variableTabStops, int defaultTabStop, int breakStrategy,
                                       int hyphenationFrequency) {
        Class[] paramType = new Class[]{long.class, char[].class, int.class, float.class,
                int.class, float.class, int[].class, int.class, int.class, int.class};
        Object[] paramValue = new Object[]{nativePtr, text, length, firstWidth, firstWidthLineCount,
                restWidth, variableTabStops, defaultTabStop, breakStrategy, hyphenationFrequency};
        ReflectHelp.invokeStatic(StaticLayout.class, "nSetupParagraph", paramType, paramValue);
    }

    public static float nAddStyleRun(long nativePtr, long nativePaint,
                                     long nativeTypeface, int start, int end, boolean isRtl) {
        try {
            Class[] paramType = new Class[]{long.class, long.class, long.class, int.class,
                    int.class, boolean.class};
            Object[] paramValue = new Object[]{nativePtr, nativePaint, nativeTypeface, start, end, isRtl};
            return (float) ReflectHelp.invokeStatic(StaticLayout.class, "nAddStyleRun", paramType, paramValue);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void nAddMeasuredRun(long nativePtr,
                                       int start, int end, float[] widths) {
        Class[] paramType = new Class[]{long.class, int.class, int.class, float[].class};
        Object[] paramValue = new Object[]{nativePtr, start, end, widths};

        ReflectHelp.invokeStatic(StaticLayout.class, "nAddMeasuredRun", paramType, paramValue);

    }

    public static void nAddReplacementRun(long nativePtr, int start, int end, float width) {
        Class[] paramType = new Class[]{long.class, int.class, int.class, float.class};
        Object[] paramValue = new Object[]{nativePtr, start, end, width};

        ReflectHelp.invokeStatic(StaticLayout.class, "nAddReplacementRun", paramType, paramValue);
    }

    public static void nGetWidths(long nativePtr, float[] widths) {
        Class[] paramType = new Class[]{long.class, float[].class};
        Object[] paramValue = new Object[]{nativePtr, widths};

        ReflectHelp.invokeStatic(StaticLayout.class, "nGetWidths", paramType, paramValue);
    }

    // populates LineBreaks and returns the number of breaks found
    //
    // the arrays inside the LineBreaks objects are passed in as well
    // to reduce the number of JNI calls in the common case where the
    // arrays do not have to be resized
    public static int nComputeLineBreaks(long nativePtr, Object recycle,
                                         int[] recycleBreaks, float[] recycleWidths, int[] recycleFlags,
                                         int recycleLength) {
        Class[] paramType = new Class[]{long.class, LineBreaksProxy.getLineBreaksClass(), int[].class,
                float[].class, int[].class, int.class};
        Object[] paramValue = new Object[]{nativePtr, recycle, recycleBreaks, recycleWidths,
                recycleFlags, recycleLength};

        Object object = ReflectHelp.invokeStatic(StaticLayout.class, "nComputeLineBreaks", paramType, paramValue);

        if (object != null && object instanceof Integer) {
            return (int) object;
        }
        return 0;
    }
}
