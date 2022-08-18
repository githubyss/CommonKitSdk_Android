package com.githubyss.mobile.common.kit.util

import android.text.TextUtils
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


/**
 * LogUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:00:59
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "LogUtils"

private val SYSTEM_LINE_SEPARATOR: String = System.getProperty("line.separator") ?: ""

/** 日志分隔线 */
private const val LOG_LEFT_TOP____CORNER_DOUBLE__THIN: String = "╔"
private const val LOG_LEFT_MIDDLE_CORNER_DOUBLE__THIN: String = "╟"
private const val LOG_LEFT_BOTTOM_CORNER_DOUBLE__THIN: String = "╚"
private const val LOG_LEFT_TOP____CORNER_SINGLE_THICK: String = "┏"
private const val LOG_LEFT_MIDDLE_CORNER_SINGLE_THICK: String = "┃"
private const val LOG_LEFT_BOTTOM_CORNER_SINGLE_THICK: String = "┗"
private const val LOG_VERTICAL___DOUBLE__THIN: String = "║"
private const val LOG_VERTICAL___SINGLE_THICK: String = "┃"
private const val LOG_HORIZONTAL_SINGLE__THIN: String = "──────────────────────────────────────────────────"
private const val LOG_HORIZONTAL_DOUBLE__THIN: String = "══════════════════════════════════════════════════"
private const val LOG_HORIZONTAL_SINGLE_THICK: String = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
private const val LOG_HORIZONTAL_SINGLE_THICK_1: String = "━━━━━━━━━━"
private const val LOG_HORIZONTAL_SINGLE_THICK_2: String = "━━━━━━━━━━ ━━━━━━━━━━"
private const val LOG_HORIZONTAL_SINGLE_THICK_3: String = "━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━"
private const val LOG_HORIZONTAL_SINGLE_THICK_4: String = "━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━"
private const val LOG_HORIZONTAL_SINGLE_THICK_5: String = "━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━ ━━━━━━━━━━"
private const val LOG_TOP____BORDER_DOUBLE__THIN: String = "$LOG_LEFT_TOP____CORNER_DOUBLE__THIN$LOG_HORIZONTAL_DOUBLE__THIN$LOG_HORIZONTAL_DOUBLE__THIN"
private const val LOG_MIDDLE_BORDER_DOUBLE__THIN: String = "$LOG_LEFT_MIDDLE_CORNER_DOUBLE__THIN$LOG_HORIZONTAL_SINGLE__THIN$LOG_HORIZONTAL_SINGLE__THIN"
private const val LOG_BOTTOM_BORDER_DOUBLE__THIN: String = "$LOG_LEFT_BOTTOM_CORNER_DOUBLE__THIN$LOG_HORIZONTAL_DOUBLE__THIN$LOG_HORIZONTAL_DOUBLE__THIN"

/** 日志级别 */
const val LOG_LEVEL_VERBOSE: Int = Log.VERBOSE
const val LOG_LEVEL_DEBUG: Int = Log.DEBUG
const val LOG_LEVEL_INFO: Int = Log.INFO
const val LOG_LEVEL_WARN: Int = Log.WARN
const val LOG_LEVEL_ERROR: Int = Log.ERROR
const val LOG_LEVEL_ASSERT: Int = Log.ASSERT
const val LOG_LEVEL_NOTHING: Int = Log.ASSERT + 1

/** 日志前缀 */
private const val LOG_PREFIX: String = ">>>>>"

/** 分段日志最大长度 */
private const val MAX_SECTION_LENGTH = 3500

/** 日志启停状态 */
private var isShowLog = false

/** 日志级别 */
var logLevel: Int = LOG_LEVEL_VERBOSE

/** 日志前缀 */
var logPrefix: String = LOG_PREFIX


/** ****************************** Functions ****************************** */

/** ******************** 启停 ******************** */

/**
 * 启用日志
 */
fun enableLog() {
    isShowLog = true
}

/**
 * 禁用日志
 */
fun disableLog() {
    isShowLog = false
}

/** ******************** 常规打印 ******************** */

/** ********** VERBOSE 详细日志 ********** */

/**
 * 打印 VERBOSE 详细日志
 *
 * @param tag 记号
 * @param msg 信息
 */
fun logV(tag: String = TAG, msg: String) {
    check(tag, msg, LOG_LEVEL_VERBOSE) { tag_, msg_ -> Log.v(tag_, msg_) }
}

/**
 * 打印 VERBOSE 详细日志（可分段）
 *
 * @param tag 记号
 * @param msg 信息
 * @param canSection 是否可分段
 */
fun logV(tag: String = TAG, msg: String, canSection: Boolean) {
    // section(tag, msg, canSection) { tag_, msg_ -> v(tag_, msg_) }
    section(tag, msg, canSection, ::logV)
}

/** ********** DEBUG 调试日志 ********** */

/**
 * 打印 DEBUG 调试日志
 *
 * @param tag 记号
 * @param msg 信息
 */
fun logD(tag: String = TAG, msg: String) {
    check(tag, msg, LOG_LEVEL_DEBUG) { tag_, msg_ -> Log.d(tag_, msg_) }
}

/**
 * 打印 DEBUG 调试日志（可分段）
 *
 * @param tag 记号
 * @param msg 信息
 * @param canSection 是否可分段
 */
fun logD(tag: String = TAG, msg: String, canSection: Boolean) {
    // section(tag, msg, canSection) { tag_, msg_ -> d(tag_, msg_) }
    section(tag, msg, canSection, ::logD)
}

/** ********** INFO 信息日志 ********** */

/**
 * 打印 INFO 信息日志
 *
 * @param tag 记号
 * @param msg 信息
 */
fun logI(tag: String = TAG, msg: String) {
    check(tag, msg, LOG_LEVEL_INFO) { tag_, msg_ -> Log.i(tag_, msg_) }
}

/**
 * 打印 INFO 信息日志（可分段）
 *
 * @param tag 记号
 * @param msg 信息
 * @param canSection 是否可分段
 */
fun logI(tag: String = TAG, msg: String, canSection: Boolean) {
    // section(tag, msg, canSection) { tag_, msg_ -> logI(tag_, msg_) }
    section(tag, msg, canSection, ::logI)
}

/** ********** WARN 报警日志 ********** */

/**
 * 打印 WARN 报警日志
 *
 * @param tag 记号
 * @param msg 信息
 */
fun logW(tag: String = TAG, msg: String) {
    check(tag, msg, LOG_LEVEL_WARN) { tag_, msg_ -> Log.w(tag_, msg_) }
}

/**
 * 打印 WARN 报警日志（可分段）
 *
 * @param tag 记号
 * @param msg 信息
 * @param canSection 是否可分段
 */
fun logW(tag: String = TAG, msg: String, canSection: Boolean) {
    // section(tag, msg, canSection) { tag_, msg_ -> logW(tag_, msg_) }
    section(tag, msg, canSection, ::logW)
}

/** ********** ERROR 错误日志 ********** */

/**
 * 打印 ERROR 错误日志
 *
 * @param tag 记号
 * @param msg 信息
 */
fun logE(tag: String = TAG, msg: String) {
    check(tag, msg, LOG_LEVEL_ERROR) { tag_, msg_ -> Log.e(tag_, msg_) }
}

/**
 * 打印 ERROR 错误日志（可分段）
 *
 * @param tag 记号
 * @param msg 信息
 * @param canSection 是否可分段
 */
fun logE(tag: String = TAG, msg: String, canSection: Boolean) {
    // section(tag, msg, canSection) { tag_, msg_ -> e(tag_, msg_) }
    section(tag, msg, canSection, ::logE)
}

/**
 * 打印 ERROR 错误日志
 *
 * @param tag 记号
 * @param msg 信息
 * @param t 异常
 */
fun logE(tag: String = TAG, msg: String = "", t: Throwable) {
    check(tag, msg, t, LOG_LEVEL_ERROR) { tag_, msg_, t_ -> Log.e(tag_, msg_, t_) }
}

/** ********** 日志启停状态、日志级别检查 ********** */

/**
 * 启停状态、级别检查
 *
 * @param tag 记号
 * @param msg 信息
 * @param level 日志级别
 * @param log 指定打印方法
 */
private fun check(tag: String, msg: String, level: Int, log: (tag: String, msg: String) -> Unit) {
    if (isShowLog && logLevel <= level) {
        log("$logPrefix $tag", msg)
    }
}

/**
 * 启停状态、级别检查
 *
 * @param tag 记号
 * @param msg 信息
 * @param t 异常
 * @param level 日志级别
 * @param log 指定打印方法
 */
private fun check(tag: String, msg: String, t: Throwable, level: Int, log: (tag: String, msg: String, t: Throwable) -> Unit) {
    if (isShowLog && logLevel <= level) {
        log("$logPrefix $tag", msg, t)
    }
}

/** ********** 日志分段打印 ********** */

/**
 * 分段打印
 *
 * @param tag 记号
 * @param msg 信息
 * @param canSection 是否可分段
 * @param log 指定打印方法
 */
private fun section(tag: String = TAG, msg: String, canSection: Boolean, log: (tag: String, msg: String) -> Unit) {
    var msg = msg
    if (canSection) {
        while (msg.length > MAX_SECTION_LENGTH) {
            log(tag, msg.substring(0, MAX_SECTION_LENGTH))
            msg = msg.substring(MAX_SECTION_LENGTH)
        }
        if (msg.isNotEmpty()) {
            log(tag, msg)
        }
    }
    else {
        log(tag, msg)
    }
}

/** ******************** 格式化打印 ******************** */

/** ********** 通用格式化打印 ********** */

fun logStart(msg: String, level: Int) {
    logStart(msg, level, ::logD)
}

private fun logStart(msg: String, level: Int, log: (tag: String, msg: String) -> Unit) {
    val logPrefix = "$LOG_LEFT_TOP____CORNER_SINGLE_THICK $LOG_HORIZONTAL_SINGLE_THICK_1"
    val logPostfix = logPostfix(level)
    log(TAG, "$logPrefix $msg >> START $logPostfix")
}

fun logMiddle(msg: String) {
    logMiddle(msg, ::logD)
}

private fun logMiddle(msg: String, log: (tag: String, msg: String) -> Unit) {
    val logPrefix = LOG_LEFT_MIDDLE_CORNER_SINGLE_THICK
    log(TAG, "$logPrefix $msg")
}

fun logEnd(msg: String, level: Int) {
    logEnd(msg, level, ::logD)
}

private fun logEnd(msg: String, level: Int, log: (tag: String, msg: String) -> Unit) {
    val logPrefix = "$LOG_LEFT_BOTTOM_CORNER_SINGLE_THICK $LOG_HORIZONTAL_SINGLE_THICK_1"
    val logPostfix = logPostfix(level)
    log(TAG, "$logPrefix $msg >> END $logPostfix")
}

private fun logPostfix(level: Int): String {
    return when (level) {
        1 -> LOG_HORIZONTAL_SINGLE_THICK_1
        2 -> LOG_HORIZONTAL_SINGLE_THICK_2
        3 -> LOG_HORIZONTAL_SINGLE_THICK_3
        4 -> LOG_HORIZONTAL_SINGLE_THICK_4
        5 -> LOG_HORIZONTAL_SINGLE_THICK_5
        else -> LOG_HORIZONTAL_SINGLE_THICK_5
    }
}

/** ********** Json 日志 ********** */

/**
 * 打印格式化 JsonString
 *
 * @param jsonString
 */
fun logJson(jsonString: String) {
    if (isShowLog && logLevel <= LOG_LEVEL_DEBUG) {
        printJson(getStackTrace(), jsonString)
    }
}

/**
 * 打印 Json
 *
 * @param element
 * @param jsonString
 */
private fun printJson(element: StackTraceElement, jsonString: String) {
    val values = generateValues(element)
    val tag = values[0]
    val fileName = values[1]

    if (TextUtils.isEmpty(jsonString)) {
        logD(tag, "{logJson is empty.}")
        return
    }

    var jsonStringIndented = ""

    try {
        if (jsonString.startsWith("{")) {
            val jsonObject = JSONObject(jsonString)
            jsonStringIndented = jsonObject.toString(4)
        }
        else if (jsonString.startsWith("[")) {
            val jsonArray = JSONArray(jsonString)
            jsonStringIndented = jsonArray.toString(4)
        }

        val lines = jsonStringIndented.split(SYSTEM_LINE_SEPARATOR)
        val stringBuilder = StringBuilder()
        logD(fileName, LOG_TOP____BORDER_DOUBLE__THIN)
        logD(fileName, "$LOG_VERTICAL___DOUBLE__THIN $tag")
        logD(fileName, LOG_MIDDLE_BORDER_DOUBLE__THIN)
        for (line in lines) {
            stringBuilder.append("$LOG_VERTICAL___DOUBLE__THIN ")
                .append(line)
                .append(SYSTEM_LINE_SEPARATOR)
            logD(fileName, stringBuilder.toString())
            stringBuilder.delete(0, stringBuilder.length)
        }
        logD(fileName, LOG_BOTTOM_BORDER_DOUBLE__THIN)
    }
    catch (e: JSONException) {
        logE(tag, e.message ?: "")
    }
}

/** ********** Object 日志 ********** */

/**
 * 打印格式化 Object
 *
 * @param `object`
 */
fun <T : Any> logAny(any: T?) {
    if (isShowLog && logLevel <= LOG_LEVEL_DEBUG) {
        printAny(getStackTrace(), any)
    }
}

/**
 * 打印 Object
 *
 * @param element
 * @param `object`
 */
private fun <T : Any> printAny(element: StackTraceElement, any: T?) {
    val values = generateValues(element)
    val tag = values[0]
    val fileName = values[1]

    if (any == null) {
        logD(tag, "Object is null.")
        return
    }

    val simpleName = any.javaClass.simpleName
    when (any) {
        is String, is StringBuilder, is StringBuffer -> {
            val string: String = any.toString()
            printString(tag, fileName, simpleName, string)
        }

        is Array<*> -> {
            val array: Collection<*> = any.toList()
            printCollectionOneLine(tag, fileName, simpleName, array)
        }

        is Collection<*> -> {
            val collection: Collection<*> = any
            printCollection(tag, fileName, simpleName, collection)
        }

        is Map<*, *> -> {
            val map: Map<*, *> = any
            printMap(tag, fileName, simpleName, map)
        }

        else -> {
            val message = any.object2String()
            logD(fileName, LOG_TOP____BORDER_DOUBLE__THIN)
            logD(fileName, "$LOG_VERTICAL___DOUBLE__THIN $tag")
            logD(fileName, LOG_MIDDLE_BORDER_DOUBLE__THIN)
            logD(fileName, "$LOG_VERTICAL___DOUBLE__THIN $message")
            logD(fileName, LOG_BOTTOM_BORDER_DOUBLE__THIN)
        }
    }
}

/**
 * 打印 String 类型
 *
 * @param tag 记号
 * @param fileName
 * @param simpleName
 * @param string
 */
private fun printString(tag: String, fileName: String, simpleName: String, string: String) {
    var msg = " %s size = %d \""
    msg = String.format(msg, simpleName, string.length)
    if (isNotEmpty(string)) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(LOG_TOP____BORDER_DOUBLE__THIN)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_VERTICAL___DOUBLE__THIN)
            .append(" ")
            .append(tag)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_MIDDLE_BORDER_DOUBLE__THIN)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_VERTICAL___DOUBLE__THIN)
            .append(msg)
        stringBuilder.append(string)
        stringBuilder.append("\"\n")
            .append(LOG_BOTTOM_BORDER_DOUBLE__THIN)
        logD(fileName, stringBuilder.toString())
    }
    else {
        logD(fileName, "$msg and is empty.\"")
    }
}

/**
 * 打印 Collection 类型在同一行
 *
 * @param tag 记号
 * @param fileName
 * @param simpleName
 * @param collection
 */
private fun printCollectionOneLine(tag: String, fileName: String, simpleName: String, collection: Collection<*>) {
    var msg = " %s size = %d ["
    msg = String.format(msg, simpleName, collection.size)
    if (!collection.isEmpty()) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(LOG_TOP____BORDER_DOUBLE__THIN)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_VERTICAL___DOUBLE__THIN)
            .append(" ")
            .append(tag)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_MIDDLE_BORDER_DOUBLE__THIN)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_VERTICAL___DOUBLE__THIN)
            .append(msg)
        val iterator = collection.iterator()
        var index = 0
        while (iterator.hasNext()) {
            val itemString = "%s%s"
            val item = iterator.next()
            stringBuilder.append(String.format(itemString, item.object2String(), if (index++ < collection.size - 1) ", " else ""))
        }
        stringBuilder.append("]\n")
            .append(LOG_BOTTOM_BORDER_DOUBLE__THIN)
        logD(fileName, stringBuilder.toString())
    }
    else {
        logD(fileName, "$msg and is empty.]")
    }
}

/**
 * 打印 Collection 类型
 *
 * @param tag 记号
 * @param fileName
 * @param simpleName
 * @param collection
 */
private fun printCollection(tag: String, fileName: String, simpleName: String, collection: Collection<*>) {
    var msg = " %s size = %d [\n"
    msg = String.format(msg, simpleName, collection.size)
    if (!collection.isEmpty()) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(LOG_TOP____BORDER_DOUBLE__THIN)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_VERTICAL___DOUBLE__THIN)
            .append(" ")
            .append(tag)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_MIDDLE_BORDER_DOUBLE__THIN)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_VERTICAL___DOUBLE__THIN)
            .append(msg)
        val iterator = collection.iterator()
        var index = 0
        while (iterator.hasNext()) {
            val itemString = "$LOG_VERTICAL___DOUBLE__THIN [%d]:%s%s"
            val item = iterator.next()
            stringBuilder.append(String.format(itemString, index, item.object2String(), if (index++ < collection.size - 1) ",\n" else "\n"))
        }
        stringBuilder.append("$LOG_VERTICAL___DOUBLE__THIN ]\n")
            .append(LOG_BOTTOM_BORDER_DOUBLE__THIN)
        logD(fileName, stringBuilder.toString())
    }
    else {
        logD(fileName, "$msg and is empty.]")
    }
}

/**
 * 打印 Map 类型
 *
 * @param tag 记号
 * @param fileName
 * @param simpleName
 * @param map
 */
private fun printMap(tag: String, fileName: String, simpleName: String, map: Map<*, *>) {
    val keys = map.keys
    if (keys.isNotEmpty()) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(LOG_TOP____BORDER_DOUBLE__THIN)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_VERTICAL___DOUBLE__THIN)
            .append(" ")
            .append(tag)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_MIDDLE_BORDER_DOUBLE__THIN)
            .append(SYSTEM_LINE_SEPARATOR)
            .append(LOG_VERTICAL___DOUBLE__THIN)
            .append(" ")
            .append(simpleName)
            .append(" {\n")

        for (key in keys) {
            stringBuilder.append(LOG_VERTICAL___DOUBLE__THIN)
                .append(" ")
                .append(String.format("[%s -> %s]\n", key.object2String(), (if (map[key] is Array<*>) (map[key] as Array<*>).array2String() else map[key]))).object2String()
        }
        stringBuilder.append(LOG_VERTICAL___DOUBLE__THIN)
            .append(" ")
            .append("}\n")
            .append(LOG_BOTTOM_BORDER_DOUBLE__THIN)
        logD(fileName, stringBuilder.toString())
    }
    else {
        logD(fileName, "$simpleName is empty.")
    }
}
