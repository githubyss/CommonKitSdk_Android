package com.githubyss.common.kit.manager.speech_recognition

import android.content.Context
import android.os.Bundle
import com.githubyss.common.base.application.BaseApplicationHolder
import com.githubyss.common.kit.util.logD
import com.githubyss.common.kit.util.showToast
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

    private val TAG: String = SpeechRecognitionManager::class.java.simpleName

    var speechRecognizer: SpeechRecognizer? = null
    var textUnderstander: TextUnderstander? = null

    var onSpeechRecognizerCallback: OnSpeechRecognizerCallback? = null
    var onTextUnderstanderCallback: OnTextUnderstanderCallback? = null

    var isSpeaking = false


    /** ****************************** Functions ****************************** */

    /**
     * 0.初始化语音识别组件
     *
     * @param context
     * @param appId 确保 appId 和 MSC 库相匹配，此处为"b579ee15"
     */
    fun initSdk(context: Context = BaseApplicationHolder.getApp(), appId: String = "b579ee15") {
        // 初始化科大讯飞 SDK
        SpeechUtility.createUtility(context, "${SpeechConstant.APPID}=$appId")
    }

    /**
     * 1.配置「语音识别器」和「文本语义理解器」实例
     *
     * @param context
     * @param listener 本地听写时传 InitListener
     */
    fun configSDK(context: Context = BaseApplicationHolder.getApp()) {
        // 创建「语音识别器」 SpeechRecognizer 对象
        speechRecognizer = SpeechRecognizer.createRecognizer(context, null)

        // 创建「文本语义理解器」 TextUnderstander 对象
        textUnderstander = TextUnderstander.createTextUnderstander(context, initListener)
    }

    /**
     * 2.设置「语音识别器」聆听参数，详见《科大讯飞 MSC API 手册（Android）》SpeechConstant 类
     *
     * @param context
     * @param domain
     * @param language
     * @param accent
     * @param asrPtt
     * @param vadBos
     * @param vadEos
     */
    fun setSpeechRecognizerParam(context: Context = BaseApplicationHolder.getApp(), domain: String = "iat", language: String = "zh_cn", accent: String = "mandarin ", asrPtt: String = "0", vadBos: String = "100000", vadEos: String = "100000") {
        if (speechRecognizer != null) {
            speechRecognizer?.setParameter(SpeechConstant.DOMAIN, domain)
            speechRecognizer?.setParameter(SpeechConstant.LANGUAGE, language)
            speechRecognizer?.setParameter(SpeechConstant.ACCENT, accent)
            speechRecognizer?.setParameter(SpeechConstant.ASR_PTT, asrPtt)
            speechRecognizer?.setParameter(SpeechConstant.VAD_BOS, vadBos)
            speechRecognizer?.setParameter(SpeechConstant.VAD_EOS, vadEos)
        }
        else {
            showToast("抱歉，服务器开了个小差，请退出重试")
            speechRecognizer = SpeechRecognizer.createRecognizer(context, null)
        }
    }

    /**
     * 「语音识别器」开始聆听
     */
    fun startListening() {
        isSpeaking = true
        speechRecognizer?.startListening(speechRecognizerListener)
    }

    /**
     * 「语音识别器」停止聆听
     */
    fun stopListening() {
        isSpeaking = false
        speechRecognizer?.stopListening()
    }

    /**
     * 「文本语义理解器」理解文本
     *
     * @param text
     */
    fun understandText(text: String) {
        if (textUnderstander?.isUnderstanding == true) {
            textUnderstander?.cancel()
        }
        else {
            // 设置语义情景
            // textUnderstander.setParameter(SpeechConstant.SCENE, "main");
            val ret = textUnderstander?.understandText(text, textUnderstanderListener)
            if (ret != 0) {
                // ToastUtil.showToast("语义理解失败,错误码:$ret")
            }
        }
    }

    /**
     * 「语音识别器」判空
     *
     * @return `true`: yes <br>
     *         `false`: no
     */
    fun isSpeechRecognizerNull(): Boolean {
        return speechRecognizer == null
    }

    /**
     * 「语音识别器」销毁
     */
    fun destroy() {
        if (textUnderstander?.isUnderstanding != false) {
            textUnderstander?.cancel()
        }
        textUnderstander?.destroy()
    }


    /** ****************************** Implementations ****************************** */

    /**
     * 初始化监听器（文本到语义）。
     */
    private val initListener: InitListener = InitListener { code ->
        if (code != ErrorCode.SUCCESS) {
            logD(TAG, "InitListener.onInit >>> 初始化失败，错误码：${code}")
        }
    }

    /**
     * 「语音识别器」监听器
     */
    private val speechRecognizerListener: RecognizerListener = object : RecognizerListener {
        // 内部录音机已经准备好了，用户可以开始语音输入
        override fun onBeginOfSpeech() {
            logD(TAG, "RecognizerListener.onBeginOfSpeech")
            onSpeechRecognizerCallback?.onBeginOfSpeech()
        }

        // 错误码：10118(您没有说话)，可能是录音机权限被禁，需要提示用户打开应用的录音权限。
        // 如果使用本地功能（语记）需要提示用户开启语记的录音权限。
        override fun onError(error: SpeechError) {
            logD(TAG, "RecognizerListener.onError >>> errorCode:${error.errorCode}, errorDescription:${error.errorDescription}")
            onSpeechRecognizerCallback?.onError(error.errorCode, error.errorDescription)
        }

        // 检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
        override fun onEndOfSpeech() {
            logD(TAG, "RecognizerListener.onEndOfSpeech")
            if (speechRecognizer == null) return
            if (isSpeaking) {
                // 如果用户还是没有松手，则强行听写用户说话内容
                speechRecognizer?.startListening(this)
            }
            onSpeechRecognizerCallback?.onEndOfSpeech()
        }

        override fun onResult(results: RecognizerResult, isLast: Boolean) {
            logD(TAG, "RecognizerListener.onResult >>> resultString:${results.resultString}, isLast:${isLast}")
            onSpeechRecognizerCallback?.onResult(results.resultString, isLast)
        }

        override fun onVolumeChanged(volume: Int, data: ByteArray) {
            logD(TAG, "RecognizerListener.onVolumeChanged >>> 返回音频数据 volume:${volume}, data.size:${data.size}")
            onSpeechRecognizerCallback?.onVolumeChanged(volume, data)
        }

        // 以下代码用于获取与云端的会话 id，当业务出错时将会话 id 提供给技术支持人员，可用于查询会话日志，定位出错原因
        // 若使用本地能力，会话 id 为 null
        override fun onEvent(eventType: Int, arg1: Int, arg2: Int, obj: Bundle?) {
            logD(TAG, "RecognizerListener.onEvent >>> eventType:${eventType}, arg1:${arg1}, arg2:${arg2}")
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                val sid: String? = obj?.getString(SpeechEvent.KEY_EVENT_SESSION_ID)
                logD(TAG, "RecognizerListener.onEvent >>> session id:${sid}")
            }
            onSpeechRecognizerCallback?.onEvent(eventType, arg1, arg2, obj)
        }
    }

    /**
     * 「文本语义理解器」监听器
     */
    private val textUnderstanderListener: TextUnderstanderListener = object : TextUnderstanderListener {
        override fun onResult(result: UnderstanderResult) {
            logD(TAG, "TextUnderstanderListener.onResult >>> resultString:${result.resultString}")
            onTextUnderstanderCallback?.onResult(result.resultString)
        }

        override fun onError(error: SpeechError) {
            logD(TAG, "TextUnderstanderListener.onError >>> errorCode:${error.errorCode}, errorDescription:${error.errorDescription}")

            // 文本语义不能使用回调错误码 14002，请确认您下载 SDK 时是否勾选语义场景和私有语义的发布
            // 请到 aiui.xfyun.cn 配置语义，从 1115 前的 SDK 更新到 1116 以上版本 SDK 后，语义需要重新到 aiui.xfyun.cn 配置
            onTextUnderstanderCallback?.onError(error.errorCode, error.errorDescription)
        }
    }


    /** ****************************** Interface ****************************** */

    /**
     * 「语音识别器」对外回调接口
     */
    interface OnSpeechRecognizerCallback {
        fun onBeginOfSpeech()
        fun onError(errorCode: Int, errorDescription: String)
        fun onEndOfSpeech()
        fun onResult(result: String, isLast: Boolean)
        fun onVolumeChanged(volume: Int, data: ByteArray)
        fun onEvent(eventType: Int, arg1: Int, arg2: Int, obj: Bundle?)
    }

    /**
     * 「文本语义理解器」对外回调接口
     */
    interface OnTextUnderstanderCallback {
        fun onResult(result: String)
        fun onError(errorCode: Int, errorDescription: String)
    }
}
