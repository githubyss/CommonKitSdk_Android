package com.githubyss.mobile.common.kit.app.design_pattern.decorator


class DecoratorStringDecrypt1 : DecoratorStringDecrypt() {
    override fun decrypt(input: String): String {
        return super.decrypt(input).replace("*", "")
    }
}