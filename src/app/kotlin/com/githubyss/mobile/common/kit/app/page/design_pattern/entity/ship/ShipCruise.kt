package com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship


/**
 * ShipCruise
 * 游轮
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 14:49:56
 */
class ShipCruise : IShip {
    override fun weighAnchor() {
        println("游轮:启航")
    }

    override fun dropAnchor() {
        println("游轮:抛锚")
    }
}