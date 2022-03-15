package com.githubyss.mobile.common.kit.util

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityService.GestureResultCallback
import android.accessibilityservice.AccessibilityServiceInfo
import android.accessibilityservice.GestureDescription
import android.accessibilityservice.GestureDescription.StrokeDescription
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.graphics.Path
import android.graphics.Point
import android.os.Build
import android.view.ViewConfiguration
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.enumeration.NodeTapState
import com.githubyss.mobile.common.kit.enumeration.VersionCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


/**
 * AccessibilityUtils
 * 无障碍辅助封装工具类
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/11/09 14:51:51
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "AccessibilityUtils"

private val TAP_TIMEOUT: Int = ViewConfiguration.getTapTimeout()
private val LONG_PRESS_TIMEOUT: Int = ViewConfiguration.getLongPressTimeout()
private val DOUBLE_TAP_TIMEOUT: Int = ViewConfiguration.getDoubleTapTimeout()

private const val DEFAULT_TAP_DURATION: Long = 100
private const val DEFAULT_LONG_TAP_DURATION: Long = 500
private const val DEFAULT_DOUBLE_TAP_DURATION: Long = 150


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/**
 * 获取根节点信息
 *
 * @param service 无障碍辅助服务
 * @param event 无障碍辅助事件
 * @return
 */
fun getRootNodeInfo(service: AccessibilityService? = null, event: AccessibilityEvent? = null): AccessibilityNodeInfo? {
    // logStart("getRootNodeInfo")
    var rootNodeInfo: AccessibilityNodeInfo? = null
    try {
        // logMiddle("service?.rootInActiveWindow: ${service?.rootInActiveWindow}")
        // logMiddle("event?.source: ${event?.source}")
        // logMiddle("service?.windows: ${service?.windows}")
        rootNodeInfo = if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN) {
            service?.rootInActiveWindow
        }
        else {
            // 最好不用，这个 source 不准
            event?.source
            null
        }
    }
    catch (e: Exception) {
        logE(TAG, "e: $e")
    }

    // logMiddle("rootNodeInfo: ${if (rootNodeInfo == null) "空" else "不空"}")
    // logEnd("getRootNodeInfo")
    return rootNodeInfo
}

/**
 * 根据文案获取节点列表
 *
 * @param text 待查找文案
 * @param service 无障碍辅助服务
 * @param event 无障碍辅助事件
 * @param rootNodeInfo 根节点信息
 * @return
 */
fun findNodeInfosByText(text: String = "", service: AccessibilityService? = null, event: AccessibilityEvent? = null, rootNodeInfo: AccessibilityNodeInfo? = null): List<AccessibilityNodeInfo?> {
    // logStart("findNodeInfosByText")
    var rootNodeInfoCopy = rootNodeInfo
    // 传入的 rootNodeInfo 为 null，则根据 service 和 event 重新获取
    if (rootNodeInfoCopy == null) {
        rootNodeInfoCopy = getRootNodeInfo(service = service, event = event)
        // logMiddle("rootNodeInfo: ${if (rootNodeInfo == null) "空" else "不空"}")
    }
    var nodeInfoList = emptyList<AccessibilityNodeInfo?>()
    if (rootNodeInfoCopy != null) {
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR2) {
            // 需要在 xml 文件中声明权限 android:accessibilityFlags="flagReportViewIds"
            // 并且版本大于 4.3 才能获取到 view 的 ID
            // logMiddle("viewIdResourceName: ${rootNodeInfoCopy.viewIdResourceName}")
            nodeInfoList = rootNodeInfoCopy.findAccessibilityNodeInfosByText(text)
            // logMiddle("nodeInfoList: $nodeInfoList")
        }
    }
    else {
        logMiddle("nodeInfo is null")
    }

    // logEnd("findNodeInfosByText")
    return nodeInfoList
}

/**
 * 根据 Id 获取节点列表
 *
 * @param viewId 待查找视图 Id
 * @param service 无障碍辅助服务
 * @param event 无障碍辅助事件
 * @param rootNodeInfo 根节点信息
 * @return
 */
fun findNodeInfosById(viewId: String = "", service: AccessibilityService? = null, event: AccessibilityEvent? = null, rootNodeInfo: AccessibilityNodeInfo? = null): List<AccessibilityNodeInfo?> {
    // logStart("findNodeInfosById")
    var rootNodeInfoCopy = rootNodeInfo
    // 传入的 rootNodeInfo 为 null，则根据 service 和 event 重新获取
    if (rootNodeInfoCopy == null) {
        rootNodeInfoCopy = getRootNodeInfo(service = service, event = event)
        // logMiddle("rootNodeInfo: ${if (rootNodeInfo == null) "空" else "不空"}")
    }
    var nodeInfoList = emptyList<AccessibilityNodeInfo?>()
    if (rootNodeInfoCopy != null) {
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR2) {
            // 需要在 xml 文件中声明权限 android:accessibilityFlags="flagReportViewIds"
            // 并且版本大于 4.3 才能获取到 view 的 ID
            // logMiddle("viewIdResourceName: ${rootNodeInfoCopy.viewIdResourceName}")
            nodeInfoList = rootNodeInfoCopy.findAccessibilityNodeInfosByViewId(viewId)
            // logMiddle("nodeInfoList: $nodeInfoList")
        }
    }
    else {
        logMiddle("nodeInfo is null")
    }

    // logEnd("findNodeInfosById")
    return nodeInfoList
}


/** ******************** Checker ******************** */

/**
 * 判断是否开启某项无障碍辅助服务
 *
 * @param context 上下文
 * @param serviceName 无障碍辅助服务名
 * @return
 */
fun isStartAccessibilityService(serviceName: String = "", context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    logStart("isStartAccessibilityService")
    val am = context?.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    val serviceInfos = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC)
    var ret = false
    for (info in serviceInfos) {
        val id = info.id
        if (id.contains(serviceName)) {
            logMiddle("info.id: $id")
            ret = true
        }
    }
    logEnd("isStartAccessibilityService")
    return ret
}

// fun isAccessibilitySettingsOn(mContext: Context, clazz: Class<out AccessibilityService?>): Boolean {
//     var accessibilityEnabled = 0
//     val service = mContext.packageName + "/" + clazz.canonicalName
//     try {
//         accessibilityEnabled = Settings.Secure.getInt(mContext.applicationContext.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED)
//     } catch (e: SettingNotFoundException) {
//         logE(TAG, t = e)
//     }
//     val mStringColonSplitter = SimpleStringSplitter(':')
//     if (accessibilityEnabled == 1) {
//         val settingValue = Settings.Secure.getString(mContext.applicationContext.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
//         if (settingValue != null) {
//             mStringColonSplitter.setString(settingValue)
//             while (mStringColonSplitter.hasNext()) {
//                 val accessibilityService = mStringColonSplitter.next()
//                 if (accessibilityService.equals(service, ignoreCase = true)) {
//                     return true
//                 }
//             }
//         }
//     }
//     return false
// }


/** ******************** Operator ******************** */

/** ********** Print ********** */

/**
 * 打印事件日志
 *
 * @param event 无障碍辅助事件
 * @return
 */
fun printEventLog(event: AccessibilityEvent? = null) {
    logStart("printEventLog")
    event?.let {
        val eventType = event.eventType // 事件类型
        // 响应事件的包名 | 事件源的类名 | 事件源描述
        logMiddle("Package Name (包名): ${event.packageName} | Source Class (类名): ${event.className} | Description (描述): ${event.contentDescription}")
        val eventTypeString = when (eventType) {
            AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED -> "TYPE_NOTIFICATION_STATE_CHANGED (通知栏事件)" // 通知栏事件
            AccessibilityEvent.TYPE_VIEW_CLICKED -> "TYPE_VIEW_CLICKED (点击事件)" // 点击事件
            AccessibilityEvent.TYPE_VIEW_FOCUSED -> "TYPE_VIEW_FOCUSED (获取焦点事件)" // 获取焦点事件
            AccessibilityEvent.TYPE_VIEW_LONG_CLICKED -> "TYPE_VIEW_LONG_CLICKED (长按事件)" // 长按事件
            AccessibilityEvent.TYPE_VIEW_SCROLLED -> "TYPE_VIEW_SCROLLED (页面滚屏事件)" // 页面滚屏事件
            AccessibilityEvent.TYPE_VIEW_SELECTED -> "TYPE_VIEW_SELECTED (控件选中)" // 控件选中
            AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED -> "TYPE_VIEW_TEXT_CHANGED (输入框文本改变)" // 输入框文本改变
            AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED -> "TYPE_VIEW_TEXT_SELECTION_CHANGED (输入框文本 selection 改变)" // 输入框文本 selection 改变
            AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED -> "TYPE_VIEW_ACCESSIBILITY_FOCUSED" //
            AccessibilityEvent.TYPE_GESTURE_DETECTION_START -> "TYPE_GESTURE_DETECTION_START" //
            AccessibilityEvent.TYPE_GESTURE_DETECTION_END -> "TYPE_GESTURE_DETECTION_END" //
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> "TYPE_WINDOW_CONTENT_CHANGED (窗口内容改变)" // 窗口内容改变
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> "TYPE_WINDOW_STATE_CHANGED (窗口状态改变)" // 窗口状态改变
            else -> "No listen event"
        }
        // 事件类型
        logMiddle("Event Type (类型)(int): $eventType | Event Type (类型)(String): $eventTypeString")
        logMiddle("Event Texts: ${event.text}")
        // for (txt in event.text) {
        //     logD(TAG, "Event text: $txt")
        // }
    }
    logEnd("printEventLog")
}

/** ********** Open App ********** */

/**
 * 监听通知栏，打开应用
 *
 * @param event 无障碍辅助事件
 * @return
 */
fun openAppByNotification(event: AccessibilityEvent? = null) {
    logStart("openAppByNotification")

    event?.let {
        if (event.parcelableData != null && event.parcelableData is Notification) {
            val notification: Notification = event.parcelableData as Notification
            try {
                val pendingIntent: PendingIntent = notification.contentIntent
                pendingIntent.send()
            }
            catch (e: PendingIntent.CanceledException) {
                logE(TAG, t = e)
            }
        }
    }

    logEnd("openAppByNotification")
}

/** ********** Click ********** */

/**
 * 点击可点击节点
 * 点击指定的节点，通过入参决定是否需要判断节点的可点击性
 *
 * @param tapNodeInfo 待点击节点信息
 * @param isTapForcibly 是否强制点击
 * @param onTap 点击回调
 * @return
 */
fun tapClickableSelf(tapNodeInfo: AccessibilityNodeInfo? = null, isTapForcibly: Boolean = false, onTap: ((tapState: String) -> Unit)? = null) {
    logStart("tapClickableSelf")
    // logMiddle("tapNodeInfo: $tapNodeInfo")

    val nodeTapState: String = if (tapNodeInfo == null) {
        NodeTapState.NULL
    }
    else {
        if (isTapForcibly || tapNodeInfo.isClickable) {
            tapNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            logMiddle("操作点击")

            NodeTapState.CLICKED
        }
        else {
            NodeTapState.UNCLICKABLE
        }
    }
    logMiddle("节点点击状态『$nodeTapState』")

    // 回调点击接口，传回节点点击状态
    onTap?.let { it(nodeTapState) }

    logEnd("tapClickableSelf")
}

/**
 * 点击可点击节点
 * 递归节点本身及其父节点，一层一层判断可点击性，点击最近一个可点击的节点
 *
 * @param tapNodeInfo 待点击节点信息
 * @param onTap 点击回调
 * @return
 */
fun tapClickableParent(tapNodeInfo: AccessibilityNodeInfo? = null, onTap: ((tapState: String) -> Unit)? = null) {
    logStart("tapClickableParent")
    // logMiddle("tapNodeInfo: $tapNodeInfo")

    // 默认节点点击状态-不可点击
    var nodeTapState: String = NodeTapState.UNCLICKABLE

    if (tapNodeInfo == null) {
        nodeTapState = NodeTapState.NULL
    }
    // 节点不为空，则递归父节点，尝试点击
    else {
        var parentNodeInfo = tapNodeInfo
        // 递归父节点寻找可点击节点
        while (parentNodeInfo != null) {
            // 找到可点击节点，进行点击
            if (parentNodeInfo.isClickable) {
                parentNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                logMiddle("操作点击")

                nodeTapState = NodeTapState.CLICKED
                break
            }
            parentNodeInfo = parentNodeInfo.parent
        }
    }
    logMiddle("节点点击状态『$nodeTapState』")

    // 回调点击接口，传回节点点击状态
    onTap?.let { it(nodeTapState) }

    logEnd("tapClickableParent")
}

/**
 * 按一次
 * 默认短按一次
 * tap(service, Point(0, 400), 100L)
 *
 * @param service 无障碍辅助服务
 * @param point 坐标点
 * @param duration 持续时长
 * @return
 */
fun tap(service: AccessibilityService?, point: Point, duration: Long = DEFAULT_TAP_DURATION) {
    logStart("tap")
    if (Build.VERSION.SDK_INT >= VersionCode.N) {
        val path = Path()
        path.moveTo(point.x.toFloat(), point.y.toFloat())
        val strokeDesc = StrokeDescription(path, 0, duration)
        val gestureDesc = GestureDescription.Builder()
            .addStroke(strokeDesc)
            .build()

        when {
            duration <= TAP_TIMEOUT -> logMiddle("短按一次")
            duration >= LONG_PRESS_TIMEOUT -> logMiddle("长按一次")
        }

        service?.dispatchGesture(gestureDesc, null, null)
    }
    logEnd("tap")
}

/**
 * 长按一次
 * longTap(service, Point(0, 400), 500L)
 *
 * @param service 无障碍辅助服务
 * @param point 坐标点
 * @param duration 持续时长
 * @return
 */
fun longTap(service: AccessibilityService?, point: Point, duration: Long = DEFAULT_LONG_TAP_DURATION) {
    tap(service, point, duration)
}

/**
 * 短按两次
 * longTap(service, Point(0, 400), 100L, 150L)
 *
 * @param service 无障碍辅助服务
 * @param point 坐标点
 * @param duration 持续时长
 * @return
 */
fun doubleTap(service: AccessibilityService?, point: Point, duration: Long = DEFAULT_TAP_DURATION, gap: Long = DEFAULT_DOUBLE_TAP_DURATION) {
    logStart("doubleTap")
    if (Build.VERSION.SDK_INT >= VersionCode.N) {
        val path = Path()
        path.moveTo(point.x.toFloat(), point.y.toFloat())
        val strokeDesc = StrokeDescription(path, 0, duration)
        val gestureDesc = GestureDescription.Builder()
            .addStroke(strokeDesc)
            .build()

        logMiddle("短按第一次")
        service?.dispatchGesture(gestureDesc, object : GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription?) {
                super.onCompleted(gestureDescription)
                logMiddle("短按第一次完成")
                runBlocking {
                    // 延时 gap
                    delay(gap)
                    logMiddle("短按第二次")
                    service.dispatchGesture(gestureDesc, null, null)
                }
            }

            override fun onCancelled(gestureDescription: GestureDescription?) {
                logMiddle("短按第一次被取消")
                super.onCancelled(gestureDescription)
            }
        }, null)
    }
}

/** ********** Back ********** */

/**
 * 返回一次
 *
 * @param service 无障碍辅助服务
 * @param delay 延迟时长
 * @return
 */
fun backOnce(service: AccessibilityService?, delay: Long = 0) {
    logStart("backOnce")
    logMiddle("返回一次")
    runBlocking {
        delay(delay)
        service?.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
        logMiddle("延迟 ${delay}ms > GLOBAL_ACTION_BACK (执行返回一次)")
    }
    logEnd("backOnce")
}

/** ********** Home Screen ********** */

/**
 * 进入桌面
 *
 * @param service 无障碍辅助服务
 * @param delay 延迟时长
 * @return
 */
fun goHomeScreen(service: AccessibilityService?, delay: Long = 0) {
    logStart("goHomeScreen")
    logMiddle("进入桌面")
    runBlocking {
        delay(delay)
        service?.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME)
        logMiddle("延迟 ${delay}ms > GLOBAL_ACTION_HOME (执行进入桌面)")
    }
    logEnd("goHomeScreen")
}

/** ********** Slide ********** */

/**
 * 滑动一次
 * slideOnce(service, Point(0, 400), Point(400, 400), 0L, 500L)
 *
 * @param service 无障碍辅助服务
 * @param pointFrom 起点坐标
 * @param pointTo 终点坐标
 * @param startTime 启动延迟
 * @param duration 持续时长
 * @return
 */
fun slideOnce(service: AccessibilityService?, pointFrom: Point, pointTo: Point, startTime: Long = 0, duration: Long = 1000) {
    logStart("slideOnce")
    if (Build.VERSION.SDK_INT >= VersionCode.N) {
        // 线性的 path 代表手势路径，点代表按下，封闭的没用
        val path = Path()
        path.moveTo(pointFrom.x.toFloat(), pointFrom.y.toFloat())
        path.lineTo(pointTo.x.toFloat(), pointTo.y.toFloat())
        val strokeDesc = StrokeDescription(path, startTime, duration)
        val gestureDesc = GestureDescription.Builder()
            .addStroke(strokeDesc)
            .build()

        logMiddle("手势滑动一次")
        service?.dispatchGesture(gestureDesc, null, null)
    }
    logEnd("slideOnce")
}

/**
 * 滑动两次
 * slideTwice(service, Point(0, 400), Point(400, 400), 0L, 500L, Point(600, 600), Point(600, 800), 1000L, 500L)
 *
 * @param service 无障碍辅助服务
 * @param pointFrom1 起点坐标1
 * @param pointTo1 终点坐标1
 * @param startTime1 启动延迟1
 * @param duration1 持续时长1
 * @param pointFrom2 起点坐标2
 * @param pointTo2 终点坐标2
 * @param startTime2 启动延迟2
 * @param duration2 持续时长2
 *
 * @return
 */
fun slideTwice(service: AccessibilityService?, pointFrom1: Point, pointTo1: Point, startTime1: Long, duration1: Long, pointFrom2: Point, pointTo2: Point, startTime2: Long, duration2: Long) {
    logStart("slideTwice")
    if (Build.VERSION.SDK_INT >= VersionCode.N) {
        val path1 = Path()
        path1.moveTo(pointFrom1.x.toFloat(), pointFrom1.y.toFloat())
        path1.lineTo(pointTo1.x.toFloat(), pointTo1.y.toFloat())
        val strokeDesc1 = StrokeDescription(path1, startTime1, duration1)
        val gestureDesc1 = GestureDescription.Builder()
            .addStroke(strokeDesc1)
            .build()

        // 第一个手势动作
        logMiddle("手势滑动第一次")
        service?.dispatchGesture(gestureDesc1, object : GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription) {
                super.onCompleted(gestureDescription)
                logMiddle("手势滑动第一次完成")

                // 也可以使用 handler 延时 1.5 秒就不用在这里回调了
                val path2 = Path()
                path2.moveTo(pointFrom2.x.toFloat(), pointFrom2.y.toFloat())
                path2.lineTo(pointTo2.x.toFloat(), pointTo2.y.toFloat())
                val strokeDesc2 = StrokeDescription(path2, startTime2, duration2)
                val gestureDesc2 = GestureDescription.Builder()
                    .addStroke(strokeDesc2)
                    .build()

                // 第一个手势动作后，再过 startTime2 秒，进行第二个动作
                logMiddle("手势滑动第二次")
                service.dispatchGesture(gestureDesc2, null, null)
            }

            override fun onCancelled(gestureDescription: GestureDescription) {
                logMiddle("手势滑动第一次被取消")
                super.onCancelled(gestureDescription)
            }
        }, null)
    }
    logEnd("slideTwice")
}