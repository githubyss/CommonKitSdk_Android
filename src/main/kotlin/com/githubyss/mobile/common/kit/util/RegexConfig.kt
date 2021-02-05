package com.githubyss.mobile.common.kit.util

/**
 * RegexConfig
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object RegexConfig {
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
        val CHINESE_TELEPHONE_NORMAL = "^0\\d{2,3}\\d{7,8}(\\d{1,5})?$"

        /**
         * 12999999999
         * by Ace Yan
         */
        val CHINESE_CELLPHONE_NORMAL = "^1[2-9]\\d{9}$"

        /**
         * 129-9999-9999
         * by Ace Yan
         */
        val CHINESE_CELLPHONE_DASH_SPLIT = "^$"

        /**
         * 129 9999 9999
         * by Ace Yan
         */
        val CHINESE_CELLPHONE_SPACE_SPLIT = "^$"

        /**
         * 320100199901010000
         * by Ace Yan
         */
        val CHINESE_IDENTITY_CARD = "^$"

        /**
         *
         * by Ace Yan
         */
        val CHINESE_PASSPORT_CARD = "^$"

        /**
         * 10000
         * by Ace Yan
         */
        val QQ = "^[1-9]\\d{4,}$"
    }
}
