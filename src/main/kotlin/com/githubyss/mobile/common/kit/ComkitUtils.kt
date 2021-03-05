package com.githubyss.mobile.common.kit

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.Application
import android.content.Context
import android.os.Process
import android.text.TextUtils
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.reflect.InvocationTargetException


/**
 * ComkitUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/02 15:40:52
 */
object ComkitUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = ComkitUtils::class.simpleName ?: "simpleName is null"
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    // init {
    //     throw UnsupportedOperationException("u can't instantiate me...")
    // }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun isAppForeground(): Boolean {
        val am = ComkitApplicationConfig.getApp()
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val info = am.runningAppProcesses
        if (info == null || info.size == 0) return false
        for (aInfo in info) {
            if (aInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (aInfo.processName == ComkitApplicationConfig.getApp().packageName) {
                    return true
                }
            }
        }
        return false
    }
    
    fun getCurrentProcessName(): String? {
        var name: String? = getCurrentProcessNameByFile()
        if (!TextUtils.isEmpty(name)) return name
        name = getCurrentProcessNameByAms()
        if (!TextUtils.isEmpty(name)) return name
        name = getCurrentProcessNameByReflect()
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
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
    
    fun getCurrentProcessNameByAms(): String {
        val am = ComkitApplicationConfig.getApp()
                .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val info = am.runningAppProcesses
        if (info == null || info.size == 0) return ""
        val pid = Process.myPid()
        for (aInfo in info) {
            if (aInfo.pid == pid) {
                if (aInfo.processName != null) {
                    return aInfo.processName
                }
            }
        }
        return ""
    }
    
    fun getCurrentProcessNameByReflect(): String {
        var processName = ""
        try {
            val app: Application? = ComkitApplicationConfig.getApp()
            app?.let {
                val loadedApkField = app.javaClass.getField("mLoadedApk")
                loadedApkField.isAccessible = true
                val loadedApk = loadedApkField[app]
                
                val activityThreadField = loadedApk.javaClass.getDeclaredField("mActivityThread")
                activityThreadField.isAccessible = true
                val activityThread = activityThreadField[loadedApk]
                
                val getProcessName = activityThread.javaClass.getDeclaredMethod("getProcessName")
                processName = getProcessName.invoke(activityThread) as String
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return processName
    }
    
    fun getApplicationByReflect(): Application {
        try {
            @SuppressLint("PrivateApi")
            val activityThread = Class.forName("android.app.ActivityThread")
            val thread = activityThread.getMethod("currentActivityThread")
                    .invoke(null)
            val app = activityThread.getMethod("getApplication")
                    .invoke(thread) ?: throw NullPointerException("u should init first")
            return app as Application
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        throw NullPointerException("u should init first")
    }
}
