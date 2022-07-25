package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_view_model

import android.os.Bundle
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root.inflate
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseBinding


/**
 * BaseInlineBindingViewModelActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/22 13:10:15
 */
abstract class BaseInlineBindingViewModelActivity : BaseActivity(R.layout.comkit_activity_base) {

    /** ****************************** Properties ****************************** */

    /**  */
    val binding by inflate<ComkitActivityBaseBinding>()


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindLifecycleOwner()
        bindViewModelXml()
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
    abstract fun bindViewModelXml()

    /** 观察 ViewModel 的数据变化 */
    abstract fun observeViewModelData()

    /** 移除 ViewModel 的数据观察 */
    abstract fun removeViewModelObserver()
}
