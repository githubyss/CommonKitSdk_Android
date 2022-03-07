package com.githubyss.mobile.common.kit.util

import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.util.TypedValue
import android.view.*
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.drawerlayout.widget.DrawerLayout
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.enumeration.VersionCode


/**
 * BarUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/08 17:41:28
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "BarUtils"

private const val TAG_STATUS_BAR = "TAG_STATUS_BAR"
private const val TAG_OFFSET = "TAG_OFFSET"
private const val KEY_OFFSET = -123


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/** ********** StatusBar ********** */

/**
 * Return the status bar's height.
 *
 * @param context The context.
 * @return the status bar's height
 */
fun getStatusBarHeight(context: Context? = ComkitApplicationConfig.getApp()): Int {
    val resources = getResources(context) ?: return -1
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else -1
}

/** ********** ActionBar ********** */

/**
 * Return the action bar's height.
 *
 * @return the action bar's height
 */
fun getActionBarHeight(context: Context? = ComkitApplicationConfig.getApp()): Int {
    val resources = getResources(context) ?: return -1
    val tv = TypedValue()
    return if (context?.theme?.resolveAttribute(R.attr.actionBarSize, tv, true) ?: return -1) TypedValue.complexToDimensionPixelSize(tv.data, getDisplayMetrics(context)) else -1
}

/** ********** NavigationBar ********** */

/**
 * Return the navigation bar's height.
 *
 * @return the navigation bar's height
 */
fun getNavBarHeight(context: Context? = ComkitApplicationConfig.getApp()): Int {
    val resources = getResources(context) ?: return -1
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else -1
}

/**
 * Return the color of navigation bar.
 *
 * @param activity The activity.
 * @return the color of navigation bar
 */
@RequiresApi(VersionCode.LOLLIPOP)
fun getNavBarColor(activity: Activity?): Int {
    activity ?: return -1

    return getNavBarColor(activity.window)
}

/**
 * Return the color of navigation bar.
 *
 * @param window The window.
 * @return the color of navigation bar
 */
@RequiresApi(VersionCode.LOLLIPOP)
fun getNavBarColor(window: Window?): Int {
    window ?: return -1

    return window.navigationBarColor
}

/** ******************** Checker ******************** */

/** ********** StatusBar ********** */

/**
 * Return whether the status bar is visible.
 *
 * @param activity The activity.
 * @return `true`: yes<br></br>`false`: no
 */
fun isStatusBarVisible(activity: Activity?): Boolean {
    activity ?: return false

    return isStatusBarVisible(activity.window)
}

/**
 * Return whether the status bar is visible.
 *
 * @param window The window.
 * @return `true`: yes<br></br>`false`: no
 */
fun isStatusBarVisible(window: Window?): Boolean {
    window ?: return false

    val flags = window.attributes?.flags ?: return false
    return flags and WindowManager.LayoutParams.FLAG_FULLSCREEN == 0
}

/**
 * Is the status bar light mode.
 *
 * @param activity The activity.
 * @return `true`: yes<br></br>`false`: no
 */
fun isStatusBarLightMode(activity: Activity?): Boolean {
    activity ?: return false

    return isStatusBarLightMode(activity.window)
}

/**
 * Is the status bar light mode.
 *
 * @param window The window.
 * @return `true`: yes<br></br>`false`: no
 */
fun isStatusBarLightMode(window: Window?): Boolean {
    window ?: return false

    if (Build.VERSION.SDK_INT >= VersionCode.M) {
        val decorView = window.decorView
        val vis = decorView.systemUiVisibility
        return vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR != 0
    }
    return false
}

/** ********** NavigationBar ********** */

/**
 * Return whether the navigation bar visible.
 *
 * Call it in onWindowFocusChanged will get right result.
 *
 * @param activity The activity.
 * @return `true`: yes<br></br>`false`: no
 */
fun isNavBarVisible(activity: Activity?): Boolean {
    activity ?: return false

    return isNavBarVisible(activity.window)
}

/**
 * Return whether the navigation bar visible.
 *
 * Call it in onWindowFocusChanged will get right result.
 *
 * @param window The window.
 * @return `true`: yes<br></br>`false`: no
 */
fun isNavBarVisible(window: Window?, context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    window ?: return false
    context ?: return false

    var isVisible = false
    val decorView = window.decorView as ViewGroup
    var i = 0
    val count = decorView.childCount
    while (i < count) {
        val child = decorView.getChildAt(i)
        val id = child.id
        if (id != View.NO_ID) {
            val resourceEntryName: String? = context.resources?.getResourceEntryName(id)
            if ("navigationBarBackground" == resourceEntryName && child.visibility == View.VISIBLE) {
                isVisible = true
                break
            }
        }
        i++
    }
    if (isVisible) {
        val visibility = decorView.systemUiVisibility
        isVisible = visibility and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION == 0
    }
    return isVisible
}

/**
 * Return whether the navigation bar visible.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isSupportNavBar(context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
        val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager? ?: return false
        val display = wm.defaultDisplay
        val size = Point()
        val realSize = Point()
        display.getSize(size)
        display.getRealSize(realSize)
        return realSize.y != size.y || realSize.x != size.x
    }
    val menu = ViewConfiguration.get(context).hasPermanentMenuKey()
    val back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
    return !menu && !back
}

/** ******************** Processor ******************** */

/** ********** StatusBar ********** */

/**
 * Set the status bar's visibility.
 *
 * @param activity  The activity.
 * @param isVisible True to set status bar visible, false otherwise.
 */
fun setStatusBarVisibility(activity: Activity?, isVisible: Boolean) {
    setStatusBarVisibility(activity?.window, isVisible)
}

/**
 * Set the status bar's visibility.
 *
 * @param window    The window.
 * @param isVisible True to set status bar visible, false otherwise.
 */
fun setStatusBarVisibility(window: Window?, isVisible: Boolean) {
    if (isVisible) {
        window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        showStatusBarView(window)
        addMarginTopEqualStatusBarHeight(window)
    }
    else {
        window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        hideStatusBarView(window)
        subtractMarginTopEqualStatusBarHeight(window)
    }
}

/**
 * Set the status bar's light mode.
 *
 * @param activity    The activity.
 * @param isLightMode True to set status bar light mode, false otherwise.
 */
fun setStatusBarLightMode(activity: Activity?, isLightMode: Boolean) {
    setStatusBarLightMode(activity?.window, isLightMode)
}

/**
 * Set the status bar's light mode.
 *
 * @param window      The window.
 * @param isLightMode True to set status bar light mode, false otherwise.
 */
fun setStatusBarLightMode(window: Window?, isLightMode: Boolean) {
    if (Build.VERSION.SDK_INT >= VersionCode.M) {
        val decorView = window?.decorView
        if (decorView != null) {
            var vis = decorView.systemUiVisibility
            vis = if (isLightMode) {
                vis or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            else {
                vis and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
            decorView.systemUiVisibility = vis
        }
    }
}

/**
 * Add the top margin size equals status bar's height for view.
 *
 * @param view The view.
 */
fun addMarginTopEqualStatusBarHeight(view: View?) {
    if (view == null) return
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    view.tag = TAG_OFFSET
    val haveSetOffset = view.getTag(KEY_OFFSET)
    if (haveSetOffset != null && haveSetOffset as Boolean) return
    val layoutParams = view.layoutParams as MarginLayoutParams
    layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin + getStatusBarHeight(), layoutParams.rightMargin, layoutParams.bottomMargin)
    view.setTag(KEY_OFFSET, true)
}

/**
 * Subtract the top margin size equals status bar's height for view.
 *
 * @param view The view.
 */
fun subtractMarginTopEqualStatusBarHeight(view: View?) {
    if (view == null) return
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    val haveSetOffset = view.getTag(KEY_OFFSET)
    if (haveSetOffset == null || !(haveSetOffset as Boolean)) return
    val layoutParams = view.layoutParams as MarginLayoutParams
    layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin - getStatusBarHeight(), layoutParams.rightMargin, layoutParams.bottomMargin)
    view.setTag(KEY_OFFSET, false)
}

private fun addMarginTopEqualStatusBarHeight(window: Window?) {
    if (window == null) return
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    val withTag = window.decorView.findViewWithTag<View>(TAG_OFFSET) ?: return
    addMarginTopEqualStatusBarHeight(withTag)
}

private fun subtractMarginTopEqualStatusBarHeight(window: Window?) {
    if (window == null) return
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    val withTag = window.decorView.findViewWithTag<View>(TAG_OFFSET) ?: return
    subtractMarginTopEqualStatusBarHeight(withTag)
}

/**
 * Set the status bar's color.
 *
 * @param activity The activity.
 * @param color    The status bar's color.
 * @param isDecor  True to add fake status bar in DecorView,
 * false to add fake status bar in ContentView.
 */
fun setStatusBarColor(activity: Activity?, @ColorInt color: Int, isDecor: Boolean = false): View? {
    if (activity == null) return null
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return null
    transparentStatusBar(activity)
    return applyStatusBarColor(activity, color, isDecor)
}

/**
 * Set the status bar's color.
 *
 * @param fakeStatusBar The fake status bar view.
 * @param color         The status bar's color.
 */
fun setStatusBarColor(fakeStatusBar: View?, @ColorInt color: Int) {
    if (fakeStatusBar == null) return
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    val activity: Activity = ActivityUtils.getActivityByView(fakeStatusBar) ?: return
    transparentStatusBar(activity)
    fakeStatusBar.visibility = View.VISIBLE
    val layoutParams = fakeStatusBar.layoutParams
    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
    layoutParams.height = getStatusBarHeight()
    fakeStatusBar.setBackgroundColor(color)
}

/**
 * Set the custom status bar.
 *
 * @param fakeStatusBar The fake status bar view.
 */
fun setStatusBarCustom(fakeStatusBar: View?) {
    if (fakeStatusBar == null) return
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    val activity: Activity = ActivityUtils.getActivityByView(fakeStatusBar) ?: return
    transparentStatusBar(activity)
    fakeStatusBar.visibility = View.VISIBLE
    var layoutParams = fakeStatusBar.layoutParams
    if (layoutParams == null) {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
        fakeStatusBar.layoutParams = layoutParams
    }
    else {
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = getStatusBarHeight()
    }
}

/**
 * Set the status bar's color for DrawerLayout.
 *
 * DrawLayout must add `android:fitsSystemWindows="true"`
 *
 * @param drawer        The DrawLayout.
 * @param fakeStatusBar The fake status bar view.
 * @param color         The status bar's color.
 * @param isTop         True to set DrawerLayout at the top layer, false otherwise.
 */
fun setStatusBarColor4Drawer(drawer: DrawerLayout?, fakeStatusBar: View?, @ColorInt color: Int, isTop: Boolean = false) {
    if (drawer == null || fakeStatusBar == null) return
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    val activity: Activity = ActivityUtils.getActivityByView(fakeStatusBar) ?: return
    transparentStatusBar(activity)
    drawer.fitsSystemWindows = false
    setStatusBarColor(fakeStatusBar, color)
    var i = 0
    val count: Int = drawer.childCount
    while (i < count) {
        drawer.getChildAt(i).fitsSystemWindows = false
        i++
    }
    if (isTop) {
        hideStatusBarView(activity)
    }
    else {
        setStatusBarColor(activity, color, false)
    }
}

private fun applyStatusBarColor(activity: Activity?, color: Int, isDecor: Boolean): View? {
    if (activity == null) return null
    val parent = (if (isDecor) activity.window.decorView as ViewGroup else activity.findViewById(R.id.content) as ViewGroup)
    var fakeStatusBarView = parent.findViewWithTag<View>(TAG_STATUS_BAR)
    if (fakeStatusBarView != null) {
        if (fakeStatusBarView.visibility == View.GONE) {
            fakeStatusBarView.visibility = View.VISIBLE
        }
        fakeStatusBarView.setBackgroundColor(color)
    }
    else {
        fakeStatusBarView = createStatusBarView(activity, color)
        parent.addView(fakeStatusBarView)
    }
    return fakeStatusBarView
}

private fun hideStatusBarView(activity: Activity?) {
    hideStatusBarView(activity?.window)
}

private fun hideStatusBarView(window: Window?) {
    if (window == null) return
    val decorView = window.decorView as ViewGroup
    val fakeStatusBarView = decorView.findViewWithTag<View>(TAG_STATUS_BAR) ?: return
    fakeStatusBarView.visibility = View.GONE
}

private fun showStatusBarView(window: Window?) {
    if (window == null) return
    val decorView = window.decorView as ViewGroup
    val fakeStatusBarView = decorView.findViewWithTag<View>(TAG_STATUS_BAR) ?: return
    fakeStatusBarView.visibility = View.VISIBLE
}

private fun createStatusBarView(activity: Activity?, color: Int): View? {
    if (activity == null) return null
    val statusBarView = View(activity)
    statusBarView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
    statusBarView.setBackgroundColor(color)
    statusBarView.tag = TAG_STATUS_BAR
    return statusBarView
}

private fun transparentStatusBar(activity: Activity?) {
    if (activity == null) return
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    val window = activity.window
    if (Build.VERSION.SDK_INT >= VersionCode.LOLLIPOP) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        val option = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= VersionCode.M) {
            val vis = window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.decorView.systemUiVisibility = option or vis
        }
        else {
            window.decorView.systemUiVisibility = option
        }
        window.statusBarColor = Color.TRANSPARENT
    }
    else {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}

/** ********** NotificationBar ********** */

/**
 * Set the notification bar's visibility.
 *
 * Must hold `<uses-permission android:name="android.permission.EXPAND_STATUS_BAR" />`
 *
 * @param isVisible True to set notification bar visible, false otherwise.
 */
@RequiresPermission(permission.EXPAND_STATUS_BAR)
fun setNotificationBarVisibility(isVisible: Boolean) {
    val methodName: String = if (isVisible) {
        if (Build.VERSION.SDK_INT <= VersionCode.JELLY_BEAN) "expand" else "expandNotificationsPanel"
    }
    else {
        if (Build.VERSION.SDK_INT <= VersionCode.JELLY_BEAN) "collapse" else "collapsePanels"
    }
    invokePanels(methodName)
}

private fun invokePanels(methodName: String, context: Context? = ComkitApplicationConfig.getApp()) {
    try {
        @SuppressLint("WrongConstant")
        val service: Any? = context?.getSystemService("statusbar")

        @SuppressLint("PrivateApi")
        val statusBarManager = Class.forName("android.app.StatusBarManager")
        val expand = statusBarManager.getMethod(methodName)
        expand.invoke(service)
    }
    catch (e: Exception) {
        e.printStackTrace()
    }
}

/** ********** NavigationBar ********** */

/**
 * Set the navigation bar's visibility.
 *
 * @param activity  The activity.
 * @param isVisible True to set navigation bar visible, false otherwise.
 */
fun setNavBarVisibility(activity: Activity?, isVisible: Boolean) {
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    setNavBarVisibility(activity?.window, isVisible)
}

/**
 * Set the navigation bar's visibility.
 *
 * @param window    The window.
 * @param isVisible True to set navigation bar visible, false otherwise.
 */
fun setNavBarVisibility(window: Window?, isVisible: Boolean, context: Context? = ComkitApplicationConfig.getApp()) {
    if (window == null) return
    if (Build.VERSION.SDK_INT < VersionCode.KITKAT) return
    val decorView = window.decorView as ViewGroup
    var i = 0
    val count = decorView.childCount
    while (i < count) {
        val child = decorView.getChildAt(i)
        val id = child.id
        if (id != View.NO_ID) {
            val resourceEntryName: String? = context?.resources?.getResourceEntryName(id)
            if ("navigationBarBackground" == resourceEntryName) {
                child.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
            }
        }
        i++
    }
    val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    if (isVisible) {
        decorView.systemUiVisibility = decorView.systemUiVisibility and uiOptions.inv()
    }
    else {
        decorView.systemUiVisibility = decorView.systemUiVisibility or uiOptions
    }
}

/**
 * Set the navigation bar's color.
 *
 * @param activity The activity.
 * @param color    The navigation bar's color.
 */
@RequiresApi(VersionCode.LOLLIPOP)
fun setNavBarColor(activity: Activity?, @ColorInt color: Int) {
    setNavBarColor(activity?.window, color)
}

/**
 * Set the navigation bar's color.
 *
 * @param window The window.
 * @param color  The navigation bar's color.
 */
@RequiresApi(VersionCode.LOLLIPOP)
fun setNavBarColor(window: Window?, @ColorInt color: Int) {
    window?.navigationBarColor = color
}
