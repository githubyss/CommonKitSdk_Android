package com.githubyss.mobile.common.kit.app.design_pattern.entity.stock


class Stock2 : IStock {
    override fun buy() {
        println("股票2买入")
    }

    override fun sell() {
        println("股票2卖出")
    }
}