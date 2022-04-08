package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder.Product
import com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder.ProductBuilderAbstract


class ProductBuilderDirector {
    fun build(builder: ProductBuilderAbstract): Product {
        builder.addPartA()
        builder.addPartB()
        return builder.create()
    }
}