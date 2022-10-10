package com.githubyss.common.kit.util

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.githubyss.common.base.application.BaseApplicationHolder
import com.githubyss.common.base.lifecycle.LifecycleHolder
import java.lang.ref.WeakReference


/**
 * ActivityUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/03 14:37:52
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "AppUtils"


/** ****************************** Functions ****************************** */

/** The list of activity. */
var activityList = LifecycleHolder.activityLifecycle.activityHolder.activityList

/** The name of launcher activity. */
var launcherActivityName = getLauncherActivityName(BaseApplicationHolder.getApp().packageName)

/** The top activity in activity's stack. */
var topActivity = WeakReference<Activity?>(LifecycleHolder.activityLifecycle.activityHolder.getTopActivity())

/**  */
var topActivityOrApp = when {
    isAppForeground() -> WeakReference<Context?>(LifecycleHolder.activityLifecycle.activityHolder.getTopActivity())
    else -> WeakReference<Context?>(BaseApplicationHolder.getApp())
}


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

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
fun getActivityByContext(context: Context? = BaseApplicationHolder.getApp()): Activity? {
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
fun getLauncherActivityName(packageName: String?, context: Context? = BaseApplicationHolder.getApp()): String {
    packageName ?: return ""
    context ?: return ""
    if (isSpace(packageName)) return ""

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
    logD(TAG, "no $packageName")
    return ""
}

/**
 * Return the icon of activity.
 *
 * @param clazz   The activity class.
 * @param context The context.
 * @return the icon of activity
 */
fun getActivityIcon(clazz: Class<out Activity?>?, context: Context? = BaseApplicationHolder.getApp()): Drawable? {
    clazz ?: return null
    context ?: return null

    return getActivityIcon(ComponentName(context, clazz), context)
}

inline fun <reified A : Activity> getActivityIcon(context: Context? = BaseApplicationHolder.getApp()): Drawable? {
    return getActivityIcon(A::class.java, context)
}

/**
 * Return the icon of activity.
 *
 * @param activity The activity.
 * @param context  The context.
 * @return the icon of activity
 */
fun getActivityIcon(activity: Activity?, context: Context? = BaseApplicationHolder.getApp()): Drawable? {
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
fun getActivityIcon(activityName: ComponentName?, context: Context? = BaseApplicationHolder.getApp()): Drawable? {
    activityName ?: return null
    context ?: return null

    val packageManager: PackageManager = context.packageManager
    return try {
        packageManager.getActivityIcon(activityName)
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        null
    }
}

/**
 * Return the logo of activity.
 *
 * @param clazz   The activity class.
 * @param context The context.
 * @return the logo of activity
 */
fun getActivityLogo(clazz: Class<out Activity?>?, context: Context? = BaseApplicationHolder.getApp()): Drawable? {
    clazz ?: return null
    context ?: return null

    return getActivityLogo(ComponentName(context, clazz), context)
}

inline fun <reified A : Activity> getActivityLogo(context: Context? = BaseApplicationHolder.getApp()): Drawable? {
    return getActivityLogo(A::class.java, context)
}

/**
 * Return the logo of activity.
 *
 * @param activity The activity.
 * @param context  The context.
 * @return the logo of activity
 */
fun getActivityLogo(activity: Activity?, context: Context? = BaseApplicationHolder.getApp()): Drawable? {
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
fun getActivityLogo(activityName: ComponentName?, context: Context? = BaseApplicationHolder.getApp()): Drawable? {
    activityName ?: return null
    context ?: return null

    val packageManager = context.packageManager
    return try {
        packageManager.getActivityLogo(activityName)
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        null
    }
}

/** ******************** Checker ******************** */

/**
 * Return whether the activity exists.
 *
 * @param packageName The name of the package.
 * @param cls         The name of the class.
 * @param context     The context.
 * @return `true`: yes<br></br>`false`: no
 */
fun isActivityExists(packageName: String?, cls: String?, context: Context? = BaseApplicationHolder.getApp()): Boolean {
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
 * @param clazz The activity class.
 * @return `true`: yes<br></br>`false`: no
 */
fun isActivityExistsInStack(clazz: Class<out Activity?>?): Boolean {
    clazz ?: return false

    val activities: List<Activity> = activityList
    for (aActivity in activities) {
        if (aActivity.javaClass == clazz) {
            return true
        }
    }
    return false
}

/**  */
inline fun <reified A : Activity> isActivityExistsInStack(): Boolean {
    return isActivityExistsInStack(A::class.java)
}

/**
 * Return whether the activity is destroy.
 *
 * @param activity The activity.
 * @return `true`: yes<br></br>`false`: no
 */
fun isActivityDestroy(activity: Activity?): Boolean {
    activity ?: return true

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

/** ******************** Operator ******************** */

/** ********** startActivityExt by options ********** */

/**
 * Start the activity.
 *
 * @param context The context.
 * @param uri     The activity uri.
 * @return `true`: success<br></br>`false`: fail
 */
fun startActivityExt(context: Any? = topActivityOrApp.get(), uri: Any?): Boolean {
    context ?: return false
    uri ?: return false

    val intent = when (uri) {
        is Uri -> Intent(Intent.ACTION_VIEW, uri)
        is String -> Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        else -> return false
    }

    return when (context) {
        is Context -> {
            val activities: List<ResolveInfo> = context.packageManager.queryIntentActivities(intent, 0)
            if (activities.isNotEmpty()) {
                startActivityExt(context, intent)
                true
            }
            else {
                Toast.makeText(context, "打开第三方APP失败", Toast.LENGTH_SHORT).show()
                false
            }
        }
        is Activity -> {
            val activities: List<ResolveInfo> = context.packageManager.queryIntentActivities(intent, 0)
            if (activities.isNotEmpty()) {
                startActivityExt(context, intent)
                true
            }
            else {
                Toast.makeText(context, "打开第三方APP失败", Toast.LENGTH_SHORT).show()
                false
            }
        }
        else -> false
    }
}

/**
 * Start the activity.
 *
 * @param context The context.
 * @param clazz   The activity class.
 * @param extras  The Bundle of extras to add to this intent.
 * @param options Additional options for how the Activity should be started.
 * @return `true`: success<br></br>`false`: fail
 */
fun startActivityExt(context: Any? = topActivityOrApp.get(), clazz: Class<out Activity?>?, extras: Bundle? = null, options: Bundle? = null): Boolean {
    context ?: return false
    clazz ?: return false

    return when (context) {
        is Context -> {
            startActivityExt(context, context.packageName, clazz.name, extras, options)
            true
        }
        is Activity -> {
            startActivityExt(context, context.packageName, clazz.name, extras, options)
            true
        }
        else -> false
    }
}

/**  */
inline fun <reified A : Activity> startActivityExt(context: Any? = topActivityOrApp.get(), extras: Bundle? = null, options: Bundle? = null): Boolean {
    return startActivityExt(context, A::class.java, extras, options)
}

/**
 * Start the activity.
 *
 * @param context     The context.
 * @param packageName The name of the package.
 * @param cls         The name of the class.
 * @param extras      The Bundle of extras to add to this intent.
 * @param options     Additional options for how the Activity should be started.
 * @return `true`: success<br></br>`false`: fail
 */
fun startActivityExt(context: Any? = topActivityOrApp.get(), packageName: String?, cls: String?, extras: Bundle? = null, options: Bundle? = null): Boolean {
    context ?: return false
    packageName ?: return false
    cls ?: return false

    return when (context) {
        is Context, is Activity -> {
            val intent = Intent(Intent.ACTION_VIEW)
            if (extras != null) {
                intent.putExtras(extras)
            }
            intent.component = ComponentName(packageName, cls)
            startActivityExt(context, intent, options)
            true
        }
        else -> false
    }
}

/**
 * Start the activity.
 *
 * @param context The context.
 * @param intent  The description of the activity to start.
 * @param options Additional options for how the Activity should be started.
 * @return `true`: success<br></br>`false`: fail
 */
fun startActivityExt(context: Any? = topActivityOrApp.get(), intent: Intent?, options: Bundle? = null): Boolean {
    context ?: return false
    intent ?: return false

    when (context) {
        is Context -> {
            if (!isIntentAvailable(context, intent)) {
                logD(TAG, "intent is unavailable")
                return false
            }
            if (context !is Activity) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) context.startActivity(intent, options)
            else context.startActivity(intent)

            return true
        }
        is Activity -> {
            if (!isIntentAvailable(context, intent)) {
                logD(TAG, "intent is unavailable")
                return false
            }
            if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) context.startActivity(intent, options)
            else context.startActivity(intent)

            return true
        }
        else -> return false
    }
}

/** ********** startActivityExt by enterAnim, exitAnim ********** */

/**
 * Start the activity.
 *
 * @param context   The context.
 * @param clazz     The activity class.
 * @param extras    The Bundle of extras to add to this intent.
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 * @return `true`: success<br></br>`false`: fail
 */
fun startActivityExt(context: Any? = topActivityOrApp.get(), clazz: Class<out Activity?>?, extras: Bundle? = null, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int): Boolean {
    context ?: return false
    clazz ?: return false

    return when (context) {
        is Context -> {
            startActivityExt(context, context.packageName, clazz.name, extras, getOptionsBundle(context, enterAnim, exitAnim))
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && context is Activity) {
                context.overridePendingTransition(enterAnim, exitAnim)
            }
            true
        }
        is Activity -> {
            startActivityExt(context, context.packageName, clazz.name, extras, getOptionsBundle(context, enterAnim, exitAnim))
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                context.overridePendingTransition(enterAnim, exitAnim)
            }
            true
        }
        else -> false
    }
}

/**
 * Start the activity.
 *
 * @param context     The context.
 * @param packageName The name of the package.
 * @param cls         The name of the class.
 * @param extras      The Bundle of extras to add to this intent.
 * @param enterAnim   A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim    A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 * @return `true`: success<br></br>`false`: fail
 */
fun startActivityExt(context: Any? = topActivityOrApp.get(), packageName: String?, cls: String?, extras: Bundle? = null, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int): Boolean {
    context ?: return false
    packageName ?: return false
    cls ?: return false

    return when (context) {
        is Context -> {
            startActivityExt(context, packageName, cls, extras, getOptionsBundle(context, enterAnim, exitAnim))
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && context is Activity) {
                context.overridePendingTransition(enterAnim, exitAnim)
            }
            true
        }
        is Activity -> {
            startActivityExt(context, packageName, cls, extras, getOptionsBundle(context, enterAnim, exitAnim))
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                context.overridePendingTransition(enterAnim, exitAnim)
            }
            true
        }
        else -> false
    }
}

/**
 * Start the activity.
 *
 * @param context   The context.
 * @param intent    The description of the activity to start.
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 * @return `true`: success<br></br>`false`: fail
 */
fun startActivityExt(context: Any? = topActivityOrApp.get(), intent: Intent?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int): Boolean {
    context ?: return false
    intent ?: return false

    return when (context) {
        is Context -> {
            val isSuccess = startActivityExt(context, intent, getOptionsBundle(context, enterAnim, exitAnim))
            if (isSuccess && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN && context is Activity) {
                context.overridePendingTransition(enterAnim, exitAnim)
            }
            isSuccess
        }
        is Activity -> {
            val isSuccess = startActivityExt(context, intent, getOptionsBundle(context, enterAnim, exitAnim))
            if (isSuccess && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                context.overridePendingTransition(enterAnim, exitAnim)
            }
            isSuccess
        }
        else -> false
    }
}

/** ********** startActivityExt by sharedElements ********** */

/**  */
@JvmName("startActivityExt_")
inline fun <reified A : Activity> startActivityExt(activity: Activity?, extras: Bundle? = null, vararg sharedElements: View) =
    activity.startActivityExt<A>(extras, *sharedElements)

inline fun <reified A : Activity> Activity?.startActivityExt(extras: Bundle? = null, vararg sharedElements: View) =
    this.startActivityExt(A::class.java, extras, *sharedElements)

/**
 * Start the activity.
 *
 * @param activity       The activity.
 * @param clazz          The activity class.
 * @param extras         The Bundle of extras to add to this intent.
 * @param sharedElements The names of the shared elements to transfer to the called activity and their associated Views.
 */
@JvmName("startActivityExt_")
fun startActivityExt(activity: Activity?, clazz: Class<out Activity?>?, extras: Bundle? = null, vararg sharedElements: View) =
    activity.startActivityExt(clazz, extras, *sharedElements)

fun Activity?.startActivityExt(clazz: Class<out Activity?>?, extras: Bundle? = null, vararg sharedElements: View) {
    this ?: return
    clazz ?: return

    startActivityExt(this, this.packageName, clazz.name, extras, getOptionsBundle(this, sharedElements))
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
fun startActivityExt(activity: Activity?, packageName: String?, cls: String?, extras: Bundle? = null, vararg sharedElements: View) {
    activity ?: return
    packageName ?: return
    cls ?: return

    startActivityExt(activity, packageName, cls, extras, getOptionsBundle(activity, sharedElements))
}

/**
 * Start the activity.
 *
 * @param activity       The activity.
 * @param intent         The description of the activity to start.
 * @param sharedElements The names of the shared elements to transfer to the called activity and their associated Views.
 */
fun startActivityExt(activity: Activity?, intent: Intent?, vararg sharedElements: View) {
    activity ?: return
    intent ?: return

    startActivityExt(activity, intent, getOptionsBundle(activity, sharedElements))
}

/** ********** startActivityForResult by options ********** */

/**
 * Start the activity.
 *
 * @param activity    The activity.
 * @param clazz       The activity class.
 * @param requestCode if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
 * @param extras      The Bundle of extras to add to this intent.
 * @param options     Additional options for how the Activity should be started.
 */
fun startActivityForResult(activity: Activity?, clazz: Class<out Activity?>?, requestCode: Int, extras: Bundle? = null, options: Bundle? = null) {
    activity ?: return
    clazz ?: return

    startActivityForResult(activity, activity.packageName, clazz.name, requestCode, extras, options)
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
        logE(TAG, "intent is unavailable")
        return false
    }
    if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        activity.startActivityForResult(intent, requestCode, options)
    }
    else {
        activity.startActivityForResult(intent, requestCode)
    }
    return true
}

/** ********** startActivityForResult by enterAnim, exitAnim ********** */

/**
 * Start the activity.
 *
 * @param activity    The activity.
 * @param clazz       The activity class.
 * @param requestCode if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
 * @param extras      The Bundle of extras to add to this intent.
 * @param enterAnim   A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim    A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
fun startActivityForResult(activity: Activity?, clazz: Class<out Activity?>?, requestCode: Int, extras: Bundle? = null, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
    activity ?: return
    clazz ?: return

    startActivityForResult(activity, activity.packageName, clazz.name, requestCode, extras, getOptionsBundle(activity, enterAnim, exitAnim))
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
 * @param enterAnim   A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim    A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
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
 * @param enterAnim   A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim    A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
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
 * @param clazz          The activity class.
 * @param requestCode    if &gt;= 0, this code will be returned in onActivityResult() when the activity exits.
 * @param extras         The Bundle of extras to add to this intent.
 * @param sharedElements The names of the shared elements to transfer to the called activity and their associated Views.
 */
fun startActivityForResult(activity: Activity?, clazz: Class<out Activity?>?, requestCode: Int, extras: Bundle? = null, vararg sharedElements: View) {
    activity ?: return
    clazz ?: return

    startActivityForResult(activity, activity.packageName, clazz.name, requestCode, extras, getOptionsBundle(activity, sharedElements))
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
fun startActivities(context: Context? = topActivityOrApp.get(), intents: Array<Intent>?, options: Bundle? = null) {
    context ?: return
    intents ?: return

    if (context !is Activity) {
        for (aIntent in intents) {
            aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }
    if (options != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        context.startActivities(intents, options)
    }
    else {
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
    }
    else {
        activity.startActivities(intents)
    }
}

/** ********** startActivities by enterAnim, exitAnim ********** */

/**
 * Start activities.
 *
 * @param context   The context.
 * @param intents   The descriptions of the activities to start.
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
fun startActivities(context: Context? = topActivityOrApp.get(), intents: Array<Intent>?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
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
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
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
    startActivityExt(intent = homeIntent)
}

/** ********** finishActivity by isLoadAnim ********** */

/**
 * Finish the activity.
 *
 * @param clazz      The activity class.
 * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
 */
@JvmOverloads
fun finishActivity(clazz: Class<out Activity?>?, isLoadAnim: Boolean = false) {
    clazz ?: return

    val activities = activityList
    for (aActivity in activities) {
        if (aActivity.javaClass == clazz) {
            if (isActivityAlive(aActivity)) {
                aActivity.finish()
                if (!isLoadAnim) {
                    aActivity.overridePendingTransition(0, 0)
                }
            }
        }
    }
}

/**
 * Finish the activity.
 *
 * @param activity   The activity.
 * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
 */
@JvmOverloads
fun finishActivity(activity: Activity?, isLoadAnim: Boolean = false) {
    activity ?: return

    if (isActivityAlive(activity)) {
        activity.finish()
        if (!isLoadAnim) {
            activity.overridePendingTransition(0, 0)
        }
    }
}

/** ********** finishActivity by enterAnim, exitAnim ********** */

/**
 * Finish the activity.
 *
 * @param clazz     The activity class.
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
@JvmOverloads
fun finishActivity(clazz: Class<out Activity?>?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
    clazz ?: return

    val activities = activityList
    for (aActivity in activities) {
        if (aActivity.javaClass == clazz) {
            if (isActivityAlive(aActivity)) {
                aActivity.finish()
                aActivity.overridePendingTransition(enterAnim, exitAnim)
            }
        }
    }
}

/**
 * Finish the activity.
 *
 * @param activity  The activity.
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
@JvmOverloads
fun finishActivity(activity: Activity?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
    activity ?: return

    if (isActivityAlive(activity)) {
        activity.finish()
        activity.overridePendingTransition(enterAnim, exitAnim)
    }
}

/** ********** finishToActivity by isLoadAnim ********** */

/**
 * Finish to the activity.
 *
 * @param clazz         The activity class.
 * @param isIncludeSelf True to include the activity, false otherwise.
 * @param isLoadAnim    True to use animation for the outgoing activity, false otherwise.
 */
@JvmOverloads
fun finishToActivity(clazz: Class<out Activity?>?, isIncludeSelf: Boolean, isLoadAnim: Boolean = false): Boolean {
    clazz ?: return false

    val activities = activityList
    for (aActivity in activities.reversed()) {
        if (aActivity.javaClass == clazz) {
            if (isIncludeSelf) {
                finishActivity(aActivity, isLoadAnim)
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
 */
@JvmOverloads
fun finishToActivity(activity: Activity?, isIncludeSelf: Boolean, isLoadAnim: Boolean = false): Boolean {
    activity ?: return false

    val activities = activityList
    for (aActivity in activities.reversed()) {
        if (aActivity == activity) {
            if (isIncludeSelf) {
                finishActivity(aActivity, isLoadAnim)
            }
            return true
        }
        finishActivity(aActivity, isLoadAnim)
    }
    return false
}

/** ********** finishToActivity by enterAnim, exitAnim ********** */

/**
 * Finish to the activity.
 *
 * @param clazz         The activity class.
 * @param isIncludeSelf True to include the activity, false otherwise.
 * @param enterAnim     A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim      A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
@JvmOverloads
fun finishToActivity(clazz: Class<out Activity?>?, isIncludeSelf: Boolean, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int): Boolean {
    clazz ?: return false

    val activities = activityList
    for (aActivity in activities.reversed()) {
        if (aActivity.javaClass == clazz) {
            if (isIncludeSelf) {
                finishActivity(aActivity, enterAnim, exitAnim)
            }
            return true
        }
        finishActivity(aActivity, enterAnim, exitAnim)
    }
    return false
}

/**
 * Finish to the activity.
 *
 * @param activity      The activity.
 * @param isIncludeSelf True to include the activity, false otherwise.
 * @param enterAnim     A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim      A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
@JvmOverloads
fun finishToActivity(activity: Activity?, isIncludeSelf: Boolean, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int): Boolean {
    activity ?: return false

    val activities = activityList
    for (aActivity in activities.reversed()) {
        if (aActivity == activity) {
            if (isIncludeSelf) {
                finishActivity(aActivity, enterAnim, exitAnim)
            }
            return true
        }
        finishActivity(aActivity, enterAnim, exitAnim)
    }
    return false
}

/** ********** finishOtherActivities by isLoadAnim ********** */

/**
 * Finish the activities whose type not equals the activity class.
 *
 * @param clazz      The activity class.
 * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
 */
@JvmOverloads
fun finishOtherActivities(clazz: Class<out Activity?>?, isLoadAnim: Boolean = false) {
    clazz ?: return

    val activities = activityList
    for (aActivity in activities.reversed()) {
        if (aActivity.javaClass != clazz) {
            finishActivity(aActivity, isLoadAnim)
        }
    }
}

/**
 * Finish the activities whose type not equals the activity class.
 *
 * @param activity   The activity.
 * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
 */
@JvmOverloads
fun finishOtherActivities(activity: Activity?, isLoadAnim: Boolean = false) {
    activity ?: return

    val activities = activityList
    for (aActivity in activities.reversed()) {
        if (aActivity != activity) {
            finishActivity(aActivity, isLoadAnim)
        }
    }
}

/** ********** finishOtherActivities by enterAnim, exitAnim ********** */

/**
 * Finish the activities whose type not equals the activity class.
 *
 * @param clazz     The activity class.
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
@JvmOverloads
fun finishOtherActivities(clazz: Class<out Activity?>?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
    clazz ?: return

    val activities = activityList
    for (aActivity in activities.reversed()) {
        if (aActivity.javaClass != clazz) {
            finishActivity(aActivity, enterAnim, exitAnim)
        }
    }
}

/**
 * Finish the activities whose type not equals the activity class.
 *
 * @param activity  The activity.
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
@JvmOverloads
fun finishOtherActivities(activity: Activity?, @AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
    activity ?: return

    val activities = activityList
    for (aActivity in activities.reversed()) {
        if (aActivity != activity) {
            finishActivity(aActivity, enterAnim, exitAnim)
        }
    }
}

/** ********** finishTopActivity by isLoadAnim ********** */

/**
 * Finish the top activity.
 *
 * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
 */
fun finishTopActivity(isLoadAnim: Boolean = false) {
    topActivity ?: return

    finishActivity(topActivity.get(), isLoadAnim)
}

/** ********** finishTopActivity by enterAnim, exitAnim ********** */

/**
 * Finish the top activity.
 *
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
fun finishTopActivity(@AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
    topActivity ?: return

    finishActivity(topActivity.get(), enterAnim, exitAnim)
}

/** ********** finishAllActivities by isLoadAnim ********** */

/**
 * Finish all of activities.
 *
 * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
 */
@JvmOverloads
fun finishAllActivities(isLoadAnim: Boolean = false) {
    val activityList = activityList
    for (aActivity in activityList.reversed()) { // remove from top
        // activityList remove the index activity at onActivityDestroyed
        finishActivity(aActivity, isLoadAnim)
    }
}

/** ********** finishAllActivities by enterAnim, exitAnim ********** */

/**
 * Finish all of activities.
 *
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
@JvmOverloads
fun finishAllActivities(@AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
    val activityList = activityList
    for (aActivity in activityList.reversed()) { // remove from top
        // activityList remove the index activity at onActivityDestroyed
        finishActivity(aActivity, enterAnim, exitAnim)
    }
    LifecycleHolder.activityLifecycle.activityHolder.activityList.clear()
}

/** ********** finishAllActivitiesExceptNewest by isLoadAnim ********** */

/**
 * Finish all of activities except the newest activity.
 *
 * @param isLoadAnim True to use animation for the outgoing activity, false otherwise.
 */
@JvmOverloads
fun finishAllActivitiesExceptNewest(isLoadAnim: Boolean = false) {
    val activities = activityList
    for (i in activities.size - 2 downTo 0) {
        finishActivity(activities[i], isLoadAnim)
    }
}

/** ********** finishAllActivitiesExceptNewest by enterAnim, exitAnim ********** */

/**
 * Finish all of activities except the newest activity.
 *
 * @param enterAnim A resource ID of the animation resource to use for the incoming activity. Use 0 for no animation.
 * @param exitAnim  A resource ID of the animation resource to use for the outgoing activity. Use 0 for no animation.
 */
@JvmOverloads
fun finishAllActivitiesExceptNewest(@AnimRes enterAnim: Int, @AnimRes exitAnim: Int) {
    val activities = activityList
    for (i in activities.size - 2 downTo 0) {
        finishActivity(activities[i], enterAnim, exitAnim)
    }
}


/** ****************************** Private ****************************** */

private fun isIntentAvailable(context: Context? = BaseApplicationHolder.getApp(), intent: Intent?): Boolean {
    context ?: return false
    intent ?: return false

    return context.packageManager?.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)?.size ?: -1 > 0
}

private fun getOptionsBundle(context: Context? = BaseApplicationHolder.getApp(), enterAnim: Int, exitAnim: Int): Bundle? {
    context ?: return null

    return ActivityOptionsCompat.makeCustomAnimation(context, enterAnim, exitAnim)
        .toBundle()
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
    return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, *pairs)
        .toBundle()
}
