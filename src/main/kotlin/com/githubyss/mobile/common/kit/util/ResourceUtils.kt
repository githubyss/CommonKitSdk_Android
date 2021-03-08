package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.graphics.drawable.Drawable
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
object ResourceUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = ResourceUtils::class.simpleName ?: "simpleName is null"
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Getter ********** ********** */
    
    /**
     * Get string.
     *
     * @param context   The context.
     * @param resId     The resource ID.
     * @param resFormat The resource format.
     * @return The string.
     */
    fun getString(resId: Int, context: Context = ComkitApplicationConfig.getApp(), vararg resFormat: Any? = emptyArray()): String {
        return if (resFormat.isEmpty()) context.resources.getString(resId) else context.resources.getString(resId, resFormat)
    }
    
    /**
     * Get color.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The color.
     */
    fun getColor(resId: Int, context: Context = ComkitApplicationConfig.getApp()): Int {
        return ContextCompat.getColor(context, resId)
    }
    
    /**
     * Get dimension.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The dimension.
     */
    fun getDimension(resId: Int, context: Context = ComkitApplicationConfig.getApp()): Float {
        return context.resources.getDimension(resId)
    }
    
    /**
     * Get dimension pixel size.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The dimension pixel size.
     */
    fun getDimensionPixelSize(resId: Int, context: Context = ComkitApplicationConfig.getApp()): Int {
        return context.resources.getDimensionPixelSize(resId)
    }
    
    /**
     * Get drawable.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The drawable.
     */
    fun getDrawable(resId: Int, context: Context = ComkitApplicationConfig.getApp()): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }
    
    /**
     * Return the drawable identifier by name.
     *
     * @param name The name of drawable.
     * @return the drawable identifier by name
     */
    fun getDrawableIdByName(name: String?, context: Context = ComkitApplicationConfig.getApp()): Int {
        return context.resources.getIdentifier(name, "drawable", context.packageName)
    }
    
    /** ********** ********** Processor ********** ********** */
    
    /** ********** Assets ********** */
    
    /**
     * Copy the file from assets.
     *
     * @param assetsFilePath The path of file in assets.
     * @param destFilePath   The path of destination file.
     * @return `true`: success<br></br>`false`: fail
     */
    fun copyFileFromAssets(assetsFilePath: String?, destFilePath: String?, context: Context = ComkitApplicationConfig.getApp()): Boolean {
        var res = true
        try {
            val assets: Array<String> = context.assets.list(assetsFilePath ?: return false) as Array<String>
            if (assets.isNotEmpty()) {
                for (asset in assets) {
                    res = res and copyFileFromAssets("$assetsFilePath/$asset", "$destFilePath/$asset")
                }
            } else {
                res = FileUtils.writeFileFromInput(destFilePath ?: return false, context.assets.open(assetsFilePath), false)
            }
        } catch (e: IOException) {
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
    fun readAssets2String(assetsFilePath: String?, charsetName: String? = null, context: Context = ComkitApplicationConfig.getApp()): String {
        val `is`: InputStream
        `is` = try {
            context.assets.open(assetsFilePath ?: return "")
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        val bytes: ByteArray = ConvertUtils.input2Bytes(`is`) ?: return ""
        return if (StringUtils.isSpace(charsetName)) {
            String(bytes)
        } else {
            try {
                String(bytes, charset(charsetName ?: return ""))
            } catch (e: UnsupportedEncodingException) {
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
    fun readAssets2List(assetsFilePath: String?, charsetName: String? = null, context: Context = ComkitApplicationConfig.getApp()): List<String?>? {
        return try {
            ConvertUtils.input2List(context.resources.assets.open(assetsFilePath ?: return null), charsetName)
        } catch (e: IOException) {
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
    fun copyFileFromRaw(@RawRes resId: Int, destFilePath: String?, context: Context = ComkitApplicationConfig.getApp()): Boolean {
        return FileUtils.writeFileFromInput(destFilePath, context.resources.openRawResource(resId), false)
    }
    
    /**
     * Return the content of resource in raw.
     *
     * @param resId       The resource id.
     * @param charsetName The name of charset.
     * @return the content of resource in raw
     */
    fun readRaw2String(@RawRes resId: Int, charsetName: String? = null, context: Context = ComkitApplicationConfig.getApp()): String {
        val `is`: InputStream = context.resources.openRawResource(resId)
        val bytes: ByteArray = ConvertUtils.input2Bytes(`is`) ?: return ""
        return if (StringUtils.isSpace(charsetName)) {
            String(bytes)
        } else {
            try {
                String(bytes, charset(charsetName ?: return ""))
            } catch (e: UnsupportedEncodingException) {
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
    fun readRaw2List(@RawRes resId: Int, charsetName: String? = null, context: Context = ComkitApplicationConfig.getApp()): List<String?>? {
        return ConvertUtils.input2List(context.resources.openRawResource(resId), charsetName)
    }
}
