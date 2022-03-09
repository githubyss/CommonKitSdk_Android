package com.githubyss.mobile.common.kit.design_pattern.factory_simple

import com.githubyss.mobile.common.kit.design_pattern.entity.car.SportCar
import com.githubyss.mobile.common.kit.design_pattern.entity.car.JeepCar
import com.githubyss.mobile.common.kit.design_pattern.entity.car.Car
import com.githubyss.mobile.common.kit.design_pattern.entity.car.CarType


class CarFactory {
    fun create(type: CarType): Car {
        return when (type) {
            CarType.SPORT_CAR -> SportCar()
            CarType.JEEP_CAR -> JeepCar()
        }
    }
}
