package com.githubyss.mobile.common.kit.app.page.lifecycle

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarFragment
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentLifecycleNextBinding


/**
 * LifecycleNextFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/15 10:40:03
 */
class LifecycleNextFragment : BaseReflectBindingToolbarFragment<ComkitFragmentLifecycleNextBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = LifecycleNextFragment::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun init() {
        super.init()
    }

    override fun destroy() {
        super.destroy()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_lifecycle_title)
    }
}
