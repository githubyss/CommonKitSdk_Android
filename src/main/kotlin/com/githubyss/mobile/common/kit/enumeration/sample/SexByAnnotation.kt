package com.githubyss.mobile.common.kit.enumeration.sample

import androidx.annotation.StringDef


/**
 * SexByAnnotation
 * 只能使用 {@link #MAN} {@link #WOMEN}
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/21 16:16:10
 */
// 开启Doc文档
@MustBeDocumented
// 限定为 MAN、WOMEN
@StringDef(SexByAnnotation.MAN, SexByAnnotation.WOMEN)
// 注解作用范围，参数注解，成员注解，方法注解
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
// 注解所存活的时间，在运行时，而不会存在 .class 文件中
@Retention(AnnotationRetention.SOURCE)
annotation class SexByAnnotation {
    // 接口，定义新的注解类型
    companion object {
        const val MAN = "男"
        const val WOMEN = "女"
    }
}
