package com.githubyss.mobile.common.kit.app.page.binding_reflect

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingFragment
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentViewBindingReflectBinding
import com.githubyss.mobile.common.kit.util.getStringFromRes


/**
 * ReflectFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/21 11:16:42
 */
class ReflectFragment : BaseReflectBindingFragment<ComkitFragmentViewBindingReflectBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = ReflectFragment::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        binding?.textBindingReflect?.text = getStringFromRes(R.string.comkit_view_binding_reflect)
    }
}
