package com.githubyss.mobile.common.kit.app.design_pattern.strategy

import com.githubyss.mobile.common.kit.app.design_pattern.entity.cash.ICashStrategy


class StrategyContextCash(private var cashStrategy: ICashStrategy) {
    fun acceptCash(money: Double): Double {
        return cashStrategy.acceptCash(money)
    }
}