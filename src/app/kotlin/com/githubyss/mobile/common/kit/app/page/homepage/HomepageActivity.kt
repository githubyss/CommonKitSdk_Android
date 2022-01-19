package com.githubyss.mobile.common.kit.app.page.homepage

import android.os.Bundle
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.inline.BaseInlineBindingToolbarActivity
import com.githubyss.mobile.common.kit.util.LogUtils


/**
 * HomepageActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/16 11:39:34
 */
class HomepageActivity : BaseInlineBindingToolbarActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = HomepageActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    override fun init() {
        addFragment(HomepageFragment(), HomepageFragment.TAG, false, binding.layoutFragmentContainer.id)
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_homepage_title)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityCreated(activity: Activity, savedInstanceState: Bundle?)
     *
     * @param savedInstanceState
     * @return
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "${this::class.java.simpleName} > onCreate")
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityStarted(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onStart() {
        super.onStart()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onStart")
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 Nothing
     *
     * @param
     * @return
     */
    override fun onRestart() {
        super.onRestart()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onRestart")
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityResumed(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onResume")
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityPaused(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onPause() {
        super.onPause()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onPause")
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityStopped(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onStop() {
        super.onStop()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onStop")
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivitySaveInstanceState(activity: Activity, outState: Bundle)
     *
     * @param outState
     * @return
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityDestroyed(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "${this::class.java.simpleName} > onDestroy")
    }
}
