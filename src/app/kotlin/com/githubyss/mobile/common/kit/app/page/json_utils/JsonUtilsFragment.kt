package com.githubyss.mobile.common.kit.app.page.json_utils

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.json_utils.view_model.JsonUtilsViewModel
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentJsonUtilsBinding


/**
 * JsonUtilsFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/09 16:00:29
 */
class JsonUtilsFragment : BaseReflectBindingToolbarFragment<ComkitFragmentJsonUtilsBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = JsonUtilsFragment::class.java.simpleName
    }

    private val jsonUtilsViewModel: JsonUtilsViewModel by lazy { ViewModelProvider(requireActivity()).get(JsonUtilsViewModel::class.java) }


    /** ****************************** Override ****************************** */

    override fun init() {
        initView()
        initData()
        observeViewModel()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_json_utils_title)
    }

    override fun destroy() {
        removeViewModelObserver()
    }


    /** ****************************** Functions ****************************** */

    private fun initView() {
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    private fun initData() {
        binding?.jsonUtilsViewModel = jsonUtilsViewModel
    }

    private fun observeViewModel() {
        this.jsonUtilsViewModel.jsonText?.observe(viewLifecycleOwner, viewModelObserver)
    }

    private fun removeViewModelObserver() {
        this.jsonUtilsViewModel.jsonText?.removeObservers(viewLifecycleOwner)
    }


    /** ****************************** Implementations ****************************** */

    private val viewModelObserver = Observer<String> { t ->
        when (t) {

        }
    }
}
