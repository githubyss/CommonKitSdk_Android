package com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator


interface IOperator {
    @OperatorType
    fun operator(): String
}