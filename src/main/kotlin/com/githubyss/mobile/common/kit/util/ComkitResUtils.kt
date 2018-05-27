package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat

/**
 * ComkitResUtils.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitResUtils {
    /**
     * ComkitResUtils.getColor(resContext, resId)
     * <Description> Get color.
     * <Details>
     *
     * @param resContext
     * @param resId
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun getColor(resContext: Context, resId: Int): Int {
        return ContextCompat.getColor(resContext, resId)
    }

    /**
     * ComkitResUtils.getString(resContext, resId)
     * <Description> Get string.
     * <Details>
     *
     * @param resContext
     * @param resId
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun getString(resContext: Context, resId: Int): String {
        return resContext.resources.getString(resId)
    }

    /**
     * ComkitResUtils.getString(resContext, resId, resFormat)
     * <Description> Get string.
     * <Details>
     *
     * @param resContext
     * @param resId
     * @param resFormat
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun getString(resContext: Context, resId: Int, vararg resFormat: Any): String {
        return resContext.resources.getString(resId, resFormat)
    }

    /**
     * ComkitResUtils.getDimension(resContext, resId)
     * <Description> Get dimension.
     * <Details>
     *
     * @param resContext
     * @param resId
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun getDimension(resContext: Context, resId: Int): Float {
        return resContext.resources.getDimension(resId)
    }

    /**
     * ComkitResUtils.getDimensionPixelSize(resContext, resId)
     * <Description> Get dimension pixel size.
     * <Details>
     *
     * @param resContext
     * @param resId
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun getDimensionPixelSize(resContext: Context, resId: Int): Int {
        return resContext.resources.getDimensionPixelSize(resId)
    }

    /**
     * ComkitResUtils.getDrawable(resContext, resId)
     * <Description> Get drawable.
     * <Details>
     *
     * @param resContext
     * @param resId
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun getDrawable(resContext: Context, resId: Int): Drawable {
        return ContextCompat.getDrawable(resContext, resId)
    }
}
