package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.os.Looper
import android.view.Gravity
import android.widget.Toast
import com.githubyss.mobile.common.kit.ComkitApplication

/**
 * ComkitToastUtils.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitToastUtils {
    private var toast: Toast? = null

    private var centerable = false


    /**
     * ComkitToastUtils.showMessage(context, msgId, duration, centerable)
     * <Description>
     * <Details>
     *
     * @param context
     * @param msgId
     * @param duration
     * @param centerable
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun showMessage(context: Context? = ComkitApplication.instance.application?.applicationContext,
                    msgId: Int,
                    duration: Int = if (ComkitResUtils.getString(context, msgId)?.length ?: 0 <= 10) Toast.LENGTH_SHORT else Toast.LENGTH_LONG,
                    centerable: Boolean = false) {
        showMessage(context, ComkitResUtils.getString(context, msgId) ?: "", duration, centerable)
    }

    /**
     * ComkitToastUtils.showMessage(context, msgStr, duration, centerable)
     * <Description>
     * <Details>
     *
     * @param context The context to use. Usually your Application or Activity object.
     * @param msgStr The text to show.
     * @param duration How long to display the message. Either Toast.LENGTH_SHORT or Toast.LENGTH_LONG.
     * @param centerable
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun showMessage(context: Context? = ComkitApplication.instance.application?.applicationContext,
                    msgStr: String,
                    duration: Int = if (msgStr.length <= 10) Toast.LENGTH_SHORT else Toast.LENGTH_LONG,
                    centerable: Boolean = false) {
        this@ComkitToastUtils.centerable = centerable

        if (Looper.myLooper() == null) {
            Looper.prepare()
        }

        if (toast != null) {
            toast?.cancel()
        }

        toast = Toast.makeText(context, msgStr, duration)
        if (this@ComkitToastUtils.centerable) {
            toast?.setGravity(Gravity.CENTER, 0, 0)
        }

        show()
    }

    /**
     * ComkitToastUtils.show()
     * <Description>
     * <Details>
     *
     * @param
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    private fun show() {
        synchronized(ComkitToastUtils::class.java) {
            toast?.show()
        }
    }

    fun close(context: Context) {
        try {
            if (toast != null) {
                toast?.cancel()
            }
        } catch (exception: Exception) {
            ComkitLogcatUtils.e(t = exception)
        }
    }
}
