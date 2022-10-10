package com.githubyss.common.kit.enumeration.sample;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;


/**
 * SexByInterface
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/21 16:15:56
 */
@Documented
@StringDef({SexByInterface.MAN, SexByInterface.WOMEN})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface SexByInterface {
    final String MAN = "男";
    final String WOMEN = "女";
}
