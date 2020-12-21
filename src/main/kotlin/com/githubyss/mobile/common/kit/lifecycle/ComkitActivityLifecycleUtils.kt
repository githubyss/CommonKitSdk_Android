package com.githubyss.mobile.common.kit.lifecycle

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.githubyss.mobile.common.kit.constant.Constants
import java.util.*

/**
 * ComkitActivityLifecycleUtils
 * <Description> 生命周期回调
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/17 17:46:15
 */
class ComkitActivityLifecycleUtils private constructor() : Application.ActivityLifecycleCallbacks {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    companion object {
        val INSTANCE: ComkitActivityLifecycleUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ComkitActivityLifecycleUtils()
        }
        private val TAG = ComkitActivityLifecycleUtils::class.simpleName
    }
    
    
    /** 应用是否处于前端 */
    var isForeground = false
    var count = 0
    var activityList: MutableList<Activity>? = null
    var currentShowActivity: Activity? = null
    
    init {
        count = 0
        isForeground = false
        activityList = ArrayList()
    }
    
    // ---------- ---------- ---------- Override Methods ---------- ---------- ----------
    
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        currentShowActivity = activity
        activityList?.add(activity)
    }
    
    override fun onActivityStarted(activity: Activity) {
        count++
    }
    
    override fun onActivityResumed(activity: Activity) {
        isForeground = true
        sendBroadcast(activity)
        currentShowActivity = activity
    }
    
    override fun onActivityPaused(activity: Activity) {
    }
    
    override fun onActivityStopped(activity: Activity) {
        //重要，如果Activity的stop中判断应用再前后台，一定要报super.stop()放在第一行
        count--
        if (count == 0) {
            isForeground = false
            sendBroadcast(activity)
            //切换到后台,提示用户
            if (activity != null) {
                // ToastUtil.showMessage(ResUtil.getString(EPApp.getApp(), R.string.lifecycle_in_background))
            }
        }
    }
    
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }
    
    override fun onActivityDestroyed(activity: Activity) {
        activityList?.remove(activity)
        //正常打开新页面，都是现在新页面的onCreate，再调用前一个页面的onDestroy
        //返回的时候，返回的页面先走onResume,再调用返回前一个页面的onDestroy
        //如果销毁activity和记录的是同一个对象的话，说明应用的所有页面都关闭了
        if (currentShowActivity === activity) {
            currentShowActivity = null
        }
    }
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    /**
     * 关闭所有的activity
     */
    fun closeAllActivity() {
        if (activityList != null && !activityList!!.isEmpty()) {
            for (activity in activityList!!) {
                // if (!ActivityUtil.isActivityDestory(activity)) {
                //     activity.finish()
                // }
            }
            activityList!!.clear()
        }
    }
    
    fun getActivityNum(): Int {
        return activityList?.size ?: 0
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    /**
     * ComkitActivityLifecycleUtils.sendBroadcast([activity])
     * <Description> 发送应用内广播
     * <Details>
     *
     * @param [activity]
     * @return
     * @author Ace Yan
     * @github githubyss
     * @createdTime 2020/12/17 19:16:06
     */
    private fun sendBroadcast(activity: Activity) {
        val intent = Intent(Constants.INTENT_ACTION_IS_FOREGROUND)
        intent.putExtra("isForeground", isForeground)
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent)
    }
    
    
    // ---------- ---------- ---------- Getter ---------- ---------- ----------
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
}
