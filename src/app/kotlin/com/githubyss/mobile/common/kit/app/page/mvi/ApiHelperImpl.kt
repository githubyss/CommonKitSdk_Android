package com.githubyss.mobile.common.kit.app.page.mvi

import com.githubyss.mobile.common.kit.app.page.mvi.model.User


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}
