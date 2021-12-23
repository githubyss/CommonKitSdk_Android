package com.githubyss.mobile.common.kit.manager.audio_player.model

import android.text.TextUtils
import com.githubyss.mobile.common.kit.manager.audio_player.enumeration.VoiceType
import java.io.Serializable


/**
 * AudioModel
 * 音频信息数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/24 14:08:34
 */
class AudioModel : Serializable {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var id: String? = null
    var title: String? = null
    var author: String? = null
    var icon: String? = null
    var url: String? = null
    var maleUrl: String? = null
    var femaleUrl: String? = null
    
    @VoiceType
    var voiceType = VoiceType.MALE
        private set
    var isHasBothVoiceUrl = false
    var isPlaying = false
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor()
    
    constructor(id: String?, title: String?, author: String?, icon: String?, url: String?, maleUrl: String?, femaleUrl: String?) {
        this.id = id
        this.title = title
        this.author = author
        this.icon = icon
        this.url = url
        this.maleUrl = maleUrl
        this.femaleUrl = femaleUrl
        setVoiceType(AudioListModel.savedVoiceType)
        isHasBothVoiceUrl = !TextUtils.isEmpty(this.maleUrl) && !TextUtils.isEmpty(this.femaleUrl)
        processVoiceAndUrl()
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    fun processVoiceAndUrl() {
        if (!TextUtils.isEmpty(maleUrl) && TextUtils.isEmpty(femaleUrl)) {
            setVoiceType(VoiceType.MALE)
            url = maleUrl
            return
        }
        if (!TextUtils.isEmpty(femaleUrl) && TextUtils.isEmpty(maleUrl)) {
            setVoiceType(VoiceType.FEMALE)
            url = femaleUrl
            return
        }
        if (TextUtils.isEmpty(maleUrl) && TextUtils.isEmpty(femaleUrl)) {
            setVoiceType(AudioListModel.savedVoiceType)
            url = ""
            return
        }
        if (!TextUtils.isEmpty(maleUrl) && !TextUtils.isEmpty(femaleUrl)) {
            setVoiceType(AudioListModel.savedVoiceType)
            when (voiceType) {
                VoiceType.MALE -> url = maleUrl
                VoiceType.FEMALE -> url = femaleUrl
            }
            return
        }
    }
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    private fun setVoiceType(@VoiceType voiceType: Int) {
        this.voiceType = voiceType
    }
}
