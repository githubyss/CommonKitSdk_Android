package com.githubyss.mobile.common.kit.formatter

import com.githubyss.mobile.common.kit.checker.ComkitNumberCheckUtils

/**
 * ComkitNumberFormatUtils.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitNumberFormatUtils {
    /**
     * ComkitNumberFormatUtils.formatConventionalIntegerNonNegative(input)
     * <Description> Format the input to conventional non negative integer.
     * <Details>
     *
     * @param input String to be formatted.
     * @return Conventional non negative integer, such as "0", "1", "10", "1234".
     * @author Ace Yan
     * @github githubyss
     */
    fun formatConventionalIntegerNonNegative(input: String
    ): String = when {
        ComkitNumberCheckUtils.checkInteger(input) -> input.toLong().toString().replace("-", "")
        else -> ""
    }
}
