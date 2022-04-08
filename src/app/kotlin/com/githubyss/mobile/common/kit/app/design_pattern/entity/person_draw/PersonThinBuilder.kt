package com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw


class PersonThinBuilder(canvas: DrawCanvas, paint: DrawPaint) : PersonBuilder(canvas, paint) {
    override fun buildHead() {
        println("瘦子：头")
        // canvas.drawCircle(0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildBody() {
        println("瘦子：躯干")
        // canvas.drawRect(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildArnLeft() {
        println("瘦子：左胳膊")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildArmRight() {
        println("瘦子：右胳膊")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildLegLeft() {
        println("瘦子：左腿")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildLegRight() {
        println("瘦子：右腿")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }
}