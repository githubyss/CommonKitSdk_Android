package com.githubyss.mobile.common.kit.design_pattern.factory_abstract

import com.githubyss.mobile.common.kit.util.logE


/**
 * FactoryConcrete
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/09 14:24:20
 */
class FactoryConcrete<I> : FactoryAbstract<I>() {
    companion object {
        private val TAG: String = FactoryConcrete::class.java.simpleName
    }

    override fun <E : I> create(clz: Class<E>): E {
        var entity: I? = null
        try {
            entity = Class.forName(clz.name).newInstance() as I
        }
        catch (e: Exception) {
            logE(TAG, t = e)
        }
        return entity as E
    }
}