package com.githubyss.mobile.common.kit.app.page.mvi

import com.githubyss.mobile.common.kit.app.page.mvi.model.User


interface ApiHelper {
    suspend fun getUsers(): List<User>
}
