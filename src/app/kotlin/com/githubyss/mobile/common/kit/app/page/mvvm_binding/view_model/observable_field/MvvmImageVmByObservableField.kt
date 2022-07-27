package com.githubyss.mobile.common.kit.app.page.mvvm_binding.view_model.observable_field

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.model.MvvmModel


/**
 * MvvmImageVmByObservableField
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/27 01:00:51
 */
class MvvmImageVmByObservableField : ViewModel() {

    /** ****************************** Properties ****************************** */

    /** model（数据源 Java Bean） */
    private var imageBean: MvvmModel.ImageBean? = null

    /** 数据绑定，绑定到 UI 的字段（data field） */
    val imageUrl by lazy { ObservableField<String>() }


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
        this.imageBean = MvvmModel.ImageBean("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")

        this.imageUrl.set(imageBean?.imageUrl)
    }

    /**  */
    private fun clearData() {
        this.imageBean = null
    }

    /** ******************** Event Handling ******************** */

    /**  */
    fun onButtonImageClick(view: View) {
        when (view.id) {
            R.id.button_image_dog -> {
                this.imageUrl.set("https://n.sinaimg.cn/tech/transform/403/w179h224/20210207/befe-kirmaiu6765911.gif")
            }

            R.id.button_image_cat -> {
                this.imageUrl.set("https://n.sinaimg.cn/tech/transform/356/w222h134/20210224/4f29-kkmphps7924390.gif")
            }

            R.id.button_image_chameleon -> {
                this.imageUrl.set("https://n.sinaimg.cn/tech/transform/398/w212h186/20210309/512c-kmeeius1127364.gif")
            }
        }
    }
}
