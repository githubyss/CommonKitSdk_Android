package com.githubyss.mobile.common.kit.app.page.lifecycle

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * LifecycleViewModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/11 14:55:07
 */
class LifecycleViewModel : ViewModel() {

    /** model（数据源 Java Bean） */
    var lifecycleLogEntity: StringBuilder? = null

    /** 数据绑定，绑定到 UI 的字段（data field） */
    var lifecycleLog: MutableLiveData<StringBuilder>? = null
    var viewId: MutableLiveData<Int>? = null


    /** ****************************** Constructors ****************************** */

    init {
        initViewModelField()
        loadData()
    }


    /** ****************************** Override ****************************** */

    override fun onCleared() {
        super.onCleared()
        clearData()
    }


    /** ****************************** Functions ****************************** */

    /** ******************** Data Handling ******************** */

    private fun initViewModelField() {
        this.lifecycleLog = MutableLiveData()
        this.viewId = MutableLiveData()
    }

    private fun loadData() {
        this.lifecycleLogEntity = StringBuilder()
    }

    private fun clearData() {
        this.lifecycleLog = null
        this.viewId = null
    }

    /** ******************** Event Handling ******************** */

    fun onAnyButtonClick(view: View) {
        this.viewId?.value = view.id
    }
}
