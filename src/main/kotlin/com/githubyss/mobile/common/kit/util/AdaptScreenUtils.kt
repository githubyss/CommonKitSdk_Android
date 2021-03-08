package com.githubyss.mobile.common.kit.util

import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log
import java.lang.reflect.Field
import java.util.*


/**
 * AdaptScreenUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/08 17:33:01
 */
object AdaptScreenUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = AdaptScreenUtils::class.simpleName ?: "simpleName is null"
    
    private var metricsFields: List<Field>? = null
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Getter ********** ********** */
    
    fun getNavBarHeight(resources: Resources): Int {
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId != 0) {
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
    
    /** ********** ********** Checker ********** ********** */
    
    /** ********** ********** Processor ********** ********** */
    
    /**
     * Adapt for the horizontal screen, and call it in [android.app.Activity.getResources].
     */
    fun adaptWidth(resources: Resources, designWidth: Int): Resources? {
        val newXdpi = resources.displayMetrics.widthPixels * 72f / designWidth
        applyDisplayMetrics(resources, newXdpi)
        return resources
    }
    
    /**
     * Adapt for the vertical screen, and call it in [android.app.Activity.getResources].
     */
    fun adaptHeight(resources: Resources, designHeight: Int): Resources? {
        return adaptHeight(resources, designHeight, false)
    }
    
    /**
     * Adapt for the vertical screen, and call it in [android.app.Activity.getResources].
     */
    fun adaptHeight(resources: Resources, designHeight: Int, includeNavBar: Boolean): Resources? {
        val screenHeight = resources.displayMetrics.heightPixels * 72f + if (includeNavBar) getNavBarHeight(resources) else 0
        val newXdpi = screenHeight / designHeight
        applyDisplayMetrics(resources, newXdpi)
        return resources
    }
    
    /**
     * @param resources The resources.
     * @return the resource
     */
    fun closeAdapt(resources: Resources): Resources? {
        val newXdpi = ScreenUtils.getScreenDensity() * 72f
        applyDisplayMetrics(resources, newXdpi)
        return resources
    }
    
    private fun applyDisplayMetrics(resources: Resources, newXdpi: Float) {
        resources.displayMetrics.xdpi = newXdpi
        ScreenUtils.getDisplayMetrics().xdpi = newXdpi
        applyOtherDisplayMetrics(resources, newXdpi)
    }
    
    private fun applyOtherDisplayMetrics(resources: Resources, newXdpi: Float) {
        if (metricsFields == null) {
            metricsFields = ArrayList<Field>()
            var resCls: Class<*>? = resources.javaClass
            var declaredFields = resCls?.declaredFields
            while (declaredFields != null && declaredFields.isNotEmpty()) {
                for (field in declaredFields) {
                    if (field.type.isAssignableFrom(DisplayMetrics::class.java)) {
                        field.isAccessible = true
                        val tmpDm = getMetricsFromField(resources, field)
                        if (tmpDm != null) {
                            (metricsFields as ArrayList<Field>).add(field)
                            tmpDm.xdpi = newXdpi
                        }
                    }
                }
                resCls = resCls?.superclass
                declaredFields = resCls?.declaredFields ?: break
            }
        } else {
            applyMetricsFields(resources, newXdpi)
        }
    }
    
    private fun applyMetricsFields(resources: Resources, newXdpi: Float) {
        for (metricsField in metricsFields ?: return) {
            try {
                val dm: DisplayMetrics? = metricsField[resources] as DisplayMetrics?
                if (dm != null) dm.xdpi = newXdpi
            } catch (e: Exception) {
                Log.e("AdaptScreenUtils", "applyMetricsFields: $e")
            }
        }
    }
    
    private fun getMetricsFromField(resources: Resources, field: Field): DisplayMetrics? {
        return try {
            field[resources] as DisplayMetrics
        } catch (e: Exception) {
            Log.e("AdaptScreenUtils", "getMetricsFromField: $e")
            null
        }
    }
}
