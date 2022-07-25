package com.githubyss.mobile.common.kit.app.page.binding_inline

import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline.BaseInlineBindingActivity
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root.inflate
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseBinding


/**
 * InlineActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 17:42:44
 */
class InlineActivity : BaseInlineBindingActivity<ComkitActivityBaseBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        private val TAG: String = InlineActivity::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val _binding by inflate<ComkitActivityBaseBinding>()


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        binding = _binding
        switchFragment(InlineFragment(), InlineFragment.TAG, FRAGMENT_BASE_CONTAINER_ID, false)
    }
}
