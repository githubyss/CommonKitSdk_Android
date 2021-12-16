package com.githubyss.mobile.common.kit.manager.audio.player

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.githubyss.mobile.common.kit.constant.Constants


/**
 * AudioPlayService
 * 音频播放服务
 * 总控制service，其他界面，通过binder，广播等形式，通知关闭打开悬浮框，和音乐切换等。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/24 14:07:15
 */
class AudioPlayService : Service() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        var instance: AudioPlayService? = null
            private set
        
        private val TAG: String = AudioPlayService::class.java.simpleName
    }
    
    private var receiver: MyBroadcastReceiver? = null
    private var closeMusic = true
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    
    /** ********** ********** ********** Override ********** ********** ********** */
    
    override fun onBind(intent: Intent?): IBinder? {
        return MusicBinder()
    }
    
    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY //不让杀死自启动
    }
    
    override fun onCreate() {
        initView()
        initData()
        initLocalBroadcastReceiver()
        super.onCreate()
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // if (floatView != null)
        //     floatView.remove();
        if (receiver != null && baseContext != null) LocalBroadcastManager.getInstance(baseContext)
            .unregisterReceiver(receiver!!)
        // if (mMusicNotification != null)
        //     mMusicNotification.destroy();
        if (closeMusic) AudioPlayManager.INSTANCE.destroy()
        closeMusic = true
        // mMusicNotification = null;
        receiver = null
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    
    /** ********** ********** ********** Private ********** ********** ********** */
    
    private fun initView() {
        // if (floatView == null) {
        //     floatView = new FloatView(getBaseContext());
        //     mMusicView = new MusicViewMini(getBaseContext());
        //     floatView.addContentView(mMusicView);//悬浮框不依赖与service，可以更新UI
        //     mMusicNotification = new MusicNotification(getBaseContext());
        // }
    }
    
    private fun initData() {
        // mMusicView.setMusicInterface(new MusicInterface() {
        //     @Override
        //     public void onStateChanged(AudioState audioState) {
        //         if (mMusicNotification != null && floatView != null && !floatView.isShowing())
        //             mMusicNotification.showNotify();
        // }
        //
        // @Override
        // public void onPlayProgress(int CurrentPosition) {
        //
        // }
        //
        // @Override
        // public void onBufferingUpdateProgress(int percent) {
        //
        // }
        // });
    }
    
    private fun initLocalBroadcastReceiver() {
        // mMusicNotification.initBroadcastReceiver();
        val intentFilter = IntentFilter()
        intentFilter.addAction(Constants.INTENT_ACTION_IS_FOREGROUND)
        intentFilter.addAction(Constants.INTENT_ACTION_CLOSE_FLOAT)
        receiver = MyBroadcastReceiver()
        // 注册广播接收器, LocalBroadcastManager.getInstance(getBaseContext())接不到通知栏的广播
        LocalBroadcastManager.getInstance(baseContext)
            .registerReceiver(receiver!!, intentFilter)
    }
    
    
    /** ********** ********** ********** Class ********** ********** ********** */
    
    // client 可以通过Binder获取Service实例
    class MusicBinder : Binder() {
        val service: AudioPlayService?
            get() = instance
    }
    
    private class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == Constants.INTENT_ACTION_IS_FOREGROUND) {
                // if (floatView != null && mMusicNotification != null) {
                //     if (intent.getBooleanExtra("isForeground", true)) { // 程序是否进入后台
                //         if(!floatView.isShowing()) {
                //             floatView.show();
                //             mMusicNotification.cancel();
                //         }
                //     } else {
                //         if(floatView.isShowing()) {
                //             floatView.hide();
                //             mMusicNotification.showNotify();
                //         }
                //     }
                // }
            }
            else if (action == Constants.INTENT_ACTION_CLOSE_FLOAT) {
                instance?.closeMusic = false
                instance?.stopSelf()
            }
        }
    }
}
