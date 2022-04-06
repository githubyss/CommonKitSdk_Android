package com.githubyss.mobile.common.kit.app.design_pattern.decorator


class DecoratorFineryTie : DecoratorFinery() {
    override fun show() {
        print("领带")
        super.show()
    }
}