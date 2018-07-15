package com.githubyss.mobile.common.kit.processor

import android.os.Looper

/**
 * ComkitThreadProcessor
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitThreadProcessor {
    /**
     * ComkitThreadProcessor.assertMainThread()
     * <Description> Assert function run in main thread.
     * <Details>
     *
     * @param
     * @return
     * @author Ace Yan
     * @github githubyss
     */
    fun assertMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) throw IllegalStateException("Call the method must be in main thread: ${Thread.currentThread().stackTrace[3]?.toString()}")
    }
}
