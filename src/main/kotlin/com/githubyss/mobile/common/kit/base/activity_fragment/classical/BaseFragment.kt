package com.githubyss.mobile.common.kit.base.activity_fragment.classical

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = BaseFragment::class.java.simpleName
    }


    /** ****************************** Constructors ****************************** */

    init {
        logD(TAG, "Constructor init")
    }


    /** ****************************** Open ****************************** */

    open fun setupUi() {}
    open fun setupData() {}
    open fun setupViewModel() {}
    open fun observeViewModel() {}
    open fun removeViewModelObserver() {}


    /** ****************************** Override ****************************** */

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val message = "${this::class.java.simpleName} > onAttach"
        logD(TAG, message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message = "${this::class.java.simpleName} > onCreate"
        logD(TAG, message)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val message = "${this::class.java.simpleName} > onCreateView"
        logD(TAG, message)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = "${this::class.java.simpleName} > onViewCreated"
        logD(TAG, message)

        setupUi()
        setupData()
        setupViewModel()
        observeViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val message = "${this::class.java.simpleName} > onActivityCreated"
        logD(TAG, message)
    }

    override fun onStart() {
        super.onStart()
        val message = "${this::class.java.simpleName} > onStart"
        logD(TAG, message)
    }

    override fun onResume() {
        super.onResume()
        val message = "${this::class.java.simpleName} > onResume"
        logD(TAG, message)
    }

    override fun onPause() {
        super.onPause()
        val message = "${this::class.java.simpleName} > onPause"
        logD(TAG, message)
    }

    override fun onStop() {
        super.onStop()
        val message = "${this::class.java.simpleName} > onStop"
        logD(TAG, message)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val message = "${this::class.java.simpleName} > onSaveInstanceState"
        logD(TAG, message)
    }

    override fun onDestroyView() {
        removeViewModelObserver()

        val message = "${this::class.java.simpleName} > onDestroyView"
        logD(TAG, message)
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        val message = "${this::class.java.simpleName} > onDestroy"
        logD(TAG, message)
    }

    override fun onDetach() {
        super.onDetach()
        val message = "${this::class.java.simpleName} > onDetach"
        logD(TAG, message)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        val message = "${this::class.java.simpleName} > onAttachFragment"
        logD(TAG, message)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        val message = "${this::class.java.simpleName} > onHiddenChanged, hidden:${hidden}"
        logD(TAG, message)
    }


    /** ****************************** Functions ****************************** */

    /** Change button status. */
    protected fun changeBtnStatus(button: Button?, status: Boolean) {
        button?.isEnabled = status
        button?.isClickable = status
    }

    /** Switch fragment within fragments. */
    protected fun switchFragment(fragment: Fragment, fragmentTag: String? = null, currentFragment: Any?, @IdRes containerId: Int, addToBackStack: Boolean = true) {
        FragmentUtils.switchFragmentByAddHideShow(fragment, fragmentTag, currentFragment, parentFragmentManager, containerId, addToBackStack)
    }
}
