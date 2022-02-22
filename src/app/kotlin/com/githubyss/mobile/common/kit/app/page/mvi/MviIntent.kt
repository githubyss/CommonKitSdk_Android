package com.githubyss.mobile.common.kit.app.page.mvi


sealed class MviIntent {
    object FetchUser : MviIntent()
}
