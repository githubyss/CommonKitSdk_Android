package com.githubyss.mobile.common.kit.app.page.binding_reflect

import com.githubyss.mobile.common.kit.R
import com.githubyss.common.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarActivity
import com.githubyss.common.base.databinding.CombaseActivityBaseToolbarBinding


/**
 * ReflectToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 19:17:26
 */
class ReflectToolbarActivity : BaseReflectBindingToolbarActivity<CombaseActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = ReflectToolbarActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_view_binding_reflect_toolbar_title)
    }

    override fun setupUi() {
        switchFragment(ReflectToolbarFragment(), ReflectToolbarFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }
}
