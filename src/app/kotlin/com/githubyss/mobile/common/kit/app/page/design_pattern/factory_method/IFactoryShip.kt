package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.IShip


interface IFactoryShip {
    fun create(): IShip
}