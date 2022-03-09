package com.githubyss.mobile.common.kit.design_pattern.entity.ship


/**
 * WarShip
 * 战舰
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 14:49:47
 */
class WarShip : Ship() {
    override fun weighAnchor() {
        println("战舰:启航")
    }

    override fun dropAnchor() {
        println("战舰:抛锚")
    }

    fun launchMissile() {
        println("战舰:发射导弹")
    }
}
