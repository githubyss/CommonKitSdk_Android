package com.githubyss.mobile.common.kit.app.page.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MviViewModel::class.java)) {
            return MviViewModel(MviRepository(apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
