package com.githubyss.mobile.common.kit.design_pattern.factory.simple.product


/**
 * AbstractFactory
 * 抽象工厂
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 17:20:43
 */
abstract class AbstractFactory {
    abstract fun <T : AbstractProduct> createProduct(clz: Class<T>): T
}
