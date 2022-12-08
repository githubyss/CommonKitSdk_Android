package com.githubyss.common.kit.util

import android.content.Context
import com.githubyss.common.base.application.BaseApplicationHolder
import com.githubyss.common.kit.constant.JSON_STRING_START_ERROR
import com.githubyss.common.kit.enumeration.DataType
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

/** ********** getBoolean ********** */

@JvmOverloads
fun getBooleanFromJSONObject(jsonObject: JSONObject?, key: String?, defaultValue: Boolean = false) = getValueByType(jsonObject, key, defaultValue, DataType.BOOLEAN)
fun JSONObject?.boolean(key: String?, defaultValue: Boolean = false) = getBooleanFromJSONObject(this, key, defaultValue)

@JvmOverloads
fun getBooleanFromJsonString(jsonString: String?, key: String?, defaultValue: Boolean = false) = getValueByType(jsonString, key, defaultValue, DataType.BOOLEAN)
fun String?.boolean(key: String?, defaultValue: Boolean = false) = getBooleanFromJsonString(this, key, defaultValue)

@JvmOverloads
fun getBooleanFromJSONArray(jsonArray: JSONArray?, idx: Int?, defaultValue: Boolean = false) = getValueByType(jsonArray, idx, defaultValue, DataType.BOOLEAN)
fun JSONArray?.boolean(idx: Int?, defaultValue: Boolean = false) = getBooleanFromJSONArray(this, idx, defaultValue)

@JvmOverloads
fun getBooleanFromJsonString(jsonString: String?, idx: Int?, defaultValue: Boolean = false) = getValueByType(jsonString, idx, defaultValue, DataType.BOOLEAN)
fun String?.boolean(idx: Int?, defaultValue: Boolean = false) = getBooleanFromJsonString(this, idx, defaultValue)

/** ********** getInt ********** */

@JvmOverloads
fun getIntFromJSONObject(jsonObject: JSONObject?, key: String?, defaultValue: Int = -1) = getValueByType(jsonObject, key, defaultValue, DataType.INT)
fun JSONObject?.int(key: String?, defaultValue: Int = -1) = getIntFromJSONObject(this, key, defaultValue)

@JvmOverloads
fun getIntFromJsonString(jsonString: String?, key: String?, defaultValue: Int = -1) = getValueByType(jsonString, key, defaultValue, DataType.INT)
fun String?.int(key: String?, defaultValue: Int = -1) = getIntFromJsonString(this, key, defaultValue)

@JvmOverloads
fun getIntFromJSONArray(jsonArray: JSONArray?, idx: Int?, defaultValue: Int = -1) = getValueByType(jsonArray, idx, defaultValue, DataType.INT)
fun JSONArray?.int(idx: Int?, defaultValue: Int = -1) = getIntFromJSONArray(this, idx, defaultValue)

@JvmOverloads
fun getIntFromJsonString(jsonString: String?, idx: Int?, defaultValue: Int = -1) = getValueByType(jsonString, idx, defaultValue, DataType.INT)
fun String?.int(idx: Int?, defaultValue: Int = -1) = getIntFromJsonString(this, idx, defaultValue)

/** ********** getLong ********** */

@JvmOverloads
fun getLongFromJSONObject(jsonObject: JSONObject?, key: String?, defaultValue: Long = -1) = getValueByType(jsonObject, key, defaultValue, DataType.LONG)
fun JSONObject?.long(key: String?, defaultValue: Long = -1) = getLongFromJSONObject(this, key, defaultValue)

@JvmOverloads
fun getLongFromJsonString(jsonString: String?, key: String?, defaultValue: Long = -1) = getValueByType(jsonString, key, defaultValue, DataType.LONG)
fun String?.long(key: String?, defaultValue: Long = -1) = getLongFromJsonString(this, key, defaultValue)

@JvmOverloads
fun getLongFromJSONArray(jsonArray: JSONArray?, idx: Int?, defaultValue: Long = -1) = getValueByType(jsonArray, idx, defaultValue, DataType.LONG)
fun JSONArray?.long(idx: Int?, defaultValue: Long = -1) = getLongFromJSONArray(this, idx, defaultValue)

@JvmOverloads
fun getLongFromJsonString(jsonString: String?, idx: Int?, defaultValue: Long = -1) = getValueByType(jsonString, idx, defaultValue, DataType.LONG)
fun String?.long(idx: Int?, defaultValue: Long = -1) = getLongFromJsonString(this, idx, defaultValue)

/** ********** getDouble ********** */

@JvmOverloads
fun getDoubleFromJSONObject(jsonObject: JSONObject?, key: String?, defaultValue: Double = -1.0) = getValueByType(jsonObject, key, defaultValue, DataType.DOUBLE)
fun JSONObject?.double(key: String?, defaultValue: Double = -1.0) = getDoubleFromJSONObject(this, key, defaultValue)

@JvmOverloads
fun getDoubleFromJsonString(jsonString: String?, key: String?, defaultValue: Double = -1.0) = getValueByType(jsonString, key, defaultValue, DataType.DOUBLE)
fun String?.double(key: String?, defaultValue: Double = -1.0) = getDoubleFromJsonString(this, key, defaultValue)

@JvmOverloads
fun getDoubleFromJSONArray(jsonArray: JSONArray?, idx: Int?, defaultValue: Double = -1.0) = getValueByType(jsonArray, idx, defaultValue, DataType.DOUBLE)
fun JSONArray?.double(idx: Int?, defaultValue: Double = -1.0) = getDoubleFromJSONArray(this, idx, defaultValue)

@JvmOverloads
fun getDoubleFromJsonString(jsonString: String?, idx: Int?, defaultValue: Double = -1.0) = getValueByType(jsonString, idx, defaultValue, DataType.DOUBLE)
fun String?.double(idx: Int?, defaultValue: Double = -1.0) = getDoubleFromJsonString(this, idx, defaultValue)

/** ********** getString ********** */

@JvmOverloads
fun getStringFromJSONObject(jsonObject: JSONObject?, key: String?, defaultValue: String = "") = getValueByType(jsonObject, key, defaultValue, DataType.STRING)
fun JSONObject?.string(key: String?, defaultValue: String = "") = getStringFromJSONObject(this, key, defaultValue)

@JvmOverloads
fun getStringFromJsonString(jsonString: String?, key: String?, defaultValue: String = "") = getValueByType(jsonString, key, defaultValue, DataType.STRING)
fun String?.string(key: String?, defaultValue: String = "") = getStringFromJsonString(this, key, defaultValue)

@JvmOverloads
fun getStringFromJSONArray(jsonArray: JSONArray?, idx: Int?, defaultValue: String = "") = getValueByType(jsonArray, idx, defaultValue, DataType.STRING)
fun JSONArray?.string(idx: Int?, defaultValue: String = "") = getStringFromJSONArray(this, idx, defaultValue)

@JvmOverloads
fun getStringFromJsonString(jsonString: String?, idx: Int?, defaultValue: String = "") = getValueByType(jsonString, idx, defaultValue, DataType.STRING)
fun String?.string(idx: Int?, defaultValue: String = "") = getStringFromJsonString(this, idx, defaultValue)

/** ********** getJSONObject ********** */

@JvmOverloads
fun getJSONObjectFromJSONObject(jsonObject: JSONObject?, key: String?, defaultValue: JSONObject = JSONObject()) = getValueByType(jsonObject, key, defaultValue, DataType.JSON_OBJECT)
fun JSONObject?.jsonObject(key: String?, defaultValue: JSONObject = JSONObject()) = getJSONObjectFromJSONObject(this, key, defaultValue)

@JvmOverloads
fun getJSONObjectFromJsonString(jsonString: String?, key: String?, defaultValue: JSONObject = JSONObject()) = getValueByType(jsonString, key, defaultValue, DataType.JSON_OBJECT)
fun String?.jsonObject(key: String?, defaultValue: JSONObject = JSONObject()) = getJSONObjectFromJsonString(this, key, defaultValue)

@JvmOverloads
fun getJSONObjectFromJSONArray(jsonArray: JSONArray?, idx: Int?, defaultValue: JSONObject = JSONObject()) = getValueByType(jsonArray, idx, defaultValue, DataType.JSON_OBJECT)
fun JSONArray?.jsonObject(idx: Int?, defaultValue: JSONObject = JSONObject()) = getJSONObjectFromJSONArray(this, idx, defaultValue)

@JvmOverloads
fun getJSONObjectFromJsonString(jsonString: String?, idx: Int?, defaultValue: JSONObject = JSONObject()) = getValueByType(jsonString, idx, defaultValue, DataType.JSON_OBJECT)
fun String?.jsonObject(idx: Int?, defaultValue: JSONObject = JSONObject()) = getJSONObjectFromJsonString(this, idx, defaultValue)

/**
 * Get the JSONObject from file in assets dir.
 *
 * @param jsonAssetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
 * @param context The context.
 * @return JSONObject if file content is JSONObject String, otherwise null.
 */
@JvmOverloads
fun getJSONObjectFromAssets(jsonAssetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()) = string2JSONObject(getStringFromAssets(jsonAssetsFilePath, context))
fun Context?.getJSONObjectFromAssets(jsonAssetsFilePath: String?) = getJSONObjectFromAssets(jsonAssetsFilePath, this)

/** ********** getJSONArray ********** */

@JvmOverloads
fun getJSONArrayFromJSONObject(jsonObject: JSONObject?, key: String?, defaultValue: JSONArray = JSONArray()) = getValueByType(jsonObject, key, defaultValue, DataType.JSON_ARRAY)
fun JSONObject?.jsonArray(key: String?, defaultValue: JSONArray = JSONArray()) = getJSONArrayFromJSONObject(this, key, defaultValue)

@JvmOverloads
fun getJSONArrayFromJsonString(jsonString: String?, key: String?, defaultValue: JSONArray = JSONArray()) = getValueByType(jsonString, key, defaultValue, DataType.JSON_ARRAY)
fun String?.jsonArray(key: String?, defaultValue: JSONArray = JSONArray()) = getJSONArrayFromJsonString(this, key, defaultValue)

@JvmOverloads
fun getJSONArrayFromJSONArray(jsonArray: JSONArray?, idx: Int?, defaultValue: JSONArray = JSONArray()) = getValueByType(jsonArray, idx, defaultValue, DataType.JSON_ARRAY)
fun JSONArray?.jsonArray(idx: Int?, defaultValue: JSONArray = JSONArray()) = getJSONArrayFromJSONArray(this, idx, defaultValue)

@JvmOverloads
fun getJSONArrayFromJsonString(jsonString: String?, idx: Int?, defaultValue: JSONArray = JSONArray()) = getValueByType(jsonString, idx, defaultValue, DataType.JSON_ARRAY)
fun String?.jsonArray(idx: Int?, defaultValue: JSONArray = JSONArray()) = getJSONArrayFromJsonString(this, idx, defaultValue)

/**
 * Get the JSONArray from file in assets dir.
 *
 * @param jsonAssetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
 * @param context The context.
 * @return JSONArray if file content is JSONArray String, otherwise null.
 */
@JvmOverloads
fun getJSONArrayFromAssets(jsonAssetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()) = string2JSONArray(getStringFromAssets(jsonAssetsFilePath, context))
fun Context?.getJSONArrayFromAssets(jsonAssetsFilePath: String?) = getJSONArrayFromAssets(jsonAssetsFilePath, this)

/** ********** getJsonString ********** */

/**
 * Get the Json String from file in assets dir.
 *
 * @param jsonAssetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
 * @param context The context.
 * @return Json String if file content is Json String, otherwise null.
 */
@JvmOverloads
fun getJsonStringFromAssets(jsonAssetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()): String {
    val string = getStringFromAssets(jsonAssetsFilePath, context)
    return when {
        isJsonString(string) -> string
        else -> ""
    }
}

fun Context?.getJsonStringFromAssets(jsonAssetsFilePath: String?) = getJsonStringFromAssets(jsonAssetsFilePath, this)


/**
 * Get the JSONObject String from file in assets dir.
 *
 * @param jsonAssetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
 * @param context The context.
 * @return JSONObject String if file content is JSONObject String, otherwise null.
 */
@JvmOverloads
fun getJSONObjectStringFromAssets(jsonAssetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()): String {
    val string = getStringFromAssets(jsonAssetsFilePath, context)
    return when {
        isJSONObjectString(string) -> string
        else -> ""
    }
}

fun Context?.getJSONObjectStringFromAssets(jsonAssetsFilePath: String?) = getJSONObjectStringFromAssets(jsonAssetsFilePath, this)


/**
 * Get the JSONArray String from file in assets dir.
 *
 * @param jsonAssetsFilePath The path of file in assets. e.g., it can be "json/net_resp/xxx.json" when xxx.json in dir [assets/json/net_resp/]
 * @param context The context.
 * @return JSONArray String if file content is JSONArray String, otherwise null.
 */
@JvmOverloads
fun getJSONArrayStringFromAssets(jsonAssetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()): String {
    val string = getStringFromAssets(jsonAssetsFilePath, context)
    return when {
        isJSONArrayString(string) -> string
        else -> ""
    }
}

fun Context?.getJSONArrayStringFromAssets(jsonAssetsFilePath: String?) = getJSONArrayStringFromAssets(jsonAssetsFilePath, this)


/** ********** getValueByType ********** */

/**
 * Parse value from JSONObject
 *
 * @param jsonObject The JSONObject to be parsed.
 * @param key The key of value to parse.
 * @param defaultValue The default value when parse failed.
 * @param type The value type to be parsed.
 * @return T value of key.
 */
private fun <T> getValueByType(jsonObject: JSONObject?, key: String?, defaultValue: T, type: Int) =
    when {
        jsonObject == null || key == null || key.isEmpty() -> defaultValue
        else -> try {
            val ret = when (type) {
                DataType.BOOLEAN -> jsonObject.optBoolean(key)
                DataType.INT -> jsonObject.optInt(key)
                DataType.LONG -> jsonObject.optLong(key)
                DataType.DOUBLE -> jsonObject.optDouble(key)
                DataType.STRING -> jsonObject.optString(key)
                DataType.JSON_OBJECT -> jsonObject.optJSONObject(key)
                DataType.JSON_ARRAY -> jsonObject.optJSONArray(key)
                else -> defaultValue
            }
            when (ret) {
                null -> defaultValue
                else -> ret as T
            }
        }
        catch (e: JSONException) {
            logE(TAG, "getValueByType", e)
            defaultValue
        }
    }

private inline fun <reified T> getValueByType(jsonObject: JSONObject?, key: String?, defaultValue: T) =
    when {
        jsonObject == null || key == null || key.isEmpty() -> defaultValue
        else -> try {
            val ret = when (T::class) {
                Boolean::class -> jsonObject.optBoolean(key)
                Int::class -> jsonObject.optInt(key)
                Long::class -> jsonObject.optLong(key)
                Double::class -> jsonObject.optDouble(key)
                String::class -> jsonObject.optString(key)
                JSONObject::class -> jsonObject.optJSONObject(key)
                JSONArray::class -> jsonObject.optJSONArray(key)
                else -> defaultValue
            }
            when (ret) {
                null -> defaultValue
                else -> ret as T
            }
        }
        catch (e: JSONException) {
            logE(TAG, "getValueByType", e)
            defaultValue
        }
    }

/**
 * Parse value from JSONObject String
 *
 * @param jsonString The JSONObject String to be parsed.
 * @param key The key of value to parse.
 * @param defaultValue The default value when parse failed.
 * @param type The value type to be parsed.
 * @return T value of key.
 */
private fun <T> getValueByType(jsonString: String?, key: String?, defaultValue: T, type: Int) =
    when {
        jsonString == null || jsonString.isEmpty() || key == null || key.isEmpty() -> defaultValue
        else -> try {
            when {
                jsonString.isJSONObjectString() -> getValueByType(JSONObject(jsonString), key, defaultValue, type)
                else -> defaultValue
            }
        }
        catch (e: JSONException) {
            logE(TAG, "getValueByType", e)
            defaultValue
        }
    }

private inline fun <reified T> getValueByType(jsonString: String?, key: String?, defaultValue: T) =
    when {
        jsonString == null || jsonString.isEmpty() || key == null || key.isEmpty() -> defaultValue
        else -> try {
            when {
                jsonString.isJSONObjectString() -> getValueByType<T>(JSONObject(jsonString), key, defaultValue)
                else -> defaultValue
            }
        }
        catch (e: JSONException) {
            logE(TAG, "getValueByType", e)
            defaultValue
        }
    }

/**
 * Parse value from JSONArray
 *
 * @param jsonArray The JSONArray to be parsed.
 * @param idx The index of value to parse.
 * @param defaultValue The default value when parse failed.
 * @param type The value type to be parsed.
 * @return T value in idx.
 */
private fun <T> getValueByType(jsonArray: JSONArray?, idx: Int?, defaultValue: T, type: Int) =
    when {
        jsonArray == null || idx == null || idx < 0 -> defaultValue
        else -> try {
            val ret = when (type) {
                DataType.BOOLEAN -> jsonArray.optBoolean(idx)
                DataType.INT -> jsonArray.optInt(idx)
                DataType.LONG -> jsonArray.optLong(idx)
                DataType.DOUBLE -> jsonArray.optDouble(idx)
                DataType.STRING -> jsonArray.optString(idx)
                DataType.JSON_OBJECT -> jsonArray.optJSONObject(idx)
                DataType.JSON_ARRAY -> jsonArray.optJSONArray(idx)
                else -> defaultValue
            }
            when (ret) {
                null -> defaultValue
                else -> ret as T
            }
        }
        catch (e: JSONException) {
            logE(TAG, "getValueByType", e)
            defaultValue
        }
    }

private inline fun <reified T> getValueByType(jsonArray: JSONArray?, idx: Int?, defaultValue: T) =
    when {
        jsonArray == null || idx == null || idx < 0 -> defaultValue
        else -> try {
            val ret = when (T::class) {
                Boolean::class -> jsonArray.optBoolean(idx)
                Int::class -> jsonArray.optInt(idx)
                Long::class -> jsonArray.optLong(idx)
                Double::class -> jsonArray.optDouble(idx)
                String::class -> jsonArray.optString(idx)
                JSONObject::class -> jsonArray.optJSONObject(idx)
                JSONArray::class -> jsonArray.optJSONArray(idx)
                else -> defaultValue
            }
            when (ret) {
                null -> defaultValue
                else -> ret as T
            }
        }
        catch (e: JSONException) {
            logE(TAG, "getValueByType", e)
            defaultValue
        }
    }

/**
 * Parse value from JSONArray String
 *
 * @param jsonString The JSONArray String to be parsed.
 * @param idx The index of value to parse.
 * @param defaultValue The default value when parse failed.
 * @param type The value type to be parsed.
 * @return T value in idx.
 */
private fun <T> getValueByType(jsonString: String?, idx: Int?, defaultValue: T, type: Int) =
    when {
        jsonString == null || jsonString.isEmpty() || idx == null || idx < 0 -> defaultValue
        else -> try {
            when {
                jsonString.isJSONArrayString() -> getValueByType(JSONArray(jsonString), idx, defaultValue, type)
                else -> defaultValue
            }
        }
        catch (e: JSONException) {
            logE(TAG, "getValueByType", e)
            defaultValue
        }
    }

private inline fun <reified T> getValueByType(jsonString: String?, idx: Int?, defaultValue: T) =
    when {
        jsonString == null || jsonString.isEmpty() || idx == null || idx < 0 -> defaultValue
        else -> try {
            when {
                jsonString.isJSONArrayString() -> getValueByType<T>(JSONArray(jsonString), idx, defaultValue)
                else -> defaultValue
            }
        }
        catch (e: JSONException) {
            logE(TAG, "getValueByType", e)
            defaultValue
        }
    }


/** ******************** Converter ******************** */

/** ********** String -> JSON ********** */

/**
 * Convert String to JSONObject.
 *
 * @param string Json Object String.
 * @return JSONObject of given String.
 */
fun string2JSONObject(string: String?) = string2JSON<JSONObject>(string)

@JvmName("string2JSONObject1")
fun String?.string2JSONObject() = string2JSONObject(this)


/**
 * Convert String to JSONArray.
 *
 * @param string Json Array String.
 * @return JSONArray of given String.
 */
fun string2JSONArray(string: String?) = string2JSON<JSONArray>(string)

@JvmName("string2JSONArray1")
fun String?.string2JSONArray() = string2JSONArray(this)


/**
 * Convert String to JSONObject or JSONArray.
 *
 * @param string Json String.
 * @param type The value type to be parsed.
 * @return JSONObject or JSONArray of given String.
 */
fun <T> string2JSON(string: String?, type: Int) =
    when {
        string == null || string.isEmpty() -> null
        else -> try {
            when (type) {
                DataType.JSON_OBJECT -> JSONObject(string)
                DataType.JSON_ARRAY -> JSONArray(string)
                else -> null
            } as T
        }
        catch (e: JSONException) {
            logE("TAG", "string2JSON", e)
            null
        }
    }

@JvmName("string2JSON1")
fun <T> String?.string2JSON(type: Int) = string2JSON<T>(this, type)


inline fun <reified T> string2JSON(string: String?) =
    when {
        string == null || string.isEmpty() -> null
        else -> try {
            when (T::class) {
                JSONObject::class -> JSONObject(string)
                JSONArray::class -> JSONArray(string)
                else -> null
            } as T

            // when {
            //     JSONObject() is T -> JSONObject(jsonString)
            //     JSONArray() is T -> JSONArray(jsonString)
            //     else -> null
            // } as T
        }
        catch (e: JSONException) {
            logE("TAG", "string2JSON", e)
            null
        }
    }

@JvmName("string2JSON1")
inline fun <reified T> String?.string2JSON() = string2JSON<T>(this)


/** ********** JSON -> String ********** */

/**
 * Convert JSONObject to String.
 *
 * @param jsonObject JSONObject.
 * @return String of given JSONObject.
 */
fun jsonObject2String(jsonObject: JSONObject?) = json2String<JSONObject>(jsonObject)

@JvmName("jsonObject2String1")
fun JSONObject?.jsonObject2String() = jsonObject2String(this)


/**
 * Convert JSONArray to String.
 *
 * @param jsonArray JSONArray.
 * @return String of given JSONArray.
 */
fun jsonArray2String(jsonArray: JSONArray?) = json2String<JSONArray>(jsonArray)

@JvmName("jsonArray2String1")
fun JSONArray?.jsonArray2String() = jsonArray2String(this)


/**
 * Convert JSONObject or JSONArray to String.
 *
 * @param json JSONObject or JSONArray.
 * @param type The value type to be parsed.
 * @return String of given JSONObject or JSONArray.
 */
fun <T> json2String(json: T?, type: Int) =
    when (json) {
        null -> ""
        else -> try {
            when (type) {
                DataType.JSON_OBJECT,
                DataType.JSON_ARRAY -> json.toString()
                else -> ""
            }

        }
        catch (e: JSONException) {
            logE("TAG", "json2String", e)
            ""
        }
    }

@JvmName("json2String1")
fun <T> T?.json2String(type: Int) = json2String<T>(this, type)


inline fun <reified T> json2String(json: T?) =
    when (json) {
        null -> ""
        else -> try {
            when (T::class) {
                JSONObject::class,
                JSONArray::class -> json.toString()
                else -> ""
            }

        }
        catch (e: JSONException) {
            logE("TAG", "json2String", e)
            ""
        }
    }

@JvmName("json2String1")
inline fun <reified T> T?.json2String() = json2String<T>(this)


/** ******************** Formatter ******************** */

@JvmOverloads
fun formatJson(string: String?, indentSpaces: Int = 4): String {
    when {
        string == null || string.isEmpty() -> return ""
        else -> {
            try {
                for (idx in string.indices) {
                    val c = string[idx]
                    return when {
                        c == '{' -> JSONObject(string).toString(indentSpaces)
                        c == '[' -> JSONArray(string).toString(indentSpaces)
                        !Character.isWhitespace(c) -> throw Exception(JSON_STRING_START_ERROR)
                        else -> throw Exception(JSON_STRING_START_ERROR)
                    }
                }
            }
            catch (e: JSONException) {
                logE(TAG, t = e)
                return ""
            }

            return string
        }
    }
}

@JvmName("formatJson1")
@JvmOverloads
fun String?.formatJson(indentSpaces: Int = 4) = formatJson(this, indentSpaces)


/** ******************** Checker ******************** */

/**
 * Check input string whether it is yield a JSON.
 *
 * @param string The string to be checked.
 * @return `true` if input string is yield a JSON.
 */
fun isJsonString(string: String?) = isJSONObjectString(string) || isJSONArrayString(string)

@JvmName("isJsonString1")
fun String?.isJsonString() = isJsonString(this)

// fun String?.isJsonString() =
// when {
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


/**
 * Check input string whether it is yield a JSONObject.
 *
 * @param string The string to be checked.
 * @return `true` if input string is yield a JSONObject.
 */
fun isJSONObjectString(string: String?) =
    when {
        string == null || string.isEmpty() -> false
        else -> try {
            JSONObject(string)
            true
        }
        catch (e: JSONException) {
            logE(TAG, t = e)
            false
        }
    }

@JvmName("isJSONObjectString1")
fun String?.isJSONObjectString() = isJSONObjectString(this)


/**
 * Check input string whether it is yield a JSONArray.
 *
 * @param string The string to be checked.
 * @return `true` if input string is yield a JSONArray.
 */
fun isJSONArrayString(string: String?) =
    when {
        string == null || string.isEmpty() -> false
        else -> try {
            JSONArray(string)
            true
        }
        catch (e: JSONException) {
            logE(TAG, t = e)
            false
        }
    }

@JvmName("isJSONArrayString1")
fun String?.isJSONArrayString() = isJSONArrayString(this)


/** ******************** Operator ******************** */

/** ********** JSONArray iterator ********** */

operator fun JSONArray.iterator() = (0 until length()).asSequence().map { get(it) as JSONObject }.iterator()
