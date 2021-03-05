package com.githubyss.mobile.common.kit.util

import java.util.regex.Pattern


/**
 * RegexUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:15:12
 */
object RegexUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = RegexUtils::class.simpleName ?: "simpleName is null"
    
    
    /**
     * RegexUtils.regExPatternMatches(input, regEx)
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
    fun regExPatternMatches(input: String?, regEx: String): Boolean {
        return Pattern.compile(regEx)
                .matcher(input ?: return false)
                .matches()
    }
}
