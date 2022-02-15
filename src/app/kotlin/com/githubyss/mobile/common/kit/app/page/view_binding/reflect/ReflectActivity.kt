package com.githubyss.mobile.common.kit.app.page.view_binding.reflect

import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingActivity
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.res.databinding.ComresActivityBaseBinding


/**
 * ReflectActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 18:01:36
 */
class ReflectActivity : BaseReflectBindingActivity<ComresActivityBaseBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = ReflectActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun init() {
        FragmentUtils.switchFragmentWithAddHideShow(ReflectFragment(), ReflectFragment.TAG, null, supportFragmentManager, false, binding?.layoutFragmentContainer?.id ?: return)
    }
}
