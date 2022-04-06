package com.githubyss.mobile.common.kit.app.page.compose_button

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.util.getStringFromRes


/**
 * ComposeButtonViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/06 10:15:01
 */
class ComposeButtonViewModel : ViewModel() {

    /** ****************************** Properties ****************************** */

    private val titleDefault = getStringFromRes(R.string.comkit_compose_button_title)

    /** 数据绑定，绑定到 UI 的字段（data field） */
    var title: String by mutableStateOf(titleDefault)
        private set


    /** ****************************** Functions ****************************** */

    fun changeTitle(title: String) {
        this.title = title
    }
}