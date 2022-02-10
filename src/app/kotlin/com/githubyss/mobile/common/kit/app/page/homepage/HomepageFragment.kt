package com.githubyss.mobile.common.kit.app.page.homepage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.json_utils.JsonUtilsFragment
import com.githubyss.mobile.common.kit.app.page.mvvm.MvvmFragment
import com.githubyss.mobile.common.kit.app.page.view_binding.inline.InlineActivity
import com.githubyss.mobile.common.kit.app.page.view_binding.inline.InlineToolbarActivity
import com.githubyss.mobile.common.kit.app.page.view_binding.reflect.ReflectActivity
import com.githubyss.mobile.common.kit.app.page.view_binding.reflect.ReflectToolbarActivity
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingToolbarFragment
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.bindView
import com.githubyss.mobile.common.kit.databinding.ComkitFragmentHomepageBinding
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.LogUtils


/**
 * HomepageFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:42
 */
class HomepageFragment : BaseInlineBindingToolbarFragment(R.layout.comkit_fragment_homepage) {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = HomepageFragment::class.java.simpleName
    }

    private val binding by bindView<ComkitFragmentHomepageBinding>()


    /** ****************************** Override ****************************** */

    override fun init() {
        initView()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_homepage_title)
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context)
     *
     * @param context
     * @return
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onAttach")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?)
     *
     * @param savedInstanceState
     * @return
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onCreate")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 Nothing
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtils.d(TAG, "${this::class.java.simpleName} > onCreateView")
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
        LogUtils.d(TAG, "${this::class.java.simpleName} > onViewCreated")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?)
     *
     * @param savedInstanceState
     * @return
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onActivityCreated")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentStarted(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onStart() {
        super.onStart()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onStart")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentResumed(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onResume")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentPaused(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onPause() {
        super.onPause()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onPause")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentStopped(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onStop() {
        super.onStop()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onStop")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle)
     *
     * @param outState
     * @return
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onSaveInstanceState")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentViewDestroyed(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onDestroyView")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentDestroyed(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onDestroy")
    }

    /**
     * 对应 FragmentLifecycleCallbacks 的 onFragmentDetached(fm: FragmentManager, f: Fragment)
     *
     * @param
     * @return
     */
    override fun onDetach() {
        super.onDetach()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onDetach")
    }

    /**
     * 当一个子 Fragment attach 到当前 Fragment 时
     *
     * @param childFragment
     * @return
     */
    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onAttachFragment")
    }


    /** ****************************** Functions ****************************** */

    private fun initView() {
        binding.btnLog.setOnClickListener(onClickListener)

        binding.buttonMvvm.setOnClickListener(onClickListener)

        binding.buttonBindingReflect.setOnClickListener(onClickListener)
        binding.buttonBindingInline.setOnClickListener(onClickListener)
        binding.buttonBindingReflectToolbar.setOnClickListener(onClickListener)
        binding.buttonBindingInlineToolbar.setOnClickListener(onClickListener)

        binding.btnJsonUtils.setOnClickListener(onClickListener)
    }


    /** ****************************** Implementations ****************************** */

    private val onClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.btn_log -> {
            }

            R.id.button_mvvm -> replaceFragment(MvvmFragment(), MvvmFragment.TAG, true)

            R.id.button_binding_reflect -> ActivityUtils.startActivity(activity, ReflectActivity::class.java)
            R.id.button_binding_inline -> ActivityUtils.startActivity(activity, InlineActivity::class.java)
            R.id.button_binding_reflect_toolbar -> ActivityUtils.startActivity(activity, ReflectToolbarActivity::class.java)
            R.id.button_binding_inline_toolbar -> ActivityUtils.startActivity(activity, InlineToolbarActivity::class.java)

            R.id.btn_json_utils -> replaceFragment(JsonUtilsFragment(), JsonUtilsFragment.TAG, true)
        }
    }
}
