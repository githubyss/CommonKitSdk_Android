package com.githubyss.mobile.common.kit.app.page.mvi


class MviRepository(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}
