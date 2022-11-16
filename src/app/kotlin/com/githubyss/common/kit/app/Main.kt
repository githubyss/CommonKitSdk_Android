package com.githubyss.common.kit.app

import com.githubyss.common.kit.util.*
import org.joda.time.DateTime


fun main(args: Array<String>) {
    // println(currentMillis)
    // println(currentDatetimeJoda)
    // println(DateTime("2022-12-21"))
    // println(DateTime("2022-12"))
    // Thread.sleep(500)
    // println(currentMillis)
    // println(currentDatetimeJoda)
    println(DateTime.now().toString())
    println(DateTime(0).toString())
    println()
    println(0L.datetimeString(PATTERN_DATETIME_UTC_FULL))
    println(0L.datetimeString(PATTERN_DATETIME_UTC_ABBR))
    println()
    println("2022/07/29 22:51:20".datetimeStringUtc("yyyy/MM/DD HH:mm:ss"))
    println("2022-07-29T22:51:20.000+00:00".equalTodayForUtc)
    println("2022-07-29T22:51:20.000".datetimeStringForUtc("M月d日"))
}
