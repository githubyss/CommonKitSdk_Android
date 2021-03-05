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
    
    /** ********** ********** Checker ********** ********** */
    
    /** ********** Mathematical Number ********** */
    
    fun isInteger(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_INTEGER)
    }
    
    fun isZeroInteger(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_ZERO_INTEGER)
    }
    
    fun isNonNegativeInteger(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_NOT_NEGATIVE_INTEGER)
    }
    
    fun isNonPositiveInteger(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_NOT_POSITIVE_INTEGER)
    }
    
    /** ********** Sociological Number ********** */
    
    fun isCellphone(input: String?): Boolean {
        return isSimpleCellphone(input) || isSimpleCellphoneWithDash(input) || isSimpleCellphoneWithSpace(input) || isExactCellphone(input) || isExactCellphoneWithDash(input) || isExactCellphoneWithSpace(input)
    }
    
    fun isSimpleCellphone(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_CELLPHONE_SIMPLE)
    }
    
    fun isSimpleCellphoneWithDash(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_CELLPHONE_SIMPLE_WITH_DASH)
    }
    
    fun isSimpleCellphoneWithSpace(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_CELLPHONE_SIMPLE_WITH_SPACE)
    }
    
    fun isExactCellphone(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_CELLPHONE_EXACT)
    }
    
    fun isExactCellphoneWithDash(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_CELLPHONE_EXACT_WITH_DASH)
    }
    
    fun isExactCellphoneWithSpace(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_CELLPHONE_EXACT_WITH_SPACE)
    }
    
    fun isTelephone(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_TELEPHONE)
    }
    
    fun isIdentityCard(input: String?): Boolean {
        return isIdentityCard15(input) || isIdentityCard18(input)
    }
    
    fun isIdentityCard15(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_ID_CARD15)
    }
    
    fun isIdentityCard18(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_ID_CARD18)
    }
    
    fun isPassportCard(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_PASSPORT_CARD)
    }
    
    fun isQq(input: String?): Boolean {
        return RegexUtils.regExPatternMatches(input, RegexSyntax.REGEX_QQ)
    }
    
    
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
        return if (isInteger(input)) input?.toLong().toString().replace("-", "")
        else ""
    }
    
    /** ********** Sociological Number ********** */
    
    fun cellphone2Normal(input: String?): String {
        return when {
            isCellphone(input) -> ""
            else -> ""
        }
    }
}
