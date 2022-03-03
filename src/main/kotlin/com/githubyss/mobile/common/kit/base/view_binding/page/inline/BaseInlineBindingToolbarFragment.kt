package com.githubyss.mobile.common.kit.base.view_binding.page.inline

import androidx.annotation.LayoutRes
import com.githubyss.mobile.common.kit.base.activity_fragment.BaseFragment
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * BaseInlineBindingToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 16:52:19
 */
abstract class BaseInlineBindingToolbarFragment(@LayoutRes layoutId: Int) : BaseFragment(layoutId) {

    /** ****************************** Override ****************************** */

    override fun onResume() {
        super.onResume()
        setToolbarTitle()
    }


    /** ****************************** Open ****************************** */

    open fun setToolbarTitle() {}


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseInlineBindingToolbarActivity && (activity as BaseInlineBindingToolbarActivity).binding is ComkitActivityBaseToolbarBinding) {
            ((activity as BaseInlineBindingToolbarActivity).binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.setTitle(titleResId)
        }
    }

    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseInlineBindingToolbarActivity && (activity as BaseInlineBindingToolbarActivity).binding is ComkitActivityBaseToolbarBinding) {
            ((activity as BaseInlineBindingToolbarActivity).binding as ComkitActivityBaseToolbarBinding).toolbarBase.toolbarBase.title = titleString
        }
    }
}
