package com.githubyss.mobile.common.kit.app.page.lifecycle

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarActivity
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.res.databinding.ComresActivityBaseToolbarBinding


/**
 * LifecycleNextActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/15 10:44:18
 */
class LifecycleNextActivity : BaseReflectBindingToolbarActivity<ComresActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = LifecycleNextActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun init() {
        super.init()
        FragmentUtils.switchFragmentWithAddHideShow(LifecycleNextFragment(), LifecycleNextFragment.TAG, null, supportFragmentManager, false, binding?.layoutFragmentContainer?.id ?: return)
    }

    override fun destroy() {
        super.destroy()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_lifecycle_title)
    }
}
