package com.githubyss.mobile.common.kit.app.design_pattern.entity.stock


class Realty1 : IStock {
    override fun buy() {
        println("房地产1买入")
    }

    override fun sell() {
        println("房地产1卖出")
    }
}