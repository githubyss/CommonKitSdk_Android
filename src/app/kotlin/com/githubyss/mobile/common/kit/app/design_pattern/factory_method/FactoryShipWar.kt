package com.githubyss.mobile.common.kit.app.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.design_pattern.entity.ship.IShip
import com.githubyss.mobile.common.kit.app.design_pattern.entity.ship.ShipWar


object FactoryShipWar : IFactoryShip {
    override fun create(): IShip {
        return ShipWar()
    }
}