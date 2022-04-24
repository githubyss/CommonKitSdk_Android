package com.githubyss.mobile.common.kit.util


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