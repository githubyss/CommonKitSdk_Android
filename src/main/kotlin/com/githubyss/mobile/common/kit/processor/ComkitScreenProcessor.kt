package com.githubyss.mobile.common.kit.processor

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import com.githubyss.mobile.common.kit.ComkitApplication

/**
 * ComkitScreenProcessor
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitScreenProcessor {
    fun windowManager(context: Context = ComkitApplication.instance.application.applicationContext): WindowManager?
            = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    fun defaultDisplay(context: Context = ComkitApplication.instance.application.applicationContext): Display?
            = windowManager(context)?.defaultDisplay


    fun displayMetrics(context: Context = ComkitApplication.instance.application.applicationContext): DisplayMetrics?
            = context.resources.displayMetrics

    fun widthPixels(context: Context = ComkitApplication.instance.application.applicationContext): Int
            = displayMetrics(context)?.widthPixels ?: 0

    fun heightPixels(context: Context = ComkitApplication.instance.application.applicationContext): Int
            = displayMetrics(context)?.heightPixels ?: 0

    fun density(context: Context = ComkitApplication.instance.application.applicationContext): Float
            = displayMetrics(context)?.density ?: 0F

    fun scaledDensity(context: Context = ComkitApplication.instance.application.applicationContext): Float
            = displayMetrics(context)?.scaledDensity ?: 0F

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
