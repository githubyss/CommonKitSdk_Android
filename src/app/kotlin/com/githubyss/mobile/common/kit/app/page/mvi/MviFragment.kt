package com.githubyss.mobile.common.kit.app.page.mvi

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.json_utils.JsonUtilsFragment
import com.githubyss.mobile.common.kit.app.page.lifecycle.LifecycleActivity
import com.githubyss.mobile.common.kit.app.page.mvi.model.User
import com.githubyss.mobile.common.kit.app.page.mvvm.MvvmFragment
import com.githubyss.mobile.common.kit.app.page.view_binding.inline.InlineActivity
import com.githubyss.mobile.common.kit.app.page.view_binding.inline.InlineToolbarActivity
import com.githubyss.mobile.common.kit.app.page.view_binding.reflect.ReflectActivity
import com.githubyss.mobile.common.kit.app.page.view_binding.reflect.ReflectToolbarActivity
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentMviBinding
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.FragmentUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * MviFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/17 17:31:31
 */
class MviFragment : BaseReflectBindingToolbarFragment<ComkitFragmentMviBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = MviFragment::class.java.simpleName
    }

    private lateinit var mviViewModel: MviViewModel
    private var adapter = MviAdapter(arrayListOf())


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        binding?.recyclerView?.layoutManager = LinearLayoutManager(activity)
        binding?.recyclerView?.run {
            addItemDecoration(
                DividerItemDecoration(
                    binding?.recyclerView?.context,
                    (binding?.recyclerView?.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        binding?.recyclerView?.adapter = adapter

        setupClicks()
    }

    override fun setupViewModel() {
        mviViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                // ApiHelperImpl(
                RetrofitBuilder.apiService
                // )
            )
        ).get(MviViewModel::class.java)
    }

    override fun observeViewModel() {
        lifecycleScope.launch {
            mviViewModel.state.collect {
                when (it) {
                    is MviState.Idle -> {

                    }
                    is MviState.Loading -> {
                        binding?.buttonFetchUser?.visibility = View.GONE
                        binding?.progressBar?.visibility = View.VISIBLE
                    }

                    is MviState.Users -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.buttonFetchUser?.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MviState.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.buttonFetchUser?.visibility = View.VISIBLE
                        Toast.makeText(activity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun removeViewModelObserver() {
        // this.mviViewModel.viewId?.removeObservers(viewLifecycleOwner)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_mvi_title)
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
        // binding?.homepageVm = homepageVm
        // this.homepageVm.viewId?.value = 0
    }

    private fun setupClicks() {
        binding?.buttonFetchUser?.setOnClickListener {
            lifecycleScope.launch {
                mviViewModel.userIntent.send(MviIntent.FetchUser)
            }
        }
    }

    private fun renderList(users: List<User>) {
        binding?.recyclerView?.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }


    /** ****************************** Implementations ****************************** */

    private val vmObserverViewId = Observer<Int> { t ->
        when (t) {
            R.id.button_mvvm -> FragmentUtils.switchFragmentByAddHideShow(MvvmFragment(), MvvmFragment.TAG, this, parentFragmentManager, R.id.layout_fragment_base_container, true)

            R.id.button_binding_reflect -> ActivityUtils.startActivity(activity, ReflectActivity::class.java)
            R.id.button_binding_inline -> ActivityUtils.startActivity(activity, InlineActivity::class.java)
            R.id.button_binding_reflect_toolbar -> ActivityUtils.startActivity(activity, ReflectToolbarActivity::class.java)
            R.id.button_binding_inline_toolbar -> ActivityUtils.startActivity(activity, InlineToolbarActivity::class.java)

            R.id.button_log -> {
            }
            R.id.button_json_utils -> FragmentUtils.switchFragmentByAddHideShow(JsonUtilsFragment(), JsonUtilsFragment.TAG, this, parentFragmentManager, R.id.layout_fragment_base_container, true)
            R.id.btn_lifecycle -> ActivityUtils.startActivity(activity, LifecycleActivity::class.java)
        }
    }
}
