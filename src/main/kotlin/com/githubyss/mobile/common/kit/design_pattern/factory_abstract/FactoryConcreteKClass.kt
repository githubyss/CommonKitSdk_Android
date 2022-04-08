package com.githubyss.mobile.common.kit.design_pattern.factory_abstract

import com.githubyss.mobile.common.kit.util.logE
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.createInstance


/**
 * FactoryConcreteKClass
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/07 16:27:25
 */
class FactoryConcreteKClass<I> : FactoryAbstractKClass<I>() {
    companion object {
        private val TAG: String = FactoryConcreteKClass::class.java.simpleName
    }

    override fun <E : I> create(kClass: KClass<*>, vararg initArgs: Any): E? {
        var entity: I? = null
        val argsSize = initArgs.size

        try {
            when (argsSize) {
                0 -> {
                    entity = kClass.createInstance() as I?
                }
                else -> {
                    val constructors: Collection<KFunction<*>> = kClass.constructors
                    constructors.forEach {
                        if (it.parameters.size == argsSize) {
                            entity = when (argsSize) {
                                1 -> it.call(initArgs[0])
                                2 -> it.call(initArgs[0], initArgs[1])
                                3 -> it.call(initArgs[0], initArgs[1], initArgs[2])
                                4 -> it.call(initArgs[0], initArgs[1], initArgs[2], initArgs[3])
                                5 -> it.call(initArgs[0], initArgs[1], initArgs[2], initArgs[3], initArgs[4])
                                else -> null
                            } as I?
                        }
                    }
                }
            }
        }
        catch (e: Exception) {
            logE(TAG, t = e)
        }

        return try {
            entity as E?
        }
        catch (e: TypeCastException) {
            null
        }
    }
}