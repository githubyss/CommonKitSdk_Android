package com.githubyss.mobile.common.kit.util

import android.view.View
import androidx.annotation.IntRange
import androidx.annotation.NonNull


/**
 * AntiShakeUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/08 17:41:23
 */
object AntiShakeUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = AntiShakeUtils::class.simpleName ?: "simpleName is null"
    
    private const val DEFAULT_DURATION: Long = 200
    private const val TAG_KEY = 0x7EFFFFFF
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Checker ********** ********** */
    
    fun isValid(@NonNull view: View): Boolean {
        return isValid(view, DEFAULT_DURATION)
    }
    
    fun isValid(@NonNull view: View, @IntRange(from = 0) duration: Long): Boolean {
        val curTime = System.currentTimeMillis()
        val tag = view.getTag(TAG_KEY)
        if (tag !is Long) {
            view.setTag(TAG_KEY, curTime)
            return true
        }
        if (curTime - tag <= duration) return false
        view.setTag(TAG_KEY, curTime)
        return true
    }
}