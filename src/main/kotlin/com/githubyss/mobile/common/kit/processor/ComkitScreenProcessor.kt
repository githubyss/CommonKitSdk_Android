package com.githubyss.mobile.common.kit.processor

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import com.githubyss.mobile.common.kit.ComkitApplication
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils


/**
 * ComkitScreenProcessor
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitScreenProcessor {
    // ---------- ---------- ---------- Info ---------- ---------- ----------

    fun windowManager(context: Context = ComkitApplication.instance.application.applicationContext): WindowManager?
            = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    fun displayMetrics(context: Context = ComkitApplication.instance.application.applicationContext): DisplayMetrics?
            = context.resources.displayMetrics

    fun defaultDisplay(context: Context = ComkitApplication.instance.application.applicationContext): Display?
            = windowManager(context)?.defaultDisplay

    fun widthPixels(context: Context = ComkitApplication.instance.application.applicationContext): Int {
        windowManager(context) ?: return displayMetrics(context)?.widthPixels ?: -1
        return screenPointPixels(context)?.x ?: -1
    }

    fun heightPixels(context: Context = ComkitApplication.instance.application.applicationContext): Int {
        windowManager(context) ?: return displayMetrics(context)?.heightPixels ?: -1
        return screenPointPixels(context)?.y ?: -1
    }

    fun screenPointPixels(context: Context = ComkitApplication.instance.application.applicationContext): Point? {
        val point: Point? = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            defaultDisplay(context)?.getRealSize(point) ?: return null
        } else {
            defaultDisplay(context)?.getSize(point) ?: return null
        }
        ComkitLogcatUtils.d(msg = "screenPointPixels(): {pW:${point?.x ?: -1}, pH:${point?.x ?: -1}}")
        return point
    }

    fun density(context: Context = ComkitApplication.instance.application.applicationContext): Float
            = displayMetrics(context)?.density ?: -1F

    fun scaledDensity(context: Context = ComkitApplication.instance.application.applicationContext): Float
            = displayMetrics(context)?.scaledDensity ?: -1F


    // ---------- ---------- ---------- Converter ---------- ---------- ----------

    fun dp2Px(context: Context = ComkitApplication.instance.application.applicationContext, input: Float?): Int
            = ((input ?: 0F) * density(context) + 0.5F).toInt()

    fun px2Dp(context: Context = ComkitApplication.instance.application.applicationContext, input: Float?): Int
            = ((input ?: 0F) / density(context) + 0.5F).toInt()

    fun sp2Px(context: Context = ComkitApplication.instance.application.applicationContext, input: Float?): Int
            = ((input ?: 0F) * scaledDensity(context) + 0.5F).toInt()

    fun px2Sp(context: Context = ComkitApplication.instance.application.applicationContext, input: Float?): Int
            = ((input ?: 0F) / scaledDensity(context) + 0.5F).toInt()
}
