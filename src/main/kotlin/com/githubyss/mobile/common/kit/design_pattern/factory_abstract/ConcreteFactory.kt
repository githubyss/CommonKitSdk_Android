package com.githubyss.mobile.common.kit.design_pattern.factory_abstract

import com.githubyss.mobile.common.kit.util.logE


/**
 * ConcreteFactory
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 14:24:20
 */
class ConcreteFactory<O> : AbstractFactory<O>() {
    companion object {
        private val TAG: String = ConcreteFactory::class.java.simpleName
    }

    override fun <T : O> create(clz: Class<T>): T {
        var entity: O? = null
        try {
            entity = Class.forName(clz.name).newInstance() as O
        }
        catch (e: Exception) {
            logE(TAG, t = e)
        }
        return entity as T
    }
}
