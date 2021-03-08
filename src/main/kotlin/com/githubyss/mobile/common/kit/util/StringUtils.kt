package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.content.res.Resources.NotFoundException
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import kotlin.experimental.and


/**
 * StringUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 11:48:47
 */
object StringUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = StringUtils::class.simpleName ?: "simpleName is null"
    
    private val TYPES = arrayOf("int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte")
    private val HEX_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Getter ********** ********** */
    
    /**
     * Return the length of string.
     *
     * @param s The string.
     * @return The length of string.
     */
    fun length(s: CharSequence?): Int {
        return s?.length ?: 0
    }
    
    /**
     * Return the string value associated with a particular resource ID.
     *
     * @param id         The desired resource identifier.
     * @param formatArgs The format arguments that will be used for substitution.
     * @return The string value associated with a particular resource ID.
     */
    fun getString(context: Context = ComkitApplicationConfig.getApp(), @StringRes id: Int, vararg formatArgs: Any? = emptyArray()): String {
        return try {
            if (formatArgs.isEmpty()) {
                context.resources.getString(id)
            } else {
                context.resources.getString(id, formatArgs)
            }
        } catch (ignore: NotFoundException) {
            ""
        }
    }
    
    /**
     * Return the string array associated with a particular resource ID.
     *
     * @param id The desired resource identifier.
     * @return The string array associated with the resource.
     */
    fun getStringArray(context: Context = ComkitApplicationConfig.getApp(), @ArrayRes id: Int): Array<String?>? {
        return try {
            context.resources?.getStringArray(id)
        } catch (ignore: NotFoundException) {
            arrayOfNulls(0)
        }
    }
    
    /** ********** ********** Checker ********** ********** */
    
    /**
     * Return whether the string is null or 0-length.
     *
     * @param s The string.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isEmpty(s: CharSequence?): Boolean {
        return s == null || s.isEmpty()
    }
    
    /**
     * Return whether the string is null or whitespace.
     *
     * @param s The string.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isTrimEmpty(s: String?): Boolean {
        return s == null || s.trim().isEmpty()
    }
    
    /**
     * Return whether the string is null or white space.
     *
     * @param s The string.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun isSpace(s: String?): Boolean {
        if (s == null || s.isEmpty()) return true
        
        for (element in s) {
            if (!Character.isWhitespace(element)) {
                return false
            }
        }
        return true
    }
    
    /**
     * Return whether string1 is equals to string2.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun equals(s1: CharSequence?, s2: CharSequence?): Boolean {
        if (s1 === s2) return true
        
        val length: Int
        if (s1 != null && s2 != null) {
            length = s1.length
            if (s1.length == s2.length) {
                if (s1 is String && s2 is String) {
                    return s1 == s2
                } else {
                    for (i in 0 until length) {
                        if (s1[i] != s2[i]) return false
                    }
                    return true
                }
            }
        }
        return false
    }
    
    /**
     * Return whether string1 is equals to string2, ignoring case considerations..
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return {@code true}: yes, {@code false}: no.
     */
    fun equalsIgnoreCase(s1: String?, s2: String?): Boolean {
        return s1?.equals(s2, ignoreCase = true) ?: (s2 == null)
    }
    
    /** ********** ********** Converter ********** ********** */
    
    /**
     * Return {@code ""} if string equals null.
     *
     * @param s The string.
     * @return {@code ""} if string equals null.
     */
    fun null2Length0(s: String?): String {
        return s ?: ""
    }
    
    /**
     * Object to string.
     * e.g. object2String({name=value, name=value, name=value}) returns "Object{ name=value, name=value, name=value }".
     *
     * @param object The Object.
     * @return The object string.
     */
    fun <T : Any> object2String(`object`: T?): String {
        if (`object` == null) return "Object{ object is null }"
        if (`object`.toString().startsWith(`object`.javaClass.name + "@")) {
            val stringBuilder = StringBuilder(`object`.javaClass.simpleName + "Object{ ")
            val fields = `object`.javaClass.declaredFields
            for (field in fields) {
                field.isAccessible = true
                var flag = false
                for (type in TYPES) {
                    if (field.type.name.equals(type, ignoreCase = true)) {
                        flag = true
                        var value: Any? = null
                        try {
                            value = field.get(`object`)
                        } catch (e: IllegalAccessException) {
                            LogcatUtils.e(TAG, e)
                        } finally {
                            stringBuilder.append(String.format("%s=%s, ", field.name, value?.toString() ?: "null"))
                            break
                        }
                    }
                }
                if (!flag) {
                    stringBuilder.append(String.format("%s=%s, ", field.name, "Object"))
                }
            }
            return stringBuilder.replace(stringBuilder.length - 2, stringBuilder.length - 1, " }").toString()
        } else {
            return `object`.toString()
        }
    }
    
    /**
     * Array to string.
     * e.g. object2String([item, item, item]) returns "Array[ item, item, item ]".
     *
     * @param array The array.
     * @return The array string.
     */
    fun array2String(array: Array<*>?): String {
        if (array == null) return "Array[ array is null ]"
        val stringBuilder = StringBuilder()
        val arraySize = array.size
        for (idx in 0 until arraySize) {
            if (idx == 0) {
                stringBuilder.append("Array[ ")
            }
            stringBuilder.append(array[idx].toString())
            if (idx == (arraySize - 1)) {
                stringBuilder.append(" ]")
            } else {
                stringBuilder.append(", ")
            }
        }
        return stringBuilder.toString()
    }
    
    /**
     * List to string.
     * e.g.
     *
     * @param list The list.
     * @return The list string.
     */
    fun list2String(list: List<*>?): String {
        if (list == null) return "List[ list is null ]"
        return list.toString()
    }
    
    /**
     * Bytes to hex string.
     * e.g. bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns "00A8".
     *
     * @param bytes The bytes.
     * @return The hex string.
     */
    fun bytes2HexString(bytes: ByteArray?): String {
        if (bytes == null || bytes.isEmpty()) return ""
        val bytesSize = bytes.size
        val chars = CharArray(bytesSize shl 1)
        var j = 0
        for (i in 0 until bytesSize) {
            chars[j++] = HEX_DIGITS[(bytes[i].toInt() shr 4) and 0x0f]
            chars[j++] = HEX_DIGITS[(bytes[i] and 0x0f).toInt()]
        }
        return String(chars)
    }
    
    /**
     * Set the first letter of string upper.
     *
     * @param s The string.
     * @return The string with first letter upper.
     */
    fun uppercaseFirstLetter(s: String?): String {
        if (isSpace(s)) return ""
        // 首字符可能会是数字，所以不能直接用 Character.isUpperCase 判断
        if (!Character.isLowerCase(s?.get(0) ?: return "")) return s
        return (s[0].toInt() - 32).toChar().toString() + s.substring(1)
    }
    
    /**
     * Set the first letter of string lower.
     *
     * @param s The string.
     * @return The string with first letter lower.
     */
    fun lowercaseFirstLetter(s: String?): String {
        if (isSpace(s)) return ""
        // 首字符可能会是数字，所以不能直接用 Character.isLowerCase 判断
        if (!Character.isUpperCase(s?.get(0) ?: return "")) return s
        return (s[0].toInt() + 32).toChar().toString() + s.substring(1)
    }
    
    /**
     * Reverse the string.
     *
     * @param s The string.
     * @return The reverse string.
     */
    fun reverse(s: String?): String {
        if (s == null) return ""
        
        val len = s.length
        if (len <= 1) return s
        
        val mid = len shr 1
        val chars = s.toCharArray()
        var c: Char
        for (i in 0 until mid) {
            c = chars[i]
            chars[i] = chars[len - i - 1]
            chars[len - i - 1] = c
        }
        return String(chars)
    }
    
    /**
     * Convert string to DBC.
     *
     * @param s The string.
     * @return The DBC string.
     */
    fun toDBC(s: String?): String {
        if (isSpace(s)) return ""
        
        val chars = s?.toCharArray() ?: return ""
        for (i in chars.indices) {
            when {
                chars[i].toInt() == 12288 -> {
                    chars[i] = ' '
                }
                chars[i].toInt() in 65281..65374 -> {
                    chars[i] = (chars[i] - 65248)
                }
                else -> {
                    chars[i] = chars[i]
                }
            }
        }
        return String(chars)
    }
    
    /**
     * Convert string to SBC.
     *
     * @param s The string.
     * @return The SBC string.
     */
    fun toSBC(s: String?): String {
        if (isSpace(s)) return ""
        
        val chars = s?.toCharArray() ?: return ""
        for (i in chars.indices) {
            when {
                chars[i] == ' ' -> {
                    chars[i] = 12288.toChar()
                }
                chars[i].toInt() in 33..126 -> {
                    chars[i] = (chars[i] + 65248)
                }
                else -> {
                    chars[i] = chars[i]
                }
            }
        }
        return String(chars)
    }
}
