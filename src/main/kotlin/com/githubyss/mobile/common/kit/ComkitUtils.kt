package com.githubyss.mobile.common.kit

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.text.TextUtils
import com.githubyss.mobile.common.kit.util.ActivityUtils
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.reflect.InvocationTargetException
import java.util.concurrent.Executors


/**
 * ComkitUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/02 15:40:52
 */
object ComkitUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val UTIL_POOL = Executors.newFixedThreadPool(3)
    
    val UTIL_HANDLER = Handler(Looper.getMainLooper())
    
    @SuppressLint("StaticFieldLeak")
    private var application: Application? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    // init {
    //     throw UnsupportedOperationException("u can't instantiate me...")
    // }
    
    
    /** ********** ********** ********** Public ********** ********** ********** */
    
    /**
     * Init utils.
     * Init it in the class of Application.
     *
     * @param context context
     */
    fun init(context: Context?) {
        if (context == null) {
            init(getApplicationByReflect())
            return
        }
        init(context.applicationContext as Application)
    }
    
    /**
     * Init utils.
     * Init it in the class of Application.
     *
     * @param app application
     */
    fun init(app: Application?) {
        if (application == null) {
            application = app ?: getApplicationByReflect()
            application?.registerActivityLifecycleCallbacks(ActivityUtils.activityLifecycle)
        } else {
            if (app != null && app.javaClass != application?.javaClass) {
                application?.unregisterActivityLifecycleCallbacks(ActivityUtils.activityLifecycle)
                ActivityUtils.activityLifecycle.activityList.clear()
                application = app
                application?.registerActivityLifecycleCallbacks(ActivityUtils.activityLifecycle)
            }
        }
    }
    
    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    fun getApp(): Application {
        if (application != null) {
            return application ?: throw NullPointerException("application is null...")
        }
        val app: Application = getApplicationByReflect()
        init(app)
        return app
    }
    
    fun isAppForeground(): Boolean {
        val am = getApp().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val info = am.runningAppProcesses
        if (info == null || info.size == 0) return false
        for (aInfo in info) {
            if (aInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (aInfo.processName == getApp().packageName) {
                    return true
                }
            }
        }
        return false
    }
    
    fun <T> doAsync(task: Task<T>?): Task<T>? {
        UTIL_POOL.execute(task)
        return task
    }
    
    fun runOnUiThread(runnable: Runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run()
        } else {
            UTIL_HANDLER.post(runnable)
        }
    }
    
    fun runOnUiThreadDelayed(runnable: Runnable, delayMillis: Long) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run()
        } else {
            UTIL_HANDLER.postDelayed(runnable, delayMillis)
        }
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
        val am = getApp().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager ?: return ""
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
            val app: Application? = getApp()
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
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    abstract class Task<Result>(callback: Callback<Result>) : Runnable {
        @Volatile
        private var state = NEW
        abstract fun doInBackground(): Result
        private val callback: Callback<Result> = callback
        
        override fun run() {
            try {
                val t = doInBackground()
                if (state != NEW) return
                state = COMPLETING
                UTIL_HANDLER.post(Runnable { callback.onCall(t) })
            } catch (th: Throwable) {
                if (state != NEW) return
                state = EXCEPTIONAL
            }
        }
        
        fun cancel() {
            state = CANCELLED
        }
        
        fun isDone(): Boolean {
            return state != NEW
        }
        
        fun isCanceled(): Boolean {
            return state == CANCELLED
        }
        
        companion object {
            private const val NEW = 0
            private const val COMPLETING = 1
            private const val CANCELLED = 2
            private const val EXCEPTIONAL = 3
        }
    }
    
    interface Callback<T> {
        fun onCall(data: T)
    }
}
