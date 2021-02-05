package com.githubyss.mobile.common.kit.processor

import com.githubyss.mobile.common.kit.util.RegexConfig
import com.githubyss.mobile.common.kit.util.RegexUtils

/**
 * ComkitSociologicalNumberProcessor
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitSociologicalNumberProcessor {
    fun checkChineseTelephoneNormal(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.SociologicalNumber.CHINESE_TELEPHONE_NORMAL)

    fun checkChineseCellphone(input: String): Boolean
            = checkChineseCellphoneNormal(input) || checkChineseCellphoneDashSplit(input) || checkChineseCellphoneSpaceSplit(input)

    fun checkChineseCellphoneNormal(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.SociologicalNumber.CHINESE_CELLPHONE_NORMAL)

    fun checkChineseCellphoneDashSplit(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.SociologicalNumber.CHINESE_CELLPHONE_DASH_SPLIT)

    fun checkChineseCellphoneSpaceSplit(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.SociologicalNumber.CHINESE_CELLPHONE_SPACE_SPLIT)

    fun checkChineseIdentityCard(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.SociologicalNumber.CHINESE_IDENTITY_CARD)

    fun checkChinesePassportCard(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.SociologicalNumber.CHINESE_PASSPORT_CARD)

    fun checkQq(input: String): Boolean
            = RegexUtils.regExPatternMatches(input, RegexConfig.SociologicalNumber.QQ)


    fun cellphoneNormal2DashSplit(input: String): String
            = when {
        checkChineseCellphoneNormal(input) -> ""
        else -> ""
    }

    fun cellphoneNormal2SpaceSplit(input: String): String
            = when {
        checkChineseCellphoneNormal(input) -> ""
        else -> ""
    }

    fun cellphoneDashSplit2Normal(input: String): String
            = when {
        checkChineseCellphoneDashSplit(input) -> ""
        else -> ""
    }

    fun cellphoneSpaceSplit2Normal(input: String): String
            = when {
        checkChineseCellphoneSpaceSplit(input) -> ""
        else -> ""
    }
}
