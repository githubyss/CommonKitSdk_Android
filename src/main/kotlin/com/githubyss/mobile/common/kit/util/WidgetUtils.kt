package com.githubyss.mobile.common.kit.util

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
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

/** 刷新 RemoteViewsService.RemoteViewsFactory 的视图 */
@JvmName("refreshWidget_")
inline fun <reified W : AppWidgetProvider> refreshWidget(context: Context?, @IdRes redId: Int) {
    context ?: return

    val widgetManager = AppWidgetManager.getInstance(context)
    val widgetComponentName = ComponentName(context, W::class.java)
    val appWidgetIds = widgetManager.getAppWidgetIds(widgetComponentName)

    // 这句话会调用 RemoteViewsService 中 RemoteViewsFactory 的 onDataSetChanged() 方法
    widgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, redId)
}

inline fun <reified W : AppWidgetProvider> Context?.refreshWidget(@IdRes redId: Int) = refreshWidget<W>(this, redId)
