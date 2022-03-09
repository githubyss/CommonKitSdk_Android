package com.githubyss.mobile.common.kit.design_pattern.factory_abstract

import com.githubyss.mobile.common.kit.util.logE


/**
 * ConcreteInlineFactory
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 15:46:13
 */
class ConcreteInlineFactory<O> {
    companion object {
        val TAG: String = ConcreteInlineFactory::class.java.simpleName
    }

    inline fun <reified T : O> create(): T {
        var entity: O? = null
        try {
            entity = T::class.java.newInstance() as O
        }
        catch (e: Exception) {
            logE(TAG, t = e)
        }
        return entity as T
    }
}
