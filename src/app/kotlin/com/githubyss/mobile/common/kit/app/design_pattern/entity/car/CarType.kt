package com.githubyss.mobile.common.kit.app.design_pattern.entity.car


sealed class CarType {
    object SPORT_CAR : CarType()
    object JEEP_CAR : CarType()
}