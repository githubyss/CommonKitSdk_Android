package com.githubyss.mobile.common.kit.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors


/**
 * TaskUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/04 20:02:37
 */
object TaskUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = TaskUtils::class.simpleName ?: "simpleName is null"
    
    private val UTIL_POOL = Executors.newFixedThreadPool(3)
    val UTIL_HANDLER = Handler(Looper.getMainLooper())
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun <T> doAsync(task: Task<T>?): Task<T>? {
        task ?: return null
        
        UTIL_POOL.execute(task)
        return task
    }
    
    fun runOnUiThread(runnable: Runnable?) {
        runnable ?: return
        
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run()
        } else {
            UTIL_HANDLER.post(runnable)
        }
    }
    
    fun runOnUiThreadDelayed(runnable: Runnable?, delayMillis: Long) {
        runnable ?: return
        
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run()
        } else {
            UTIL_HANDLER.postDelayed(runnable, delayMillis)
        }
    }
    
    
    /** ********** ********** ********** Class ********** ********** ********** */
    
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
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface Callback<T> {
        fun onCall(data: T)
    }
}
