package com.githubyss.mobile.common.kit.app.page.view_binding.reflect

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarActivity
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.res.databinding.ComresActivityBaseToolbarBinding


/**
 * ReflectToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 19:17:26
 */
class ReflectToolbarActivity : BaseReflectBindingToolbarActivity<ComresActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = ReflectToolbarActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun init() {
        FragmentUtils.switchFragmentWithAddHideShow(ReflectToolbarFragment(), ReflectToolbarFragment.TAG, null, supportFragmentManager, false, binding?.layoutFragmentContainer?.id ?: return)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_view_binding_toolbar_reflect_title)
    }
}
