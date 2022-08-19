package com.githubyss.mobile.common.kit.app.application

import com.alibaba.android.arouter.launcher.ARouter
import com.githubyss.common.base.application.BaseApplication
import com.githubyss.mobile.common.kit.BuildConfig
import com.githubyss.mobile.common.kit.util.LOG_LEVEL_VERBOSE
import com.githubyss.mobile.common.kit.util.disableLog
import com.githubyss.mobile.common.kit.util.enableLog
import com.githubyss.mobile.common.kit.util.logLevel


/**
 * ComkitApplication
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/08/24 19:34:38
 */
class ComkitApplication : BaseApplication() {

    /** ****************************** Properties ****************************** */

    /**  */
    companion object {
        private val TAG: String = ComkitApplication::class.java.simpleName
        fun getApp() = instance
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    /**  */
    override fun initLog() {
        // 可调试模式，启用日志
        if (BuildConfig.DEBUG) {
            enableLog()
        }
        else {
            disableLog()
        }
        logLevel = LOG_LEVEL_VERBOSE
    }

    /**  */
    override fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(instance)
    }
}
