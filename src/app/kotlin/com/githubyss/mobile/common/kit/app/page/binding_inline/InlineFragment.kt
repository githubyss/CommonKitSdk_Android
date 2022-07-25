package com.githubyss.mobile.common.kit.app.page.binding_inline

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.BaseInlineBindingFragment
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root.bindView
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentViewBindingInlineBinding
import com.githubyss.mobile.common.kit.util.getStringFromRes


/**
 * InlineFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 17:44:22
 */
class InlineFragment : BaseInlineBindingFragment<ComkitFragmentViewBindingInlineBinding>(R.layout.comkit_fragment_view_binding_inline) {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = InlineFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val _binding by bindView<ComkitFragmentViewBindingInlineBinding>()


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        binding = _binding
        binding.textBindingInline.text = getStringFromRes(R.string.comkit_view_binding_inline)
    }
}
