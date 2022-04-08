package com.githubyss.mobile.common.kit.app.design_pattern.factory_abstract

import com.githubyss.mobile.common.kit.app.design_pattern.POST_NUMBER
import com.githubyss.mobile.common.kit.app.design_pattern.PRE_NUMBER
import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.CarJeep
import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.CarSport
import com.githubyss.mobile.common.kit.app.design_pattern.entity.car.ICar
import com.githubyss.mobile.common.kit.app.design_pattern.entity.leifeng.Leifeng
import com.githubyss.mobile.common.kit.app.design_pattern.entity.leifeng.LeifengUndergraduate
import com.githubyss.mobile.common.kit.app.design_pattern.entity.leifeng.LeifengVolunteer
import com.githubyss.mobile.common.kit.app.design_pattern.entity.math_operator.*
import com.githubyss.mobile.common.kit.app.design_pattern.entity.ship.IShip
import com.githubyss.mobile.common.kit.app.design_pattern.entity.ship.ShipCruise
import com.githubyss.mobile.common.kit.app.design_pattern.entity.ship.ShipWar
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryAbstract
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcrete
import com.githubyss.mobile.common.kit.design_pattern.factory_abstract.FactoryConcreteInline


fun factoryAbstract() {
    val shipFactory: FactoryAbstract<IShip> = FactoryConcrete<IShip>()
    val shipCruise: IShip? = shipFactory.create<ShipCruise>(ShipCruise::class.java)
    shipCruise?.let {
        shipCruise.weighAnchor()
        shipCruise.dropAnchor()
    }
    val shipWar: IShip? = shipFactory.create<ShipWar>(ShipWar::class)
    shipWar?.let {
        shipWar.weighAnchor()
        shipWar.dropAnchor()
        (shipWar as ShipWar).launchMissile()
    }
    println()

    val carFactory: FactoryConcreteInline<ICar> = FactoryConcreteInline<ICar>()
    val carSport: ICar? = carFactory.create<CarSport>()
    val carJeep: ICar? = carFactory.create<CarJeep>()
    carSport?.let {
        carSport.drive()
        carSport.selfNavigation()
    }
    carJeep?.let {
        carJeep.drive()
        carJeep.selfNavigation()
    }
    println()

    val operatorUnaryFactory: FactoryConcreteInline<IOperatorUnary> = FactoryConcreteInline<IOperatorUnary>()
    val operatorEqu: IOperatorUnary? = operatorUnaryFactory.create<OperatorEqu>()
    val operatorDyadicFactory: FactoryConcreteInline<IOperatorDyadic> = FactoryConcreteInline<IOperatorDyadic>()
    val operatorAdd: IOperatorDyadic? = operatorDyadicFactory.create<OperatorAdd>()
    val operatorSub: IOperatorDyadic? = operatorDyadicFactory.create<OperatorSub>()
    val operatorMul: IOperatorDyadic? = operatorDyadicFactory.create<OperatorMul>()
    val operatorDiv: IOperatorDyadic? = operatorDyadicFactory.create<OperatorDiv>()
    println("$PRE_NUMBER ${operatorAdd?.operator()} $POST_NUMBER ${operatorEqu?.operator()} ${operatorAdd?.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorSub?.operator()} $POST_NUMBER ${operatorEqu?.operator()} ${operatorSub?.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorMul?.operator()} $POST_NUMBER ${operatorEqu?.operator()} ${operatorMul?.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println("$PRE_NUMBER ${operatorDiv?.operator()} $POST_NUMBER ${operatorEqu?.operator()} ${operatorDiv?.calculate(PRE_NUMBER, POST_NUMBER)}}")
    println()

    val leifengFactory: FactoryConcreteInline<Leifeng> = FactoryConcreteInline<Leifeng>()
    val leifeng1: Leifeng? = leifengFactory.create<LeifengUndergraduate>()
    val leifeng2: Leifeng? = leifengFactory.create<LeifengUndergraduate>()
    val leifeng3: Leifeng? = leifengFactory.create<LeifengUndergraduate>()
    val leifeng4: Leifeng? = leifengFactory.create<LeifengVolunteer>()
    leifeng1?.sweep()
    leifeng2?.wash()
    leifeng3?.buyRice()
    leifeng4?.buyRice()
    println()
}