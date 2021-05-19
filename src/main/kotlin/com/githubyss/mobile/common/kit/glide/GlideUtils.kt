package com.githubyss.mobile.common.kit.glide

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.ViewGroup
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
     * @param loadPath    The image path, see top description.
     * @param imageView   The image view to load.
     * @param errorIcon   The error icon when load failed.
     * @param defaultIcon The default icon when loading.
     * @param context     The context.
     */
    fun loadImage(loadPath: Any?, imageView: ImageView?, errorIcon: Int? = null, defaultIcon: Int? = null, context: Context? = ComkitApplicationConfig.getApp()) {
        loadPath ?: return
        imageView ?: return
        context ?: return
        
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) if (ActivityUtils.isActivityDestroy(context)) return
        }
        
        when (loadPath) {
            is String, is Int -> {
                when {
                    defaultIcon == null && errorIcon == null -> {
                        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
                    }
                    defaultIcon == null && errorIcon != null -> {
                        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).error(errorIcon).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
                    }
                    defaultIcon != null && errorIcon == null -> {
                        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defaultIcon).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
                    }
                    defaultIcon != null && errorIcon != null -> {
                        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).error(errorIcon).placeholder(defaultIcon).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
                    }
                }
            }
        }
    }
    
    /**
     * Load image as gif by path.
     *
     * @param loadPath    The image path, see top description.
     * @param imageView   The image view to load.
     * @param errorIcon   The error icon when load failed.
     * @param defaultIcon The default icon when loading.
     * @param context     The context.
     */
    fun loadImageAsGif(loadPath: Any?, imageView: ImageView?, errorIcon: Int? = null, defaultIcon: Int? = null, context: Context? = ComkitApplicationConfig.getApp()) {
        loadPath ?: return
        imageView ?: return
        context ?: return
        
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) if (ActivityUtils.isActivityDestroy(context)) return
        }
        
        when (loadPath) {
            is Int, is String -> {
                when {
                    defaultIcon == null && errorIcon == null -> {
                        Glide.with(context).asGif().load(loadPath).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView)
                    }
                    defaultIcon == null && errorIcon != null -> {
                        Glide.with(context).asGif().load(loadPath).diskCacheStrategy(DiskCacheStrategy.RESOURCE).error(errorIcon).into(imageView)
                    }
                    defaultIcon != null && errorIcon == null -> {
                        Glide.with(context).asGif().load(loadPath).diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder(defaultIcon).into(imageView)
                    }
                    defaultIcon != null && errorIcon != null -> {
                        Glide.with(context).asGif().load(loadPath).diskCacheStrategy(DiskCacheStrategy.RESOURCE).error(errorIcon).placeholder(defaultIcon).into(imageView)
                    }
                }
            }
        }
    }
    
    /**
     * Load image as circled by path.
     *
     * @param loadPath    The image path, see top description.
     * @param imageView   The image view to load.
     * @param errorIcon   The error icon when load failed.
     * @param defaultIcon The default icon when loading.
     * @param context     The context.
     */
    fun loadCircleImage(loadPath: Any?, imageView: ImageView?, errorIcon: Int? = null, defaultIcon: Int? = null, context: Context? = ComkitApplicationConfig.getApp()) {
        loadPath ?: return
        imageView ?: return
        context ?: return
        
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) if (ActivityUtils.isActivityDestroy(context)) return
        }
        
        when (loadPath) {
            is Int, is String -> {
                when {
                    defaultIcon == null && errorIcon == null -> {
                        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).apply(RequestOptions.circleCropTransform()).into(imageView)
                    }
                    defaultIcon == null && errorIcon != null -> {
                        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).apply(RequestOptions.circleCropTransform()).error(errorIcon).into(imageView)
                    }
                    defaultIcon != null && errorIcon == null -> {
                        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).apply(RequestOptions.circleCropTransform()).placeholder(defaultIcon).into(imageView)
                    }
                    defaultIcon != null && errorIcon != null -> {
                        Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).apply(RequestOptions.circleCropTransform()).error(errorIcon).placeholder(defaultIcon).into(imageView)
                    }
                }
            }
        }
    }
    
    /**
     * Load image into view group background by path.
     *
     * @param loadPath    The image path, see top description.
     * @param viewGroup   The view group to load.
     * @param errorIcon   The error icon when load failed.
     * @param defaultIcon The default icon when loading.
     * @param context     The context.
     */
    fun loadBackground(loadPath: Any?, viewGroup: ViewGroup?, errorIcon: Int? = null, defaultIcon: Int? = null, context: Context? = ComkitApplicationConfig.getApp()) {
        loadPath ?: return
        viewGroup ?: return
        context ?: return
        
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) if (ActivityUtils.isActivityDestroy(context)) return
        }
        
        val viewGroupTarget = object : CustomTarget<Bitmap?>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    viewGroup.background = placeholder
                }
            }
            
            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    viewGroup.background = errorDrawable
                }
            }
            
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    viewGroup.background = BitmapDrawable(null, resource)
                }
            }
        }
        
        when (loadPath) {
            is String, is Int -> {
                when {
                    defaultIcon == null && errorIcon == null -> {
                        Glide.with(context).asBitmap().load(loadPath).into(viewGroupTarget)
                    }
                    defaultIcon == null && errorIcon != null -> {
                        Glide.with(context).asBitmap().load(loadPath).error(errorIcon).into(viewGroupTarget)
                    }
                    defaultIcon != null && errorIcon == null -> {
                        Glide.with(context).asBitmap().load(loadPath).placeholder(defaultIcon).into(viewGroupTarget)
                    }
                    defaultIcon != null && errorIcon != null -> {
                        Glide.with(context).asBitmap().load(loadPath).error(errorIcon).placeholder(defaultIcon).into(viewGroupTarget)
                    }
                }
            }
        }
    }
    
    /**
     * Preload image into cache by path.
     *
     * @param loadPath The image path, see top description.
     * @param context  The context.
     */
    fun preloadImage(loadPath: Any?, context: Context? = ComkitApplicationConfig.getApp()) {
        loadPath ?: return
        context ?: return
        
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) if (ActivityUtils.isActivityDestroy(context)) return
        }
        
        when (loadPath) {
            is String, is Int -> {
                Glide.with(context).load(loadPath).skipMemoryCache(false).diskCacheStrategy(DiskCacheStrategy.ALL).preload()
            }
        }
    }
    
    /**
     * Get bitmap by path.
     *
     * @param loadPath The image path, see top description.
     * @param listener Get bitmap listener.
     * @param context  The context.
     */
    fun getBitmapByUrl(loadPath: Any?, listener: GlideGetBitmapListener?, context: Context? = ComkitApplicationConfig.getApp()) {
        loadPath ?: return
        listener ?: return
        context ?: return
        
        if (Util.isOnMainThread() && context is Activity) {
            if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) if (ActivityUtils.isActivityDestroy(context)) return
        }
        
        val listenerTarget = object : CustomTarget<Bitmap?>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                listener.onFail()
            }
            
            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                listener.onFail()
            }
            
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                listener.onSucceed(resource)
            }
        }
        
        when (loadPath) {
            is String, is Int -> {
                Glide.with(context).asBitmap().load(loadPath).into(listenerTarget)
            }
        }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface GlideGetBitmapListener {
        fun onSucceed(resource: Bitmap?)
        fun onFail()
    }
}
