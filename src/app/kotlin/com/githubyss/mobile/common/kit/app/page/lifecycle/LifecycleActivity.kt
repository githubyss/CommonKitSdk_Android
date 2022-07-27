package com.githubyss.mobile.common.kit.app.page.lifecycle

import android.os.Bundle
import androidx.activity.viewModels
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect_view_model.BaseReflectBindingViewModelToolbarActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * LifecycleActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/11 15:19:51
 */
class LifecycleActivity : BaseReflectBindingViewModelToolbarActivity<ComkitActivityBaseToolbarBinding>() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        private val TAG = LifecycleActivity::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val lifecycleVm: LifecycleViewModel by viewModels()

    private var activityName = this::class.java.simpleName


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupUi() {
        switchFragment(LifecycleFragment(), LifecycleFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }

    /**  */
    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_lifecycle_title)
    }

    /**  */
    override fun bindLifecycleOwner() {
        binding.lifecycleOwner = this
    }

    /**  */
    override fun bindXmlData() {
    }

    /**  */
    override fun observeViewModelData() {
        this.lifecycleVm.lifecycleLog?.observe(this) {}
    }

    /**  */
    override fun removeViewModelObserver() {
        this.lifecycleVm.lifecycleLog?.removeObservers(this)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityCreated(activity: Activity, savedInstanceState: Bundle?)
     *
     * @param savedInstanceState
     * @return
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message = "$activityName > onCreate"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityStarted(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onStart() {
        super.onStart()
        val message = "$activityName > onStart"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 Nothing
     *
     * @param
     * @return
     */
    override fun onRestart() {
        super.onRestart()
        val message = "$activityName > onRestart"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityResumed(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onResume() {
        super.onResume()
        val message = "$activityName > onResume"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityPaused(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onPause() {
        super.onPause()
        val message = "$activityName > onPause"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityStopped(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onStop() {
        super.onStop()
        val message = "$activityName > onStop"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivitySaveInstanceState(activity: Activity, outState: Bundle)
     *
     * @param outState
     * @return
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val message = "$activityName > onSaveInstanceState"
        refreshLifecycleLog(message)
    }

    /**
     * 对应 ActivityLifecycleCallbacks 的 onActivityDestroyed(activity: Activity)
     *
     * @param
     * @return
     */
    override fun onDestroy() {
        super.onDestroy()
        val message = "$activityName > onDestroy"
        refreshLifecycleLog(message)
    }


    /** ****************************** Functions ****************************** */

    /**  */
    private fun refreshLifecycleLog(message: String) {
        this.lifecycleVm.lifecycleLogEntity?.append(message)?.appendLine()
        this.lifecycleVm.lifecycleLog?.value = this.lifecycleVm.lifecycleLogEntity
    }
}
