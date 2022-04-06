package com.githubyss.mobile.common.kit.app.design_pattern.entity.cash

import kotlin.math.floor


class CashStrategyReturn(var moneyCondition: Double = 0.0, var moneyReturn: Double = 0.0) : ICashStrategy {
    override fun acceptCash(money: Double): Double {
        val moneyCashed = when {
            moneyCondition <= 0.0 || moneyReturn <= 0.0 -> money
            money / moneyCondition > 1 -> money - floor(money / moneyCondition) * moneyReturn
            else -> money
        }
        println("消费:$moneyCashed")
        return moneyCashed
    }
}