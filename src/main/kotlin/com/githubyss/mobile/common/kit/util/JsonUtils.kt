package com.githubyss.mobile.common.kit.util

import android.content.Context
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*


/**
 * JsonUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/01/24 10:08:00
 */
object JsonUtils {

    /** ****************************** Properties ****************************** */

    private val TAG: String = JsonUtils::class.java.simpleName


    /** ****************************** Functions ****************************** */

    /** ******************** Getter ******************** */

    /**
     * Parse String from JSONObject
     *
     * @param json The JSONObject to be parsed.
     * @param name The key of value to parse.
     * @return String value of name.
     */
    fun getString(json: JSONObject?, name: String?): String {
        return when {
            json == null -> ""
            StringUtils.isSpace(name) -> ""
            json.isNull(name) -> ""
            else -> json.optString(name)
        }
    }

    /**
     * Parse Boolean from JSONObject
     *
     * @param json The JSONObject to be parsed.
     * @param name The key of value to parse.
     * @return Boolean value of name.
     */
    fun getBoolean(json: JSONObject?, name: String?): Boolean {
        return when {
            json == null -> false
            StringUtils.isSpace(name) -> false
            json.isNull(name) -> false
            else -> json.optBoolean(name)
        }
    }

    /**
     * Parse Int from JSONObject
     *
     * @param json The JSONObject to be parsed.
     * @param name The key of value to parse.
     * @return Int value of name.
     */
    fun getInt(json: JSONObject?, name: String?): Int {
        return when {
            json == null -> -1
            StringUtils.isSpace(name) -> -1
            json.isNull(name) -> -1
            else -> json.optInt(name)
        }
    }

    /**
     * Parse Long from JSONObject
     *
     * @param json The JSONObject to be parsed.
     * @param name The key of value to parse.
     * @return Long value of name.
     */
    fun getLong(json: JSONObject?, name: String?): Long {
        return when {
            json == null -> -1
            StringUtils.isSpace(name) -> -1
            json.isNull(name) -> -1
            else -> json.optLong(name)
        }
    }

    /**
     * Parse Double from JSONObject
     *
     * @param json The JSONObject to be parsed.
     * @param name The key of value to parse.
     * @return Double value of name.
     */
    fun getDouble(json: JSONObject?, name: String?): Double {
        return when {
            json == null -> -1.0
            StringUtils.isSpace(name) -> -1.0
            json.isNull(name) -> -1.0
            else -> json.optDouble(name)
        }
    }

    /**
     * Parse JSONObject from JSONObject
     *
     * @param json The JSONObject to be parsed.
     * @param name The key of value to parse.
     * @return JSONObject value of name.
     */
    fun getJSONObject(json: JSONObject?, name: String?): JSONObject? {
        return when {
            json == null -> null
            StringUtils.isSpace(name) -> null
            json.isNull(name) -> null
            else -> json.optJSONObject(name)
        }
    }

    /**
     * Parse JSONObject from JSONArray
     *
     * @param json The JSONObject to be parsed.
     * @param idx The index of value to parse.
     * @return JSONObject value of idx.
     */
    fun getJSONObject(json: JSONArray?, idx: Int?): JSONObject? {
        return when {
            json == null -> null
            idx == null -> null
            json.isNull(idx) -> null
            else -> json.optJSONObject(idx)
        }
    }

    /**
     * Parse JSONArray from JSONObject
     *
     * @param json The JSONObject to be parsed.
     * @param name The key of value to parse.
     * @return JSONArray value of name.
     */
    fun getJSONArray(json: JSONObject?, name: String?): JSONArray? {
        return when {
            json == null -> null
            StringUtils.isSpace(name) -> null
            json.isNull(name) -> null
            else -> json.optJSONArray(name)
        }
    }

    /**
     * Parse JSONArray from JSONArray
     *
     * @param json The JSONObject to be parsed.
     * @param idx The index of value to parse.
     * @return JSONArray value of idx.
     */
    fun getJSONArray(json: JSONArray?, idx: Int?): JSONArray? {
        return when {
            json == null -> null
            idx == null -> null
            json.isNull(idx) -> null
            else -> json.optJSONArray(idx)
        }
    }

    /**
     * Get the Json String from file in assets dir.
     *
     * @param assetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
     * @param context        The context.
     * @return Json String if file content is Json String, otherwise "".
     */
    fun getJsonStringFromAssets(assetsFilePath: String?, context: Context? = ComkitApplicationConfig.getApp()): String {
        val string = ResourceUtils.getStringFromAssets(assetsFilePath, context)
        return if (isJsonString(string)) {
            string
        }
        else {
            ""
        }
    }

    /**
     * Get the JSONObject from file in assets dir.
     *
     * @param assetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
     * @param context        The context.
     * @return JSONObject if file content is Json String, otherwise null.
     */
    fun getJSONObjectFromAssets(assetsFilePath: String?, context: Context? = ComkitApplicationConfig.getApp()): JSONObject? {
        return when {
            StringUtils.isSpace(assetsFilePath) -> null
            else -> {
                try {
                    JSONObject(ResourceUtils.getStringFromAssets(assetsFilePath, context))
                }
                catch (e: JSONException) {
                    LogUtils.e(TAG, t = e)
                    null
                }
            }
        }
    }

    /** ******************** Checker ******************** */

    /**
     * Check input string whether it is yield a JSONObject.
     *
     * @param jsonString The string to be checked.
     * @return `true` if input string is yield a JSONObject.
     */
    fun isJsonString(jsonString: String?): Boolean {
        return when {
            jsonString == null -> false
            StringUtils.isSpace(jsonString) -> false
            else -> try {
                JSONObject(jsonString)
                true
            }
            catch (e: JSONException) {
                LogUtils.e(TAG, t = e)
                false
            }
        }
    }

    // fun isJsonString(input: String): Boolean {
    //     return when {
    //         StringUtils.isSpace(input) -> false
    //         else -> try {
    //             JsonParser().parse(input)
    //             true
    //         }
    //         catch (e: JsonSyntaxException) {
    //             LogUtils.e(TAG, t = e)
    //             false
    //         }
    //     }
    // }

    /** ******************** Converter ******************** */

    /**
     * Convert Object to JsonString.
     *
     * @param any Custom Object.
     * @return Json string.
     */
    fun anyToJsonString(any: Any?): String {
        return Gson().toJson(any) ?: ""
    }

    /**
     * Convert Object to Console.
     *
     * @param any Custom Object.
     */
    fun anyToConsole(any: Any?) {
        Gson().toJson(any, System.out)
    }

    /**
     * Convert JsonString to Any object according to given Class type.
     *
     * @param jsonString Json string.
     * @param cls Class type to parse the JsonString.
     * @return Object of Class T.
     */
    fun <T> jsonStringToAny(jsonString: String?, cls: Class<T>?): T? {
        return Gson().fromJson(jsonString, cls)
    }

    fun <T> jsonStringToMap(jsonString: String?): Map<String?, T>? {
        return Gson().fromJson(jsonString, object : TypeToken<Map<String?, T>?>() {}.type)
    }

    fun <T> jsonStringToList(jsonString: String?, cls: Class<T>?): List<T?> {
        val list: MutableList<T?> = ArrayList()
        val jsonArray: JsonArray = JsonParser().parse(jsonString).asJsonArray
        for (jsonElement in jsonArray) {
            list.add(Gson().fromJson(jsonElement, cls))
        }
        return list
    }

    fun jsonStringToJSONObject(jsonString: String?): JSONObject? {
        return when {
            jsonString == null -> null
            isJsonString(jsonString) -> JSONObject(jsonString)
            else -> null
        }
    }
}