package com.githubyss.mobile.common.kit.app.page.binding_inline

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.BaseInlineBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.bindView
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentViewBindingInlineBinding
import com.githubyss.mobile.common.kit.util.getStringFromRes


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

    override fun setupUi() {
        binding?.textBindingInline?.text = getStringFromRes(R.string.comkit_view_binding_inline_toolbar)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_view_binding_inline_toolbar_title)
    }
}
