package com.githubyss.mobile.common.kit.manager.audio.enumeration;


/**
 * AudioState
 * <Description> 播放状态类
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 11:06:31
 */
public enum AudioState {
    /**
     * 播放状态类
     */
    START(0x15), PREPARE(0x25), PLAYING(0x35), STOP(0x45), END(0x45), PAUSE(0x55), READY(0x65), SWITCH(0x75);
    
    private int state;
    
    AudioState(int state) {
        this.state = state;
    }
}
