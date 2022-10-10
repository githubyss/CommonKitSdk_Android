package com.githubyss.common.kit.enumeration;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;


/**
 * WindowManagerLayoutParamsType
 * WindowManager 窗口样式
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/01/11 15:14:55
 */
@Documented
@IntDef({WindowManagerLayoutParamsType.TYPE_PHONE, WindowManagerLayoutParamsType.TYPE_TOAST, WindowManagerLayoutParamsType.TYPE_PRESENTATION, WindowManagerLayoutParamsType.TYPE_APPLICATION_OVERLAY})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface WindowManagerLayoutParamsType {
    
    final int FIRST_SYSTEM_WINDOW = 2000;
    final int TYPE_PHONE          = FIRST_SYSTEM_WINDOW + 2;
    final int TYPE_TOAST          = FIRST_SYSTEM_WINDOW + 5;
    
    /** 小米 8.0, 8.1 的坑，正常规则走不通，只能走系统漏洞。 */
    final int TYPE_PRESENTATION = FIRST_SYSTEM_WINDOW + 37;
    
    /** 8.0 type 样式，不可修改，为适应低版本编译，自己定义。 */
    final int TYPE_APPLICATION_OVERLAY = FIRST_SYSTEM_WINDOW + 38;
}
