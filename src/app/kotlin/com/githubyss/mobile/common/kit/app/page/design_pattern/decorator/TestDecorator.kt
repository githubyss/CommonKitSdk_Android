package com.githubyss.mobile.common.kit.app.page.design_pattern.decorator

import com.githubyss.mobile.common.kit.app.page.design_pattern.entity.person_finery.Person


fun decorator() {
    val person: Person = Person("我")
    println("第一种装扮")
    val decoratorFineryTShirts: DecoratorFinery = DecoratorFineryTShirts()
    val decoratorFineryBigTrouser: DecoratorFinery = DecoratorFineryBigTrouser()
    val decoratorFinerySneaker: DecoratorFinery = DecoratorFinerySneaker()
    decoratorFineryTShirts.decorator(person)
    decoratorFineryBigTrouser.decorator(decoratorFineryTShirts)
    decoratorFinerySneaker.decorator(decoratorFineryBigTrouser)
    decoratorFinerySneaker.show()

    println("第一种装扮")
    val decoratorFinerySuit: DecoratorFinery = DecoratorFinerySuit()
    val decoratorFineryTie: DecoratorFinery = DecoratorFineryTie()
    val decoratorFineryLeatherShoes: DecoratorFinery = DecoratorFineryLeatherShoes()
    decoratorFinerySuit.decorator(person)
    decoratorFineryTie.decorator(decoratorFinerySuit)
    decoratorFineryLeatherShoes.decorator(decoratorFineryTie)
    decoratorFineryLeatherShoes.show()
}