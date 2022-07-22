package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_view_model

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseFragment


/**
 * BaseInlineBindingViewModelFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/22 13:16:59
 */
abstract class BaseInlineBindingViewModelFragment(@LayoutRes layoutId: Int) : BaseFragment(layoutId) {

    /** ****************************** Override ****************************** */

    /***/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindLifecycleOwner()
        bindViewModelXml()
        observeViewModelData()
    }

    override fun onDestroy() {
        removeViewModelObserver()
        super.onDestroy()
    }


    /** ****************************** Abstract ****************************** */

    /** 绑定 Activity LifecycleOwner 到 ViewDataBinding */
    abstract fun bindLifecycleOwner()

    /** 绑定 ViewModel 到 ViewDataBinding */
    abstract fun bindViewModelXml()

    /** 观察 ViewModel 的数据变化 */
    abstract fun observeViewModelData()

    /** 移除 ViewModel 的数据观察 */
    abstract fun removeViewModelObserver()
}
