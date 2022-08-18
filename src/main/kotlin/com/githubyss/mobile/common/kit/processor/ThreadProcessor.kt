package com.githubyss.mobile.common.kit.processor

import android.os.Looper

/**
 * ThreadProcessor
 * <Description>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ThreadProcessor {
    /**
     * ThreadProcessor.assertMainThread()
     * Assert function run in main thread.
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
