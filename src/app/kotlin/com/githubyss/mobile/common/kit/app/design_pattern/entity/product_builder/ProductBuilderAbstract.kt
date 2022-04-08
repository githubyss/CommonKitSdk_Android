package com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder


abstract class ProductBuilderAbstract {
    abstract fun addPartA()
    abstract fun addPartB()
    abstract fun create(): Product
}