package com.githubyss.mobile.common.kit.app.page.homepage

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.binding_inline.InlineActivity
import com.githubyss.mobile.common.kit.app.page.binding_inline.InlineToolbarActivity
import com.githubyss.mobile.common.kit.app.page.binding_reflect.ReflectActivity
import com.githubyss.mobile.common.kit.app.page.binding_reflect.ReflectToolbarActivity
import com.githubyss.mobile.common.kit.app.page.compose.ComposeActivity
import com.githubyss.mobile.common.kit.app.page.compose.ComposeToolbarActivity
import com.githubyss.mobile.common.kit.app.page.json_utils.JsonUtilsFragment
import com.githubyss.mobile.common.kit.app.page.lifecycle.LifecycleActivity
import com.githubyss.mobile.common.kit.app.page.mvi.MviActivity
import com.githubyss.mobile.common.kit.app.page.mvvm_binding.MvvmFragment
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.common.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentHomepageBinding
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.kit.util.startActivityExt


/**
 * HomepageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:42
 */
class HomepageFragment : BaseReflectBindingViewModelToolbarFragment<ComkitFragmentHomepageBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = HomepageFragment::class.java.simpleName
    }

    private val homepageVm: HomepageViewModel by viewModels()


    /** ****************************** Override ****************************** */

    override fun setupData() {
        this.homepageVm.viewId?.value = 0
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_homepage_title)
    }

    override fun bindLifecycleOwner() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun bindXmlData() {
        binding.homepageVm = homepageVm
    }

    override fun observeViewModelData() {
        this.homepageVm.viewId?.observe(viewLifecycleOwner, vmObserverViewId)
    }

    override fun removeViewModelObserver() {
        this.homepageVm.viewId?.removeObservers(viewLifecycleOwner)
    }

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
            R.id.button_mvvm -> FragmentUtils.switchFragmentByAddHideShow(MvvmFragment(), MvvmFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
            R.id.button_mvi -> startActivityExt(activity, MviActivity::class.java)

            R.id.button_compose -> {
                startActivityExt(activity, ComposeActivity::class.java)
                // FragmentUtils.switchFragmentByAddHideShow(ComposeFragment(), ComposeFragment.TAG, this, parentFragmentManager, true)
            }
            R.id.button_compose_toolbar -> {
                startActivityExt(activity, ComposeToolbarActivity::class.java)
                // FragmentUtils.switchFragmentByAddHideShow(ComposeToolbarFragment(), ComposeToolbarFragment.TAG, this, parentFragmentManager, true)
            }

            R.id.button_binding_reflect -> startActivityExt(activity, ReflectActivity::class.java)
            R.id.button_binding_reflect_toolbar -> startActivityExt(activity, ReflectToolbarActivity::class.java)
            R.id.button_binding_inline -> startActivityExt(activity, InlineActivity::class.java)
            R.id.button_binding_inline_toolbar -> startActivityExt(activity, InlineToolbarActivity::class.java)

            R.id.button_log -> {
            }
            R.id.button_json_utils -> FragmentUtils.switchFragmentByAddHideShow(JsonUtilsFragment(), JsonUtilsFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
            R.id.btn_lifecycle -> startActivityExt(activity, LifecycleActivity::class.java)
        }
    }
}
