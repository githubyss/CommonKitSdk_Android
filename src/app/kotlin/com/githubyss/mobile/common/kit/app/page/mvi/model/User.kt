package com.githubyss.mobile.common.kit.app.page.mvi.model

import com.squareup.moshi.Json


data class User(@Json(name = "id") val id: Int = 0,
                @Json(name = "first_name") val first_name: String = "",
                @Json(name = "email") val email: String = "",
                @Json(name = "avatar") val avatar: String = ""
)
