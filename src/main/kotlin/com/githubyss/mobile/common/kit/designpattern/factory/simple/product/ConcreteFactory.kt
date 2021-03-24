package com.githubyss.mobile.common.kit.designpattern.factory.simple.product


/**
 * ConcreteFactory
 * 具体工厂
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 17:19:51
 */
class ConcreteFactory : AbstractFactory() {
    override fun <T : AbstractProduct> createProduct(clz: Class<T>): T {
        var product: AbstractProduct? = null
        try {
            product = Class.forName(clz.name).newInstance() as AbstractProduct
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return product as T
    }
}
