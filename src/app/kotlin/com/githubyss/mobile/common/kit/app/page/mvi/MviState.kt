package com.githubyss.mobile.common.kit.app.page.mvi

import com.githubyss.mobile.common.kit.app.page.mvi.model.User


sealed class MviState {
    object Idle : MviState()
    object Loading : MviState()
    data class Users(val user: List<User>) : MviState()
    data class Error(val error: String?) : MviState()
}
