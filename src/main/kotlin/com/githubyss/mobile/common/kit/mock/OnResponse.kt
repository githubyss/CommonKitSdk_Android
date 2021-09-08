package com.githubyss.mobile.common.kit.mock


interface OnResponse<M> {
    fun onSuccess(model: M)
    fun onFail(message: String)
}
