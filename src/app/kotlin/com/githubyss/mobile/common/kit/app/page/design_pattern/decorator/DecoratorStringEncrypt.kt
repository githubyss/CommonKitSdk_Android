package com.githubyss.mobile.common.kit.app.page.design_pattern.decorator

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.string_handle.IStringEncrypt


abstract class DecoratorStringEncrypt : IStringEncrypt {
    private var stringEncrypt: IStringEncrypt? = null

    fun decorator(stringEncrypt: IStringEncrypt) {
        this.stringEncrypt = stringEncrypt
    }

    override fun encrypt(input: String): String {
        return stringEncrypt?.encrypt(input) ?: input
    }
}