package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.math_operator


interface IOperatorDyadic : IOperator {
    fun calculate(preNumber: Double, postNumber: Double): Double
}