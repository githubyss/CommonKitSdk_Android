package com.githubyss.mobile.common.kit.util

import android.app.Activity
import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.kit.lifecycle.FragmentLifecycleSubscriber


/**
 * FragmentUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 10:12:08
 */
object FragmentUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = FragmentUtils::class.simpleName ?: "simpleName is null"
    
    /** The fragment lifecycle callbacks impl. */
    var fragmentLifecycle: FragmentLifecycleSubscriber = FragmentLifecycleSubscriber.INSTANCE
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */

    /** ********** ********** Checker ********** ********** */

    /**
     * Return whether the fragment is destroy.
     *
     * @param activity The activity.
     * @param fragment The fragment.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFragmentDestroy(activity: Activity?, fragment: Fragment?): Boolean {
        return !isFragmentAlive(activity, fragment)
    }

    /**
     * Return whether the fragment is alive.
     *
     * @param activity The activity.
     * @param fragment The fragment.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isFragmentAlive(activity: Activity?, fragment: Fragment?): Boolean {
        activity ?: return false
        fragment ?: return false

        return ActivityUtils.isActivityAlive(activity) && !fragment.isDetached
    }
}
