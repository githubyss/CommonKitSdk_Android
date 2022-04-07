package com.githubyss.mobile.common.kit.app.design_pattern.decorator


class DecoratorFinerySuit : DecoratorFinery() {
    override fun show() {
        print("西装")
        super.show()
    }
}