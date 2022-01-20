package com.githubyss.mobile.common.kit.app.page.mvvm.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;


/**
 * DisplayType
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/15 15:41:04
 */
@Documented
@StringDef({TimeOperateState.START, TimeOperateState.STOP})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface TimeOperateState {
    final String START = "开始";
    final String STOP  = "停止";
}
