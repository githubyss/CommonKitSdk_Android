package com.githubyss.mobile.common.kit.base.activity_fragment.classical

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.kit.util.logD


/**
 * BaseActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/02 15:10:38
 */
abstract class BaseActivity(@LayoutRes layoutId: Int = 0) : AppCompatActivity(layoutId) {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = BaseActivity::class.java.simpleName

        @IdRes
        val FRAGMENT_BASE_CONTAINER_ID: Int = R.id.layout_fragment_base_container

        @IdRes
        val FRAGMENT_BASE_TOOLBAR_CONTAINER_ID: Int = R.id.layout_fragment_base_toolbar_container
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.let {
            it.registerFragmentLifecycleCallbacks(FragmentUtils.fragmentLifecycle, true)
        }

        val message = "${this::class.java.simpleName} > onCreate"
        logD(TAG, message)

        setupUi()
        setupData()
        setupViewModel()
        observeViewModel()
    }

    override fun onStart() {
        super.onStart()
        val message = "${this::class.java.simpleName} > onStart"
        logD(TAG, message)
    }

    override fun onRestart() {
        super.onRestart()
        val message = "${this::class.java.simpleName} > onRestart"
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

    override fun onDestroy() {
        removeViewModelObserver()

        val message = "${this::class.java.simpleName} > onDestroy"
        logD(TAG, message)

        supportFragmentManager.let {
            it.unregisterFragmentLifecycleCallbacks(FragmentUtils.fragmentLifecycle)
        }
        super.onDestroy()
    }


    /** ****************************** Functions ****************************** */

    /** Switch fragment to activity. */
    protected fun switchFragment(fragment: Fragment, fragmentTag: String? = null, @IdRes containerId: Int, addToBackStack: Boolean = true) {
        FragmentUtils.switchFragmentByAddHideShow(fragment, fragmentTag, null, supportFragmentManager, containerId, addToBackStack, intent.extras)
    }
}
