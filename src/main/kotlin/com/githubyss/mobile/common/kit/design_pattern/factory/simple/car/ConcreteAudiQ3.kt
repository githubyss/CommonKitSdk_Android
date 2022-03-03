package com.githubyss.mobile.common.kit.design_pattern.factory.simple.car


/**
 * ConcreteAudiQ3
 * 奥迪 Q3
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 17:22:18
 */
class ConcreteAudiQ3 : AbstractAudiCar() {
    override fun drive() {
        print("Q3 启动")
    }
    
    override fun selfNavigation() {
        print("Q3 开始自动导航")
    }
}
