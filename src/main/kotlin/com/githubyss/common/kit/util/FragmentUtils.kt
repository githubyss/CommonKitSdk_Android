package com.githubyss.common.kit.util

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
fun switchFragmentByAddHideShow(fragment: Fragment?, currentFragment: Any?, fragmentTag: String?, fragmentManager: FragmentManager?, @IdRes containerId: Int, bundle: Bundle? = null, addToBackStack: Boolean = true, allowingStateLoss: Boolean = true) {
    fragment ?: return
    fragmentManager ?: return

    val currentFragmentActual: Fragment? = when (currentFragment) {
        is Fragment -> currentFragment
        is String -> fragmentManager.findFragmentByTag(currentFragment)
        else -> null
    }

    fragment.arguments = bundle
    fragmentManager.beginTransaction().apply {
        when {
            currentFragmentActual == null -> {
                add(containerId, fragment, fragmentTag)
            }
            currentFragmentActual != fragment -> {
                hide(currentFragmentActual)
                when {
                    fragment.isAdded -> show(fragment)
                    else -> add(containerId, fragment, fragmentTag)
                }
            }
        }
        if (addToBackStack) addToBackStack(null)
        if (allowingStateLoss) commitAllowingStateLoss()
        else commit()
    }
}

/**
 * replace 操作会把这个 containerId 中所有 fragments 清空删除！！！然后再把指定的 fragment 添加进去！
 * 造成了在切换到以前的 fragment 时，就会重新实例化 fragment。
 * 重新加载一遍数据，这样非常消耗性能和用户的数据流量。
 */
fun replaceFragment(fragment: Fragment?, fragmentTag: String?, fragmentManager: FragmentManager?, @IdRes containerId: Int, bundle: Bundle? = null, addToBackStack: Boolean = true, allowingStateLoss: Boolean = true) {
    fragment ?: return
    fragmentManager ?: return

    fragment.arguments = bundle
    if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
        fragmentManager.beginTransaction().apply {
            replace(containerId, fragment, fragmentTag)
            if (addToBackStack) addToBackStack(null)
            if (allowingStateLoss) commitAllowingStateLoss()
            else commit()
        }
    }
}

/**  */
fun addFragment(fragment: Fragment?, fragmentTag: String?, fragmentManager: FragmentManager?, @IdRes containerId: Int, bundle: Bundle? = null, addToBackStack: Boolean = true, allowingStateLoss: Boolean = true) {
    fragment ?: return
    fragmentManager ?: return

    fragment.arguments = bundle
    if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
        fragmentManager.beginTransaction().apply {
            add(containerId, fragment, fragmentTag)
            if (addToBackStack) addToBackStack(null)
            if (allowingStateLoss) commitAllowingStateLoss()
            else commit()
        }
    }
}

/**  */
fun showFragment(fragment: Any?, fragmentManager: FragmentManager?, addToBackStack: Boolean = true, allowingStateLoss: Boolean = true) {
    fragmentManager ?: return

    val fragmentActual: Fragment? = when (fragment) {
        is Fragment -> fragment
        is String -> fragmentManager.findFragmentByTag(fragment)
        else -> null
    }
    fragmentActual ?: return

    if (fragmentManager.fragments.contains(fragmentActual)) {
        fragmentManager.beginTransaction().apply {
            show(fragmentActual)
            if (addToBackStack) addToBackStack(null)
            if (allowingStateLoss) commitAllowingStateLoss()
            else commit()
        }
    }
}

/**  */
fun hideFragment(fragment: Any?, fragmentManager: FragmentManager?, addToBackStack: Boolean = true, allowingStateLoss: Boolean = true) {
    fragmentManager ?: return

    val fragmentActual: Fragment? = when (fragment) {
        is Fragment -> fragment
        is String -> fragmentManager.findFragmentByTag(fragment)
        else -> null
    }
    fragmentActual ?: return

    if (fragmentManager.fragments.contains(fragmentActual)) {
        fragmentManager.beginTransaction().apply {
            hide(fragmentActual)
            if (addToBackStack) addToBackStack(null)
            if (allowingStateLoss) commitAllowingStateLoss()
            else commit()
        }
    }
}

/**  */
fun removeFragment(fragment: Any?, fragmentManager: FragmentManager?, addToBackStack: Boolean = true, allowingStateLoss: Boolean = true) {
    fragmentManager ?: return

    val fragmentActual: Fragment? = when (fragment) {
        is Fragment -> fragment
        is String -> fragmentManager.findFragmentByTag(fragment)
        else -> null
    }
    fragmentActual ?: return

    if (fragmentManager.fragments.contains(fragmentActual)) {
        fragmentManager.beginTransaction().apply {
            remove(fragmentActual)
            if (addToBackStack) addToBackStack(null)
            if (allowingStateLoss) commitAllowingStateLoss()
            else commit()
        }
    }
}
