package com.githubyss.mobile.common.kit.lifecycle

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.githubyss.mobile.common.kit.util.LogcatUtils


/**
 * FragmentLifecycleSubscriber
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 10:14:08
 */
open class FragmentLifecycleSubscriber private constructor() : FragmentManager.FragmentLifecycleCallbacks() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val INSTANCE: FragmentLifecycleSubscriber by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { FragmentLifecycleSubscriber() }
        
        private val TAG = FragmentLifecycleSubscriber::class.simpleName ?: "simpleName is null"
    }
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentAttached")
        super.onFragmentAttached(fm, f, context)
    }
    
    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentCreated")
        super.onFragmentCreated(fm, f, savedInstanceState)
    }
    
    override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentViewCreated")
        super.onFragmentViewCreated(fm, f, v, savedInstanceState)
    }
    
    override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentActivityCreated")
        super.onFragmentActivityCreated(fm, f, savedInstanceState)
    }
    
    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentStarted")
        super.onFragmentStarted(fm, f)
    }
    
    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentResumed")
        super.onFragmentResumed(fm, f)
    }
    
    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentPaused")
        super.onFragmentPaused(fm, f)
    }
    
    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentStopped")
        super.onFragmentStopped(fm, f)
    }
    
    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentViewDestroyed")
        super.onFragmentViewDestroyed(fm, f)
    }
    
    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentSaveInstanceState")
        super.onFragmentSaveInstanceState(fm, f, outState)
    }
    
    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentDestroyed")
        super.onFragmentDestroyed(fm, f)
    }
    
    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        LogcatUtils.d(TAG, "${f::class.java.simpleName} > onFragmentDetached")
        super.onFragmentDetached(fm, f)
    }
}
