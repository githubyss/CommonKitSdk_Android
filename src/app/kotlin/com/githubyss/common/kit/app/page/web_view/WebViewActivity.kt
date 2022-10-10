package com.githubyss.common.kit.app.page.web_view

import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarActivity
import com.githubyss.common.base.databinding.CombaseActivityBaseToolbarBinding
import com.githubyss.common.kit.R


/**
 * WebViewActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/18 15:35:51
 */
class WebViewActivity : BaseReflectBindingViewModelToolbarActivity<CombaseActivityBaseToolbarBinding>() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        private val TAG = WebViewActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        switchFragment(WebViewFragment(), WebViewFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_web_view_title)
    }
}
