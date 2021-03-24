package com.githubyss.mobile.common.kit.designpattern.factory.simple.product


/**
 * ConcreteProductA
 * 具体产品 A
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 17:20:13
 */
class ConcreteProductA : AbstractProduct() {
    override fun doSomething() {
        print("ConcreteProductA")
    }
}
