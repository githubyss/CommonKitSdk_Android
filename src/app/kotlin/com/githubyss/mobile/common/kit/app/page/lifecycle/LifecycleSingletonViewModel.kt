package com.githubyss.mobile.common.kit.app.page.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * LifecycleSingletonViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/11 14:55:07
 */
object LifecycleSingletonViewModel : ViewModel() {

    /**  */
    val lifecycleLog by lazy { MutableLiveData(StringBuilder()) }


    /** ****************************** Functions ****************************** */

    /**  */
    fun refreshLifecycleLog(message: String) {
        lifecycleLog.value = lifecycleLog.value?.append(message)?.appendLine()
    }

    /**  */
    fun clearLifecycleLog() {
        lifecycleLog.value = lifecycleLog.value?.clear()
    }
}
