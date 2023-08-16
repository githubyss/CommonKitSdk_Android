package com.githubyss.common.kit.util;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * ClassTypeUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/12/12 16:46:33
 */
public class ClassTypeUtils {

    /** *********************** Properties ****************************** */

    private static final String TAG = "ClassTypeUtils";


    /** ************************* Functions ****************************** */

    public static <T> Class<?> getTypeClassFromParadigm(final IFormatter<T> formatter) {
        Type[] genericInterfaces = formatter.getClass().getGenericInterfaces();
        Type type;
        if (genericInterfaces.length == 1) {
            type = genericInterfaces[0];
        }
        else {
            type = formatter.getClass().getGenericSuperclass();
        }
        type = ((ParameterizedType) type).getActualTypeArguments()[0];
        while (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        String className = type.toString();
        if (className.startsWith("class ")) {
            className = className.substring(6);
        }
        else if (className.startsWith("interface ")) {
            className = className.substring(10);
        }
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> getClassFromObject(final Object obj) {
        Class<?> objClass = obj.getClass();
        if (objClass.isAnonymousClass() || objClass.isSynthetic()) {
            Type[] genericInterfaces = objClass.getGenericInterfaces();
            String className;
            if (genericInterfaces.length == 1) {// interface
                Type type = genericInterfaces[0];
                while (type instanceof ParameterizedType) {
                    type = ((ParameterizedType) type).getRawType();
                }
                className = type.toString();
            }
            else {// abstract class or lambda
                Type type = objClass.getGenericSuperclass();
                while (type instanceof ParameterizedType) {
                    type = ((ParameterizedType) type).getRawType();
                }
                className = type.toString();
            }

            if (className.startsWith("class ")) {
                className = className.substring(6);
            }
            else if (className.startsWith("interface ")) {
                className = className.substring(10);
            }
            try {
                return Class.forName(className);
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return objClass;
    }


    public abstract static class IFormatter<T> {
        public abstract String format(T t);
    }

    public <T> Class<T> getTClass() {
        Type type = getClass().getGenericSuperclass();
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("Type must be a parameterized type");
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        // 获取泛型的具体类型  这里是单泛型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (null == actualTypeArguments || actualTypeArguments.length < 1) {
            throw new IllegalStateException("Number of type arguments must be 1");
        }
        return (Class<T>) actualTypeArguments[0];
    }
}
