package com.githubyss.mobile.common.kit.app.page.lifecycle

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_reflect.BaseReflectBindingToolbarActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseToolbarBinding


/**
 * LifecycleActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/11 15:19:51
 */
class LifecycleActivity : BaseReflectBindingToolbarActivity<ComkitActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = LifecycleActivity::class.java.simpleName
    }

    private val lifecycleVm: LifecycleViewModel by viewModels()


    /** ****************************** Override ****************************** */

    override fun setupUi() {
        binding?.lifecycleOwner = this
        switchFragment(LifecycleFragment(), LifecycleFragment.TAG, FRAGMENT_BASE_TOOLBAR_CONTAINER_ID, false)
    }

    override fun setupData() {
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_lifecycle_title)
    }

    override fun bindViewModelXml() {
    }

    override fun observeViewModelData() {
        // this.lifecycleVm.lifecycleLog?.observe(this, vmObserver)
    }

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
        val message = "${this::class.java.simpleName} > onCreate"
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
        val message = "${this::class.java.simpleName} > onStart"
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
        val message = "${this::class.java.simpleName} > onRestart"
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
        val message = "${this::class.java.simpleName} > onResume"
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
        val message = "${this::class.java.simpleName} > onPause"
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
        val message = "${this::class.java.simpleName} > onStop"
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
        val message = "${this::class.java.simpleName} > onSaveInstanceState"
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
        val message = "${this::class.java.simpleName} > onDestroy"
        refreshLifecycleLog(message)
    }


    /** ****************************** Functions ****************************** */

    private fun refreshLifecycleLog(message: String) {
        this.lifecycleVm.lifecycleLogEntity?.append(message)?.appendLine()
        this.lifecycleVm.lifecycleLog?.value = this.lifecycleVm.lifecycleLogEntity
    }


    /** ****************************** Implementations ****************************** */

    private val vmObserver = Observer<StringBuilder> { t ->
        when (t) {

        }
    }
}
