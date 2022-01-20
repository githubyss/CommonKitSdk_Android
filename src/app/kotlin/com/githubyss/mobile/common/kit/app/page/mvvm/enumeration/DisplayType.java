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
@StringDef({DisplayType.TEXT, DisplayType.IMAGE})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface DisplayType {
    final String TEXT  = "text";
    final String IMAGE = "image";
}
