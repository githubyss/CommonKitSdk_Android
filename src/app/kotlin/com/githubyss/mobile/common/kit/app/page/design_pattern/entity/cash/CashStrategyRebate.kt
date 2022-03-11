package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.cash


class CashStrategyRebate(var moneyRebate: Double = 1.0) : ICashStrategy {
    override fun acceptCash(money: Double): Double {
        val moneyCashed = money * moneyRebate
        println("消费:$moneyCashed")
        return moneyCashed
    }
}