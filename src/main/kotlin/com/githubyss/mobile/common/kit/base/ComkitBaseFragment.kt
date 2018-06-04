package com.githubyss.mobile.common.kit.base

import android.app.Fragment
import android.widget.Button
import com.githubyss.mobile.common.kit.R
import kotlinx.android.synthetic.main.comkit_toolbar_base.*

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

    /** Change button status. by Ace Yan */
    open fun changeBtnStatus(button: Button?, status: Boolean) {
        button?.isEnabled = status
        button?.isClickable = status
    }


    /** Setup Toolbar title by ResId. by Ace Yan */
    protected fun setToolbarTitle(titleResId: Int) {
        activity.toolbarBase.setTitle(titleResId)
    }

    /** Setup Toolbar title by String. by Ace Yan */
    protected fun setToolbarTitle(titleString: String) {
        activity.toolbarBase.title = titleString
    }

    /** Add fragment to activity. by Ace Yan */
    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean) {
        if (fragmentManager.findFragmentByTag(tag) != null) {
            return
        }

        fragment.arguments = activity.intent.extras
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.flFragmentContainer, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    protected fun replaceFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean) {
        if (fragmentManager.findFragmentByTag(tag) != null) {
            return
        }

        fragment.arguments = activity.intent.extras
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flFragmentContainer, fragment, tag)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
}
