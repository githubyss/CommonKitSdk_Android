package com.githubyss.mobile.common.kit.design_pattern.entity.math_operator


abstract class UnaryOperation : Operation() {
    abstract fun calculate(number: Double): Double
}
