package com.githubyss.mobile.common.kit.app.design_pattern.decorator

import com.githubyss.mobile.common.kit.app.design_pattern.entity.person.Person
import com.githubyss.mobile.common.kit.app.design_pattern.entity.string_handle.IStringDecrypt
import com.githubyss.mobile.common.kit.app.design_pattern.entity.string_handle.IStringEncrypt
import com.githubyss.mobile.common.kit.app.design_pattern.entity.string_handle.StringDecrypt
import com.githubyss.mobile.common.kit.app.design_pattern.entity.string_handle.StringEncrypt


fun decorator() {
    println("装扮")
    val person: Person = Person("我")
    val decoratorTShirts: DecoratorFinery = DecoratorFineryTShirts()
    val decoratorBigTrouser: DecoratorFinery = DecoratorFineryBigTrouser()
    val decoratorSneaker: DecoratorFinery = DecoratorFinerySneaker()
    decoratorTShirts.decorator(person)
    decoratorBigTrouser.decorator(decoratorTShirts)
    decoratorSneaker.decorator(decoratorBigTrouser)
    decoratorSneaker.show()
    println()

    val input = "Decorator"
    println("信息处理")
    println("源信息：${input}")
    val stringEncrypt: IStringEncrypt = StringEncrypt()
    val decoratorEncrypt1: DecoratorStringEncrypt = DecoratorStringEncrypt1()
    val decoratorEncrypt2: DecoratorStringEncrypt = DecoratorStringEncrypt2()
    decoratorEncrypt1.decorator(stringEncrypt)
    decoratorEncrypt2.decorator(decoratorEncrypt1)
    val outputEncrypt = decoratorEncrypt2.encrypt(input)
    println("加密后信息：${outputEncrypt}")

    val stringDecrypt: IStringDecrypt = StringDecrypt()
    val decoratorDecrypt1: DecoratorStringDecrypt = DecoratorStringDecrypt1()
    val decoratorDecrypt2: DecoratorStringDecrypt = DecoratorStringDecrypt2()
    decoratorDecrypt1.decorator(stringDecrypt)
    decoratorDecrypt2.decorator(decoratorDecrypt1)
    val outputDecrypt = decoratorDecrypt2.decrypt(outputEncrypt)
    println("解密后信息：${outputDecrypt}")
    println()
}