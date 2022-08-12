package com.githubyss.mobile.common.kit

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.githubyss.common.base.lifecycle.LifecycleContainer
import com.githubyss.mobile.common.kit.util.getApplicationByReflect


/**
 * ComkitApplicationConfig
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 09:47:37
 */
object ComkitApplicationConfig {

    /** ****************************** Properties ****************************** */

    @SuppressLint("StaticFieldLeak")
    private var application: Application? = null


    /** ****************************** Functions ****************************** */

    /**
     * Init utils.
     * Init it in the class of Application.
     *
     * @param context context
     */
    fun init(context: Context?) {
        if (context != null) {
            // context 非空
            // 通过 context.applicationContext 进行初始化
            init(context.applicationContext as Application)
            return
        }
        // context 空
        // 通过反射得到的 application 进行初始化
        init(getApplicationByReflect())
    }

    /**
     * Init utils.
     * Init it in the class of Application.
     *
     * @param app application
     */
    fun init(app: Application?) {
        // 缓存 application 空
        if (application == null) {
            // 初始化缓存 application 的值
            application = app ?: getApplicationByReflect()
            return
        }
        // 缓存 application 非空
        // 参数 app 非空，并与缓存 application 非同类
        if (app != null && app.javaClass != application?.javaClass) {
            // 重置 ActivityLifecycleCallbacks，以及清空缓存的 activityList
            application?.unregisterActivityLifecycleCallbacks(LifecycleContainer.activityLifecycleCallbacks)
            LifecycleContainer.activityLifecycleCallbacks.activityList.clear()
            application = app
            application?.registerActivityLifecycleCallbacks(LifecycleContainer.activityLifecycleCallbacks)
        }
        // 其他情况，保持 application 不变
    }

    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    fun getApp(): Application {
        // 缓存 application 空
        if (application == null) {
            // 通过反射得到的 application 进行初始化
            init(getApplicationByReflect())
        }
        // 缓存 application 非空
        // 返回 application
        return application ?: throw NullPointerException("application is null...")
    }
}
