package com.githubyss.mobile.common.kit.manager.audio_player.player

import android.content.Context
import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.media.MediaPlayer
import android.os.Handler
import android.os.Message
import com.githubyss.mobile.common.kit.manager.audio_player.enumeration.AudioState
import com.githubyss.mobile.common.kit.manager.audio_player.enumeration.VoiceType
import com.githubyss.mobile.common.kit.manager.audio_player.model.AudioListModel
import com.githubyss.mobile.common.kit.manager.audio_player.model.AudioModel
import com.githubyss.mobile.common.kit.manager.audio_player.util.ProgressTextUtils


/**
 * AudioPlayManager
 * 音频播放管理器
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/24 14:07:21
 */
class AudioPlayManager private constructor() {
    
    /** ****************************** Properties ****************************** */
    
    companion object {
        val INSTANCE: AudioPlayManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { AudioPlayManager() }
        
        private val TAG: String = AudioPlayManager::class.java.simpleName
        private val WHAT_REFRESH = 0x268
        private val MAX_PROGRESS = 100
    }
    
    var MaxProgress = MAX_PROGRESS
    
    @AudioState
    var audioState = AudioState.END
        private set
    
    var audioListModel: AudioListModel? = null
    var audioPlayListener: AudioPlayListener? = null
    
    private var mediaPlayer: MediaPlayer? = null
    private var audioManager: AudioManager? = null
    private var audioFocusChangeListener: OnAudioFocusChangeListener? = null
    
    /** 8.0新方法 */
    // private AudioFocusRequest mFocusRequest;
    
    /** 音量  */
    private var originalVol = 0
    
    /** 缓存进度  */
    var updateProgress = 0
    
    /** 是否是手动停止  */
    private var isManualStop = false
    
    /** 是否重新开始  */
    private var isPlayRestart = false
    
    /** 是否循环播放  */
    var isLoop = false
    
    private val handler: Handler? = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                WHAT_REFRESH -> {
                    sendEmptyMessageDelayed(WHAT_REFRESH, 200)
                    if (mediaPlayer != null && mediaPlayer?.isPlaying ?: return && audioPlayListener != null) {
                        audioPlayListener?.onPlayProgress(mediaPlayer?.currentPosition ?: return)
                    }
                }
            }
        }
    }
    
    
    /** ****************************** Constructors ****************************** */
    
    init {
        setAudioState(AudioState.STOP)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    /**
     * 外部调用，第一次开始播放
     */
    fun play(context: Context?) {
        // CookieHandlerUtil.getInstance().syncCookie();
        stop()
        isPlayRestart = true
        audioFocusInit(context)
    }
    
    /**
     * 开始播放，当暂停或准备好后才行
     */
    fun start() {
        if (audioListModel == null) {
            destroy()
            return
        }
        requestFocus()
    }
    
    /** 暂停  */
    fun pause(): Boolean {
        if (audioListModel == null) {
            destroy()
            return false
        }
        if (audioState == AudioState.PLAYING && mediaPlayer != null) {
            isManualStop = true
            mediaPlayer?.pause()
            handler?.removeMessages(WHAT_REFRESH)
            setAudioState(AudioState.PAUSE)
            return true
        }
        return false
    }
    
    /**
     * 上一首歌
     */
    fun previous(): Int {
        if (!isLoop && audioListModel != null && (audioListModel?.currentIndex ?: return -1) <= 0) {
            return -1
        }
        if (mediaPlayer == null) {
            startFromBegin()
            return 1
        }
        if (audioState == AudioState.PLAYING) {
            stop()
        }
        audioListModel?.currentIndex = (audioListModel?.currentIndex ?: return -1) - 1
        if ((audioListModel?.currentIndex ?: return -1) < 0) {
            audioListModel?.currentIndex = (audioListModel?.audioList?.size ?: return -1) - 1
        }
        audioPrepare()
        return 0
    }
    
    /**
     * 下一首歌
     */
    operator fun next() {
        if (!isLoop && audioListModel != null && audioListModel?.currentIndex ?: return >= (audioListModel?.audioList?.size ?: return) - 1) {
            return
        }
        if (mediaPlayer == null) {
            startFromBegin()
            return
        }
        if (audioState == AudioState.PLAYING) {
            stop()
        }
        audioListModel?.currentIndex = (audioListModel?.currentIndex ?: return) + 1
        if (audioListModel?.currentIndex ?: return >= audioListModel?.audioList?.size ?: return) {
            audioListModel?.currentIndex = 0
        }
        setAudioState(AudioState.SWITCH)
        audioPrepare()
    }
    
    /**
     * 切换音频类型
     */
    fun switchVoice(): Boolean {
        // if (mediaPlayer == null) {
        //     startFromBegin();
        //     return false;
        // }
        if (getCurrentAudio() == null) {
            destroy()
            return false
        }
        if (!(getCurrentAudio()?.isHasBothVoiceUrl ?: return false)) {
            return false
        }
        when (AudioListModel.savedVoiceType) {
            VoiceType.MALE -> AudioListModel.savedVoiceType = VoiceType.FEMALE
            VoiceType.FEMALE -> AudioListModel.savedVoiceType = VoiceType.MALE
        }
        audioListModel?.reprocessAudioModel()
        if (audioState == AudioState.PLAYING) {
            stop()
        }
        setAudioState(AudioState.SWITCH)
        audioPrepare()
        return true
    }
    
    /**
     * 停止
     * 注意停止后得重新 prepare，无法直接 start
     */
    fun stop(): Boolean {
        if (audioState != AudioState.STOP && mediaPlayer != null) {
            isManualStop = true
            mediaPlayer?.stop()
            handler?.removeMessages(WHAT_REFRESH)
            setAudioState(AudioState.STOP)
            return true
        }
        return false
    }
    
    /**
     * 进度条跳转
     */
    fun seekTo(progress: Int) {
        if (mediaPlayer != null) {
            mediaPlayer?.seekTo(progress)
        }
    }
    
    fun destroy() {
        end()
        // 最后清空数据
        mediaPlayer = null
        audioListModel = null
        MaxProgress = MAX_PROGRESS
        audioManager = null
        audioFocusChangeListener = null
        updateProgress = 0
        originalVol = 0
        isManualStop = false
        isLoop = false
        isPlayRestart = false
        handler?.removeMessages(WHAT_REFRESH)
    }
    
    /**
     * 音频焦点监听返回
     */
    fun audioFocusChange(focusChange: Int, asynchronous: Boolean) {
        when (focusChange) {
            /** 暂时失去 Audio Focus，并会很快再次获得。 */
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                // e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
                if (pause()) {
                    isManualStop = false
                }
            }
            /** 获得了 Audio Focus,重新获取焦点的时候回调 */
            AudioManager.AUDIOFOCUS_GAIN -> {
                /** 申请成功，处理相同  */
                // AudioManager.AUDIOFOCUS_REQUEST_GRANTED:
                // e(TAG, "AUDIOFOCUS_GAIN");
                // 手动暂停或停止的，不需要重启。
                if (asynchronous && isManualStop && (audioState == AudioState.PAUSE || audioState == AudioState.STOP)) {
                    return
                }
                startSelf()
                if (originalVol != 0 && audioManager != null) {
                    audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, originalVol, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE)
                    originalVol = 0
                }
            }
            /** 失去了 Audio Focus，并将会持续很长的时间。 */
            AudioManager.AUDIOFOCUS_LOSS -> {
                // e(TAG, "AUDIOFOCUS_LOSS");
                if (pause()) {
                    // 如果本来就在暂停状态，说明本身已经停止，所以还是手动停止的
                    isManualStop = false
                }
            }
            /** 暂时失去 AudioFocus，但是可以继续播放，不过要在降低音量 */
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK -> {
                // e(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                if (audioManager != null) {
                    originalVol = audioManager?.getStreamVolume(AudioManager.STREAM_MUSIC) ?: return
                    audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, originalVol / 2, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE)
                }
            }
            /** 申请失败 */
            AudioManager.AUDIOFOCUS_REQUEST_FAILED -> {
                // e(TAG, "AUDIOFOCUS_REQUEST_FAILED");
                // if (EPApp.mContext != null) {
                //     ToastUtil.showToast(EPApp.mContext, "其他应用正在占用音频资源");
                // }
            }
            /** 申请未获取到焦点，但是 setAcceptsDelayedFocusGain 为 true,所以没有返回申请失败，只是在等待，8.0特性 */
            AudioManager.AUDIOFOCUS_REQUEST_DELAYED -> {
                // e(TAG, "AUDIOFOCUS_REQUEST_DELAYED");
                // if (EPApp.mContext != null) {
                //     ToastUtil.showToast(EPApp.mContext, "其他应用正在占用音频资源，请稍等");
                // }
            }
        }
    }
    
    
    /** ****************************** Private ****************************** */
    
    private fun audioInit(): Boolean {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer?.setOnPreparedListener {
                MaxProgress = mediaPlayer?.duration ?: return@setOnPreparedListener
                setAudioState(AudioState.READY)
                startSelf()
            }
            mediaPlayer?.setOnCompletionListener { next() }
            mediaPlayer?.setOnBufferingUpdateListener { mp, percent ->
                if (audioPlayListener != null) {
                    updateProgress = MaxProgress * percent / 100
                    audioPlayListener?.onBufferingUpdateProgress(updateProgress)
                }
            }
            mediaPlayer?.setOnErrorListener { mp, what, extra ->
                when (extra) {
                    -1003 -> {
                        stop()
                        isPlayRestart = true
                    }
                }
                
                // e(TAG, "OnError what=" + what + " extra=" + extra);
                // 返回false会继续回调onCompletion，true不会。
                true
            }
            return true
        }
        return false
    }
    
    /**
     * 刷新音频状态
     *
     * @param
     * @return
     */
    private fun setAudioState(@AudioState audioState: Int) {
        if (this.audioState == audioState) return
        this.audioState = audioState
        audioPlayListener?.onStateChanged(audioState)
    }
    
    /**
     * 获取焦点
     * 屏蔽8.0方法，升级后打开
     */
    private fun requestFocus() {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //     if (mFocusRequest != null && mAudioManager != null) {
        //         AudioFocusChange(mAudioManager.requestAudioFocus(mFocusRequest), false);
        //     }
        // } else {
        if (audioFocusChangeListener != null && audioManager != null) {
            audioFocusChange(audioManager?.requestAudioFocus(audioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN) ?: return, false)
        }
        // }
    }
    
    /**
     * 释放焦点
     * 屏蔽8.0方法，升级后打开
     */
    private fun abandonFocus() {
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //     if (mFocusRequest != null && mAudioManager != null) {
        //         mAudioManager.abandonAudioFocusRequest(mFocusRequest);
        //     }
        // } else {
        if (audioFocusChangeListener != null && audioManager != null) {
            audioManager?.abandonAudioFocus(audioFocusChangeListener)
        }
        // }
    }
    
    /**
     * 初始化焦点获取
     */
    private fun audioFocusInit(context: Context?) {
        audioManager = context?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioFocusChangeListener = OnAudioFocusChangeListener { focusChange -> audioFocusChange(focusChange, true) }
        // 请求焦点
        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        //     AudioAttributes mPlaybackAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
        //     mFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN).setAudioAttributes(mPlaybackAttributes).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(myFocusListener, handler).build();
        // }
        requestFocus()
    }
    
    /**
     * 当前歌曲重新开始播放
     */
    private fun startFromBegin() {
        if (audioState != AudioState.PREPARE) {
            audioPrepare()
        }
    }
    
    private fun startSelf() {
        if (audioListModel == null) {
            destroy()
            return
        }
        if (mediaPlayer == null || isPlayRestart) {
            startFromBegin()
            isPlayRestart = false
            return
        }
        if (audioState == AudioState.PAUSE || audioState == AudioState.READY) {
            mediaPlayer?.start()
            handler?.sendEmptyMessage(WHAT_REFRESH)
            setAudioState(AudioState.PLAYING)
        }
    }
    
    /**
     * 每一首歌开始前的准备工作
     */
    private fun audioPrepare() {
        if (audioListModel == null || audioListModel?.audioList?.size ?: return == 0) {
            return
        }
        if (!audioInit()) {
            mediaPlayer?.reset()
        }
        try {
            mediaPlayer?.setDataSource(audioListModel?.audioList?.get(audioListModel?.currentIndex ?: return)?.url)
        }
        catch (e: Exception) {
            // e(TAG, e.getStackTrace().toString() + "");
            return
        }
        setAudioState(AudioState.START)
        mediaPlayer?.prepareAsync()
        setAudioState(AudioState.PREPARE)
    }
    
    private fun end() {
        if (mediaPlayer != null) {
            abandonFocus()
            mediaPlayer?.stop()
            mediaPlayer?.release()
            setAudioState(AudioState.END)
        }
    }
    
    fun getCurrentIndex(): Int {
        return audioListModel?.currentIndex ?: 0
    }
    
    fun getAudioList(): List<AudioModel?>? {
        return if (audioListModel == null) null else audioListModel?.audioList
    }
    
    fun getCurrentAudio(): AudioModel? {
        return if (audioListModel == null) null else audioListModel?.currentIndex?.let { audioListModel?.audioList?.get(it) }
    }
    
    fun getCurrentPosition(): Int {
        return mediaPlayer?.currentPosition ?: 0
    }
    
    fun getCurrentTime(): String? {
        return ProgressTextUtils.getProgressText(mediaPlayer?.currentPosition?.toLong() ?: return "")
    }
    
    fun getDurationTime(): String? {
        return ProgressTextUtils.getProgressText(mediaPlayer?.duration?.toLong() ?: return "")
    }
}
