package com.githubyss.mobile.common.kit.app.page.view_binding.reflect

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentViewBindingReflectBinding
import com.githubyss.mobile.common.kit.util.ResourceUtils


/**
 * ReflectToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 19:46:28
 */
class ReflectToolbarFragment : BaseReflectBindingToolbarFragment<ComkitFragmentViewBindingReflectBinding>() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = ReflectToolbarFragment::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun setupUi() {
        binding?.textBindingReflect?.text = ResourceUtils.getString(R.string.comkit_view_binding_reflect_toolbar)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_view_binding_reflect_toolbar_title)
    }
}
