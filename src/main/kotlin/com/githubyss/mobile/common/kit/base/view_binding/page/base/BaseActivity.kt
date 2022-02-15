package com.githubyss.mobile.common.kit.base.view_binding.page.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.res.R


/**
 * BaseActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 15:10:38
 */
abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = BaseActivity::class.java.simpleName
    }


    /** ****************************** Constructors ****************************** */

    init {
        LogUtils.d(TAG, "Constructor init")
    }


    /** ****************************** Override ****************************** */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.let {
            it.registerFragmentLifecycleCallbacks(FragmentUtils.fragmentLifecycle, true)
        }

        val message = "${this::class.java.simpleName} > onCreate"
        LogUtils.d(TAG, message)

        init()
    }

    override fun onStart() {
        super.onStart()
        val message = "${this::class.java.simpleName} > onStart"
        LogUtils.d(TAG, message)
    }

    override fun onRestart() {
        super.onRestart()
        val message = "${this::class.java.simpleName} > onRestart"
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

    override fun onDestroy() {
        destroy()
        val message = "${this::class.java.simpleName} > onDestroy"
        LogUtils.d(TAG, message)

        supportFragmentManager.let {
            it.unregisterFragmentLifecycleCallbacks(FragmentUtils.fragmentLifecycle)
        }
        super.onDestroy()
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

    /** Switch fragment to activity. */
    protected fun switchFragment(fragment: Fragment, fragmentTag: String? = null, addToBackStack: Boolean = true, @IdRes containerId: Int = R.id.layout_fragment_container) {
        FragmentUtils.switchFragmentWithAddHideShow(fragment, fragmentTag, null, supportFragmentManager, addToBackStack, containerId, intent.extras)
    }
}
