package com.githubyss.mobile.common.kit.design_pattern.factory_abstract

import kotlin.reflect.KClass


/**
 * FactoryAbstractKClass
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/07 16:27:17
 */
abstract class FactoryAbstractKClass<I> {
    abstract fun <E : I> create(kClass: KClass<*>, vararg initArgs: Any = emptyArray()): E?
}