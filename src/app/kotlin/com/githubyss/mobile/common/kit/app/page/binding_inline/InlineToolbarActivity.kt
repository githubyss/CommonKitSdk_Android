package com.githubyss.mobile.common.kit.app.page.binding_inline

import com.githubyss.mobile.common.kit.R
import com.githubyss.common.base.activity_fragment.binding_inline.BaseInlineBindingToolbarActivity
import com.githubyss.common.base.activity_fragment.binding_inline_root.inflate
import com.githubyss.common.base.databinding.CombaseActivityBaseToolbarBinding


/**
 * InlineToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/04 14:55:13
 */
class InlineToolbarActivity : BaseInlineBindingToolbarActivity<CombaseActivityBaseToolbarBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        private val TAG: String = InlineToolbarActivity::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val _binding by inflate<CombaseActivityBaseToolbarBinding>()


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        binding = _binding
        switchFragment(InlineToolbarFragment(), InlineToolbarFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_view_binding_inline_toolbar_title)
    }
}
