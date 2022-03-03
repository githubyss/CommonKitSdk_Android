package com.githubyss.mobile.common.kit.app.page.homepage

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.compose.ComposeActivity
import com.githubyss.mobile.common.kit.app.page.compose.ComposeToolbarActivity
import com.githubyss.mobile.common.kit.app.page.json_utils.JsonUtilsFragment
import com.githubyss.mobile.common.kit.app.page.lifecycle.LifecycleActivity
import com.githubyss.mobile.common.kit.app.page.mvi.MviActivity
import com.githubyss.mobile.common.kit.app.page.mvvm.MvvmFragment
import com.githubyss.mobile.common.kit.app.page.view_binding.inline.InlineActivity
import com.githubyss.mobile.common.kit.app.page.view_binding.inline.InlineToolbarActivity
import com.githubyss.mobile.common.kit.app.page.view_binding.reflect.ReflectActivity
import com.githubyss.mobile.common.kit.app.page.view_binding.reflect.ReflectToolbarActivity
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentHomepageBinding
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.FragmentUtils


/**
 * HomepageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:42
 */
class HomepageFragment : BaseReflectBindingToolbarFragment<ComkitFragmentHomepageBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = HomepageFragment::class.java.simpleName
    }

    private val homepageVm: HomepageViewModel by lazy { ViewModelProvider(requireActivity()).get(HomepageViewModel::class.java) }


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        initView()
        initData()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_homepage_title)
    }

    override fun observeViewModel() {
        this.homepageVm.viewId?.observe(viewLifecycleOwner, vmObserverViewId)
    }

    override fun removeViewModelObserver() {
        this.homepageVm.viewId?.removeObservers(viewLifecycleOwner)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setToolbarTitle()
            // this.homepageVm.viewId?.value = 0
        }
    }


    /** ****************************** Functions ****************************** */

    private fun initView() {
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    private fun initData() {
        binding?.homepageVm = homepageVm
        this.homepageVm.viewId?.value = 0
    }


    /** ****************************** Implementations ****************************** */

    private val vmObserverViewId = Observer<Int> { t ->
        when (t) {
            R.id.button_mvvm -> FragmentUtils.switchFragmentByAddHideShow(MvvmFragment(), MvvmFragment.TAG, this, parentFragmentManager, R.id.layout_fragment_base_container, true)
            R.id.button_mvi -> ActivityUtils.startActivity(activity, MviActivity::class.java)

            R.id.button_compose -> {
                ActivityUtils.startActivity(activity, ComposeActivity::class.java)
                // FragmentUtils.switchFragmentByAddHideShow(ComposeFragment(), ComposeFragment.TAG, this, parentFragmentManager, true)
            }
            R.id.button_compose_toolbar -> {
                ActivityUtils.startActivity(activity, ComposeToolbarActivity::class.java)
                // FragmentUtils.switchFragmentByAddHideShow(ComposeToolbarFragment(), ComposeToolbarFragment.TAG, this, parentFragmentManager, true)
            }

            R.id.button_binding_reflect -> ActivityUtils.startActivity(activity, ReflectActivity::class.java)
            R.id.button_binding_reflect_toolbar -> ActivityUtils.startActivity(activity, ReflectToolbarActivity::class.java)
            R.id.button_binding_inline -> ActivityUtils.startActivity(activity, InlineActivity::class.java)
            R.id.button_binding_inline_toolbar -> ActivityUtils.startActivity(activity, InlineToolbarActivity::class.java)

            R.id.button_log -> {
            }
            R.id.button_json_utils -> FragmentUtils.switchFragmentByAddHideShow(JsonUtilsFragment(), JsonUtilsFragment.TAG, this, parentFragmentManager, R.id.layout_fragment_base_container, true)
            R.id.btn_lifecycle -> ActivityUtils.startActivity(activity, LifecycleActivity::class.java)
        }
    }
}
