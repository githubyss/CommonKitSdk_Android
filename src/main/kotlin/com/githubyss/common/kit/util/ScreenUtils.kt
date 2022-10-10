package com.githubyss.common.kit.util

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
import android.util.TypedValue
import android.view.Display
import android.view.Surface
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresPermission
import com.githubyss.common.base.application.BaseApplicationHolder
import com.githubyss.common.kit.enumeration.VersionCode


/**
 * ScreenUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:15:35
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "ScreenUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/** ********** Display Metrics ********** */

/**
 * Get the default metrics.
 *
 * @return The default metrics.
 */
val displayMetrics: DisplayMetrics = systemResources.displayMetrics

/** ********** Scaled Density ********** */

/**
 * Get the scaled density.
 *
 * @return The scaled screenDensity.
 */
val scaledDensity: Float = displayMetrics.scaledDensity

/** ********** Screen Density ********** */

/**
 * Get the screen density.
 *
 * @return The screenDensity.
 */
val screenDensity: Float = displayMetrics.density

/** ********** Screen Density Dpi ********** */

/**
 * Get the screen density dpi.
 *
 * @return
 */
val screenDensityDpi: Int = displayMetrics.densityDpi

/**
 * Get the screen x dpi.
 *
 * @return
 */
val xDpi: Float = displayMetrics.xdpi

/**
 * Get the screen y dpi.
 *
 * @return
 */
val yDpi: Float = displayMetrics.ydpi

/** ********** Screen Size Px ********** */

/**
 * Get the width of screen in pixels.
 *
 * @param context The context.
 * @return The width of screen in pixels.
 */
fun screenWidthPx(context: Context? = BaseApplicationHolder.getApp()): Int {
    return if (context == null) {
        displayMetrics.widthPixels
    }
    else {
        screenPointPx(context = context).x
    }
}

/**
 * Get the height of screen in pixels.
 *
 * @param context The context.
 * @return The height of screen in pixels.
 */
fun screenHeightPx(context: Context? = BaseApplicationHolder.getApp()): Int {
    return if (context == null) {
        displayMetrics.heightPixels
    }
    else {
        screenPointPx(context).y
    }
}

/**
 * Get the screen point in pixels.
 *
 * @param context The context.
 * @return The screen point in pixels.
 */
fun screenPointPx(context: Context? = BaseApplicationHolder.getApp()): Point {
    val point = Point()
    if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
        defaultDisplay(context)?.getRealSize(point) ?: return point
    }
    else {
        defaultDisplay(context)?.getSize(point) ?: return point
    }
    // logD(TAG, "screenPointPx(): {screenWidthPx:${point.x}, screenHeightPx:${point.y}}")
    return point
}

/** ********** App Screen Size Px ********** */

/**
 * Get the application's width of screen in pixel.
 *
 * @return the application's width of screen in pixel
 */
fun appScreenWidthPx(context: Context? = BaseApplicationHolder.getApp()): Int {
    return if (windowManager(context) == null) {
        displayMetrics.widthPixels
    }
    else {
        appScreenPointPx(context).x
    }
}

/**
 * Get the application's height of screen in pixel.
 *
 * @return the application's height of screen in pixel
 */
fun appScreenHeightPx(context: Context? = BaseApplicationHolder.getApp()): Int {
    return if (windowManager(context) == null) {
        displayMetrics.heightPixels
    }
    else {
        appScreenPointPx(context).y
    }
}

/**
 * Get the application's screen point in pixels.
 *
 * @param context The context.
 * @return The application's screen point in pixels.
 */
fun appScreenPointPx(context: Context? = BaseApplicationHolder.getApp()): Point {
    val point = Point()
    defaultDisplay(context)?.getSize(point) ?: return point
    // logD(TAG, "appScreenPointPx(): {appScreenWidthPx:${point.x}, appScreenHeightPx:${point.y}}")
    return point
}

/** ********** Screen Rotation ********** */

/**
 * Return the rotation of screen.
 *
 * @param activity The activity.
 * @return the rotation of screen
 */
fun screenRotation(activity: Activity?): Int {
    activity ?: return 0

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
fun sleepDuration(context: Context? = BaseApplicationHolder.getApp()): Int {
    return try {
        Settings.System.getInt(context?.contentResolver, Settings.System.SCREEN_OFF_TIMEOUT)
    }
    catch (e: SettingNotFoundException) {
        logE(TAG, t = e)
        0
    }
}

/**
 * Get the default display.
 *
 * @param context The context.
 * @return The default display.
 */
fun defaultDisplay(context: Context? = BaseApplicationHolder.getApp()): Display? {
    return windowManager(context)?.defaultDisplay
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
fun isLandscape(context: Context? = BaseApplicationHolder.getApp()): Boolean {
    return systemResources.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE
}

/**
 * Return whether screen is portrait.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isPortrait(context: Context? = BaseApplicationHolder.getApp()): Boolean {
    return systemResources.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT
}

/**
 * Return whether screen is locked.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isScreenLock(context: Context? = BaseApplicationHolder.getApp()): Boolean {
    return getKeyguardManager(context)?.inKeyguardRestrictedInputMode() ?: false
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
    }
    else {
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
fun setSleepDuration(duration: Int, context: Context? = BaseApplicationHolder.getApp()) {
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
    defaultDisplay(activity)?.getMetrics(dm)
    val ret: Bitmap
    ret = if (isDeleteStatusBar) {
        val statusBarHeight = getStatusBarHeight(activity)
        Bitmap.createBitmap(bmp, 0, statusBarHeight, dm.widthPixels, dm.heightPixels - statusBarHeight)
    }
    else {
        Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels)
    }
    decorView.destroyDrawingCache()
    return ret
}


/** ******************** Converter ******************** */

private const val VALUE_TYPE_ERROR: String = "Value type must be Int or Long or Float or Double."

/** ********** Px - Dp ********** */

/**
 * Value of px to value of dp.
 *
 * @param pxValue The value of px.
 * @return The value of dp.
 */
fun px2Dp(pxValue: Number): Float {
    return when (pxValue) {
        is Int -> pxValue / screenDensity + 0.5F
        is Long -> pxValue / screenDensity + 0.5F
        is Float -> pxValue / screenDensity + 0.5F
        is Double -> (pxValue / screenDensity + 0.5F).toFloat()
        else -> throw Exception(VALUE_TYPE_ERROR)
    }
}

inline val Int.px2dp: Float get() = this / screenDensity + 0.5F
inline val Long.px2dp: Float get() = this / screenDensity + 0.5F
inline val Float.px2dp: Float get() = this / screenDensity + 0.5F
inline val Double.px2dp: Float get() = (this / screenDensity + 0.5F).toFloat()

/**
 * Value of dp to value of px.
 *
 * @param dpValue The value of dp.
 * @return The value of px.
 */
fun dp2Px(dpValue: Number): Float {
    return when (dpValue) {
        is Int -> dpValue * screenDensity + 0.5F
        is Long -> dpValue * screenDensity + 0.5F
        is Float -> dpValue * screenDensity + 0.5F
        is Double -> (dpValue * screenDensity + 0.5F).toFloat()
        else -> throw Exception(VALUE_TYPE_ERROR)
    }
}

inline val Int.dp2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics)
inline val Long.dp2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics)
inline val Float.dp2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
inline val Double.dp2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics)

/** ********** Px - Sp ********** */

/**
 * Value of px to value of sp.
 *
 * @param pxValue The value of px.
 * @return The value of sp.
 */
fun px2Sp(pxValue: Number): Float {
    return when (pxValue) {
        is Int -> pxValue / scaledDensity + 0.5F
        is Long -> pxValue / scaledDensity + 0.5F
        is Float -> pxValue / scaledDensity + 0.5F
        is Double -> (pxValue / scaledDensity + 0.5F).toFloat()
        else -> throw Exception(VALUE_TYPE_ERROR)
    }
}

inline val Int.px2sp: Float get() = this / scaledDensity + 0.5F
inline val Long.px2sp: Float get() = this / scaledDensity + 0.5F
inline val Float.px2sp: Float get() = this / scaledDensity + 0.5F
inline val Double.px2sp: Float get() = (this / scaledDensity + 0.5F).toFloat()

/**
 * Value of sp to value of px.
 *
 * @param spValue The value of sp.
 * @return The value of px.
 */
fun sp2Px(spValue: Number): Float {
    return when (spValue) {
        is Int -> spValue * scaledDensity + 0.5F
        is Long -> spValue * scaledDensity + 0.5F
        is Float -> spValue * scaledDensity + 0.5F
        is Double -> (spValue * scaledDensity + 0.5F).toFloat()
        else -> throw Exception(VALUE_TYPE_ERROR)
    }
}

inline val Int.sp2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), displayMetrics)
inline val Long.sp2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), displayMetrics)
inline val Float.sp2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, displayMetrics)
inline val Double.sp2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), displayMetrics)

/** ********** Px - Pt ********** */

/**
 * Value of px to value of pt.
 *
 * @param pxValue The value of px.
 * @return value of pt
 */
fun px2Pt(pxValue: Number): Float {
    return when (pxValue) {
        is Int -> pxValue * 72 / xDpi + 0.5F
        is Long -> pxValue * 72 / xDpi + 0.5F
        is Float -> pxValue * 72 / xDpi + 0.5F
        is Double -> (pxValue * 72 / xDpi + 0.5F).toFloat()
        else -> throw Exception(VALUE_TYPE_ERROR)
    }
}

inline val Int.px2pt: Float get() = this * 72 / xDpi + 0.5F
inline val Long.px2pt: Float get() = this * 72 / xDpi + 0.5F
inline val Float.px2pt: Float get() = this * 72 / xDpi + 0.5F
inline val Double.px2pt: Float get() = (this * 72 / xDpi + 0.5F).toFloat()

/**
 * Value of pt to value of px.
 *
 * @param ptValue The value of pt.
 * @return value of px
 */
fun pt2Px(ptValue: Number): Float {
    return when (ptValue) {
        is Int -> ptValue * xDpi / 72f + 0.5F
        is Long -> ptValue * xDpi / 72f + 0.5F
        is Float -> ptValue * xDpi / 72f + 0.5F
        is Double -> (ptValue * xDpi / 72f + 0.5F).toFloat()
        else -> throw Exception(VALUE_TYPE_ERROR)
    }
}

inline val Int.pt2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, this.toFloat(), displayMetrics)
inline val Long.pt2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, this.toFloat(), displayMetrics)
inline val Float.pt2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, this, displayMetrics)
inline val Double.pt2px: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, this.toFloat(), displayMetrics)
