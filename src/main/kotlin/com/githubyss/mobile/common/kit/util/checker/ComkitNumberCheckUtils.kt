package com.githubyss.mobile.common.kit.util.checker

import com.githubyss.mobile.common.kit.constant.ComkitRegExConstants
import com.githubyss.mobile.common.kit.util.ComkitReusableUtils

/**
 * ComkitNumberCheckUtils.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitNumberCheckUtils {
    fun checkInteger(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.INTEGER)
    }

    fun checkIntegerZero(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.INTEGER_ZERO)
    }

    fun checkIntegerNonNegative(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.INTEGER_NON_NEGATIVE)
    }

    fun checkIntegerNonPositive(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.INTEGER_NON_POSITIVE)
    }


    fun checkConventionalIntegerZero(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.CONVENTIONAL_INTEGER_ZERO)
    }

    fun checkConventionalInteger(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.CONVENTIONAL_INTEGER)
    }

    fun checkConventionalIntegerPositive(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.CONVENTIONAL_INTEGER_POSITIVE)
    }

    fun checkConventionalIntegerNegative(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.CONVENTIONAL_INTEGER_NEGATIVE)
    }

    fun checkConventionalIntegerNonNegative(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.CONVENTIONAL_INTEGER_NON_NEGATIVE)
    }

    fun checkConventionalIntegerNonPositive(input: String): Boolean {
        return ComkitReusableUtils.regExPatternMatches(input, ComkitRegExConstants.MathematicalNumber.CONVENTIONAL_INTEGER_NON_POSITIVE)
    }
}
