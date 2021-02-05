package com.githubyss.mobile.common.kit.util

/**
 * NumberUtils
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object NumberUtils {
    fun checkInteger(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.INTEGER)

    fun checkIntegerZero(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.INTEGER_ZERO)

    fun checkIntegerNonNegative(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.INTEGER_NON_NEGATIVE)

    fun checkIntegerNonPositive(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.INTEGER_NON_POSITIVE)


    fun checkConventionalIntegerZero(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.CONVENTIONAL_INTEGER_ZERO)

    fun checkConventionalInteger(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.CONVENTIONAL_INTEGER)

    fun checkConventionalIntegerPositive(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.CONVENTIONAL_INTEGER_POSITIVE)

    fun checkConventionalIntegerNegative(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.CONVENTIONAL_INTEGER_NEGATIVE)

    fun checkConventionalIntegerNonNegative(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.CONVENTIONAL_INTEGER_NON_NEGATIVE)

    fun checkConventionalIntegerNonPositive(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.MathematicalNumber.CONVENTIONAL_INTEGER_NON_POSITIVE)


    /**
     * NumberUtils.string2ConventionalIntegerNonNegative(input)
     * <Description> Format the input to conventional non negative integer.
     * <Details>
     *
     * @param input String to be formatted.
     * @return Conventional non negative integer, such as "0", "1", "10", "1234".
     * @author Ace Yan
     * @github githubyss
     */
    fun string2ConventionalIntegerNonNegative(input: String): String
            = when {
        checkInteger(input) -> input.toLong().toString().replace("-", "")
        else -> ""
    }
}
