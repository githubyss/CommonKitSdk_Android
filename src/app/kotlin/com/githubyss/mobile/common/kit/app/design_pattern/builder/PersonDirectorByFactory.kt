package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw.DrawCanvas
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw.DrawPaint
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw.PersonBuilder
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryAbstract
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryAbstractKClass
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcrete
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcreteKClass


object PersonDirectorByFactory {
    inline fun <reified B : PersonBuilder> build(canvas: DrawCanvas, paint: DrawPaint) {
        val personBuilderFactory: FactoryAbstract<B> = FactoryConcrete<B>()
        val personBuilder: PersonBuilder? = personBuilderFactory.create<B>(B::class.java, canvas, paint)
        personBuilder?.let {
            personBuilder.buildHead()
            personBuilder.buildBody()
            personBuilder.buildArnLeft()
            personBuilder.buildArmRight()
            personBuilder.buildLegLeft()
            personBuilder.buildLegRight()
        }
    }

    inline fun <reified B : PersonBuilder> buildKClass(canvas: DrawCanvas, paint: DrawPaint) {
        val personBuilderFactoryKClass: FactoryAbstractKClass<B> = FactoryConcreteKClass<B>()
        val personBuilder: PersonBuilder? = personBuilderFactoryKClass.create<B>(B::class, canvas, paint)
        personBuilder?.let {
            personBuilder.buildHead()
            personBuilder.buildBody()
            personBuilder.buildArnLeft()
            personBuilder.buildArmRight()
            personBuilder.buildLegLeft()
            personBuilder.buildLegRight()
        }
    }
}