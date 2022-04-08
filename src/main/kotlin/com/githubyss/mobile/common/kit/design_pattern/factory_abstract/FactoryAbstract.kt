package com.githubyss.mobile.common.kit.design_pattern.factory_abstract


/**
 * FactoryAbstract
 * 抽象工厂
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 14:21:54
 */
abstract class FactoryAbstract<I> {
    abstract fun <E : I> create(jClass: Class<E>, vararg initArgs: Any = emptyArray()): E?
}