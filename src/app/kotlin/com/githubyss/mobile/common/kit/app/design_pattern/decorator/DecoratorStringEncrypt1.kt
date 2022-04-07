package com.githubyss.mobile.common.kit.app.design_pattern.decorator


class DecoratorStringEncrypt1 : DecoratorStringEncrypt() {
    override fun encrypt(input: String): String {
        return "**${super.encrypt(input)}**"
    }
}