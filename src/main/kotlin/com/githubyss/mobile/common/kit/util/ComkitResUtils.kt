package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.support.v4.content.ContextCompat
import com.githubyss.mobile.common.kit.ComkitApplication

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
    fun getColor(resContext: Context = ComkitApplication.application.applicationContext, resId: Int) =
            ContextCompat.getColor(resContext, resId)

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
    fun getString(resContext: Context = ComkitApplication.application.applicationContext, resId: Int) =
            resContext.resources.getString(resId) ?: ""

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
    fun getString(resContext: Context = ComkitApplication.application.applicationContext, resId: Int, vararg resFormat: Any) =
            resContext.resources.getString(resId, resFormat)

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
    fun getDimension(resContext: Context = ComkitApplication.application.applicationContext, resId: Int) =
            resContext.resources.getDimension(resId)

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
    fun getDimensionPixelSize(resContext: Context = ComkitApplication.application.applicationContext, resId: Int) =
            resContext.resources.getDimensionPixelSize(resId)

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
    fun getDrawable(resContext: Context = ComkitApplication.application.applicationContext, resId: Int) =
            ContextCompat.getDrawable(resContext, resId)
}
