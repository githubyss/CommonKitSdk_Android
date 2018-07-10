package com.githubyss.mobile.common.kit.logcat

import android.text.TextUtils
import android.util.Log
import com.githubyss.mobile.common.kit.converter.ComkitTypeConverter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * ComkitLogcatUtils
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitLogcatUtils {
    private val LOG_PREFIX = "githubyss"

    val SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator")

    val LOG_LEFT_TOP_CORNER = '╔'
    val LOG_LEFT_MIDDLE_CORNER = '╟'
    val LOG_LEFT_BOTTOM_CORNER = '╚'
    val LOG_VERTICAL_DOUBLE_LINE = '║'
    val LOG_HORIZONTAL_DOUBLE_LINE = "════════════════════════════════════════════"
    val LOG_HORIZONTAL_SINGLE_LINE = "────────────────────────────────────────────"
    val LOG_TOP_BORDER = "${LOG_LEFT_TOP_CORNER}${LOG_HORIZONTAL_DOUBLE_LINE}${LOG_HORIZONTAL_DOUBLE_LINE}"
    val LOG_BOTTOM_BORDER = "${LOG_LEFT_BOTTOM_CORNER}${LOG_HORIZONTAL_DOUBLE_LINE}${LOG_HORIZONTAL_DOUBLE_LINE}"
    val LOG_MIDDLE_BORDER = "${LOG_LEFT_MIDDLE_CORNER}${LOG_HORIZONTAL_SINGLE_LINE}${LOG_HORIZONTAL_SINGLE_LINE}"

    val LOG_LEVEL_VERBOSE = 1
    val LOG_LEVEL_DEBUG = 2
    val LOG_LEVEL_INFO = 3
    val LOG_LEVEL_WARN = 4
    val LOG_LEVEL_ERROR = 5
    val LOG_LEVEL_NOTHING = 6

    var logLevel = LOG_LEVEL_VERBOSE


    fun v(tag: String = LOG_PREFIX, msg: String) {
        if (logLevel <= LOG_LEVEL_VERBOSE) {
            Log.v(tag, msg)
        }
    }

    fun d(tag: String = LOG_PREFIX, msg: String) {
        if (logLevel <= LOG_LEVEL_DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun i(tag: String = LOG_PREFIX, msg: String) {
        if (logLevel <= LOG_LEVEL_INFO) {
            Log.i(tag, msg)
        }
    }

    fun w(tag: String = LOG_PREFIX, msg: String) {
        if (logLevel <= LOG_LEVEL_WARN) {
            Log.w(tag, msg)
        }
    }

    fun e(tag: String = LOG_PREFIX, msg: String) {
        if (logLevel <= LOG_LEVEL_ERROR) {
            Log.e(tag, msg)
        }
    }

    fun e(tag: String = LOG_PREFIX, t: Throwable) {
        if (logLevel <= LOG_LEVEL_ERROR) {
            Log.e(tag, "", t)
        }
    }

    fun json(jsonString: String) {
        if (logLevel <= LOG_LEVEL_INFO) {
            printJson(ComkitStackTraceElementUtils.getStackTrace(), jsonString)
        }
    }

    fun <T : Any> `object`(`object`: T?) {
        if (logLevel <= LOG_LEVEL_INFO) {
            printObject(ComkitStackTraceElementUtils.getStackTrace(), `object`)
        }
    }

    private fun printJson(element: StackTraceElement, jsonString: String) {
        val values = ComkitStackTraceElementUtils.generateValues(element)
        val tag = values[0]
        val fileName = values[1]

        if (TextUtils.isEmpty(jsonString)) {
            v(tag, "{json is empty.}")
            return
        }

        var jsonStringIndented = ""

        try {
            if (jsonString.startsWith("{")) {
                val jsonObject = JSONObject(jsonString)
                jsonStringIndented = jsonObject.toString(4)
            } else if (jsonString.startsWith("[")) {
                val jsonArray = JSONArray(jsonString)
                jsonStringIndented = jsonArray.toString(4)
            }

            val lines = jsonStringIndented.split(SYSTEM_LINE_SEPARATOR)
            val stringBuilder = StringBuilder()
            v(fileName, LOG_TOP_BORDER)
            v(fileName, "${LOG_VERTICAL_DOUBLE_LINE} $tag")
            v(fileName, LOG_MIDDLE_BORDER)
            for (line in lines) {
                stringBuilder.append("${LOG_VERTICAL_DOUBLE_LINE} ").append(line).append(SYSTEM_LINE_SEPARATOR)
                v(fileName, stringBuilder.toString())
                stringBuilder.delete(0, stringBuilder.length)
            }
            v(fileName, LOG_BOTTOM_BORDER)
        } catch (e: JSONException) {
            Log.e(tag, e.message)
        }
    }

    private fun <T : Any> printObject(element: StackTraceElement, `object`: T?) {
        val values = ComkitStackTraceElementUtils.generateValues(element)
        val tag = values[0]
        val fileName = values[1]

        if (`object` == null) {
            v(tag, "Object is null.")
            return
        }

        val simpleName = `object`.javaClass.simpleName
        when (`object`) {
            is String,
            is StringBuilder,
            is StringBuffer -> {
                val string = `object`.toString()
                printObjectString(tag, fileName, simpleName, string)
            }

            is Array<*> -> {
                val collection = `object`.toList()
                printObjectCollectionOneLine(tag, fileName, simpleName, collection)
            }

            is Collection<*> -> {
                val collection = `object`
                printObjectCollection(tag, fileName, simpleName, collection)
            }

            is Map<*, *> -> {
                val map = `object`
                printObjectMap(tag, fileName, simpleName, map)
            }

            else -> {
                val message = ComkitTypeConverter.object2String(`object`)
                v(fileName, LOG_TOP_BORDER)
                v(fileName, LOG_VERTICAL_DOUBLE_LINE + " " + tag)
                v(fileName, LOG_MIDDLE_BORDER)
                v(fileName, LOG_VERTICAL_DOUBLE_LINE + " " + message)
                v(fileName, LOG_BOTTOM_BORDER)
            }
        }
    }

    private fun printObjectString(tag: String, fileName: String, simpleName: String, string: String) {
        var msg = " %s size = %d \""
        msg = String.format(msg, simpleName, string.length)
        if (!string.isEmpty()) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(LOG_TOP_BORDER).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_VERTICAL_DOUBLE_LINE).append(" ").append(tag).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_MIDDLE_BORDER).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_VERTICAL_DOUBLE_LINE).append(msg)
            stringBuilder.append(string)
            stringBuilder.append("\"\n").append(LOG_BOTTOM_BORDER)
            v(fileName, stringBuilder.toString())
        } else {
            v(fileName, "$msg and is empty.\"")
        }
    }

    private fun printObjectCollectionOneLine(tag: String, fileName: String, simpleName: String, collection: Collection<*>) {
        var msg = " %s size = %d ["
        msg = String.format(msg, simpleName, collection.size)
        if (!collection.isEmpty()) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(LOG_TOP_BORDER).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_VERTICAL_DOUBLE_LINE).append(" ").append(tag).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_MIDDLE_BORDER).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_VERTICAL_DOUBLE_LINE).append(msg)
            val iterator = collection.iterator()
            var index = 0
            while (iterator.hasNext()) {
                val itemString = "%s%s"
                val item = iterator.next()
                stringBuilder.append(String.format(itemString, ComkitTypeConverter.object2String(item), if (index++ < collection.size - 1) ", " else ""))
            }
            stringBuilder.append("]\n").append(LOG_BOTTOM_BORDER)
            v(fileName, stringBuilder.toString())
        } else {
            v(fileName, "$msg and is empty.]")
        }
    }

    private fun printObjectCollection(tag: String, fileName: String, simpleName: String, collection: Collection<*>) {
        var msg = " %s size = %d [\n"
        msg = String.format(msg, simpleName, collection.size)
        if (!collection.isEmpty()) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(LOG_TOP_BORDER).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_VERTICAL_DOUBLE_LINE).append(" ").append(tag).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_MIDDLE_BORDER).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_VERTICAL_DOUBLE_LINE).append(msg)
            val iterator = collection.iterator()
            var index = 0
            while (iterator.hasNext()) {
                val itemString = "${LOG_VERTICAL_DOUBLE_LINE} [%d]:%s%s"
                val item = iterator.next()
                stringBuilder.append(String.format(itemString, index, ComkitTypeConverter.object2String(item), if (index++ < collection.size - 1) ",\n" else "\n"))
            }
            stringBuilder.append("${LOG_VERTICAL_DOUBLE_LINE} ]\n").append(LOG_BOTTOM_BORDER)
            v(fileName, stringBuilder.toString())
        } else {
            v(fileName, "$msg and is empty.]")
        }
    }

    private fun printObjectMap(tag: String, fileName: String, simpleName: String, map: Map<*, *>) {
        val keys = map.keys
        if (keys.isNotEmpty()) {
            val stringBuilder = StringBuilder()
            stringBuilder.append(LOG_TOP_BORDER).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_VERTICAL_DOUBLE_LINE).append(" ").append(tag).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_MIDDLE_BORDER).append(SYSTEM_LINE_SEPARATOR)
                    .append(LOG_VERTICAL_DOUBLE_LINE).append(" ").append(simpleName).append(" {\n")

            for (key in keys) {
                stringBuilder.append(LOG_VERTICAL_DOUBLE_LINE).append(" ")
                        .append(String.format("[%s -> %s]\n", ComkitTypeConverter.object2String(key), ComkitTypeConverter.object2String(if (map[key] is Array<*>) ComkitTypeConverter.array2String(map[key] as Array<*>) else map[key])))
            }
            stringBuilder.append(LOG_VERTICAL_DOUBLE_LINE).append(" ").append("}\n")
                    .append(LOG_BOTTOM_BORDER)
            v(fileName, stringBuilder.toString())
        } else {
            v(fileName, "$simpleName is empty.")
        }
    }
}
