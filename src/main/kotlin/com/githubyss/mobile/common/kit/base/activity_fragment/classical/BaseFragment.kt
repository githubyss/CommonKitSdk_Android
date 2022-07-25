package com.githubyss.mobile.common.kit.base.activity_fragment.classical

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.kit.util.logD


/**
 * BaseFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 15:11:59
 */
abstract class BaseFragment(@LayoutRes layoutId: Int = 0) : Fragment(layoutId) {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = BaseFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private var fragmentName = this::class.java.simpleName


    /** ****************************** Constructors ****************************** */

    /**  */
    init {
        logD(TAG, "Constructor init")
    }


    /** ****************************** Open ****************************** */

    /** 初始化 UI */
    open fun setupUi() {}

    /** 初始化数据 */
    open fun setupData() {}


    /** ****************************** Override ****************************** */

    /**  */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val message = "$fragmentName > onAttach"
        logD(TAG, message)
    }

    /**  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message = "$fragmentName > onCreate"
        logD(TAG, message)
    }

    /**  */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val message = "$fragmentName > onCreateView"
        logD(TAG, message)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**  */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = "$fragmentName > onViewCreated"
        logD(TAG, message)

        setupUi()
        setupData()
    }

    /**  */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val message = "$fragmentName > onActivityCreated"
        logD(TAG, message)
    }

    /**  */
    override fun onStart() {
        super.onStart()
        val message = "$fragmentName > onStart"
        logD(TAG, message)
    }

    /**  */
    override fun onResume() {
        super.onResume()
        val message = "$fragmentName > onResume"
        logD(TAG, message)
    }

    /**  */
    override fun onPause() {
        super.onPause()
        val message = "$fragmentName > onPause"
        logD(TAG, message)
    }

    /**  */
    override fun onStop() {
        super.onStop()
        val message = "$fragmentName > onStop"
        logD(TAG, message)
    }

    /**  */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val message = "$fragmentName > onSaveInstanceState"
        logD(TAG, message)
    }

    /**  */
    override fun onDestroyView() {
        val message = "$fragmentName > onDestroyView"
        logD(TAG, message)
        super.onDestroyView()
    }

    /**  */
    override fun onDestroy() {
        super.onDestroy()
        val message = "$fragmentName > onDestroy"
        logD(TAG, message)
    }

    /**  */
    override fun onDetach() {
        super.onDetach()
        val message = "$fragmentName > onDetach"
        logD(TAG, message)
    }

    /**  */
    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        val message = "$fragmentName > onAttachFragment"
        logD(TAG, message)
    }

    /**  */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        val message = "$fragmentName > onHiddenChanged, hidden:${hidden}"
        logD(TAG, message)
    }


    /** ****************************** Functions ****************************** */

    /** Change button status. */
    // protected fun changeBtnStatus(button: Button?, status: Boolean) {
    //     button?.isEnabled = status
    //     button?.isClickable = status
    // }

    /** Switch fragment within fragments. */
    protected fun switchFragment(fragment: Fragment, fragmentTag: String? = null, currentFragment: Any?, @IdRes containerId: Int, addToBackStack: Boolean = true) {
        FragmentUtils.switchFragmentByAddHideShow(fragment, fragmentTag, currentFragment, parentFragmentManager, containerId, addToBackStack)
    }
}
