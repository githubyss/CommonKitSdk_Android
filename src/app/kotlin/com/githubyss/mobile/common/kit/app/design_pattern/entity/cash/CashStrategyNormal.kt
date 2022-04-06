package com.githubyss.mobile.common.kit.app.design_pattern.entity.cash


class CashStrategyNormal : ICashStrategy {
    override fun acceptCash(money: Double): Double {
        val moneyCashed = money
        println("消费:$moneyCashed")
        return moneyCashed
    }
}