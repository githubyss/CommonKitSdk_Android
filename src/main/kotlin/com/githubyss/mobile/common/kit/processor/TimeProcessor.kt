package com.githubyss.mobile.common.kit.processor


import java.text.SimpleDateFormat
import java.util.*


/**
 * TimeProcessor
 *
 * @author Ace Yan
 * @github githubyss
 */
object TimeProcessor {
    
    private val DATETIME_FORMAT_DEFAULT = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
    
    fun millisToSecond(timeMillis: Long): Long {
        return timeMillis / 1000
    }
    
    fun secondToMillis(timeSecond: Long): Long {
        return timeSecond * 1000
    }
    
    fun millis2Default(timeMillis: Long): String {
        return DATETIME_FORMAT_DEFAULT.format(timeMillis)
    }
}
