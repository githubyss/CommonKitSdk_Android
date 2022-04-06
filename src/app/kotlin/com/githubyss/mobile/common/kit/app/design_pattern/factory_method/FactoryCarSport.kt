package com.githubyss.mobile.common.kit.app.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.CarSport
import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.ICar


object FactoryCarSport : IFactoryCar {
    override fun create(): ICar {
        return CarSport()
    }
}