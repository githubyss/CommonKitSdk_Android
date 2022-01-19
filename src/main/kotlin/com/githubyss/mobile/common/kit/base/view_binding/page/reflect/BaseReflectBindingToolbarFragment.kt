package com.githubyss.mobile.common.kit.base.view_binding.page.reflect

import androidx.viewbinding.ViewBinding
import com.githubyss.mobile.common.kit.databinding.ComuiActivityBaseToolbarBinding


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
    
    
    /** ****************************** Abstract ****************************** */
    
    abstract fun setToolbarTitle()
    
    
    /** ****************************** Functions ****************************** */
    
    /** Setup Toolbar text by ResId. */
    protected fun setToolbarTitle(titleResId: Int) {
        if (activity is BaseReflectBindingToolbarActivity<*> && (activity as BaseReflectBindingToolbarActivity<*>).binding is ComuiActivityBaseToolbarBinding) {
            ((activity as BaseReflectBindingToolbarActivity<*>).binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.setTitle(titleResId)
        }
    }
    
    /** Setup Toolbar text by String. */
    protected fun setToolbarTitle(titleString: String) {
        if (activity is BaseReflectBindingToolbarActivity<*> && (activity as BaseReflectBindingToolbarActivity<*>).binding is ComuiActivityBaseToolbarBinding) {
            ((activity as BaseReflectBindingToolbarActivity<*>).binding as ComuiActivityBaseToolbarBinding).toolbarBase.toolbarBase.title = titleString
        }
    }
}
