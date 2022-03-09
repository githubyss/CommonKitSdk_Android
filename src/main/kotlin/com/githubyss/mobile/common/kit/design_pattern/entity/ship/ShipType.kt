package com.githubyss.mobile.common.kit.design_pattern.entity.ship


sealed class ShipType {
    object CRUISE_SHIP : ShipType()
    object WAR_SHIP : ShipType()
}
