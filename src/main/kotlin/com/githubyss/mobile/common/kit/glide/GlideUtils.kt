package com.githubyss.mobile.common.kit.glide

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.util.Util
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.enumeration.VersionCode
import com.githubyss.mobile.common.kit.util.ActivityUtils


/**
 * GlideUtils
 *
 * > loadPath description:
 * load SD card resources:         load("file://${Environment.getExternalStorageDirectory().getPath()}/test.jpg")
 * load assets resources:          load("file:///android_asset/f003.gif")
 * load raw resources:             load("Android.resource://com.frank.glide/raw/raw_1") or load("android.resource://com.frank.glide/raw/${R.raw.raw_1}")
 * load drawable resources:        load("android.resource://com.frank.glide/drawable/news") or load("android.resource://com.frank.glide/drawable/${R.drawable.news}")
 * load ContentProvider resources: load("content://media/external/images/media/139469")
 * load http resources:            load("http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg")
 * load https resources:           load("https://img.alicdn.com/tps/TB1uyhoMpXXXXcLXVXXXXXXXXXX-476-538.jpg_240x5000q50.jpg_.webp")
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/05 10:11:54
 */
object GlideUtils {
    
    /**
     * Load image by path.
     *
     * @param context   The context.
     * @param loadPath  The image path, see top description.
     * @param imageView The image view to load.
     */
    fun loadImage(loadPath: String?, imageView: ImageView?, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }
    
    fun loadImage(loadPath: Int, imageView: ImageView?, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }
    
    /**
     * Load image by path.
     *
     * @param context   The context.
     * @param loadPath  The image path, see top description.
     * @param imageView The image view to load.
     * @param errorIcon The error icon when load failed.
     */
    fun loadImage(loadPath: String?, imageView: ImageView?, errorIcon: Int, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).error(errorIcon).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }
    
    fun loadImage(loadPath: Int, imageView: ImageView?, errorIcon: Int, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).error(errorIcon).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }
    
    /**
     * Load image by path.
     *
     * @param context   The context.
     * @param loadPath  The image path, see top description.
     * @param imageView The image view to load.
     * @param defIcon   The error icon when load failed.
     * @param errorIcon The error icon when load failed.
     */
    fun loadImage(loadPath: String?, imageView: ImageView?, defIcon: Int, errorIcon: Int, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defIcon).error(errorIcon).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }
    
    fun loadImage(loadPath: Int, imageView: ImageView?, defIcon: Int, errorIcon: Int, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defIcon).error(errorIcon).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }
    
    /**
     * Load image as gif by path.
     *
     * @param context   The context.
     * @param loadPath  The image path, see top description.
     * @param imageView The image view to load.
     */
    fun loadImageAsGif(loadPath: String?, imageView: ImageView?, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).asGif().load(loadPath).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView)
    }
    
    fun loadImageAsGif(loadPath: Int, imageView: ImageView?, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).asGif().load(loadPath).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView)
    }
    
    /**
     * Load image as circled by path.
     *
     * @param context   The context.
     * @param loadPath  The image path, see top description.
     * @param imageView The image view to load.
     */
    fun loadCircleImage(loadPath: String?, imageView: ImageView?, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).apply(RequestOptions.circleCropTransform()).into(imageView)
    }
    
    fun loadCircleImage(loadPath: Int, imageView: ImageView?, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (imageView == null) return
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).apply(RequestOptions.circleCropTransform()).into(imageView)
    }
    
    /**
     * Preload image into cache by path.
     *
     * @param context  The context.
     * @param loadPath The image path, see top description.
     */
    fun preloadImage(loadPath: String?, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).preload()
    }
    
    fun preloadImage(loadPath: Int, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).preload()
    }
    
    /**
     * Get bitmap by path.
     *
     * @param context  The context.
     * @param loadPath The image path, see top description.
     * @param listener Get bitmap listener.
     */
    fun getBitmapByUrl(loadPath: String?, listener: GlideGetBitmapListener?, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (listener == null) return
        Glide.with(context).asBitmap().load(loadPath).into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                listener.success(resource)
            }
            
            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                listener.failed()
            }
            
            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
    }
    
    fun getBitmapByUrl(loadPath: Int, listener: GlideGetBitmapListener?, context: Context = ComkitApplicationConfig.getApp()) {
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
                if (ActivityUtils.isActivityDestroy(context)) return
            }
        }
        if (listener == null) return
        Glide.with(context).asBitmap().load(loadPath).into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                listener.success(resource)
            }
            
            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                listener.failed()
            }
            
            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
    }
    
    interface GlideGetBitmapListener {
        fun success(resource: Bitmap?)
        fun failed()
    }
}
