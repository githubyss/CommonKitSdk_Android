package com.githubyss.mobile.common.kit.design_pattern.factory.simple.car


/**
 * ConcreteAudiCarFactory
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 17:24:08
 */
class ConcreteAudiCarFactory {
    fun <T : AbstractAudiCar> createAudiCar(clz: Class<T>): T {
        var audiCar: AbstractAudiCar? = null
        try {
            audiCar = Class.forName(clz.name).newInstance() as AbstractAudiCar
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return audiCar as T
    }
}
