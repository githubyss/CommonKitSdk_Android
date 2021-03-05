package com.githubyss.mobile.common.kit.util


/**
 * SystemUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:16:09
 */
object SystemUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = SystemUtils::class.simpleName ?: "simpleName is null"
    
    
    
    fun currentTimeMillis(): Long
            = System.currentTimeMillis()
}
