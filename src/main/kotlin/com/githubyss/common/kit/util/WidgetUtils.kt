package com.githubyss.common.kit.util

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.widget.RemoteViews
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes


/**
 * WidgetUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/29 19:34:46
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "WidgetUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/** ********** RemoteViews ********** */

/**
 * Get RemoteViews from given layoutId.
 *
 * @param context
 * @param layoutId
 * @return
 */
@JvmName("getRemoteViews1")
fun getRemoteViews(context: Context?, @LayoutRes layoutId: Int) = RemoteViews(context?.packageName, layoutId)
fun Context?.getRemoteViews(@LayoutRes layoutId: Int) = getRemoteViews(this, layoutId)

/** ********** AppWidgetIds ********** */

/**
 * Get AppWidgetIds from given context.
 *
 * @param context
 * @return
 */
@JvmName("getAppWidgetIds1")
inline fun <reified W : AppWidgetProvider> getAppWidgetIds(context: Context?) = getAppWidgetIds(context, W::class.java)
inline fun <reified W : AppWidgetProvider> Context?.getAppWidgetIds() = getAppWidgetIds<W>(this)

@JvmName("getAppWidgetIds1")
fun getAppWidgetIds(context: Context?, clazz: Class<*>) = if (context == null) null else getAppWidgetIds(AppWidgetManager.getInstance(context), ComponentName(context, clazz))
fun Context?.getAppWidgetIds(clazz: Class<*>) = getAppWidgetIds(this, clazz)

/**
 * Get AppWidgetIds from given component name.
 *
 * @param context
 * @param appWidgetComponentName
 * @return
 */
@JvmName("getAppWidgetIds1")
fun getAppWidgetIds(context: Context?, appWidgetComponentName: ComponentName?) = getAppWidgetIds(AppWidgetManager.getInstance(context), appWidgetComponentName)
fun Context?.getAppWidgetIds(appWidgetComponentName: ComponentName?) = getAppWidgetIds(this, appWidgetComponentName)

/**
 * Get AppWidgetIds from given component name.
 *
 * @param appWidgetManager
 * @param appWidgetComponentName
 * @return
 */
fun getAppWidgetIds(appWidgetManager: AppWidgetManager?, appWidgetComponentName: ComponentName?) = appWidgetManager?.getAppWidgetIds(appWidgetComponentName)


/** ******************** Refresh ******************** */

/** ********** Refresh view in widget by updateAppWidget ********** */

/**
 * Refresh RemoteViews in Widget with AppWidgetIds build by given Widget Class.
 *
 * @param context
 * @param remoteViews
 * @return
 */
@JvmName("refreshAppWidgetViewByIds1")
inline fun <reified W : AppWidgetProvider> refreshAppWidgetViewByIds(context: Context?, remoteViews: RemoteViews?) = context?.let {
    refreshAppWidgetView(context, getAppWidgetIds<W>(context) ?: return@let, remoteViews)
}

inline fun <reified W : AppWidgetProvider> Context?.refreshAppWidgetViewByIds(remoteViews: RemoteViews?) = refreshAppWidgetViewByIds<W>(this, remoteViews)

/**
 * Refresh RemoteViews in Widget with ComponentName build by given Widget Class.
 *
 * @param context
 * @param remoteViews
 * @return
 */
@JvmName("refreshAppWidgetViewByComponentName1")
inline fun <reified W : AppWidgetProvider> refreshAppWidgetViewByComponentName(context: Context?, remoteViews: RemoteViews?) = context?.let {
    refreshAppWidgetView(context, ComponentName(context, W::class.java), remoteViews)
}

inline fun <reified W : AppWidgetProvider> Context?.refreshAppWidgetViewByComponentName(remoteViews: RemoteViews?) = refreshAppWidgetViewByComponentName<W>(this, remoteViews)


/**
 * Refresh RemoteViews in Widget with given WidgetId.
 *
 * @param context
 * @param appWidgetId
 * @param remoteViews
 * @return
 */
@JvmName("refreshAppWidgetView1")
fun refreshAppWidgetView(context: Context?, appWidgetId: Int, remoteViews: RemoteViews?) = AppWidgetManager.getInstance(context).updateAppWidget(appWidgetId, remoteViews)
fun Context?.refreshAppWidgetView(appWidgetId: Int, remoteViews: RemoteViews?) = refreshAppWidgetView(this, appWidgetId, remoteViews)

/**
 * Refresh RemoteViews in Widget with given WidgetIds.
 *
 * @param context
 * @param appWidgetIds
 * @param remoteViews
 * @return
 */
@JvmName("refreshAppWidgetView1")
fun refreshAppWidgetView(context: Context?, appWidgetIds: IntArray?, remoteViews: RemoteViews?) = AppWidgetManager.getInstance(context).updateAppWidget(appWidgetIds, remoteViews)
fun Context?.refreshAppWidgetView(appWidgetIds: IntArray?, remoteViews: RemoteViews?) = refreshAppWidgetView(this, appWidgetIds, remoteViews)

/**
 * Refresh RemoteViews in Widget with given ComponentName.
 *
 * @param
 * @return
 */
@JvmName("refreshAppWidgetView1")
fun refreshAppWidgetView(context: Context?, appWidgetComponentName: ComponentName?, remoteViews: RemoteViews?) = AppWidgetManager.getInstance(context).updateAppWidget(appWidgetComponentName, remoteViews)
fun Context?.refreshAppWidgetView(appWidgetComponentName: ComponentName?, remoteViews: RemoteViews?) = refreshAppWidgetView(this, appWidgetComponentName, remoteViews)


/** ********** Refresh list in widget by notifyAppWidgetViewDataChanged ********** */

/**
 * 刷新 RemoteViewsService.RemoteViewsFactory 的视图
 * Refresh RemoteViews ListView in Widget with given listResId.
 *
 * @param context
 * @param listResId
 * @return
 */
@JvmName("refreshAppWidgetList1")
inline fun <reified W : AppWidgetProvider> refreshAppWidgetList(context: Context?, @IdRes listResId: Int) = refreshAppWidgetList(context, W::class.java, listResId)
inline fun <reified W : AppWidgetProvider> Context?.refreshAppWidgetList(@IdRes redId: Int) = refreshAppWidgetList<W>(this, redId)

@JvmName("refreshAppWidgetList1")
fun refreshAppWidgetList(context: Context?, clazz: Class<*>, @IdRes listResId: Int) = context?.let {
    val appWidgetManager = AppWidgetManager.getInstance(context)
    val appWidgetComponentName = ComponentName(context, clazz)

    // val appWidgetIds = getAppWidgetIds(appWidgetManager, appWidgetComponentName)
    val appWidgetIds = getAppWidgetIds(context, clazz)

    // 这句话会调用 RemoteViewsService 中 RemoteViewsFactory 的 onDataSetChanged() 方法
    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, listResId)
}

fun Context?.refreshAppWidgetList(clazz: Class<*>, @IdRes redId: Int) = refreshAppWidgetList(this, clazz, redId)
