package com.githubyss.mobile.common.kit.util

import android.content.res.Resources
import android.util.DisplayMetrics
import java.lang.reflect.Field


/**
 * AdaptScreenUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/08 17:33:01
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "AdaptScreenUtils"

private var metricsFields: List<Field>? = null


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

fun getNavBarHeight(resources: Resources?): Int {
    resources ?: return -1

    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId != 0) {
        resources.getDimensionPixelSize(resourceId)
    }
    else {
        -1
    }
}

/** ******************** Checker ******************** */

/** ******************** Processor ******************** */

/**
 * Adapt for the horizontal screen, and call it in [android.app.Activity.getResources].
 */
fun adaptWidth(resources: Resources?, designWidth: Int): Resources? {
    resources ?: return null

    val newXdpi = resources.displayMetrics.widthPixels * 72f / designWidth
    applyDisplayMetrics(resources, newXdpi)
    return resources
}

/**
 * Adapt for the vertical screen, and call it in [android.app.Activity.getResources].
 */
fun adaptHeight(resources: Resources?, designHeight: Int): Resources? {
    resources ?: return null

    return adaptHeight(resources, designHeight, false)
}

/**
 * Adapt for the vertical screen, and call it in [android.app.Activity.getResources].
 */
fun adaptHeight(resources: Resources?, designHeight: Int, includeNavBar: Boolean): Resources? {
    resources ?: return null

    val screenHeight = resources.displayMetrics.heightPixels * 72f + if (includeNavBar) getNavBarHeight(resources) else 0
    val newXdpi = screenHeight / designHeight
    applyDisplayMetrics(resources, newXdpi)
    return resources
}

/**
 * @param resources The resources.
 * @return the resource
 */
fun closeAdapt(resources: Resources?): Resources? {
    resources ?: return null

    val newXdpi = screenDensity * 72f
    applyDisplayMetrics(resources, newXdpi)
    return resources
}

private fun applyDisplayMetrics(resources: Resources?, newXdpi: Float) {
    resources ?: return

    resources.displayMetrics.xdpi = newXdpi
    displayMetrics.xdpi = newXdpi
    applyOtherDisplayMetrics(resources, newXdpi)
}

private fun applyOtherDisplayMetrics(resources: Resources?, newXdpi: Float) {
    resources ?: return

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
    }
    else {
        applyMetricsFields(resources, newXdpi)
    }
}

private fun applyMetricsFields(resources: Resources?, newXdpi: Float) {
    resources ?: return

    for (metricsField in metricsFields ?: return) {
        try {
            val dm: DisplayMetrics? = metricsField[resources] as DisplayMetrics?
            if (dm != null) dm.xdpi = newXdpi
        }
        catch (e: Exception) {
            logE(TAG, "applyMetricsFields: $e")
        }
    }
}

private fun getMetricsFromField(resources: Resources?, field: Field?): DisplayMetrics? {
    resources ?: return null
    field ?: return null

    return try {
        field[resources] as DisplayMetrics
    }
    catch (e: Exception) {
        logE(TAG, "getMetricsFromField: $e")
        null
    }
}
