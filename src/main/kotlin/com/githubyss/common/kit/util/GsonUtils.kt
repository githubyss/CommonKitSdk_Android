package com.githubyss.common.kit.util

import android.content.Context
import com.githubyss.common.base.application.BaseApplicationHolder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.Reader
import java.lang.reflect.Type


/**
 * GsonUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/12/07 23:37:12
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "GsonUtils"

private val GSON = createGson(true)
private val GSON_NO_NULLS = createGson(false)


/** ****************************** Functions ****************************** */

/** ******************** Creator ******************** */

/**
 * Create a pre-configured [Gson] instance.
 *
 * @param serializeNulls determines if nulls will be serialized.
 * @return [Gson] instance.
 */
private fun createGson(serializeNulls: Boolean): Gson {
    val builder = GsonBuilder()
    if (serializeNulls) builder.serializeNulls()
    return builder.create()
}

/** ******************** Getter ******************** */

/** ********** getGson ********** */

/**
 * Gets pre-configured [Gson] instance.
 *
 * @param serializeNulls Determines if nulls will be serialized.
 * @return [Gson] instance.
 */
@JvmOverloads
fun getGson(serializeNulls: Boolean = true) =
    when {
        serializeNulls -> GSON_NO_NULLS
        else -> GSON
    }


/** ******************** Converter ******************** */

/** ********** Object -> Console ********** */

/**
 * Convert Object to Console.
 *
 * @param src Custom Object.
 */
fun object2Console(src: Any?) = Gson().toJson(src, System.out)

@JvmName("object2Console1")
fun Any?.object2Console() = object2Console(this)


/** ********** Object -> Json String ********** */

/**
 * Serializes an object into json string.
 *
 * @param src The object to serialize.
 * @param typeOfSrc The specific genericized type of src.
 * @param includeNulls Determines if nulls will be included.
 * @return object serialized into json string.
 */
@JvmOverloads
fun object2JsonString(src: Any?, typeOfSrc: Type? = null, includeNulls: Boolean = true) =
    when (src) {
        null -> ""
        else -> try {
            when (typeOfSrc) {
                null -> {
                    when {
                        includeNulls -> GSON.toJson(src)
                        else -> GSON_NO_NULLS.toJson(src)
                    }
                }
                else -> {
                    when {
                        includeNulls -> GSON.toJson(src, typeOfSrc)
                        else -> GSON_NO_NULLS.toJson(src, typeOfSrc)
                    }
                }
            } ?: ""
        }
        catch (e: IOException) {
            logE(TAG, "object2JsonString", e)
            ""
        }
    }

@JvmName("object2JsonString1")
fun Any?.object2JsonString(typeOfSrc: Type? = null, includeNulls: Boolean = true) = object2JsonString(this, typeOfSrc, includeNulls)

/** ********** String -> Object ********** */

/**
 * Converts [String] to given type.
 *
 * @param json The json to convert.
 * @param type Type json will be converted to.
 * @return instance of type
 */
fun <T> fromJson(json: String?, type: Class<T>?): T {
    return GSON.fromJson(json, type)
}

/**
 * Converts [String] to given type.
 *
 * @param json the json to convert.
 * @param type type type json will be converted to.
 * @return instance of type
 */
fun <T> fromJson(json: String?, type: Type?): T {
    return GSON.fromJson(json, type)
}

/**
 * Converts [Reader] to given type.
 *
 * @param reader the reader to convert.
 * @param type   type type json will be converted to.
 * @return instance of type
 */
fun <T> fromJson(reader: Reader?, type: Class<T>?): T {
    return GSON.fromJson(reader, type)
}

/**
 * Converts [Reader] to given type.
 *
 * @param reader the reader to convert.
 * @param type   type type json will be converted to.
 * @return instance of type
 */
fun <T> fromJson(reader: Reader?, type: Type?): T {
    return GSON.fromJson(reader, type)
}


/** ******************** Getter ******************** */

/** ********** getMap ********** */

@JvmOverloads
fun <V> getMapFromAssets(assetsFilePath: String?, context: Context? = BaseApplicationHolder.getApp()) = context.getMapFromAssets<V>(assetsFilePath)
fun <V> Context?.getMapFromAssets(assetsFilePath: String?) = this.getJsonStringFromAssets(assetsFilePath)?.jsonString2Map<V?>()

/** ********** getList ********** */


/** ******************** Converter ******************** */

/** ********** String -> Any ********** */

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
