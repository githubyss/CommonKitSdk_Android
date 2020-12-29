package com.githubyss.mobile.common.kit.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


/**
 * TimeUnit
 * <Description> 时间单位
 * <Details> 以毫秒计
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/29 14:09:12
 */
@Documented
@IntDef({TimeUnit.MSEC, TimeUnit.SEC, TimeUnit.MIN, TimeUnit.HOUR, TimeUnit.DAY})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface TimeUnit {
    
    final int MSEC = 1;
    final int SEC  = 1000;
    final int MIN  = 60000;
    final int HOUR = 3600000;
    final int DAY  = 86400000;
}
