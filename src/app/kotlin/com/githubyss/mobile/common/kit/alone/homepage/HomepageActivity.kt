package com.githubyss.mobile.common.kit.alone.homepage

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.ui.base.view_binding.page.inline.BaseToolbarActivity


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:34
 */
class HomepageActivity : BaseToolbarActivity() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        private val TAG: String = HomepageActivity::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    override fun init() {
        addFragment(HomepageFragment(), HomepageFragment.TAG, false, binding.layoutFragmentContainer.id)
    }
    
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_homepage_title)
    }
}
