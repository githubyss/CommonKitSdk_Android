package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.car


/**
 * CarSport
 * 跑车
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 17:22:18
 */
class CarSport : ICar {
    override fun drive() {
        println("跑车:启动")
    }

    override fun selfNavigation() {
        println("跑车:自动导航")
    }
}