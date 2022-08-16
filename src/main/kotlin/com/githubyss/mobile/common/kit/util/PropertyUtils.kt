package com.githubyss.mobile.common.kit.util

import android.content.Context
import com.githubyss.common.base.application.BaseApplicationHolder
import java.io.IOException
import java.io.InputStream
import java.util.*


/**
 * PropertyUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/14 12:12:57
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "PropertyUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */
fun loadProperties(path: String, context: Context = BaseApplicationHolder.getApp()): Properties? {
    val properties = Properties()
    try {
        val `in`: InputStream = context.assets.open(path)
        properties.load(`in`)
        `in`.close()
    }
    catch (e: IOException) {
        e.printStackTrace()
        return null
    }
    return properties
}

fun getProperties(path: String): Properties {
    val props = Properties()
    try {
        val `in`: InputStream? = Class::class.java.getResourceAsStream(path)
        props.load(`in`)
    }
    catch (e: Exception) {
        e.printStackTrace()
    }
    return props
}

fun getProperty(path: String, key: String) {
    getProperties(path).getProperty(key)
}