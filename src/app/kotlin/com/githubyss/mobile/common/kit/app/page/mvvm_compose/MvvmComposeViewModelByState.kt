package com.githubyss.mobile.common.kit.app.page.mvvm_compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.util.getStringFromRes


/**
 * MvvmComposeViewModelByState
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/08 16:20:56
 */
class MvvmComposeViewModelByState : ViewModel() {

    /** ****************************** Properties ****************************** */

    private val TITLE = getStringFromRes(R.string.comkit_compose_toolbar_title)

    var title: String by mutableStateOf(TITLE)
        private set
    var count: Int by mutableStateOf(0)
        private set


    /** ****************************** Functions ****************************** */

    fun changeTitle(title: String) {
        this.title = title
    }

    fun plus() {
        this.count++
    }
}
