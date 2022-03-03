package com.githubyss.mobile.common.kit.app.page.view_binding.reflect

import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseBinding


/**
 * ReflectActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 18:01:36
 */
class ReflectActivity : BaseReflectBindingActivity<ComkitActivityBaseBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = ReflectActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        switchFragment(ReflectFragment(), ReflectFragment.TAG, FRAGMENT_BASE_CONTAINER_ID,false)
    }
}
