package com.githubyss.mobile.common.kit.enumeration;

import androidx.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * SexType
 * <Description> 性别类型
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/29 14:08:32
 */
@Documented
@IntDef({SexType.MAN, SexType.WOMEN})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface SexType {
    
    final int MAN   = 0x02;
    final int WOMEN = 0x03;
}
