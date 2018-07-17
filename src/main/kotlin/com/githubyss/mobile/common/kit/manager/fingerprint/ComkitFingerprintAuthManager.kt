package com.githubyss.mobile.common.kit.manager.fingerprint

import android.app.Application
import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal
import android.support.annotation.RequiresApi
import com.githubyss.mobile.common.kit.logcat.ComkitLogcatUtils
import java.lang.Exception

/**
 * ComkitFingerprintAuthManager
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComkitFingerprintAuthManager private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = ComkitFingerprintAuthManager()
    }


    interface OnFingerprintAuthListener {
        fun onFingerprintAuth(result: FingerprintAuthResult, info: String)
    }


    private val TOTAL_AUTH_FAILED_MAX_COUNT = 5
    private val SINGLE_AUTH_FAILED_MAX_COUNT = 3

    private var onFingerprintAuthListener: OnFingerprintAuthListener? = null
    private var fingerprintManager: FingerprintManager? = null
    private var cancellationSignal: CancellationSignal? = null

    private var hasFingerprintManager = false
    private var currentTotalFailedCount = 0
    private var currentSingleFailedCount = 0


    enum class FingerprintAuthResult(val info: String) {
        SYSTEM_SUCCEEDED("SYSTEM_SUCCEEDED"),
        SYSTEM_FAILED("SYSTEM_FAILED"),
        SYSTEM_ERROR("SYSTEM_ERROR"),
        SYSTEM_HELP("SYSTEM_HELP"),
        INTERIOR_CANCEL("INTERIOR_CANCEL"),
        EXTERIOR_CANCEL("EXTERIOR_CANCEL")
    }

    private inner class FingerprintAuthCallBack : FingerprintManager.AuthenticationCallback() {
        private var errorFlag = false

        override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult?) {
            onFingerprintAuthListener?.onFingerprintAuth(FingerprintAuthResult.SYSTEM_SUCCEEDED, FingerprintAuthResult.SYSTEM_SUCCEEDED.info)
            cancellationSignal = null
            currentTotalFailedCount = 0

            logcatAuthCount("onAuthenticationSucceeded()")
        }

        override fun onAuthenticationFailed() {
            currentTotalFailedCount++
            currentSingleFailedCount++
            if (currentTotalFailedCount <= TOTAL_AUTH_FAILED_MAX_COUNT) {
                if (currentSingleFailedCount == SINGLE_AUTH_FAILED_MAX_COUNT) {
                    cancelAuthInternally()
                    currentSingleFailedCount = 0
                    return
                } else {
                    onFingerprintAuthListener?.onFingerprintAuth(FingerprintAuthResult.SYSTEM_FAILED, "${FingerprintAuthResult.SYSTEM_FAILED.info}: {currentTotalFailedCount:$currentTotalFailedCount}")
                }
            } else {
                currentTotalFailedCount = 0
            }

            logcatAuthCount("onAuthenticationFailed()")
        }

        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            if (errorFlag || (cancellationSignal?.isCanceled == true)) {
                cancellationSignal = null
                return
            }

            errorFlag = true
            currentTotalFailedCount = 0
            onFingerprintAuthListener?.onFingerprintAuth(FingerprintAuthResult.SYSTEM_ERROR, "${FingerprintAuthResult.SYSTEM_ERROR.info}: {errString:$errString}")

            logcatAuthCount("onAuthenticationError()")
        }

        override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
            onFingerprintAuthListener?.onFingerprintAuth(FingerprintAuthResult.SYSTEM_HELP, "${FingerprintAuthResult.SYSTEM_HELP.info}: {helpCode:$helpString, helpString:$helpString}")

            logcatAuthCount("onAuthenticationHelp()")
        }
    }


    fun startAuth(application: Application, onFingerprintAuthListener: OnFingerprintAuthListener) {
        this@ComkitFingerprintAuthManager.onFingerprintAuthListener = onFingerprintAuthListener
        if (beSdkVersionAfterM() && fingerprintManager == null) {
            try {
                fingerprintManager = application.getSystemService(Context.FINGERPRINT_SERVICE) as FingerprintManager
                hasFingerprintManager = true
            } catch (e: Exception) {
                ComkitLogcatUtils.e(msg = e.toString())
                hasFingerprintManager = false
            }
        }

        if (hasEnrolledFingerprints()) {
            currentSingleFailedCount = 0
            cancellationSignal = CancellationSignal()
            fingerprintManager?.authenticate(null, cancellationSignal, 0, FingerprintAuthCallBack(), null)
        }

        logcatAuthCount("startAuth()")
    }

    fun release() {
        onFingerprintAuthListener = null
        cancelAuthExternally()

        logcatAuthCount("release()")
    }

    fun cancelAuthExternally() {
        if (beSdkVersionAfterM() && (cancellationSignal?.isCanceled != true)) {
            cancellationSignal?.cancel()
            onFingerprintAuthListener?.onFingerprintAuth(FingerprintAuthResult.EXTERIOR_CANCEL, FingerprintAuthResult.EXTERIOR_CANCEL.info)
        }

        logcatAuthCount("cancelAuthExternally()")
    }

    private fun cancelAuthInternally() {
        if (beSdkVersionAfterM() && (cancellationSignal?.isCanceled != true)) {
            cancellationSignal?.cancel()
            onFingerprintAuthListener?.onFingerprintAuth(FingerprintAuthResult.INTERIOR_CANCEL, FingerprintAuthResult.INTERIOR_CANCEL.info)
        }

        logcatAuthCount("cancelAuthInternally()")
    }

    private fun hasEnrolledFingerprints(): Boolean
            = beSupportFingerprint() && (fingerprintManager?.hasEnrolledFingerprints() == true)

    private fun beSupportFingerprint(): Boolean
            = beSdkVersionAfterM() && hasFingerprintManager && (fingerprintManager?.isHardwareDetected == true)

    private fun beSdkVersionAfterM(): Boolean
            = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    private fun logcatAuthCount(location: String = "") {
        ComkitLogcatUtils.d(
                msg = "$location: " +
                        "{currentSingleFailedCount:$currentSingleFailedCount, currentTotalFailedCount:$currentTotalFailedCount}\t")
    }
}
