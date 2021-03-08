package com.githubyss.mobile.common.kit.util

import com.githubyss.mobile.common.kit.constant.RegexSyntax


/**
 * NumberUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:15:04
 */
object NumberUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = NumberUtils::class.simpleName ?: "simpleName is null"
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Converter ********** ********** */
    
    /** ********** Mathematical Number ********** */
    
    /** ********** Bin, Oct, Dec, Hex ********** */
    
    /**
     * Dec to hex.
     *
     * @param dec The dec.
     * @return The hex.
     */
    fun dec2Hex(dec: Int): Char {
        return dec.toChar()
    }
    
    /**
     * Hex to dec.
     *
     * @param hex The hex.
     * @return The dec.
     */
    fun hex2Dec(hex: Char): Int {
        return when (hex) {
            in '0'..'9' -> hex - '0'
            in 'A'..'F' -> hex - 'A' + 10
            else -> throw IllegalArgumentException()
        }
    }
    
    /**
     * Format the input to conventional non negative integer.
     *
     * @param input String to be formatted.
     * @return Conventional non negative integer, such as "0", "1", "10", "1234".
     */
    fun string2NonNegativeInteger(input: String?): String {
        return if (RegexUtils.isInteger(input)) input?.toLong().toString().replace("-", "")
        else ""
    }
    
    /** ********** Sociological Number ********** */
    
    fun cellphone2Normal(input: String?): String {
        return when {
            RegexUtils.isCellphone(input) -> ""
            else -> ""
        }
    }
}
