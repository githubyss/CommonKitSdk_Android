package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root.RootInlineBindingFragment
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * BaseInlineBindingToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 16:52:19
 */
abstract class BaseInlineBindingToolbarFragment<B : ViewDataBinding>(@LayoutRes layoutId: Int) : RootInlineBindingFragment<B>(layoutId) {

    /** ****************************** Override ****************************** */

    /***/
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

    /***/
    abstract fun setToolbarTitle()


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseInlineBindingToolbarActivity<*>
            && (activity as BaseInlineBindingToolbarActivity<*>).binding is ComkitActivityBaseToolbarBinding) {
            ((activity as BaseInlineBindingToolbarActivity<*>).binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.setTitle(titleResId)
        }
    }

    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseInlineBindingToolbarActivity<*>
            && (activity as BaseInlineBindingToolbarActivity<*>).binding is ComkitActivityBaseToolbarBinding) {
            ((activity as BaseInlineBindingToolbarActivity<*>).binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.title = titleString
        }
    }
}
