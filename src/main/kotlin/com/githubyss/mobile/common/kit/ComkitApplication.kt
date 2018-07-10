package com.githubyss.mobile.common.kit

import android.app.Application

/**
 * ComkitApplication
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


    lateinit var application: Application
        private set


    fun init(application: Application): ComkitApplication {
        instance.application = application
        return instance
    }
}
