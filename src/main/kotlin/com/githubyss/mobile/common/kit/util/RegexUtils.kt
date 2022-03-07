package com.githubyss.mobile.common.kit.util

import androidx.collection.SimpleArrayMap
import com.githubyss.mobile.common.kit.constant.RegexSyntax
import java.util.*
import java.util.regex.Pattern


/**
 * RegexUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:15:12
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "RegexUtils"

private val CITY_MAP: SimpleArrayMap<String, String> = SimpleArrayMap()


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/**
 * Return the list of input matches the regex.
 *
 * @param regex The regex.
 * @param input The input.
 * @return the list of input matches the regex
 */
fun getMatches(input: CharSequence?, regex: String): List<String>? {
    input ?: return emptyList()

    val matches: MutableList<String> = ArrayList()
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(input)
    while (matcher.find()) {
        matches.add(matcher.group())
    }
    return matches
}

/**
 * Splits input around matches of the regex.
 *
 * @param input The input.
 * @param regex The regex.
 * @return the array of strings computed by splitting input around matches of regex
 */
fun getSplits(input: String?, regex: String): Array<String>? {
    return input?.split(regex.toRegex())
        ?.toTypedArray()
}

/**
 * Replace the first subsequence of the input sequence that matches the
 * regex with the given replacement string.
 *
 * @param input       The input.
 * @param regex       The regex.
 * @param replacement The replacement string.
 * @return the string constructed by replacing the first matching
 * subsequence by the replacement string, substituting captured
 * subsequences as needed
 */
fun getReplaceFirst(input: String?, regex: String, replacement: String): String {
    input ?: return ""

    return Pattern.compile(regex)
        .matcher(input)
        .replaceFirst(replacement)
}

/**
 * Replace every subsequence of the input sequence that matches the
 * pattern with the given replacement string.
 *
 * @param input       The input.
 * @param regex       The regex.
 * @param replacement The replacement string.
 * @return the string constructed by replacing each matching subsequence
 * by the replacement string, substituting captured subsequences
 * as needed
 */
fun getReplaceAll(input: String?, regex: String, replacement: String): String {
    input ?: return ""

    return Pattern.compile(regex)
        .matcher(input)
        .replaceAll(replacement)
}

/** ******************** Checker ******************** */

/**
 * Return whether input matches the regex.
 *
 * @param regex The regex.
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isMatch(input: CharSequence?, regex: String): Boolean {
    return input != null && Pattern.matches(regex, input)
}

/** ********** Mathematical Number ********** */

fun isInteger(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_INTEGER)
}

fun isZeroInteger(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_ZERO_INTEGER)
}

fun isNonNegativeInteger(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_NOT_NEGATIVE_INTEGER)
}

fun isNonPositiveInteger(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_NOT_POSITIVE_INTEGER)
}

/** ********** Sociological Number ********** */

fun isCellphone(input: CharSequence?): Boolean {
    return isCellphoneSimple(input) || isCellphoneSimpleWithDash(input) || isCellphoneSimpleWithSpace(input) || isCellphoneExact(input) || isCellphoneExactWithDash(input) || isCellphoneExactWithSpace(input)
}

/**
 * Return whether input matches regex of simple mobile.
 *
 * @param input The input.
 * @return {@code true}: yes<br>{@code false}: no
 */
fun isCellphoneSimple(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_CELLPHONE_SIMPLE)
}

fun isCellphoneSimpleWithDash(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_CELLPHONE_SIMPLE_WITH_DASH)
}

fun isCellphoneSimpleWithSpace(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_CELLPHONE_SIMPLE_WITH_SPACE)
}

/**
 * Return whether input matches regex of exact mobile.
 *
 * @param input The input.
 * @return {@code true}: yes<br>{@code false}: no
 */
fun isCellphoneExact(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_CELLPHONE_EXACT)
}

fun isCellphoneExactWithDash(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_CELLPHONE_EXACT_WITH_DASH)
}

fun isCellphoneExactWithSpace(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_CELLPHONE_EXACT_WITH_SPACE)
}

/**
 * Return whether input matches regex of telephone number.
 *
 * @param input The input.
 * @return {@code true}: yes<br>{@code false}: no
 */
fun isTelephone(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_TELEPHONE)
}

/**
 * Return whether input matches regex of id card.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isIdentityCard(input: CharSequence?): Boolean {
    return isIdentityCard15(input) || isIdentityCard18(input)
}

/**
 * Return whether input matches regex of id card number which length is 15.
 *
 * @param input The input.
 * @return {@code true}: yes<br>{@code false}: no
 */
fun isIdentityCard15(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_ID_CARD15)
}

/**
 * Return whether input matches regex of id card number which length is 18.
 *
 * @param input The input.
 * @return {@code true}: yes<br>{@code false}: no
 */
fun isIdentityCard18(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_ID_CARD18)
}

/**
 * Return whether input matches regex of exact id card number which length is 18.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isIdentityCard18Exact(input: CharSequence): Boolean {
    if (isIdentityCard18(input)) {
        val factor = intArrayOf(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2)
        val suffix = charArrayOf('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2')
        if (CITY_MAP.isEmpty) {
            CITY_MAP.put("11", "北京")
            CITY_MAP.put("12", "天津")
            CITY_MAP.put("13", "河北")
            CITY_MAP.put("14", "山西")
            CITY_MAP.put("15", "内蒙古")
            CITY_MAP.put("21", "辽宁")
            CITY_MAP.put("22", "吉林")
            CITY_MAP.put("23", "黑龙江")
            CITY_MAP.put("31", "上海")
            CITY_MAP.put("32", "江苏")
            CITY_MAP.put("33", "浙江")
            CITY_MAP.put("34", "安徽")
            CITY_MAP.put("35", "福建")
            CITY_MAP.put("36", "江西")
            CITY_MAP.put("37", "山东")
            CITY_MAP.put("41", "河南")
            CITY_MAP.put("42", "湖北")
            CITY_MAP.put("43", "湖南")
            CITY_MAP.put("44", "广东")
            CITY_MAP.put("45", "广西")
            CITY_MAP.put("46", "海南")
            CITY_MAP.put("50", "重庆")
            CITY_MAP.put("51", "四川")
            CITY_MAP.put("52", "贵州")
            CITY_MAP.put("53", "云南")
            CITY_MAP.put("54", "西藏")
            CITY_MAP.put("61", "陕西")
            CITY_MAP.put("62", "甘肃")
            CITY_MAP.put("63", "青海")
            CITY_MAP.put("64", "宁夏")
            CITY_MAP.put("65", "新疆")
            CITY_MAP.put("71", "台湾")
            CITY_MAP.put("81", "香港")
            CITY_MAP.put("82", "澳门")
            CITY_MAP.put("91", "国外")
        }
        if (CITY_MAP[input.subSequence(0, 2)
                .toString()] != null) {
            var weightSum = 0
            for (i in 0..16) {
                weightSum += (input[i] - '0') * factor[i]
            }
            val idCardMod = weightSum % 11
            val idCardLast = input[17]
            return idCardLast == suffix[idCardMod]
        }
    }
    return false
}

/**
 * Return whether input matches regex of passport card.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isPassportCard(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_PASSPORT_CARD)
}

/**
 * Return whether input matches regex of postal code.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isPostalCode(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_POSTAL_CODE)
}

/**
 * Return whether input matches regex of QQ.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isQq(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_QQ)
}

/**
 * Return whether input matches regex of email.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isEmail(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_EMAIL)
}

/** ********** Net ********** */

/**
 * Return whether input matches regex of url.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isUrl(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_URL)
}

/**
 * Return whether input matches regex of ip address.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isIp(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_IP)
}

/**
 * Return whether input matches regex of Chinese character.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isZh(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_ZH)
}

/**
 * Return whether input matches regex of username.
 *
 * scope for "a-z", "A-Z", "0-9", "_", "Chinese character"
 *
 * can't end with "_"
 *
 * length is between 6 to 20.
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isUsername(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_USERNAME)
}

/** ********** Date time ********** */

/**
 * Return whether input matches regex of date which pattern is "yyyy-MM-dd".
 *
 * @param input The input.
 * @return `true`: yes<br></br>`false`: no
 */
fun isDate(input: CharSequence?): Boolean {
    return isMatch(input, RegexSyntax.REGEX_DATE)
}
