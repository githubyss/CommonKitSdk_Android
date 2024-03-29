package com.githubyss.common.kit.util

import android.content.Context
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission
import com.githubyss.common.base.application.BaseApplicationHolder
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.net.UnknownHostException


/**
 * NetworkUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:14:56
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "NetworkUtils"

/** China Mobile Communications Corporation Proxy */
private const val CMCC_PROXY = "10.0.0.172"

/** China Unicom Communications Corporation Proxy */
private const val CUCC_PROXY = "10.0.0.172"

/** China Telecom Communications Corporation Proxy */
private const val CTCC_PROXY = "10.0.0.200"

enum class NetworkType {
    NETWORK_ETHERNET,
    NETWORK_WIFI,
    NETWORK_4G,
    NETWORK_3G,
    NETWORK_2G,
    NETWORK_UNKNOWN,
    NETWORK_NO
}


/** ****************************** Functions ****************************** */

@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun getActiveNetworkInfo(context: Context? = BaseApplicationHolder.getApp()): NetworkInfo? {
    context ?: return null

    val connectivityManager = getConnectivityManager(context) ?: return null
    return connectivityManager.activeNetworkInfo
}

fun getCellphoneIpAddress(useIpv4: Boolean = true): String {
    try {
        val networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces()
        for (networkInterface in networkInterfaceEnumeration) {
            val inetAddressEnumeration = networkInterface.inetAddresses
            for (inetAddress in inetAddressEnumeration) {
                if (!inetAddress.isLoopbackAddress) {
                    val hostAddress = inetAddress.hostAddress
                    val isIpv4 = hostAddress.indexOf(':') < 0
                    if (useIpv4) {
                        if (isIpv4) return hostAddress
                    }
                    else {
                        if (!isIpv4) {
                            val index = hostAddress.indexOf('%')
                            return if (index < 0) hostAddress.toUpperCase() else hostAddress.substring(0, index).toUpperCase()
                        }
                    }
                }
            }
        }
        return ""
    }
    catch (e: SocketException) {
        logE(TAG, e.toString())
        return ""
    }
}

fun getDomainAddress(domain: String?): String {
    domain ?: return ""

    return try {
        InetAddress.getByName(domain).hostAddress
    }
    catch (e: UnknownHostException) {
        logE(TAG, e.toString())
        ""
    }
}

@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun getNetworkType(context: Context? = BaseApplicationHolder.getApp()): String {
    context ?: return ""

    val networkInfo = getActiveNetworkInfo(context) ?: return ""
    val typeName = networkInfo.typeName
    return when (typeName.toLowerCase()) {
        "mobile" -> networkInfo.extraInfo.toLowerCase()
        else -> typeName
    }
}

@RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
fun getApnProxy(context: Context? = BaseApplicationHolder.getApp()): String {
    context ?: return ""

    val networkInfo = getActiveNetworkInfo(context) ?: return ""
    val typeName = networkInfo.typeName
    return when (typeName.toLowerCase()) {
        "mobile" -> {
            when (networkInfo.extraInfo.toLowerCase()) {
                "cmwap" -> CMCC_PROXY
                "3gwap" -> CMCC_PROXY
                "uniwap" -> CMCC_PROXY
                "ctwap" -> CTCC_PROXY
                else -> ""
            }
        }

        else -> ""
    }
}
