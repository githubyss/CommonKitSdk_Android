package com.githubyss.mobile.common.kit.app.page.lifecycle

import android.view.View
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentLifecycleNextBinding


/**
 * LifecycleNextFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/15 10:40:03
 */
class LifecycleNextFragment : BaseReflectBindingViewModelToolbarFragment<ComkitFragmentLifecycleNextBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = LifecycleNextFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val onClickPresenter by lazy { OnClickPresenter() }


    /** ****************************** Override ****************************** */

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_lifecycle_title)
    }

    /**  */
    override fun bindXmlData() {
        binding.onClickPresenter = onClickPresenter
    }

    /**  */
    override fun observeViewModelData() {
    }

    /**  */
    override fun removeViewModelObserver() {
    }


    /** ****************************** Class ****************************** */

    /**  */
    inner class OnClickPresenter {
        fun onBack(v: View) {
            activity?.onBackPressed()
        }
    }
}
