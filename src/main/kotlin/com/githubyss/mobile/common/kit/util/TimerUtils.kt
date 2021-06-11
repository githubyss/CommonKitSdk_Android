package com.githubyss.mobile.common.kit.util

import android.os.Handler
import android.os.HandlerThread
import java.util.*

class TimerUtils private constructor() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val INSTANCE: Timer by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { Timer() }
    }
    
    private var handler: Handler? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        val thread = HandlerThread("timer")
        thread.start()
        handler = Handler(thread.looper)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun post(runnable: Runnable) {
        handler?.post(runnable)
    }
    
    fun postDelayed(runnable: Runnable, delay: Long) {
        handler?.postDelayed(runnable, delay)
    }
}
