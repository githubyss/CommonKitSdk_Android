package com.githubyss.mobile.common.kit.design_pattern.factory_abstract

import com.githubyss.mobile.common.kit.util.logE


/**
 * FactoryConcreteInline
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 15:46:13
 */
class FactoryConcreteInline<I> {
    companion object {
        val TAG: String = FactoryConcreteInline::class.java.simpleName
    }

    inline fun <reified E : I> create(): E {
        var entity: I? = null
        try {
            entity = E::class.java.newInstance() as I
        }
        catch (e: Exception) {
            logE(TAG, t = e)
        }
        return entity as E
    }
}