package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_method

import com.githubyss.mobile.common.kit.app.page.design_pattern.POST_NUMBER
import com.githubyss.mobile.common.kit.app.page.design_pattern.PRE_NUMBER
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.car.ICar
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.Leifeng
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.math_operator.IOperatorDyadic
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.math_operator.IOperatorUnary
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.IShip
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.ShipWar


fun factoryMethod() {
    val shipCruise: IShip = FactoryShipCruise.create()
    val shipWar: IShip = FactoryShipWar.create()
    shipCruise.weighAnchor()
    shipCruise.dropAnchor()
    shipWar.weighAnchor()
    shipWar.dropAnchor()
    (shipWar as ShipWar).launchMissile()

    val carSport: ICar = FactoryCarSport.create()
    val carJeep: ICar = FactoryCarJeep.create()
    carSport.drive()
    carSport.selfNavigation()
    carJeep.drive()
    carJeep.selfNavigation()

    val operatorEqu: IOperatorUnary = FactoryOperatorEqu.create() as IOperatorUnary
    val operatorAdd: IOperatorDyadic = FactoryOperatorAdd.create() as IOperatorDyadic
    val operatorSub: IOperatorDyadic = FactoryOperatorSub.create() as IOperatorDyadic
    val operatorMul: IOperatorDyadic = FactoryOperatorMul.create() as IOperatorDyadic
    val operatorDiv: IOperatorDyadic = FactoryOperatorDiv.create() as IOperatorDyadic
    println("$PRE_NUMBER ${operatorAdd.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorAdd.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorSub.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorSub.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorMul.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorMul.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorDiv.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorDiv.calculate(PRE_NUMBER, POST_NUMBER)}}")

    val leifeng1: Leifeng = FactoryLeifengUndergraduate.create()
    val leifeng2: Leifeng = FactoryLeifengUndergraduate.create()
    val leifeng3: Leifeng = FactoryLeifengUndergraduate.create()
    val leifeng4: Leifeng = FactoryLeifengVolunteer.create()
    leifeng1.sweep()
    leifeng2.wash()
    leifeng3.buyRice()
    leifeng4.buyRice()

    println()
}