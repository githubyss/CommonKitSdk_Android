package com.githubyss.common.kit.app.page.json_utils

import androidx.fragment.app.viewModels
import com.githubyss.common.kit.R
import com.githubyss.common.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.common.kit.databinding.ComkitFragmentJsonUtilsBinding


/**
 * JsonUtilsFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/09 16:00:29
 */
class JsonUtilsFragment : BaseReflectBindingViewModelToolbarFragment<ComkitFragmentJsonUtilsBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = JsonUtilsFragment::class.java.simpleName
    }

    private val jsonUtilsVm: JsonUtilsViewModel by viewModels()


    /** ****************************** Override ****************************** */

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_json_utils_title)
    }

    override fun bindLifecycleOwner() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun bindXmlData() {
        binding.jsonUtilsVm = jsonUtilsVm
    }

    override fun observeViewModelData() {
        this.jsonUtilsVm.jsonText?.observe(viewLifecycleOwner) {}
    }

    override fun removeViewModelObserver() {
        this.jsonUtilsVm.jsonText?.removeObservers(viewLifecycleOwner)
    }
}
