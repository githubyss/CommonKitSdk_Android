package com.githubyss.mobile.common.kit.app.design_pattern.builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.PersonBuilderAbstract
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.draw.DrawCanvas
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.draw.DrawPaint
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryAbstract
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcrete
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcreteInline


object PersonBuilderDirectorByFactory {
    inline fun <reified B : PersonBuilderAbstract> buildByJClassInline(canvas: DrawCanvas, paint: DrawPaint) {
        val factory: FactoryConcreteInline<B> = FactoryConcreteInline<B>()
        val builder: PersonBuilderAbstract? = factory.create<B>(initArgs = arrayOf(canvas, paint))
        builder?.let {
            builder.addHead()
            builder.addBody()
            builder.addArmLeft()
            builder.addArmRight()
            builder.addLegLeft()
            builder.addLegRight()
        }
    }

    inline fun <reified B : PersonBuilderAbstract> buildByKClassInline(canvas: DrawCanvas, paint: DrawPaint) {
        val factory: FactoryConcreteInline<B> = FactoryConcreteInline<B>()
        val builder: PersonBuilderAbstract? = factory.create<B>(true, canvas, paint)
        builder?.let {
            builder.addHead()
            builder.addBody()
            builder.addArmLeft()
            builder.addArmRight()
            builder.addLegLeft()
            builder.addLegRight()
        }
    }

    inline fun <reified B : PersonBuilderAbstract> buildByJClass(canvas: DrawCanvas, paint: DrawPaint) {
        val factory: FactoryAbstract<B> = FactoryConcrete<B>()
        val builder: PersonBuilderAbstract? = factory.create<B>(B::class.java, canvas, paint)
        builder?.let {
            builder.addHead()
            builder.addBody()
            builder.addArmLeft()
            builder.addArmRight()
            builder.addLegLeft()
            builder.addLegRight()
        }
    }

    inline fun <reified B : PersonBuilderAbstract> buildByKClass(canvas: DrawCanvas, paint: DrawPaint) {
        val factory: FactoryAbstract<B> = FactoryConcrete<B>()
        val builder: PersonBuilderAbstract? = factory.create<B>(B::class, canvas, paint)
        builder?.let {
            builder.addHead()
            builder.addBody()
            builder.addArmLeft()
            builder.addArmRight()
            builder.addLegLeft()
            builder.addLegRight()
        }
    }
}