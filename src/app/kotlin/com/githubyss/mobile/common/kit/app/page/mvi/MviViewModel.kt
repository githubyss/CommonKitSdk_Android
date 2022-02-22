package com.githubyss.mobile.common.kit.app.page.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


class MviViewModel(private val repository: MviRepository) : ViewModel() {
    val userIntent = Channel<MviIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<MviState>(MviState.Idle)
    val state: StateFlow<MviState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MviIntent.FetchUser -> fetchUser()
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _state.value = MviState.Loading
            _state.value = try {
                MviState.Users(repository.getUsers())
            }
            catch (e: Exception) {
                MviState.Error(e.localizedMessage)
            }
        }
    }
}
