package com.githubyss.common.kit.manager.audio_player.util

import com.githubyss.common.kit.util.logD
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

    /** ****************************** Properties ****************************** */

    private val TAG: String = ProgressTextUtils::class.java.simpleName


    /** ****************************** Functions ****************************** */

    /**
     * 获取进度文案
     *
     * @param time 时间
     * @return 进度条文案
     */
    fun getProgressText(time: Long): String? {
        val calendar = Calendar.getInstance()
        logD(TAG, "getProgressText >>> time: $time")
        val roundTime = java.lang.Double.valueOf(Math.round(time / 1000f) * 1000.toDouble())
            .toLong()
        logD(TAG, "getProgressText >>> roundTime: $roundTime")
        calendar.time = Date(roundTime)
        val minute = calendar[Calendar.MINUTE].toDouble()
        val second = calendar[Calendar.SECOND].toDouble()
        val format = DecimalFormat("00")
        return format.format(minute) + ":" + format.format(second)
    }
}
