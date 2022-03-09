package com.githubyss.mobile.common.kit.util

import android.Manifest.permission
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Debug
import android.os.PowerManager
import android.provider.Settings
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import java.io.File
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException


/**
 * DeviceUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:00:14
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "DeviceUtils"

private val LOCATIONS = arrayOf("/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/", "/system/sbin/", "/usr/bin/", "/vendor/bin/")


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/**
 * Return the version name of device's system.
 *
 * @return the version name of device's system
 */
fun getSDKVersionName(): String {
    return Build.VERSION.RELEASE
}

/**
 * Return version code of device's system.
 *
 * @return version code of device's system
 */
fun getSDKVersionCode(): Int {
    return Build.VERSION.SDK_INT
}

/**
 * Return the manufacturer of the product/hardware.
 *
 * e.g. Xiaomi
 *
 * @return the manufacturer of the product/hardware
 */
fun getManufacturer(): String {
    return Build.MANUFACTURER
}

/**
 * Return the android id of device.
 *
 * @return the android id of device
 */
@SuppressLint("HardwareIds")
fun getAndroidID(context: Context? = ComkitApplicationConfig.getApp()): String {
    context ?: return ""

    return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) ?: ""
}

/**
 * Return the MAC address.
 *
 * Must hold `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />`,
 * `<uses-permission android:name="android.permission.INTERNET" />`
 *
 * @return the MAC address
 */
@RequiresPermission(allOf = [permission.ACCESS_WIFI_STATE, permission.INTERNET])
fun getMacAddress(): String {
    return getMacAddress(null)
}

/**
 * Return the MAC address.
 *
 * Must hold `<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />`,
 * `<uses-permission android:name="android.permission.INTERNET" />`
 *
 * @return the MAC address
 */
@RequiresPermission(allOf = [permission.ACCESS_WIFI_STATE, permission.INTERNET])
fun getMacAddress(vararg excepts: String?): String {
    var macAddress: String = getMacAddressByNetworkInterface()
    if (isAddressNotInExcepts(macAddress, *excepts)) return macAddress

    macAddress = getMacAddressByInetAddress()
    if (isAddressNotInExcepts(macAddress, *excepts)) return macAddress

    macAddress = getMacAddressByWifiInfo()
    if (isAddressNotInExcepts(macAddress, *excepts)) return macAddress

    macAddress = getMacAddressByFile()
    return if (isAddressNotInExcepts(macAddress, *excepts)) macAddress else ""
}

@SuppressLint("MissingPermission", "HardwareIds")
private fun getMacAddressByWifiInfo(context: Context? = ComkitApplicationConfig.getApp()): String {
    context ?: return ""

    try {
        val wifi = getWifiManager(context)
        if (wifi != null) {
            val info = wifi.connectionInfo
            if (info != null) return info.macAddress
        }
    }
    catch (e: Exception) {
        logE(TAG, t = e)
    }
    return "02:00:00:00:00:00"
}

private fun getMacAddressByNetworkInterface(): String {
    try {
        val nis = NetworkInterface.getNetworkInterfaces()
        while (nis.hasMoreElements()) {
            val ni = nis.nextElement()
            if (ni == null || !ni.name.equals("wlan0", ignoreCase = true)) continue
            val macBytes = ni.hardwareAddress
            if (macBytes != null && macBytes.isNotEmpty()) {
                val sb = StringBuilder()
                for (b in macBytes) {
                    sb.append(String.format("%02x:", b))
                }
                return sb.substring(0, sb.length - 1)
            }
        }
    }
    catch (e: Exception) {
        logE(TAG, t = e)
    }
    return "02:00:00:00:00:00"
}

private fun getMacAddressByInetAddress(): String {
    try {
        val inetAddress: InetAddress? = getInetAddress()
        if (inetAddress != null) {
            val ni = NetworkInterface.getByInetAddress(inetAddress)
            if (ni != null) {
                val macBytes = ni.hardwareAddress
                if (macBytes != null && macBytes.isNotEmpty()) {
                    val sb = StringBuilder()
                    for (b in macBytes) {
                        sb.append(String.format("%02x:", b))
                    }
                    return sb.substring(0, sb.length - 1)
                }
            }
        }
    }
    catch (e: Exception) {
        logE(TAG, t = e)
    }
    return "02:00:00:00:00:00"
}

private fun getInetAddress(): InetAddress? {
    try {
        val nis = NetworkInterface.getNetworkInterfaces()
        while (nis.hasMoreElements()) {
            val ni = nis.nextElement()
            // To prevent phone of xiaomi return "10.0.2.15"
            if (!ni.isUp) continue
            val addresses = ni.inetAddresses
            while (addresses.hasMoreElements()) {
                val inetAddress = addresses.nextElement()
                if (!inetAddress.isLoopbackAddress) {
                    val hostAddress = inetAddress.hostAddress
                    if (hostAddress.indexOf(':') < 0) return inetAddress
                }
            }
        }
    }
    catch (e: SocketException) {
        logE(TAG, t = e)
    }
    return null
}

private fun getMacAddressByFile(): String {
    var result: CommandResult? = execCmd(command = "getprop wifi.interface", isRooted = false)
    if (result?.result == 0) {
        val name: String? = result.successMsg
        if (name != null) {
            result = execCmd("cat /sys/class/net/$name/address", false)
            if (result?.result == 0) {
                val address: String? = result.successMsg
                if (address != null && address.isNotEmpty()) {
                    return address
                }
            }
        }
    }
    return "02:00:00:00:00:00"
}

/**
 * Return the model of device.
 *
 * e.g. MI2SC
 *
 * @return the model of device
 */
fun getModel(): String {
    var model = Build.MODEL
    model = model?.trim { it <= ' ' }?.replace("\\s*".toRegex(), "") ?: ""
    return model
}

/**
 * Return an ordered list of ABIs supported by this device. The most preferred ABI is the first
 * element in the list.
 *
 * @return an ordered list of ABIs supported by this device
 */
fun getABIs(): Array<String?>? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Build.SUPPORTED_ABIS
    }
    else {
        if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
            arrayOf(Build.CPU_ABI, Build.CPU_ABI2)
        }
        else arrayOf(Build.CPU_ABI)
    }
}

/** ******************** Checker ******************** */

/**
 * Return whether device is rooted.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isDeviceRooted(): Boolean {
    val su = "su"
    for (location in LOCATIONS) {
        if (File(location + su).exists()) {
            return true
        }
    }
    return false
}

/**
 * Return whether ADB is enabled.
 *
 * @return `true`: yes<br></br>`false`: no
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
fun isAdbEnabled(context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    context ?: return false

    return Settings.Secure.getInt(context.contentResolver, Settings.Global.ADB_ENABLED, 0) > 0
}

/**
 * Return whether device is tablet.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isTablet(context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    context ?: return false

    return (getResources(context)?.configuration?.screenLayout ?: return false) and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
}

/**
 * Return whether device is emulator.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isEmulator(context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    context ?: return false

    val checkProperty = Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase()
        .contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.SERIAL.equals("unknown", ignoreCase = true) || Build.SERIAL.equals("android", ignoreCase = true) || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic") || "google_sdk" == Build.PRODUCT
    if (checkProperty) return true

    val checkDebuggerConnected = Debug.isDebuggerConnected()
    if (checkDebuggerConnected) return true

    var operatorName = ""
    val tm = getTelephonyManager(context)
    if (tm != null) {
        val name = tm.networkOperatorName
        if (name != null) {
            operatorName = name
        }
    }
    val checkOperatorName = operatorName.toLowerCase() == "android"
    if (checkOperatorName) return true

    val url = "tel:" + "123456"
    val intent = Intent()
    intent.data = Uri.parse(url)
    intent.action = Intent.ACTION_DIAL
    return intent.resolveActivity(context.packageManager) != null
}

private fun isAddressNotInExcepts(address: String?, vararg excepts: String?): Boolean {
    if (excepts == null || excepts.isEmpty()) {
        return "02:00:00:00:00:00" != address
    }
    for (filter in excepts) {
        if (address == filter) {
            return false
        }
    }
    return true
}

/** ******************** Processor ******************** */

/**
 * Shutdown the device
 *
 * Requires root permission
 * or hold `android:sharedUserId="android.uid.system"`,
 * `<uses-permission android:name="android.permission.SHUTDOWN/>`
 * in manifest.
 */
fun shutdown(context: Context? = ComkitApplicationConfig.getApp()) {
    context ?: return

    execCmd("reboot -p", true)
    val intent = Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN")
    intent.putExtra("android.intent.extra.KEY_CONFIRM", false)
    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}

/**
 * Reboot the device.
 *
 * Requires root permission
 * or hold `android:sharedUserId="android.uid.system"` in manifest.
 */
fun reboot(context: Context? = ComkitApplicationConfig.getApp()) {
    context ?: return

    execCmd("reboot", true)
    val intent = Intent(Intent.ACTION_REBOOT)
    intent.putExtra("nowait", 1)
    intent.putExtra("interval", 1)
    intent.putExtra("window", 0)
    context.sendBroadcast(intent)
}

/**
 * Reboot the device.
 *
 * Requires root permission
 * or hold `android:sharedUserId="android.uid.system"`,
 * `<uses-permission android:name="android.permission.REBOOT" />`
 *
 * @param reason code to pass to the kernel (e.g., "recovery") to
 * request special boot modes, or null.
 */
fun reboot(reason: String?, context: Context? = ComkitApplicationConfig.getApp()) {
    context ?: return

    val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    pm.reboot(reason)
}

/**
 * Reboot the device to recovery.
 *
 * Requires root permission.
 */
fun reboot2Recovery() {
    execCmd("reboot recovery", true)
}

/**
 * Reboot the device to bootloader.
 *
 * Requires root permission.
 */
fun reboot2Bootloader() {
    execCmd("reboot bootloader", true)
}
