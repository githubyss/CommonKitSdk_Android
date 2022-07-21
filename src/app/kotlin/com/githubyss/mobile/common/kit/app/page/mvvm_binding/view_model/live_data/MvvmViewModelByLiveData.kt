package com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.live_data

import android.text.Editable
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.child.MvvmChildVm
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.enumeration.DisplayType
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.enumeration.TimeOperateState
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.model.MvvmModel
import com.githubyss.mobile.common.kit.util.cancelTimer
import com.githubyss.mobile.common.kit.util.runTaskPeriodicallyWithTimeOffset
import java.util.*


/**
 * MvvmViewModelByLiveData
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/11 11:12:44
 */
class MvvmViewModelByLiveData : ViewModel() {

    /** ****************************** Properties ****************************** */

    /** context */
    // var context: Activity? = null

    /** model（数据源 Java Bean） */
    private var textBean: MvvmModel.TextBean? = null
    private var imageBean: MvvmModel.ImageBean? = null

    /** 数据绑定，绑定到 UI 的字段（data field） */
    var text: MutableLiveData<String> = MutableLiveData()
    var edittext: MutableLiveData<String> = MutableLiveData()
    var imageUrl: MutableLiveData<String> = MutableLiveData()
    var isTimeShow: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var displayType: MutableLiveData<DisplayType>
    lateinit var timeOperateState: MutableLiveData<TimeOperateState>

    // var viewStyle = ViewStyle()

    /** 命令绑定（command） */
    // val onButtonShowCommand=ReplyCom

    /** 子 ViewModel */
    lateinit var itemViewModel: MutableLiveData<MvvmChildVm>


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
        this.displayType = MutableLiveData()
        this.timeOperateState = MutableLiveData()
    }

    private fun loadData() {
        this.textBean = MvvmModel.TextBean("请点击开始！")
        this.imageBean = MvvmModel.ImageBean("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")

        this.text.value = textBean?.text
        this.imageUrl.value = imageBean?.imageUrl
        this.edittext.value = ""
        this.isTimeShow.value = true
        this.displayType.value = DisplayType.TEXT
        this.timeOperateState.value = TimeOperateState.START
    }

    private fun clearData() {
        this.textBean = null
        this.imageBean = null
    }

    /** ******************** Event Handling ******************** */

    fun onButtonOperateTimeClick() {
        when (this.timeOperateState.value) {
            TimeOperateState.START -> {
                this.timeOperateState.value = TimeOperateState.STOP
                val timerTask = object : TimerTask() {
                    override fun run() {
                        this@MvvmViewModelByLiveData.text.postValue("当前时间: ${System.currentTimeMillis()}")
                    }
                }
                runTaskPeriodicallyWithTimeOffset(timerTask, 0, 500)
            }
            TimeOperateState.STOP -> {
                this.timeOperateState.value = TimeOperateState.START
                cancelTimer()
            }
        }
    }

    fun onButtonShowTimeTextClick() {
        this.isTimeShow.value = true
    }

    fun onButtonHideTimeTextClick() {
        this.isTimeShow.value = false
    }

    fun onButtonImageClick(view: View) {
        when (view.id) {
            R.id.button_image_dog -> {
                this.imageUrl.value = "https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif"
            }

            R.id.button_image_cat -> {
                this.imageUrl.value = "https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif"
            }

            R.id.button_image_chameleon -> {
                this.imageUrl.value = "https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif"
            }
        }
    }

    fun onButtonSwitchTextClick() {
        this.displayType.value = DisplayType.TEXT
    }

    fun onButtonSwitchImageClick() {
        this.displayType.value = DisplayType.IMAGE
    }

    fun onButtonSwitchEdittextClick() {
        this.displayType.value = DisplayType.EDITTEXT
    }

    fun afterTextChanged(value: Editable) {
        this.edittext.value = value.toString()
    }


    /** ****************************** Class ****************************** */

    // class ViewStyle {
    //     var isTimeShow = ObservableBoolean()
    // }
}
