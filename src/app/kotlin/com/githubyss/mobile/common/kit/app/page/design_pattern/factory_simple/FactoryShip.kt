package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_simple

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.IShip
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.ShipCruise
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.ShipType
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.ShipWar


object FactoryShip {
    fun create(type: ShipType): IShip {
        return when (type) {
            ShipType.CRUISE_SHIP -> ShipCruise()
            ShipType.WAR_SHIP -> ShipWar()
        }
    }
}
