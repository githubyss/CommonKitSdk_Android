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
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = LogUtils::class.simpleName ?: "simpleName is null"
    
    private const val LOG_PREFIX = ">>>>>"
    
    private val SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator") ?: ""
    
    private const val LOG_LEFT_TOP____CORNER = '╔'
    private const val LOG_LEFT_MIDDLE_CORNER = '╟'
    private const val LOG_LEFT_BOTTOM_CORNER = '╚'
    private const val LOG_VERTICAL___DOUBLE_LINE = '║'
    private const val LOG_HORIZONTAL_DOUBLE_LINE = "════════════════════════════════════════════"
    private const val LOG_HORIZONTAL_SINGLE_LINE = "────────────────────────────────────────────"
    private const val LOG_TOP____BORDER = "$LOG_LEFT_TOP____CORNER$LOG_HORIZONTAL_DOUBLE_LINE$LOG_HORIZONTAL_DOUBLE_LINE"
    private const val LOG_MIDDLE_BORDER = "$LOG_LEFT_MIDDLE_CORNER$LOG_HORIZONTAL_SINGLE_LINE$LOG_HORIZONTAL_SINGLE_LINE"
    private const val LOG_BOTTOM_BORDER = "$LOG_LEFT_BOTTOM_CORNER$LOG_HORIZONTAL_DOUBLE_LINE$LOG_HORIZONTAL_DOUBLE_LINE"
    
    const val LOG_LEVEL_VERBOSE = Log.VERBOSE
    const val LOG_LEVEL_DEBUG = Log.DEBUG
    const val LOG_LEVEL_INFO = Log.INFO
    const val LOG_LEVEL_WARN = Log.WARN
    const val LOG_LEVEL_ERROR = Log.ERROR
    const val LOG_LEVEL_ASSERT = Log.ASSERT
    const val LOG_LEVEL_NOTHING = Log.ASSERT + 1
    
    var logLevel = LOG_LEVEL_VERBOSE
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun v(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_VERBOSE) {
            Log.v("$LOG_PREFIX $tag", msg)
        }
    }
    
    fun d(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_DEBUG) {
            Log.d("$LOG_PREFIX $tag", msg)
        }
    }
    
    fun i(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_INFO) {
            Log.i("$LOG_PREFIX $tag", msg)
        }
    }
    
    fun w(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_WARN) {
            Log.w("$LOG_PREFIX $tag", msg)
        }
    }
    
    fun e(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_ERROR) {
            Log.e("$LOG_PREFIX $tag", msg)
        }
    }
    
    fun e(tag: String = TAG, t: Throwable) {
        if (logLevel <= LOG_LEVEL_ERROR) {
            Log.e("$LOG_PREFIX $tag", "", t)
        }
    }
    
    fun json(jsonString: String) {
        if (logLevel <= LOG_LEVEL_INFO) {
            printJson(StackTraceElementUtils.getStackTrace(), jsonString)
        }
    }
    
    fun <T : Any> `object`(`object`: T?) {
        if (logLevel <= LOG_LEVEL_INFO) {
            printObject(StackTraceElementUtils.getStackTrace(), `object`)
        }
    }
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    private fun printJson(element: StackTraceElement, jsonString: String) {
        val values = StackTraceElementUtils.generateValues(element)
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
            v(fileName, LOG_TOP____BORDER)
            v(fileName, "$LOG_VERTICAL___DOUBLE_LINE $tag")
            v(fileName, LOG_MIDDLE_BORDER)
            for (line in lines) {
                stringBuilder.append("$LOG_VERTICAL___DOUBLE_LINE ")
                    .append(line)
                    .append(SYSTEM_LINE_SEPARATOR)
                v(fileName, stringBuilder.toString())
                stringBuilder.delete(0, stringBuilder.length)
            }
            v(fileName, LOG_BOTTOM_BORDER)
        } catch (e: JSONException) {
            Log.e(tag, e.message)
        }
    }
    
    private fun <T : Any> printObject(element: StackTraceElement, `object`: T?) {
        val values = StackTraceElementUtils.generateValues(element)
        val tag = values[0]
        val fileName = values[1]
        
        if (`object` == null) {
            v(tag, "Object is null.")
            return
        }
        
        val simpleName = `object`.javaClass.simpleName
        when (`object`) {
            is String, is StringBuilder, is StringBuffer -> {
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
                val message = ConvertUtils.object2String(`object`)
                v(fileName, LOG_TOP____BORDER)
                v(fileName, LOG_VERTICAL___DOUBLE_LINE + " " + tag)
                v(fileName, LOG_MIDDLE_BORDER)
                v(fileName, LOG_VERTICAL___DOUBLE_LINE + " " + message)
                v(fileName, LOG_BOTTOM_BORDER)
            }
        }
    }
    
    private fun printObjectString(tag: String, fileName: String, simpleName: String, string: String) {
        var msg = " %s size = %d \""
        msg = String.format(msg, simpleName, string.length)
        if (!string.isEmpty()) {
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
            v(fileName, stringBuilder.toString())
        } else {
            v(fileName, "$msg and is empty.]")
        }
    }
    
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
            v(fileName, stringBuilder.toString())
        } else {
            v(fileName, "$simpleName is empty.")
        }
    }
}
