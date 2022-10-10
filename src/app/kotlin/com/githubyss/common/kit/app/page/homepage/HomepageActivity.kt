package com.githubyss.common.kit.app.page.homepage

import com.githubyss.common.base.activity_fragment.binding_reflect.BaseReflectBindingActivity
import com.githubyss.common.base.databinding.CombaseActivityBaseBinding


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:34
 */
class HomepageActivity : BaseReflectBindingActivity<CombaseActivityBaseBinding>() {

    /** ****************************** Properties ****************************** */

    /**  */
    companion object {
        private val TAG: String = HomepageActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        switchFragment(HomepageComposeFragment(), HomepageComposeFragment.TAG, FRAGMENT_BASE_CONTAINER_ID, false)
    }
}
