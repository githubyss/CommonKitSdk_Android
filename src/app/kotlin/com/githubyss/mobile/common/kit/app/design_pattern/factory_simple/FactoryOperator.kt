package com.githubyss.mobile.common.kit.app.design_pattern.factory_simple

import com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator.*


object FactoryOperator {
    fun create(@OperatorType type: String): IOperator {
        return when (type) {
            OperatorType.ADD -> OperatorAdd()
            OperatorType.SUB -> OperatorSub()
            OperatorType.MUL -> OperatorMul()
            OperatorType.DIV -> OperatorDiv()
            OperatorType.EQU -> OperatorEqu()
            else -> OperatorNan()
        }
    }
}
