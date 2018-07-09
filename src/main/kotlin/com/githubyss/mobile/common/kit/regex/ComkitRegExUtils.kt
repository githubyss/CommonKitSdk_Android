package com.githubyss.mobile.common.kit.regex

import java.util.regex.Pattern

/**
 * ComkitRegExUtils.kt <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitRegExUtils {
    /**
     * ComkitRegExUtils.regExPatternMatches(input, regEx)
     * <Description>
     * <Details>
     *
     * fun regExPatternMatches(input: String, regEx: String): Boolean {
     *     val pattern = Pattern.compile(regEx)
     *     val matcher = pattern.matcher(input)
     *     return matcher.matches()
     * }
     *
     * @param input
     * @param regEx
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun regExPatternMatches(input: String, regEx: String): Boolean
            = Pattern.compile(regEx).matcher(input).matches()
}
