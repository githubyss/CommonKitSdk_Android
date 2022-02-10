package com.githubyss.mobile.common.kit.app.page.json_utils.model

import com.google.gson.annotations.SerializedName


data class RootResponse<T>(
    @SerializedName("code")
    var code: String,

    @SerializedName("message")
    var message: String,

    @SerializedName("data")
    var data: T
)
