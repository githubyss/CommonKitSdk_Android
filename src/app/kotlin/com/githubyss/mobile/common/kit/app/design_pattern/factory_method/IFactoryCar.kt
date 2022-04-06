package com.githubyss.mobile.common.kit.app.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.ICar


interface IFactoryCar {
    fun create(): ICar
}