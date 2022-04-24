package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RawRes
import androidx.core.content.ContextCompat
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import java.io.IOException
import java.io.InputStream
import java.io.UnsupportedEncodingException


/**
 * ResourceUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:15:26
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "ResourceUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/**
 * Get system resources.
 */
val systemResources: Resources = Resources.getSystem()

/**
 * Get resources.
 *
 * @param context The context.
 * @return The resources.
 */
fun getContextResources(context: Context? = ComkitApplicationConfig.getApp()): Resources {
    context ?: return Resources.getSystem()
    return context.resources
}

/**
 * Get integer.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The integer.
 */
fun getIntFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Int {
    context ?: return 0

    return try {
        getContextResources(context).getInteger(resId)
    }
    catch (e: Resources.NotFoundException) {
        logE(TAG, t = e)
        0
    }
}

/**
 * Get float.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The float.
 */
fun getFloatFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Float {
    context ?: return 0f

    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) getContextResources(context).getFloat(resId)
        else 0f
    }
    catch (e: Resources.NotFoundException) {
        logE(TAG, t = e)
        0f
    }
}

/**
 * Get boolean.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The boolean.
 */
fun getBooleanFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    context ?: return false

    return try {
        getContextResources(context).getBoolean(resId)
    }
    catch (e: Resources.NotFoundException) {
        logE(TAG, t = e)
        false
    }
}

/**
 * Return the string value associated with a particular resource ID.
 *
 * @param resId     The desired resource identifier.
 * @param resFormat The format arguments that will be used for substitution.
 * @param context   The context.
 * @return The string value associated with a particular resource ID.
 */
fun getStringFromRes(resId: Int, vararg resFormat: Any = emptyArray(), context: Context? = ComkitApplicationConfig.getApp()): String {
    context ?: return ""

    return try {
        if (resFormat.isEmpty()) getContextResources(context).getString(resId)
        else getContextResources(context).getString(resId, resFormat)
    }
    catch (e: Resources.NotFoundException) {
        logE(TAG, t = e)
        ""
    }
}

/**
 * Get color.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The color.
 */
fun getColorFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Int {
    context ?: return 0

    return try {
        ContextCompat.getColor(context, resId)
    }
    catch (e: Resources.NotFoundException) {
        logE(TAG, t = e)
        0
    }
}

/**
 * Get dimension.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The dimension.
 */
fun getDimensionFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Float {
    context ?: return 0f

    return try {
        getContextResources(context).getDimension(resId)
    }
    catch (e: Resources.NotFoundException) {
        logE(TAG, t = e)
        0f
    }
}

/**
 * Get dimension pixel size.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The dimension pixel size.
 */
fun getDimensionPixelSizeFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Int {
    context ?: return 0

    return try {
        getContextResources(context).getDimensionPixelSize(resId)
    }
    catch (e: Resources.NotFoundException) {
        logE(TAG, t = e)
        0
    }
}

/**
 * Get drawable.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The drawable.
 */
fun getDrawableFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Drawable? {
    context ?: return null

    return try {
        ContextCompat.getDrawable(context, resId)
    }
    catch (e: Resources.NotFoundException) {
        logE(TAG, t = e)
        null
    }
}

/**
 * Get the content string of assets.
 *
 * @param assetsFilePath The path of file in assets.
 * @param context        The context.
 * @return the content of assets
 */
fun getStringFromAssets(assetsFilePath: String?, context: Context? = ComkitApplicationConfig.getApp()): String {
    return readAssets2String(assetsFilePath, context = context)
}

/**
 * Return the drawable identifier by name.
 *
 * @param name    The name of drawable.
 * @param context The context.
 * @return the drawable identifier by name
 */
fun getDrawableIdByName(name: String?, context: Context? = ComkitApplicationConfig.getApp()): Int {
    name ?: return 0
    context ?: return 0

    return getContextResources(context).getIdentifier(name, "drawable", context.packageName)
}

/** ******************** Processor ******************** */

/** ********** Assets ********** */

/**
 * Copy the file from assets.
 *
 * @param assetsFilePath The path of file in assets.
 * @param destFilePath   The path of destination file.
 * @return `true`: success<br></br>`false`: fail
 */
fun copyFileFromAssets(assetsFilePath: String?, destFilePath: String?, context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    assetsFilePath ?: return false
    destFilePath ?: return false
    context ?: return false
    if (isSpace(assetsFilePath)) return false
    if (isSpace(destFilePath)) return false

    var res = true
    try {
        val assets: Array<String> = context.assets.list(assetsFilePath) as Array<String>
        if (assets.isNotEmpty()) {
            for (asset in assets) {
                res = res and copyFileFromAssets("$assetsFilePath/$asset", "$destFilePath/$asset")
            }
        }
        else {
            res = writeFileFromInput(destFilePath, context.assets.open(assetsFilePath), false)
        }
    }
    catch (e: IOException) {
        logE(TAG, t = e)
        res = false
    }
    return res
}

/**
 * Return the content of assets.
 *
 * @param assetsFilePath The path of file in assets.
 * @param charsetName    The name of charset.
 * @return the content of assets
 */
fun readAssets2String(assetsFilePath: String?, charsetName: String? = null, context: Context? = ComkitApplicationConfig.getApp()): String {
    assetsFilePath ?: return ""
    context ?: return ""
    if (isSpace(assetsFilePath)) return ""

    val `is`: InputStream = try {
        context.assets.open(assetsFilePath)
    }
    catch (e: IOException) {
        logE(TAG, t = e)
        return ""
    }
    val bytes = input2Bytes(`is`) ?: return ""
    return if (isSpace(charsetName)) {
        String(bytes)
    }
    else {
        try {
            String(bytes, charset(charsetName ?: return ""))
        }
        catch (e: UnsupportedEncodingException) {
            logE(TAG, t = e)
            ""
        }
    }
}

/**
 * Return the content of file in assets.
 *
 * @param assetsFilePath  The path of file in assets.
 * @param charsetName The name of charset.
 * @return the content of file in assets
 */
fun readAssets2List(assetsFilePath: String?, charsetName: String? = null, context: Context? = ComkitApplicationConfig.getApp()): List<String>? {
    assetsFilePath ?: return null
    if (isSpace(assetsFilePath)) return null

    return try {
        input2List(getContextResources(context).assets?.open(assetsFilePath) ?: return null, charsetName)
    }
    catch (e: IOException) {
        logE(TAG, t = e)
        null
    }
}

/** ********** Raw ********** */

/**
 * Copy the file from raw.
 *
 * @param resId        The resource id.
 * @param destFilePath The path of destination file.
 * @return `true`: success<br></br>`false`: fail
 */
fun copyFileFromRaw(@RawRes resId: Int, destFilePath: String?, context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    return writeFileFromInput(destFilePath, getContextResources(context).openRawResource(resId), false)
}

/**
 * Return the content of resource in raw.
 *
 * @param resId       The resource id.
 * @param charsetName The name of charset.
 * @return the content of resource in raw
 */
fun readRaw2String(@RawRes resId: Int, charsetName: String? = null, context: Context? = ComkitApplicationConfig.getApp()): String {
    val `is`: InputStream = getContextResources(context).openRawResource(resId)
    val bytes = input2Bytes(`is`) ?: return ""
    return if (isSpace(charsetName)) {
        String(bytes)
    }
    else {
        try {
            String(bytes, charset(charsetName ?: return ""))
        }
        catch (e: UnsupportedEncodingException) {
            logE(TAG, t = e)
            ""
        }
    }
}

/**
 * Return the content of resource in raw.
 *
 * @param resId       The resource id.
 * @param charsetName The name of charset.
 * @return the content of file in assets
 */
fun readRaw2List(@RawRes resId: Int, charsetName: String? = null, context: Context? = ComkitApplicationConfig.getApp()): List<String>? {
    return input2List(getContextResources(context).openRawResource(resId), charsetName)
}
