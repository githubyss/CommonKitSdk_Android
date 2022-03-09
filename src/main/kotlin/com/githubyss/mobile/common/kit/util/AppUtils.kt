package com.githubyss.mobile.common.kit.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.AppOpsManager
import android.app.Application
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Process
import android.provider.Settings
import android.text.TextUtils
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.lifecycle.ActivityLifecycleSubscriber
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.reflect.InvocationTargetException
import java.util.*


/**
 * AppUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 11:24:12
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "AppUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

fun getApplicationByReflect(): Application {
    try {
        @SuppressLint("PrivateApi")
        val activityThread = Class.forName("android.app.ActivityThread")
        val thread = activityThread.getMethod("currentActivityThread")
            .invoke(null)
        val app = activityThread.getMethod("getApplication")
            .invoke(thread) ?: throw NullPointerException("u should init first")
        return app as Application
    }
    catch (e: NoSuchMethodException) {
        logE(TAG, t = e)
    }
    catch (e: IllegalAccessException) {
        logE(TAG, t = e)
    }
    catch (e: InvocationTargetException) {
        logE(TAG, t = e)
    }
    catch (e: ClassNotFoundException) {
        logE(TAG, t = e)
    }
    throw NullPointerException("u should init first")
}

/**
 * Return the application's icon.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return the application's icon
 */
fun getAppIcon(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): Drawable? {
    packageName ?: return null
    context ?: return null
    if (isSpace(packageName)) return null

    return try {
        val packageManager = context.packageManager ?: return null
        val packageInfo = packageManager.getPackageInfo(packageName, 0) ?: return null
        packageInfo.applicationInfo?.loadIcon(packageManager)
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        null
    }
}

/**
 * Return the application's package name.
 *
 * @param context The context.
 * @return the application's package name
 */
fun getAppPackageName(context: Context? = ComkitApplicationConfig.getApp()): String {
    return context?.packageName ?: ""
}

/**
 * Return the application's name.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return the application's name
 */
fun getAppName(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): String {
    packageName ?: return ""
    context ?: return ""
    if (isSpace(packageName)) return ""

    return try {
        val packageManager = context.packageManager ?: return ""
        val packageInfo = packageManager.getPackageInfo(packageName, 0) ?: return ""
        packageInfo.applicationInfo?.loadLabel(packageManager)
            ?.toString() ?: ""
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        ""
    }
}

/**
 * Return the application's path.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return the application's path
 */
fun getAppPath(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): String {
    packageName ?: return ""
    context ?: return ""
    if (isSpace(packageName)) return ""

    return try {
        val packageManager = context.packageManager ?: return ""
        val packageInfo = packageManager.getPackageInfo(packageName, 0) ?: return ""
        packageInfo.applicationInfo?.sourceDir ?: ""
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        ""
    }
}

/**
 * Return the application's version name.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return the application's version name
 */
fun getAppVersionName(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): String {
    packageName ?: return ""
    context ?: return ""
    if (isSpace(packageName)) return ""

    return try {
        val packageManager = context.packageManager ?: return ""
        val packageInfo = packageManager.getPackageInfo(packageName, 0) ?: return ""
        packageInfo.versionName ?: ""
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        ""
    }
}

/**
 * Return the application's version code.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return the application's version code
 */
fun getAppVersionCode(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): Int {
    packageName ?: return -1
    context ?: return -1
    if (isSpace(packageName)) return -1

    return try {
        val packageManager = context.packageManager ?: return -1
        val packageInfo = packageManager.getPackageInfo(packageName, 0) ?: return -1
        packageInfo.versionCode
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        -1
    }
}

/**
 * Return the application's signature.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return the application's signature
 */
fun getAppSignature(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): Array<Signature?>? {
    packageName ?: return null
    context ?: return null
    if (isSpace(packageName)) return null

    return try {
        val packageManager = context.packageManager ?: return null

        @SuppressLint("PackageManagerGetSignatures")
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES) ?: return null
        packageInfo.signatures
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        null
    }
}

/**
 * Return the application's signature for SHA1 value.
 *
 * @param packageName The name of the package.
 * @return the application's signature for SHA1 value
 */
fun getAppSignatureSHA1(packageName: String? = getAppPackageName()): String {
    return getAppSignatureHash(packageName, "SHA1")
}

/**
 * Return the application's signature for SHA256 value.
 *
 * @param packageName The name of the package.
 * @return the application's signature for SHA256 value
 */
fun getAppSignatureSHA256(packageName: String? = getAppPackageName()): String {
    return getAppSignatureHash(packageName, "SHA256")
}

/**
 * Return the application's signature for MD5 value.
 *
 * @param packageName The name of the package.
 * @return the application's signature for MD5 value
 */
fun getAppSignatureMD5(packageName: String? = getAppPackageName()): String {
    return getAppSignatureHash(packageName, "MD5")
}

private fun getAppSignatureHash(packageName: String?, algorithm: String?): String {
    packageName ?: return ""
    algorithm ?: return ""
    if (isSpace(packageName)) return ""

    val signature = getAppSignature(packageName) ?: return ""
    return if (signature.isEmpty()) ""
    else bytes2HexString(hashTemplate(signature[0]?.toByteArray(), algorithm))
        .replace("(?<=[0-9A-F]{2})[0-9A-F]{2}".toRegex(), ":$0")
}

/**
 * Return the application's user-ID.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return the application's signature for MD5 value
 */
fun getAppUid(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): Int {
    packageName ?: return -1
    context ?: return -1
    if (isSpace(packageName)) return -1

    return try {
        val applicationInfo = context.packageManager?.getApplicationInfo(packageName, 0) ?: return -1
        applicationInfo.uid
    }
    catch (e: Exception) {
        logE(TAG, t = e)
        -1
    }
}

/**
 * Return the application's information.
 *
 *  * name of package
 *  * icon
 *  * name
 *  * path of package
 *  * version name
 *  * version code
 *  * is system
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return the application's information
 */
fun getAppInfo(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): AppInfo? {
    packageName ?: return null
    context ?: return null
    if (isSpace(packageName)) return null

    return try {
        val packageManager = context.packageManager ?: return null
        getBean(packageManager, packageManager.getPackageInfo(packageName, 0))
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        null
    }
}

/**
 * Return the applications' information.
 *
 * @param context The context.
 * @return the applications' information
 */
fun getAppsInfo(context: Context? = ComkitApplicationConfig.getApp()): List<AppInfo>? {
    context ?: return null

    val list: MutableList<AppInfo> = ArrayList()
    val packageManager = context.packageManager ?: return list
    val installedPackages = packageManager.getInstalledPackages(0) ?: return list
    for (aPackage in installedPackages) {
        val appInfo: AppInfo = getBean(packageManager, aPackage) ?: continue
        list.add(appInfo)
    }
    return list
}

/**
 * Return the application's package information.
 *
 * @param apkFile The file of apk.
 * @param context The context.
 * @return the application's package information
 */
fun getApkInfo(apkFile: File?, context: Context? = ComkitApplicationConfig.getApp()): AppInfo? {
    apkFile ?: return null
    if (!apkFile.isFile || !apkFile.exists()) return null

    return getApkInfo(apkFile.absolutePath, context)
}

/**
 * Return the application's package information.
 *
 * @param apkFilePath The path of apk file.
 * @param context     The context.
 * @return the application's package information
 */
fun getApkInfo(apkFilePath: String?, context: Context? = ComkitApplicationConfig.getApp()): AppInfo? {
    apkFilePath ?: return null
    context ?: return null
    if (isSpace(apkFilePath)) return null

    val packageManager = context.packageManager ?: return null
    val packageInfo = packageManager.getPackageArchiveInfo(apkFilePath, 0) ?: return null
    val appInfo = packageInfo.applicationInfo
    appInfo?.sourceDir = apkFilePath
    appInfo?.publicSourceDir = apkFilePath
    return getBean(packageManager, packageInfo)
}

private fun getBean(packageManager: PackageManager?, packageInfo: PackageInfo?): AppInfo? {
    packageManager ?: return null
    packageInfo ?: return null

    val applicationInfo = packageInfo.applicationInfo
    val packageName = packageInfo.packageName
    val name = applicationInfo.loadLabel(packageManager)
        .toString()
    val icon = applicationInfo.loadIcon(packageManager)
    val packagePath = applicationInfo.sourceDir
    val versionName = packageInfo.versionName
    val versionCode = packageInfo.versionCode
    val isSystem = ApplicationInfo.FLAG_SYSTEM and applicationInfo.flags != 0
    return AppInfo(packageName, name, icon, packagePath, versionName, versionCode, isSystem)
}

fun getForegroundProcessName(application: Application? = ComkitApplicationConfig.getApp()): String {
    application ?: return ""

    val activityManager = application.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val processInfo = activityManager.runningAppProcesses ?: return ""
    if (processInfo.size == 0) return ""

    if (processInfo.size > 0) {
        for (aInfo in processInfo) {
            if (aInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return aInfo.processName
            }
        }
    }

    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
        val packageManager: PackageManager = application.packageManager
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        val list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        logI(TAG, list.toString())

        if (list.size <= 0) {
            logI(TAG, "getForegroundProcessName: noun of access to usage information.")
            return ""
        }

        try {
            // Access to usage information.
            val applicationInfo = packageManager.getApplicationInfo(application.packageName, 0)
            val appOpsManager = application.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager

            if (appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, applicationInfo.uid, applicationInfo.packageName) != AppOpsManager.MODE_ALLOWED) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                application.startActivity(intent)
            }

            if (appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, applicationInfo.uid, applicationInfo.packageName) != AppOpsManager.MODE_ALLOWED) {
                logI(TAG, "getForegroundProcessName: refuse to device usage stats.")
                return ""
            }

            val usageStatsManager = application.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager?
            var usageStatsList: List<UsageStats>? = null
            if (usageStatsManager != null) {
                val endTime = System.currentTimeMillis()
                val beginTime = endTime - 86400000 * 7
                usageStatsList = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, beginTime, endTime)
            }

            if (usageStatsList == null || usageStatsList.isEmpty()) return ""
            var recentStats: UsageStats? = null
            for (usageStats in usageStatsList) {
                if (recentStats == null || usageStats.lastTimeUsed > recentStats.lastTimeUsed) {
                    recentStats = usageStats
                }
            }
            return recentStats?.packageName ?: ""
        }
        catch (e: PackageManager.NameNotFoundException) {
            logE(TAG, t = e)
        }
    }

    return ""
}

fun getCurrentProcessName(application: Application? = ComkitApplicationConfig.getApp()): String {
    application ?: return ""

    var name = getCurrentProcessNameByFile()
    if (!TextUtils.isEmpty(name)) return name
    name = getCurrentProcessNameByAms(application)
    if (!TextUtils.isEmpty(name)) return name
    name = getCurrentProcessNameByReflect(application)
    return name
}

fun getCurrentProcessNameByFile(): String {
    return try {
        val file = File("/proc/" + Process.myPid() + "/" + "cmdline")
        val mBufferedReader = BufferedReader(FileReader(file))
        val processName = mBufferedReader.readLine()
            .trim { it <= ' ' }
        mBufferedReader.close()
        processName
    }
    catch (e: Exception) {
        logE(TAG, t = e)
        ""
    }
}

fun getCurrentProcessNameByAms(application: Application? = ComkitApplicationConfig.getApp()): String {
    application ?: return ""

    val activityManager = application.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val processInfo = activityManager.runningAppProcesses ?: return ""
    if (processInfo.size == 0) return ""

    val pid = Process.myPid()
    for (aInfo in processInfo) {
        if (aInfo.pid == pid) {
            if (aInfo.processName != null) {
                return aInfo.processName
            }
        }
    }
    return ""
}

fun getCurrentProcessNameByReflect(application: Application? = ComkitApplicationConfig.getApp()): String {
    application ?: return ""

    var processName = ""
    try {
        val app = application
        val loadedApkField = app.javaClass.getField("mLoadedApk")
        loadedApkField.isAccessible = true
        val loadedApk = loadedApkField[app]

        val activityThreadField = loadedApk.javaClass.getDeclaredField("mActivityThread")
        activityThreadField.isAccessible = true
        val activityThread = activityThreadField[loadedApk]

        val getProcessName = activityThread.javaClass.getDeclaredMethod("getProcessName")
        processName = getProcessName.invoke(activityThread) as String
    }
    catch (e: Exception) {
        logE(TAG, t = e)
    }
    return processName
}

/** ******************** Checker ******************** */

/**
 * Return whether the app is installed.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return `true`: yes<br></br>`false`: no
 */
fun isAppInstalled(packageName: String?, context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    packageName ?: return false
    context ?: return false
    if (isSpace(packageName)) return false

    val packageManager = context.packageManager
    return try {
        packageManager?.getApplicationInfo(packageName, 0) != null
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        false
    }
}

/**
 * Return whether the application with root permission.
 *
 * @return `true`: yes<br></br>`false`: no
 */
fun isAppRoot(): Boolean {
    val result: CommandResult? = execCmd("echo root", true)
    if (result?.result == 0) return true
    if (result?.errorMsg != null) {
        logD("AppUtils", "isAppRoot() called" + result.errorMsg)
    }
    return false
}

/**
 * Return whether it is a debug application.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return `true`: yes<br></br>`false`: no
 */
fun isAppDebug(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    packageName ?: return false
    context ?: return false
    if (isSpace(packageName)) return false

    return try {
        val packageManager = context.packageManager ?: return false
        val applicationInfo = packageManager.getApplicationInfo(packageName, 0) ?: return false
        applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        false
    }
}

/**
 * Return whether it is a system application.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return `true`: yes<br></br>`false`: no
 */
fun isAppSystem(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    packageName ?: return false
    context ?: return false
    if (isSpace(packageName)) return false

    return try {
        val packageManager = context.packageManager ?: return false
        val applicationInfo = packageManager.getApplicationInfo(packageName, 0) ?: return false
        applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        false
    }
}

/**
 * Return whether application is foreground.
 *
 * Target APIs greater than 21 must hold
 * `<uses-permission android:name="android.permission.PACKAGE_USAGE_STATS" />`
 *
 * @param packageName The name of the package.
 * @return `true`: yes<br></br>`false`: no
 */
fun isAppForeground(packageName: String? = getAppPackageName()): Boolean {
    packageName ?: return false

    return !isSpace(packageName) && packageName == getForegroundProcessName()
}

/**
 * Return whether application is running.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 * @return `true`: yes<br></br>`false`: no
 */
fun isAppRunning(packageName: String?, context: Context? = ComkitApplicationConfig.getApp()): Boolean {
    packageName ?: return false
    context ?: return false
    if (isSpace(packageName)) return false

    val uid: Int
    val packageManager = context.packageManager ?: return false
    uid = try {
        val applicationInfo = packageManager.getApplicationInfo(packageName, 0) ?: return false
        applicationInfo.uid
    }
    catch (e: PackageManager.NameNotFoundException) {
        logE(TAG, t = e)
        return false
    }

    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager? ?: return false
    val taskInfo = activityManager.getRunningTasks(Int.MAX_VALUE)
    if (taskInfo != null && taskInfo.size > 0) {
        for (aInfo in taskInfo) {
            if (packageName == aInfo.baseActivity?.packageName) {
                return true
            }
        }
    }

    val serviceInfo = activityManager.getRunningServices(Int.MAX_VALUE)
    if (serviceInfo != null && serviceInfo.size > 0) {
        for (aInfo in serviceInfo) {
            if (uid == aInfo.uid) {
                return true
            }
        }
    }

    return false
}


/** ******************** Processor ******************** */

/**
 * Register the status of application changed listener.
 *
 * @param obj      The object.
 * @param listener The status of application changed listener
 */
fun registerAppStatusChangedListener(obj: Any?, listener: ActivityLifecycleSubscriber.OnAppStatusChangedListener?) {
    ActivityLifecycleSubscriber.INSTANCE.addOnAppStatusChangedListener(obj, listener)
}

/**
 * Unregister the status of application changed listener.
 *
 * @param obj The object.
 */
fun unregisterAppStatusChangedListener(obj: Any?) {
    ActivityLifecycleSubscriber.INSTANCE.removeOnAppStatusChangedListener(obj)
}

/** ********** installApp ********** */

/**
 * Install the app.
 *
 * Target APIs greater than 25 must hold
 * `<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />`
 *
 * @param filePath The path of file.
 * @param context  The context.
 */
fun installApp(filePath: String?, context: Context? = ComkitApplicationConfig.getApp()) {
    installApp(getFileByPath(filePath), context)
}

/**
 * Install the app.
 *
 * Target APIs greater than 25 must hold
 * `<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />`
 *
 * @param file    The file.
 * @param context The context.
 */
fun installApp(file: File?, context: Context? = ComkitApplicationConfig.getApp()) {
    file ?: return
    context ?: return
    if (!isFileExists(file)) return

    context.startActivity(getInstallAppIntent(file, true))
}

/**
 * Install the app.
 *
 * Target APIs greater than 25 must hold
 * `<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />`
 *
 * @param filePath    The path of file.
 * @param requestCode If &gt;= 0, this code will be returned in
 * @param activity    The activity.
 * onActivityResult() when the activity exits.
 */
fun installApp(filePath: String?, requestCode: Int, activity: Activity?) {
    installApp(getFileByPath(filePath), requestCode, activity)
}

/**
 * Install the app.
 *
 * Target APIs greater than 25 must hold
 * `<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />`
 *
 * @param file        The file.
 * @param requestCode If &gt;= 0, this code will be returned in
 * @param activity    The activity.
 * onActivityResult() when the activity exits.
 */
fun installApp(file: File?, requestCode: Int, activity: Activity?) {
    file ?: return
    activity ?: return
    if (!isFileExists(file)) return

    activity.startActivityForResult(getInstallAppIntent(file), requestCode)
}

/** ********** installAppSilent ********** */

/**
 * Install the app silently.
 *
 * Without root permission must hold
 * `android:sharedUserId="android.uid.shell"` and
 * `<uses-permission android:name="android.permission.INSTALL_PACKAGES" />`
 *
 * @param filePath The path of file.
 * @param params   The params of installation(e.g.,`-r`, `-s`).
 * @param isRooted True to use root, false otherwise.
 * @return `true`: success<br></br>`false`: fail
 */
fun installAppSilent(filePath: String?, params: String? = null, isRooted: Boolean = isDeviceRooted()): Boolean {
    return installAppSilent(getFileByPath(filePath), params, isRooted)
}

/**
 * Install the app silently.
 *
 * Without root permission must hold
 * `android:sharedUserId="android.uid.shell"` and
 * `<uses-permission android:name="android.permission.INSTALL_PACKAGES" />`
 *
 * @param file     The file.
 * @param params   The params of installation(e.g.,`-r`, `-s`).
 * @param isRooted True to use root, false otherwise.
 * @return `true`: success<br></br>`false`: fail
 */
fun installAppSilent(file: File?, params: String? = null, isRooted: Boolean = isDeviceRooted()): Boolean {
    file ?: return false
    params ?: return false
    if (!isFileExists(file)) return false

    val filePath = '"'.toString() + file.absolutePath + '"'
    val command = "LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm install $params $filePath"
    val commandResult: CommandResult? = execCmd(command, isRooted)
    return if (commandResult?.successMsg != null && commandResult.successMsg.toLowerCase()
            .contains("success")) {
        true
    }
    else {
        logE("AppUtils", "installAppSilent successMsg: " + commandResult?.successMsg.toString() + ", errorMsg: " + commandResult?.errorMsg)
        false
    }
}

/** ********** uninstallApp ********** */

/**
 * Uninstall the app.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 */
fun uninstallApp(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()) {
    packageName ?: return
    context ?: return
    if (isSpace(packageName)) return

    context.startActivity(getUninstallAppIntent(packageName, true))
}

/**
 * Uninstall the app.
 *
 * @param packageName The name of the package.
 * @param requestCode If &gt;= 0, this code will be returned in
 * @param activity    The activity.
 * onActivityResult() when the activity exits.
 */
fun uninstallApp(packageName: String? = getAppPackageName(), requestCode: Int, activity: Activity?) {
    packageName ?: return
    activity ?: return
    if (isSpace(packageName)) return

    activity.startActivityForResult(getUninstallAppIntent(packageName), requestCode)
}

/** ********** uninstallAppSilent ********** */

/**
 * Uninstall the app silently.
 *
 * Without root permission must hold
 * `android:sharedUserId="android.uid.shell"` and
 * `<uses-permission android:name="android.permission.DELETE_PACKAGES" />`
 *
 * @param packageName The name of the package.
 * @param isKeepData  Is keep the data.
 * @param isRooted    True to use root, false otherwise.
 * @return `true`: success<br></br>`false`: fail
 */
fun uninstallAppSilent(packageName: String? = getAppPackageName(), isKeepData: Boolean = false, isRooted: Boolean = isDeviceRooted()): Boolean {
    packageName ?: return false
    if (isSpace(packageName)) return false

    val command = "LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm uninstall " + (if (isKeepData) "-k " else "") + packageName
    val commandResult: CommandResult? = execCmd(command, isRooted)
    return if (commandResult?.successMsg != null && commandResult.successMsg.toLowerCase()
            .contains("success")) {
        true
    }
    else {
        logE("AppUtils", "uninstallAppSilent successMsg: " + commandResult?.successMsg.toString() + ", errorMsg: " + commandResult?.errorMsg)
        false
    }
}

/** ********** launchApp ********** */

/**
 * Launch the application.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 */
fun launchApp(packageName: String?, context: Context? = ComkitApplicationConfig.getApp()) {
    packageName ?: return
    context ?: return
    if (isSpace(packageName)) return

    context.startActivity(getLaunchAppIntent(packageName, true))
}

/**
 * Launch the application.
 *
 * @param packageName The name of the package.
 * @param requestCode If &gt;= 0, this code will be returned in
 * @param activity    The activity.
 * onActivityResult() when the activity exits.
 */
fun launchApp(packageName: String?, requestCode: Int, activity: Activity?) {
    packageName ?: return
    activity ?: return
    if (isSpace(packageName)) return

    activity.startActivityForResult(getLaunchAppIntent(packageName), requestCode)
}

/** ********** relaunchApp ********** */

/**
 * Relaunch the application.
 *
 * @param isKillProcess True to kill the process, false otherwise.
 * @param context       The context.
 */
fun relaunchApp(isKillProcess: Boolean = false, context: Context? = ComkitApplicationConfig.getApp()) {
    context ?: return

    val packageManager = context.packageManager ?: return
    val intent = packageManager.getLaunchIntentForPackage(context.packageName) ?: return
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    context.startActivity(intent)
    if (!isKillProcess) return
    Process.killProcess(Process.myPid())
    System.exit(0)
}

/** ********** launchAppDetailsSettings ********** */

/**
 * Launch the application's details settings.
 *
 * @param packageName The name of the package.
 * @param context     The context.
 */
fun launchAppDetailsSettings(packageName: String? = getAppPackageName(), context: Context? = ComkitApplicationConfig.getApp()) {
    packageName ?: return
    context ?: return
    if (isSpace(packageName)) return

    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:$packageName")
    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}

/** ********** exitApp ********** */

/**
 * Exit the application.
 */
fun exitApp() {
    val activityList = ActivityUtils.activityList
    for (aActivity in activityList.reversed()) { // remove from top
        // activityList remove the index activity at onActivityDestroyed
        ActivityUtils.finishActivity(aActivity)
    }
    System.exit(0)
}


/** ****************************** Class ****************************** */

/**
 * The application's information.
 */
class AppInfo(var packageName: String? = null, var name: String? = null, var icon: Drawable? = null, var packagePath: String? = null, var versionName: String? = null, var versionCode: Int = 0, var isSystem: Boolean = false) {
    override fun toString(): String {
        return "{\n  package name: $packageName\n  app icon: $icon\n  app name: $name\n  app path: $packagePath\n  app v name: $versionName\n  app v code: $versionCode\n  is system: $isSystem}"
    }
}
