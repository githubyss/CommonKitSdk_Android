package com.githubyss.mobile.common.kit.app.page.lifecycle

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarFragment
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentLifecycleBinding
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.FragmentUtils


/**
 * LifecycleFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/11 14:51:56
 */
class LifecycleFragment : BaseReflectBindingViewModelToolbarFragment<ComkitFragmentLifecycleBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = LifecycleFragment::class.java.simpleName
    }

    private val lifecycleVm: LifecycleViewModel by viewModels()


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupData() {
        this.lifecycleVm.viewId?.value = 0
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_lifecycle_title)
    }

    override fun bindLifecycleOwner() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun bindViewModelXml() {
        binding.lifecycleVm = lifecycleVm
    }

    override fun observeViewModelData() {
        // this.lifecycleVm.lifecycleLog?.observe(viewLifecycleOwner, vmObserverLifecycleLog)
        this.lifecycleVm.viewId?.observe(viewLifecycleOwner, vmObserverViewId)
    }

    override fun removeViewModelObserver() {
        this.lifecycleVm.lifecycleLog?.removeObservers(viewLifecycleOwner)
        this.lifecycleVm.viewId?.removeObservers(viewLifecycleOwner)
    }


    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context)
     *
     * @param context
     * @return
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val message = "${this::class.java.simpleName} > onAttach"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?)
     *
     * @param savedInstanceState
     * @return
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message = "${this::class.java.simpleName} > onCreate"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 Nothing
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val message = "${this::class.java.simpleName} > onCreateView"
        refreshLifecycleLog(message)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?)
     *
     * @param view
     * @param savedInstanceState
     * @return
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = "${this::class.java.simpleName} > onViewCreated"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?)
     *
     * @param savedInstanceState
     * @return
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val message = "${this::class.java.simpleName} > onActivityCreated"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentStarted(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onStart() {
        super.onStart()
        val message = "${this::class.java.simpleName} > onStart"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentResumed(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onResume() {
        super.onResume()
        val message = "${this::class.java.simpleName} > onResume"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentPaused(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onPause() {
        super.onPause()
        val message = "${this::class.java.simpleName} > onPause"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentStopped(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onStop() {
        super.onStop()
        val message = "${this::class.java.simpleName} > onStop"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle)
     *
     * @param outState
     * @return
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val message = "${this::class.java.simpleName} > onSaveInstanceState"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentViewDestroyed(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onDestroyView() {
        super.onDestroyView()
        val message = "${this::class.java.simpleName} > onDestroyView"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentDestroyed(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onDestroy() {
        super.onDestroy()
        val message = "${this::class.java.simpleName} > onDestroy"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentDetached(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onDetach() {
        super.onDetach()
        val message = "${this::class.java.simpleName} > onDetach"
        refreshLifecycleLog(message)
    }

    /**
     * 当一个子 Fragment attach 到当前 Fragment 时
     *
     * @param childFragment
     * @return
     */
    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        val message = "${this::class.java.simpleName} > onAttachFragment"
        refreshLifecycleLog(message)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        val message = "${this::class.java.simpleName} > onHiddenChanged, hidden:${hidden}"
        refreshLifecycleLog(message)
    }


    /** ****************************** Functions ****************************** */

    private fun refreshLifecycleLog(message: String) {
        this.lifecycleVm.lifecycleLogEntity?.append(message)?.appendLine()
        this.lifecycleVm.lifecycleLog?.value = this.lifecycleVm.lifecycleLogEntity
    }

    private fun clearLifecycleLog() {
        this.lifecycleVm.lifecycleLogEntity?.clear()
        this.lifecycleVm.lifecycleLog?.value = this.lifecycleVm.lifecycleLogEntity
    }


    /** ****************************** Implementations ****************************** */

    private val vmObserverLifecycleLog = Observer<StringBuilder> { t ->
    }

    private val vmObserverViewId = Observer<Int> { t ->
        when (t) {
            R.id.button_start_activity_1 -> ActivityUtils.startActivity(activity, LifecycleNextActivity::class.java)
            R.id.button_start_activity_2 -> ActivityUtils.startActivity(activity, LifecycleNextActivity::class.java)
            R.id.button_add_fragment -> FragmentUtils.switchFragmentByAddHideShow(LifecycleNextFragment(), LifecycleNextFragment.TAG, this, parentFragmentManager, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
            R.id.button_replace_fragment -> FragmentUtils.replaceFragment(LifecycleNextFragment(), LifecycleNextFragment.TAG, parentFragmentManager, BaseActivity.FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, true)
            R.id.button_clear_log -> {
                clearLifecycleLog()
            }
        }
    }
}
