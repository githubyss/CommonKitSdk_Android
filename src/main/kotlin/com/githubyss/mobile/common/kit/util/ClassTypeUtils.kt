package com.githubyss.mobile.common.kit.util

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 * ClassTypeUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/15 10:56:17
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "ClassTypeUtils"


/** ****************************** Functions ****************************** */

/**
 * Get actual type of generic nested
 *
 * - Get type of List<String>: [getType(List::class.java, String::class.java)]
 * - Get type of List<Int>: [getType(List::class.java, Int::class.javaObjectType)]
 * - Get type of List<List<Int>>: [getType(List::class.java, getType(List::class.java, Int::class.javaObjectType))]
 * - Get type of Map<String, Int>: [getType(Map::class.java, String::class.java, Int::class.javaObjectType)]
 * - Get type of Map<String, List<Int>>: [getType(Map::class.java, String::class.java, getType(List::class.java, Int::class.javaObjectType))]
 *
 * Kotlin 在使用基本类型时，会根据需要自动装箱（如 Int 在 Jvm 中会自动转成 int 或 Integer），而泛型要求使用的必须是 Object 类型，因此在使用 ParameterizedType 传入基本类型时，需要使用它们的包装类，比如 Int 的就是 Int::class.javaObjectType
 *
 * @param
 * @return
 */
fun getType(raw: Class<out Any>, vararg args: Type): ParameterizedType {
    return object : ParameterizedType {
        override fun getActualTypeArguments(): Array<out Type> {
            return args
        }

        override fun getRawType(): Type {
            return raw
        }

        override fun getOwnerType(): Type? {
            return null
        }
    }
}

/**
 * Get actual type of generic nested
 *
 * - Get type of List<String>: [getType<List<String>>()]
 * - Get type of List<Int>: [getType<List<Int>>()]
 * - Get type of List<List<Int>>: [getType<List<List<Int>>>()]
 * - Get type of Map<String, Int>: [getType<Map<String, Int>>()]
 * - Get type of Map<String, List<Int>>: [getType<Map<String, List<Int>>>()]
 *
 * @param
 * @return
 */
inline fun <reified T> getType(): Type {
    return object : ClassType<T>() {}.type
}

abstract class ClassType<T> {
    val type: Type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
}