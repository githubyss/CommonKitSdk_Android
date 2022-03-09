package com.githubyss.mobile.common.kit.design_pattern.factory_simple

import com.githubyss.mobile.common.kit.design_pattern.entity.ship.CruiseShip
import com.githubyss.mobile.common.kit.design_pattern.entity.ship.Ship
import com.githubyss.mobile.common.kit.design_pattern.entity.ship.ShipType
import com.githubyss.mobile.common.kit.design_pattern.entity.ship.WarShip


class ShipFactory {
    fun create(type: ShipType): Ship {
        return when (type) {
            ShipType.CRUISE_SHIP -> CruiseShip()
            ShipType.WAR_SHIP -> WarShip()
        }
    }
}
