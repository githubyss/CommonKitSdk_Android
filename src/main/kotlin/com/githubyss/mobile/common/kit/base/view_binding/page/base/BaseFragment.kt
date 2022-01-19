package com.githubyss.mobile.common.kit.base.view_binding.page.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.util.LogUtils


/**
 * BaseFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 15:11:59
 */
abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = BaseFragment::class.java.simpleName
    }

    var fragmentContext: Context? = null


    /** ****************************** Constructors ****************************** */

    init {
        LogUtils.d(TAG, "Constructor init")
    }


    /** ****************************** Override ****************************** */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentContext = activity?.baseContext
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    /** ****************************** Abstract ****************************** */

    abstract fun init()


    /** ****************************** Functions ****************************** */

    /** Change button status. */
    protected fun changeBtnStatus(button: Button?, status: Boolean) {
        button?.isEnabled = status
        button?.isClickable = status
    }

    /** Add fragment to activity. */
    protected fun addFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragment_container) {
        parentFragmentManager.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }

            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.add(containerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    protected fun replaceFragment(fragment: Fragment, tag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragment_container) {
        parentFragmentManager.let {
            if (it.findFragmentByTag(tag) != null) {
                return
            }

            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(containerId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commitAllowingStateLoss()
        }
    }
}
