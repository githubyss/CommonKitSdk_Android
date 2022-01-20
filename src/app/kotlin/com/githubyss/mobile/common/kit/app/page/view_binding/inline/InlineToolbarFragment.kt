package com.githubyss.mobile.common.kit.app.page.view_binding.inline

import android.view.View
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentViewBindingInlineBinding


/**
 * InlineToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/04 14:52:55
 */
class InlineToolbarFragment : BaseInlineBindingToolbarFragment(R.layout.comkit_fragment_view_binding_inline) {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val TAG: String = InlineToolbarFragment::class.java.simpleName
    }
    
    private val binding by bindView<ComkitFragmentViewBindingInlineBinding>()
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        initView()
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_view_binding_toolbar_inline_title)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    private fun initView() {
        binding.textBindingInline.setOnClickListener(onClickListener)
    }
    
    
    /** ****************************** Implementations ****************************** */
    
    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
        }
    }
}
