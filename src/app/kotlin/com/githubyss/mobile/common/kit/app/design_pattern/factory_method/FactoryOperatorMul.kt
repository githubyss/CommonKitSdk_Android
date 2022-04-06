package com.githubyss.mobile.common.kit.app.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator.IOperator
import com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator.OperatorMul


object FactoryOperatorMul : IFactoryOperator {
    override fun create(): IOperator {
        return OperatorMul()
    }
}