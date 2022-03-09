package com.githubyss.mobile.common.kit.design_pattern.entity.math_operator


class Div : DyadicOperation() {
    override fun calculate(preNumber: Double, postNumber: Double): Double {
        return if (postNumber != 0.0) preNumber / postNumber
        else {
            println("被除数不能为0")
            Double.NaN
        }
    }

    @MathOperatorType
    override fun operator(): String {
        return MathOperatorType.DIV
    }
}
