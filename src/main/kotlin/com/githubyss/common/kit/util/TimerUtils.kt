package com.githubyss.common.kit.util

import java.util.*


/**
 * TimerUtils
 * 计时器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/22 14:55:14
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "TimerUtils"

// private var handler: Handler? = null


/** ****************************** Constructors ****************************** */

// init {
// val thread = HandlerThread("timer")
// thread.start()
// handler = Handler(thread.looper)
// }


/** ****************************** Functions ****************************** */

// fun post(runnable: Runnable) {
//     handler?.post(runnable)
// }
//
// fun postDelayed(runnable: Runnable, delay: Long) {
//     handler?.postDelayed(runnable, delay)
// }

/** ******************** runTaskOnce ******************** */

/**
 * Run the timer task once.
 *
 * @param timer     The timer.
 * @param timerTask The timer task.
 * @param delay     The delay before timer task execute run().
 * @return The method exec result.
 */
fun runTaskOnce(timer: Timer?, timerTask: TimerTask?, delay: Long?): Boolean {
    timer ?: return false
    timerTask ?: return false
    delay ?: return false

    return try {
        timer.schedule(timerTask, delay)
        true
    }
    catch (e: Exception) {
        logE(TAG, t = e)
        false
    }
}

/**
 * Run the timer task once.
 *
 * @param timer     The timer.
 * @param timerTask The timer task.
 * @param date      The time moment when timer task execute run().
 * @return The method exec result.
 */
fun runTaskOnce(timer: Timer?, timerTask: TimerTask?, date: Date?): Boolean {
    timer ?: return false
    timerTask ?: return false
    date ?: return false

    return try {
        timer.schedule(timerTask, date)
        true
    }
    catch (e: Exception) {
        logE(TAG, t = e)
        false
    }
}

/** ******************** runTaskPeriodically ******************** */

/**
 * Run the timer task periodically.
 *
 * @param timer     The timer.
 * @param timerTask The timer task.
 * @param delay     The delay before timer task execute run().
 * @param period    The period between every timer task.
 * @return The method exec result.
 */
fun runTaskPeriodically(timer: Timer?, timerTask: TimerTask?, delay: Long?, period: Long?): Boolean {
    timer ?: return false
    timerTask ?: return false
    delay ?: return false
    period ?: return false

    return try {
        timer.schedule(timerTask, delay, period)
        true
    }
    catch (e: Exception) {
        logE(TAG, t = e)
        false
    }
}

/**
 * Run the timer task periodically.
 *
 * @param timer     The timer.
 * @param timerTask The timer task.
 * @param date      The date moment when timer task execute run().
 * @param period    The period between every timer task.
 * @return The method exec result.
 */
fun runTaskPeriodically(timer: Timer?, timerTask: TimerTask?, date: Date?, period: Long?): Boolean {
    timer ?: return false
    timerTask ?: return false
    date ?: return false
    period ?: return false

    return try {
        timer.schedule(timerTask, date, period)
        true
    }
    catch (e: Exception) {
        logE(TAG, t = e)
        false
    }
}

/**
 * Run the timer task periodically with time offset.
 *
 * @param timer     The timer.
 * @param timerTask The timer task.
 * @param delay     The delay before timer task execute run().
 * @param period    The period between every timer task.
 * @return The method exec result.
 */
fun runTaskPeriodicallyAtFixedRate(timer: Timer?, timerTask: TimerTask?, delay: Long?, period: Long?): Boolean {
    timer ?: return false
    timerTask ?: return false
    delay ?: return false
    period ?: return false

    return try {
        timer.scheduleAtFixedRate(timerTask, delay, period)
        true
    }
    catch (e: Exception) {
        logE(TAG, t = e)
        false
    }
}

/**
 * Run the timer task periodically with time offset.
 *
 * @param timer     The timer.
 * @param timerTask The timer task.
 * @param date      The time moment when timer task execute run().
 * @param period    The period between every timer task.
 * @return The method exec result.
 */
fun runTaskPeriodicallyAtFixedRate(timer: Timer?, timerTask: TimerTask?, date: Date?, period: Long?): Boolean {
    timer ?: return false
    timerTask ?: return false
    date ?: return false
    period ?: return false

    return try {
        timer.scheduleAtFixedRate(timerTask, date, period)
        true
    }
    catch (e: Exception) {
        logE(TAG, t = e)
        false
    }
}

/** ******************** cancel ******************** */

/**
 * Need be called when timer task stopped.
 */
fun cancelTimer(timer: Timer?) {
    timer?.cancel()
    timer?.purge()
}
