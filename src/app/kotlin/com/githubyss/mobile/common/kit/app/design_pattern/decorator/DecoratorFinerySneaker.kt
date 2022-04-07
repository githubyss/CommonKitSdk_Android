package com.githubyss.mobile.common.kit.app.design_pattern.decorator


class DecoratorFinerySneaker : DecoratorFinery() {
    override fun show() {
        print("破球鞋")
        super.show()
    }
}