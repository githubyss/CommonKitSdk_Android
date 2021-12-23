package com.githubyss.mobile.common.kit.manager.speech_recognition

import android.content.Context
import com.iflytek.cloud.SpeechConstant
import com.iflytek.cloud.SpeechUtility


/**
 * SpeechRecognitionManager
 * 语音识别
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/12/23 16:44:19
 */
object SpeechRecognitionManager {

    /** ********** ********** ********** Properties ********** ********** ********** */

    fun initSdk(context: Context, appId: String = "59898a55") {
        // 初始化科大讯飞 SDK
        SpeechUtility.createUtility(context, SpeechConstant.APPID.toString() + "=$appId")
    }


    /** ********** ********** ********** Functions ********** ********** ********** */

}
