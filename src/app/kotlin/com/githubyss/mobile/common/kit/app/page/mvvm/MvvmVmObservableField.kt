package com.githubyss.mobile.common.kit.app.page.mvvm

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.app.page.mvvm.child.MvvmChildVm


/**
 * MvvmVmObservableField
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 11:40:41
 */
class MvvmVmObservableField : ViewModel() {
    
    /** ****************************** Properties ****************************** */
    
    /** context */
    // var context: Activity? = null
    
    /** model（数据源 Java Bean） */
    private var textBean: MvvmModel.TextBean? = null
    private var imageBean: MvvmModel.ImageBean? = null
    
    /** 数据绑定，绑定到 UI 的字段（data field） */
    var text: ObservableField<String>? = null
    var imageUrl: ObservableField<String>? = null
    var isTextShow: ObservableBoolean? = null
    // var viewStyle = ViewStyle()
    
    /** 命令绑定（command） */
    // val onButtonShowCommand=ReplyCom
    
    /** 子 ViewModel */
    var itemViewModel: ObservableArrayList<MvvmChildVm>? = null
    
    
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
        text = ObservableField()
        imageUrl = ObservableField()
        isTextShow = ObservableBoolean()
    }
    
    private fun loadData() {
        textBean = MvvmModel.TextBean("请点击开始！")
        imageBean = MvvmModel.ImageBean("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")
        
        text?.set(textBean?.text)
        imageUrl?.set(imageBean?.imageUrl)
        isTextShow?.set(true)
    }
    
    private fun clearData() {
        textBean = null
    }
    
    /** ******************** Event Handling ******************** */
    
    fun onButtonChangeTextClick() {
        val text = "Current Time: ${System.currentTimeMillis()}"
        this.text?.set(text)
    }
    
    fun onButtonShowTextClick() {
        val isTextShow = true
        this.isTextShow?.set(isTextShow)
    }
    
    fun onButtonHideTextClick() {
        val isTextShow = false
        this.isTextShow?.set(isTextShow)
    }
    
    
    /** ****************************** Class ****************************** */
    
    // class ViewStyle {
    //     var isTextShow = ObservableBoolean()
    // }
}
