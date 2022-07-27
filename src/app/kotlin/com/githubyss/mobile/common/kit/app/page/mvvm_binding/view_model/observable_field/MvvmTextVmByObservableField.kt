package com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.observable_field

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.enumeration.TimeOperateState
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.model.MvvmModel
import com.githubyss.mobile.common.kit.util.cancelTimer
import com.githubyss.mobile.common.kit.util.runTaskPeriodicallyWithTimeOffset
import java.util.*


/**
 * MvvmTextVmByObservableField
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/27 01:00:46
 */
class MvvmTextVmByObservableField : ViewModel() {

    /** ****************************** Properties ****************************** */

    /** model（数据源 Java Bean） */
    private var textBean: MvvmModel.TextBean? = null

    /** 数据绑定，绑定到 UI 的字段（data field） */
    val text by lazy { ObservableField<String>() }

    // val isTimeShow by lazy { ObservableBoolean() }
    val isTimeShow by lazy { ObservableField<Boolean>() }

    val timeOperateState by lazy { ObservableField<TimeOperateState>() }


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
        this.textBean = MvvmModel.TextBean("请点击开始！")

        this.text.set(textBean?.text)
        this.isTimeShow.set(true)
        this.timeOperateState.set(TimeOperateState.START)
    }

    /**  */
    private fun clearData() {
        this.textBean = null
    }

    /** ******************** Event Handling ******************** */

    /**  */
    fun onButtonOperateTimeClick() {
        when (this.timeOperateState.get()) {
            TimeOperateState.START -> {
                this.timeOperateState.set(TimeOperateState.STOP)
                val timerTask = object : TimerTask() {
                    override fun run() {
                        this@MvvmTextVmByObservableField.text.set("当前时间: ${System.currentTimeMillis()}")
                    }
                }
                runTaskPeriodicallyWithTimeOffset(timerTask, 0, 500)
            }
            TimeOperateState.STOP -> {
                this.timeOperateState.set(TimeOperateState.START)
                cancelTimer()
            }
        }
    }

    /**  */
    fun onButtonShowTimeTextClick() {
        this.isTimeShow.set(true)
    }

    /**  */
    fun onButtonHideTimeTextClick() {
        this.isTimeShow.set(false)
    }
}
