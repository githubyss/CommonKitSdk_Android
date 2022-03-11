package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.math_operator


class OperatorMul : IOperatorDyadic {
    override fun calculate(preNumber: Double, postNumber: Double): Double {
        return preNumber * postNumber
    }

    @OperatorType
    override fun operator(): String {
        return OperatorType.MUL
    }
}