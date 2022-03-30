package com.githubyss.mobile.common.kit.app.page.homepage

import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:34
 */
class HomepageActivity : BaseReflectBindingActivity<ComkitActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = HomepageActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        switchFragment(HomepageFragment(), HomepageFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }
}
