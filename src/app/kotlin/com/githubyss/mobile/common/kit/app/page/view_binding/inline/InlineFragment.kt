package com.githubyss.mobile.common.kit.app.page.view_binding.inline

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingFragment
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentViewBindingInlineBinding


/**
 * InlineFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 17:44:22
 */
class InlineFragment : BaseInlineBindingFragment(R.layout.comkit_fragment_view_binding_inline) {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = InlineFragment::class.java.simpleName
    }
    
    private val binding by bindView<ComkitFragmentViewBindingInlineBinding>()
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        binding?.textBindingInline?.setOnClickListener { }
    }
}
