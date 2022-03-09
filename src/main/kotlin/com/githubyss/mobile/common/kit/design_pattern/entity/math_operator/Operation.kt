package com.githubyss.mobile.common.kit.design_pattern.entity.math_operator


abstract class Operation {
    @MathOperatorType
    abstract fun operator(): String
}
