package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.githubyss.mobile.common.kit.ComkitUtils


/**
 * ResUtils
 *
 * @author Ace Yan
 * @github githubyss
 */
object ResUtils {
    
    /**
     * Get string.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The string.
     */
    fun getString(context: Context = ComkitUtils.getApp(), resId: Int): String {
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
    fun getString(context: Context = ComkitUtils.getApp(), resId: Int, vararg resFormat: Any): String {
        return context.resources.getString(resId, resFormat)
    }
    
    /**
     * Get color.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The color.
     */
    fun getColor(context: Context = ComkitUtils.getApp(), resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }
    
    /**
     * Get dimension.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The dimension.
     */
    fun getDimension(context: Context = ComkitUtils.getApp(), resId: Int): Float {
        return context.resources.getDimension(resId)
    }
    
    /**
     * Get dimension pixel size.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The dimension pixel size.
     */
    fun getDimensionPixelSize(context: Context = ComkitUtils.getApp(), resId: Int): Int {
        return context.resources.getDimensionPixelSize(resId)
    }
    
    /**
     * Get drawable.
     *
     * @param context The context.
     * @param resId   The resource ID.
     * @return The drawable.
     */
    fun getDrawable(context: Context = ComkitUtils.getApp(), resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }
}
