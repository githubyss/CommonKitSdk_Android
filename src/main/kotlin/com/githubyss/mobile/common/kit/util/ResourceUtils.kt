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
 * Get resources.
 *
 * @param context The context.
 * @return The resources.
 */
fun getResources(context: Context? = ComkitApplicationConfig.getApp()): Resources {
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
    return getResources(context).getInteger(resId)
}

/**
 * Get float.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The float.
 */
fun getFloatFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Float {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        getResources(context).getFloat(resId)
    }
    else {
        -1.0f
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
    return getResources(context).getBoolean(resId)
}

/**
 * Return the string value associated with a particular resource ID.
 *
 * @param resId     The desired resource identifier.
 * @param resFormat The format arguments that will be used for substitution.
 * @param context   The context.
 * @return The string value associated with a particular resource ID.
 */
fun getStringFromRes(resId: Int, vararg resFormat: Any? = emptyArray(), context: Context? = ComkitApplicationConfig.getApp()): String {
    context ?: return ""

    return try {
        if (resFormat.isEmpty()) getResources(context).getString(resId)
        getResources(context).getString(resId, resFormat)
    }
    catch (ignore: Resources.NotFoundException) {
        ""
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
 * Get color.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The color.
 */
fun getColorFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Int {
    context ?: return -1
    return ContextCompat.getColor(context, resId)
}

/**
 * Get dimension.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The dimension.
 */
fun getDimensionFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Float {
    return getResources(context).getDimension(resId)
}

/**
 * Get dimension pixel size.
 *
 * @param resId   The resource ID.
 * @param context The context.
 * @return The dimension pixel size.
 */
fun getDimensionPixelSizeFromRes(resId: Int, context: Context? = ComkitApplicationConfig.getApp()): Int {
    return getResources(context).getDimensionPixelSize(resId)
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
    return ContextCompat.getDrawable(context, resId)
}

/**
 * Return the drawable identifier by name.
 *
 * @param name    The name of drawable.
 * @param context The context.
 * @return the drawable identifier by name
 */
fun getDrawableIdByName(name: String?, context: Context? = ComkitApplicationConfig.getApp()): Int {
    context ?: return 0
    return getResources(context).getIdentifier(name, "drawable", context.packageName)
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
        e.printStackTrace()
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

    val `is`: InputStream
    `is` = try {
        context.assets.open(assetsFilePath)
    }
    catch (e: IOException) {
        e.printStackTrace()
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
            e.printStackTrace()
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
        input2List(getResources(context).assets?.open(assetsFilePath) ?: return null, charsetName)
    }
    catch (e: IOException) {
        e.printStackTrace()
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
    return writeFileFromInput(destFilePath, getResources(context).openRawResource(resId), false)
}

/**
 * Return the content of resource in raw.
 *
 * @param resId       The resource id.
 * @param charsetName The name of charset.
 * @return the content of resource in raw
 */
fun readRaw2String(@RawRes resId: Int, charsetName: String? = null, context: Context? = ComkitApplicationConfig.getApp()): String {
    val `is`: InputStream = getResources(context).openRawResource(resId)
    val bytes = input2Bytes(`is`) ?: return ""
    return if (isSpace(charsetName)) {
        String(bytes)
    }
    else {
        try {
            String(bytes, charset(charsetName ?: return ""))
        }
        catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
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
    return input2List(getResources(context).openRawResource(resId), charsetName)
}
