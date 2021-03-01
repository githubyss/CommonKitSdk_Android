package com.githubyss.mobile.common.kit.util

import android.content.Context
import android.content.res.Resources.NotFoundException
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.ComkitUtils


/**
 * StringUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 11:48:47
 */
object StringUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    
    /** ********** ********** ********** Public ********** ********** ********** */
    
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
        return s == null || s.trim()
                .isEmpty()
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
     * Return the length of string.
     *
     * @param s The string.
     * @return The length of string.
     */
    fun length(s: CharSequence?): Int {
        return s?.length ?: 0
    }
    
    /**
     * Set the first letter of string upper.
     *
     * @param s The string.
     * @return The string with first letter upper.
     */
    fun uppercaseFirstLetter(s: String?): String {
        if (s == null || s.isEmpty()) return ""
        // 首字符可能会是数字，所以不能直接用 Character.isUpperCase 判断
        if (!Character.isLowerCase(s[0])) return s
        return (s[0].toInt() - 32).toChar()
                .toString() + s.substring(1)
    }
    
    /**
     * Set the first letter of string lower.
     *
     * @param s The string.
     * @return The string with first letter lower.
     */
    fun lowercaseFirstLetter(s: String?): String {
        if (s == null || s.isEmpty()) return ""
        // 首字符可能会是数字，所以不能直接用 Character.isLowerCase 判断
        if (!Character.isUpperCase(s[0])) return s
        return (s[0].toInt() + 32).toChar()
                .toString() + s.substring(1)
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
        if (s == null || s.isEmpty()) return ""
        
        val chars = s.toCharArray()
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
    fun toSBC(s: String?): String? {
        if (s == null || s.isEmpty()) return ""
        
        val chars = s.toCharArray()
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
    
    /**
     * Return the string value associated with a particular resource ID.
     *
     * @param id The desired resource identifier.
     * @return The string value associated with a particular resource ID.
     */
    fun getString(context: Context = ComkitApplicationConfig.getApp(), @StringRes id: Int): String {
        return try {
            context.resources.getString(id)
        } catch (ignore: NotFoundException) {
            ""
        }
    }
    
    /**
     * Return the string value associated with a particular resource ID.
     *
     * @param id         The desired resource identifier.
     * @param formatArgs The format arguments that will be used for substitution.
     * @return The string value associated with a particular resource ID.
     */
    fun getString(context: Context = ComkitApplicationConfig.getApp(), @StringRes id: Int, vararg formatArgs: Any?): String {
        return try {
            context.resources.getString(id, formatArgs)
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
}
