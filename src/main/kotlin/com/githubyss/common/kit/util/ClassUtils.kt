package com.githubyss.common.kit.util


/**
 * ClassUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/13 10:40:25
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "ClassUtils"


/** ****************************** Functions ****************************** */

/** ******************** Creator ******************** */

/**
 * Create Class of appointed Type.
 *
 * @param classNameWithPackagePath The whole class name with package path.
 * @return
 */
inline fun <reified I> createClass(classNameWithPackagePath: String): I {
    val clazz: Class<out Any> = Class.forName(classNameWithPackagePath)
    return clazz.newInstance() as I
}

/**
 * Create Class of appointed Type.
 *
 * @param packagePath The package path.
 * @param className The class name.
 * @return
 */
inline fun <reified I> createClass(packagePath: String, className: String): I {
    val clazz: Class<out Any> = Class.forName("$packagePath.$className")
    return clazz.newInstance() as I
}

/** ******************** Checker ******************** */

/**
 * Checks if a class is a subclass of a class with the specified name.Used as an instance of without having to load the class,useful when trying to check for classes that might not be available in the runtime JRE.
 *
 * @param clazz The class to check
 * @param className The class name to look for in the super classes
 * @return true if the class extends a class by the specified name.
 */
fun extendsClass(clazz: Class<*>, className: String): Boolean {
    var superClass = clazz.superclass
    while (superClass != null) {
        if (superClass.name == className) {
            return true
        }
        superClass = superClass.superclass
    }
    return false
}

inline fun <reified T> extendsClass(className: String) = extendsClass(T::class.java, className)
