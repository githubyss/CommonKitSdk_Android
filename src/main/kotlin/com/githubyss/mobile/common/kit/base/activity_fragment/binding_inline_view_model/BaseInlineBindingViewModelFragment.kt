package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_view_model

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.BaseInlineBindingFragment


/**
 * BaseInlineBindingViewModelFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/22 13:16:59
 */
abstract class BaseInlineBindingViewModelFragment<B : ViewDataBinding>(@LayoutRes layoutId: Int) : BaseInlineBindingFragment<B>(layoutId) {

    /** ****************************** Override ****************************** */

    /**  */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindLifecycleOwner()
        bindXmlData()
        observeViewModelData()
    }

    /**  */
    override fun onDestroy() {
        removeViewModelObserver()
        super.onDestroy()
    }


    /** ****************************** Abstract ****************************** */

    /** 绑定 Activity LifecycleOwner 到 ViewDataBinding */
    abstract fun bindLifecycleOwner()

    /** 绑定 ViewModel 到 ViewDataBinding */
    abstract fun bindXmlData()

    /** 观察 ViewModel 的数据变化 */
    abstract fun observeViewModelData()

    /** 移除 ViewModel 的数据观察 */
    abstract fun removeViewModelObserver()
}
