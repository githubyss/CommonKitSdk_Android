package com.githubyss.mobile.common.kit.util

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.widget.RemoteViews
import androidx.annotation.IdRes


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

/**
 * Get AppWidgetIds from given context.
 *
 * @param
 * @return
 */
@JvmName("getAppWidgetIds_")
inline fun <reified W : AppWidgetProvider> getAppWidgetIds(context: Context?) = if (context == null) null else getAppWidgetIds(AppWidgetManager.getInstance(context), ComponentName(context, W::class.java))
inline fun <reified W : AppWidgetProvider> Context?.getAppWidgetIds() = getAppWidgetIds<W>(this)

/**
 *
 *
 * @param
 * @return
 */
fun getAppWidgetIds(appWidgetManager: AppWidgetManager?, appWidgetComponentName: ComponentName) = appWidgetManager?.getAppWidgetIds(appWidgetComponentName)


/** ******************** Checker ******************** */

/** ********** updateAppWidget ********** */

/**
 * 刷新 RemoteViews 的视图
 *
 * @param
 * @return
 */
@JvmName("refreshAppWidgetView_")
fun refreshAppWidgetView(context: Context?, appWidgetId: Int, remoteViews: RemoteViews) {
    val appWidgetManager = AppWidgetManager.getInstance(context)
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
}

fun Context?.refreshAppWidgetView(appWidgetId: Int, remoteViews: RemoteViews) = refreshAppWidgetView(this, appWidgetId, remoteViews)

/**
 *
 *
 * @param
 * @return
 */
@JvmName("refreshAppWidgetView_")
inline fun <reified W : AppWidgetProvider> refreshAppWidgetView(context: Context?, remoteViews: RemoteViews) {
    context ?: return

    val appWidgetManager = AppWidgetManager.getInstance(context)
    val appWidgetComponentName = ComponentName(context, W::class.java)

    appWidgetManager.updateAppWidget(appWidgetComponentName, remoteViews)
}

inline fun <reified W : AppWidgetProvider> Context?.refreshAppWidgetView(remoteViews: RemoteViews) = refreshAppWidgetView<W>(this, remoteViews)


/** ********** notifyAppWidgetViewDataChanged ********** */

/**
 * 刷新 RemoteViewsService.RemoteViewsFactory 的视图
 *
 * @param
 * @return
 */
@JvmName("refreshAppWidgetList_")
inline fun <reified W : AppWidgetProvider> refreshAppWidgetList(context: Context?, @IdRes listResId: Int) {
    context ?: return

    val appWidgetManager = AppWidgetManager.getInstance(context)
    val appWidgetComponentName = ComponentName(context, W::class.java)

    // val appWidgetIds = getAppWidgetIds(appWidgetManager, appWidgetComponentName)
    val appWidgetIds = getAppWidgetIds<W>(context)

    // 这句话会调用 RemoteViewsService 中 RemoteViewsFactory 的 onDataSetChanged() 方法
    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, listResId)
}

inline fun <reified W : AppWidgetProvider> Context?.refreshAppWidgetList(@IdRes redId: Int) = refreshAppWidgetList<W>(this, redId)