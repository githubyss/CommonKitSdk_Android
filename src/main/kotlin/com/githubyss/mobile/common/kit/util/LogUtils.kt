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
object LogUtils {

    /** ****************************** Properties ****************************** */

    private val TAG: String = LogUtils::class.java.simpleName

    private val SYSTEM_LINE_SEPARATOR: String = System.getProperty("line.separator") ?: ""

    /** 日志分隔线 */
    private const val LOG_LEFT_TOP____CORNER: String = "╔"
    private const val LOG_LEFT_MIDDLE_CORNER: String = "╟"
    private const val LOG_LEFT_BOTTOM_CORNER: String = "╚"
    private const val LOG_VERTICAL___DOUBLE_LINE: String = "║"
    private const val LOG_HORIZONTAL_DOUBLE_LINE: String = "════════════════════════════════════════════"
    private const val LOG_HORIZONTAL_SINGLE_LINE: String = "────────────────────────────────────────────"
    private const val LOG_TOP____BORDER: String = "$LOG_LEFT_TOP____CORNER$LOG_HORIZONTAL_DOUBLE_LINE$LOG_HORIZONTAL_DOUBLE_LINE"
    private const val LOG_MIDDLE_BORDER: String = "$LOG_LEFT_MIDDLE_CORNER$LOG_HORIZONTAL_SINGLE_LINE$LOG_HORIZONTAL_SINGLE_LINE"
    private const val LOG_BOTTOM_BORDER: String = "$LOG_LEFT_BOTTOM_CORNER$LOG_HORIZONTAL_DOUBLE_LINE$LOG_HORIZONTAL_DOUBLE_LINE"

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
    fun v(tag: String = TAG, msg: String) {
        check(tag, msg, LOG_LEVEL_VERBOSE) { tag_, msg_ -> Log.v(tag_, msg_) }
    }

    /**
     * 打印 VERBOSE 详细日志（可分段）
     *
     * @param tag 记号
     * @param msg 信息
     * @param canSection 是否可分段
     */
    fun v(tag: String = TAG, msg: String, canSection: Boolean) {
        // section(tag, msg, canSection) { tag_, msg_ -> v(tag_, msg_) }
        section(tag, msg, canSection, ::v)
    }

    /** ********** DEBUG 调试日志 ********** */

    /**
     * 打印 DEBUG 调试日志
     *
     * @param tag 记号
     * @param msg 信息
     */
    fun d(tag: String = TAG, msg: String) {
        check(tag, msg, LOG_LEVEL_DEBUG) { tag_, msg_ -> Log.d(tag_, msg_) }
    }

    /**
     * 打印 DEBUG 调试日志（可分段）
     *
     * @param tag 记号
     * @param msg 信息
     * @param canSection 是否可分段
     */
    fun d(tag: String = TAG, msg: String, canSection: Boolean) {
        // section(tag, msg, canSection) { tag_, msg_ -> d(tag_, msg_) }
        section(tag, msg, canSection, ::d)
    }

    /** ********** INFO 信息日志 ********** */

    /**
     * 打印 INFO 信息日志
     *
     * @param tag 记号
     * @param msg 信息
     */
    fun i(tag: String = TAG, msg: String) {
        check(tag, msg, LOG_LEVEL_INFO) { tag_, msg_ -> Log.i(tag_, msg_) }
    }

    /**
     * 打印 INFO 信息日志（可分段）
     *
     * @param tag 记号
     * @param msg 信息
     * @param canSection 是否可分段
     */
    fun i(tag: String = TAG, msg: String, canSection: Boolean) {
        // section(tag, msg, canSection) { tag_, msg_ -> i(tag_, msg_) }
        section(tag, msg, canSection, ::i)
    }

    /** ********** WARN 报警日志 ********** */

    /**
     * 打印 WARN 报警日志
     *
     * @param tag 记号
     * @param msg 信息
     */
    fun w(tag: String = TAG, msg: String) {
        check(tag, msg, LOG_LEVEL_WARN) { tag_, msg_ -> Log.w(tag_, msg_) }
    }

    /**
     * 打印 WARN 报警日志（可分段）
     *
     * @param tag 记号
     * @param msg 信息
     * @param canSection 是否可分段
     */
    fun w(tag: String = TAG, msg: String, canSection: Boolean) {
        // section(tag, msg, canSection) { tag_, msg_ -> w(tag_, msg_) }
        section(tag, msg, canSection, ::w)
    }

    /** ********** ERROR 错误日志 ********** */

    /**
     * 打印 ERROR 错误日志
     *
     * @param tag 记号
     * @param msg 信息
     */
    fun e(tag: String = TAG, msg: String) {
        check(tag, msg, LOG_LEVEL_ERROR) { tag_, msg_ -> Log.e(tag_, msg_) }
    }

    /**
     * 打印 ERROR 错误日志（可分段）
     *
     * @param tag 记号
     * @param msg 信息
     * @param canSection 是否可分段
     */
    fun e(tag: String = TAG, msg: String, canSection: Boolean) {
        // section(tag, msg, canSection) { tag_, msg_ -> e(tag_, msg_) }
        section(tag, msg, canSection, ::e)
    }

    /**
     * 打印 ERROR 错误日志
     *
     * @param tag 记号
     * @param msg 信息
     * @param t 异常
     */
    fun e(tag: String = TAG, msg: String = "", t: Throwable) {
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

    /** ********** Json 日志 ********** */

    /**
     * 打印格式化 JsonString
     *
     * @param jsonString
     */
    fun json(jsonString: String) {
        if (isShowLog && logLevel <= LOG_LEVEL_DEBUG) {
            printJson(StackTraceElementUtils.getStackTrace(), jsonString)
        }
    }

    /**
     * 打印 Json
     *
     * @param element
     * @param jsonString
     */
    private fun printJson(element: StackTraceElement, jsonString: String) {
        val values = StackTraceElementUtils.generateValues(element)
        val tag = values[0]
        val fileName = values[1]

        if (TextUtils.isEmpty(jsonString)) {
            d(tag, "{json is empty.}")
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
            d(fileName, "$LOG_TOP____BORDER")
            d(fileName, "$LOG_VERTICAL___DOUBLE_LINE $tag")
            d(fileName, "$LOG_MIDDLE_BORDER")
            for (line in lines) {
                stringBuilder.append("$LOG_VERTICAL___DOUBLE_LINE ")
                    .append(line)
                    .append(SYSTEM_LINE_SEPARATOR)
                d(fileName, stringBuilder.toString())
                stringBuilder.delete(0, stringBuilder.length)
            }
            d(fileName, "$LOG_BOTTOM_BORDER")
        }
        catch (e: JSONException) {
            e(tag, e.message ?: "")
        }
    }

    /** ********** Object 日志 ********** */

    /**
     * 打印格式化 Object
     *
     * @param object
     */
    fun <T : Any> `object`(`object`: T?) {
        if (isShowLog && logLevel <= LOG_LEVEL_DEBUG) {
            printObject(StackTraceElementUtils.getStackTrace(), `object`)
        }
    }

    /**
     * 打印 Object
     *
     * @param element
     * @param object
     */
    private fun <T : Any> printObject(element: StackTraceElement, `object`: T?) {
        val values = StackTraceElementUtils.generateValues(element)
        val tag = values[0]
        val fileName = values[1]

        if (`object` == null) {
            d(tag, "Object is null.")
            return
        }

        val simpleName = `object`.javaClass.simpleName
        when (`object`) {
            is String, is StringBuilder, is StringBuffer -> {
                val string: String = `object`.toString()
                printObjectString(tag, fileName, simpleName, string)
            }

            is Array<*> -> {
                val array: Collection<*> = `object`.toList()
                printObjectCollectionOneLine(tag, fileName, simpleName, array)
            }

            is Collection<*> -> {
                val collection: Collection<*> = `object`
                printObjectCollection(tag, fileName, simpleName, collection)
            }

            is Map<*, *> -> {
                val map: Map<*, *> = `object`
                printObjectMap(tag, fileName, simpleName, map)
            }

            else -> {
                val message = ConvertUtils.object2String(`object`)
                d(fileName, "$LOG_TOP____BORDER")
                d(fileName, "$LOG_VERTICAL___DOUBLE_LINE $tag")
                d(fileName, "$LOG_MIDDLE_BORDER")
                d(fileName, "$LOG_VERTICAL___DOUBLE_LINE $message")
                d(fileName, "$LOG_BOTTOM_BORDER")
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
    private fun printObjectString(tag: String, fileName: String, simpleName: String, string: String) {
        var msg = " %s size = %d \""
        msg = String.format(msg, simpleName, string.length)
        if (StringUtils.isNotEmpty(string)) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(LOG_TOP____BORDER)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_VERTICAL___DOUBLE_LINE)
                .append(" ")
                .append(tag)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_MIDDLE_BORDER)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_VERTICAL___DOUBLE_LINE)
                .append(msg)
            stringBuilder.append(string)
            stringBuilder.append("\"\n")
                .append(LOG_BOTTOM_BORDER)
            d(fileName, stringBuilder.toString())
        }
        else {
            d(fileName, "$msg and is empty.\"")
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
    private fun printObjectCollectionOneLine(tag: String, fileName: String, simpleName: String, collection: Collection<*>) {
        var msg = " %s size = %d ["
        msg = String.format(msg, simpleName, collection.size)
        if (!collection.isEmpty()) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(LOG_TOP____BORDER)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_VERTICAL___DOUBLE_LINE)
                .append(" ")
                .append(tag)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_MIDDLE_BORDER)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_VERTICAL___DOUBLE_LINE)
                .append(msg)
            val iterator = collection.iterator()
            var index = 0
            while (iterator.hasNext()) {
                val itemString = "%s%s"
                val item = iterator.next()
                stringBuilder.append(String.format(itemString, ConvertUtils.object2String(item), if (index++ < collection.size - 1) ", " else ""))
            }
            stringBuilder.append("]\n")
                .append(LOG_BOTTOM_BORDER)
            d(fileName, stringBuilder.toString())
        }
        else {
            d(fileName, "$msg and is empty.]")
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
    private fun printObjectCollection(tag: String, fileName: String, simpleName: String, collection: Collection<*>) {
        var msg = " %s size = %d [\n"
        msg = String.format(msg, simpleName, collection.size)
        if (!collection.isEmpty()) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(LOG_TOP____BORDER)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_VERTICAL___DOUBLE_LINE)
                .append(" ")
                .append(tag)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_MIDDLE_BORDER)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_VERTICAL___DOUBLE_LINE)
                .append(msg)
            val iterator = collection.iterator()
            var index = 0
            while (iterator.hasNext()) {
                val itemString = "$LOG_VERTICAL___DOUBLE_LINE [%d]:%s%s"
                val item = iterator.next()
                stringBuilder.append(String.format(itemString, index, ConvertUtils.object2String(item), if (index++ < collection.size - 1) ",\n" else "\n"))
            }
            stringBuilder.append("$LOG_VERTICAL___DOUBLE_LINE ]\n")
                .append(LOG_BOTTOM_BORDER)
            d(fileName, stringBuilder.toString())
        }
        else {
            d(fileName, "$msg and is empty.]")
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
    private fun printObjectMap(tag: String, fileName: String, simpleName: String, map: Map<*, *>) {
        val keys = map.keys
        if (keys.isNotEmpty()) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(LOG_TOP____BORDER)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_VERTICAL___DOUBLE_LINE)
                .append(" ")
                .append(tag)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_MIDDLE_BORDER)
                .append(SYSTEM_LINE_SEPARATOR)
                .append(LOG_VERTICAL___DOUBLE_LINE)
                .append(" ")
                .append(simpleName)
                .append(" {\n")

            for (key in keys) {
                stringBuilder.append(LOG_VERTICAL___DOUBLE_LINE)
                    .append(" ")
                    .append(String.format("[%s -> %s]\n", ConvertUtils.object2String(key), ConvertUtils.object2String(if (map[key] is Array<*>) ConvertUtils.array2String(map[key] as Array<*>) else map[key])))
            }
            stringBuilder.append(LOG_VERTICAL___DOUBLE_LINE)
                .append(" ")
                .append("}\n")
                .append(LOG_BOTTOM_BORDER)
            d(fileName, stringBuilder.toString())
        }
        else {
            d(fileName, "$simpleName is empty.")
        }
    }
}
