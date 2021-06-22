package com.githubyss.mobile.common.kit.glide

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.bumptech.glide.util.Util
import com.githubyss.mobile.common.kit.enumeration.VersionCode
import com.githubyss.mobile.common.kit.util.ActivityUtils
import com.githubyss.mobile.common.kit.util.FragmentUtils


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
     * @param imageView      The image view to load.
     * @param context        The context.
     * @param loadPath       The image path, see top description.
     * @param placeholderRes The placeholder resource when loading.
     * @param errorRes       The error resource when load failed.
     */
    fun loadImage(imageView: ImageView?, context: Any?, loadPath: Any?, placeholderRes: Any? = null, errorRes: Any? = null) {
        imageView ?: return
        context ?: return
        loadPath ?: return
        
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.skipMemoryCache(false)
        requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions = when (placeholderRes) {
            is Int -> requestOptions.placeholder(placeholderRes)
            is Drawable -> requestOptions.placeholder(placeholderRes)
            else -> requestOptions
        }
        requestOptions = when (errorRes) {
            is Int -> requestOptions.error(errorRes)
            is Drawable -> requestOptions.error(errorRes)
            else -> requestOptions
        }
        
        loadImageByOptions(imageView, context, loadPath, requestOptions, GlideDecodeStrategy.AS_DRAWABLE)
    }
    
    /**
     * Load image as gif by path.
     *
     * @param imageView      The image view to load.
     * @param context        The context.
     * @param loadPath       The image path, see top description.
     * @param placeholderRes The placeholder resource when loading.
     * @param errorRes       The error resource when load failed.
     */
    fun loadImageAsGif(imageView: ImageView?, context: Context?, loadPath: Any?, placeholderRes: Any? = null, errorRes: Any? = null) {
        imageView ?: return
        context ?: return
        loadPath ?: return
        
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.skipMemoryCache(false)
        requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        requestOptions = when (placeholderRes) {
            is Int -> requestOptions.placeholder(placeholderRes)
            is Drawable -> requestOptions.placeholder(placeholderRes)
            else -> requestOptions
        }
        requestOptions = when (errorRes) {
            is Int -> requestOptions.error(errorRes)
            is Drawable -> requestOptions.error(errorRes)
            else -> requestOptions
        }
        
        loadImageByOptions(imageView, context, loadPath, requestOptions, GlideDecodeStrategy.AS_GIF)
    }
    
    /**
     * Load image as circled by path.
     *
     * @param imageView      The image view to load.
     * @param context        The context.
     * @param loadPath       The image path, see top description.
     * @param placeholderRes The placeholder resource when loading.
     * @param errorRes       The error resource when load failed.
     */
    fun loadCircleImage(imageView: ImageView?, context: Any?, loadPath: Any?, placeholderRes: Any? = null, errorRes: Any? = null) {
        imageView ?: return
        context ?: return
        loadPath ?: return
        
        var requestOptions = RequestOptions.circleCropTransform()
        requestOptions = requestOptions.skipMemoryCache(false)
        requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions = when (placeholderRes) {
            is Int -> requestOptions.placeholder(placeholderRes)
            is Drawable -> requestOptions.placeholder(placeholderRes)
            else -> requestOptions
        }
        requestOptions = when (errorRes) {
            is Int -> requestOptions.error(errorRes)
            is Drawable -> requestOptions.error(errorRes)
            else -> requestOptions
        }
        
        loadImageByOptions(imageView, context, loadPath, requestOptions, GlideDecodeStrategy.AS_DRAWABLE)
    }
    
    /**
     * Load image by path, requestOptions, transitionOptions.
     * Default load as drawable.
     *
     * @param imageView         The image view to load.
     * @param context           The context.
     * @param loadPath          The image path, see top description.
     * @param requestOptions    The request options.
     * @param decodeStrategy    The decode strategy to load image resource.
     */
    fun loadImageByOptions(imageView: ImageView?, context: Any?, loadPath: Any?, requestOptions: RequestOptions? = null, @GlideDecodeStrategy decodeStrategy: String = GlideDecodeStrategy.AS_DRAWABLE) {
        imageView ?: return
        context ?: return
        loadPath ?: return
        
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
            if (Util.isOnMainThread()) {
                when (context) {
                    is Activity -> if (ActivityUtils.isActivityDestroy(context)) return
                    is FragmentActivity -> if (ActivityUtils.isActivityDestroy(context)) return
                    is Fragment -> if (FragmentUtils.isFragmentDestroy(context.activity, context)) return
                    is android.app.Fragment -> if (FragmentUtils.isFragmentDestroy(context.activity, context)) return
                }
            }
        }
        
        var requestManager: RequestManager? = null
        when (context) {
            is Context -> requestManager = Glide.with(context)
            is Activity -> requestManager = Glide.with(context)
            is FragmentActivity -> requestManager = Glide.with(context)
            is Fragment -> requestManager = Glide.with(context)
            is android.app.Fragment -> requestManager = Glide.with(context)
            is View -> requestManager = Glide.with(context)
        }
        requestManager ?: return
        
        var requestBuilder: RequestBuilder<*>? = when (decodeStrategy) {
            GlideDecodeStrategy.AS_BITMAP -> requestManager.asBitmap()
                .transition(BitmapTransitionOptions.withCrossFade())
            GlideDecodeStrategy.AS_GIF -> requestManager.asGif()
                .transition(DrawableTransitionOptions.withCrossFade())
            GlideDecodeStrategy.AS_DRAWABLE -> requestManager.asDrawable()
                .transition(DrawableTransitionOptions.withCrossFade())
            GlideDecodeStrategy.AS_FILE -> requestManager.asFile()
                .transition(GenericTransitionOptions.withNoTransition())
            else -> null
        }
        requestBuilder = requestOptions?.let {
            requestBuilder?.apply(requestOptions)
        }
        requestBuilder ?: return
        
        when (loadPath) {
            is Int, is String -> {
                requestBuilder.load(loadPath)
                    .into(imageView)
            }
        }
    }
    
    /**
     * Load image into view group background by path.
     *
     * @param viewGroup      The view group to load.
     * @param context        The context.
     * @param loadPath       The image path, see top description.
     * @param placeholderRes The placeholder resource when loading.
     * @param errorRes       The error resource when load failed.
     */
    fun loadBackground(viewGroup: ViewGroup?, context: Any?, loadPath: Any?, placeholderRes: Any? = null, errorRes: Any? = null) {
        viewGroup ?: return
        context ?: return
        loadPath ?: return
        
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.skipMemoryCache(false)
        requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions = when (placeholderRes) {
            is Int -> requestOptions.placeholder(placeholderRes)
            is Drawable -> requestOptions.placeholder(placeholderRes)
            else -> requestOptions
        }
        requestOptions = when (errorRes) {
            is Int -> requestOptions.error(errorRes)
            is Drawable -> requestOptions.error(errorRes)
            else -> requestOptions
        }
        
        loadBackgroundByOptions(viewGroup, context, loadPath, requestOptions)
    }
    
    /**
     * Load image into view group background by path.
     *
     * @param viewGroup      The view group to load.
     * @param context        The context.
     * @param loadPath       The image path, see top description.
     * @param requestOptions The request options.
     */
    fun loadBackgroundByOptions(viewGroup: ViewGroup?, context: Any?, loadPath: Any?, requestOptions: RequestOptions? = null) {
        viewGroup ?: return
        context ?: return
        loadPath ?: return
        
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
            if (Util.isOnMainThread()) {
                when (context) {
                    is Activity -> if (ActivityUtils.isActivityDestroy(context)) return
                    is FragmentActivity -> if (ActivityUtils.isActivityDestroy(context)) return
                    is Fragment -> if (FragmentUtils.isFragmentDestroy(context.activity, context)) return
                    is android.app.Fragment -> if (FragmentUtils.isFragmentDestroy(context.activity, context)) return
                }
            }
        }
        
        var requestManager: RequestManager? = null
        when (context) {
            is Context -> requestManager = Glide.with(context)
            is Activity -> requestManager = Glide.with(context)
            is FragmentActivity -> requestManager = Glide.with(context)
            is Fragment -> requestManager = Glide.with(context)
            is android.app.Fragment -> requestManager = Glide.with(context)
            is View -> requestManager = Glide.with(context)
        }
        requestManager ?: return
        
        var requestBuilder: RequestBuilder<Bitmap>? = requestManager.asBitmap()
            .transition(BitmapTransitionOptions.withCrossFade())
        requestBuilder = requestOptions?.let {
            requestBuilder?.apply(requestOptions)
        }
        requestBuilder ?: return
        
        val viewGroupTarget = object : CustomTarget<Bitmap?>() {
            override fun onLoadCleared(placeholderDrawable: Drawable?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    viewGroup.background = placeholderDrawable
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
            is Int, is String -> {
                requestBuilder.load(loadPath)
                    .into(viewGroupTarget)
            }
        }
    }
    
    /**
     * Get bitmap by path.
     *
     * @param context        The context.
     * @param loadPath       The image path, see top description.
     * @param listener       Get bitmap listener.
     * @param placeholderRes The placeholder resource when loading.
     * @param errorRes       The error resource when load failed.
     */
    fun getBitmap(context: Any?, loadPath: Any?, listener: GlideGetBitmapListener?, placeholderRes: Any? = null, errorRes: Any? = null) {
        context ?: return
        loadPath ?: return
        listener ?: return
        
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.skipMemoryCache(false)
        requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions = when (placeholderRes) {
            is Int -> requestOptions.placeholder(placeholderRes)
            is Drawable -> requestOptions.placeholder(placeholderRes)
            else -> requestOptions
        }
        requestOptions = when (errorRes) {
            is Int -> requestOptions.error(errorRes)
            is Drawable -> requestOptions.error(errorRes)
            else -> requestOptions
        }
        
        getBitmapByOptions(context, loadPath, listener, requestOptions)
    }
    
    /**
     * Get bitmap by path.
     *
     * @param context        The context.
     * @param loadPath       The image path, see top description.
     * @param listener       Get bitmap listener.
     * @param requestOptions The request options.
     */
    fun getBitmapByOptions(context: Any?, loadPath: Any?, listener: GlideGetBitmapListener?, requestOptions: RequestOptions? = null) {
        context ?: return
        loadPath ?: return
        listener ?: return
        
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
            if (Util.isOnMainThread()) {
                when (context) {
                    is Activity -> if (ActivityUtils.isActivityDestroy(context)) return
                    is FragmentActivity -> if (ActivityUtils.isActivityDestroy(context)) return
                    is Fragment -> if (FragmentUtils.isFragmentDestroy(context.activity, context)) return
                    is android.app.Fragment -> if (FragmentUtils.isFragmentDestroy(context.activity, context)) return
                }
            }
        }
        
        var requestManager: RequestManager? = null
        when (context) {
            is Context -> requestManager = Glide.with(context)
            is Activity -> requestManager = Glide.with(context)
            is FragmentActivity -> requestManager = Glide.with(context)
            is Fragment -> requestManager = Glide.with(context)
            is android.app.Fragment -> requestManager = Glide.with(context)
            is View -> requestManager = Glide.with(context)
        }
        requestManager ?: return
        
        var requestBuilder: RequestBuilder<Bitmap>? = requestManager.asBitmap()
            .transition(BitmapTransitionOptions.withCrossFade())
        requestBuilder = requestOptions?.let {
            requestBuilder?.apply(requestOptions)
        }
        requestBuilder ?: return
        
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
            is Int, is String -> {
                requestBuilder.load(loadPath)
                    .into(listenerTarget)
            }
        }
    }
    
    /**
     * Preload image into cache by path.
     *
     * @param context  The context.
     * @param loadPath The image path, see top description.
     * @param placeholderRes The placeholder resource when loading.
     * @param errorRes       The error resource when load failed.
     */
    fun preloadImage(context: Any?, loadPath: Any?, placeholderRes: Any? = null, errorRes: Any? = null) {
        context ?: return
        loadPath ?: return
        
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.skipMemoryCache(false)
        requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        requestOptions = when (placeholderRes) {
            is Int -> requestOptions.placeholder(placeholderRes)
            is Drawable -> requestOptions.placeholder(placeholderRes)
            else -> requestOptions
        }
        requestOptions = when (errorRes) {
            is Int -> requestOptions.error(errorRes)
            is Drawable -> requestOptions.error(errorRes)
            else -> requestOptions
        }
        
        preloadImage(context, loadPath, requestOptions)
    }
    
    /**
     * Preload image into cache by path.
     *
     * @param context  The context.
     * @param loadPath The image path, see top description.
     * @param requestOptions The request options.
     */
    fun preloadImageByOptions(context: Any?, loadPath: Any?, requestOptions: RequestOptions? = null) {
        context ?: return
        loadPath ?: return
        
        if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
            if (Util.isOnMainThread()) {
                when (context) {
                    is Activity -> if (ActivityUtils.isActivityDestroy(context)) return
                    is FragmentActivity -> if (ActivityUtils.isActivityDestroy(context)) return
                    is Fragment -> if (FragmentUtils.isFragmentDestroy(context.activity, context)) return
                    is android.app.Fragment -> if (FragmentUtils.isFragmentDestroy(context.activity, context)) return
                }
            }
        }
        
        var requestManager: RequestManager? = null
        when (context) {
            is Context -> requestManager = Glide.with(context)
            is Activity -> requestManager = Glide.with(context)
            is FragmentActivity -> requestManager = Glide.with(context)
            is Fragment -> requestManager = Glide.with(context)
            is android.app.Fragment -> requestManager = Glide.with(context)
            is View -> requestManager = Glide.with(context)
        }
        requestManager ?: return
        
        var requestBuilder: RequestBuilder<Bitmap>? = requestManager.asBitmap()
            .transition(BitmapTransitionOptions.withCrossFade())
        requestBuilder = requestOptions?.let {
            requestBuilder?.apply(requestOptions)
        }
        requestBuilder ?: return
        
        when (loadPath) {
            is Int, is String -> {
                requestBuilder.load(loadPath)
                    .preload()
            }
        }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface GlideGetBitmapListener {
        fun onSucceed(resource: Bitmap?)
        fun onFail()
    }
}
