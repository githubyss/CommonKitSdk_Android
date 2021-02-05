package com.githubyss.mobile.common.kit.manager.audio.player;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.githubyss.mobile.common.kit.manager.audio.constant.Constant;


/**
 * 88396251
 * 2018/5/23
 * 总控制service，其他界面，通过binder，广播等形式，通知关闭打开悬浮框，和音乐切换等。
 */

public class AudioPlayService extends Service {
    // private FloatView floatView;
    // 初始化广播接收器，只接受本地广播
    private MyBroadcastReceiver receviver = null;
    private boolean closeMusic = true;
    // private MusicViewMini mMusicView;
    // private MusicNotification mMusicNotification;

    @Override
    public IBinder onBind(Intent intent) {
        return new AudioPlayService.MusicBinder();
    }

    //client 可以通过Binder获取Service实例
    public class MusicBinder extends Binder {
        public AudioPlayService getService() {
            return AudioPlayService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        initView();
        initData();
        initLocalBroadcastReceiver();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_NOT_STICKY;//不让杀死自启动
    }

    private void initView() {
        // if (floatView == null) {
        //     floatView = new FloatView(getBaseContext());
        //     mMusicView = new MusicViewMini(getBaseContext());
        //     floatView.addContentView(mMusicView);//悬浮框不依赖与service，可以更新UI
        //     mMusicNotification = new MusicNotification(getBaseContext());
        // }
    }

    private void initData() {

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

    private void initLocalBroadcastReceiver() {
        // mMusicNotification.initBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.INTENT_ACTION_IS_FOREGROUND);
        intentFilter.addAction(Constant.INTENT_ACTION_CLOSE_FLOAT);
        receviver = new MyBroadcastReceiver();
        // 注册广播接收器, LocalBroadcastManager.getInstance(getBaseContext())接不到通知栏的广播
        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(receviver, intentFilter);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constant.INTENT_ACTION_IS_FOREGROUND)) {
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
            } else if (action.equals(Constant.INTENT_ACTION_CLOSE_FLOAT)) {
                closeMusic = false;
                stopSelf();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // if (floatView != null)
        //     floatView.remove();
        if (receviver != null && getBaseContext() != null)
            LocalBroadcastManager.getInstance(getBaseContext()).unregisterReceiver(receviver);
        // if (mMusicNotification != null)
        //     mMusicNotification.destroy();
        if (closeMusic)
            AudioPlayManager.getInstance().destroy();
        closeMusic = true;
        // mMusicNotification = null;
        receviver = null;
    }
}

