package com.githubyss.mobile.common.kit.app.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator.IOperator


interface IFactoryOperator {
    fun create(): IOperator
}