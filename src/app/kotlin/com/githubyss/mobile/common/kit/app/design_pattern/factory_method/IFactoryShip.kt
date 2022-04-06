package com.githubyss.mobile.common.kit.app.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.design_pattern.entity.ship.IShip


interface IFactoryShip {
    fun create(): IShip
}