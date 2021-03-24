package com.githubyss.mobile.common.kit.designpattern.factory.simple.car


/**
 * ConcreteAudiQ5
 * 奥迪 Q5
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 17:23:28
 */
class ConcreteAudiQ5 : AbstractAudiCar() {
    override fun drive() {
        print("Q5 启动")
    }
    
    override fun selfNavigation() {
        print("Q5 开始自动导航")
    }
}
