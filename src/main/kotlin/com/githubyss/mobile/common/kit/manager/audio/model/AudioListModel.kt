package com.githubyss.mobile.common.kit.manager.audio.model

import android.text.TextUtils
import com.githubyss.mobile.common.kit.manager.audio.enumeration.VoiceType
import java.io.Serializable
import java.util.*


/**
 * AudioListModel
 * 音频信息列表数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/24 14:08:51
 */
class AudioListModel : Serializable {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        @VoiceType
        var savedVoiceType = VoiceType.FEMALE
    }
    
    var currentIndex = 0
    var audioList: List<AudioModel?>? = null
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun reprocessAudioModel() {
        for (audio in audioList ?: return) {
            audio?.processVoiceAndUrl()
        }
    }
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    private fun filtrateAudioList(audioList: List<AudioModel>): MutableList<AudioModel?>? {
        val filtratedAudioList: MutableList<AudioModel?> = ArrayList()
        for (audioInfo in audioList) {
            val isIdEmpty = TextUtils.isEmpty(audioInfo.id)
            val isTitleEmpty = TextUtils.isEmpty(audioInfo.title)
            val isBothVoiceUrlEmpty = TextUtils.isEmpty(audioInfo.maleUrl) && TextUtils.isEmpty(audioInfo.femaleUrl)
            if (isIdEmpty || isTitleEmpty || isBothVoiceUrlEmpty) {
                continue
            }
            filtratedAudioList.add(audioInfo)
        }
        return filtratedAudioList
    }
}
