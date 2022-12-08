package com.githubyss.common.kit.util

import kotlin.reflect.KClass


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

/** ********** createClass ********** */

/**
 * Create Class of appointed Type.
 *
 * @param className The whole class name with package path. e.g., "android.app.ActivityThread".
 * @return JavaClass instance.
 */
fun createClass(className: String) =
    try {
        Class.forName(className)
    }
    catch (e: ClassNotFoundException) {
        logE(TAG, "createClass", e)
        null
    }

fun createClass(packageName: String, className: String) =
    try {
        Class.forName("$packageName.$className")
    }
    catch (e: ClassNotFoundException) {
        logE(TAG, "createClass", e)
        null
    }

/**
 * Create Class of appointed Type.
 *
 * @param className The whole class name with package path. e.g., "android.app.ActivityThread".
 * @param classLoader The classLoader.
 * @return JavaClass instance.
 */
fun createClass(className: String, classLoader: ClassLoader) =
    try {
        Class.forName(className, true, classLoader)
    }
    catch (e: ClassNotFoundException) {
        logE(TAG, "createClass", e)
        null
    }

fun createClass(packageName: String, className: String, classLoader: ClassLoader) =
    try {
        Class.forName("$packageName.$className", true, classLoader)
    }
    catch (e: ClassNotFoundException) {
        logE(TAG, "createClass", e)
        null
    }

fun createClass(clazz: Class<*>?) = clazz
fun createClass(clazz: KClass<*>?) = clazz?.java
fun createClass(instance: Any?) = instance?.javaClass
private fun createClass2(instance: Any?) = if (instance == null) null else instance::class.java
inline fun <reified T> createClass() = T::class.java
private inline fun <reified T> createClass2() = (T::class as Any).javaClass
private inline fun <reified T> createClass3() = (T::class as Any)::class.java

/** ********** createKClass ********** */

/**
 * Create KClass of appointed Type.
 *
 * @param className The whole class name with package path. e.g., "android.app.ActivityThread".
 * @return KotlinClass instance.
 */
fun createKClass(className: String) =
    try {
        Class.forName(className).kotlin
    }
    catch (e: ClassNotFoundException) {
        logE(TAG, "createClass", e)
        null
    }

fun createKClass(packageName: String, className: String) =
    try {
        Class.forName("$packageName.$className").kotlin
    }
    catch (e: ClassNotFoundException) {
        logE(TAG, "createClass", e)
        null
    }

/**
 * Create KClass of appointed Type.
 *
 * @param className The whole class name with package path. e.g., "android.app.ActivityThread".
 * @param classLoader The classLoader.
 * @return KotlinClass instance.
 */

fun createKClass(className: String, classLoader: ClassLoader) =
    try {
        Class.forName(className, true, classLoader).kotlin
    }
    catch (e: ClassNotFoundException) {
        logE(TAG, "createClass", e)
        null
    }

fun createKClass(packageName: String, className: String, classLoader: ClassLoader) =
    try {
        Class.forName("$packageName.$className", true, classLoader).kotlin
    }
    catch (e: ClassNotFoundException) {
        logE(TAG, "createClass", e)
        null
    }

fun createKClass(clazz: Class<*>?) = clazz?.kotlin
fun createKClass(clazz: KClass<*>?) = clazz
fun createKClass(instance: Any?) = instance?.javaClass?.kotlin
private fun createKClass2(instance: Any?) = if (instance == null) null else instance::class
inline fun <reified T : Any> createKClass() = T::class
private inline fun <reified T> createKClass2() = (T::class as Any).javaClass.kotlin
private inline fun <reified T> createKClass3() = (T::class as Any)::class

/** ********** createInstance ********** */

/**
 * Create Class instance of appointed Type.
 *
 * @param className The whole class name with package path. e.g., "android.app.ActivityThread".
 * @return
 */
fun <T> createInstance(className: String) = createClass(className)?.newInstance() as T
fun <T> createInstance(packageName: String, className: String) = createClass("$packageName.$className")?.newInstance() as T

fun <T> createInstance(clazz: Class<*>?) = createClass(clazz)?.newInstance() as T
fun <T> createInstance(clazz: KClass<*>?) = createClass(clazz)?.newInstance() as T
fun <T> createInstance(instance: Any?) = createClass(instance)?.newInstance() as T
inline fun <reified T> createInstance() = createClass<T>().newInstance() as T


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
