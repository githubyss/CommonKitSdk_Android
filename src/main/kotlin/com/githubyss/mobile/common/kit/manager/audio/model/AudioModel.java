package com.githubyss.mobile.common.kit.manager.audio.model;

import android.text.TextUtils;

import com.githubyss.mobile.common.kit.manager.audio.enumeration.VoiceType;

import java.io.Serializable;


/**
 * MusicModel
 * <Description> 音频信息数据结构
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/10 10:59:10
 */
public class AudioModel implements Serializable {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private String id;
    private String title;
    private String author;
    private String icon;
    private String url;
    private String maleUrl;
    private String femaleUrl;
    private VoiceType voiceType;
    private boolean isHasBothVoiceUrl;
    private boolean isPlaying;
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    public AudioModel() {
    }
    
    public AudioModel(String id, String title, String author, String icon, String url, String maleUrl, String femaleUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.icon = icon;
        this.url = url;
        this.maleUrl = maleUrl;
        this.femaleUrl = femaleUrl;
        this.voiceType = AudioListModel.savedVoiceType;
        this.isHasBothVoiceUrl = !TextUtils.isEmpty(this.maleUrl) && !TextUtils.isEmpty(this.femaleUrl);
        processVoiceAndUrl();
    }
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    public void processVoiceAndUrl() {
        if (!TextUtils.isEmpty(this.maleUrl) && TextUtils.isEmpty(this.femaleUrl)) {
            this.voiceType = VoiceType.MALE;
            this.url = this.maleUrl;
            return;
        }
        if (!TextUtils.isEmpty(this.femaleUrl) && TextUtils.isEmpty(this.maleUrl)) {
            this.voiceType = VoiceType.FEMALE;
            this.url = this.femaleUrl;
            return;
        }
        if (TextUtils.isEmpty(this.maleUrl) && TextUtils.isEmpty(this.femaleUrl)) {
            this.voiceType = AudioListModel.savedVoiceType;
            this.url = "";
            return;
        }
        if (!TextUtils.isEmpty(this.maleUrl) && !TextUtils.isEmpty(this.femaleUrl)) {
            this.voiceType = AudioListModel.savedVoiceType;
            switch (this.voiceType) {
                case MALE:
                    this.url = this.maleUrl;
                    break;
                case FEMALE:
                    this.url = this.femaleUrl;
                    break;
                default:
                    break;
            }
            return;
        }
    }
    
    
    // ---------- ---------- ---------- Getter ---------- ---------- ----------
    
    public String getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getMaleUrl() {
        return maleUrl;
    }
    
    public String getFemaleUrl() {
        return femaleUrl;
    }
    
    public VoiceType getVoiceType() {
        return voiceType;
    }
    
    public boolean isHasBothVoiceUrl() {
        return isHasBothVoiceUrl;
    }
    
    public boolean isPlaying() {
        return isPlaying;
    }
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    public AudioModel setIcon(String icon) {
        this.icon = icon;
        return this;
    }
    
    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
}
