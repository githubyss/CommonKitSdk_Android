package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.PersonBuilderAbstract
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.PersonBuilderFat
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.PersonBuilderThin
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.draw.DrawCanvas
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.draw.DrawPaint
import com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder.ProductBuilderAbstract
import com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder.ProductBuilderConcrete1
import com.githubyss.mobile.common.kit.app.design_pattern.entity.product_builder.ProductBuilderConcrete2


/**
 * TestBuilder
 *
 * 使用场景：
 *
 * 建造者模式是逐步建造产品的，建造者的 Builder 类里的建造方法必须要足够普遍，以便为各种类型的具体建造者构造。
 *
 * 用于创建一些复杂的对象，这些对象内部构建间的建造顺序通常是稳定的，但对象内部具体的构建通常面临着复杂的变化。
 *
 * 使建造代码和表示代码分离，由于建造者隐藏了该产品是如何组装的，所以若需要改变一个产品的内部表示，只需要再定义一个具体的建造者就可以了。
 *
 * 建造者模式是在当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时适用的模式。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/07 14:34:20
 */
fun builder() {
    val canvas: DrawCanvas = DrawCanvas()
    val paint: DrawPaint = DrawPaint()

    println("Builder 模式")
    val personBuilderThin: PersonBuilderAbstract = PersonBuilderThin(canvas, paint)
    val personBuilderFat: PersonBuilderAbstract = PersonBuilderFat(canvas, paint)
    var personBuilderDirector: PersonBuilderDirector = PersonBuilderDirector(personBuilderThin)
    personBuilderDirector.build()
    personBuilderDirector = PersonBuilderDirector(personBuilderFat)
    personBuilderDirector.build()
    println()

    val productBuilderConcrete1: ProductBuilderAbstract = ProductBuilderConcrete1()
    val productBuilderConcrete2: ProductBuilderAbstract = ProductBuilderConcrete2()
    val productBuilderDirector: ProductBuilderDirector = ProductBuilderDirector()
    val product1 = productBuilderDirector.build(productBuilderConcrete1)
    product1.show()
    val product2 = productBuilderDirector.build(productBuilderConcrete2)
    product2.show()
    println()

    println("Builder 模式 & AbstractFactory 模式")
    PersonBuilderDirectorByFactory.buildByJClassInline<PersonBuilderThin>(canvas, paint)
    PersonBuilderDirectorByFactory.buildByKClassInline<PersonBuilderFat>(canvas, paint)
    PersonBuilderDirectorByFactory.buildByJClass<PersonBuilderThin>(canvas, paint)
    PersonBuilderDirectorByFactory.buildByKClass<PersonBuilderFat>(canvas, paint)
    println()

    ProductBuilderDirectorByFactory.build<ProductBuilderConcrete1>()?.show()
    ProductBuilderDirectorByFactory.build<ProductBuilderConcrete2>()?.show()
    println()
}