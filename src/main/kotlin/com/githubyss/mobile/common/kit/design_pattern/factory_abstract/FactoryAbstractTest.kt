package com.githubyss.mobile.common.kit.design_pattern.factory_abstract

import com.githubyss.mobile.common.kit.design_pattern.entity.car.Car
import com.githubyss.mobile.common.kit.design_pattern.entity.car.JeepCar
import com.githubyss.mobile.common.kit.design_pattern.entity.car.SportCar
import com.githubyss.mobile.common.kit.design_pattern.entity.math_operator.*
import com.githubyss.mobile.common.kit.design_pattern.entity.ship.CruiseShip
import com.githubyss.mobile.common.kit.design_pattern.entity.ship.Ship
import com.githubyss.mobile.common.kit.design_pattern.entity.ship.WarShip


private const val PRE_NUMBER = 3.0
private const val POST_NUMBER = 9.0

fun factoryAbstract() {
    val shipFactory: AbstractFactory<Ship> = ConcreteFactory<Ship>()
    val cruiseShip: Ship = shipFactory.create<CruiseShip>(CruiseShip::class.java)
    val warShip: Ship = shipFactory.create<WarShip>(WarShip::class.java)
    cruiseShip.weighAnchor()
    cruiseShip.dropAnchor()
    warShip.weighAnchor()
    warShip.dropAnchor()
    (warShip as WarShip).launchMissile()

    val carFactory: ConcreteInlineFactory<Car> = ConcreteInlineFactory<Car>()
    val sportCar: Car = carFactory.create<SportCar>()
    val jeepCar: Car = carFactory.create<JeepCar>()
    sportCar.drive()
    sportCar.selfNavigation()
    jeepCar.drive()
    jeepCar.selfNavigation()

    val unaryOperationFactory: ConcreteInlineFactory<UnaryOperation> = ConcreteInlineFactory<UnaryOperation>()
    val equ: UnaryOperation = unaryOperationFactory.create<Equ>()
    val dyadicOperationFactory: ConcreteInlineFactory<DyadicOperation> = ConcreteInlineFactory<DyadicOperation>()
    val add: DyadicOperation = dyadicOperationFactory.create<Add>()
    val sub: DyadicOperation = dyadicOperationFactory.create<Sub>()
    val mul: DyadicOperation = dyadicOperationFactory.create<Mul>()
    val div: DyadicOperation = dyadicOperationFactory.create<Div>()
    println("$PRE_NUMBER ${add.operator()} $POST_NUMBER ${equ.operator()} ${add.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${sub.operator()} $POST_NUMBER ${equ.operator()} ${sub.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${mul.operator()} $POST_NUMBER ${equ.operator()} ${mul.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${div.operator()} $POST_NUMBER ${equ.operator()} ${div.calculate(PRE_NUMBER, POST_NUMBER)}}")
}
