package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import com.githubyss.mobile.common.kit.ComkitUtils
import com.githubyss.mobile.common.kit.enumeration.VersionCode


/**
 * ScreenUtils
 *
 * @author Ace Yan
 * @github githubyss
 */
object ScreenUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = ScreenUtils::class.simpleName ?: "simpleName is null"
    
    
    /** ********** ********** Info ********** ********** */
    
    /**
     * Get the window manager.
     *
     * @param context The context.
     * @return The window manager.
     */
    fun windowManager(context: Context = ComkitUtils.getApp()): WindowManager? {
        return context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
    
    /**
     * Get the default display.
     *
     * @param context The context.
     * @return The default display.
     */
    fun defaultDisplay(context: Context = ComkitUtils.getApp()): Display? {
        return windowManager(context = context)?.defaultDisplay
    }
    
    /**
     * Get the default metrics.
     *
     * @return The default metrics.
     */
    fun displayMetrics(): DisplayMetrics? {
        return Resources.getSystem().displayMetrics
    }
    
    /**
     * Get the default metrics.
     *
     * @param context The context.
     * @return The default metrics.
     */
    fun displayMetrics(context: Context = ComkitUtils.getApp()): DisplayMetrics? {
        return context.resources.displayMetrics
    }
    
    /**
     * Get the density.
     *
     * @return The density.
     */
    fun density(): Float? {
        return displayMetrics()?.density
    }
    
    /**
     * Get the density.
     *
     * @param context The context.
     * @return The density.
     */
    fun density(context: Context = ComkitUtils.getApp()): Float? {
        return displayMetrics(context = context)?.density
    }
    
    /**
     * Get the scaled density.
     *
     * @return The scaled density.
     */
    fun scaledDensity(): Float? {
        return displayMetrics()?.scaledDensity
    }
    
    /**
     * Get the scaled density.
     *
     * @param context The context.
     * @return The scaled density.
     */
    fun scaledDensity(context: Context = ComkitUtils.getApp()): Float? {
        return displayMetrics(context = context)?.scaledDensity
    }
    
    /**
     * Get the width in pixels.
     *
     * @return The width in pixels.
     */
    fun widthPx(): Int? {
        return displayMetrics()?.widthPixels
    }
    
    /**
     * Get the width in pixels.
     *
     * @param context The context.
     * @return The width in pixels.
     */
    fun widthPx(context: Context = ComkitUtils.getApp()): Int? {
        windowManager(context = context) ?: return displayMetrics(context = context)?.widthPixels
        return screenPointPx(context = context)?.x
    }
    
    /**
     * Get the height in pixels.
     *
     * @return The height in pixels.
     */
    fun heightPx(): Int? {
        return displayMetrics()?.heightPixels
    }
    
    /**
     * Get the height in pixels.
     *
     * @param context The context.
     * @return The height in pixels.
     */
    fun heightPx(context: Context = ComkitUtils.getApp()): Int? {
        windowManager(context = context) ?: return displayMetrics(context = context)?.heightPixels
        return screenPointPx(context = context)?.y
    }
    
    /**
     * Get the screen point in pixels.
     *
     * @param context The context.
     * @return The screen point in pixels.
     */
    fun screenPointPx(context: Context = ComkitUtils.getApp()): Point? {
        val point: Point? = Point()
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
            defaultDisplay(context = context)?.getRealSize(point) ?: return null
        } else {
            defaultDisplay(context = context)?.getSize(point) ?: return null
        }
        LogcatUtils.d(msg = "screenPointPx(): {widthPx:${point?.x ?: -1}, heightPx:${point?.y ?: -1}}")
        return point
    }
    
    /**
     * Get the status bar height.
     *
     * @return The status bar height.
     */
    fun getStatusBarHeight(): Int? {
        var result: Int? = null
        val resourceId = Resources.getSystem()
                .getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = Resources.getSystem()
                    .getDimensionPixelSize(resourceId)
        }
        return result
    }
    
    /**
     * Get the status bar height.
     *
     * @param context The context.
     * @return The status bar height.
     */
    fun getStatusBarHeight(context: Context = ComkitUtils.getApp()): Int? {
        var result: Int? = null
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
    
    /** ********** ********** Converter ********** ********** */
    
    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return The value of px.
     */
    fun dp2Px(dpValue: Float): Int? {
        return (dpValue * (density() ?: return null) + 0.5F).toInt()
    }
    
    /**
     * Value of px to value of dp.
     *
     * @param pxValue The value of px.
     * @return The value of dp.
     */
    fun px2Dp(pxValue: Float): Int? {
        return (pxValue / (density() ?: return null) + 0.5F).toInt()
    }
    
    /**
     * Value of sp to value of px.
     *
     * @param spValue The value of sp.
     * @return The value of px.
     */
    fun sp2Px(spValue: Float): Int? {
        return (spValue * (scaledDensity() ?: return null) + 0.5F).toInt()
    }
    
    /**
     * Value of px to value of sp.
     *
     * @param pxValue The value of px.
     * @return The value of sp.
     */
    fun px2Sp(pxValue: Float): Int? {
        return (pxValue / (scaledDensity() ?: return null) + 0.5F).toInt()
    }
}
