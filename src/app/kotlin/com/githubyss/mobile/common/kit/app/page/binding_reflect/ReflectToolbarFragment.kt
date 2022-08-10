package com.githubyss.mobile.common.kit.app.page.binding_reflect

import com.githubyss.mobile.common.kit.R
import com.githubyss.common.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentViewBindingReflectBinding
import com.githubyss.mobile.common.kit.util.getStringFromRes


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
        binding?.textBindingReflect?.text = getStringFromRes(R.string.comkit_view_binding_reflect_toolbar)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_view_binding_reflect_toolbar_title)
    }
}
