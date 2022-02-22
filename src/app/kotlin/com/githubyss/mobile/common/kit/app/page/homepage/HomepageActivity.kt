package com.githubyss.mobile.common.kit.app.page.homepage

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarActivity
import com.githubyss.mobile.common.res.databinding.ComresActivityBaseToolbarBinding


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:34
 */
class HomepageActivity : BaseReflectBindingToolbarActivity<ComresActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = HomepageActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun init() {
        switchFragment(HomepageFragment(), HomepageFragment.TAG, false)
    }

    override fun destroy() {
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_homepage_title)
    }
}
