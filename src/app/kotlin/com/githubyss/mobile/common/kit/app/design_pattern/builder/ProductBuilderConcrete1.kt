package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder.Product


class ProductBuilderConcrete1 : ProductBuilderAbstract() {
    private val product: Product = Product()

    override fun addPartA() {
        product.add("产品1：部件A")
    }

    override fun addPartB() {
        product.add("产品1：部件B")

    }

    override fun build(): Product {
        return product
    }
}