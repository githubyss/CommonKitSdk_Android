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


    fun currentTimeMillis(): Long
            = System.currentTimeMillis()

    fun defaultFormattedTime(timeMillis: Long): String
            = DATE_FORMAT_DEFAULT.format(timeMillis)

    fun millisToS(timeMillis: Long): Long
            = timeMillis / 1000
}
