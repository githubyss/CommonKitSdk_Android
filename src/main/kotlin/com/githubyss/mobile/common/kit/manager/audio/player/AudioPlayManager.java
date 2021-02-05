package com.githubyss.mobile.common.kit.manager.audio.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;

import com.githubyss.mobile.common.kit.manager.audio.enumeration.AudioState;
import com.githubyss.mobile.common.kit.manager.audio.enumeration.VoiceType;
import com.githubyss.mobile.common.kit.manager.audio.model.AudioListModel;
import com.githubyss.mobile.common.kit.manager.audio.model.AudioModel;
import com.githubyss.mobile.common.kit.manager.audio.util.ProgressTextUtils;

import java.util.List;

import static com.githubyss.mobile.common.kit.manager.audio.enumeration.AudioState.*;


/**
 * AudioPlayManager
 * <Description> 音频播放管理器
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/11 16:42:04
 */
public class AudioPlayManager {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private static final String TAG = "AudioPlayManager";
    
    private static volatile AudioPlayManager instance = null;
    
    private static final int WHAT_REFRESH = 0x268;
    private static final int MAX_PROGRESS = 100;
    private static final int AUDIOFOCUS_REQUEST_DELAYED = 2;
    
    public int MaxProgress = MAX_PROGRESS;
    
    private AudioState audioState;
    private AudioListModel audioListModel;
    private MediaPlayer mediaPlayer;
    private AudioPlayListener audioPlayListener;
    
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    
    
    /** 8.0新方法 */
    // private AudioFocusRequest mFocusRequest;
    
    /** 音量 */
    private int originalVol;
    /** 缓存进度 */
    private int updateProgress = 0;
    /** 是否是手动停止 */
    private boolean isManualStop = false;
    /** 是否循环播放 */
    public boolean isLoop = false;
    /** 是否重新开始 */
    private boolean isPlayRestart = false;
    
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case WHAT_REFRESH:
                    handler.sendEmptyMessageDelayed(WHAT_REFRESH, 200);
                    if (mediaPlayer != null && mediaPlayer.isPlaying() && audioPlayListener != null) {
                        audioPlayListener.onPlayProgress(mediaPlayer.getCurrentPosition());
                    }
                    break;
                default:
                    break;
            }
        }
    };
    
    
    // ---------- ---------- ---------- Constructors ---------- ---------- ----------
    
    private AudioPlayManager() {
        audioState = STOP;
    }
    
    public static AudioPlayManager getInstance() {
        if (instance == null) {
            synchronized (AudioPlayManager.class) {
                if (instance == null) {
                    instance = new AudioPlayManager();
                }
            }
        }
        return instance;
    }
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    /**
     * 外部调用，第一次开始播放
     */
    public void play(Context context) {
        // CookieHandlerUtil.getInstance().syncCookie();
        stop();
        isPlayRestart = true;
        audioFocusInit(context);
    }
    
    /**
     * 开始播放，当暂停或准备好后才行
     */
    public void start() {
        if (audioListModel == null) {
            destroy();
            return;
        }
        requestFocus();
    }
    
    /** 暂停 */
    public boolean pause() {
        if (audioListModel == null) {
            destroy();
            return false;
        }
        if (audioState == PLAYING && mediaPlayer != null) {
            isManualStop = true;
            mediaPlayer.pause();
            handler.removeMessages(WHAT_REFRESH);
            setAudioState(PAUSE);
            return true;
        }
        return false;
    }
    
    /**
     * 上一首歌
     */
    public int previous() {
        if (!isLoop && audioListModel != null && audioListModel.getCurrentIndex() <= 0) {
            return -1;
        }
        if (mediaPlayer == null) {
            startFromBegin();
            return 1;
        }
        if (audioState == PLAYING) {
            stop();
        }
        audioListModel.setCurrentIndex(audioListModel.getCurrentIndex() - 1);
        if (audioListModel.getCurrentIndex() < 0) {
            audioListModel.setCurrentIndex(audioListModel.getAudioList().size() - 1);
        }
        audioPrepare();
        return 0;
    }
    
    /**
     * 下一首歌
     */
    public void next() {
        if (!isLoop && audioListModel != null && audioListModel.getCurrentIndex() >= audioListModel.getAudioList().size() - 1) {
            return;
        }
        if (mediaPlayer == null) {
            startFromBegin();
            return;
        }
        if (audioState == PLAYING) {
            stop();
        }
        audioListModel.setCurrentIndex(audioListModel.getCurrentIndex() + 1);
        if (audioListModel.getCurrentIndex() >= audioListModel.getAudioList().size()) {
            audioListModel.setCurrentIndex(0);
        }
        setAudioState(SWITCH);
        
        audioPrepare();
    }
    
    /**
     * 切换音频类型
     */
    public boolean switchVoice() {
        // if (mediaPlayer == null) {
        //     startFromBegin();
        //     return false;
        // }
        if (getCurrentAudio() == null) {
            destroy();
            return false;
        }
        if (!getCurrentAudio().isHasBothVoiceUrl()) {
            return false;
        }
        
        switch (AudioListModel.savedVoiceType) {
            case MALE:
                AudioListModel.savedVoiceType = VoiceType.FEMALE;
                break;
            case FEMALE:
                AudioListModel.savedVoiceType = VoiceType.MALE;
                break;
            default:
                break;
        }
        audioListModel.reprocessAudioModel();
        if (audioState == PLAYING) {
            stop();
        }
        setAudioState(SWITCH);
        audioPrepare();
        return true;
    }
    
    /**
     * 停止
     * 注意停止后得重新 prepare，无法直接 start
     */
    public boolean stop() {
        if (audioState != STOP && mediaPlayer != null) {
            isManualStop = true;
            mediaPlayer.stop();
            handler.removeMessages(WHAT_REFRESH);
            setAudioState(STOP);
            return true;
        }
        return false;
    }
    
    /**
     * 进度条跳转
     */
    public void seekTo(int progress) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(progress);
        }
    }
    
    public void destroy() {
        end();
        // 最后清空数据
        mediaPlayer = null;
        audioListModel = null;
        MaxProgress = MAX_PROGRESS;
        audioManager = null;
        audioFocusChangeListener = null;
        updateProgress = 0;
        originalVol = 0;
        isManualStop = false;
        isLoop = false;
        isPlayRestart = false;
        if (handler != null) {
            handler.removeMessages(WHAT_REFRESH);
        }
    }
    
    /**
     * 音频焦点监听返回
     */
    public void audioFocusChange(int focusChange, boolean asynchronous) {
        switch (focusChange) {
            /** 暂时失去 Audio Focus，并会很快再次获得。 */
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                // LogUtils.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
                if (pause()) {
                    isManualStop = false;
                }
                break;
            /** 获得了 Audio Focus,重新获取焦点的时候回调 */
            case AudioManager.AUDIOFOCUS_GAIN:
                /** 申请成功，处理相同 */
                // AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                // LogUtils.e(TAG, "AUDIOFOCUS_GAIN");
                //手动暂停或停止的，不需要重启。
                if (asynchronous && isManualStop && (audioState == PAUSE || audioState == STOP)) {
                    return;
                }
                startSelf();
                if (originalVol != 0 && audioManager != null) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVol, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                    originalVol = 0;
                }
                break;
            /** 失去了 Audio Focus，并将会持续很长的时间。 */
            case AudioManager.AUDIOFOCUS_LOSS:
                // LogUtils.e(TAG, "AUDIOFOCUS_LOSS");
                if (pause()) {//如果本来就在暂停状态，说明本身已经停止，所以还是手动停止的
                    isManualStop = false;
                }
                break;
            /** 暂时失去 AudioFocus，但是可以继续播放，不过要在降低音量 */
            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                // LogUtils.e(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                if (audioManager != null) {
                    originalVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVol / 2, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                }
                break;
            /** 申请失败 */
            case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                // LogUtils.e(TAG, "AUDIOFOCUS_REQUEST_FAILED");
                // if (EPApp.mContext != null) {
                //     ToastUtil.showMessage(EPApp.mContext, "其他应用正在占用音频资源");
                // }
                break;
            /** 申请未获取到焦点，但是 setAcceptsDelayedFocusGain 为 true,所以没有返回申请失败，只是在等待，8.0特性 */
            case AUDIOFOCUS_REQUEST_DELAYED:
                // LogUtils.e(TAG, "AUDIOFOCUS_REQUEST_DELAYED");
                // if (EPApp.mContext != null) {
                //     ToastUtil.showMessage(EPApp.mContext, "其他应用正在占用音频资源，请稍等");
                // }
                break;
            default:
                break;
        }
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    private boolean audioInit() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    MaxProgress = mediaPlayer.getDuration();
                    setAudioState(READY);
                    startSelf();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    next();
                }
            });
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    if (audioPlayListener != null) {
                        updateProgress = MaxProgress * percent / 100;
                        audioPlayListener.onBufferingUpdateProgress(updateProgress);
                    }
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    switch (extra) {
                        case -1003://初始化的时候连接异常。错误码未公开
                            stop();
                            isPlayRestart = true;
                            break;
                    }
                    
                    // LogUtils.e(TAG, "OnError what=" + what + " extra=" + extra);
                    return true;//返回false会继续回调onCompletion，true不会。
                }
            });
            return true;
        }
        return false;
    }
    
    /**
     * 获取焦点
     * 屏蔽8.0方法，升级后打开
     */
    private void requestFocus() {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //     if (mFocusRequest != null && mAudioManager != null) {
        //         AudioFocusChange(mAudioManager.requestAudioFocus(mFocusRequest), false);
        //     }
        // } else {
        if (audioFocusChangeListener != null && audioManager != null) {
            audioFocusChange(audioManager.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN), false);
        }
        // }
    }
    
    /**
     * 释放焦点
     * 屏蔽8.0方法，升级后打开
     */
    private void abandonFocus() {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //     if (mFocusRequest != null && mAudioManager != null) {
        //         mAudioManager.abandonAudioFocusRequest(mFocusRequest);
        //     }
        // } else {
        if (audioFocusChangeListener != null && audioManager != null) {
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
        // }
    }
    
    /**
     * 初始化焦点获取
     */
    private void audioFocusInit(Context context) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                audioFocusChange(focusChange, true);
            }
        };
        // 请求焦点
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //     AudioAttributes mPlaybackAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
        //     mFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN).setAudioAttributes(mPlaybackAttributes).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(myFocusListener, handler).build();
        // }
        requestFocus();
    }
    
    /**
     * 当前歌曲重新开始播放
     */
    private void startFromBegin() {
        if (audioState != PREPARE) {
            audioPrepare();
        }
    }
    
    private void startSelf() {
        if (audioListModel == null) {
            destroy();
            return;
        }
        if (mediaPlayer == null || isPlayRestart) {
            startFromBegin();
            isPlayRestart = false;
            return;
        }
        if (audioState == PAUSE || audioState == READY) {
            mediaPlayer.start();
            handler.sendEmptyMessage(WHAT_REFRESH);
            setAudioState(PLAYING);
        }
    }
    
    /**
     * 每一首歌开始前的准备工作
     */
    private void audioPrepare() {
        if (audioListModel == null || audioListModel.getAudioList().size() == 0) {
            return;
        }
        if (!audioInit()) {
            mediaPlayer.reset();
        }
        try {
            mediaPlayer.setDataSource(audioListModel.getAudioList().get(audioListModel.getCurrentIndex()).getUrl());
        } catch (Exception e) {
            // LogUtils.e(TAG, e.getStackTrace().toString() + "");
            return;
        }
        
        setAudioState(START);
        mediaPlayer.prepareAsync();
        setAudioState(PREPARE);
    }
    
    private void end() {
        if (mediaPlayer != null) {
            abandonFocus();
            mediaPlayer.stop();
            mediaPlayer.release();
            setAudioState(AudioState.END);
        }
    }
    
    
    // ---------- ---------- ---------- Setter ---------- ---------- ----------
    
    /**
     * 当前状态
     */
    private void setAudioState(AudioState audioState) {
        if (this.audioState == audioState) {
            return;
        }
        this.audioState = audioState;
        if (audioPlayListener != null) {
            audioPlayListener.onStateChanged(audioState);
        }
    }
    
    public void setInfo(AudioListModel audioListModel) {
        this.audioListModel = audioListModel;
    }
    
    public void setMusicListener(AudioPlayListener audioPlayListener) {
        this.audioPlayListener = audioPlayListener;
    }
    
    
    // ---------- ---------- ---------- Getter ---------- ---------- ----------
    
    public AudioState getAudioState() {
        return audioState;
    }
    
    public int getCurrentIndex() {
        if (audioListModel == null) {
            return 0;
        }
        return audioListModel.getCurrentIndex();
    }
    
    public List<AudioModel> getAudioList() {
        if (audioListModel == null) {
            return null;
        }
        return audioListModel.getAudioList();
    }
    
    public AudioModel getCurrentAudio() {
        if (audioListModel == null) {
            return null;
        }
        return audioListModel.getAudioList().get(audioListModel.getCurrentIndex());
    }
    
    public int getCurrentPosition() {
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }
    
    public String getCurrentTime() {
        if (mediaPlayer != null) {
            return ProgressTextUtils.getProgressText(mediaPlayer.getCurrentPosition());
        }
        return "";
    }
    
    public String getDurationTime() {
        if (mediaPlayer != null) {
            return ProgressTextUtils.getProgressText(mediaPlayer.getDuration());
        }
        return "";
    }
    
    public int getUpdateProgress() {
        return updateProgress;
    }
}
