package com.githubyss.mobile.common.kit.app.page.mvvm.view_model.observable_field

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.mvvm.model.MvvmModel
import com.githubyss.mobile.common.kit.app.page.mvvm.child.MvvmChildVm
import com.githubyss.mobile.common.kit.app.page.mvvm.enumeration.DisplayType
import com.githubyss.mobile.common.kit.app.page.mvvm.enumeration.TimeOperateState
import com.githubyss.mobile.common.kit.util.TimerUtils
import java.util.*


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
    var isTimeShow: ObservableField<Boolean>? = null
    // var isTimeShow: ObservableBoolean? = null

    @DisplayType
    var displayType: ObservableField<String>? = null

    @TimeOperateState
    var timeOperateState: ObservableField<String>? = null

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
        this.text = ObservableField()
        this.imageUrl = ObservableField()
        this.isTimeShow = ObservableField()
        // this.isTimeShow = ObservableBoolean()
        this.displayType = ObservableField()
        this.timeOperateState = ObservableField()
    }

    private fun loadData() {
        this.textBean = MvvmModel.TextBean("请点击开始！")
        this.imageBean = MvvmModel.ImageBean("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")

        this.text?.set(textBean?.text)
        this.imageUrl?.set(imageBean?.imageUrl)
        this.isTimeShow?.set(true)
        this.displayType?.set(DisplayType.TEXT)
        this.timeOperateState?.set(TimeOperateState.START)
    }

    private fun clearData() {
        this.textBean = null
        this.imageBean = null
    }

    /** ******************** Event Handling ******************** */

    fun onButtonOperateTimeClick() {
        when (this.timeOperateState?.get()) {
            TimeOperateState.START -> {
                this.timeOperateState?.set(TimeOperateState.STOP)
                val timerTask = object : TimerTask() {
                    override fun run() {
                        this@MvvmVmObservableField.text?.set("当前时间: ${System.currentTimeMillis()}")
                    }
                }
                TimerUtils.runTaskPeriodicallyWithTimeOffset(timerTask, 0, 500)
            }
            TimeOperateState.STOP -> {
                this.timeOperateState?.set(TimeOperateState.START)
                TimerUtils.cancel()
            }
        }
    }

    fun onButtonShowTimeTextClick() {
        this.isTimeShow?.set(true)
    }

    fun onButtonHideTimeTextClick() {
        this.isTimeShow?.set(false)
    }

    fun onButtonImageClick(view: View) {
        when (view.id) {
            R.id.button_image_dog -> {
                this.imageUrl?.set("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")
            }

            R.id.button_image_cat -> {
                this.imageUrl?.set("https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif")
            }

            R.id.button_image_chameleon -> {
                this.imageUrl?.set("https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif")
            }
        }
    }

    fun onButtonSwitchTextClick() {
        this.displayType?.set(DisplayType.TEXT)
    }

    fun onButtonSwitchImageClick() {
        this.displayType?.set(DisplayType.IMAGE)
    }


    /** ****************************** Class ****************************** */

    // class ViewStyle {
    //     var isTimeShow = ObservableBoolean()
    // }
}
