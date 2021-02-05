package com.githubyss.mobile.common.kit.manager.audio.enumeration;


/**
 * VoiceType
 * <Description> 声音类型类
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 11:06:31
 */
public enum VoiceType {
    /**
     * 声音类型类
     */
    MALE(0x15), FEMALE(0x25);

    private int type;

    VoiceType(int type) {
        this.type = type;
    }
}
