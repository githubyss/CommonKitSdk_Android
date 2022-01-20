package com.githubyss.mobile.common.kit.app.page.mvvm

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.mvvm.child.MvvmChildVm
import com.githubyss.mobile.common.kit.app.page.mvvm.enumeration.DisplayType
import com.githubyss.mobile.common.kit.app.page.mvvm.enumeration.TimeOperateState
import com.githubyss.mobile.common.kit.util.TimerUtils
import java.util.*


/**
 * MvvmVmLiveData
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/11 11:12:44
 */
class MvvmVmLiveData : ViewModel() {
    
    /** ****************************** Properties ****************************** */
    
    /** context */
    // var context: Activity? = null
    
    /** model（数据源 Java Bean） */
    private var textBean: MvvmModel.TextBean? = null
    private var imageBean: MvvmModel.ImageBean? = null
    
    /** 数据绑定，绑定到 UI 的字段（data field） */
    var text: MutableLiveData<String>? = null
    var imageUrl: MutableLiveData<String>? = null
    var isTextShow: MutableLiveData<Boolean>? = null
    
    @DisplayType
    var displayType: MutableLiveData<String>? = null
    
    @TimeOperateState
    var timeOperateState: MutableLiveData<String>? = null
    
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
        this.text = MutableLiveData()
        this.imageUrl = MutableLiveData()
        this.isTextShow = MutableLiveData()
        this.displayType = MutableLiveData()
        this.timeOperateState = MutableLiveData()
    }
    
    private fun loadData() {
        textBean = MvvmModel.TextBean("请点击开始！")
        imageBean = MvvmModel.ImageBean("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")
        
        this.text?.value = textBean?.text
        this.imageUrl?.value = imageBean?.imageUrl
        this.isTextShow?.value = true
        this.displayType?.value = DisplayType.TEXT
        this.timeOperateState?.value = TimeOperateState.START
    }
    
    private fun clearData() {
        textBean = null
    }
    
    /** ******************** Event Handling ******************** */
    
    fun onButtonClick(view: View) {
        when (view.id) {
            R.id.button_image_dog -> {
                this.imageUrl?.value = "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif"
            }
            
            R.id.button_image_cat -> {
                this.imageUrl?.value = "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif"
            }
            
            R.id.button_image_chameleon -> {
                this.imageUrl?.value = "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif"
            }
        }
    }
    
    fun onButtonOperateTimeClick() {
        when (this.timeOperateState?.value) {
            TimeOperateState.START -> {
                this.timeOperateState?.value = TimeOperateState.STOP
                val timerTask = object : TimerTask() {
                    override fun run() {
                        this@MvvmVmLiveData.text?.postValue("当前时间: ${System.currentTimeMillis()}")
                    }
                }
                TimerUtils.runTaskPeriodicallyWithTimeOffset(timerTask, 0, 500)
            }
            TimeOperateState.STOP -> {
                this.timeOperateState?.value = TimeOperateState.START
                TimerUtils.cancel()
            }
        }
    }
    
    fun onButtonShowTimeTextClick() {
        this.isTextShow?.value = true
    }
    
    fun onButtonHideTimeTextClick() {
        this.isTextShow?.value = false
    }
    
    fun onButtonSwitchTextClick() {
        this.displayType?.value = DisplayType.TEXT
    }
    
    fun onButtonSwitchImageClick() {
        this.displayType?.value = DisplayType.IMAGE
    }
    
    
    /** ****************************** Class ****************************** */
    
    // class ViewStyle {
    //     var isTextShow = ObservableBoolean()
    // }
}
