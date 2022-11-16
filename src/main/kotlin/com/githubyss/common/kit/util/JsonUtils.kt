package com.githubyss.common.kit.util

import android.content.Context
import com.githubyss.common.base.application.BaseApplicationHolder
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


/**
 * JsonUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/01/24 10:08:00
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "JsonUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/** ********** get FromJSONObject ********** */

/**
 * Parse String from JSONObject
 *
 * @param json The JSONObject to be parsed.
 * @param name The key of value to parse.
 * @return String value of name.
 */
fun getStringFromJSONObject(json: JSONObject?, name: String?) = json.string(name)
fun JSONObject?.string(name: String?) = when (this) {
    null -> null
    else -> this.optString(name)
}

/**
 * Parse Boolean from JSONObject
 *
 * @param json The JSONObject to be parsed.
 * @param name The key of value to parse.
 * @return Boolean value of name.
 */
fun getBooleanFromJSONObject(json: JSONObject?, name: String?) = json.boolean(name)
fun JSONObject?.boolean(name: String?) = when (this) {
    null -> null
    else -> this.optBoolean(name)
}

/**
 * Parse Int from JSONObject
 *
 * @param json The JSONObject to be parsed.
 * @param name The key of value to parse.
 * @return Int value of name.
 */
fun getIntFromJSONObject(json: JSONObject?, name: String?) = json.int(name)
fun JSONObject?.int(name: String?) = when (this) {
    null -> null
    else -> this.optInt(name)
}

/**
 * Parse Long from JSONObject
 *
 * @param json The JSONObject to be parsed.
 * @param name The key of value to parse.
 * @return Long value of name.
 */
fun getLongFromJSONObject(json: JSONObject?, name: String?) = json.long(name)
fun JSONObject?.long(name: String?): Long? = when (this) {
    null -> null
    else -> this.optLong(name)
}

/**
 * Parse Double from JSONObject
 *
 * @param json The JSONObject to be parsed.
 * @param name The key of value to parse.
 * @return Double value of name.
 */
fun getDoubleFromJSONObject(json: JSONObject?, name: String?) = json.double(name)
fun JSONObject?.double(name: String?) = when (this) {
    null -> null
    else -> this.optDouble(name)
}

/**
 * Parse JSONObject from JSONObject
 *
 * @param json The JSONObject to be parsed.
 * @param name The key of value to parse.
 * @return JSONObject value of name.
 */
fun getJSONObjectFromJSONObject(json: JSONObject?, name: String?) = json.jsonObject(name)
fun JSONObject?.jsonObject(name: String?) = when (this) {
    null -> null
    else -> this.optJSONObject(name)
}

/**
 * Parse JSONArray from JSONObject
 *
 * @param json The JSONObject to be parsed.
 * @param name The key of value to parse.
 * @return JSONArray value of name.
 */
fun getJSONArrayFromJSONObject(json: JSONObject?, name: String?) = json.jsonArray(name)
fun JSONObject?.jsonArray(name: String?) = when (this) {
    null -> null
    else -> this.optJSONArray(name)
}

/** ********** get FromJSONArray ********** */

/**
 * Parse JSONObject from JSONArray
 *
 * @param json The JSONObject to be parsed.
 * @param idx The index of value to parse.
 * @return JSONObject value of idx.
 */
fun getJSONObjectFromJSONArray(json: JSONArray?, idx: Int?) = json.jsonObject(idx)
fun JSONArray?.jsonObject(idx: Int?) = when {
    this == null -> null
    idx == null -> null
    else -> this.optJSONObject(idx)
}

/**
 * Parse JSONArray from JSONArray
 *
 * @param json The JSONObject to be parsed.
 * @param idx The index of value to parse.
 * @return JSONArray value of idx.
 */
fun getJSONArrayFromJSONArray(json: JSONArray?, idx: Int?) = json.jsonArray(idx)
fun JSONArray?.jsonArray(idx: Int?) = when {
    this == null -> null
    idx == null -> null
    else -> this.optJSONArray(idx)
}

/** ********** get FromAssets JsonFile ********** */

/**
 * Get the Json String from file in assets dir.
 *
 * @param assetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
 * @param context The context.
 * @return Json String if file content is Json String, otherwise null.
 */
@JvmOverloads
fun getJsonStringFromAssets(assetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()) = context.getJsonStringFromAssets(assetsFilePath)
fun Context?.getJsonStringFromAssets(assetsFilePath: String?): String? {
    val string = this.getStringFromAssets(assetsFilePath)
    return when {
        string.isJsonString() -> string
        else -> null
    }
}

/**
 * Get the JSONObject String from file in assets dir.
 *
 * @param assetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
 * @param context The context.
 * @return JSONObject String if file content is JSONObject String, otherwise null.
 */
@JvmOverloads
fun getJsonObjectStringFromAssets(assetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()) = context.getJsonObjectStringFromAssets(assetsFilePath)
fun Context?.getJsonObjectStringFromAssets(assetsFilePath: String?): String? {
    val string = this.getStringFromAssets(assetsFilePath)
    return when {
        string.isJsonObjectString() -> string
        else -> null
    }
}

/**
 * Get the JSONArray String from file in assets dir.
 *
 * @param assetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
 * @param context The context.
 * @return JSONArray String if file content is JSONArray String, otherwise null.
 */
@JvmOverloads
fun getJsonArrayStringFromAssets(assetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()) = context.getJsonArrayStringFromAssets(assetsFilePath)
fun Context?.getJsonArrayStringFromAssets(assetsFilePath: String?): String? {
    val string = this.getStringFromAssets(assetsFilePath)
    return when {
        string.isJsonArrayString() -> string
        else -> null
    }
}

/**
 * Get the JSONObject from file in assets dir.
 *
 * @param assetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
 * @param context        The context.
 * @return JSONObject if file content is Json String, otherwise null.
 */
@JvmOverloads
fun getJSONObjectFromAssets(assetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()) = context.getJSONObjectFromAssets(assetsFilePath)
fun Context?.getJSONObjectFromAssets(assetsFilePath: String?) = this.getJsonObjectStringFromAssets(assetsFilePath)?.jsonObjectString2JSONObject()

@JvmOverloads
fun getJSONArrayFromAssets(assetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()) = context.getJSONArrayFromAssets(assetsFilePath)
fun Context?.getJSONArrayFromAssets(assetsFilePath: String?) = this.getJsonArrayStringFromAssets(assetsFilePath)?.jsonArrayString2JSONArray()

@JvmOverloads
fun <V> getMapFromAssets(assetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()) = context.getMapFromAssets<V>(assetsFilePath)
fun <V> Context?.getMapFromAssets(assetsFilePath: String?) = this.getJsonStringFromAssets(assetsFilePath)?.jsonString2Map<V?>()

/** ********** JSONArray iterator ********** */

operator fun JSONArray.iterator() = (0 until length()).asSequence().map { get(it) as JSONObject }.iterator()

/** ******************** Checker ******************** */

/**
 * Check input string whether it is yield a JSON.
 *
 * @param s The string to be checked.
 * @return `true` if input string is yield a JSON.
 */
@JvmName("isJsonString1")
fun isJsonString(s: String?) = s.isJsonString()
fun String?.isJsonString() = this.isJsonObjectString() || this.isJsonArrayString()

/**
 * Check input string whether it is yield a JSONObject.
 *
 * @param s The string to be checked.
 * @return `true` if input string is yield a JSONObject.
 */
@JvmName("isJsonObjectString1")
fun isJsonObjectString(s: String?) = s.isJsonObjectString()
fun String?.isJsonObjectString() = when {
    this == null -> false
    this.isSpaceNonNull() -> false
    else -> try {
        JSONObject(this)
        true
    }
    catch (e: JSONException) {
        logE(TAG, t = e)
        false
    }
}

/**
 * Check input string whether it is yield a JSONArray.
 *
 * @param s The string to be checked.
 * @return `true` if input string is yield a JSONArray.
 */
@JvmName("isJsonArrayString1")
fun isJsonArrayString(s: String?) = s.isJsonArrayString()
fun String?.isJsonArrayString() = when {
    this == null -> false
    this.isSpaceNonNull() -> false
    else -> try {
        JSONArray(this)
        true
    }
    catch (e: JSONException) {
        logE(TAG, t = e)
        false
    }
}

// fun String?.isJsonString() = when {
//     this == null -> false
//     this.isSpaceNonNull() -> false
//     else -> try {
//         JsonParser.parseString(this)
//         true
//     }
//     catch (e: JsonSyntaxException) {
//         logE(TAG, t = e)
//         false
//     }
// }

/** ******************** Converter ******************** */

/** ********** String -> ********** */

/**
 * Convert String to JSONObject.
 *
 * @param s Any String.
 * @return JSONObject of given String.
 */
@JvmName("string2JSONObject1")
fun string2JSONObject(s: String?) = s.string2JSONObject()
fun String?.string2JSONObject() = when {
    this == null -> null
    this.isJsonObjectString() -> try {
        JSONObject(this)
    }
    catch (e: JSONException) {
        logE(TAG, t = e)
        null
    }
    else -> null
}

/**
 * Convert String to JSONArray.
 *
 * @param s Any String.
 * @return JSONArray of given String.
 */
@JvmName("string2JSONArray1")
fun string2JSONArray(s: String?) = s.string2JSONArray()
fun String?.string2JSONArray() = when {
    this == null -> null
    this.isJsonArrayString() -> try {
        JSONArray(this)
    }
    catch (e: JSONException) {
        logE(TAG, t = e)
        null
    }
    else -> null
}

/** ********** JsonString -> ********** */

/**
 * Convert JsonObjectString to JSONObject.
 *
 * @param jsonObjectString Json Object String.
 * @return JSONObject of given JsonObjectString.
 */
@JvmName("jsonObjectString2JSONObject1")
fun jsonObjectString2JSONObject(jsonObjectString: String) = jsonObjectString.jsonObjectString2JSONObject()
fun String.jsonObjectString2JSONObject() = try {
    JSONObject(this)
}
catch (e: JSONException) {
    logE(TAG, t = e)
    null
}

/**
 * Convert JsonArrayString to JSONArray.
 *
 * @param jsonArrayString Json Array String.
 * @return JSONArray of given JsonArrayString.
 */
@JvmName("jsonArrayString2JSONArray1")
fun jsonArrayString2JSONArray(jsonArrayString: String) = jsonArrayString.jsonArrayString2JSONArray()
fun String.jsonArrayString2JSONArray() = try {
    JSONArray(this)
}
catch (e: JSONException) {
    logE(TAG, t = e)
    null
}

/**
 * Convert JsonString to Any object according to given Class type.
 *
 * @param jsonString Json string.
 * @return Object of Class T.
 */
@JvmName("jsonString2Any1")
inline fun <reified T> jsonString2Any(jsonString: String) = jsonString.jsonString2Any<T>()
inline fun <reified T> String.jsonString2Any() = this.jsonString2Any(T::class.java)

/**
 * Convert JsonString to Any object according to given Class type.
 *
 * @param jsonString Json string.
 * @param cls Class type to parse the JsonString.
 * @return Object of Class T.
 */
@JvmName("jsonString2Any1")
fun <T> jsonString2Any(jsonString: String, cls: Class<T>?) = jsonString.jsonString2Any(cls)
fun <T> String.jsonString2Any(cls: Class<T>?) = try {
    Gson().fromJson(this, cls) ?: null
}
catch (e: JsonSyntaxException) {
    logE(TAG, t = e)
    null
}

/**
 * Convert JsonString to Map<String, V>.
 *
 * @param jsonString Json string.
 * @return Map with value type V.
 */
@JvmName("jsonString2Map1")
fun <V> jsonString2Map(jsonString: String) = jsonString.jsonString2Map<V>()
fun <V> String.jsonString2Map() = try {
    Gson().fromJson<Map<String, V?>>(this, object : TypeToken<Map<String, V?>>() {}.type) ?: null
}
catch (e: JsonSyntaxException) {
    logE(TAG, t = e)
    null
}

/**
 * Convert JsonString to List<T>.
 *
 * @param jsonString Json string.
 * @return List with type T.
 */
@JvmName("jsonString2List1")
inline fun <reified T> jsonString2List(jsonString: String) = jsonString.jsonString2List<T>()
inline fun <reified T> String.jsonString2List() = this.jsonString2List(T::class.java)

/**
 * Convert JsonString to List<T>.
 *
 * @param jsonString Json string.
 * @param cls Class type to parse the JsonString.
 * @return List with type T.
 */
@JvmName("jsonString2List1")
fun <T> jsonString2List(jsonString: String, cls: Class<T>?) = jsonString.jsonString2List(cls)
fun <T> String.jsonString2List(cls: Class<T>?): List<T?>? {
    val jsonArray = try {
        JsonParser.parseString(this).asJsonArray ?: null
    }
    catch (e: IllegalStateException) {
        logE(TAG, t = e)
        null
    }

    jsonArray ?: return null
    val list = ArrayList<T?>()
    for (jsonElement in jsonArray) {
        val item = try {
            Gson().fromJson(jsonElement, cls)
        }
        catch (e: JsonSyntaxException) {
            logE(TAG, t = e)
            null
        }
        list.add(item)
    }
    return list
}

/** ********** -> JsonString ********** */

/**
 * Convert Object to JsonString.
 *
 * @param any Custom Object.
 * @return Json string.
 */
@JvmName("any2JsonString1")
fun any2JsonString(any: Any?) = any.any2JsonString()
fun Any?.any2JsonString() = Gson().toJson(this) ?: ""

/**
 * Convert Object to Console.
 *
 * @param any Custom Object.
 */
@JvmName("any2Console1")
fun any2Console(any: Any?) = any.any2Console()
fun Any?.any2Console() = Gson().toJson(this, System.out)
