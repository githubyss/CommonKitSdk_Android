package com.githubyss.mobile.common.kit.design_pattern.factory_abstract


/**
 * AbstractFactory
 * 抽象工厂
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 14:21:54
 */
abstract class AbstractFactory<O> {
    abstract fun <T : O> create(clz: Class<T>): T
}
