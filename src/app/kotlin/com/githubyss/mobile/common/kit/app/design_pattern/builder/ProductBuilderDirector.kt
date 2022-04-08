package com.githubyss.mobile.common.kit.app.design_pattern.builder


class ProductBuilderDirector {
    fun build(builder: ProductBuilderAbstract) {
        builder.addPartA()
        builder.addPartB()
    }
}