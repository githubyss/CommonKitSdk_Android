package com.githubyss.mobile.common.kit.manager.audio.model;

import android.text.TextUtils;

import com.githubyss.mobile.common.kit.manager.audio.enumeration.VoiceType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * AudioInfo
 * <Description> 音频信息列表数据结构
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 11:04:00
 */
public class AudioListModel implements Serializable {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private int currentIndex;
    private List<AudioModel> audioList;
    
    public static VoiceType savedVoiceType = VoiceType.FEMALE;
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    public void reprocessAudioModel() {
        for (AudioModel audio : audioList) {
            audio.processVoiceAndUrl();
        }
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private List<AudioModel> filtrateAudioList(List<AudioModel> audioList) {
        List<AudioModel> filtratedAudioList = new ArrayList();
        for (AudioModel audioInfo : audioList) {
            boolean isIdEmpty = TextUtils.isEmpty(audioInfo.getId());
            boolean isTitleEmpty = TextUtils.isEmpty(audioInfo.getTitle());
            boolean isBothVoiceUrlEmpty = TextUtils.isEmpty(audioInfo.getMaleUrl()) && TextUtils.isEmpty(audioInfo.getFemaleUrl());
            if (isIdEmpty || isTitleEmpty || isBothVoiceUrlEmpty) {
                continue;
            }
            filtratedAudioList.add(audioInfo);
        }
        return filtratedAudioList;
    }
    
    
    // ---------- ---------- ---------- Getter ---------- ---------- ----------
    
    public int getCurrentIndex() {
        return currentIndex;
    }
    
    public List<AudioModel> getAudioList() {
        return audioList;
    }
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public AudioListModel setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
        return this;
    }
    
    public AudioListModel setAudioList(List<AudioModel> audioList) {
        this.audioList = filtrateAudioList(audioList);
        return this;
    }
}
