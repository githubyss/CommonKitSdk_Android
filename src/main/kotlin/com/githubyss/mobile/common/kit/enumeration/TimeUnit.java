package com.githubyss.mobile.common.kit.enumeration;

import androidx.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * TimeUnit
 * 时间单位
 * 以毫秒计
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/08/24 15:12:35
 */
@Documented
@IntDef({TimeUnit.MILLISECOND, TimeUnit.SECOND, TimeUnit.MINUTE, TimeUnit.HOUR, TimeUnit.DAY})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface TimeUnit {
    final int MILLISECOND = 1;
    final int SECOND      = 1000;
    final int MINUTE      = 60000;
    final int HOUR        = 3600000;
    final int DAY         = 86400000;
}
