package com.githubyss.mobile.common.kit.app.page.binding_inline

import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.BaseInlineBindingActivity


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

    override fun setupUi() {
        switchFragment(InlineFragment(), InlineFragment.TAG, FRAGMENT_BASE_CONTAINER_ID, false)
    }
}