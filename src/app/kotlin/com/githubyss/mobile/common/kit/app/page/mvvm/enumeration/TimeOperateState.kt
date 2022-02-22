package com.githubyss.mobile.common.kit.app.page.mvvm.enumeration


/**
 * TimeOperateState
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/21 16:01:30
 */
sealed class TimeOperateState {
    object START : TimeOperateState()
    object STOP : TimeOperateState()
}
