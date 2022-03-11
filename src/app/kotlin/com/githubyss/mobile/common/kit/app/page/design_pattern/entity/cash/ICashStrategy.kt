package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.cash


interface ICashStrategy {
    fun acceptCash(money: Double): Double
}