package com.githubyss.mobile.common.kit.manager.speech_recognition

import android.app.Activity
import android.content.Context
import com.githubyss.mobile.common.kit.AceTemp
import com.githubyss.mobile.common.kit.util.LogUtils
import com.iflytek.cloud.*


/**
 * SpeechRecognitionManager
 * 语音识别
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/23 16:44:19
 */
object SpeechRecognitionManager {

    /** ****************************** Properties ****************************** */

    private val TAG: String = AceTemp::class.java.simpleName
    var iat: SpeechRecognizer? = null
    var textUnderstander: TextUnderstander? = null

    /** ****************************** Functions ****************************** */

    fun initSdk(context: Context, appId: String = "59898a55") {
        // 初始化科大讯飞 SDK
        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=$appId")
    }

    fun configSDK(activity: Activity, listener: InitListener = textUdrInitListener) {
        // 1.创建SpeechRecognizer对象，第二个参数：本地听写时传InitListener
        iat = SpeechRecognizer.createRecognizer(activity, null)

        // 2.设置听写参数，详见《科大讯飞MSC API手册(Android)》SpeechConstant类
        // 初始化文本到语义
        textUnderstander = TextUnderstander.createTextUnderstander(activity, listener)
    }


    /** ****************************** Implementations ****************************** */

    /**
     * 初始化监听器（文本到语义）。
     */
    private val textUdrInitListener = InitListener { code ->
        if (code != ErrorCode.SUCCESS) {
            LogUtils.d(TAG, "初始化失败，错误码：$code")
            // ToastUtil.showMessage("初始化失败,错误码：$code")
        }
    }
}
