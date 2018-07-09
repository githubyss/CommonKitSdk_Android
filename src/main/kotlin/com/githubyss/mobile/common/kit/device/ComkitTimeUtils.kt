package com.githubyss.mobile.common.kit.device

import java.text.SimpleDateFormat
import java.util.*

/**
 * ComkitTimeUtils.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitTimeUtils {
    private val DATE_FORMAT_DEFAULT = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())


    fun currentTimeMillis(
    ) = System.currentTimeMillis()

    fun defaultFormattedTime(timeMillis: Long
    ) = DATE_FORMAT_DEFAULT.format(timeMillis)

    fun millisToS(timeMillis: Long
    ) = timeMillis / 1000
}
