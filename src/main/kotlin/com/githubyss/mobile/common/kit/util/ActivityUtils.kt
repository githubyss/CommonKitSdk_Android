package com.githubyss.mobile.common.kit.util

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.AnimRes
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.lifecycle.ActivityLifecycleImpl


/**
 * ActivityUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/03 14:37:52
 */
object ActivityUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = ActivityUtils::class.simpleName ?: "simpleName is null"
    
    /** The activity lifecycle callbacks impl. */
    var activityLifecycle: ActivityLifecycleImpl = ActivityLifecycleImpl.INSTANCE
    
    /** The list of activity. */
    var activityList: List<Activity> = activityLifecycle.activityList
    
    /** The name of launcher activity. */
    var launcherActivityName: String = getLauncherActivityName(ComkitApplicationConfig.getApp()?.packageName)
    
    /** The top activity in activity's stack. */
    var topActivity: Activity? = activityLifecycle.getTopActivity()
    
    var topActivityOrApp: Context? = if (AppUtils.isAppForeground()) {
        activityLifecycle.getTopActivity()
    } else {
        ComkitApplicationConfig.getApp()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Getter ********** ********** */
    
    /**
     * Return the activity by view.
     *
     * @param view  The view.
     * @return the activity by view.
     */
    fun getActivityByView(view: View?): Activity? {
        view ?: return null
        
        return getActivityByContext(view.context ?: return null)
    }
    
    /**
     * Return the activity by context.
     *
     * @param context The context.
     * @return the activity by context.
     */
    fun getActivityByContext(context: Context? = ComkitApplicationConfig.getApp()): Activity? {
        context ?: return null
        
        var context = context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }
    
    /**
     * Return the name of launcher activity.
     *
     * @param packageName The name of the package.
     * @param context     The context.
     * @return the name of launcher activity
     */
    fun getLauncherActivityName(packageName: String?, context: Context? = ComkitApplicationConfig.getApp()): String {
        packageName ?: return ""
        context ?: return ""
        if (StringUtils.isSpace(packageName)) return ""
        
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        val pm = context.packageManager ?: return ""
        val info = pm.queryIntentActivities(intent, 0)
        for (aInfo in info) {
            if (aInfo.activityInfo.packageName == packageName) {
                return aInfo.activityInfo.name
            }
        }
        LogcatUtils.d(TAG, "no $packageName")
        return ""
    }
    
    /**
     * Return the icon of activity.
     *
     * @param clz     The activity class.
     * @param context The context.
     * @return the icon of activity
     */
    fun getActivityIcon(clz: Class<out Activity?>?, context: Context? = ComkitApplicationConfig.getApp()): Drawable? {
        clz ?: return null
        context ?: return null
        
        return getActivityIcon(ComponentName(context, clz), context)
    }
    
    /**
     * Return the icon of activity.
     *
     * @param activity The activity.
     * @param context  The context.
     * @return the icon of activity
     */
    fun getActivityIcon(activity: Activity?, context: Context? = ComkitApplicationConfig.getApp()): Drawable? {
        activity ?: return null
        context ?: return null
        
        return getActivityIcon(activity.componentName, context)
    }
    
    /**
     * Return the icon of activity.
     *
     * @param activityName The name of activity.
     * @param context      The context.
     * @return the icon of activity
     */
    fun getActivityIcon(activityName: ComponentName?, context: Context? = ComkitApplicationConfig.getApp()): Drawable? {
        activityName ?: return null
        context ?: return null
        
        val packageManager: PackageManager = context.packageManager
        return try {
            packageManager.getActivityIcon(activityName)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }
    
    /**
     * Return the logo of activity.
     *
     * @param clz     The activity class.
     * @param context The context.
     * @return the logo of activity
     */
    fun getActivityLogo(clz: Class<out Activity?>?, context: Context? = ComkitApplicationConfig.getApp()): Drawable? {
        clz ?: return null
        context ?: return null
        
        return getActivityLogo(ComponentName(context, clz), context)
    }
    
    /**
     * Return the logo of activity.
     *
     * @param activity The activity.
     * @param context  The context.
     * @return the logo of activity
     */
    fun getActivityLogo(activity: Activity?, context: Context? = ComkitApplicationConfig.getApp()): Drawable? {
        activity ?: return null
        context ?: return null
        
        return getActivityLogo(activity.componentName, context)
    }
    
    /**
     * Return the logo of activity.
     *
     * @param activityName The name of activity.
     * @param context      The context.
     * @return the logo of activity
     */
    fun getActivityLogo(activityName: ComponentName?, context: Context? = ComkitApplicationConfig.getApp()): Drawable? {
        activityName ?: return null
        context ?: return null
        
        val packageManager = context.packageManager
        return try {
            packageManager.getActivityLogo(activityName)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }
    
    /** ********** ********** Checker ********** ********** */
    
    /**
     * Return whether the activity exists.
     *
     * @param packageName The name of the package.
     * @param cls         The name of the class.
     * @param context     The context.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isActivityExists(packageName: String?, cls: String?, context: Context? = ComkitApplicationConfig.getApp()): Boolean {
        packageName ?: return false
        cls ?: return false
        context ?: return false
        
        val intent = Intent()
        intent.setClassName(packageName, cls)
        return !(context.packageManager.resolveActivity(intent, 0) == null || context.packageManager?.let { intent.resolveActivity(it) } == null || context.packageManager.queryIntentActivities(intent, 0).size == 0)
    }
    
    /**
     * Return whether the activity exists in activity's stack.
     *
     * @param activity The activity.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isActivityExistsInStack(activity: Activity?): Boolean {
        activity ?: return false
        
        val activities: List<Activity> = activityList
        for (aActivity in activities) {
            if (aActivity == activity) {
                return true
            }
        }
        return false
    }
    
    /**
     * Return whether the activity exists in activity's stack.
     *
     * @param clz The activity class.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isActivityExistsInStack(clz: Class<out Activity?>?): Boolean {
        clz ?: return false
        
        val activities: List<Activity> = activityList
        for (aActivity in activities) {
            if (aActivity.javaClass == clz) {
                return true
            }
        }
        return false
    }
    
    /**
     * Return whether the activity is destroy.
     *
     * @param activity The activity.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isActivityDestroy(activity: Activity?): Boolean {
        return !isActivityAlive(activity)
    }
    
    /**
     * Return whether the activity is alive.
     *
     * @param activity The activity.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isActivityAlive(activity: Activity?): Boolean {
        activity ?: return false
        
        return !activity.isFinishing && (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1 || !activity.isDestroyed)
    }
    
    /**
     * Return whether the fragment is destroy.
     *
     * @param activity The activity.
     * @param fragment The fragment.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFragmentDestroy(activity: Activity?, fragment: Fragment?): Boolean {
        return !isFragmentAlive(activity, fragment)
    }
    
    /**
     * Return whether the fragment is alive.
     *
     * @param activity The activity.
     * @param fragment The fragment.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFragmentAlive(activity: Activity?, fragment: Fragment?): Boolean {
        activity ?: return false
        fragment ?: return false
        
        return isActivityAlive(activity) && !fragment.isDetached
    }
    
    /** ********** ********** Operator ********** ********** */
    
    /** ********** startActivity by options ********** */
    
    /**
     * Start the activity.
     *
     * @param context The context.
     * @param clz     The activity class.
     * @param extras  The Bundle of extras to add to this intent.
     * @param options Additional options for how the Activity should be started.
     */
    fun startActivity(context: Context? = topActivityOrApp, clz: Class<out Activity?>?, extras: Bundle? = null, options: Bundle? = null) {
        context ?: return
        clz ?: return
        
        startActivity(context, context.packageName, clz.name, extras, options)
    }
    
    /**
     * Start the activity.
     *
     * @param activity The activity.
     * @param clz      The activity class.
     * @param extras   The Bundle of extras to add to this intent.
     * @param options  Additional options for how the Activity should be started.
     */
    fun startActivity(activity: Activity?, clz: Class<out Activity?>?, extras: Bundle? = null, options: Bundle? = null) {
        activity ?: return
        clz ?: return
        
        startActivity(activity, activity.packageName, clz.name, extras, options)
    }
    
    /**
     * Start the activity.
     *
     * @param context     The context.
     * @param packageName The name of the package.
     * @param cls         The name of the class.
     * @param extras      The Bundle of extras to add to this intent.
     * @param options     Additional options for how the Activity should be started.
     */
    fun startActivity(context: Context? = topActivityOrApp, packageName: String?, cls: String?, extras: Bundle? = null, options: Bundle? = null) {
        context ?: return
        packageName ?: return
        cls ?: return
        
        val intent = Intent(Intent.ACTION_VIEW)
        if (extras != null) {
            intent.putExtras(extras)
        }
        intent.component = ComponentName(packageName, cls)
        startActivity(context, intent, options)
    }
    
    /**
     * Start the activity.
     *
     * @param activity    The activity.
     * @param packageName The name of the package.
     * @param cls         The name of the class.
     * @param extras      The Bundle of extras to add to this intent.
     * @param options     Additional options for how the Activity should be started.
     */
    fun startActivity(activity: Activity?, packageName: String?, cls: String?, extras: Bundle? = null, options: Bundle? = null) {
        activity ?: return
        packageName ?: return
        cls ?: return
        
        val intent = Intent(Intent.ACTION_VIEW)
        if (extras != null) {
            intent.putExtras(extras)
        }
        intent.component = ComponentName(packageName, cls)
        startActivity(activity, intent, options)
    }
    
    /**
     * Start the activity.
     *
     * @param context The context.
     * @param intent  The description of the activity to start.
     * @param options Additional options for how the Activity should be started.
     * @return `true`: success<br></br>`false`: fail
     */
    fun startActivity(context: Context? = topActivityOrApp, intent: Intent?, options: Bundle? = null): Boolean {
        context ?: return false
        intent ?: return false
        
        if (!isIntentAvailable(context, intent)) {
            LogcatUtils.d(TAG, "intent is unavailable")
            return false
        }
        if (context !is Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            context.startActivity(intent, options)
        } else {
            context.startActivity(intent)
        }
        return true
    }
    
    /**
     * Start the activity.
     *
     * @param activity The activity.
     * @param intent   The description of the activity to start.
     * @param options  Additional options for how the Activity should be started.
     */
    fun startActivity(activity: Activity?, intent: Intent?, options: Bundle? = null): Boolean {
        activity ?: return false
        intent ?: return false
        
        if (!isIntentAvailable(activity, intent)) {
            Log.e("ActivityUtils", "intent is unavailable")
            return false
        }
        if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivity(intent, options)
        } else {
            activity.startActivity(intent)
        }
        return true
    }
    
    /** ********** startActivity by enterAnim, exitAnim ********** */
    
    /**
     * Start the activity.
     *
     * @param context   The context.
     * @param clz       The activity class.
     * @param extras    The Bundle of extras to add to this intent.
     * @param enterAnim A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivity(context: Context? = topActivityOrApp, clz: Class<out Activity?>?, extras: Bundle? = null, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        context ?: return
        clz ?: return
        
        startActivity(context, context.packageName, clz.name, extras, getOptionsBundle(context, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && context is Activity) {
            context.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /**
     * Start the activity.
     *
     * @param activity  The activity.
     * @param clz       The activity class.
     * @param extras    The Bundle of extras to add to this intent.
     * @param enterAnim A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivity(activity: Activity?, clz: Class<out Activity?>?, extras: Bundle? = null, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        activity ?: return
        clz ?: return
        
        startActivity(activity, activity.packageName, clz.name, extras, getOptionsBundle(activity, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /**
     * Start the activity.
     *
     * @param context     The context.
     * @param packageName The name of the package.
     * @param cls         The name of the class.
     * @param extras      The Bundle of extras to add to this intent.
     * @param enterAnim   A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim    A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivity(context: Context? = topActivityOrApp, packageName: String?, cls: String?, extras: Bundle? = null, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        context ?: return
        packageName ?: return
        cls ?: return
        
        startActivity(context, packageName, cls, extras, getOptionsBundle(context, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && context is Activity) {
            context.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /**
     * Start the activity.
     *
     * @param activity    The activity.
     * @param packageName The name of the package.
     * @param cls         The name of the class.
     * @param extras      The Bundle of extras to add to this intent.
     * @param enterAnim   A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim    A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivity(activity: Activity?, packageName: String?, cls: String?, extras: Bundle? = null, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        activity ?: return
        packageName ?: return
        cls ?: return
        
        startActivity(activity, packageName, cls, extras, getOptionsBundle(activity, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /**
     * Start the activity.
     *
     * @param context   The context.
     * @param intent    The description of the activity to start.
     * @param enterAnim A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity.
     * @return `true`: success<br></br>`false`: fail
     */
    fun startActivity(context: Context? = topActivityOrApp, intent: Intent?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int): Boolean {
        context ?: return false
        intent ?: return false
        
        val isSuccess = startActivity(context, intent, getOptionsBundle(context, enterAnim, exitAnim))
        if (isSuccess) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && context is Activity) {
                context.overridePendingTransition(enterAnim, exitAnim)
            }
        }
        return isSuccess
    }
    
    /**
     * Start the activity.
     *
     * @param activity  The activity.
     * @param intent    The description of the activity to start.
     * @param enterAnim A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivity(activity: Activity?, intent: Intent?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        activity ?: return
        intent ?: return
        
        startActivity(activity, intent, getOptionsBundle(activity, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /** ********** startActivity by sharedElements ********** */
    
    /**
     * Start the activity.
     *
     * @param activity       The activity.
     * @param clz            The activity class.
     * @param extras         The Bundle of extras to add to this intent.
     * @param sharedElements The names of the shared elements to transfer to the called activity and their associated Views.
     */
    fun startActivity(activity: Activity?, clz: Class<out Activity?>?, extras: Bundle? = null, vararg sharedElements: View) {
        activity ?: return
        clz ?: return
        
        startActivity(activity, activity.packageName, clz.name, extras, getOptionsBundle(activity, sharedElements))
    }
    
    /**
     * Start the activity.
     *
     * @param activity       The activity.
     * @param packageName    The name of the package.
     * @param cls            The name of the class.
     * @param extras         The Bundle of extras to add to this intent.
     * @param sharedElements The names of the shared elements to transfer to the called activity and their associated Views.
     */
    fun startActivity(activity: Activity?, packageName: String?, cls: String?, extras: Bundle? = null, vararg sharedElements: View) {
        activity ?: return
        packageName ?: return
        cls ?: return
        
        startActivity(activity, packageName, cls, extras, getOptionsBundle(activity, sharedElements))
    }
    
    /**
     * Start the activity.
     *
     * @param activity       The activity.
     * @param intent         The description of the activity to start.
     * @param sharedElements The names of the shared elements to transfer to the called activity and their associated Views.
     */
    fun startActivity(activity: Activity?, intent: Intent?, vararg sharedElements: View) {
        activity ?: return
        intent ?: return
        
        startActivity(activity, intent, getOptionsBundle(activity, sharedElements))
    }
    
    /** ********** startActivityForResult by options ********** */
    
    /**
     * Start the activity.
     *
     * @param activity    The activity.
     * @param clz         The activity class.
     * @param requestCode if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param extras      The Bundle of extras to add to this intent.
     * @param options     Additional options for how the Activity should be started.
     */
    fun startActivityForResult(activity: Activity?, clz: Class<out Activity?>?, requestCode: Int, extras: Bundle? = null, options: Bundle? = null) {
        activity ?: return
        clz ?: return
        
        startActivityForResult(activity, activity.packageName, clz.name, requestCode, extras, options)
    }
    
    /**
     * Start the activity for result.
     *
     * @param activity    The activity.
     * @param packageName The name of the package.
     * @param cls         The name of the class.
     * @param requestCode if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param extras      The Bundle of extras to add to this intent.
     * @param options     Additional options for how the Activity should be started.
     */
    fun startActivityForResult(activity: Activity?, packageName: String?, cls: String?, requestCode: Int, extras: Bundle? = null, options: Bundle? = null): Boolean {
        activity ?: return false
        packageName ?: return false
        cls ?: return false
        
        val intent = Intent(Intent.ACTION_VIEW)
        if (extras != null) intent.putExtras(extras)
        intent.component = ComponentName(packageName, cls)
        return startActivityForResult(activity, intent, requestCode, options)
    }
    
    /**
     * Start the activity for result.
     *
     * @param activity    The activity.
     * @param intent      The description of the activity to start.
     * @param requestCode if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param options     Additional options for how the Activity should be started.
     */
    fun startActivityForResult(activity: Activity?, intent: Intent?, requestCode: Int, options: Bundle? = null): Boolean {
        activity ?: return false
        intent ?: return false
        
        if (!isIntentAvailable(activity, intent)) {
            Log.e("ActivityUtils", "intent is unavailable")
            return false
        }
        if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivityForResult(intent, requestCode, options)
        } else {
            activity.startActivityForResult(intent, requestCode)
        }
        return true
    }
    
    /** ********** startActivityForResult by enterAnim, exitAnim ********** */
    
    /**
     * Start the activity.
     *
     * @param activity    The activity.
     * @param clz         The activity class.
     * @param requestCode if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param extras      The Bundle of extras to add to this intent.
     * @param enterAnim   A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim    A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivityForResult(activity: Activity?, clz: Class<out Activity?>?, requestCode: Int, extras: Bundle? = null, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        activity ?: return
        clz ?: return
        
        startActivityForResult(activity, activity.packageName, clz.name, requestCode, extras, getOptionsBundle(activity, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /**
     * Start the activity for result.
     *
     * @param activity    The activity.
     * @param packageName The name of the package.
     * @param cls         The name of the class.
     * @param requestCode if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param extras      The Bundle of extras to add to this intent.
     * @param enterAnim   A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim    A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivityForResult(activity: Activity?, packageName: String?, cls: String?, requestCode: Int, extras: Bundle? = null, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        activity ?: return
        packageName ?: return
        cls ?: return
        
        startActivityForResult(activity, packageName, cls, requestCode, extras, getOptionsBundle(activity, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /**
     * Start the activity for result.
     *
     * @param activity    The activity.
     * @param intent      The description of the activity to start.
     * @param requestCode if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param enterAnim   A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim    A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivityForResult(activity: Activity?, intent: Intent?, requestCode: Int, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        activity ?: return
        intent ?: return
        
        startActivityForResult(activity, intent, requestCode, getOptionsBundle(activity, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /** ********** startActivityForResult by sharedElements ********** */
    
    /**
     * Start the activity.
     *
     * @param activity       The activity.
     * @param clz            The activity class.
     * @param requestCode    if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param extras         The Bundle of extras to add to this intent.
     * @param sharedElements The names of the shared elements to transfer to the called activity and their associated Views.
     */
    fun startActivityForResult(activity: Activity?, clz: Class<out Activity?>?, requestCode: Int, extras: Bundle? = null, vararg sharedElements: View) {
        activity ?: return
        clz ?: return
        
        startActivityForResult(activity, activity.packageName, clz.name, requestCode, extras, getOptionsBundle(activity, sharedElements))
    }
    
    /**
     * Start the activity for result.
     *
     * @param activity       The activity.
     * @param packageName    The name of the package.
     * @param cls            The name of the class.
     * @param requestCode    if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param extras         The Bundle of extras to add to this intent.
     * @param sharedElements The names of the shared elements to transfer to the called activity and their associated Views.
     */
    fun startActivityForResult(activity: Activity?, packageName: String?, cls: String?, requestCode: Int, extras: Bundle? = null, vararg sharedElements: View) {
        activity ?: return
        packageName ?: return
        cls ?: return
        
        startActivityForResult(activity, packageName, cls, requestCode, extras, getOptionsBundle(activity, sharedElements))
    }
    
    /**
     * Start the activity for result.
     *
     * @param activity       The activity.
     * @param intent         The description of the activity to start.
     * @param requestCode    if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
     * @param sharedElements The names of the shared elements to transfer to the called Activity and their associated Views.
     */
    fun startActivityForResult(activity: Activity?, intent: Intent?, requestCode: Int, vararg sharedElements: View) {
        activity ?: return
        intent ?: return
        
        startActivityForResult(activity, intent, requestCode, getOptionsBundle(activity, sharedElements))
    }
    
    /** ********** startActivities by options ********** */
    
    /**
     * Start activities.
     *
     * @param context The context.
     * @param intents The descriptions of the activities to start.
     * @param options Additional options for how the Activity should be started.
     */
    fun startActivities(context: Context? = topActivityOrApp, intents: Array<Intent>?, options: Bundle? = null) {
        context ?: return
        intents ?: return
        
        if (context !is Activity) {
            for (intent in intents) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
        if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            context.startActivities(intents, options)
        } else {
            context.startActivities(intents)
        }
    }
    
    /**
     * Start activities.
     *
     * @param activity The activity.
     * @param intents  The descriptions of the activities to start.
     * @param options  Additional options for how the Activity should be started.
     */
    fun startActivities(activity: Activity?, intents: Array<Intent>?, options: Bundle? = null) {
        activity ?: return
        intents ?: return
        
        if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivities(intents, options)
        } else {
            activity.startActivities(intents)
        }
    }
    
    /** ********** startActivities by enterAnim, exitAnim ********** */
    
    /**
     * Start activities.
     *
     * @param context   The context.
     * @param intents   The descriptions of the activities to start.
     * @param enterAnim A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivities(context: Context? = topActivityOrApp, intents: Array<Intent>?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        context ?: return
        intents ?: return
        
        startActivities(context, intents, getOptionsBundle(context, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && context is Activity) {
            context.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /**
     * Start activities.
     *
     * @param activity  The activity.
     * @param intents   The descriptions of the activities to start.
     * @param enterAnim A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity.
     */
    fun startActivities(activity: Activity?, intents: Array<Intent>?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
        activity ?: return
        intents ?: return
        
        startActivities(activity, intents, getOptionsBundle(activity, enterAnim, exitAnim))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /** ********** startHomeActivity ********** */
    
    /**
     * Start home activity.
     */
    fun startHomeActivity() {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent = homeIntent)
    }
    
    /** ********** finishActivity by enterAnim, exitAnim ********** */
    
    /**
     * Finish the activity.
     *
     * @param clz        The activity class.
     * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
     * @param enterAnim  A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim   A resource ID of the animation resource to use for the outgoing activity.
     */
    @JvmOverloads
    fun finishActivity(clz: Class<out Activity?>?, isLoadAnim: Boolean = false, @AnimRes enterAnim: Int = 0, @AnimRes exitAnim: Int = 0) {
        clz ?: return
        
        val activities = activityList
        for (activity in activities) {
            if (activity.javaClass == clz) {
                activity.finish()
                if (!isLoadAnim) {
                    activity.overridePendingTransition(enterAnim, exitAnim)
                }
            }
        }
    }
    
    /**
     * Finish the activity.
     *
     * @param activity   The activity.
     * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
     * @param enterAnim  A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim   A resource ID of the animation resource to use for the outgoing activity.
     */
    @JvmOverloads
    fun finishActivity(activity: Activity?, isLoadAnim: Boolean = false, @AnimRes enterAnim: Int = 0, @AnimRes exitAnim: Int = 0) {
        activity ?: return
        
        activity.finish()
        if (!isLoadAnim) {
            activity.overridePendingTransition(enterAnim, exitAnim)
        }
    }
    
    /** ********** finishToActivity by enterAnim, exitAnim ********** */
    
    /**
     * Finish to the activity.
     *
     * @param clz           The activity class.
     * @param isIncludeSelf True to include the activity, false otherwise.
     * @param isLoadAnim    True to use animation for the outgoing activity, false otherwise.
     * @param enterAnim     A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim      A resource ID of the animation resource to use for the outgoing activity.
     */
    @JvmOverloads
    fun finishToActivity(clz: Class<out Activity?>?, isIncludeSelf: Boolean, isLoadAnim: Boolean = false, @AnimRes enterAnim: Int = 0, @AnimRes exitAnim: Int = 0): Boolean {
        clz ?: return false
        
        val activities = activityList
        for (i in activities.indices.reversed()) {
            val aActivity = activities[i]
            if (aActivity.javaClass == clz) {
                if (isIncludeSelf) {
                    finishActivity(aActivity, isLoadAnim, enterAnim, exitAnim)
                }
                return true
            }
            finishActivity(aActivity, isLoadAnim)
        }
        return false
    }
    
    /**
     * Finish to the activity.
     *
     * @param activity      The activity.
     * @param isIncludeSelf True to include the activity, false otherwise.
     * @param isLoadAnim    True to use animation for the outgoing activity, false otherwise.
     * @param enterAnim     A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim      A resource ID of the animation resource to use for the outgoing activity.
     */
    @JvmOverloads
    fun finishToActivity(activity: Activity?, isIncludeSelf: Boolean, isLoadAnim: Boolean = false, @AnimRes enterAnim: Int = 0, @AnimRes exitAnim: Int = 0): Boolean {
        activity ?: return false
        
        val activities = activityList
        for (i in activities.indices.reversed()) {
            val aActivity = activities[i]
            if (aActivity == activity) {
                if (isIncludeSelf) {
                    finishActivity(aActivity, isLoadAnim, enterAnim, exitAnim)
                }
                return true
            }
            finishActivity(aActivity, isLoadAnim)
        }
        return false
    }
    
    /** ********** finishOtherActivities by enterAnim, exitAnim ********** */
    
    /**
     * Finish the activities whose type not equals the activity class.
     *
     * @param clz        The activity class.
     * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
     * @param enterAnim  A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim   A resource ID of the animation resource to use for the outgoing activity.
     */
    @JvmOverloads
    fun finishOtherActivities(clz: Class<out Activity?>?, isLoadAnim: Boolean = false, @AnimRes enterAnim: Int = 0, @AnimRes exitAnim: Int = 0) {
        clz ?: return
        
        val activities = activityList
        for (i in activities.indices.reversed()) {
            val activity = activities[i]
            if (activity.javaClass != clz) {
                finishActivity(activity, isLoadAnim, enterAnim, exitAnim)
            }
        }
    }
    
    /** ********** finishAllActivities by enterAnim, exitAnim ********** */
    
    /**
     * Finish all of activities.
     *
     * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
     * @param enterAnim  A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim   A resource ID of the animation resource to use for the outgoing activity.
     */
    @JvmOverloads
    fun finishAllActivities(isLoadAnim: Boolean = false, @AnimRes enterAnim: Int = 0, @AnimRes exitAnim: Int = 0) {
        val activityList = activityList
        for (i in activityList.indices.reversed()) { // remove from top
            val activity = activityList[i]
            // sActivityList remove the index activity at onActivityDestroyed
            activity.finish()
            if (!isLoadAnim) {
                activity.overridePendingTransition(enterAnim, exitAnim)
            }
        }
    }
    
    /** ********** finishAllActivitiesExceptNewest by enterAnim, exitAnim ********** */
    
    /**
     * Finish all of activities except the newest activity.
     *
     * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
     * @param enterAnim  A resource ID of the animation resource to use for the incoming activity.
     * @param exitAnim   A resource ID of the animation resource to use for the outgoing activity.
     */
    @JvmOverloads
    fun finishAllActivitiesExceptNewest(isLoadAnim: Boolean = false, @AnimRes enterAnim: Int = 0, @AnimRes exitAnim: Int = 0) {
        val activities = activityList
        for (i in activities.size - 2 downTo 0) {
            finishActivity(activities[i], isLoadAnim, enterAnim, exitAnim)
        }
    }
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    private fun isIntentAvailable(context: Context? = ComkitApplicationConfig.getApp(), intent: Intent?): Boolean {
        context ?: return false
        intent ?: return false
        
        return context.packageManager?.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)?.size ?: -1 > 0
    }
    
    private fun getOptionsBundle(context: Context? = ComkitApplicationConfig.getApp(), enterAnim: Int, exitAnim: Int): Bundle? {
        context ?: return null
        
        return ActivityOptionsCompat.makeCustomAnimation(context, enterAnim, exitAnim).toBundle()
    }
    
    private fun getOptionsBundle(activity: Activity?, sharedElements: Array<out View>?): Bundle? {
        activity ?: return null
        
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return null
        if (sharedElements == null) return null
        val len = sharedElements.size
        if (len <= 0) return null
        val pairs: Array<Pair<View, String>?> = arrayOfNulls(len)
        for (i in 0 until len) {
            pairs[i] = Pair.create(sharedElements[i], sharedElements[i].transitionName)
        }
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *pairs).toBundle()
    }
}
