package com.githubyss.mobile.common.kit.manager.audio_player.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


/**
 * AudioState
 * 播放状态
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/24 14:07:42
 */
@Documented
@IntDef({AudioState.START, AudioState.PREPARE, AudioState.PLAYING, AudioState.STOP, AudioState.END, AudioState.PAUSE, AudioState.READY, AudioState.SWITCH})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface AudioState {
    
    final int START   = 0x15;
    final int PREPARE = 0x25;
    final int PLAYING = 0x35;
    final int STOP    = 0x45;
    final int END     = 0x55;
    final int PAUSE   = 0x65;
    final int READY   = 0x75;
    final int SWITCH  = 0x85;
}
