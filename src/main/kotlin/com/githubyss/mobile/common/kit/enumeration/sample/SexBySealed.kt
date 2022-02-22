package com.githubyss.mobile.common.kit.enumeration.sample


/**
 * SexBySealed
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/21 16:11:16
 */
sealed class SexBySealed(val sex: String) {
    object MAN : SexBySealed("男")
    object WOMEN : SexBySealed("女")
}
