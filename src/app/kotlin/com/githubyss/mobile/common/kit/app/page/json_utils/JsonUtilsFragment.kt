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

    private val jsonUtilsVm: JsonUtilsViewModel by lazy { ViewModelProvider(requireActivity()).get(JsonUtilsViewModel::class.java) }


    /** ****************************** Override ****************************** */

    override fun init() {
        super.init()
        initView()
        initData()
    }

    override fun destroy() {
        super.destroy()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_json_utils_title)
    }

    override fun observeViewModel() {
        this.jsonUtilsVm.jsonText?.observe(viewLifecycleOwner, vmObserver)
    }

    override fun removeViewModelObserver() {
        this.jsonUtilsVm.jsonText?.removeObservers(viewLifecycleOwner)
    }


    /** ****************************** Functions ****************************** */

    private fun initView() {
        binding?.lifecycleOwner = viewLifecycleOwner
    }

    private fun initData() {
        binding?.jsonUtilsVm = jsonUtilsVm
    }


    /** ****************************** Implementations ****************************** */

    private val vmObserver = Observer<String> { t ->
        when (t) {

        }
    }
}
