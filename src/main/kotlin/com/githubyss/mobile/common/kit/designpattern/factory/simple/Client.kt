package com.githubyss.mobile.common.kit.designpattern.factory.simple

import com.githubyss.mobile.common.kit.designpattern.factory.simple.car.AbstractAudiCar
import com.githubyss.mobile.common.kit.designpattern.factory.simple.car.ConcreteAudiCarFactory
import com.githubyss.mobile.common.kit.designpattern.factory.simple.car.ConcreteAudiQ3
import com.githubyss.mobile.common.kit.designpattern.factory.simple.car.ConcreteAudiQ5
import com.githubyss.mobile.common.kit.designpattern.factory.simple.product.*


/**
 * Client
 * 客户端
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 17:20:57
 */
class Client {
    fun main() {
        val factory: AbstractFactory = ConcreteFactory()
        var productA: AbstractProduct = factory.createProduct(ConcreteProductA::class.java)
        var productB: AbstractProduct = factory.createProduct(ConcreteProductB::class.java)
        
        val audiCarFactory = ConcreteAudiCarFactory()
        val audiQ3: AbstractAudiCar = audiCarFactory.createAudiCar(ConcreteAudiQ3::class.java)
        val audiQ5: AbstractAudiCar = audiCarFactory.createAudiCar(ConcreteAudiQ5::class.java)
        audiQ3.drive()
        audiQ3.selfNavigation()
        audiQ5.drive()
        audiQ5.selfNavigation()
    }
}
