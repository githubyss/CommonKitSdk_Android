package com.githubyss.mobile.common.kit.enumeration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


/**
 * MemoryUnit
 * 内存单位
 * <Details> 以字节计
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/29 14:08:54
 */
@Documented
@IntDef({MemoryUnit.BYTE, MemoryUnit.KB, MemoryUnit.MB, MemoryUnit.GB})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface MemoryUnit {

    final int BYTE = 1;
    final int KB = 1024;
    final int MB = 1048576;
    final int GB = 1073741824;
}
