package com.githubyss.mobile.common.kit.util

import android.content.Context

/**
 * ComkitToastUtils.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitToastUtils {
    val DELAY_SHORT = 2000
    val DELAY_LONG = 3500

    var beCenter = false

    fun showMessage(context: Context, msgId: Int) {
        showMessage(context, ComkitResUtils.getString(context, msgId))
    }

    fun showMessage(context: Context, msgId: Int, delay: Int) {
        showMessage(context, ComkitResUtils.getString(context, msgId), delay)
    }

    fun showMessage(context: Context, msg: String) {

    }

    fun showMessage(context: Context, msg: String, delay: Int) {

    }
}
