package com.githubyss.mobile.common.kit.app.design_pattern.entity.person_draw


class PersonFatBuilder(canvas: DrawCanvas, paint: DrawPaint) : PersonBuilder(canvas, paint) {
    override fun buildHead() {
        println("胖子：头")
        // canvas.drawCircle(0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildBody() {
        println("胖子：躯干")
        // canvas.drawCircle(0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildArnLeft() {
        println("胖子：左胳膊")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildArmRight() {
        println("胖子：右胳膊")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildLegLeft() {
        println("胖子：左腿")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun buildLegRight() {
        println("胖子：右腿")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }
}