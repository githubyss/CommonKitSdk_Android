package com.githubyss.mobile.common.kit.manager.audio_player.util

import com.githubyss.mobile.common.kit.util.LogUtils
import java.text.DecimalFormat
import java.util.*


/**
 * ProgressTextUtils
 * 进度条文案工具
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/02/25 09:27:39
 */
object ProgressTextUtils {
    
    /**
     * 获取进度文案
     *
     * @param time 时间
     * @return 进度条文案
     */
    fun getProgressText(time: Long): String? {
        val calendar = Calendar.getInstance()
        LogUtils.d("getProgressText", "time: $time")
        val roundTime = java.lang.Double.valueOf(Math.round(time / 1000f) * 1000.toDouble())
                .toLong()
        LogUtils.d("getProgressText", "roundTime: $roundTime")
        calendar.time = Date(roundTime)
        val minute = calendar[Calendar.MINUTE].toDouble()
        val second = calendar[Calendar.SECOND].toDouble()
        val format = DecimalFormat("00")
        return format.format(minute) + ":" + format.format(second)
    }
}
