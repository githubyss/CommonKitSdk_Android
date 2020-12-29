package com.githubyss.mobile.common.kit.info

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import com.githubyss.mobile.common.kit.ComkitApplication
import com.githubyss.mobile.common.kit.logcat.LogcatUtils


/**
 * ScreenInfo
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ScreenInfo {
    
    /** ********** ********** Info ********** ********** */
    
    /**
     * <Description> Get the window manager.
     * <Details>
     *
     * @param context The context.
     * @return The window manager.
     */
    fun windowManager(context: Context = ComkitApplication.instance.application): WindowManager? {
        return context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
    
    /**
     * <Description> Get the default display.
     * <Details>
     *
     * @param context The context.
     * @return The default display.
     */
    fun defaultDisplay(context: Context = ComkitApplication.instance.application): Display? {
        return windowManager(context = context)?.defaultDisplay
    }
    
    /**
     * <Description> Get the default metrics.
     * <Details>
     *
     * @return The default metrics.
     */
    fun displayMetrics(): DisplayMetrics? {
        return Resources.getSystem().displayMetrics
    }
    
    /**
     * <Description> Get the default metrics.
     * <Details>
     *
     * @param context The context.
     * @return The default metrics.
     */
    fun displayMetrics(context: Context = ComkitApplication.instance.application): DisplayMetrics? {
        return context.resources.displayMetrics
    }
    
    /**
     * <Description> Get the density.
     * <Details>
     *
     * @return The density.
     */
    fun density(): Float {
        return displayMetrics()?.density ?: -1F
    }
    
    /**
     * <Description> Get the density.
     * <Details>
     *
     * @param context The context.
     * @return The density.
     */
    fun density(context: Context = ComkitApplication.instance.application): Float {
        return displayMetrics(context = context)?.density ?: -1F
    }
    
    /**
     * <Description> Get the scaled density.
     * <Details>
     *
     * @return The scaled density.
     */
    fun scaledDensity(): Float {
        return displayMetrics()?.scaledDensity ?: -1F
    }
    
    /**
     * <Description> Get the scaled density.
     * <Details>
     *
     * @param context The context.
     * @return The scaled density.
     */
    fun scaledDensity(context: Context = ComkitApplication.instance.application): Float {
        return displayMetrics(context = context)?.scaledDensity ?: -1F
    }
    
    /**
     * <Description> Get the width in pixels.
     * <Details>
     *
     * @param context The context.
     * @return The width in pixels.
     */
    fun widthPx(context: Context = ComkitApplication.instance.application): Int {
        windowManager(context = context) ?: return displayMetrics(context = context)?.widthPixels ?: -1
        return screenPointPx(context = context)?.x ?: -1
    }
    
    /**
     * <Description> Get the height in pixels.
     * <Details>
     *
     * @param context The context.
     * @return The height in pixels.
     */
    fun heightPx(context: Context = ComkitApplication.instance.application): Int {
        windowManager(context = context) ?: return displayMetrics(context = context)?.heightPixels ?: -1
        return screenPointPx(context = context)?.y ?: -1
    }
    
    /**
     * <Description> Get the screen point in pixels.
     * <Details>
     *
     * @param context The context.
     * @return The screen point in pixels.
     */
    fun screenPointPx(context: Context = ComkitApplication.instance.application): Point? {
        val point: Point? = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            defaultDisplay(context = context)?.getRealSize(point) ?: return null
        } else {
            defaultDisplay(context = context)?.getSize(point) ?: return null
        }
        LogcatUtils.d(msg = "screenPointPx(): {widthPx:${point?.x ?: -1}, heightPx:${point?.y ?: -1}}")
        return point
    }
    
    /** ********** ********** Convert ********** ********** */
    
    /**
     * <Description> Value of dp to value of px.
     * <Details>
     *
     * @param dpValue The value of dp.
     * @return The value of px.
     */
    fun dp2Px(dpValue: Float): Int {
        return (dpValue * density() + 0.5F).toInt()
    }
    
    /**
     * <Description>  Value of px to value of dp.
     * <Details>
     *
     * @param pxValue The value of px.
     * @return The value of dp.
     */
    fun px2Dp(pxValue: Float): Int {
        return (pxValue / density() + 0.5F).toInt()
    }
    
    /**
     * <Description> Value of sp to value of px.
     * <Details>
     *
     * @param spValue The value of sp.
     * @return The value of px.
     */
    fun sp2Px(spValue: Float): Int {
        return (spValue * scaledDensity() + 0.5F).toInt()
    }
    
    /**
     * <Description> Value of px to value of sp.
     * <Details>
     *
     * @param pxValue The value of px.
     * @return The value of sp.
     */
    fun px2Sp(pxValue: Float): Int {
        return (pxValue / scaledDensity() + 0.5F).toInt()
    }
}
