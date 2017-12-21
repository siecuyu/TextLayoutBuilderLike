package com.facebook.fbui.textlayoutbuilder.proxy;


import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectHelp {

	public static Object getObjectByConstructor(String className, Class[] intArgsClass, Object[] intArgs) {

		Object returnObj = null;
		try {
			Class classType = Class.forName(className);
			Constructor constructor = classType.getDeclaredConstructor(intArgsClass); 
			constructor.setAccessible(true);
			returnObj = constructor.newInstance(intArgs);
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnObj;
	}

	public static void modifyFileValue(Object object, String filedName, String filedValue) {
		Class classType = object.getClass();
		Field fild = null;
		try {
			fild = classType.getDeclaredField(filedName);
			fild.setAccessible(true);
			fild.set(object, filedValue);
		} catch (NoSuchFieldException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	public static Object getFieldValue(Object object, String filedName) {
		Class classType = object.getClass();
		Field fild = null;
		Object fildValue = null;
		try {
			fild = classType.getDeclaredField(filedName);
			fild.setAccessible(true);
			fildValue = fild.get(object);

		} catch (NoSuchFieldException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fildValue;
	}

	
	public static Object invoke( Object obj, String methodName, Class[] paramType, Object[] paramValue) {
		if (obj == null || TextUtils.isEmpty(methodName)) {
			return null;
		}
		Class c = obj.getClass();
		try {
			Method m = getMethod(c, methodName, paramType);

			StringBuilder stringBuilder = new StringBuilder();
			if (paramValue != null) {
				for (int i = 0; i < paramValue.length; i++) {
					stringBuilder.append(paramValue[i]).append(",");
				}
			}
			Log.i("test", "invoke paramValue = " + stringBuilder.toString());

			if (m != null) {
				m.setAccessible(true);
				return m.invoke(obj, paramValue);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;

	}

    public static Object invokeThrowException(Object obj, String methodName, Class[] paramType, Object[] paramValue)
            throws Exception {
        if (obj == null || TextUtils.isEmpty(methodName)) {
            throw new IllegalArgumentException("obj == null or method is null");
        }
        Class c = obj.getClass();
        Method m = getMethod(c, methodName, paramType);

		StringBuilder stringBuilder = new StringBuilder();
		if (paramValue != null) {
			for (int i = 0; i < paramValue.length; i++) {
				stringBuilder.append(paramValue[i]).append(",");
			}
		}
		Log.i("test", "invokeThrowException paramValue = " + stringBuilder.toString());

        if (m != null) {
            m.setAccessible(true);
            return m.invoke(obj, paramValue);
        }

        throw new IllegalStateException("method is null");
    }

	public static Method getMethod(Class c, String methodName, Class[] paramClass) {
		if (c == null || TextUtils.isEmpty(methodName)) {
			return null;
		}
		Method m = null;

		StringBuilder stringBuilder = new StringBuilder();
		if (paramClass != null) {
			for (int i = 0; i < paramClass.length; i++) {
				stringBuilder.append(paramClass[i]).append(",");
			}
		}
		Log.i("test", "getMethod Class = " + c + ", methodName = " + methodName
				+ ", paramType = " + stringBuilder.toString());

		try {
			m = c.getDeclaredMethod(methodName, paramClass);
		} catch (Exception e) {
			try {
				m = c.getMethod(methodName, paramClass);
			} catch (Exception e1) {
				if (c.getSuperclass() != null) {
					m = getMethod(c.getSuperclass(), methodName, paramClass);
				} else {
					Log.i("test", "getMethod Method1 = " + m);
					return m;
				}
			}
		}

		Log.i("test", "getMethod Method2 = " + m);
		return m;
	}

	
	public static void setFieldValue(Class c, Object obj, String fieldName, Object value) {
		if ((obj == null && c == null) || TextUtils.isEmpty(fieldName))
			return;
		if (obj != null)
			c = obj.getClass();
		Field field = getField(c, fieldName);
		if (field != null) {
			field.setAccessible(true);
			try {
				field.set(obj, value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public static Object getFieldValue(Class c, Object obj, String fieldName) {
		if ((obj == null && c == null) || TextUtils.isEmpty(fieldName)) {
			return null;
		}
		if (obj != null)
			c = obj.getClass();
		Field field = getField(c, fieldName);
		if (field != null) {
			field.setAccessible(true);
			try {
				return field.get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Object getFieldValue(Class c, String fieldName) {
		if (c == null || TextUtils.isEmpty(fieldName)) {
			return null;
		}

		Field field = getField(c, fieldName);
		if (field != null) {
			field.setAccessible(true);
			try {
				return field.get(c);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Field getField(Class c, String fieldName) {
		if (c == null || TextUtils.isEmpty(fieldName)) {
			return null;
		}
		Field field = null;
		try {
			field = c.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			try {
				field = c.getField(fieldName);
			} catch (NoSuchFieldException e1) {
				if (c.getSuperclass() != null) {
					field = getField(c.getSuperclass(), fieldName);
				} else {
					return field;
				}
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return field;
	}

	public static Constructor getConstructor(String className, Class[] parameterTypes) {
		Constructor constructor = null;
		Class c;
		try {
			c = Class.forName(className);
			if (c != null) {
				constructor = c.getDeclaredConstructor(parameterTypes);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return constructor;
	}

	
	public static Constructor getConstructorForInnerClass(String innerClassName, Class[] parameterTypes) {
		Constructor constructor = null;
		Class c, innerClass;
		String className = "";
		String[] strArray = innerClassName.split("\\$");
		Class[] newParams = new Class[parameterTypes != null ? parameterTypes.length + 1 : 1];
		if (strArray.length >= 1) {
			className = strArray[0];
			try {
				c = Class.forName(className);
				newParams[0] = c;
				if (parameterTypes != null) {
					for (int i = 0; i < parameterTypes.length; i++) {
						newParams[i + 1] = parameterTypes[i];
					}
				}
				innerClass = Class.forName(innerClassName);
				if (c != null && innerClass != null) {
					constructor = c.getDeclaredConstructor(newParams);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return constructor;
	}
	
	
	public static Object getObjectFromInnerClass(String innerClassName, Class[] outClassParamsTypes,
			Object[] outClassParamsArgs, Class[] parameterTypes, Object[] paramsArgs) {
		Object result = null;
		try {
			Constructor con = getConstructorForInnerClass(innerClassName, parameterTypes);
			if (con != null) {
				Object[] newParamsObj = new Object[paramsArgs != null ? paramsArgs.length + 1 : 1];
				if (newParamsObj.length >= 1) {
					String outClassName = "";
					String[] strArray = innerClassName.split("\\$");
					if (strArray.length > 0) {
						outClassName = strArray[0];
						newParamsObj[0] = getConstructor(outClassName, outClassParamsTypes).newInstance(
								outClassParamsArgs);
						if (paramsArgs != null) {
							for (int i = 0; i < paramsArgs.length; i++) {
								newParamsObj[i + 1] = paramsArgs[i];
							}
						}
						result = getConstructorForInnerClass(innerClassName, parameterTypes).newInstance(newParamsObj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Class getClassFromName(String className) {
		Class c = null;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}
	

	public static Object invokeStatic( Class c, String methodName, Class[] paramType, Object[] paramValue) {
		if (c == null || TextUtils.isEmpty(methodName)) {
			return null;
		}
		try {
			Method m = getMethod(c, methodName, paramType);

			StringBuilder stringBuilder = new StringBuilder();
			if (paramValue != null) {
				for (int i = 0; i < paramValue.length; i++) {
					stringBuilder.append(paramValue[i]).append(",");
				}
			}
			Log.i("test", "invokeStatic paramValue = " + stringBuilder.toString());

			if (m != null) {
				m.setAccessible(true);
				return m.invoke(null, paramValue);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Constructor getConstructorForStaticInnerClass(String innerClassName, Class[] parameterTypes) {
		Constructor constructor = null;
		Class innerClass;
		try {
			innerClass = Class.forName(innerClassName);
			if (innerClass != null) {
				constructor = innerClass.getDeclaredConstructor(parameterTypes);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return constructor;
	}

	public static Object getObjectFromStaticInnerClass(String innerClassName, Class[] parameterTypes,
													   Object[] paramsArgs) {
		Object result = null;
		try {
			Constructor con = getConstructorForStaticInnerClass(innerClassName, parameterTypes);
			if (con != null) {
				con.setAccessible(true);
				result = con.newInstance(paramsArgs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
