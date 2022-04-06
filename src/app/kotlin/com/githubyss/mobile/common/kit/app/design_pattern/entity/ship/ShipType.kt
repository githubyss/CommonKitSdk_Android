package com.githubyss.mobile.common.kit.app.design_pattern.entity.ship


sealed class ShipType {
    object CRUISE_SHIP : ShipType()
    object WAR_SHIP : ShipType()
}