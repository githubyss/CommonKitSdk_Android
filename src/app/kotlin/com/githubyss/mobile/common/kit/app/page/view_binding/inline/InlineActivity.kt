package com.githubyss.mobile.common.kit.app.page.view_binding.inline

import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingActivity
import com.githubyss.mobile.common.kit.util.FragmentUtils


/**
 * InlineActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 17:42:44
 */
class InlineActivity : BaseInlineBindingActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = InlineActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun init() {
        FragmentUtils.switchFragmentWithAddHideShow(InlineFragment(), InlineFragment.TAG, null, supportFragmentManager, false, binding?.layoutFragmentContainer?.id ?: return)
    }
}
