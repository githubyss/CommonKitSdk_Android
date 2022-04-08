package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw.DrawCanvas
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw.DrawPaint
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw.PersonBuilder
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryAbstract
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcrete
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcreteInline


object PersonDirectorByFactory {
    inline fun <reified B : PersonBuilder> buildByJClassInline(canvas: DrawCanvas, paint: DrawPaint) {
        val personBuilderFactory: FactoryConcreteInline<B> = FactoryConcreteInline<B>()
        val personBuilder: PersonBuilder? = personBuilderFactory.create<B>(initArgs = arrayOf(canvas, paint))
        personBuilder?.let {
            personBuilder.buildHead()
            personBuilder.buildBody()
            personBuilder.buildArnLeft()
            personBuilder.buildArmRight()
            personBuilder.buildLegLeft()
            personBuilder.buildLegRight()
        }
    }

    inline fun <reified B : PersonBuilder> buildByKClassInline(canvas: DrawCanvas, paint: DrawPaint) {
        val personBuilderFactory: FactoryConcreteInline<B> = FactoryConcreteInline<B>()
        val personBuilder: PersonBuilder? = personBuilderFactory.create<B>(true, canvas, paint)
        personBuilder?.let {
            personBuilder.buildHead()
            personBuilder.buildBody()
            personBuilder.buildArnLeft()
            personBuilder.buildArmRight()
            personBuilder.buildLegLeft()
            personBuilder.buildLegRight()
        }
    }

    inline fun <reified B : PersonBuilder> buildByJClass(canvas: DrawCanvas, paint: DrawPaint) {
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

    inline fun <reified B : PersonBuilder> buildByKClass(canvas: DrawCanvas, paint: DrawPaint) {
        val personBuilderFactory: FactoryAbstract<B> = FactoryConcrete<B>()
        val personBuilder: PersonBuilder? = personBuilderFactory.create<B>(B::class, canvas, paint)
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