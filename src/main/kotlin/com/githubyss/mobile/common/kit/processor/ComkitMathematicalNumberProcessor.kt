package com.githubyss.mobile.common.kit.processor

import com.githubyss.mobile.common.kit.regex.ComkitRegExConfig
import com.githubyss.mobile.common.kit.regex.ComkitRegExUtils

/**
 * ComkitMathematicalNumberProcessor
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitMathematicalNumberProcessor {
    fun checkInteger(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.INTEGER)

    fun checkIntegerZero(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.INTEGER_ZERO)

    fun checkIntegerNonNegative(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.INTEGER_NON_NEGATIVE)

    fun checkIntegerNonPositive(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.INTEGER_NON_POSITIVE)


    fun checkConventionalIntegerZero(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.CONVENTIONAL_INTEGER_ZERO)

    fun checkConventionalInteger(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.CONVENTIONAL_INTEGER)

    fun checkConventionalIntegerPositive(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.CONVENTIONAL_INTEGER_POSITIVE)

    fun checkConventionalIntegerNegative(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.CONVENTIONAL_INTEGER_NEGATIVE)

    fun checkConventionalIntegerNonNegative(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.CONVENTIONAL_INTEGER_NON_NEGATIVE)

    fun checkConventionalIntegerNonPositive(input: String): Boolean
            = ComkitRegExUtils.regExPatternMatches(input, ComkitRegExConfig.MathematicalNumber.CONVENTIONAL_INTEGER_NON_POSITIVE)


    /**
     * ComkitMathematicalNumberProcessor.string2ConventionalIntegerNonNegative(input)
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
