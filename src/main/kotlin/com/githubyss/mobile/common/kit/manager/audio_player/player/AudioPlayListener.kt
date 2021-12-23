package com.githubyss.mobile.common.kit.manager.audio_player.player

import com.githubyss.mobile.common.kit.manager.audio_player.enumeration.AudioState


/**
 * AudioPlayListener
 * 播放状态接口
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/24 11:41:40
 */
interface AudioPlayListener {
    
    /**
     * 当前状态改变
     */
    fun onStateChanged(@AudioState audioState: Int)
    
    /**
     * 当前播放进度
     */
    fun onPlayProgress(currentPosition: Int)
    
    /**
     * 当前缓存进度
     */
    fun onBufferingUpdateProgress(percent: Int)
}
