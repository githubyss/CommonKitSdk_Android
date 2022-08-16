package com.githubyss.mobile.common.kit.base.application

import androidx.core.os.TraceCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.githubyss.common.base.application.BaseApplication
import com.githubyss.mobile.common.kit.BuildConfig
import com.githubyss.mobile.common.kit.util.LOG_LEVEL_VERBOSE
import com.githubyss.mobile.common.kit.util.disableLog
import com.githubyss.mobile.common.kit.util.enableLog
import com.githubyss.mobile.common.kit.util.logLevel


/**
 * ComkitBaseApplication
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/10 10:29:08
 */
abstract class ComkitBaseApplication : BaseApplication() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        private val TAG: String = ComkitBaseApplication::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

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

    override fun initTrace() {
        TraceCompat.beginSection("")
        TraceCompat.endSection()
    }


    /** ****************************** Functions ****************************** */

    /**  */
}
