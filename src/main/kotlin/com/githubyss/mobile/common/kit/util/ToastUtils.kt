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
object ToastUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG: String = ToastUtils::class.java.simpleName
    private var toast: Toast? = null
    private var centerable = false
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /**
     * Show toast.
     *
     * @param msgId      The text ID to show.
     * @param duration   How long to display the message. Either Toast.LENGTH_SHORT or Toast.LENGTH_LONG.
     * @param centerable Is toast show in center.
     * @param context    The context to use. Usually your Application or Activity object.
     */
    fun showMessage(msgId: Int, duration: Int = if (ResourceUtils.getString(msgId).length <= 10) Toast.LENGTH_SHORT else Toast.LENGTH_LONG, centerable: Boolean = false, context: Context? = ComkitApplicationConfig.getApp()) {
        showMessage(ResourceUtils.getString(msgId), duration, centerable, context)
    }
    
    /**
     * Show toast.
     *
     * @param msgStr     The text to show.
     * @param duration   How long to display the message. Either Toast.LENGTH_SHORT or Toast.LENGTH_LONG.
     * @param centerable Is toast show in center.
     * @param context    The context to use. Usually your Application or Activity object.
     */
    fun showMessage(msgStr: String, duration: Int = if (msgStr.length <= 10) Toast.LENGTH_SHORT else Toast.LENGTH_LONG, centerable: Boolean = false, context: Context? = ComkitApplicationConfig.getApp()) {
        context ?: return
        
        ToastUtils.centerable = centerable
        
        if (Looper.myLooper() == null) {
            Looper.prepare()
        }
        
        if (toast != null) {
            toast?.cancel()
        }
        
        toast = Toast.makeText(context, msgStr, duration)
        if (ToastUtils.centerable) {
            toast?.setGravity(Gravity.CENTER, 0, 0)
        }
        
        show()
    }
    
    /**
     * Show toast.
     */
    private fun show() {
        synchronized(ToastUtils::class.java) {
            toast?.show()
        }
    }
    
    /**
     * Close toast.
     */
    fun close() {
        try {
            if (toast != null) {
                toast?.cancel()
            }
        } catch (exception: Exception) {
            LogUtils.e(t = exception)
        }
    }
}
