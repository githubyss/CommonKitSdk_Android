package com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.observable_field

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.child.MvvmChildVm
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.enumeration.DisplayType


/**
 * MvvmViewModelByObservableField
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 11:40:41
 */
class MvvmViewModelByObservableField : ViewModel() {

    /** ****************************** Properties ****************************** */

    /** context */
    // var context: Activity? = null
    // var viewStyle = ViewStyle()

    /** 命令绑定（command） */
    // val onButtonShowCommand=ReplyCom

    /** 子 ViewModel */
    var itemViewModel: ObservableArrayList<MvvmChildVm>? = null

    /** 数据绑定，绑定到 UI 的字段（data field） */
    val displayType by lazy { ObservableField<String>() }


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
        this.displayType.set(DisplayType.TEXT)
    }

    /**  */
    private fun clearData() {
    }

    /** ******************** Event Handling ******************** */

    /**  */
    fun onButtonSwitchTextClick() {
        this.displayType.set(DisplayType.TEXT)
    }

    /**  */
    fun onButtonSwitchImageClick() {
        this.displayType.set(DisplayType.IMAGE)
    }

    /**  */
    fun onButtonSwitchEdittextClick() {
        this.displayType.set(DisplayType.EDITTEXT)
    }


    /** ****************************** Class ****************************** */

    // /**  */
    // class ViewStyle {
    //     var isTimeShow = ObservableBoolean()
    // }
}
