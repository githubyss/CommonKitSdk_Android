package com.githubyss.mobile.common.kit.design_pattern.factory_simple

import com.githubyss.mobile.common.kit.design_pattern.entity.math_operator.*


class OperationFactory {
    fun create(@MathOperatorType type: String): Operation {
        return when (type) {
            MathOperatorType.ADD -> Add()
            MathOperatorType.SUB -> Sub()
            MathOperatorType.MUL -> Mul()
            MathOperatorType.DIV -> Div()
            MathOperatorType.EQU -> Equ()
            else -> Nan()
        }
    }
}
