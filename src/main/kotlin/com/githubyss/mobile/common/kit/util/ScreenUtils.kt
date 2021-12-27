package com.githubyss.mobile.common.kit.util

import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Build
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.util.DisplayMetrics
import android.view.Display
import android.view.Surface
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresPermission
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.enumeration.VersionCode


/**
 * ScreenUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:15:35
 */
object ScreenUtils {
    
    /** ****************************** Properties ****************************** */
    
    private val TAG: String = ScreenUtils::class.java.simpleName
    
    
    /** ****************************** Functions ****************************** */
    
    /** ******************** Getter ******************** */
    
    /**
     * Get the width of screen in pixels.
     *
     * @param context The context.
     * @return The width of screen in pixels.
     */
    fun getScreenWidthPx(context: Context? = ComkitApplicationConfig.getApp()): Int {
        return if (context == null) {
            getDisplayMetrics()?.widthPixels ?: -1
        } else {
            getScreenPointPx(context = context)?.x ?: -1
        }
    }
    
    /**
     * Get the height of screen in pixels.
     *
     * @param context The context.
     * @return The height of screen in pixels.
     */
    fun getScreenHeightPx(context: Context? = null): Int {
        return if (context == null) {
            getDisplayMetrics()?.heightPixels ?: -1
        } else {
            getScreenPointPx(context)?.y ?: -1
        }
    }
    
    /**
     * Get the application's width of screen in pixel.
     *
     * @return the application's width of screen in pixel
     */
    fun getAppScreenWidthPx(context: Context? = ComkitApplicationConfig.getApp()): Int {
        return if (SystemUtils.getWindowManager(context) == null) {
            getDisplayMetrics(context)?.widthPixels ?: -1
        } else {
            getAppScreenPointPx(context)?.x ?: -1
        }
    }
    
    /**
     * Get the application's height of screen in pixel.
     *
     * @return the application's height of screen in pixel
     */
    fun getAppScreenHeightPx(context: Context? = ComkitApplicationConfig.getApp()): Int {
        return if (SystemUtils.getWindowManager(context) == null) {
            getDisplayMetrics(context)?.heightPixels ?: -1
        } else {
            getAppScreenPointPx(context)?.y ?: -1
        }
    }
    
    /**
     * Get the screen point in pixels.
     *
     * @param context The context.
     * @return The screen point in pixels.
     */
    fun getScreenPointPx(context: Context? = ComkitApplicationConfig.getApp()): Point? {
        val point = Point()
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
            getDefaultDisplay(context)?.getRealSize(point) ?: return null
        } else {
            getDefaultDisplay(context)?.getSize(point) ?: return null
        }
        // LogUtils.d(TAG, "getScreenPointPx(): {screenWidthPx:${point.x}, screenHeightPx:${point.y}}")
        return point
    }
    
    /**
     * Get the application's screen point in pixels.
     *
     * @param context The context.
     * @return The application's screen point in pixels.
     */
    fun getAppScreenPointPx(context: Context? = ComkitApplicationConfig.getApp()): Point? {
        val point = Point()
        getDefaultDisplay(context)?.getSize(point) ?: return null
        // LogUtils.d(TAG, "getAppScreenPointPx(): {appScreenWidthPx:${point.x}, appScreenHeightPx:${point.y}}")
        return point
    }
    
    /**
     * Return the rotation of screen.
     *
     * @param activity The activity.
     * @return the rotation of screen
     */
    fun getScreenRotation(activity: Activity?): Int {
        activity ?: return -1
        
        return when (activity.windowManager.defaultDisplay.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> 0
        }
    }
    
    /**
     * Return the duration of sleep.
     *
     * @return the duration of sleep.
     */
    fun getSleepDuration(context: Context? = ComkitApplicationConfig.getApp()): Int {
        return try {
            Settings.System.getInt(context?.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT)
        } catch (e: SettingNotFoundException) {
            e.printStackTrace()
            -1
        }
    }
    
    /**
     * Get the screen density.
     *
     * @param context The context.
     * @return The getScreenDensity.
     */
    fun getScreenDensity(context: Context? = ComkitApplicationConfig.getApp()): Float {
        return getDisplayMetrics(context)?.density ?: -1.0f
    }
    
    /**
     * Get the screen density dpi.
     *
     * @param
     * @return
     */
    fun getScreenDensityDpi(context: Context? = ComkitApplicationConfig.getApp()): Int {
        return getDisplayMetrics(context)?.densityDpi ?: -1
    }
    
    /**
     * Get the screen x dpi.
     *
     * @param
     * @return
     */
    fun getXDpi(context: Context? = ComkitApplicationConfig.getApp()): Float {
        return getDisplayMetrics(context)?.xdpi ?: -1.0f
    }
    
    /**
     * Get the screen y dpi.
     *
     * @param
     * @return
     */
    fun getYDpi(context: Context? = ComkitApplicationConfig.getApp()): Float {
        return getDisplayMetrics(context)?.ydpi ?: -1.0f
    }
    
    /**
     * Get the scaled density.
     *
     * @param context The context.
     * @return The scaled getScreenDensity.
     */
    fun getScaledDensity(context: Context? = ComkitApplicationConfig.getApp()): Float {
        return getDisplayMetrics(context)?.scaledDensity ?: -1.0f
    }
    
    /**
     * Get the default metrics.
     *
     * @param context The context.
     * @return The default metrics.
     */
    fun getDisplayMetrics(context: Context? = ComkitApplicationConfig.getApp()): DisplayMetrics? {
        return ResourceUtils.getResources(context)?.displayMetrics
    }
    
    /**
     * Get the default display.
     *
     * @param context The context.
     * @return The default display.
     */
    fun getDefaultDisplay(context: Context? = ComkitApplicationConfig.getApp()): Display? {
        return SystemUtils.getWindowManager(context)?.defaultDisplay
    }
    
    /** ******************** Checker ******************** */
    
    /**
     * Return whether screen is full.
     *
     * @param activity The activity.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFullScreen(activity: Activity?): Boolean {
        activity ?: return false
        
        return isFullScreen(activity.window)
    }
    
    /**
     * Return whether screen is full.
     *
     * @param window The window.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFullScreen(window: Window?): Boolean {
        window ?: return false
        
        val fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN
        return window.attributes.flags and fullScreenFlag == fullScreenFlag
    }
    
    /**
     * Return whether screen is landscape.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isLandscape(context: Context? = ComkitApplicationConfig.getApp()): Boolean {
        return ResourceUtils.getResources(context)?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
    
    /**
     * Return whether screen is portrait.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isPortrait(context: Context? = ComkitApplicationConfig.getApp()): Boolean {
        return ResourceUtils.getResources(context)?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT
    }
    
    /**
     * Return whether screen is locked.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isScreenLock(context: Context? = ComkitApplicationConfig.getApp()): Boolean {
        return SystemUtils.getKeyguardManager(context)?.inKeyguardRestrictedInputMode() ?: false
    }
    
    /** ******************** Processor ******************** */
    
    /**
     * Set full screen.
     *
     * @param activity The activity.
     */
    fun setFullScreen(activity: Activity?) {
        activity ?: return
        
        setFullScreen(activity.window)
    }
    
    /**
     * Set full screen.
     *
     * @param window The window.
     */
    fun setFullScreen(window: Window?) {
        window ?: return
        
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    
    /**
     * Set non full screen.
     *
     * @param activity The activity.
     */
    fun setNonFullScreen(activity: Activity?) {
        activity ?: return
        
        setNonFullScreen(activity.window)
    }
    
    /**
     * Set non full screen.
     *
     * @param window The window.
     * @param activity The activity.
     */
    fun setNonFullScreen(window: Window?) {
        window ?: return
        
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    
    /**
     * Toggle full screen.
     *
     * @param activity The activity.
     */
    fun toggleFullScreen(activity: Activity?) {
        activity ?: return
        
        toggleFullScreen(activity.window)
    }
    
    /**
     * Toggle full screen.
     *
     * @param window The window.
     */
    fun toggleFullScreen(window: Window?) {
        window ?: return
        
        val isFullScreen = isFullScreen(window)
        if (isFullScreen) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
    
    /**
     * Set the screen to landscape.
     *
     * @param activity The activity.
     */
    @SuppressLint("SourceLockedOrientationActivity")
    fun setLandscape(activity: Activity?) {
        activity ?: return
        
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
    
    /**
     * Set the screen to portrait.
     *
     * @param activity The activity.
     */
    @SuppressLint("SourceLockedOrientationActivity")
    fun setPortrait(activity: Activity?) {
        activity ?: return
        
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
    
    /**
     * Set the duration of sleep.
     *
     * Must hold `<uses-permission android:name="android.permission.WRITE_SETTINGS" />`
     *
     * @param duration The duration.
     */
    @RequiresPermission(permission.WRITE_SETTINGS)
    fun setSleepDuration(duration: Int, context: Context? = ComkitApplicationConfig.getApp()) {
        context ?: return
        
        Settings.System.putInt(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, duration)
    }
    
    /**
     * Return the bitmap of screen.
     *
     * @param activity          The activity.
     * @param isDeleteStatusBar True to delete status bar, false otherwise.
     * @return the bitmap of screen
     */
    fun screenShot(activity: Activity?, isDeleteStatusBar: Boolean = false): Bitmap? {
        activity ?: return null
        
        val decorView = activity.window.decorView
        decorView.isDrawingCacheEnabled = true
        decorView.setWillNotCacheDrawing(false)
        val bmp = decorView.drawingCache ?: return null
        val dm = DisplayMetrics()
        getDefaultDisplay(activity)?.getMetrics(dm)
        val ret: Bitmap
        ret = if (isDeleteStatusBar) {
            val statusBarHeight = BarUtils.getStatusBarHeight(activity)
            Bitmap.createBitmap(bmp, 0, statusBarHeight, dm.widthPixels, dm.heightPixels - statusBarHeight)
        } else {
            Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels)
        }
        decorView.destroyDrawingCache()
        return ret
    }
    
    
    /** ******************** Converter ******************** */
    
    /**
     * Value of px to value of dp.
     *
     * @param pxValue The value of px.
     * @return The value of dp.
     */
    fun px2Dp(pxValue: Float, context: Context? = ComkitApplicationConfig.getApp()): Int {
        return (pxValue / (getScreenDensity(context)) + 0.5F).toInt()
    }
    
    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return The value of px.
     */
    fun dp2Px(dpValue: Float, context: Context? = ComkitApplicationConfig.getApp()): Int {
        return (dpValue * (getScreenDensity(context)) + 0.5F).toInt()
    }
    
    /**
     * Value of px to value of sp.
     *
     * @param pxValue The value of px.
     * @return The value of sp.
     */
    fun px2Sp(pxValue: Float, context: Context? = ComkitApplicationConfig.getApp()): Int {
        return (pxValue / (getScaledDensity(context)) + 0.5F).toInt()
    }
    
    /**
     * Value of sp to value of px.
     *
     * @param spValue The value of sp.
     * @return The value of px.
     */
    fun sp2Px(spValue: Float, context: Context? = ComkitApplicationConfig.getApp()): Int {
        return (spValue * (getScaledDensity(context)) + 0.5F).toInt()
    }
    
    /**
     * Value of px to value of pt.
     *
     * @param pxValue The value of px.
     * @return value of pt
     */
    fun px2Pt(pxValue: Float, context: Context? = ComkitApplicationConfig.getApp()): Int {
        return (pxValue * 72 / getXDpi(context) + 0.5).toInt()
    }
    
    /**
     * Value of pt to value of px.
     *
     * @param ptValue The value of pt.
     * @return value of px
     */
    fun pt2Px(ptValue: Float, context: Context? = ComkitApplicationConfig.getApp()): Int {
        return (ptValue * getXDpi(context) / 72f + 0.5).toInt()
    }
}
