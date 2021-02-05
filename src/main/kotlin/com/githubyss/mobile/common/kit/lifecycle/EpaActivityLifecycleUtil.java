// package com.githubyss.mobile.common.kit.lifecycle;
//
// import android.app.Activity;
// import android.app.Application;
// import android.content.Intent;
// import android.os.Bundle;
// import android.os.SystemClock;
// import android.support.v4.content.LocalBroadcastManager;
// import android.text.TextUtils;
//
// import com.suning.mobile.epa.EPApp;
// import com.suning.mobile.epa.R;
// import com.suning.mobile.epa.account.common.StaticVariable;
// import com.suning.mobile.epa.account.net.HandlerLogonOperation;
// import com.suning.mobile.epa.audio.util.Constant;
// import com.suning.mobile.epa.common.IAutoLogonChannel;
// import com.suning.mobile.epa.config.ConfigEPA;
// import com.suning.mobile.epa.data.GestureSqliteOpenHelper;
// import com.suning.mobile.epa.data.UserAccountDAO;
// import com.suning.mobile.epa.kits.utils.ResUtil;
// import com.suning.mobile.epa.kits.utils.StringUtil;
// import com.suning.mobile.epa.kits.utils.ToastUtil;
// import com.suning.mobile.epa.kits.view.suspendview.SuspendViewBean;
// import com.suning.mobile.epa.kits.view.suspendview.SuspendViewController;
// import com.suning.mobile.epa.launcher.LauncherActivity;
// import com.suning.mobile.epa.paymentcode.main.PaymentCodeMainActivity;
// import com.suning.mobile.epa.statistic.CustomStatisticsProxy;
// import com.suning.mobile.epa.ui.init.SplashActivity;
// import com.suning.mobile.epa.ui.moreinfo.gesturepwd.GestureLogonActivity;
// import com.suning.mobile.epa.utils.log.LogUtils;
// import com.suning.mobile.epa.utils.timeTicker.ticker.AutoLogonTicker;
//
// import java.util.ArrayList;
// import java.util.List;
//
// /**
//  * Created by 15091170 on 2017/3/14.
//  */
//
// public final class EpaActivityLifecycleUtil implements Application.ActivityLifecycleCallbacks {
//
//     private final static String TAG = "EpaActivityLifecycleUtil";
//     // 锁屏延迟时间（毫秒）
//     private static final long LOCK_DELAY = 5 * 60 * 1000;
//     private static EpaActivityLifecycleUtil lifecycleUtil;
//     /**
//      * 应用是否处于前端
//      */
//     private boolean isForeground;
//
//     /**
//      * 应用当前展示的Activity
//      */
//     private Activity currentShowActivity;
//
//     private int foregroundCount;
//
//     private static Object lock = new Object();
//
//     private List<Activity> activityList;
//
//     // 手势计时是否触发
//     private boolean gestureTimerOpened = false;
//
//     // 离开应用时间
//     private long mUserLeaveTime = 0;
//
//     //切换后台后，自动登录时间
//     private long mAutoLogonLeaveTime = 0L;
//
//     //当前activity名称
//     String nativeActivityName = "";
//     //悬浮框是否被创建
//     boolean suspendViewOnCreatedFlag;
//     //小程序包名
//     private static String SMP_ACTIVITY_NAME = "com.suning.epa.sminip.SNFMPActivity";
//
//     private EpaActivityLifecycleUtil(){
//         foregroundCount = 0;
//         isForeground = false;
//         activityList = new ArrayList<>();
//     }
//
//     public long getUserLeaveTime() {
//         return mUserLeaveTime;
//     }
//
//     public void setUserLeaveTime(long userLeaveTime) {
//         mUserLeaveTime = userLeaveTime;
//     }
//
//     public static EpaActivityLifecycleUtil getInstance(){
//         if(lifecycleUtil ==null){
//             synchronized (lock){
//                 if(lifecycleUtil == null){
//                     lifecycleUtil = new EpaActivityLifecycleUtil();
//                 }
//             }
//         }
//         return lifecycleUtil;
//     }
//
//     public boolean isForeground() {
//         return isForeground;
//     }
//
//     public Activity getCurrentShowActivity() {
//         return currentShowActivity;
//     }
//
//     public String getCurrentShowActivityFullName() {
//         if(currentShowActivity == null){
//             return "";
//         }else{
//             String componentName = currentShowActivity.getPackageName();
//             String name = currentShowActivity.getLocalClassName();
//             return componentName + "." + name;
//         }
//     }
//
//     public void setGestureTimerOpened(boolean gestureTimerOpened) {
//         this.gestureTimerOpened = gestureTimerOpened;
//     }
//
//     @Override
//     public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//         //应用放置后台，内存回收后，重新启动应用
//         if(activity != null && !(activity instanceof SplashActivity) && EPApp.getApp().isColdStart && savedInstanceState != null){
//             LogUtils.d(TAG,"onActivityCreated: savedInstanceState"+activity.getClass().getSimpleName() +"  isForeground:"+isForeground);
//             //恢复到最底层页面，进行跳转到Splash
//             if(activity instanceof LauncherActivity) {
//                 Intent intent = new Intent(activity, SplashActivity.class);
//                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                 activity.startActivity(intent);
//             }
//             activity.finish();
//             StaticVariable.IS_EXIT_APPLICATION = true;
//             return;
//         }
//         currentShowActivity = activity;
//         activityList.add(activity);
//         if (!TextUtils.equals(activity.getClass().getName(), SMP_ACTIVITY_NAME)) {
//             if (SuspendViewController.Companion.getSington().isNativeShow() &&
//                     !SuspendViewController.Companion.getSington().getShowStatus() &&
//                     TextUtils.isEmpty(nativeActivityName)) {
//                 nativeActivityName = activity.getClass().getName();
//                 SuspendViewBean bean = SuspendViewController.Companion.getSington().getSuspendViewBean();
//                 LogUtils.d(TAG, "addView SuspendView");
//                 CustomStatisticsProxy.recordLogStatus("suspendView", "addView");
//                 SuspendViewController.Companion.getSington().addView(activity);
//                 if (bean != null) {
//                     SuspendViewController.Companion.getSington().setForegroundCount(0);
//                     LogUtils.d(TAG, "show SuspendView");
//                     CustomStatisticsProxy.recordLogStatus("suspendView", "addView");
//                     SuspendViewController.Companion.getSington().show(bean.getCountDownTime());
//                 }
//                 suspendViewOnCreatedFlag = true;
//             }
//         }
//         LogUtils.d(TAG,"onActivityCreated:"+activity.getClass().getSimpleName() +"  isForeground:"+isForeground );
//     }
//
//
//     @Override
//     public void onActivityStarted(Activity activity) {
//         foregroundCount ++;
//         LogUtils.d(TAG,"onActivityStarted:"+activity.getClass().getSimpleName()+"  isForeground:"+isForeground);
//     }
//
//     @Override
//     public void onActivityResumed(Activity activity) {
//         isForeground = true;
//         sendBroadcast(activity);
//         currentShowActivity = activity;
//         if (gestureTimerOpened) {
//             gestureTimerOpened = false;
//             long userBackTime = SystemClock.elapsedRealtime();
//             if (userBackTime - mUserLeaveTime > LOCK_DELAY && EPApp.getApp().isLogon()) {
//                 mUserLeaveTime = userBackTime;
//                 jumpToGestureLogonActivity(activity);
//             }
//         }
//         //如果离开时间不是0，说明从后台切换过来的，当前后台切换时间大于自动登录间隔时间，做一次自动登录;
//         //启动页面不做该操作
//         if(mAutoLogonLeaveTime != 0 && activity != null && !(activity instanceof SplashActivity)){
//             if(SystemClock.elapsedRealtime() - mAutoLogonLeaveTime > AutoLogonTicker.AUTO_DELAY){
//                 if (StaticVariable.getUserLogonInfo() != null) {
//                     HandlerLogonOperation.getInstance().autoLogon(IAutoLogonChannel.CHANNEL_AUTOLOGON_SERVICE);
//                 }
//             }
//         }
//         //将离开时间还原
//         mAutoLogonLeaveTime = 0L;
//         LogUtils.d(TAG,"onActivityResumed:"+activity.getClass().getSimpleName()+"  isForeground:"+isForeground);
//         // 发送埋点数据
//         try {
//             CustomStatisticsProxy.onResumeForCloudytrace(activity, activity.getClass().getName());
//         } catch (Exception e) {
//             LogUtils.logException(e);
//         }
//         if (!TextUtils.equals(activity.getClass().getName(), SMP_ACTIVITY_NAME)) {
//             if (TextUtils.equals(activity.getClass().getName(), nativeActivityName) &&
//                     SuspendViewController.Companion.getSington().getShowStatus()) {
//                 if (suspendViewOnCreatedFlag) {
//                     suspendViewOnCreatedFlag = false;
//                     return;
//                 }
//                 LogUtils.d(TAG, "reshow SuspendView");
//                 CustomStatisticsProxy.recordLogStatus("suspendView", "reshow");
//                 SuspendViewController.Companion.getSington().reshow();
//             }
//         }
//     }
//
//     @Override
//     public void onActivityPaused(Activity activity) {
//         if (!TextUtils.equals(activity.getClass().getName(), SMP_ACTIVITY_NAME)) {
//             if (TextUtils.equals(activity.getClass().getName(), nativeActivityName)) {
//                 LogUtils.d(TAG, "hide SuspendView");
//                 CustomStatisticsProxy.recordLogStatus("suspendView", "hide");
//                 SuspendViewController.Companion.getSington().hide();
//             }
//         }
//         LogUtils.d(TAG,"onActivityPaused:"+activity.getClass().getSimpleName()+"  isForeground:"+isForeground);
//         //不建议在Pause加，在页面切换时，会有瞬间的错误值
//         //        isForeground = false;
//         try {
//             CustomStatisticsProxy.onPauseForCloudytrace(activity);
//         } catch (Exception e) {
//             LogUtils.logException(e);
//         }
//     }
//
//     @Override
//     public void onActivityStopped(Activity activity) {
//         //重要，如果Activity的stop中判断应用再前后台，一定要报super.stop()放在第一行
//         foregroundCount--;
//         if (foregroundCount == 0) {
//             isForeground = false;
//             sendBroadcast(activity);
//             //切换到后台,提示用户
//             if(activity != null){
//                 ToastUtil.showMessage(ResUtil.getString(EPApp.getApp(), R.string.lifecycle_in_background));
//             }
//             //非启动页，切换到后台，记录离开时间
//             if(activity != null && !(activity instanceof SplashActivity) ){
//                 mAutoLogonLeaveTime = SystemClock.elapsedRealtime();
//             }
//             if (!activity.getClass().getName()
//                     .equals(GestureLogonActivity.class.getName())) {
//                 boolean isFpEnable = UserAccountDAO.getInstance().isFingerprintEnable();
//                 if (GestureSqliteOpenHelper.getInstance().isGestureBeenActivated()
//                         || isFpEnable) {
//                     if (ConfigEPA.IS_GESTURE_OPEND) {
//                         mUserLeaveTime = SystemClock.elapsedRealtime();
//                         gestureTimerOpened = true;
//                     }
//                 }
//             }
//         }
//         if (!TextUtils.equals(activity.getClass().getName(), SMP_ACTIVITY_NAME)) {
//             if (TextUtils.equals(activity.getClass().getName(), nativeActivityName)) {
//                 int len = activityList.size();
//                 if (len > 0) {
//                     LogUtils.d(TAG, "remove SuspendView: " + activityList.get(len -1).getClass().getSimpleName() + " , nativeActivityName: " + nativeActivityName);
//                 }
//                 if (len > 0 && TextUtils.equals(activityList.get(len -1).getClass().getName(), nativeActivityName) && isForeground) {
//                     CustomStatisticsProxy.recordLogStatus("suspendView", "removeView");
//                     SuspendViewController.Companion.getSington().removeView(true);
//                     nativeActivityName = "";
//                     SuspendViewController.Companion.getSington().setNativeShow(false);
//                 }
//             }
//         }
//         LogUtils.d(TAG, "onActivityStopped:" + activity.getClass().getSimpleName() + "  " +
//                 "isForeground:" + isForeground);
//     }
//
//     @Override
//     public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//         LogUtils.d(TAG,"onActivitySaveInstanceState:"+activity.getClass().getSimpleName()+"  isForeground:"+isForeground);
//     }
//
//     @Override
//     public void onActivityDestroyed(Activity activity) {
//         activityList.remove(activity);
//         //正常打开新页面，都是现在新页面的onCreate，再调用前一个页面的onDestroy
//         //返回的时候，返回的页面先走onResume,再调用返回前一个页面的onDestroy
//         //如果销毁activity和记录的是同一个对象的话，说明应用的所有页面都关闭了
//         if(currentShowActivity == activity){
//             currentShowActivity = null;
//         }
//         if (!TextUtils.equals(activity.getClass().getName(), SMP_ACTIVITY_NAME)) {
//             if (TextUtils.equals(activity.getClass().getName(), nativeActivityName) &&
//                     SuspendViewController.Companion.getSington().isNativeShow()) {
//                 SuspendViewController.Companion.getSington().setNativeShow(false);
//                 nativeActivityName = "";
//             }
//         }
//         LogUtils.d(TAG,"onActivityDestroyed:"+activity.getClass().getSimpleName()+"  isForeground:"+isForeground);
//     }
//
//     /**
//      * 去解锁页
//      */
//     private void jumpToGestureLogonActivity(Activity currentShowActivity) {
//         Intent intent = new Intent(currentShowActivity, GestureLogonActivity.class);
//         intent.putExtra("isFromLife",true);
//         currentShowActivity.startActivity(intent);
//     }
//
//     /**
//      * 关闭所有的activity
//      */
//     public void closeAllActivity(){
//         if(activityList != null && !activityList.isEmpty()){
//             for(Activity activity:activityList){
//                 if(!ActivityUtil.isActivityDestory(activity)){
//                     activity.finish();
//                 }
//             }
//             activityList.clear();
//         }
//     }
//
//     public int getActivityNum() {
//         return activityList == null ? 0 : activityList.size();
//     }
//
//     //发送应用内广播
//     private void sendBroadcast(Activity activity) {
//         Intent intent = new Intent(Constant.INTENT_ACTION_IS_FOREGROUND);
//         intent.putExtra("isForeground", isForeground);
//         LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
//     }
//
//     //栈内是否存在解锁页
//     public boolean isGestureExist(){
//         if(activityList != null && !activityList.isEmpty()){
//             for(Activity activity:activityList){
//                 if (activity instanceof GestureLogonActivity){
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }
//     //栈内是否存在启动页
//     public boolean isSplashExist(){
//         if(activityList != null && !activityList.isEmpty()){
//             for(Activity activity:activityList){
//                 if (activity instanceof SplashActivity){
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }
//     //应用在后台是否超出设定时间
//     public boolean isOverTime(){
//         if (gestureTimerOpened) {
//             long currentTime = SystemClock.elapsedRealtime();
//             if (currentTime - mUserLeaveTime > LOCK_DELAY && EPApp.getApp().isLogon()) {
//                 gestureTimerOpened = false;
//                 return true;
//             }
//         }
//         return false;
//     }
//
//     //栈内是否存在付款码页面
//     public boolean isHasPayCode(){
//         if(activityList != null && !activityList.isEmpty()){
//             for(Activity activity:activityList){
//                 if (activity instanceof PaymentCodeMainActivity){
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }
//
//     //栈内结束付款码页面
//     public void doFinishPayCode(){
//         if(activityList != null && !activityList.isEmpty()){
//             for(Activity activity:activityList){
//                 if (activity instanceof PaymentCodeMainActivity){
//                     activity.finish();
//                 }
//             }
//         }
//     }
//
//     //栈内结束解锁页
//     public void doFinishGesture(){
//         if(activityList != null && !activityList.isEmpty()){
//             for(Activity activity:activityList){
//                 if (activity instanceof GestureLogonActivity){
//                     activity.finish();
//                 }
//             }
//         }
//     }
//
//     /**
//      * 银行授权登录 特殊场景使用，销毁当前页面之外的页面
//      * 若app未登录，则授权登录需要展示用户数活动登录，若之前有其他操作，会导致app
//      * 页面栈不可控。
//      */
//     public void closeOtherActivity(){
//         int num = getActivityNum();
//         if(num <=1){
//             //个数为0或1，直接返回
//             return;
//         }
//         //遍历 销毁
//         for(int i =0;i<num-1;i++){
//             Activity activity = activityList.get(i);
//             if(!ActivityUtil.isActivityDestory(activity)){
//                 activity.finish();
//             }
//         }
//     }
//
//     /**
//      * 是否已经包含该Activity
//      * 如果在activity生命周期内判断，请在super.onCreate之前调用
//      * @param activity 实例对象
//      */
//     public boolean isContains(Activity activity){
//         if(activity == null){
//             return false;
//         }
//         String name = activity.getClass().getName();
//         if(StringUtil.isEmptyOrNull(name)){
//             return false;
//         }
//         for(Activity temp:activityList){
//             if(ActivityUtil.isActivityDestory(temp)){
//                 continue;
//             }
//             if(name.equals(temp.getClass().getName())){
//                 return true;
//             }
//         }
//         return false;
//     }
//
//     /**
//      * 是否已经包含该Activity
//      * 如果在activity生命周期内判断，请在super.onCreate之前调用
//      * @param activityClass class类
//      */
//     public boolean isContains(Class activityClass){
//         if(activityClass == null){
//             return false;
//         }
//         String name = activityClass.getName();
//         if(StringUtil.isEmptyOrNull(name)){
//             return false;
//         }
//         for(Activity temp:activityList){
//             if(ActivityUtil.isActivityDestory(temp)){
//                 continue;
//             }
//             if(name.equals(temp.getClass().getName())){
//                 return true;
//             }
//         }
//         return false;
//     }
// }
