package com.githubyss.mobile.common.kit.design_pattern.factory_simple

import com.githubyss.mobile.common.kit.design_pattern.entity.car.Car
import com.githubyss.mobile.common.kit.design_pattern.entity.car.CarType
import com.githubyss.mobile.common.kit.design_pattern.entity.math_operator.DyadicOperation
import com.githubyss.mobile.common.kit.design_pattern.entity.math_operator.MathOperatorType
import com.githubyss.mobile.common.kit.design_pattern.entity.math_operator.UnaryOperation
import com.githubyss.mobile.common.kit.design_pattern.entity.ship.Ship
import com.githubyss.mobile.common.kit.design_pattern.entity.ship.ShipType
import com.githubyss.mobile.common.kit.design_pattern.entity.ship.WarShip


private const val PRE_NUMBER = 3.0
private const val POST_NUMBER = 9.0

fun factorySimple() {
    val shipFactory: ShipFactory = ShipFactory()
    val cruiseShip: Ship = shipFactory.create(ShipType.CRUISE_SHIP)
    val warShip: Ship = shipFactory.create(ShipType.WAR_SHIP)
    cruiseShip.weighAnchor()
    cruiseShip.dropAnchor()
    warShip.weighAnchor()
    warShip.dropAnchor()
    (warShip as WarShip).launchMissile()

    val carFactory: CarFactory = CarFactory()
    val sportCar: Car = carFactory.create(CarType.SPORT_CAR)
    val jeepCar: Car = carFactory.create(CarType.JEEP_CAR)
    sportCar.drive()
    sportCar.selfNavigation()
    jeepCar.drive()
    jeepCar.selfNavigation()

    val operationFactory = OperationFactory()
    val equ: UnaryOperation = operationFactory.create(MathOperatorType.EQU) as UnaryOperation
    val add: DyadicOperation = operationFactory.create(MathOperatorType.ADD) as DyadicOperation
    val sub: DyadicOperation = operationFactory.create(MathOperatorType.SUB) as DyadicOperation
    val mul: DyadicOperation = operationFactory.create(MathOperatorType.MUL) as DyadicOperation
    val div: DyadicOperation = operationFactory.create(MathOperatorType.DIV) as DyadicOperation
    println("$PRE_NUMBER ${add.operator()} $POST_NUMBER ${equ.operator()} ${add.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${sub.operator()} $POST_NUMBER ${equ.operator()} ${sub.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${mul.operator()} $POST_NUMBER ${equ.operator()} ${mul.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${div.operator()} $POST_NUMBER ${equ.operator()} ${div.calculate(PRE_NUMBER, POST_NUMBER)}}")
}
