package com.githubyss.common.kit.enumeration

import androidx.annotation.StringDef


/**
 * CheckState
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/04 13:15:25
 */
@MustBeDocumented
@StringDef(CheckState.CHECK_YES, CheckState.CHECK_NO, CheckState.CHECK_PARTLY)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class CheckState {
    companion object {
        const val CHECK_YES = "选中"
        const val CHECK_NO = "未选中"
        const val CHECK_PARTLY = "部分选中"
    }
}
