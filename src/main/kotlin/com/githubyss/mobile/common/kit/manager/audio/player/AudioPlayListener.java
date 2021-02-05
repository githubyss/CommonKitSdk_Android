package com.githubyss.mobile.common.kit.manager.audio.player;


import com.githubyss.mobile.common.kit.manager.audio.enumeration.AudioState;

/**
 * AudioPlayInterface
 * <Description> 播放状态接口
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 17:59:28
 */
public interface AudioPlayListener {
    
    /**
     * 当前状态改变
     */
    void onStateChanged(AudioState audioState);
    
    /**
     * 当前播放进度
     */
    void onPlayProgress(int currentPosition);
    
    /**
     * 当前缓存进度
     */
    void onBufferingUpdateProgress(int percent);
}
