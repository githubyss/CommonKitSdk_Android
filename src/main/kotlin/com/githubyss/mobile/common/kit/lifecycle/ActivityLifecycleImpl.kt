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
import com.githubyss.mobile.common.kit.ComkitUtils
import com.githubyss.mobile.common.kit.constant.Constants
import com.githubyss.mobile.common.kit.listener.OnActivityDestroyedListener
import com.githubyss.mobile.common.kit.listener.OnAppStatusChangedListener
import com.githubyss.mobile.common.kit.util.LogcatUtils
import com.githubyss.mobile.common.kit.util.ActivityUtils
import java.lang.reflect.InvocationTargetException
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * ActivityLifecycleImpl
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/17 17:46:15
 */
class ActivityLifecycleImpl : Application.ActivityLifecycleCallbacks {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val INSTANCE: ActivityLifecycleImpl by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ActivityLifecycleImpl()
        }
        
        private val TAG = ActivityLifecycleImpl::class.simpleName ?: "simpleName is null"
        private val PERMISSION_ACTIVITY_CLASS_NAME: String? = "com.blankj.utilcode.util.PermissionUtils\$PermissionActivity"
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
    
    /** The status listener map */
    private var statusListenerMap: MutableMap<Any?, OnAppStatusChangedListener?> = HashMap()
    
    /** The destroyed listener map */
    private var destroyedListenerMap: MutableMap<Activity?, Set<OnActivityDestroyedListener?>?> = HashMap()
    
    
    /** ********** ********** ********** Override Methods ********** ********** ********** */
    
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        setAnimatorsEnabled()
        setTopActivity(activity)
        // currentShowActivity = activity
    }
    
    override fun onActivityStarted(activity: Activity) {
        if (isForeground) {
            setTopActivity(activity)
        }
        if (configCount < 0) {
            configCount++
        } else {
            foregroundCount++
        }
    }
    
    override fun onActivityResumed(activity: Activity) {
        setTopActivity(activity)
        if (!isForeground) {
            isForeground = true
            postStatus(true)
            sendBroadcast(activity)
        }
        // currentShowActivity = activity
    }
    
    override fun onActivityPaused(activity: Activity) {
    }
    
    override fun onActivityStopped(activity: Activity) {
        // 重要，如果 Activity 的 stop 中判断应用再前后台，一定要把 super.stop() 放在第一行
        if (activity.isChangingConfigurations) {
            configCount--
        } else {
            foregroundCount--
            if (foregroundCount <= 0) {
                isForeground = false
                postStatus(false)
                sendBroadcast(activity)
            }
        }
    }
    
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }
    
    override fun onActivityDestroyed(activity: Activity) {
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
    
    
    /** ********** ********** ********** Public ********** ********** ********** */
    
    fun getTopActivity(): Activity? {
        if (activityList.isNotEmpty()) {
            val topActivity: Activity? = activityList.last
            if (topActivity != null) {
                return topActivity
            }
        }
        val topActivityByReflect: Activity? = getTopActivityByReflect()
        if (topActivityByReflect != null) {
            setTopActivity(topActivityByReflect)
        }
        return topActivityByReflect
    }
    
    /**
     * Close all activities.
     *
     * @param
     * @return
     */
    fun closeAllActivities() {
        if (!activityList.isEmpty()) {
            for (activity in activityList) {
                if (!ActivityUtils.isActivityDestroy(activity)) {
                    activity.finish()
                }
            }
            activityList.clear()
        }
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
        if (activity == null || listener == null) return
        val listeners: MutableSet<OnActivityDestroyedListener?>?
        if (!destroyedListenerMap.containsKey(activity)) {
            listeners = HashSet()
            destroyedListenerMap[activity] = listeners
        } else {
            listeners = destroyedListenerMap[activity] as MutableSet
            if (listeners.contains(listener)) return
        }
        listeners.add(listener)
    }
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    private fun postStatus(isForeground: Boolean) {
        if (statusListenerMap.isEmpty()) return
        for (onAppStatusChangedListener in statusListenerMap.values) {
            if (onAppStatusChangedListener == null) return
            if (isForeground) {
                onAppStatusChangedListener.onForeground()
            } else {
                onAppStatusChangedListener.onBackground()
            }
        }
    }
    
    /**
     * Send broadcast in application.
     *
     * @param activity The activity.
     */
    private fun sendBroadcast(activity: Activity) {
        val intent = Intent(Constants.INTENT_ACTION_IS_FOREGROUND)
        intent.putExtra("isForeground", isForeground)
        LocalBroadcastManager.getInstance(activity)
                .sendBroadcast(intent)
    }
    
    private fun setTopActivity(activity: Activity) {
        if (PERMISSION_ACTIVITY_CLASS_NAME == activity.javaClass.name) return
        if (activityList.contains(activity)) {
            if (activityList.last != activity) {
                activityList.remove(activity)
                activityList.addLast(activity)
            }
        } else {
            activityList.addLast(activity)
        }
    }
    
    /**
     * Set animators enabled.
     */
    private fun setAnimatorsEnabled() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && ValueAnimator.areAnimatorsEnabled()) {
            return
        }
        try {
            val sDurationScaleField = ValueAnimator::class.java.getDeclaredField("sDurationScale")
            sDurationScaleField.isAccessible = true
            val sDurationScale = sDurationScaleField[null] as Float
            if (sDurationScale == 0f) {
                sDurationScaleField[null] = 1f
                LogcatUtils.d(TAG, "setAnimatorsEnabled: Animators are enabled now!")
            }
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
    
    private fun consumeOnActivityDestroyedListener(activity: Activity) {
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
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }
        return null
    }
    
    private fun fixSoftInputLeaks(activity: Activity?) {
        if (activity == null) return
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
            } catch (ignore: Throwable) {
            }
        }
    }
}
