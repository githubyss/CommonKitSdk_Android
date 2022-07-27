package com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect

import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_root.RootReflectBindingFragment
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * BaseReflectBindingToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:12
 */
abstract class BaseReflectBindingToolbarFragment<B : ViewDataBinding> : RootReflectBindingFragment<B>() {

    /** ****************************** Override ****************************** */

    /**  */
    override fun onResume() {
        super.onResume()
        setToolbarTitle()
    }

    /**  */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setToolbarTitle()
        }
    }


    /** ****************************** Abstract ****************************** */

    /**  */
    abstract fun setToolbarTitle()


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        when {
            activity is BaseReflectBindingToolbarActivity<*> && (activity as BaseReflectBindingToolbarActivity<*>).binding is ComkitActivityBaseToolbarBinding -> {
                ((activity as BaseReflectBindingToolbarActivity<*>).binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.setTitle(titleResId)
            }
        }
    }

    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        when {
            activity is BaseReflectBindingToolbarActivity<*> && (activity as BaseReflectBindingToolbarActivity<*>).binding is ComkitActivityBaseToolbarBinding -> {
                ((activity as BaseReflectBindingToolbarActivity<*>).binding as ComkitActivityBaseToolbarBinding).layoutToolbar.toolbarBase.title = titleString
            }
        }
    }
}
