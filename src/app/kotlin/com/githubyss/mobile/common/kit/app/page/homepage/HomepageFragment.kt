package com.githubyss.mobile.common.kit.app.page.homepage

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.common.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.json_utils.JsonUtilsFragment
import com.githubyss.mobile.common.kit.app.page.web_view.WebViewActivity
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentHomepageBinding
import com.githubyss.mobile.common.kit.util.startActivityExt
import com.githubyss.mobile.common.kit.util.switchFragmentByAddHideShow


/**
 * HomepageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:42
 */
class HomepageFragment : BaseReflectBindingViewModelToolbarFragment<ComkitFragmentHomepageBinding>() {

    /** ****************************** Properties ****************************** */

    /**  */
    companion object {
        val TAG: String = HomepageFragment::class.java.simpleName
    }

    /**  */
    private val homepageVm: HomepageViewModel by viewModels()


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupData() {
        this.homepageVm.viewId?.value = 0
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_homepage_title)
    }

    /**  */
    override fun bindLifecycleOwner() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    /**  */
    override fun bindXmlData() {
        binding.homepageVm = homepageVm
    }

    /**  */
    override fun observeViewModelData() {
        this.homepageVm.viewId?.observe(viewLifecycleOwner, vmObserverViewId)
    }

    /**  */
    override fun removeViewModelObserver() {
        this.homepageVm.viewId?.removeObservers(viewLifecycleOwner)
    }

    /**  */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            // this.homepageVm.viewId?.value = 0
        }
    }


    /** ****************************** Implementations ****************************** */

    /**  */
    private val vmObserverViewId = Observer<Int> { t ->
        when (t) {
            R.id.button_web_view -> activity.startActivityExt<WebViewActivity>()
            R.id.button_json_utils -> switchFragmentByAddHideShow(JsonUtilsFragment(), JsonUtilsFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
        }
    }
}
