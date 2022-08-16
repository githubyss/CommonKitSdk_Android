package com.githubyss.mobile.common.kit.util

import android.app.Activity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


/**
 * FragmentUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/03 10:12:08
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "FragmentUtils"


/** ****************************** Functions ****************************** */

/** ******************** Checker ******************** */

/**
 * Return whether the fragment is destroy.
 *
 * @param activity The activity.
 * @param fragment The fragment.
 * @return `true`: yes<br></br>`false`: no
 */
fun isFragmentDestroy(activity: Activity?, fragment: Any?): Boolean {
    activity ?: return true
    fragment ?: return true

    return when (fragment) {
        is Fragment -> !isFragmentAlive(activity, fragment)
        is android.app.Fragment -> !isFragmentAlive(activity, fragment)
        else -> true
    }
}

/**
 * Return whether the fragment is alive.
 *
 * @param activity The activity.
 * @param fragment The fragment.
 * @return `true`: yes<br></br>`false`: no
 */
fun isFragmentAlive(activity: Activity?, fragment: Any?): Boolean {
    activity ?: return false
    fragment ?: return false

    return when (fragment) {
        is Fragment -> isActivityAlive(activity) && !fragment.isDetached
        is android.app.Fragment -> isActivityAlive(activity) && !fragment.isDetached
        else -> false
    }
}

/** ******************** Operator ******************** */

/**
 * 正确的切换方式是 add，切换时 hide，add 另一个 fragment；再次切换时，只需 hide 当前，show 另一个。
 * 这样就能做到多个 fragment 切换不重新实例化。
 */
fun switchFragmentByAddHideShow(fragment: Fragment?, fragmentTag: String?, currentFragment: Any?, fragmentManager: FragmentManager?, @IdRes containerId: Int, addToBackStack: Boolean = true, bundle: Bundle? = null) {
    fragment ?: return
    fragmentManager ?: return

    val currentFragment: Fragment? = when (currentFragment) {
        is Fragment -> {
            currentFragment
        }
        is String -> {
            fragmentManager.findFragmentByTag(currentFragment)
        }
        else -> null
    }

    fragment.arguments = bundle
    val fragmentTransaction = fragmentManager.beginTransaction()
    if (currentFragment == null) {
        fragmentTransaction.add(containerId, fragment, fragmentTag)
    }
    else if (currentFragment != fragment) {
        fragmentTransaction.hide(currentFragment)
        if (fragment.isAdded) {
            fragmentTransaction.show(fragment)
        }
        else {
            fragmentTransaction.add(containerId, fragment, fragmentTag)
        }
    }
    if (addToBackStack) fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commitAllowingStateLoss()
}

/**
 * replace 操作会把这个 containerId 中所有 fragments 清空删除！！！然后再把指定的 fragment 添加进去！
 * 造成了在切换到以前的 fragment 时，就会重新实例化 fragment。
 * 重新加载一遍数据，这样非常消耗性能和用户的数据流量。
 */
fun replaceFragment(fragment: Fragment?, fragmentTag: String?, fragmentManager: FragmentManager?, @IdRes containerId: Int, addToBackStack: Boolean = true, bundle: Bundle? = null) {
    fragment ?: return
    fragmentManager ?: return

    fragment.arguments = bundle
    if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerId, fragment, fragmentTag)
        if (addToBackStack) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()
    }
}

/**  */
fun addFragment(fragment: Fragment?, fragmentTag: String?, fragmentManager: FragmentManager?, @IdRes containerId: Int, addToBackStack: Boolean = true, bundle: Bundle? = null) {
    fragment ?: return
    fragmentManager ?: return

    fragment.arguments = bundle
    if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(containerId, fragment, fragmentTag)
        if (addToBackStack) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()
    }
}

/**  */
fun showFragment(fragment: Any?, fragmentManager: FragmentManager?, addToBackStack: Boolean = true) {
    fragmentManager ?: return

    val fragment: Fragment? = when (fragment) {
        is Fragment -> {
            fragment
        }
        is String -> {
            fragmentManager.findFragmentByTag(fragment)
        }
        else -> null
    }

    fragment ?: return
    if (fragmentManager.fragments.contains(fragment)) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.show(fragment)
        if (addToBackStack) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()
    }
}

/**  */
fun hideFragment(fragment: Any?, fragmentManager: FragmentManager?, addToBackStack: Boolean = true) {
    fragmentManager ?: return

    val fragment: Fragment? = when (fragment) {
        is Fragment -> {
            fragment
        }
        is String -> {
            fragmentManager.findFragmentByTag(fragment)
        }
        else -> null
    }

    fragment ?: return
    if (fragmentManager.fragments.contains(fragment)) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.hide(fragment)
        if (addToBackStack) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()
    }
}

/**  */
fun removeFragment(fragment: Any?, fragmentManager: FragmentManager?, addToBackStack: Boolean = true) {
    fragmentManager ?: return

    val fragment: Fragment? = when (fragment) {
        is Fragment -> {
            fragment
        }
        is String -> {
            fragmentManager.findFragmentByTag(fragment)
        }
        else -> null
    }

    fragment ?: return
    if (fragmentManager.fragments.contains(fragment)) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.remove(fragment)
        if (addToBackStack) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()
    }
}
