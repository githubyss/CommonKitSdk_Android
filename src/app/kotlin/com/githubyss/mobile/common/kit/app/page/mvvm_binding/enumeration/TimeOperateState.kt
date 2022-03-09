package com.githubyss.mobile.common.kit.app.page.mvvm_binding.enumeration

import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.res.R


/**
 * TimeOperateState
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/21 16:01:30
 */
sealed class TimeOperateState(val value: String) {
    object START : TimeOperateState(getStringFromRes(R.string.comres_start))
    object STOP : TimeOperateState(getStringFromRes(R.string.comres_stop))
}
