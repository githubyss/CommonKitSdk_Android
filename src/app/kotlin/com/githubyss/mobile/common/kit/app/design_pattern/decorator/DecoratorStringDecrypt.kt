package com.githubyss.mobile.common.kit.app.design_pattern.decorator

import com.githubyss.mobile.common.kit.app.design_pattern.entity.string_handle.IStringDecrypt


abstract class DecoratorStringDecrypt : IStringDecrypt {
    private var stringDecrypt: IStringDecrypt? = null

    fun decorator(stringDecrypt: IStringDecrypt) {
        this.stringDecrypt = stringDecrypt
    }

    override fun decrypt(input: String): String {
        return stringDecrypt?.decrypt(input) ?: input
    }
}