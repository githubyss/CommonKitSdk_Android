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
object ComkitApplication {
    lateinit var application: Application
        private set

    fun init(application: Application) {
        ComkitApplication.application = application
    }
}
