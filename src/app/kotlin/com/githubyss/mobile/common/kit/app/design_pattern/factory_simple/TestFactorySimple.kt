package com.githubyss.mobile.common.kit.app.design_pattern.factory_simple

import com.githubyss.mobile.common.kit.app.design_pattern.POST_NUMBER
import com.githubyss.mobile.common.kit.app.design_pattern.PRE_NUMBER
import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.CarType
import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.ICar
import com.githubyss.mobile.common.kit.app.design_pattern.entity.cash.ICashStrategy
import com.githubyss.mobile.common.kit.app.design_pattern.entity.leifeng.Leifeng
import com.githubyss.mobile.common.kit.app.design_pattern.entity.leifeng.LeifengType
import com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator.IOperatorDyadic
import com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator.IOperatorUnary
import com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator.OperatorType
import com.githubyss.mobile.common.kit.app.design_pattern.entity.ship.IShip
import com.githubyss.mobile.common.kit.app.design_pattern.entity.ship.ShipType
import com.githubyss.mobile.common.kit.app.design_pattern.entity.ship.ShipWar


fun factorySimple() {
    val shipCruise: IShip = FactoryShip.create(ShipType.CRUISE_SHIP)
    val shipWar: IShip = FactoryShip.create(ShipType.WAR_SHIP)
    shipCruise.weighAnchor()
    shipCruise.dropAnchor()
    shipWar.weighAnchor()
    shipWar.dropAnchor()
    (shipWar as ShipWar).launchMissile()
    println()

    val carSport: ICar = FactoryCar.create(CarType.SPORT_CAR)
    val carJeep: ICar = FactoryCar.create(CarType.JEEP_CAR)
    carSport.drive()
    carSport.selfNavigation()
    carJeep.drive()
    carJeep.selfNavigation()
    println()

    val operatorEqu: IOperatorUnary = FactoryOperator.create(OperatorType.EQU) as IOperatorUnary
    val operatorAdd: IOperatorDyadic = FactoryOperator.create(OperatorType.ADD) as IOperatorDyadic
    val operatorSub: IOperatorDyadic = FactoryOperator.create(OperatorType.SUB) as IOperatorDyadic
    val operatorMul: IOperatorDyadic = FactoryOperator.create(OperatorType.MUL) as IOperatorDyadic
    val operatorDiv: IOperatorDyadic = FactoryOperator.create(OperatorType.DIV) as IOperatorDyadic
    println("$PRE_NUMBER ${operatorAdd.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorAdd.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorSub.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorSub.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorMul.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorMul.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorDiv.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorDiv.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println()

    val leifeng1: Leifeng = FactoryLeifeng.create(LeifengType.UNDERGRADUATE)
    val leifeng2: Leifeng = FactoryLeifeng.create(LeifengType.UNDERGRADUATE)
    val leifeng3: Leifeng = FactoryLeifeng.create(LeifengType.UNDERGRADUATE)
    val leifeng4: Leifeng = FactoryLeifeng.create(LeifengType.VOLUNTEER)
    leifeng1.sweep()
    leifeng2.wash()
    leifeng3.buyRice()
    leifeng4.buyRice()
    println()

    val cashStrategyNormal: ICashStrategy = FactoryCash.create("正常收费")
    val cashStrategyRebate: ICashStrategy = FactoryCash.create("打8折")
    val cashStrategyReturn: ICashStrategy = FactoryCash.create("满300返100")
    cashStrategyNormal.acceptCash(1000.0)
    cashStrategyRebate.acceptCash(1000.0)
    cashStrategyReturn.acceptCash(1000.0)
    println()
}
