package com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.live_data

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.model.MvvmModel


/**
 * MvvmImageVmByLiveData
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/21 13:53:17
 */
class MvvmImageVmByLiveData : ViewModel() {

    /** ****************************** Properties ****************************** */

    /** model（数据源 Java Bean） */
    private var imageBean: MvvmModel.ImageBean? = null

    /** 数据绑定，绑定到 UI 的字段（data field） */
    var imageUrl: MutableLiveData<String> = MutableLiveData()


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
    }

    private fun loadData() {
        this.imageBean = MvvmModel.ImageBean("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")

        this.imageUrl.value = imageBean?.imageUrl
    }

    private fun clearData() {
        this.imageBean = null
    }

    /** ******************** Event Handling ******************** */

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
}
