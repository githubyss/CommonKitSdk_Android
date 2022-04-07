package com.githubyss.mobile.common.kit.app.design_pattern.entity.stock


class Stock1 : IStock {
    override fun buy() {
        println("股票1买入")
    }

    override fun sell() {
        println("股票1卖出")
    }
}