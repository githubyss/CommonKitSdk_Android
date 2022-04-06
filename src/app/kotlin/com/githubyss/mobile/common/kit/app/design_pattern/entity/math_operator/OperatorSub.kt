package com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator


class OperatorSub : IOperatorDyadic {
    override fun calculate(preNumber: Double, postNumber: Double): Double {
        return preNumber - postNumber
    }

    @OperatorType
    override fun operator(): String {
        return OperatorType.SUB
    }
}