package com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator


interface IOperatorUnary : IOperator {
    fun calculate(number: Double): Double
}