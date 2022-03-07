package com.githubyss.mobile.common.kit.lifecycle

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.githubyss.mobile.common.kit.constant.Constants
import com.githubyss.mobile.common.kit.util.logD
import java.lang.reflect.InvocationTargetException
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet


/**
 * ActivityLifecycleSubscriber
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/17 17:46:15
 */
open class ActivityLifecycleSubscriber private constructor() : Application.ActivityLifecycleCallbacks {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val INSTANCE: ActivityLifecycleSubscriber by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { ActivityLifecycleSubscriber() }
        
        private val TAG: String = ActivityLifecycleSubscriber::class.java.simpleName
        private val PERMISSION_ACTIVITY_CLASS_NAME: String = "com.blankj.utilcode.util.PermissionUtils\$PermissionActivity"
        
        // 锁屏延迟时间（毫秒）
        private const val LOCK_DELAY = 5 * 60 * 1000.toLong()
        
        // 自动登录延迟时间（毫秒）
        private const val AUTO_LOGON_DELAY = 14 * 60 * 1000.toLong()
    }
    
    /** Is app in foreground. */
    var isForeground = false
    
    /** Activity count in foreground. */
    var foregroundCount = 0
    
    /** Activity changing configurations count. */
    var configCount = 0
    
    /** The top activity */
    // var currentShowActivity: Activity? = null
    
    /** The activity list */
    var activityList: LinkedList<Activity> = LinkedList()
    
    /** The activity stack */
    var activityStack: Stack<Activity> = Stack()
    
    /** Gesture timer opened flag */
    private var isGestureTimerOpened = false
    
    /** User leave moment */
    private var userLeaveMoment: Long = 0
    
    /** Auto logon moment after leave */
    private var autoLogonLeaveMoment = 0L
    
    /** The status listener map */
    private var statusListenerMap: MutableMap<Any?, OnAppStatusChangedListener?> = HashMap()
    
    /** The destroyed listener map */
    private var destroyedListenerMap: MutableMap<Activity?, Set<OnActivityDestroyedListener?>?> = HashMap()
    
    
    /** ****************************** Override ****************************** */
    
    /**
     * 对应 Activity 的 onCreate(savedInstanceState: Bundle?)
     *
     * @param activity
     * @param savedInstanceState
     * @return
     */
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        logD(TAG, "${activity::class.java.simpleName} > onActivityCreated")
        
        // 应用放置后台，内存回收后，重新启动应用
        // if (activity != null && activity !is SplashActivity && EPApp.getApp().isColdStart && savedInstanceState != null) {
        //     // 恢复到最底层页面，进行跳转到Splash
        //     if (activity is LauncherActivity) {
        //         val intent = Intent(activity, SplashActivity::class.java)
        //         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        //         activity.startActivity(intent)
        //     }
        //     activity.finish()
        //     StaticVariable.IS_EXIT_APPLICATION = true
        //     return
        // }
        
        setAnimatorsEnabled()
        setTopActivity(activity)
        // currentShowActivity = activity
    }
    
    /**
     * 对应 Activity 的 onStart()
     *
     * @param activity
     * @return
     */
    override fun onActivityStarted(activity: Activity) {
        logD(TAG, "${activity::class.java.simpleName} > onActivityStarted")
        
        if (isForeground) {
            setTopActivity(activity)
        }
        if (configCount < 0) {
            configCount++
        }
        else {
            foregroundCount++
        }
    }
    
    /**
     * 对应 Activity 的 onResume()
     *
     * @param activity
     * @return
     */
    override fun onActivityResumed(activity: Activity) {
        logD(TAG, "${activity::class.java.simpleName} > onActivityResumed")
        
        if (!isForeground) {
            isForeground = true
            postStatus(true)
            sendBroadcast(activity)
        }
        setTopActivity(activity)
        // currentShowActivity = activity
        
        // 如果手势计时器开启，说明用户已经离开应用，现在刚回来
        // if (isGestureTimerOpened) {
        //     // 用户回来时刻
        //     val userBackMoment = SystemClock.elapsedRealtime()
        //     val userLeaveDuration = userBackMoment - userLeaveMoment
        //     // 用户处于后台的时长大于设置的默认锁定时长，并且登录状态是已登录，则更新用户离开时刻，并跳转到手势登录页
        //     if ((userLeaveDuration > LOCK_DELAY)) {
        //         if (isLogon()) {
        //             userLeaveMoment = userBackMoment
        //             // TODO 跳转到手势验证页
        //         }
        //     }
        //     // 将手势计时器状态重置
        //     isGestureTimerOpened = false
        // }
        
        // 如果离开时间不是0，说明从后台切换过来的，当前后台切换时间大于自动登录间隔时间，做一次自动登录;
        // 启动页面不做该操作
        // 用户回来时刻
        // if (autoLogonLeaveMoment != 0L && activity != null) {
        //     val autoLogonBackMoment = SystemClock.elapsedRealtime()
        //     val autoLogonLeaveDuration = autoLogonBackMoment - autoLogonLeaveMoment
        //     if (activity !is SplashActivity) {
        //         if (autoLogonLeaveDuration > AUTO_LOGON_DELAY) {
        //             // TODO 自动登录逻辑
        //         }
        //     }
        //     // 将离开时间还原
        //     autoLogonLeaveMoment = 0L
        // }
    }
    
    /**
     * 对应 Activity 的 onPause()
     *
     * @param activity
     * @return
     */
    override fun onActivityPaused(activity: Activity) {
        logD(TAG, "${activity::class.java.simpleName} > onActivityPaused")
    }
    
    /**
     * 对应 Activity 的 onStop()
     *
     * @param activity
     * @return
     */
    override fun onActivityStopped(activity: Activity) {
        logD(TAG, "${activity::class.java.simpleName} > onActivityStopped")
        
        // 重要，如果 Activity 的 stop 中判断应用再前后台，一定要把 super.stop() 放在第一行
        if (activity.isChangingConfigurations) {
            configCount--
        }
        else {
            foregroundCount--
            if (foregroundCount <= 0) {
                isForeground = false
                postStatus(false)
                sendBroadcast(activity)
                
                // 切换到后台，提示用户
                // if (activity != null) {
                //     ToastUtil.showToast(ResUtil.getString(EPApp.getApp(), R.string.lifecycle_in_background))
                // }
                
                // 非启动页，切换到后台，记录离开时间
                // if (activity != null && activity !is SplashActivity) {
                //     autoLogonLeaveMoment = SystemClock.elapsedRealtime()
                // }
                
                // if (!activity.javaClass.name.equals(GestureLogonActivity::class.java.getName())) {
                //     val isFpEnable: Boolean = UserAccountDAO.getInstance()
                //             .isFingerprintEnable()
                //     if (GestureSqliteOpenHelper.getInstance()
                //                     .isGestureBeenActivated() || isFpEnable) {
                //         if (ConfigEPA.IS_GESTURE_OPEND) {
                //             userLeaveMoment = SystemClock.elapsedRealtime()
                //             isGestureTimerOpened = true
                //         }
                //     }
                // }
            }
        }
    }
    
    /**
     * 对应 Activity 的 onSaveInstanceState(outState: Bundle)
     *
     * @param activity
     * @param outState
     * @return
     */
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        logD(TAG, "${activity::class.java.simpleName} > onActivitySaveInstanceState")
    }
    
    /**
     * 对应 Activity 的 onDestroy()
     *
     * @param activity
     * @return
     */
    override fun onActivityDestroyed(activity: Activity) {
        logD(TAG, "${activity::class.java.simpleName} > onActivityDestroyed")
        
        activityList.remove(activity)
        consumeOnActivityDestroyedListener(activity)
        fixSoftInputLeaks(activity)
        // 正常打开新页面，都是现在新页面的 onCreate，再调用前一个页面的 onDestroy
        // 返回的时候，返回的页面先走 onResume，再调用返回前一个页面的 onDestroy
        // 如果销毁 activity 和记录的是同一个对象的话，说明应用的所有页面都关闭了
        // if (currentShowActivity === activity) {
        //     currentShowActivity = null
        // }
    }
    
    
    /** ****************************** Functions ****************************** */
    
    fun getTopActivity(): Activity? {
        if (activityList.isNotEmpty()) {
            val topActivity: Activity? = activityList.last
            if (topActivity != null) {
                return topActivity
            }
        }
        val topActivityByReflect = getTopActivityByReflect()
        if (topActivityByReflect != null) {
            setTopActivity(topActivityByReflect)
        }
        return topActivityByReflect
    }
    
    fun getActivityNum(): Int {
        return activityList.size
    }
    
    fun addOnAppStatusChangedListener(`object`: Any?, listener: OnAppStatusChangedListener?) {
        statusListenerMap[`object`] = listener
    }
    
    fun removeOnAppStatusChangedListener(`object`: Any?) {
        statusListenerMap.remove(`object`)
    }
    
    fun removeOnActivityDestroyedListener(activity: Activity?) {
        if (activity == null) return
        destroyedListenerMap.remove(activity)
    }
    
    fun addOnActivityDestroyedListener(activity: Activity?, listener: OnActivityDestroyedListener?) {
        activity ?: return
        listener ?: return
        
        val listeners: MutableSet<OnActivityDestroyedListener?>?
        if (!destroyedListenerMap.containsKey(activity)) {
            listeners = HashSet()
            destroyedListenerMap[activity] = listeners
        }
        else {
            listeners = destroyedListenerMap[activity] as MutableSet
            if (listeners.contains(listener)) return
        }
        listeners.add(listener)
    }
    
    /**
     * 栈内是否存在解锁页
     *
     * @param
     * @return
     */
    // fun isGestureExist(): Boolean {
    //     if (!activityList.isEmpty()) {
    //         for (activity in activityList) {
    //             if (activity is GestureLogonActivity) {
    //                 return true
    //             }
    //         }
    //     }
    //     return false
    // }
    
    /**
     * 栈内是否存在启动页
     *
     * @param
     * @return
     */
    // fun isSplashExist(): Boolean {
    //     if (!activityList.isEmpty()) {
    //         for (activity in activityList) {
    //             if (activity is SplashActivity) {
    //                 return true
    //             }
    //         }
    //     }
    //     return false
    // }
    
    /**
     * 应用在后台是否超出设定时间
     *
     * @param
     * @return
     */
    // fun isOverTime(): Boolean {
    //     if (isGestureTimerOpened) {
    //         val currentTime = SystemClock.elapsedRealtime()
    //         if (currentTime - userLeaveMoment > LOCK_DELAY) {
    //             // if (isLogon()) {
    //             isGestureTimerOpened = false
    //             return true
    //             // }
    //         }
    //     }
    //     return false
    // }
    
    private fun postStatus(isForeground: Boolean) {
        if (statusListenerMap.isEmpty()) return
        
        for (onAppStatusChangedListener in statusListenerMap.values) {
            if (onAppStatusChangedListener == null) return
            if (isForeground) {
                onAppStatusChangedListener.onForeground()
            }
            else {
                onAppStatusChangedListener.onBackground()
            }
        }
    }
    
    /**
     * Send broadcast in application.
     *
     * @param activity The activity.
     */
    private fun sendBroadcast(activity: Activity?) {
        activity ?: return
        
        val intent = Intent(Constants.INTENT_ACTION_IS_FOREGROUND)
        intent.putExtra("isForeground", isForeground)
        LocalBroadcastManager.getInstance(activity)
            .sendBroadcast(intent)
    }
    
    private fun setTopActivity(activity: Activity?) {
        activity ?: return
        if (PERMISSION_ACTIVITY_CLASS_NAME == activity.javaClass.name) return
        
        if (activityList.contains(activity)) {
            if (activityList.last != activity) {
                activityList.remove(activity)
                activityList.addLast(activity)
            }
        }
        else {
            activityList.addLast(activity)
        }
    }
    
    /**
     * Set animators enabled.
     */
    private fun setAnimatorsEnabled() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && ValueAnimator.areAnimatorsEnabled()) return
        
        try {
            val sDurationScaleField = ValueAnimator::class.java.getDeclaredField("sDurationScale")
            sDurationScaleField.isAccessible = true
            val sDurationScale = sDurationScaleField[null] as Float
            if (sDurationScale == 0f) {
                sDurationScaleField[null] = 1f
                logD(TAG, "setAnimatorsEnabled: Animators are enabled now!")
            }
        }
        catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
        catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
    
    private fun consumeOnActivityDestroyedListener(activity: Activity?) {
        activity ?: return
        
        val iterator: MutableIterator<MutableMap.MutableEntry<Activity?, Set<OnActivityDestroyedListener?>?>> = destroyedListenerMap.entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.key === activity) {
                val value = entry.value
                if (value != null) {
                    for (listener in value) {
                        listener?.onActivityDestroyed(activity)
                    }
                }
                iterator.remove()
            }
        }
    }
    
    private fun getTopActivityByReflect(): Activity? {
        try {
            @SuppressLint("PrivateApi")
            val activityThreadClass = Class.forName("android.app.ActivityThread")
            val currentActivityThreadMethod = activityThreadClass.getMethod("currentActivityThread")
                .invoke(null)
            val activityListField = activityThreadClass.getDeclaredField("mActivityList")
            activityListField.isAccessible = true
            val activities = activityListField[currentActivityThreadMethod] as Map<*, *>
            for (activityRecord in activities.values) {
                if (activityRecord != null) {
                    val activityRecordClass: Class<Any> = activityRecord.javaClass
                    val pausedField = activityRecordClass.getDeclaredField("paused")
                    pausedField.isAccessible = true
                    if (!pausedField.getBoolean(activityRecord)) {
                        val activityField = activityRecordClass.getDeclaredField("activity")
                        activityField.isAccessible = true
                        return activityField[activityRecord] as Activity
                    }
                }
            }
        }
        catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        catch (e: NoSuchMethodException) {
            e.printStackTrace()
        }
        catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
        return null
    }
    
    private fun fixSoftInputLeaks(activity: Activity?) {
        activity ?: return
        
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val leakViews = arrayOf("mLastSrvView", "mCurRootView", "mServedView", "mNextServedView")
        for (leakView in leakViews) {
            try {
                val leakViewField = InputMethodManager::class.java.getDeclaredField(leakView)
                if (!leakViewField.isAccessible) {
                    leakViewField.isAccessible = true
                }
                val obj = leakViewField[imm] as? View ?: continue
                if (obj.rootView === activity.window.decorView.rootView) {
                    leakViewField[imm] = null
                }
            }
            catch (ignore: Throwable) {
            }
        }
    }
    
    /**
     * 去解锁页
     */
    // private fun jumpToGestureLogonActivity(currentShowActivity: Activity) {
    //     val intent = Intent(currentShowActivity, GestureLogonActivity::class.java)
    //     intent.putExtra("isFromLife", true)
    //     currentShowActivity.startActivity(intent)
    // }
    
    
    /** ****************************** Interface ****************************** */
    
    interface OnAppStatusChangedListener {
        fun onForeground()
        fun onBackground()
    }
    
    interface OnActivityDestroyedListener {
        fun onActivityDestroyed(activity: Activity?)
    }
}
