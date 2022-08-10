package com.githubyss.mobile.common.kit.app.application

import com.githubyss.mobile.common.kit.base.application.ComkitBaseApplication
import kotlin.properties.Delegates


/**
 * ComkitApplication
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/08/24 19:34:38
 */
class ComkitApplication : ComkitBaseApplication() {

    /** ****************************** Properties ****************************** */

    /**  */
    companion object {
        var instance: ComkitApplication by Delegates.notNull()
            private set

        private val TAG: String = ComkitApplication::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
