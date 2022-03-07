package com.githubyss.mobile.common.kit.lifecycle

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.githubyss.mobile.common.kit.util.logD


/**
 * FragmentLifecycleSubscriber
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 10:14:08
 */
open class FragmentLifecycleSubscriber private constructor() : FragmentManager.FragmentLifecycleCallbacks() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val INSTANCE: FragmentLifecycleSubscriber by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { FragmentLifecycleSubscriber() }
        
        private val TAG: String = FragmentLifecycleSubscriber::class.java.simpleName
    }
    
    
    /** ****************************** Override ****************************** */
    
    /**
     * 对应 Fragment 的 onAttach(context: Context)
     *
     * @param fm
     * @param f
     * @param context
     * @return
     */
    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentAttached")
        super.onFragmentAttached(fm, f, context)
    }
    
    /**
     * 对应 Fragment 的 onCreate(savedInstanceState: Bundle?)
     *
     * @param fm
     * @param f
     * @param savedInstanceState
     * @return
     */
    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentCreated")
        super.onFragmentCreated(fm, f, savedInstanceState)
    }
    
    /**
     * 对应 Fragment 的 onViewCreated(view: View, savedInstanceState: Bundle?)
     *
     * @param fm
     * @param f
     * @param v
     * @param savedInstanceState
     * @return
     */
    override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentViewCreated")
        super.onFragmentViewCreated(fm, f, v, savedInstanceState)
    }
    
    /**
     * 对应 Fragment 的 onActivityCreated(savedInstanceState: Bundle?)
     *
     * @param fm
     * @param f
     * @param savedInstanceState
     * @return
     */
    override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentActivityCreated")
        super.onFragmentActivityCreated(fm, f, savedInstanceState)
    }
    
    /**
     * 对应 Fragment 的 onStart()
     *
     * @param fm
     * @param f
     * @return
     */
    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentStarted")
        super.onFragmentStarted(fm, f)
    }
    
    /**
     * 对应 Fragment 的 onResume()
     *
     * @param fm
     * @param f
     * @return
     */
    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentResumed")
        super.onFragmentResumed(fm, f)
    }
    
    /**
     * 对应 Fragment 的 onPause()
     *
     * @param fm
     * @param f
     * @return
     */
    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentPaused")
        super.onFragmentPaused(fm, f)
    }
    
    /**
     * 对应 Fragment 的 onStop()
     *
     * @param fm
     * @param f
     * @return
     */
    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentStopped")
        super.onFragmentStopped(fm, f)
    }
    
    /**
     * 对应 Fragment 的 onSaveInstanceState(outState: Bundle)
     *
     * @param fm
     * @param f
     * @param outState
     * @return
     */
    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentSaveInstanceState")
        super.onFragmentSaveInstanceState(fm, f, outState)
    }
    
    /**
     * 对应 Fragment 的 onDestroyView()
     *
     * @param fm
     * @param f
     * @return
     */
    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentViewDestroyed")
        super.onFragmentViewDestroyed(fm, f)
    }
    
    /**
     * 对应 Fragment 的 onDestroy()
     *
     * @param fm
     * @param f
     * @return
     */
    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentDestroyed")
        super.onFragmentDestroyed(fm, f)
    }
    
    /**
     * 对应 Fragment 的 onDetach()
     *
     * @param fm
     * @param f
     * @return
     */
    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        logD(TAG, "${f::class.java.simpleName} > onFragmentDetached")
        super.onFragmentDetached(fm, f)
    }
}
