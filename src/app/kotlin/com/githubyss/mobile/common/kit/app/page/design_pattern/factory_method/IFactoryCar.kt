package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.car.ICar


interface IFactoryCar {
    fun create(): ICar
}