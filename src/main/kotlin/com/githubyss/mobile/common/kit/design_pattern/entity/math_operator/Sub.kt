package com.githubyss.mobile.common.kit.design_pattern.entity.math_operator


class Sub : DyadicOperation() {
    override fun calculate(preNumber: Double, postNumber: Double): Double {
        return preNumber - postNumber
    }

    @MathOperatorType
    override fun operator(): String {
        return MathOperatorType.SUB
    }
}
