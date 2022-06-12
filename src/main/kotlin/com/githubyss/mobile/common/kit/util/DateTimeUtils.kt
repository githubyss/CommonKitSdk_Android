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

const val PATTERN_DATETIME_WITH_UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
const val PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH = "yyyy-MM-dd"
const val PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT = "yyyy.MM.dd"
const val PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_SLASH = "yyyy/MM/dd"
const val PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_NONE = "yyyyMMdd"
const val PATTERN_DATE_YEAR_MONTH_DAY_ABBR_DIVIDED_BY_EN_DASH = "yyyy-M-d"
const val PATTERN_DATE_YEAR_MONTH_DAY_ABBR_DIVIDED_BY_DOT = "yyyy.M.d"
const val PATTERN_DATE_YEAR_MONTH_DAY_ABBR_DIVIDED_BY_SLASH = "yyyy/M/d"
const val PATTERN_TIME_HOUR12_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON = "hh:mm:ss.SSS"
const val PATTERN_TIME_HOUR24_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON = "HH:mm:ss.SSS"
const val PATTERN_TIME_HOUR12_MINUTE_SECOND_FULL_DIVIDED_BY_COLON = "hh:mm:ss"
const val PATTERN_TIME_HOUR24_MINUTE_SECOND_FULL_DIVIDED_BY_COLON = "HH:mm:ss"
const val PATTERN_TIME_HOUR12_MINUTE_FULL_DIVIDED_BY_COLON = "hh:mm"
const val PATTERN_TIME_HOUR24_MINUTE_FULL_DIVIDED_BY_COLON = "HH:mm"

const val DATETIME_OF_MILLIS_0_WITH_UTC_FORMAT = "1970-01-01T00:00:00Z"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/** ********** Milliseconds from 1970-01-01T00:00:00Z ********** */

/**
 * Get milliseconds of current instant.
 *
 * @return The current milliseconds from 1970-01-01T00:00:00Z.
 */
fun currentTimeMillis(): Long = System.currentTimeMillis()

/**
 * Get joda datetime of current instant.
 *
 * @return The joda datetime from 1970-01-01T00:00:00Z.
 */
private fun currentJodaDatetime(): DateTime = DateTime.now()

/**
 * Get datetime string with specified format pattern.
 *
 * @param pattern The specified format pattern.
 * @return The formatted datetime string.
 */
// fun currentDatetimeFormatted(pattern: String): String = DateTime().toString(DateTimeFormat.forPattern(pattern))
fun currentDatetimeFormatted(pattern: String): String = currentJodaDatetime().toString(pattern)

fun currentDatetimeWithUtcFormat(): String = currentJodaDatetime().toString(PATTERN_DATETIME_WITH_UTC_FORMAT)

fun currentDatetimeYmdH12msFullDividedByEnDash(): String = currentJodaDatetime().toString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR12_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
fun currentDatetimeYmdH24msFullDividedByEnDash(): String = currentJodaDatetime().toString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR24_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
fun currentDatetimeYmdH12msMillisFullDividedByEnDash(): String = currentJodaDatetime().toString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR12_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")
fun currentDatetimeYmdH24msMillisFullDividedByEnDash(): String = currentJodaDatetime().toString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR24_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")

fun currentDatetimeYmdH12msFullDividedByDot(): String = currentJodaDatetime().toString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR12_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
fun currentDatetimeYmdH24msFullDividedByDot(): String = currentJodaDatetime().toString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR24_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
fun currentDatetimeYmdH12msMillisFullDividedByDot(): String = currentJodaDatetime().toString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR12_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")
fun currentDatetimeYmdH24msMillisFullDividedByDot(): String = currentJodaDatetime().toString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR24_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")

/** ********** Joda DateTime ********** */

/**
 * Get joda datetime of given instant.
 *
 * @param millis The milliseconds from 1970-01-01T00:00:00Z.
 * @return The datetime of joda.
 */
fun jodaDatetime(millis: Long): DateTime = DateTime(millis)
val Long.jodaDatetime: DateTime
    get() = DateTime(this)

fun jodaDatetime(date: Date): DateTime = DateTime(date)
val Date.jodaDatetime: DateTime
    get() = DateTime(this)

fun jodaDatetime(calendar: Calendar): DateTime = DateTime(calendar)
val Calendar.jodaDatetime: DateTime
    get() = DateTime(this)

fun jodaDatetime(dateTime: DateTime): DateTime = DateTime(dateTime)
val DateTime.jodaDatetime: DateTime
    get() = DateTime(this)

fun jodaDatetime(datetime: String): DateTime = DateTime(datetime)
val String.jodaDatetime: DateTime
    get() = DateTime(this)

private fun jodaDatetime(datetime: String, pattern: String): DateTime = DateTime.parse(datetime, DateTimeFormat.forPattern(pattern))
fun String.toJodaDatetime(pattern: String): DateTime = DateTime.parse(this, DateTimeFormat.forPattern(pattern))

fun jodaDatetime(year: Int, monthOfYear: Int, dayOfMonth: Int, hourOfDay: Int, minuteOfHour: Int, secondOfMinute: Int = 0, millisOfSecond: Int = 0): DateTime = DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond)

/** ******************** Checker ******************** */

fun isBeforeNow(datetime: String, pattern: String): Boolean = datetime.toJodaDatetime(pattern).isBeforeNow
fun String.isBeforeNowDatetime(pattern: String): Boolean = this.toJodaDatetime(pattern).isBeforeNow

fun isAfterNow(datetime: String, pattern: String): Boolean = jodaDatetime(datetime, pattern).isAfterNow
fun String.isAfterNowDatetime(pattern: String): Boolean = jodaDatetime(this, pattern).isAfterNow

/** ******************** Converter ******************** */

/**
 * Get milliseconds datetime of given instant.
 *
 * @param datetime The datetime string.
 * @param pattern  The pattern string of datetime format.
 * @return The datetime of milliseconds.
 */
fun datetimeMillis(datetime: String, pattern: String): Long {
    return DateTime.parse(datetime, DateTimeFormat.forPattern(pattern)).millis
}

/** ******************** Processor ******************** */
