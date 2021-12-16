package com.githubyss.mobile.common.kit.util

import java.util.*


/**
 * TimerUtils
 * 计时器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/22 14:55:14
 */
object TimerUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = TimerUtils::class.java.simpleName
    
    private var timer: Timer? = null
    // private var handler: Handler? = null
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    // init {
    // val thread = HandlerThread("timer")
    // thread.start()
    // handler = Handler(thread.looper)
    // }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    // fun post(runnable: Runnable) {
    //     handler?.post(runnable)
    // }
    //
    // fun postDelayed(runnable: Runnable, delay: Long) {
    //     handler?.postDelayed(runnable, delay)
    // }
    
    /** ********** ********** initTimer ********** ********** */
    
    /**
     * Init the timer to ensure not null.
     */
    private fun initTimer() {
        if (timer == null) {
            timer = Timer()
        }
    }
    
    /** ********** ********** runTaskPeriodically ********** ********** */
    
    /**
     * Run the timer task periodically.
     *
     * @param timerTask The timer task.
     * @param delay     The delay before timer task execute run().
     * @param period    The period between every timer task.
     * @return The method exec result.
     */
    fun runTaskPeriodically(timerTask: TimerTask?, delay: Long?, period: Long?): Boolean {
        delay ?: return false
        period ?: return false
        
        initTimer()
        return try {
            timer?.schedule(timerTask, delay, period)
            true
        } catch (e: Exception) {
            LogUtils.e(TAG, e)
            false
        }
    }
    
    /**
     * Run the timer task periodically.
     *
     * @param timerTask The timer task.
     * @param time      The time moment when timer task execute run().
     * @param period    The period between every timer task.
     * @return The method exec result.
     */
    fun runTaskPeriodically(timerTask: TimerTask, time: Date?, period: Long?): Boolean {
        time ?: return false
        period ?: return false
        
        initTimer()
        return try {
            timer?.schedule(timerTask, time, period)
            true
        } catch (e: Exception) {
            LogUtils.e(TAG, e)
            false
        }
        
    }
    
    /**
     * Run the timer task periodically with time offset.
     *
     * @param timerTask The timer task.
     * @param delay     The delay before timer task execute run().
     * @param period    The period between every timer task.
     * @return The method exec result.
     */
    fun runTaskPeriodicallyWithTimeOffset(timerTask: TimerTask, delay: Long?, period: Long?): Boolean {
        delay ?: return false
        period ?: return false
        
        initTimer()
        return try {
            timer?.scheduleAtFixedRate(timerTask, delay, period)
            true
        } catch (e: Exception) {
            LogUtils.e(TAG, e)
            false
        }
    }
    
    /**
     * Run the timer task periodically with time offset.
     *
     * @param timerTask The timer task.
     * @param time      The time moment when timer task execute run().
     * @param period    The period between every timer task.
     * @return The method exec result.
     */
    fun runTaskPeriodicallyWithTimeOffset(timerTask: TimerTask, time: Date?, period: Long?): Boolean {
        time ?: return false
        period ?: return false
        
        initTimer()
        return try {
            timer?.scheduleAtFixedRate(timerTask, time, period)
            true
        } catch (e: Exception) {
            LogUtils.e(TAG, e)
            false
        }
    }
    
    /** ********** ********** runTaskOnce ********** ********** */
    
    /**
     * Run the timer task once.
     *
     * @param timerTask The timer task.
     * @param delay     The delay before timer task execute run().
     * @return The method exec result.
     */
    fun runTaskOnce(timerTask: TimerTask, delay: Long?): Boolean {
        delay ?: return false
        
        initTimer()
        return try {
            timer?.schedule(timerTask, delay)
            true
        } catch (e: Exception) {
            LogUtils.e(TAG, e)
            false
        }
    }
    
    /**
     * Run the timer task once.
     *
     * @param timerTask The timer task.
     * @param time      The time moment when timer task execute run().
     * @return The method exec result.
     */
    fun runTaskOnce(timerTask: TimerTask, time: Date?): Boolean {
        time ?: return false
        
        initTimer()
        return try {
            timer?.schedule(timerTask, time)
            true
        } catch (e: Exception) {
            LogUtils.e(TAG, e)
            false
        }
    }
    
    /** ********** ********** cancel ********** ********** */
    
    /**
     * Need be called when timer task stopped.
     */
    fun cancel() {
        timer?.cancel()
        timer?.purge()
        timer = null
    }
}
