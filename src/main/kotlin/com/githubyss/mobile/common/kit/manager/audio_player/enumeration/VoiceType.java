package com.githubyss.mobile.common.kit.manager.audio_player.enumeration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


/**
 * VoiceType
 * 声音类型
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/24 14:07:53
 */
@IntDef({VoiceType.MALE, VoiceType.FEMALE})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface VoiceType {
    
    final int MALE   = 0x15;
    final int FEMALE = 0x25;
}
