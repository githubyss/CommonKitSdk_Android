package com.githubyss.mobile.common.kit.regex

/**
 * ComkitRegExConfig.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitRegExConfig {
    object Common {
        val BLANK = "^\\s*$"
        val RETURN = "[\\n]"
    }

    object MathematicalNumber {
        val INTEGER = "^(\\d+|-\\d+)$" /* equals to "^([0-9]+|-[0-9]+)$" */
        val INTEGER_ZERO = "^(0+|-0+)$"
        val INTEGER_NON_NEGATIVE = "^(\\d+|-0+)$"
        val INTEGER_NON_POSITIVE = "^(-\\d+|0+)$"

        val CONVENTIONAL_INTEGER = "^([1-9]\\d*|-[1-9]\\d*|0|-0)$"
        val CONVENTIONAL_INTEGER_ZERO = "^(0|-0)$"
        val CONVENTIONAL_INTEGER_POSITIVE = "^[1-9]\\d*$"
        val CONVENTIONAL_INTEGER_NEGATIVE = "^-[1-9]\\d*$"
        val CONVENTIONAL_INTEGER_NON_NEGATIVE = "^([1-9]\\d*|0|-0)$"
        val CONVENTIONAL_INTEGER_NON_POSITIVE = "^(-[1-9]\\d*|0|-0)$"
    }

    object SociologicalNumber {
        /**
         * 02388888888
         * 0237777777
         * 023488888888
         * 02347777777
         * by Ace Yan
         */
        val CHINESE_TELEPHONE_NUMBER = "^0\\d{2,3}\\d{7,8}(\\d{1,5})?$"

        /**
         * 12999999999
         * by Ace Yan
         */
        val CHINESE_CELLPHONE_NUMBER = "^1[2-9]\\d{9}$"

        val QQ_NUMBER = "^[1-9]\\d{4,}$"
    }
}
