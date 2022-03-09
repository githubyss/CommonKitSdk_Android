package com.githubyss.mobile.common.kit.design_pattern.entity.car


/**
 * JeepCar
 * 越野车
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 17:23:28
 */
class JeepCar : Car() {
    override fun drive() {
        println("越野车:启动")
    }

    override fun selfNavigation() {
        println("越野车:自动导航")
    }
}
