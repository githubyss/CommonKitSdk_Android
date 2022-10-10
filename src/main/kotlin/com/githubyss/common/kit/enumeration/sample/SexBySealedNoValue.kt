package com.githubyss.common.kit.enumeration.sample


/**
 * SexBySealedNoValue
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/21 16:42:36
 */
sealed class SexBySealedNoValue {
    object MAN : SexBySealedNoValue()
    object WOMEN : SexBySealedNoValue()
}
