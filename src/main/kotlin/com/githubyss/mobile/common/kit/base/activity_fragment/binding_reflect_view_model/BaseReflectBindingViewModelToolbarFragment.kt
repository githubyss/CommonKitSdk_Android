package com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_view_model

import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarActivity
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_root.RootReflectBindingFragment
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * BaseReflectBindingViewModelToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/22 13:10:27
 */
abstract class BaseReflectBindingViewModelToolbarFragment<B : ViewDataBinding> : RootReflectBindingFragment<B>() {

    /** ****************************** Override ****************************** */

    override fun onResume() {
        super.onResume()

        bindLifecycleOwner()
        bindViewModelXml()
        observeViewModelData()

        setToolbarTitle()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setToolbarTitle()
        }
    }

    override fun onDestroy() {
        removeViewModelObserver()
        super.onDestroy()
    }


    /** ****************************** Abstract ****************************** */

    /***/
    abstract fun setToolbarTitle()

    /** 绑定 Activity LifecycleOwner 到 ViewDataBinding */
    abstract fun bindLifecycleOwner()

    /** 绑定 ViewModel 到 ViewDataBinding */
    abstract fun bindViewModelXml()

    /** 观察 ViewModel 的数据变化 */
    abstract fun observeViewModelData()

    /** 移除 ViewModel 的数据观察 */
    abstract fun removeViewModelObserver()


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseReflectBindingToolbarActivity<*> && (activity as BaseReflectBindingToolbarActivity<*>).binding is ComkitActivityBaseToolbarBinding) {
            ((activity as BaseReflectBindingToolbarActivity<*>).binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.setTitle(titleResId)
        }
    }

    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseReflectBindingToolbarActivity<*> && (activity as BaseReflectBindingToolbarActivity<*>).binding is ComkitActivityBaseToolbarBinding) {
            ((activity as BaseReflectBindingToolbarActivity<*>).binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.title = titleString
        }
    }
}
