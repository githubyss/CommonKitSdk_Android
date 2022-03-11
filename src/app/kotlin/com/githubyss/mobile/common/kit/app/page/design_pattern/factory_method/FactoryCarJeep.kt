package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.car.CarJeep
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.car.ICar


object FactoryCarJeep : IFactoryCar {
    override fun create(): ICar {
        return CarJeep()
    }
}