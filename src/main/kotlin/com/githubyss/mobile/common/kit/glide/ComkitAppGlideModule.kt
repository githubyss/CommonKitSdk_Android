package com.githubyss.mobile.common.kit.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule


@GlideModule
class ComkitAppGlideModule : AppGlideModule() {
    
    private val MEMORY_CACHE_SIZE = Runtime.getRuntime()
            .maxMemory()
            .toInt() / 8
    
    private val DISK_CACHE_SIZE = 50 * 1024 * 1024
    
    /**
     * 通过 GlideBuilder 设置默认的结构 (Engine, BitmapPool, ArrayPool, MemoryCache 等等).
     *
     * @param context
     * @param builder
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        // 重新设置内存限制
        builder.setMemoryCache(LruResourceCache(MEMORY_CACHE_SIZE.toLong()))
        
        // 重新设置磁盘缓存大小
        builder.setDiskCache(ExternalPreferredCacheDiskCacheFactory(context, "glide", DISK_CACHE_SIZE.toLong()))
        
        // //重新设置其他路径
        // builder.setDiskCache(new DiskCache.Factory() {
        //     @Nullable
        //     @Override
        //     public DiskCache build() {
        //         File diskCache = getDiskCache(context);
        //         if (!diskCache.exists()) {
        //             diskCache.mkdirs();
        //         }
        //         return DiskLruCacheWrapper.get(diskCache, DISK_CACHE_SIZE);
        //     }
        // });
        // //存放在data/data/xxxx/cache/
        // builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "glide", diskCacheSize));
        // //存放在外置文件浏览器
        // builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, "glide", diskCacheSize));
    }
    
    /**
     * 清单解析的开启
     * 这里不开启，避免添加相同的modules两次
     *
     * @param
     * @return
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}