package com.githubyss.common.kit.util

import android.app.KeyguardManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.telephony.TelephonyManager
import android.view.WindowManager
import com.githubyss.common.base.application.BaseApplicationHolder


/**
 * SystemUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:16:09
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "SystemUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/** ********** Manager ********** */

/**
 * Get the window manager.
 *
 * @param context The context.
 * @return The window manager.
 */
fun windowManager(context: Context? = BaseApplicationHolder.getApp()): WindowManager? {
    context ?: return null

    return context.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
}

/**
 * Get the keyguard manager.
 *
 * @param context The context.
 * @return The keyguard manager.
 */
fun getKeyguardManager(context: Context? = BaseApplicationHolder.getApp()): KeyguardManager? {
    context ?: return null

    return context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager?
}

/**
 * Get the wifi manager.
 *
 * @param context The context.
 * @return The wifi manager.
 */
fun getWifiManager(context: Context? = BaseApplicationHolder.getApp()): WifiManager? {
    context ?: return null

    return context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager?
}

/**
 * Get the telephony manager.
 *
 * @param context The context.
 * @return The telephony manager.
 */
fun getTelephonyManager(context: Context? = BaseApplicationHolder.getApp()): TelephonyManager? {
    context ?: return null

    return context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
}

/**
 * Get the connectivity manager.
 *
 * @param context The context.
 * @return The connectivity manager.
 */
fun getConnectivityManager(context: Context? = BaseApplicationHolder.getApp()): ConnectivityManager? {
    context ?: return null

    return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
}
