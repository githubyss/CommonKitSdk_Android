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
import android.os.Build
import android.os.Handler
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.enumeration.NodeTapState
import com.githubyss.mobile.common.kit.enumeration.VersionCode


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


/** ****************************** Functions ****************************** */

/**
 * 打印事件日志
 *
 * @param event 无障碍辅助事件
 * @return
 */
fun printEventLog(event: AccessibilityEvent? = null) {
    logD(TAG, "┏ ━━━━━━━━━━ printEventLog >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    event?.let {
        val eventType = event.eventType // 事件类型
        // 响应事件的包名 | 事件源的类名 | 事件源描述
        logD(TAG, "┃ Package Name (包名): ${event.packageName} | Source Class (类名): ${event.className} | Description (描述): ${event.contentDescription}")
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
        logD(TAG, "┃ Event Type (类型)(int): $eventType | Event Type (类型)(String): $eventTypeString")
        logD(TAG, "┃ Event Texts: ${event.text}")
        // for (txt in event.text) {
        //     logD(TAG, "Event text: $txt")
        // }
    }
    logD(TAG, "┗ ━━━━━━━━━━ printEventLog >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
}

/**
 * 获取根节点信息
 *
 * @param service 无障碍辅助服务
 * @param event 无障碍辅助事件
 * @return
 */
fun getRootNodeInfo(service: AccessibilityService? = null, event: AccessibilityEvent? = null): AccessibilityNodeInfo? {
    // logD(TAG, "┏ ━━━━━━━━━━ getRootNodeInfo >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    var rootNodeInfo: AccessibilityNodeInfo? = null
    try {
        // logD(TAG, "┃ service?.rootInActiveWindow: ${service?.rootInActiveWindow}")
        // logD(TAG, "┃ event?.source: ${event?.source}")
        // logD(TAG, "┃ service?.windows: ${service?.windows}")
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

    // logD(TAG, "┃ rootNodeInfo: ${if (rootNodeInfo == null) "空" else "不空"}")
    // logD(TAG, "┗ ━━━━━━━━━━ getRootNodeInfo >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
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
    // logD(TAG, "┏ ━━━━━━━━━━ findNodeInfosByText >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    var rootNodeInfoCopy = rootNodeInfo
    // 传入的 rootNodeInfo 为 null，则根据 service 和 event 重新获取
    if (rootNodeInfoCopy == null) {
        rootNodeInfoCopy = getRootNodeInfo(service = service, event = event)
        // logD(TAG, "┃ rootNodeInfo: ${if (rootNodeInfo == null) "空" else "不空"}")
    }
    var nodeInfoList = emptyList<AccessibilityNodeInfo?>()
    if (rootNodeInfoCopy != null) {
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR2) {
            // 需要在 xml 文件中声明权限 android:accessibilityFlags="flagReportViewIds"
            // 并且版本大于 4.3 才能获取到 view 的 ID
            // logD(TAG, "┃ viewIdResourceName: ${rootNodeInfoCopy.viewIdResourceName}")
            nodeInfoList = rootNodeInfoCopy.findAccessibilityNodeInfosByText(text)
            // logD(TAG, "┃ nodeInfoList: $nodeInfoList")
        }
    }
    else {
        logD(TAG, "┃ nodeInfo is null")
    }

    // logD(TAG, "┗ ━━━━━━━━━━ findNodeInfosByText >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
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
    // logD(TAG, "┏ ━━━━━━━━━━ findNodeInfosById >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    var rootNodeInfoCopy = rootNodeInfo
    // 传入的 rootNodeInfo 为 null，则根据 service 和 event 重新获取
    if (rootNodeInfoCopy == null) {
        rootNodeInfoCopy = getRootNodeInfo(service = service, event = event)
        // logD(TAG, "┃ rootNodeInfo: ${if (rootNodeInfo == null) "空" else "不空"}")
    }
    var nodeInfoList = emptyList<AccessibilityNodeInfo?>()
    if (rootNodeInfoCopy != null) {
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR2) {
            // 需要在 xml 文件中声明权限 android:accessibilityFlags="flagReportViewIds"
            // 并且版本大于 4.3 才能获取到 view 的 ID
            // logD(TAG, "┃ viewIdResourceName: ${rootNodeInfoCopy.viewIdResourceName}")
            nodeInfoList = rootNodeInfoCopy.findAccessibilityNodeInfosByViewId(viewId)
            // logD(TAG, "┃ nodeInfoList: $nodeInfoList")
        }
    }
    else {
        logD(TAG, "┃ nodeInfo is null")
    }

    // logD(TAG, "┗ ━━━━━━━━━━ findNodeInfosById >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    return nodeInfoList
}

/**
 * 监听通知栏，打开应用
 *
 * @param event 无障碍辅助事件
 * @return
 */
fun openAppByNotification(event: AccessibilityEvent? = null) {
    logD(TAG, "┏ ━━━━━━━━━━ openAppByNotification >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")

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

    logD(TAG, "┗ ━━━━━━━━━━ openAppByNotification >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
}

/**
 * 立即返回一级
 *
 * @param service 无障碍辅助服务
 * @return
 */
fun backOnceImmediately(service: AccessibilityService?) {
    logD(TAG, "┏ ━━━━━━━━━━ backOnceImmediately >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    logD(TAG, "┃ 立即返回一级")
    logD(TAG, "┃ 立刻 > GLOBAL_ACTION_BACK (执行返回一级)")
    service?.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
    logD(TAG, "┗ ━━━━━━━━━━ backOnceImmediately >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
}

/**
 * 延迟返回一级
 *
 * @param service 无障碍辅助服务
 * @param delay 延迟时长
 * @return
 */
fun backOnceWithDelay(service: AccessibilityService?, delay: Long) {
    logD(TAG, "┏ ━━━━━━━━━━ backOnceWithDelay >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    logD(TAG, "┃ 延迟返回一级")
    Handler().postDelayed({
                              logD(TAG, "┃ 延迟 ${delay}ms 结束 > GLOBAL_ACTION_BACK (执行返回一级)")
                              service?.performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
                          }, delay)
    logD(TAG, "┗ ━━━━━━━━━━ backOnceWithDelay >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
}

/**
 * 延迟返回桌面
 *
 * @param service 无障碍辅助服务
 * @param delay 延迟时长
 * @return
 */
fun backHomeWithDelay(service: AccessibilityService?, delay: Long) {
    logD(TAG, "┏ ━━━━━━━━━━ backHomeWithDelay >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    logD(TAG, "┃ 延迟返回桌面")
    Handler().postDelayed({
                              logD(TAG, "┃ 延迟 ${delay}ms 结束 > GLOBAL_ACTION_HOME (执行返回桌面)")
                              service?.performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME)
                          }, delay)
    logD(TAG, "┗ ━━━━━━━━━━ backHomeWithDelay >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
}

/**
 * 手势滑动一次
 * slideHorizontalThanVertical(service, 0F, 400F, 400F, 400F, 0L, 500L)
 *
 * @param service 无障碍辅助服务
 * @param fromX 起点坐标 X
 * @param fromY 起点坐标 Y
 * @param toX 终点坐标 X
 * @param toY 终点坐标 Y
 * @param startTime 启动延迟
 * @param duration 持续时长
 * @return
 */
fun gestureSlideOnce(service: AccessibilityService?, fromX: Float, fromY: Float, toX: Float, toY: Float, startTime: Long, duration: Long) {
    logD(TAG, "┏ ━━━━━━━━━━ gestureSlideOnce >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    if (Build.VERSION.SDK_INT >= VersionCode.N) {
        val path = Path()
        path.moveTo(fromX, fromY)
        path.lineTo(toX, toY)
        val strokeDesc = StrokeDescription(path, startTime, duration)
        val gestureDesc = GestureDescription.Builder()
            .addStroke(strokeDesc)
            .build()

        logD(TAG, "┃ 手势滑动一次")
        service?.dispatchGesture(gestureDesc, null, null)
    }
    logD(TAG, "┗ ━━━━━━━━━━ gestureSlideOnce >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
}

/**
 * 手势滑动两次
 * slideHorizontalThanVertical(service, 0F, 400F, 400F, 400F, 0L, 500L, 600F, 600F, 600F, 800F, 1000L, 500L)
 *
 * @param service 无障碍辅助服务
 * @param fromX1 起点坐标 X1
 * @param fromY1 起点坐标 Y1
 * @param toX1 终点坐标 X1
 * @param toY1 终点坐标 Y1
 * @param startTime1 启动延迟1
 * @param duration1 持续时长1
 * @param fromX2 起点坐标 X2
 * @param fromY2 起点坐标 Y2
 * @param toX2 终点坐标 X2
 * @param toY2 终点坐标 Y2
 * @param startTime2 启动延迟2
 * @param duration2 持续时长2
 *
 * @return
 */
fun gestureSlideTwice(service: AccessibilityService?, fromX1: Float, fromY1: Float, toX1: Float, toY1: Float, startTime1: Long, duration1: Long, fromX2: Float, fromY2: Float, toX2: Float, toY2: Float, startTime2: Long, duration2: Long) {
    logD(TAG, "┏ ━━━━━━━━━━ gestureSlideTwice >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    if (Build.VERSION.SDK_INT >= VersionCode.N) {
        // 线性的 path 代表手势路径，点代表按下，封闭的没用
        val path1 = Path()
        path1.moveTo(fromX1, fromY1)
        path1.lineTo(toX1, toY1)
        val strokeDesc1 = StrokeDescription(path1, startTime1, duration1)
        val gestureDesc1 = GestureDescription.Builder()
            .addStroke(strokeDesc1)
            .build()

        // 第一个手势动作
        logD(TAG, "┃ 手势滑动第一次")
        service?.dispatchGesture(gestureDesc1, object : GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription) {
                super.onCompleted(gestureDescription)

                // 也可以使用 handler 延时 1.5 秒就不用在这里回调了
                val path2 = Path()
                path2.moveTo(fromX2, fromY2)
                path2.lineTo(toX2, toY2)
                val strokeDesc2 = StrokeDescription(path2, startTime2, duration2)
                val gestureDesc2 = GestureDescription.Builder()
                    .addStroke(strokeDesc2)
                    .build()

                // 第一个手势动作后，再过 startTime2 秒，进行第二个动作
                logD(TAG, "┃ 手势滑动第二次")
                service.dispatchGesture(gestureDesc2, null, null)
            }

            override fun onCancelled(gestureDescription: GestureDescription) {
                super.onCancelled(gestureDescription)
            }
        }, null)
    }
    logD(TAG, "┗ ━━━━━━━━━━ gestureSlideTwice >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
}

/**
 * 点击可点击节点
 * 点击指定的节点，通过入参决定是否需要判断节点的可点击性
 *
 * @param tapNodeInfo 待点击节点信息
 * @param isTapForcibly 是否强制点击
 * @param tapListener 点击监听
 * @return
 */
fun tapNodeClickableSelf(tapNodeInfo: AccessibilityNodeInfo? = null, isTapForcibly: Boolean = false, onTap: (tapState: String) -> Unit) {
    logD(TAG, "┏ ━━━━━━━━━━ tapNodeClickableSelf >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    // logD(TAG, "┃ tapNodeInfo: $tapNodeInfo")

    // 默认节点点击状态-不可点击
    var nodeTapState: String = NodeTapState.UNCLICKABLE

    // 节点不为空，尝试点击
    if (tapNodeInfo != null) {
        // 强制点击 || 节点可点击，进行点击
        nodeTapState = if (isTapForcibly || tapNodeInfo.isClickable) {
            // 操作点击
            tapNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK)
            logD(TAG, "┃ 操作点击")

            // 更新状态
            NodeTapState.CLICKED
        }
        // 节点不可点击
        else {
            // 更新状态
            NodeTapState.UNCLICKABLE
        }
    }
    // 节点为空
    else {
        // 更新状态
        nodeTapState = NodeTapState.NULL
    }
    logD(TAG, "┃ 节点点击状态『$nodeTapState』")

    // 回调点击接口，传回节点点击状态
    onTap(nodeTapState)

    logD(TAG, "┗ ━━━━━━━━━━ tapNodeClickableSelf >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
}

/**
 * 点击可点击节点
 * 递归节点本身及其父节点，一层一层判断可点击性，点击最近一个可点击的节点
 *
 * @param tapNodeInfo 待点击节点信息
 * @param tapListener 点击监听
 * @return
 */
fun tapNodeClickableParent(tapNodeInfo: AccessibilityNodeInfo? = null, onTap: (tapState: String) -> Unit) {
    logD(TAG, "┏ ━━━━━━━━━━ tapNodeClickableParent >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    // logD(TAG, "┃ tapNodeInfo: $tapNodeInfo")

    // 默认节点点击状态-不可点击
    var nodeTapState: String = NodeTapState.UNCLICKABLE

    // 节点不为空，则递归父节点，尝试点击
    var parentNodeInfo = tapNodeInfo
    if (parentNodeInfo != null) {
        // 递归父节点寻找可点击节点
        while (parentNodeInfo != null) {
            // 找到可点击节点，进行点击
            if (parentNodeInfo.isClickable) {
                // 操作点击
                parentNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                logD(TAG, "┃ 操作点击")

                // 更新状态
                nodeTapState = NodeTapState.CLICKED
                break
            }
            parentNodeInfo = parentNodeInfo.parent
        }
    }
    // 节点为空
    else {
        // 更新状态
        nodeTapState = NodeTapState.NULL
    }
    logD(TAG, "┃ 节点点击状态『$nodeTapState』")

    // 回调点击接口，传回节点点击状态
    onTap(nodeTapState)

    logD(TAG, "┗ ━━━━━━━━━━ tapNodeClickableParent >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
}

/**
 * 判断是否开启某项无障碍辅助服务
 *
 * @param context 上下文
 * @param serviceName 无障碍辅助服务名
 * @return
 */
fun isStartAccessibilityService(serviceName: String = "", context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    logD(TAG, "┏ ━━━━━━━━━━ isStartAccessibilityService >> START ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
    val am = context?.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    val serviceInfos = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC)
    var ret = false
    for (info in serviceInfos) {
        val id = info.id
        if (id.contains(serviceName)) {
            logD(TAG, "┃ info.id: $id")
            ret = true
        }
    }
    logD(TAG, "┗ ━━━━━━━━━━ isStartAccessibilityService >> E N D ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━")
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
