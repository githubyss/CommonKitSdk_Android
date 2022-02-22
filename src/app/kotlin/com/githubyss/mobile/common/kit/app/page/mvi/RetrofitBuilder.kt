package com.githubyss.mobile.common.kit.app.page.mvi

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitBuilder {
    private const val BASE_URL = "https://reqres.in/api/"
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}
