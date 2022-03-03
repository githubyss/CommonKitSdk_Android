package com.githubyss.mobile.common.kit.app.page.mvi

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * MviActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/17 17:30:59
 */
class MviActivity : BaseReflectBindingToolbarActivity<ComkitActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = MviActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        switchFragment(MviFragment(), MviFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_mvi_title)
    }
}
