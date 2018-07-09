package com.githubyss.mobile.common.kit.checker

import com.githubyss.mobile.common.kit.regex.ComkitRegExConfig
import com.githubyss.mobile.common.kit.regex.ComkitRegExUtils

/**
 * ComkitNumberCheckUtils.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitNumberCheckUtils {
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
}
