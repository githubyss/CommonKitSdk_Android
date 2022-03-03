package com.githubyss.mobile.common.kit.app.page.mvvm.enumeration

import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.res.R


/**
 * TimeOperateState
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/21 16:01:30
 */
sealed class TimeOperateState(val value: String) {
    object START : TimeOperateState(ResourceUtils.getString(R.string.comres_start))
    object STOP : TimeOperateState(ResourceUtils.getString(R.string.comres_stop))
}
