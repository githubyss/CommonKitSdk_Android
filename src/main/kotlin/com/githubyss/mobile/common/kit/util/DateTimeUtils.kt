package com.githubyss.mobile.common.kit.util

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.*


/**
 * DateTimeUtils
 * 日期时间
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/08/24 15:13:28
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "DateTimeUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/** ********** Datetime of Milliseconds from 1970-01-01T00:00:00Z ********** */

/**
 * Get milliseconds datetime of current instant.
 *
 * @return The datetime of milliseconds.
 */
fun getCurrentDatetimeOfMilliseconds(): Long {
    return System.currentTimeMillis()
}

/**
 * Get milliseconds datetime of given instant.
 *
 * @param milliseconds The milliseconds from 1970-01-01T00:00:00Z.
 * @return The datetime of milliseconds.
 */
fun getDatetimeOfMilliseconds(milliseconds: Long): Long {
    return milliseconds
}

/**
 * Get milliseconds datetime of given instant.
 *
 * @param datetime The datetime string.
 * @param pattern  The pattern string of datetime format.
 * @return The datetime of milliseconds.
 */
fun getDatetimeOfMilliseconds(datetime: String, pattern: String): Long {
    return getDatetimeOfJoda(datetime, pattern).millis
}

/** ********** Datetime of Joda DateTime ********** */

/**
 * Get joda datetime of current instant.
 *
 * @return The datetime of joda.
 */
fun getCurrentDatetimeOfJoda(): DateTime {
    return DateTime.now()
}

/**
 * Get joda datetime of given instant.
 *
 * @param milliseconds The milliseconds from 1970-01-01T00:00:00Z.
 * @return The datetime of joda.
 */
fun getDatetimeOfJoda(milliseconds: Long): DateTime {
    return DateTime(milliseconds)
}

fun getDatetimeOfJoda(date: Date): DateTime {
    return DateTime(date)
}

fun getDatetimeOfJoda(calendar: Calendar): DateTime {
    return DateTime(calendar)
}

fun getDatetimeOfJoda(dateTime: DateTime): DateTime {
    return DateTime(dateTime)
}

fun getDatetimeOfJoda(datetime: String): DateTime {
    return DateTime(datetime)
}

/**
 * Get joda datetime of given instant.
 *
 * @param datetime The datetime string.
 * @param pattern  The pattern string of datetime format.
 * @return The datetime of joda.
 */
fun getDatetimeOfJoda(datetime: String, pattern: String): DateTime {
    return DateTime.parse(datetime, DateTimeFormat.forPattern(pattern))
}

/** ******************** Checker ******************** */

fun String.datetimeIsBeforeNow(pattern: String): Boolean {
    return DateTime.parse(this, DateTimeFormat.forPattern(pattern)).isBeforeNow
}

fun isBeforeNow(datetime: String, pattern: String): Boolean {
    return DateTime.parse(datetime, DateTimeFormat.forPattern(pattern)).isBeforeNow
}

fun String.datetimeIsAfterNow(pattern: String): Boolean {
    return DateTime.parse(this, DateTimeFormat.forPattern(pattern)).isAfterNow
}

fun isAfterNow(datetime: String, pattern: String): Boolean {
    return DateTime.parse(datetime, DateTimeFormat.forPattern(pattern)).isAfterNow
}

/** ******************** Converter ******************** */

/** ******************** Processor ******************** */
