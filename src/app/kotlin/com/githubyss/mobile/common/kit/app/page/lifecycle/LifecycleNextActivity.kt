package com.githubyss.mobile.common.kit.app.page.lifecycle

import com.githubyss.common.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarActivity
import com.githubyss.common.base.databinding.CombaseActivityBaseToolbarBinding
import com.githubyss.mobile.common.kit.R


/**
 * LifecycleNextActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/15 10:44:18
 */
class LifecycleNextActivity : BaseReflectBindingToolbarActivity<CombaseActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    /**  */
    companion object {
        private val TAG: String = LifecycleNextActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        switchFragment(LifecycleNextFragment(), LifecycleNextFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_lifecycle_title)
    }
}
