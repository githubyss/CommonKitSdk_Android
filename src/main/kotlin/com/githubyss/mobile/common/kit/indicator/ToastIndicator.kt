package com.githubyss.mobile.common.kit.indicator

import android.content.Context
import android.os.Looper
import android.view.Gravity
import android.widget.Toast
import com.githubyss.mobile.common.kit.ComkitApplication
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import com.githubyss.mobile.common.kit.resource.ComkitResUtils


/**
 * ToastIndicator
 * <Description> Toast 指示器
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 09:52:03
 */
object ToastIndicator {
    
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
     */
    fun showMessage(context: Context = ComkitApplication.instance.application.applicationContext, msgId: Int, duration: Int = if (ComkitResUtils.getString(context, msgId).length <= 10) Toast.LENGTH_SHORT else Toast.LENGTH_LONG, centerable: Boolean = false) {
        showMessage(context, ComkitResUtils.getString(context, msgId), duration, centerable)
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
     */
    fun showMessage(context: Context = ComkitApplication.instance.application.applicationContext, msgStr: String, duration: Int = if (msgStr.length <= 10) Toast.LENGTH_SHORT else Toast.LENGTH_LONG, centerable: Boolean = false) {
        ToastIndicator.centerable = centerable

        if (Looper.myLooper() == null) {
            Looper.prepare()
        }

        if (toast != null) {
            toast?.cancel()
        }

        toast = Toast.makeText(context, msgStr, duration)
        if (ToastIndicator.centerable) {
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
     */
    private fun show() {
        synchronized(ToastIndicator::class.java) {
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
