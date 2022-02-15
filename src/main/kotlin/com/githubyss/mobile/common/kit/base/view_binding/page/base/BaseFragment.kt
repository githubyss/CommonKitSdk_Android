package com.githubyss.mobile.common.kit.base.view_binding.page.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.util.FragmentUtils
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val message = "${this::class.java.simpleName} > onAttach"
        LogUtils.d(TAG, message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message = "${this::class.java.simpleName} > onCreate"
        LogUtils.d(TAG, message)

        fragmentContext = activity?.baseContext
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val message = "${this::class.java.simpleName} > onCreateView"
        LogUtils.d(TAG, message)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = "${this::class.java.simpleName} > onViewCreated"
        LogUtils.d(TAG, message)

        init()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val message = "${this::class.java.simpleName} > onActivityCreated"
        LogUtils.d(TAG, message)
    }

    override fun onStart() {
        super.onStart()
        val message = "${this::class.java.simpleName} > onStart"
        LogUtils.d(TAG, message)
    }

    override fun onResume() {
        super.onResume()
        val message = "${this::class.java.simpleName} > onResume"
        LogUtils.d(TAG, message)
    }

    override fun onPause() {
        super.onPause()
        val message = "${this::class.java.simpleName} > onPause"
        LogUtils.d(TAG, message)
    }

    override fun onStop() {
        super.onStop()
        val message = "${this::class.java.simpleName} > onStop"
        LogUtils.d(TAG, message)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val message = "${this::class.java.simpleName} > onSaveInstanceState"
        LogUtils.d(TAG, message)
    }

    override fun onDestroyView() {
        destroy()

        val message = "${this::class.java.simpleName} > onDestroyView"
        LogUtils.d(TAG, message)
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        val message = "${this::class.java.simpleName} > onDestroy"
        LogUtils.d(TAG, message)
    }

    override fun onDetach() {
        super.onDetach()
        val message = "${this::class.java.simpleName} > onDetach"
        LogUtils.d(TAG, message)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        val message = "${this::class.java.simpleName} > onAttachFragment"
        LogUtils.d(TAG, message)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        val message = "${this::class.java.simpleName} > onHiddenChanged, hidden:${hidden}"
        LogUtils.d(TAG, message)
    }


    /** ****************************** Open ****************************** */

    open fun init() {
        observeViewModel()
    }

    open fun destroy() {
        removeViewModelObserver()
    }

    open fun observeViewModel() {}
    open fun removeViewModelObserver() {}


    /** ****************************** Functions ****************************** */

    /** Change button status. */
    protected fun changeBtnStatus(button: Button?, status: Boolean) {
        button?.isEnabled = status
        button?.isClickable = status
    }

    /** Switch fragment within fragments. */
    protected fun switchFragment(fragment: Fragment, fragmentTag: String? = null, currentFragment: Any?, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragment_container) {
        FragmentUtils.switchFragmentWithAddHideShow(fragment, fragmentTag, currentFragment, parentFragmentManager, addToBackStack, containerId)
    }
}
