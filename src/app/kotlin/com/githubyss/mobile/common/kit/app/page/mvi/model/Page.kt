package com.githubyss.mobile.common.kit.app.page.mvi.model

import com.squareup.moshi.Json


data class Page(@Json(name = "page") val page: Int = 0,
                @Json(name = "per_page") val per_page: Int = 0,
                @Json(name = "total") val total: Int = 0,
                @Json(name = "total_pages") val total_pages: Int = 0,
                @Json(name = "data") val data: List<User> = emptyList()
)
