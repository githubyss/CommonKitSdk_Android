package com.githubyss.mobile.common.kit.app.design_pattern.factory_simple

import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.CarJeep
import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.CarSport
import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.CarType
import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.ICar


object FactoryCar {
    fun create(type: CarType): ICar {
        return when (type) {
            CarType.SPORT_CAR -> CarSport()
            CarType.JEEP_CAR -> CarJeep()
        }
    }
}
