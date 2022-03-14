package com.githubyss.mobile.common.kit.app.page.design_pattern.factory_abstract

import com.githubyss.mobile.common.kit.app.page.design_pattern.POST_NUMBER
import com.githubyss.mobile.common.kit.app.page.design_pattern.PRE_NUMBER
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.car.CarJeep
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.car.CarSport
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.car.ICar
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.Leifeng
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.LeifengUndergraduate
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.leifeng.LeifengVolunteer
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.math_operator.*
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.IShip
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.ShipCruise
import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.ship.ShipWar
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryAbstract
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcrete
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcreteInline


fun factoryAbstract() {
    val factoryShip: FactoryAbstract<IShip> = FactoryConcrete<IShip>()
    val shipCruise: IShip = factoryShip.create<ShipCruise>(ShipCruise::class.java)
    val shipWar: IShip = factoryShip.create<ShipWar>(ShipWar::class.java)
    shipCruise.weighAnchor()
    shipCruise.dropAnchor()
    shipWar.weighAnchor()
    shipWar.dropAnchor()
    (shipWar as ShipWar).launchMissile()
    println()

    val factoryCar: FactoryConcreteInline<ICar> = FactoryConcreteInline<ICar>()
    val carSport: ICar = factoryCar.create<CarSport>()
    val carJeep: ICar = factoryCar.create<CarJeep>()
    carSport.drive()
    carSport.selfNavigation()
    carJeep.drive()
    carJeep.selfNavigation()
    println()

    val factoryOperatorUnary: FactoryConcreteInline<IOperatorUnary> = FactoryConcreteInline<IOperatorUnary>()
    val operatorEqu: IOperatorUnary = factoryOperatorUnary.create<OperatorEqu>()
    val factoryOperatorDyadic: FactoryConcreteInline<IOperatorDyadic> = FactoryConcreteInline<IOperatorDyadic>()
    val operatorAdd: IOperatorDyadic = factoryOperatorDyadic.create<OperatorAdd>()
    val operatorSub: IOperatorDyadic = factoryOperatorDyadic.create<OperatorSub>()
    val operatorMul: IOperatorDyadic = factoryOperatorDyadic.create<OperatorMul>()
    val operatorDiv: IOperatorDyadic = factoryOperatorDyadic.create<OperatorDiv>()
    println("$PRE_NUMBER ${operatorAdd.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorAdd.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorSub.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorSub.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorMul.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorMul.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorDiv.operator()} $POST_NUMBER ${operatorEqu.operator()} ${operatorDiv.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println()

    val factoryLeifeng: FactoryConcreteInline<Leifeng> = FactoryConcreteInline<Leifeng>()
    val leifeng1: Leifeng = factoryLeifeng.create<LeifengUndergraduate>()
    val leifeng2: Leifeng = factoryLeifeng.create<LeifengUndergraduate>()
    val leifeng3: Leifeng = factoryLeifeng.create<LeifengUndergraduate>()
    val leifeng4: Leifeng = factoryLeifeng.create<LeifengVolunteer>()
    leifeng1.sweep()
    leifeng2.wash()
    leifeng3.buyRice()
    leifeng4.buyRice()
    println()
}