package com.githubyss.mobile.common.kit.app.page.mvi

import com.githubyss.mobile.common.kit.app.page.mvi.model.User
import retrofit2.http.GET


interface ApiService {
    @GET("users?page=2")
    suspend fun getUsers(): List<User>
}
