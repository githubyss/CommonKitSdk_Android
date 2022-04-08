package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw.*


/**
 * TestBuilder
 *
 * 使用场景：
 *
 * 建造者模式是逐步建造产品的，建造者的 Builder 类里的建造方法必须要足够普遍，以便为各种类型的具体建造者构造。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/07 14:34:20
 */
fun builder() {
    val canvas: DrawCanvas = DrawCanvas()
    val paint: DrawPaint = DrawPaint()

    println("Builder 模式")
    val personThinBuilder: PersonBuilder = PersonThinBuilder(canvas, paint)
    val personThinDirector: PersonDirector = PersonDirector(personThinBuilder)
    personThinDirector.build()
    val personFatBuilder: PersonBuilder = PersonFatBuilder(canvas, paint)
    val personFatDirector: PersonDirector = PersonDirector(personFatBuilder)
    personFatDirector.build()
    println()

    val productBuilderDirector: ProductBuilderDirector = ProductBuilderDirector()
    val productBuilderConcrete1: ProductBuilderAbstract = ProductBuilderConcrete1()
    productBuilderDirector.build(productBuilderConcrete1)
    val product1 = productBuilderConcrete1.build()
    product1.show()
    val productBuilderConcrete2: ProductBuilderAbstract = ProductBuilderConcrete2()
    productBuilderDirector.build(productBuilderConcrete2)
    val product2 = productBuilderConcrete2.build()
    product2.show()
    println()

    println("Builder 模式 & AbstractFactory 模式")
    PersonDirectorByFactory.buildByJClassInline<PersonThinBuilder>(canvas, paint)
    PersonDirectorByFactory.buildByKClassInline<PersonFatBuilder>(canvas, paint)
    PersonDirectorByFactory.buildByJClass<PersonThinBuilder>(canvas, paint)
    PersonDirectorByFactory.buildByKClass<PersonFatBuilder>(canvas, paint)
    println()

    ProductBuilderDirectorByFactory.build<ProductBuilderConcrete1>()?.show()
    ProductBuilderDirectorByFactory.build<ProductBuilderConcrete2>()?.show()
    println()
}