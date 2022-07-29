package com.githubyss.mobile.common.kit.app

import com.githubyss.mobile.common.kit.util.currentDatetimeMillis
import org.joda.time.DateTime


fun main(args: Array<String>) {
    println(currentDatetimeMillis)
    // println(currentDatetimeJoda)
    println(DateTime("2022-12-21"))
    println(DateTime("2022-12"))
    Thread.sleep(500)
    println(currentDatetimeMillis)
    // println(currentDatetimeJoda)
}
