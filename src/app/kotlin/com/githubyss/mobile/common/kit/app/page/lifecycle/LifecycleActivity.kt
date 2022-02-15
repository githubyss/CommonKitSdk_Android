package com.githubyss.mobile.common.kit.app.page.lifecycle

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.view_binding.page.reflect.BaseReflectBindingToolbarActivity
import com.githubyss.mobile.common.kit.util.FragmentUtils
import com.githubyss.mobile.common.res.databinding.ComresActivityBaseToolbarBinding


/**
 * LifecycleActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/11 15:19:51
 */
class LifecycleActivity : BaseReflectBindingToolbarActivity<ComresActivityBaseToolbarBinding>() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = LifecycleActivity::class.java.simpleName
    }

    private val lifecycleVm: LifecycleViewModel by lazy { ViewModelProvider(this).get(LifecycleViewModel::class.java) }


    /** ****************************** Override ****************************** */

    override fun init() {
        super.init()
        initView()
        initData()
        FragmentUtils.switchFragmentWithAddHideShow(LifecycleFragment(), LifecycleFragment.TAG, null, supportFragmentManager, false, binding?.layoutFragmentContainer?.id ?: return)
    }

    override fun destroy() {
        super.destroy()
    }

    override fun setToolbarTitle() {
        setToolbarTitle(R.string.comkit_lifecycle_title)
    }

    override fun observeViewModel() {
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

    private fun initView() {
        binding?.lifecycleOwner = this
    }

    private fun initData() {
    }

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
