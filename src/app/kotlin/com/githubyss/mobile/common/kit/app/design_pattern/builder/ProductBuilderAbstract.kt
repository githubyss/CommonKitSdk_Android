package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder.Product


abstract class ProductBuilderAbstract {
    abstract fun addPartA()
    abstract fun addPartB()
    abstract fun build(): Product
}