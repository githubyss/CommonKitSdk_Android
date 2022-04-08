package com.githubyss.mobile.common.kit.design_pattern.factory_abstract

import android.os.Build
import androidx.annotation.RequiresApi
import com.githubyss.mobile.common.kit.util.logE
import java.lang.reflect.Constructor


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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <E : I> create(jClass: Class<E>, vararg initArgs: Any): E? {
        var entity: I? = null
        val argsSize = initArgs.size

        try {
            when (argsSize) {
                0 -> {
                    entity = Class.forName(jClass.name).newInstance() as I?
                }
                else -> {
                    val constructors: Array<Constructor<*>> = jClass.constructors
                    constructors.forEach {
                        if (it.parameters.size == argsSize) {
                            entity = when (argsSize) {
                                1 -> it.newInstance(initArgs[0])
                                2 -> it.newInstance(initArgs[0], initArgs[1])
                                3 -> it.newInstance(initArgs[0], initArgs[1], initArgs[2])
                                4 -> it.newInstance(initArgs[0], initArgs[1], initArgs[2], initArgs[3])
                                5 -> it.newInstance(initArgs[0], initArgs[1], initArgs[2], initArgs[3], initArgs[4])
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