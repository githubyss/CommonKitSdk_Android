package com.githubyss.mobile.common.kit.enumeration

import androidx.annotation.IntDef


/** 只能使用 {@link #MAN} {@link #WOMEN} */
// 开启Doc文档
@MustBeDocumented
// 限定为 MAN、WOMEN
@IntDef(SexTypeKotlin.MAN, SexTypeKotlin.WOMEN)
// 注解作用范围，参数注解，成员注解，方法注解
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
// 注解所存活的时间，在运行时，而不会存在 .class 文件中
@Retention(AnnotationRetention.SOURCE)
annotation class SexTypeKotlin {
    // 接口，定义新的注解类型
    companion object {
        const val MAN = 0x02
        const val WOMEN = 0x03
    }
}
