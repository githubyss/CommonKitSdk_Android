package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.view.Window
import androidx.annotation.IntRange
import com.githubyss.mobile.common.kit.ComkitApplicationConfig


/**
 * BrightnessUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/08 20:00:04
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "BrightnessUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/**
 * 获取屏幕亮度
 *
 * @return 屏幕亮度 0-255
 */
fun getBrightness(context: Context? = ComkitApplicationConfig.getApp()): Int {
    context ?: return -1

    return try {
        Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS)
    }
    catch (e: SettingNotFoundException) {
        e.printStackTrace()
        -1
    }
}

/**
 * 获取窗口亮度
 *
 * @param window 窗口
 * @return 屏幕亮度 0-255
 */
fun getWindowBrightness(window: Window?): Int {
    val lp = window?.attributes
    val brightness = lp?.screenBrightness
    return if (brightness == null || brightness < 0) getBrightness() else (brightness * 255).toInt()
}

/** ******************** Checker ******************** */

/**
 * Return whether automatic brightness mode is enabled.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isAutoBrightnessEnabled(context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    context ?: return false

    return try {
        val mode = Settings.System.getInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE)
        mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
    }
    catch (e: SettingNotFoundException) {
        e.printStackTrace()
        false
    }
}

/** ******************** Processor ******************** */

/**
 * Enable or disable automatic brightness mode.
 *
 * Must hold `<uses-permission android:name="android.permission.WRITE_SETTINGS" />`
 *
 * @param enabled True to enabled, false otherwise.
 * @return `true`: success<br></br>`false`: fail
 */
fun setAutoBrightnessEnabled(enabled: Boolean, context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    context ?: return false

    return Settings.System.putInt(context.contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, if (enabled) Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC else Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
}

/**
 * 设置屏幕亮度
 *
 * 需添加权限 `<uses-permission android:name="android.permission.WRITE_SETTINGS" />`
 * 并得到授权
 *
 * @param brightness 亮度值
 */
fun setBrightness(@IntRange(from = 0, to = 255) brightness: Int, context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    context ?: return false

    val resolver = context.contentResolver
    val b = Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, brightness)
    resolver?.notifyChange(Settings.System.getUriFor("screen_brightness"), null)
    return b
}

/**
 * 设置窗口亮度
 *
 * @param window     窗口
 * @param brightness 亮度值
 */
fun setWindowBrightness(@IntRange(from = 0, to = 255) brightness: Int, window: Window?) {
    window ?: return

    val lp = window.attributes
    lp?.screenBrightness = brightness / 255f
    window.attributes = lp
}


/** ****************************** Class ****************************** */


/** ****************************** Implementations ****************************** */


/** ****************************** Interface ****************************** */
