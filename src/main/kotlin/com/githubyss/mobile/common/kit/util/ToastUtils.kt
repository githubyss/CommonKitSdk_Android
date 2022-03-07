package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.os.Looper
import android.view.Gravity
import android.widget.Toast
import com.githubyss.mobile.common.kit.ComkitApplicationConfig


/**
 * ToastUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 09:52:03
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "ToastUtils"
private var toast: Toast? = null


/** ****************************** Functions ****************************** */

/**
 * Show toast.
 *
 * @param msgId      The text ID to show.
 * @param duration   How long to display the message. Either Toast.LENGTH_SHORT or Toast.LENGTH_LONG.
 * @param centerable Is toast show in center.
 * @param context    The context to use. Usually your Application or Activity object.
 */
fun showToast(msgId: Int, duration: Int = if (getString(msgId).length <= 10) Toast.LENGTH_SHORT else Toast.LENGTH_LONG, centerable: Boolean = false, context: Context? = ComkitApplicationConfig.getApp()) {
    showToast(getString(msgId), duration, centerable, context)
}

/**
 * Show toast.
 *
 * @param msgStr     The text to show.
 * @param duration   How long to display the message. Either Toast.LENGTH_SHORT or Toast.LENGTH_LONG.
 * @param centerable Is toast show in center.
 * @param context    The context to use. Usually your Application or Activity object.
 */
fun showToast(msgStr: String, duration: Int = if (msgStr.length <= 10) Toast.LENGTH_SHORT else Toast.LENGTH_LONG, centerable: Boolean = false, context: Context? = ComkitApplicationConfig.getApp()) {
    context ?: return

    if (Looper.myLooper() == null) {
        Looper.prepare()
    }

    if (toast != null) {
        toast?.cancel()
    }

    toast = Toast.makeText(context, msgStr, duration)
    if (centerable) {
        toast?.setGravity(Gravity.CENTER, 0, 0)
    }

    show()
}

/**
 * Show toast.
 */
private fun show() {
    synchronized(toast ?: return) {
        toast?.show()
    }
}

/**
 * Close toast.
 */
fun closeToast() {
    try {
        if (toast != null) {
            toast?.cancel()
        }
    }
    catch (exception: Exception) {
        logE(TAG, t = exception)
    }
}
