package com.githubyss.common.kit.util;


import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;

import static com.githubyss.common.kit.util.LogUtilsKt.logE;

/**
 * ClassUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/12/12 16:16:51
 */
public class ClassUtils {

    /** *************** Properties ****************************** */

    private static final String TAG = "ClassUtils";


    /** ****************************** Functions ****************************** */

    /** ******************** Creator ******************** */

    /** createClass ********** */

    public static Class<?> createClass(String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            logE(TAG, "createClass", e);
            return null;
        }
    }

    public static Class<?> createClass(String packageName, String className) {
        return createClass(packageName + "." + className);
    }

    public static Class<?> createClass(String className, ClassLoader classLoader) {
        try {
            return Class.forName(className, true, classLoader);
        }
        catch (ClassNotFoundException e) {
            logE(TAG, "createClass", e);
            return null;
        }
    }

    public static Class<?> createClass(String packageName, String className, ClassLoader classLoader) {
        return createClass(packageName + "." + className, classLoader);
    }

    public static Class<?> createClass(Class<?> clazz) {
        return clazz;
    }

    public static Class<?> createClass(KClass<?> kClass) {
        return JvmClassMappingKt.getJavaClass(kClass);
    }

    public static Class<?> createClass(Object instance) {
        return instance.getClass();
    }

    // public static <T> Class<?> createClass() {
    //     return
    // }
}
