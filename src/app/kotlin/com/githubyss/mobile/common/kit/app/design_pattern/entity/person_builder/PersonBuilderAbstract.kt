package com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.draw.DrawCanvas
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.draw.DrawPaint


abstract class PersonBuilderAbstract(protected val canvas: DrawCanvas, protected val paint: DrawPaint) {
    abstract fun addHead()
    abstract fun addBody()
    abstract fun addArmLeft()
    abstract fun addArmRight()
    abstract fun addLegLeft()
    abstract fun addLegRight()
}