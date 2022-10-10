package com.githubyss.common.kit.util

import android.content.Context
import android.content.res.Resources.NotFoundException
import androidx.annotation.ArrayRes
import com.githubyss.common.base.application.BaseApplicationHolder
import kotlin.experimental.and


/**
 * StringUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 11:48:47
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "StringUtils"

/**  */
private val TYPES = arrayOf("int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte")
private val HEX_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/**
 * Return the length of string.
 *
 * @param s The string.
 * @return The length of string.
 */
fun length(s: CharSequence?) = s.length
val CharSequence?.length get() = this?.length ?: 0


/**
 * Return the string array associated with a particular resource ID.
 *
 * @param id      The desired resource identifier.
 * @param context The context.
 * @return The string array associated with the resource.
 */
fun getStringArray(@ArrayRes id: Int, context: Context? = BaseApplicationHolder.getApp()): Array<String?>? {
    context ?: return null

    return try {
        getContextResources(context).getStringArray(id)
    }
    catch (ignore: NotFoundException) {
        arrayOfNulls(0)
    }
}

/** ******************** Checker ******************** */

/**
 * Return whether the string is null or 0-length.
 *
 * @param s The string.
 * @return {@code true}: yes, {@code false}: no.
 */
fun isEmpty(s: CharSequence?) = s?.isEmptyNonNull() ?: true
fun isNotEmpty(s: CharSequence?) = s?.isNotEmptyNonNull() ?: false

@JvmName("isEmptyNonNull_")
fun isEmptyNonNull(s: CharSequence) = s.isEmptyNonNull()
fun CharSequence.isEmptyNonNull() = this.isEmpty()

@JvmName("isNotEmptyNonNull_")
fun isNotEmptyNonNull(s: CharSequence) = s.isNotEmptyNonNull()
fun CharSequence.isNotEmptyNonNull() = this.isNotEmpty()

/**  */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(s: CharSequence?) = s.isEmptyOrNull()
fun CharSequence?.isEmptyOrNull() = this.isNullOrEmpty()

/**
 * Return whether the string is null or whitespace.
 *
 * @param s The string.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isTrimEmpty_")
fun isTrimEmpty(s: CharSequence?) = s?.isTrimEmpty()
fun CharSequence?.isTrimEmpty() = this?.isTrimEmptyNonNull() ?: true

@JvmName("isTrimEmptyNonNull_")
fun isTrimEmptyNonNull(s: CharSequence) = s.isTrimEmptyNonNull()
fun CharSequence.isTrimEmptyNonNull() = this.trim().isEmpty()

/**
 * Return whether the string is null or white space.
 *
 * @param s The string.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isSpace_")
fun isSpace(s: CharSequence?) = s.isSpace()
fun CharSequence?.isSpace() = this?.isSpaceNonNull() ?: true

@JvmName("isSpaceNonNull_")
fun isSpaceNonNull(s: CharSequence) = s.isSpaceNonNull()
fun CharSequence.isSpaceNonNull(): Boolean {
    if (this.isEmptyNonNull()) return true

    for (element in this) {
        if (!Character.isWhitespace(element)) {
            return false
        }
    }
    return true
}

/**  */
@JvmName("isNotSpaceNonNull_")
fun isNotSpaceNonNull(s: CharSequence) = s.isNotSpaceNonNull()
fun CharSequence.isNotSpaceNonNull() = !this.isSpaceNonNull()

/**
 * Return whether string1 is equals to string2.
 *
 * @param s1 The first string.
 * @param s2 The second string.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("equals_")
fun equals(s1: CharSequence?, s2: CharSequence?) = s1.equals(s2)
fun CharSequence?.equals(s: CharSequence?): Boolean {
    this ?: return s == null
    // if (this === null) return s === null

    if (this === s) return true

    val length: Int
    if (this != null && s != null) {
        length = this.length
        if (this.length == s.length) {
            if (this is String && s is String) {
                return this == s
            }
            else {
                for (i in 0 until length) {
                    if (this[i] != s[i]) return false
                }
                return true
            }
        }
    }
    return false
}

/**
 * Return whether string1 is equals to string2, strictly.
 *
 * @param s1 The first string.
 * @param s2 The second string.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("equals_")
fun equals(s1: String?, s2: String?) = s1.equals(s2)
fun String?.equals(s: String?) = this.equals(s, ignoreCase = false)

/**
 * Return whether string1 is equals to string2, ignoring case considerations.
 *
 * @param s1 The first string.
 * @param s2 The second string.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("equalsIgnoreCase_")
fun equalsIgnoreCase(s1: String?, s2: String?) = s1.equalsIgnoreCase(s2)
fun String?.equalsIgnoreCase(s: String?) = this.equals(s, ignoreCase = true)

/** ******************** Converter ******************** */

/**
 * Return {@code ""} if string equals null.
 *
 * @param s The string.
 * @return {@code ""} if string equals null.
 */
@JvmName("null2Length0_")
fun null2Length0(s: String?) = s.null2Length0()
fun String?.null2Length0() = this ?: ""

/**
 * Object to string.
 * e.g. object2String({name=value, name=value, name=value}) returns "Object {name=value, name=value, name=value}".
 *
 * @param object The Object.
 * @return The object string.
 */
@JvmName("object2String_")
fun <T : Any> object2String(`object`: T?) = `object`.object2String()
fun <T : Any> T?.object2String(): String {
    this ?: return "Object {object is null}"

    if (this.toString().startsWith(this.javaClass.name + "@")) {
        val stringBuilder = StringBuilder(this.javaClass.simpleName + "Object {")
        val fields = this.javaClass.declaredFields
        for (field in fields) {
            field.isAccessible = true
            var flag = false
            for (type in TYPES) {
                if (field.type.name.equals(type, ignoreCase = true)) {
                    flag = true
                    var value: Any? = null
                    try {
                        value = field.get(this)
                    }
                    catch (e: IllegalAccessException) {
                        logE(TAG, t = e)
                    }
                    finally {
                        stringBuilder.append(String.format("%s=%s, ", field.name, value?.toString() ?: "null"))
                        break
                    }
                }
            }
            if (!flag) {
                stringBuilder.append(String.format("%s=%s, ", field.name, "Object"))
            }
        }
        return stringBuilder.replace(stringBuilder.length - 2, stringBuilder.length - 1, "}")
            .toString()
    }
    else {
        return this.toString()
    }
}

/**
 * Array to string.
 * e.g. object2String([item, item, item]) returns "Array [item, item, item]".
 *
 * @param array The array.
 * @return The array string.
 */
@JvmName("array2String_")
fun array2String(array: Array<*>?) = array.array2String()
fun Array<*>?.array2String(): String {
    this ?: return "Array [array is null]"

    val stringBuilder = StringBuilder()
    val arraySize = this.size
    for (idx in 0 until arraySize) {
        if (idx == 0) {
            stringBuilder.append("Array [")
        }
        stringBuilder.append(this[idx].toString())
        if (idx == (arraySize - 1)) {
            stringBuilder.append("]")
        }
        else {
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
@JvmName("list2String_")
fun list2String(list: List<*>?) = list.list2String()
fun List<*>?.list2String() = this?.toString() ?: "List[ list is null ]"

/**
 * Bytes to hex string.
 * e.g. bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns "00A8".
 *
 * @param bytes The bytes.
 * @return The hex string.
 */
@JvmName("bytes2HexString_")
fun bytes2HexString(bytes: ByteArray?) = bytes.bytes2HexString()
fun ByteArray?.bytes2HexString(): String {
    this ?: return ""
    if (this.isEmptyNonNull()) return ""

    val bytesSize = this.size
    val chars = CharArray(bytesSize shl 1)
    var j = 0
    for (i in 0 until bytesSize) {
        chars[j++] = HEX_DIGITS[(this[i].toInt() shr 4) and 0x0f]
        chars[j++] = HEX_DIGITS[(this[i] and 0x0f).toInt()]
    }
    return String(chars)
}

/**
 * Set the first letter of string upper.
 *
 * @param s The string.
 * @return The string with first letter upper.
 */
@JvmName("uppercaseFirstLetter_")
fun uppercaseFirstLetter(s: String?) = s.uppercaseFirstLetter()
fun String?.uppercaseFirstLetter(): String {
    this ?: return ""
    if (this.isSpaceNonNull()) return this

    // 首字符可能会是数字，所以不能直接用 Character.isUpperCase 判断
    if (!Character.isLowerCase(this[0])) return this
    return (this[0].toInt() - 32).toChar().toString() + this.substring(1)
}

/**
 * Set the first letter of string lower.
 *
 * @param s The string.
 * @return The string with first letter lower.
 */
@JvmName("lowercaseFirstLetter_")
fun lowercaseFirstLetter(s: String?) = s.lowercaseFirstLetter()
fun String?.lowercaseFirstLetter(): String {
    this ?: return ""
    if (this.isSpaceNonNull()) return this

    // 首字符可能会是数字，所以不能直接用 Character.isLowerCase 判断
    if (!Character.isUpperCase(this[0])) return this
    return (this[0].toInt() + 32).toChar().toString() + this.substring(1)
}

/**
 * Reverse the string.
 *
 * @param s The string.
 * @return The reverse string.
 */
@JvmName("reverse_")
fun reverse(s: String?) = s.reverse()
fun String?.reverse(): String {
    this ?: return ""
    if (this.isSpaceNonNull()) return this

    val len = this.length
    if (len <= 1) return this

    val mid = len shr 1
    val chars = this.toCharArray()
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
@JvmName("toDBC_")
fun toDBC(s: String?) = s.toDBC()
fun String?.toDBC(): String {
    this ?: return ""
    if (this.isSpaceNonNull()) return ""

    val chars = this.toCharArray()
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
@JvmName("toSBC_")
fun toSBC(s: String?) = s.toSBC()
fun String?.toSBC(): String {
    this ?: return ""
    if (isSpace(this)) return ""

    val chars = this.toCharArray()
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
