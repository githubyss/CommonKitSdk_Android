package com.githubyss.mobile.common.kit.constant

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


/**
 * TimeConstants
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/21 10:21:58
 */
object TimeConstants {
    
    const val MSEC = 1
    const val SEC = 1000
    const val MIN = 60000
    const val HOUR = 3600000
    const val DAY = 86400000
    
    @IntDef(MSEC, SEC, MIN, HOUR, DAY)
    @Retention(RetentionPolicy.SOURCE)
    annotation class Unit
}
