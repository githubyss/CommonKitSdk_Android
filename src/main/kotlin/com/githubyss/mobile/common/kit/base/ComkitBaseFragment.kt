package com.githubyss.mobile.common.kit.base

import android.app.Fragment

/**
 * ComkitBaseFragment.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
abstract class ComkitBaseFragment : Fragment() {
    /** Bind Presenter. by Ace Yan*/
    open fun bindPresenter() {}

    /** Init Views, Listeners, Adapters, and so on. by Ace Yan */
    open fun initView() {}

    /** Init default value of Top-level variables, and so on. by Ace Yan */
    open fun initData() {}

    /** Refresh Views. by Ace Yan */
    open fun refreshView() {}
}
