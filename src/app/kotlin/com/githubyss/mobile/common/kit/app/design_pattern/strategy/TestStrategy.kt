package com.githubyss.mobile.common.kit.app.design_pattern.strategy

import com.githubyss.mobile.common.kit.app.design_pattern.entity.cash.CashStrategyNormal
import com.githubyss.mobile.common.kit.app.design_pattern.entity.cash.CashStrategyRebate
import com.githubyss.mobile.common.kit.app.design_pattern.entity.cash.CashStrategyReturn


fun strategy() {
    val strategyCashNormal: StrategyContextCash = StrategyContextCash(CashStrategyNormal())
    val strategyCashRebate: StrategyContextCash = StrategyContextCash(CashStrategyRebate(0.8))
    val strategyCashReturn: StrategyContextCash = StrategyContextCash(CashStrategyReturn(300.0, 100.0))
    strategyCashNormal.acceptCash(1000.0)
    strategyCashRebate.acceptCash(1000.0)
    strategyCashReturn.acceptCash(1000.0)
    println()

    val strategyCashNormalFactorySimple: StrategyContextCashFactorySimple = StrategyContextCashFactorySimple("正常收费")
    val strategyCashRebateFactorySimple: StrategyContextCashFactorySimple = StrategyContextCashFactorySimple("打8折")
    val strategyCashReturnFactorySimple: StrategyContextCashFactorySimple = StrategyContextCashFactorySimple("满300返100")
    strategyCashNormalFactorySimple.acceptCash(1000.0)
    strategyCashRebateFactorySimple.acceptCash(1000.0)
    strategyCashReturnFactorySimple.acceptCash(1000.0)
    println()
}