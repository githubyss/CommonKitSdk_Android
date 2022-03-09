package com.githubyss.mobile.common.kit.design_pattern.entity.math_operator


abstract class DyadicOperation:Operation() {
    abstract fun calculate(preNumber: Double, postNumber: Double): Double
}
