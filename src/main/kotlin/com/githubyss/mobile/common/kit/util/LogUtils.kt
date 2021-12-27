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
    
    private const val LOG_LEFT_TOP____CORNER: String = "╔"
    private const val LOG_LEFT_MIDDLE_CORNER: String = "╟"
    private const val LOG_LEFT_BOTTOM_CORNER: String = "╚"
    private const val LOG_VERTICAL___DOUBLE_LINE: String = "║"
    private const val LOG_HORIZONTAL_DOUBLE_LINE: String = "════════════════════════════════════════════"
    private const val LOG_HORIZONTAL_SINGLE_LINE: String = "────────────────────────────────────────────"
    private const val LOG_TOP____BORDER: String = "$LOG_LEFT_TOP____CORNER$LOG_HORIZONTAL_DOUBLE_LINE$LOG_HORIZONTAL_DOUBLE_LINE"
    private const val LOG_MIDDLE_BORDER: String = "$LOG_LEFT_MIDDLE_CORNER$LOG_HORIZONTAL_SINGLE_LINE$LOG_HORIZONTAL_SINGLE_LINE"
    private const val LOG_BOTTOM_BORDER: String = "$LOG_LEFT_BOTTOM_CORNER$LOG_HORIZONTAL_DOUBLE_LINE$LOG_HORIZONTAL_DOUBLE_LINE"
    
    const val LOG_LEVEL_VERBOSE: Int = Log.VERBOSE
    const val LOG_LEVEL_DEBUG: Int = Log.DEBUG
    const val LOG_LEVEL_INFO: Int = Log.INFO
    const val LOG_LEVEL_WARN: Int = Log.WARN
    const val LOG_LEVEL_ERROR: Int = Log.ERROR
    const val LOG_LEVEL_ASSERT: Int = Log.ASSERT
    const val LOG_LEVEL_NOTHING: Int = Log.ASSERT + 1
    
    private const val LOG_PREFIX: String = ">>>>>"
    
    var logLevel: Int = LOG_LEVEL_VERBOSE
    var logPrefix: String = LOG_PREFIX
    
    
    /** ****************************** Functions ****************************** */
    
    fun v(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_VERBOSE) {
            Log.v("$logPrefix $tag", msg)
        }
    }
    
    fun d(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_DEBUG) {
            Log.d("$logPrefix $tag", msg)
        }
    }
    
    fun i(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_INFO) {
            Log.i("$logPrefix $tag", msg)
        }
    }
    
    fun w(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_WARN) {
            Log.w("$logPrefix $tag", msg)
        }
    }
    
    fun e(tag: String = TAG, msg: String) {
        if (logLevel <= LOG_LEVEL_ERROR) {
            Log.e("$logPrefix $tag", msg)
        }
    }
    
    fun e(tag: String = TAG, t: Throwable) {
        if (logLevel <= LOG_LEVEL_ERROR) {
            Log.e("$logPrefix $tag", "", t)
        }
    }
    
    fun json(jsonString: String) {
        if (logLevel <= LOG_LEVEL_DEBUG) {
            printJson(StackTraceElementUtils.getStackTrace(), jsonString)
        }
    }
    
    fun <T : Any> `object`(`object`: T?) {
        if (logLevel <= LOG_LEVEL_DEBUG) {
            printObject(StackTraceElementUtils.getStackTrace(), `object`)
        }
    }
    
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
            d(fileName, LOG_TOP____BORDER)
            d(fileName, "$LOG_VERTICAL___DOUBLE_LINE $tag")
            d(fileName, LOG_MIDDLE_BORDER)
            for (line in lines) {
                stringBuilder.append("$LOG_VERTICAL___DOUBLE_LINE ")
                    .append(line)
                    .append(SYSTEM_LINE_SEPARATOR)
                d(fileName, stringBuilder.toString())
                stringBuilder.delete(0, stringBuilder.length)
            }
            d(fileName, LOG_BOTTOM_BORDER)
        }
        catch (e: JSONException) {
            e(tag, e.message ?: "")
        }
    }
    
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
                d(fileName, LOG_TOP____BORDER)
                d(fileName, "$LOG_VERTICAL___DOUBLE_LINE $tag")
                d(fileName, LOG_MIDDLE_BORDER)
                d(fileName, "$LOG_VERTICAL___DOUBLE_LINE $message")
                d(fileName, LOG_BOTTOM_BORDER)
            }
        }
    }
    
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
