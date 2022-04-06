package com.githubyss.mobile.common.kit.app.design_pattern.decorator


class DecoratorStringEncrypt2 : DecoratorStringEncrypt() {
    override fun encrypt(input: String): String {
        return "##${super.encrypt(input)}##"
    }
}