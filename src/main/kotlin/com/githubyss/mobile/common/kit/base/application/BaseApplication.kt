package com.githubyss.mobile.common.kit.base.application

import android.app.Application
import android.content.Context
import androidx.core.os.TraceCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.githubyss.mobile.common.kit.BuildConfig
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.util.*
import kotlin.properties.Delegates


abstract class BaseApplication : Application() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        private val TAG: String = BaseApplication::class.java.simpleName

        private var instance: BaseApplication by Delegates.notNull()
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreate() {
        super.onCreate()
        instance = this

        initComkit(instance)
        initComnet(instance)
        initLog(LOG_LEVEL_VERBOSE)
        initARouter(instance)
        registerLifecycle()
    }

    /**  */
    override fun onTerminate() {
        unregisterLifecycle()
        super.onTerminate()
    }

    /**  */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        TraceCompat.beginSection("")
        TraceCompat.endSection()
    }


    /** ****************************** Open ****************************** */

    /**  */
    open fun initComkit(application: Application) {
        ComkitApplicationConfig.init(application)
    }

    /**  */
    open fun initComnet(application: Application) {}

    /**  */
    open fun initLog(level: Int) {
        // 可调试模式，启用日志
        if (BuildConfig.DEBUG) {
            enableLog()
        }
        else {
            disableLog()
        }
        logLevel = level
    }

    /**  */
    open fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }


    /** ****************************** Functions ****************************** */

    /**  */
    private fun registerLifecycle() {
        registerActivityLifecycleCallbacks(ActivityUtils.activityLifecycle)
    }

    /**  */
    private fun unregisterLifecycle() {
        unregisterActivityLifecycleCallbacks(ActivityUtils.activityLifecycle)
        ActivityUtils.activityLifecycle.activityList.clear()
    }
}
