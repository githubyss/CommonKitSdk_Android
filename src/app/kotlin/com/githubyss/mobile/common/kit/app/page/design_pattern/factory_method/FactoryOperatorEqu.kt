package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.math_operator.IOperator
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.math_operator.OperatorEqu


object FactoryOperatorEqu : IFactoryOperator {
    override fun create(): IOperator {
        return OperatorEqu()
    }
}