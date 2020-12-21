package com.githubyss.mobile.common.kit.constant

import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy


/**
 * MemoryConstants
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/21 10:14:06
 */
object MemoryConstants {
    
    const val BYTE = 1
    const val KB = 1024
    const val MB = 1048576
    const val GB = 1073741824
    
    @IntDef(BYTE, KB, MB, GB)
    @Retention(RetentionPolicy.SOURCE)
    annotation class Unit
}
