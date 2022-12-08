package com.githubyss.common.kit.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


/**
 * 数据类型
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/12/07 09:52:24
 */
@Documented
@IntDef({DataType.BOOLEAN, DataType.INT, DataType.LONG, DataType.DOUBLE, DataType.STRING, DataType.JSON_OBJECT, DataType.JSON_ARRAY})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface DataType {

    final int BOOLEAN = 0x00;
    final int INT = 0x01;
    final int LONG = 0x02;
    final int DOUBLE = 0x03;
    final int STRING = 0x04;
    final int JSON_OBJECT = 0x05;
    final int JSON_ARRAY = 0x06;
}
