package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.math_operator


class OperatorNan : IOperator {
    @OperatorType
    override fun operator(): String {
        return OperatorType.NAN
    }
}