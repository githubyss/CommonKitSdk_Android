package com.githubyss.mobile.common.kit.app.design_pattern.entity.stock


class Stock3 : IStock {
    override fun buy() {
        println("股票3买入")
    }

    override fun sell() {
        println("股票3卖出")
    }
}