package com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw


abstract class PersonBuilder(protected val canvas: DrawCanvas, protected val paint: DrawPaint) {
    abstract fun buildHead()
    abstract fun buildBody()
    abstract fun buildArnLeft()
    abstract fun buildArmRight()
    abstract fun buildLegLeft()
    abstract fun buildLegRight()
}