package com.githubyss.mobile.common.kit

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.githubyss.mobile.common.kit.util.AppUtils


/**
 * ComkitApplicationConfig
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 09:47:37
 */
object ComkitApplicationConfig {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    @SuppressLint("StaticFieldLeak")
    var application: Application? = null
        private set
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /**
     * Init utils.
     * Init it in the class of Application.
     *
     * @param context context
     */
    fun init(context: Context?) {
        if (context == null) {
            init(AppUtils.getApplicationByReflect())
            return
        }
        init(context.applicationContext as Application)
    }
    
    /**
     * Init utils.
     * Init it in the class of Application.
     *
     * @param app application
     */
    fun init(app: Application?) {
        if (application == null) {
            application = app ?: AppUtils.getApplicationByReflect()
        } else if (app != null && app.javaClass != application?.javaClass) {
            // application?.unregisterActivityLifecycleCallbacks(ActivityUtils.activityLifecycle)
            // ActivityUtils.activityLifecycle.activityList.clear()
            application = app
            // application?.registerActivityLifecycleCallbacks(ActivityUtils.activityLifecycle)
        }
    }
    
    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    fun getApp(): Application? {
        if (application != null) {
            return application /*?: throw NullPointerException("application is null...")*/
        }
        val app: Application? = AppUtils.getApplicationByReflect()
        init(app)
        return app
    }
}
