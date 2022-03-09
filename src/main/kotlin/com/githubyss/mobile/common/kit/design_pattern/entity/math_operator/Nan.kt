package com.githubyss.mobile.common.kit.design_pattern.entity.math_operator


class Nan : Operation() {
    @MathOperatorType
    override fun operator(): String {
        return MathOperatorType.NAN
    }
}
