package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder.Product
import com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder.ProductBuilderAbstract
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcreteInline


object ProductBuilderDirectorByFactory {
    inline fun <reified B : ProductBuilderAbstract> build(): Product? {
        val factory: FactoryConcreteInline<B> = FactoryConcreteInline<B>()
        val builder: ProductBuilderAbstract? = factory.create<B>()
        builder?.let {
            builder.addPartA()
            builder.addPartB()
        }
        return builder?.create()
    }
}