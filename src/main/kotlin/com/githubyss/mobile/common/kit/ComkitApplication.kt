package com.githubyss.mobile.common.kit

import android.app.Application

/**
 * ComkitApplication.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComkitApplication private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = ComkitApplication()
    }


    var application: Application? = null
        private set


    fun init(application: Application) {
        if (ComkitApplication.instance.application != null) {
            return
        }

        ComkitApplication.instance.application = application
    }
}
