package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.ComkitUtils


/**
 * ResUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:15:26
 */
object ResUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = ResUtils::class.simpleName ?: "simpleName is null"
    
    
    /**
     * Get string.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The string.
     */
    fun getString(context: Context = ComkitApplicationConfig.getApp(), resId: Int): String {
        return context.resources.getString(resId)
    }
    
    /**
     * Get string.
     *
     * @param context   The context.
     * @param resId     The resource ID.
     * @param resFormat The resource format.
     * @return The string.
     */
    fun getString(context: Context = ComkitApplicationConfig.getApp(), resId: Int, vararg resFormat: Any): String {
        return context.resources.getString(resId, resFormat)
    }
    
    /**
     * Get color.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The color.
     */
    fun getColor(context: Context = ComkitApplicationConfig.getApp(), resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }
    
    /**
     * Get dimension.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The dimension.
     */
    fun getDimension(context: Context = ComkitApplicationConfig.getApp(), resId: Int): Float {
        return context.resources.getDimension(resId)
    }
    
    /**
     * Get dimension pixel size.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The dimension pixel size.
     */
    fun getDimensionPixelSize(context: Context = ComkitApplicationConfig.getApp(), resId: Int): Int {
        return context.resources.getDimensionPixelSize(resId)
    }
    
    /**
     * Get drawable.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The drawable.
     */
    fun getDrawable(context: Context = ComkitApplicationConfig.getApp(), resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }
}
