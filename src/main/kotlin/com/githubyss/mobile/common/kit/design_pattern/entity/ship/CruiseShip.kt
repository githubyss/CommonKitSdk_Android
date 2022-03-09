package com.githubyss.mobile.common.kit.design_pattern.entity.ship


/**
 * CruiseShip
 * 游轮
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 14:49:56
 */
class CruiseShip : Ship() {
    override fun weighAnchor() {
        println("游轮:启航")
    }

    override fun dropAnchor() {
        println("游轮:抛锚")
    }
}
