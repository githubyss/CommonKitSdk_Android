package com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.observable_field

import android.text.Editable
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel


/**
 * MvvmEdittextVmByObservableField
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/27 01:00:16
 */
class MvvmEdittextVmByObservableField : ViewModel() {

    /** ****************************** Properties ****************************** */

    /** 数据绑定，绑定到 UI 的字段（data field） */
    val edittext by lazy { ObservableField<String>() }


    /** ****************************** Constructors ****************************** */

    /**  */
    init {
        loadData()
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCleared() {
        super.onCleared()
        clearData()
    }


    /** ****************************** Functions ****************************** */

    /** ******************** Data Handling ******************** */

    /**  */
    private fun loadData() {
        this.edittext.set("")
    }

    /**  */
    private fun clearData() {
    }

    /** ******************** Event Handling ******************** */

    /**  */
    fun afterTextChanged(value: Editable) {
        this.edittext.set(value.toString())
    }
}
