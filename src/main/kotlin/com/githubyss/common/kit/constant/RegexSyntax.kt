package com.githubyss.common.kit.constant


/**
 * RegexSyntax
 *
 * '^' Syntax start or NOT in '[]'
 * '$' Syntax end
 * '*' Match 0 time or multi times
 * '+' Match 1 time or multi times
 * '?' Match 0 time or 1 time
 * '.' Match all single char except '\n'
 * '[]' Char set
 * '()' Group
 * '{n}' Match n times
 * '{n,}' Match n times at least
 * '{n,m}' Match n times at least and m times at most
 * '|' Choose one
 *
 * If u want more please visit
 * http://toutiao.com/i6231678548520731137
 * http://tool.oschina.net/regex
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/22 10:47:37
 */
object RegexSyntax {
    
    /** ********** Mathematical Number ********** */
    
    /** Regex of zero integer. */
    const val REGEX_ZERO_INTEGER = "^-?0$"
    
    /** Regex of integer. */
    const val REGEX_INTEGER = "^-?[1-9]\\d*$"
    
    /** Regex of positive integer. */
    const val REGEX_POSITIVE_INTEGER = "^[1-9]\\d*$"
    
    /** Regex of negative integer. */
    const val REGEX_NEGATIVE_INTEGER = "^-[1-9]\\d*$"
    
    /** Regex of non-negative integer. */
    const val REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$"
    
    /** Regex of non-positive integer. */
    const val REGEX_NOT_POSITIVE_INTEGER = "^-[1-9]\\d*|0$"
    
    /** Regex of zero float. */
    const val REGEX_ZERO_FLOAT = "^-?0\\.0*$"
    
    /** Regex of float. */
    const val REGEX_FLOAT = "^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)$"
    
    /** Regex of positive float. */
    const val REGEX_POSITIVE_FLOAT = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$"
    
    /** Regex of negative float. */
    const val REGEX_NEGATIVE_FLOAT = "^-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)$"
    
    // const val REGEX_INTEGER = "^(\\d+|-\\d+)$" /* equals to "^([0-9]+|-[0-9]+)$" */
    // const val REGEX_INTEGER_ZERO = "^(0+|-0+)$"
    // const val REGEX_INTEGER_NON_NEGATIVE = "^(\\d+|-0+)$"
    // const val REGEX_INTEGER_NON_POSITIVE = "^(-\\d+|0+)$"
    
    /** ********** Sociological Number ********** */
    
    /**
     * Regex of simple cellphone.
     * 11100009999
     */
    const val REGEX_CELLPHONE_SIMPLE = "^[1]\\d{10}$"
    
    /**
     * Regex of simple cellphone with '-'.
     * 111-0000-9999
     */
    const val REGEX_CELLPHONE_SIMPLE_WITH_DASH = "^[1]\\d{3}[-]\\d{4}[-]\\d{4}$"
    
    /**
     * Regex of simple cellphone with ' '.
     * 111 0000 9999
     */
    const val REGEX_CELLPHONE_SIMPLE_WITH_SPACE = "^[1]\\d{3}[ ]\\d{4}[ ]\\d{4}$"
    
    /**
     * Regex of exact cellphone.
     * china mobile: 134(0-8), 135, 136, 137, 138, 139, 147, 150, 151, 152, 157, 158, 159, 178, 182, 183, 184, 187, 188, 198
     * china unicom: 130, 131, 132, 145, 155, 156, 166, 171, 175, 176, 185, 186
     * china telecom: 133, 153, 173, 177, 180, 181, 189, 199, 191
     * global star: 1349
     * virtual operator: 170
     *
     * 18800009999
     */
    const val REGEX_CELLPHONE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[1,8,9]))\\d{8}$"
    
    /**
     * Regex of exact cellphone with '-'.
     * 188-0000-9999
     */
    const val REGEX_CELLPHONE_EXACT_WITH_DASH = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[1,8,9]))[-]\\d{4}[-]\\d{4}$"
    
    /**
     * Regex of exact cellphone with ' '.
     * 188 0000 9999
     */
    const val REGEX_CELLPHONE_EXACT_WITH_SPACE = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[1,8,9]))[ ]\\d{4}[ ]\\d{4}$"
    
    /**
     * Regex of telephone number.
     */
    const val REGEX_TELEPHONE = "^0\\d{2,3}[- ]?\\d{7,8}$"
    
    /**
     * Regex of id card number which length is 15.
     */
    const val REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"
    
    /**
     * Regex of id card number which length is 18.
     */
    const val REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$"
    
    /**
     * Regex of passport card number.
     */
    const val REGEX_PASSPORT_CARD = "^$"
    
    /**
     * Regex of postal code in China.
     */
    const val REGEX_POSTAL_CODE = "^[1-9]\\d{5}(?!\\d)$"
    
    /**
     * Regex of QQ number.
     * 10000
     */
    const val REGEX_QQ = "^[1-9][0-9]{4,}$"
    
    /**
     * Regex of email.
     */
    const val REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"
    
    /** ********** Net ********** */
    
    /**
     * Regex of url.
     */
    const val REGEX_URL = "^[a-zA-z]+://[^\\s]*$"
    
    /**
     * Regex of ip address.
     */
    const val REGEX_IP = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$"
    
    /** ********** Language ********** */
    
    /**
     * Regex of Chinese character.
     */
    const val REGEX_ZH = "^[\\u4e00-\\u9fa5]+$"
    
    /**
     * Regex of username.
     *
     * scope for "a-z", "A-Z", "0-9", "_", "Chinese character"
     *
     * can't end with "_"
     *
     * length is between 6 to 20
     */
    const val REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$"
    
    /** ********** Date time ********** */
    
    /**
     * Regex of date which pattern is "yyyy-MM-dd".
     */
    const val REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"
    
    /** ********** String ********** */
    
    /**
     * Regex of blank.
     */
    const val REGEX_BLANK = "^\\s*$"
    
    /**
     * Regex of return.
     */
    const val REGEX_RETURN = "^\\n*$"
    
    /**
     * Regex of blank line.
     */
    const val REGEX_BLANK_LINE = "^\\n\\s*\\r$"
    
    /**
     * Regex of double-byte characters.
     */
    const val REGEX_DOUBLE_BYTE_CHAR = "^[^\\x00-\\xff]$"
}
