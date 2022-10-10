package com.githubyss.common.kit.glide;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;


/**
 * GlideDecodeStrategy
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/16 19:25:50
 */
@Documented
@StringDef({GlideDecodeStrategy.AS_BITMAP, GlideDecodeStrategy.AS_GIF, GlideDecodeStrategy.AS_DRAWABLE, GlideDecodeStrategy.AS_FILE})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface GlideDecodeStrategy {
    final String AS_BITMAP   = "asBitmap";
    final String AS_GIF      = "asGif";
    final String AS_DRAWABLE = "asDrawable";
    final String AS_FILE     = "asFile";
}
