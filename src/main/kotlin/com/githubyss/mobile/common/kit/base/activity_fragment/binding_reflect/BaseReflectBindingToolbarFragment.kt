package com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect

import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * BaseReflectBindingToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:12
 */
abstract class BaseReflectBindingToolbarFragment<B : ViewBinding> : RootReflectBindingFragment<B>() {

    /** ****************************** Override ****************************** */

    override fun onResume() {
        super.onResume()
        setToolbarTitle()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setToolbarTitle()
        }
    }


    /** ****************************** Abstract ****************************** */

    abstract fun setToolbarTitle()


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseReflectBindingToolbarActivity<*> && (activity as BaseReflectBindingToolbarActivity<*>).binding is ComkitActivityBaseToolbarBinding) {
            ((activity as BaseReflectBindingToolbarActivity<*>).binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.setTitle(titleResId)
        }
    }

    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseReflectBindingToolbarActivity<*> && (activity as BaseReflectBindingToolbarActivity<*>).binding is ComkitActivityBaseToolbarBinding) {
            ((activity as BaseReflectBindingToolbarActivity<*>).binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.title = titleString
        }
    }
}
