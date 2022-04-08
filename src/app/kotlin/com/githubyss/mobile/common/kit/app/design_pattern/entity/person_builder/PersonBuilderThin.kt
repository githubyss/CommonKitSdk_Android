package com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.draw.DrawCanvas
import com.githubyss.mobile.common.kit.app.design_pattern.entity.person_builder.draw.DrawPaint


class PersonBuilderThin(canvas: DrawCanvas, paint: DrawPaint) : PersonBuilderAbstract(canvas, paint) {
    override fun addHead() {
        println("瘦子：头")
        // canvas.drawCircle(0.0F, 0.0F, 0.0F, paint)
    }

    override fun addBody() {
        println("瘦子：躯干")
        // canvas.drawRect(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun addArmLeft() {
        println("瘦子：左胳膊")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun addArmRight() {
        println("瘦子：右胳膊")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun addLegLeft() {
        println("瘦子：左腿")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }

    override fun addLegRight() {
        println("瘦子：右腿")
        // canvas.drawLine(0.0F, 0.0F, 0.0F, 0.0F, paint)
    }
}