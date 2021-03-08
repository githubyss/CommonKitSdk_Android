package com.githubyss.mobile.common.kit.util

import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Build
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.util.DisplayMetrics
import android.view.Display
import android.view.Surface
import android.view.WindowManager
import androidx.annotation.NonNull
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
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = ScreenUtils::class.simpleName ?: "simpleName is null"
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Getter ********** ********** */
    
    /**
     * Get the width of screen in pixels.
     *
     * @return The width of screen in pixels.
     */
    fun getScreenWidthPx(): Int {
        return getDisplayMetrics().widthPixels
    }
    
    /**
     * Get the height of screen in pixels.
     *
     * @return The height of screen in pixels.
     */
    fun getScreenHeightPx(): Int {
        return getDisplayMetrics().heightPixels
    }
    
    /**
     * Get the width of screen in pixels.
     *
     * @param context The context.
     * @return The width of screen in pixels.
     */
    fun getScreenWidthPx(context: Context = ComkitApplicationConfig.getApp()): Int? {
        getWindowManager(context = context) ?: return getDisplayMetrics(context = context)?.widthPixels
        return getScreenPointPx(context = context)?.x
    }
    
    /**
     * Get the height of screen in pixels.
     *
     * @param context The context.
     * @return The height of screen in pixels.
     */
    fun getScreenHeightPx(context: Context = ComkitApplicationConfig.getApp()): Int? {
        getWindowManager(context = context) ?: return getDisplayMetrics(context = context)?.heightPixels
        return getScreenPointPx(context = context)?.y
    }
    
    /**
     * Get the application's width of screen in pixel.
     *
     * @return the application's width of screen in pixel
     */
    fun getAppScreenWidthPx(context: Context = ComkitApplicationConfig.getApp()): Int? {
        getWindowManager(context = context) ?: return getDisplayMetrics(context = context)?.widthPixels
        return getAppScreenPointPx(context = context)?.x
    }
    
    /**
     * Get the application's height of screen in pixel.
     *
     * @return the application's height of screen in pixel
     */
    fun getAppScreenHeightPx(context: Context = ComkitApplicationConfig.getApp()): Int? {
        getWindowManager(context = context) ?: return getDisplayMetrics(context = context)?.heightPixels
        return getAppScreenPointPx(context = context)?.y
    }
    
    /**
     * Get the screen point in pixels.
     *
     * @param context The context.
     * @return The screen point in pixels.
     */
    fun getScreenPointPx(context: Context = ComkitApplicationConfig.getApp()): Point? {
        val point: Point? = Point()
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
            getDefaultDisplay(context = context)?.getRealSize(point) ?: return null
        } else {
            getDefaultDisplay(context = context)?.getSize(point) ?: return null
        }
        LogcatUtils.d(msg = "getScreenPointPx(): {screenWidthPx:${point?.x ?: -1}, screenHeightPx:${point?.y ?: -1}}")
        return point
    }
    
    /**
     * Get the application's screen point in pixels.
     *
     * @param context The context.
     * @return The application's screen point in pixels.
     */
    fun getAppScreenPointPx(context: Context = ComkitApplicationConfig.getApp()): Point? {
        val point: Point? = Point()
        getDefaultDisplay(context = context)?.getSize(point) ?: return null
        LogcatUtils.d(msg = "getAppScreenPointPx(): {appScreenWidthPx:${point?.x ?: -1}, appScreenHeightPx:${point?.y ?: -1}}")
        return point
    }
    
    fun getStatusBarHeight(): Int {
        return BarUtils.getStatusBarHeight()
    }
    
    fun getStatusBarHeight(context: Context?): Int {
        return BarUtils.getStatusBarHeight(context)
    }
    
    /**
     * Return the rotation of screen.
     *
     * @param activity The activity.
     * @return the rotation of screen
     */
    fun getScreenRotation(@NonNull activity: Activity): Int {
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
    fun getSleepDuration(context: Context = ComkitApplicationConfig.getApp()): Int {
        return try {
            Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT)
        } catch (e: SettingNotFoundException) {
            e.printStackTrace()
            -123
        }
    }
    
    /**
     * Get the screen density.
     *
     * @return The getScreenDensity.
     */
    fun getScreenDensity(): Float {
        return getDisplayMetrics().density
    }
    
    /**
     * Get the screen density.
     *
     * @param context The context.
     * @return The getScreenDensity.
     */
    fun getScreenDensity(context: Context = ComkitApplicationConfig.getApp()): Float? {
        return getDisplayMetrics(context = context)?.density
    }
    
    /**
     * Get the screen density dpi.
     *
     * @param
     * @return
     */
    fun getScreenDensityDpi(): Int? {
        return getDisplayMetrics()?.densityDpi
    }
    
    /**
     * Get the screen density dpi.
     *
     * @param
     * @return
     */
    fun getScreenDensityDpi(context: Context = ComkitApplicationConfig.getApp()): Int? {
        return getDisplayMetrics(context = context)?.densityDpi
    }
    
    /**
     * Get the scaled density.
     *
     * @return The scaled getScreenDensity.
     */
    fun getScaledDensity(): Float {
        return getDisplayMetrics().scaledDensity
    }
    
    /**
     * Get the scaled density.
     *
     * @param context The context.
     * @return The scaled getScreenDensity.
     */
    fun getScaledDensity(context: Context = ComkitApplicationConfig.getApp()): Float? {
        return getDisplayMetrics(context = context)?.scaledDensity
    }
    
    /**
     * Get the default metrics.
     *
     * @return The default metrics.
     */
    fun getDisplayMetrics(): DisplayMetrics {
        return Resources.getSystem().displayMetrics
    }
    
    /**
     * Get the default metrics.
     *
     * @param context The context.
     * @return The default metrics.
     */
    fun getDisplayMetrics(context: Context = ComkitApplicationConfig.getApp()): DisplayMetrics? {
        return context.resources.displayMetrics
    }
    
    /**
     * Get the default display.
     *
     * @param context The context.
     * @return The default display.
     */
    fun getDefaultDisplay(context: Context = ComkitApplicationConfig.getApp()): Display? {
        return getWindowManager(context = context)?.defaultDisplay
    }
    
    /**
     * Get the window manager.
     *
     * @param context The context.
     * @return The window manager.
     */
    fun getWindowManager(context: Context = ComkitApplicationConfig.getApp()): WindowManager? {
        return context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
    
    /** ********** ********** Checker ********** ********** */
    
    /**
     * Return whether screen is full.
     *
     * @param activity The activity.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFullScreen(@NonNull activity: Activity): Boolean {
        val fullScreenFlag = WindowManager.LayoutParams.FLAG_FULLSCREEN
        return activity.window.attributes.flags and fullScreenFlag == fullScreenFlag
    }
    
    /**
     * Return whether screen is landscape.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isLandscape(context: Context = ComkitApplicationConfig.getApp()): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }
    
    /**
     * Return whether screen is portrait.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isPortrait(context: Context = ComkitApplicationConfig.getApp()): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }
    
    /**
     * Return whether screen is locked.
     *
     * @return `true`: yes<br></br>`false`: no
     */
    fun isScreenLock(context: Context = ComkitApplicationConfig.getApp()): Boolean {
        val km = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        return km.inKeyguardRestrictedInputMode()
    }
    
    /** ********** ********** Processor ********** ********** */
    
    /**
     * Set full screen.
     *
     * @param activity The activity.
     */
    fun setFullScreen(@NonNull activity: Activity) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    
    /**
     * Set non full screen.
     *
     * @param activity The activity.
     */
    fun setNonFullScreen(@NonNull activity: Activity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    
    /**
     * Toggle full screen.
     *
     * @param activity The activity.
     */
    fun toggleFullScreen(@NonNull activity: Activity) {
        val isFullScreen: Boolean = isFullScreen(activity)
        val window = activity.window
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
    fun setLandscape(@NonNull activity: Activity) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
    
    /**
     * Set the screen to portrait.
     *
     * @param activity The activity.
     */
    @SuppressLint("SourceLockedOrientationActivity")
    fun setPortrait(@NonNull activity: Activity) {
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
    fun setSleepDuration(context: Context = ComkitApplicationConfig.getApp(), duration: Int) {
        Settings.System.putInt(context.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, duration)
    }
    
    /**
     * Return the bitmap of screen.
     *
     * @param activity          The activity.
     * @param isDeleteStatusBar True to delete status bar, false otherwise.
     * @return the bitmap of screen
     */
    fun screenShot(@NonNull activity: Activity, isDeleteStatusBar: Boolean = false): Bitmap? {
        val decorView = activity.window.decorView
        decorView.isDrawingCacheEnabled = true
        decorView.setWillNotCacheDrawing(false)
        val bmp = decorView.drawingCache ?: return null
        val dm = DisplayMetrics()
        getDefaultDisplay(activity)?.getMetrics(dm)
        val ret: Bitmap
        ret = if (isDeleteStatusBar) {
            val statusBarHeight = getStatusBarHeight(activity)
            Bitmap.createBitmap(bmp, 0, statusBarHeight ?: return null, dm.widthPixels, dm.heightPixels - statusBarHeight)
        } else {
            Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels)
        }
        decorView.destroyDrawingCache()
        return ret
    }
    
    
    /** ********** ********** Converter ********** ********** */
    
    /**
     * Value of px to value of dp.
     *
     * @param pxValue The value of px.
     * @return The value of dp.
     */
    fun px2Dp(pxValue: Float): Int? {
        return (pxValue / (getScreenDensity() ?: return null) + 0.5F).toInt()
    }
    
    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return The value of px.
     */
    fun dp2Px(dpValue: Float): Int {
        return (dpValue * (getScreenDensity()) + 0.5F).toInt()
    }
    
    /**
     * Value of px to value of sp.
     *
     * @param pxValue The value of px.
     * @return The value of sp.
     */
    fun px2Sp(pxValue: Float): Int {
        return (pxValue / (getScaledDensity()) + 0.5F).toInt()
    }
    
    /**
     * Value of sp to value of px.
     *
     * @param spValue The value of sp.
     * @return The value of px.
     */
    fun sp2Px(spValue: Float): Int {
        return (spValue * (getScaledDensity()) + 0.5F).toInt()
    }
    
    /**
     * Value of px to value of pt.
     *
     * @param pxValue The value of px.
     * @return value of pt
     */
    fun px2Pt(pxValue: Float): Int {
        return (pxValue * 72 / getDisplayMetrics().xdpi + 0.5).toInt()
    }
    
    /**
     * Value of pt to value of px.
     *
     * @param ptValue The value of pt.
     * @return value of px
     */
    fun pt2Px(ptValue: Float): Int {
        return (ptValue * getDisplayMetrics().xdpi / 72f + 0.5).toInt()
    }
}
