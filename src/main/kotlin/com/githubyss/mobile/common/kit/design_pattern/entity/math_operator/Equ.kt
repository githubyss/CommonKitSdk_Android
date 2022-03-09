package com.githubyss.mobile.common.kit.design_pattern.entity.math_operator


class Equ : UnaryOperation() {
    override fun calculate(number: Double): Double {
        return Double.NaN
    }

    @MathOperatorType
    override fun operator(): String {
        return MathOperatorType.EQU
    }
}
