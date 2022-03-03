package com.githubyss.mobile.common.kit.app.page.lifecycle

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding
import com.githubyss.mobile.common.kit.util.FragmentUtils


/**
 * LifecycleNextActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/15 10:44:18
 */
class LifecycleNextActivity : BaseReflectBindingToolbarActivity<ComkitActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = LifecycleNextActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        FragmentUtils.switchFragmentByAddHideShow(LifecycleNextFragment(), LifecycleNextFragment.TAG, null, supportFragmentManager, binding?.layoutFragmentBaseToolbarContainer?.id ?: return, false)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_lifecycle_title)
    }
}
