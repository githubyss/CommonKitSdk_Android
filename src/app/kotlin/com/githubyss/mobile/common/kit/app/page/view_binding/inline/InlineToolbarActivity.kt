package com.githubyss.mobile.common.kit.app.page.view_binding.inline

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingToolbarActivity
import com.githubyss.mobile.common.kit.util.FragmentUtils


/**
 * InlineToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/04 14:55:13
 */
class InlineToolbarActivity : BaseInlineBindingToolbarActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = InlineToolbarActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun init() {
        FragmentUtils.switchFragmentWithAddHideShow(InlineToolbarFragment(), InlineToolbarFragment.TAG, null, supportFragmentManager, false, binding?.layoutFragmentContainer?.id ?: return)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_view_binding_toolbar_inline_title)
    }
}
