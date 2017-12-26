package android.text.proxy;

import android.util.ArrayMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 80088966 on 2017/12/26.
 */

public class StaticMethodMap {
    static ArrayMap<String, Method> StaticMethedMap = new ArrayMap<>();

    public static void putMethod(String key, Method method) {
        StaticMethedMap.put(key, method);
    }

    public static Method getMethod(String key) {
        return StaticMethedMap.get(key);
    }

    static ArrayMap<String, Field> fieldMap = new ArrayMap<>();

    public static void putField(String key, Field field) {
        fieldMap.put(key, field);
    }

    public static Field getField(String key) {
        return fieldMap.get(key);
    }

    static ArrayMap<String, Constructor> constructorMap = new ArrayMap<>();

    public static void putConstructor(String key, Constructor constructor) {
        constructorMap.put(key, constructor);
    }

    public static Constructor getConstructor(String key) {
        return constructorMap.get(key);
    }
}
