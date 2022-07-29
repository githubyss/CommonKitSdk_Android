@file:JvmName("DateTimeUtils")

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

/** */
private const val TAG: String = "DateTimeUtils"

/**
 * String 满足 UTC，指的是满足 UTC 书写格式，但不是要完全对应 UTC，比如可以是 "yyyy-MM-dd" 这种单独日期的格式，当转换为 DateTime 后会用 0 补齐其他位置。
 * 需要注意的是，必须是按照 UTC 格式的顺序从前往后写。举个反例，"MM-dd"、"HH:mm:ss"就是不符合的。
 */

/**  */
const val DATETIME_MILLIS_0_UTC = "1970-01-01T00:00:00Z"

/** 格式 */
const val PATTERN_DATETIME_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"
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


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/** ********** Milliseconds from 1970-01-01T00:00:00Z ********** */

/**
 * Get milliseconds of current instant.
 *
 * @return The current milliseconds from 1970-01-01T00:00:00Z.
 */
/**  */
val currentDatetimeMillis get() = System.currentTimeMillis()

/**
 * Get joda datetime of current instant.
 *
 * @return The current joda datetime from 1970-01-01T00:00:00Z.
 */
private val currentDatetimeJoda get() = DateTime.now()

/** ********** 当前日期时间 String 指定 pattern ********** */

/**
 * Get datetime string with specified format pattern.
 *
 * @param pattern The specified format pattern.
 * @return The formatted datetime string.
 */
fun currentDatetimeString(pattern: String) = currentDatetimeJoda.datetimeString(pattern)

var currentDatetimeStringUtc = currentDatetimeString(PATTERN_DATETIME_UTC)

var currentDatetimeStringYmdH12msFullDividedByEnDash = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR12_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH24msFullDividedByEnDash = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR24_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH12msMillisFullDividedByEnDash = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR12_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH24msMillisFullDividedByEnDash = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR24_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")

var currentDatetimeStringYmdH12msFullDividedByDot = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR12_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH24msFullDividedByDot = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR24_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH12msMillisFullDividedByDot = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR12_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH24msMillisFullDividedByDot = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR24_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")

/** ********** Joda DateTime ********** */

/** ********** 通过毫秒数获取 DateTime 实例 ********** */

/**  */
private val Long.datetimeJoda get() = datetimeJoda(this)
private fun datetimeJoda(millis: Long) = DateTime(millis)

/** ********** 通过 Date 获取 DateTime 实例 ********** */

/**  */
private val Date.datetimeJoda get() = datetimeJoda(this)
private fun datetimeJoda(date: Date) = DateTime(date)

/** ********** 通过 Calendar 获取 DateTime 实例 ********** */

/**  */
private val Calendar.datetimeJoda get() = datetimeJoda(this)
private fun datetimeJoda(calendar: Calendar) = DateTime(calendar)

/** ********** 通过 String 获取 DateTime 实例 ********** */

/** String 必须满足 pattern */
@JvmName("jodaDatetime_")
private fun String.datetimeJoda(pattern: String) = datetimeJoda(this, pattern)
private fun datetimeJoda(datetimeString: String, pattern: String) = DateTime.parse(datetimeString, DateTimeFormat.forPattern(pattern))

/** String 必须满足 UTC */
private val String.datetimeJodaUtc get() = datetimeJodaUtc(this)
private fun datetimeJodaUtc(datetimeString: String) = DateTime(datetimeString)

/** ********** 通过 年、月、日、时、分、秒、毫秒 获取 DateTime 实例 ********** */

/**  */
private fun datetimeJoda(year: Int, monthOfYear: Int, dayOfMonth: Int, hourOfDay: Int, minuteOfHour: Int, secondOfMinute: Int = 0, millisOfSecond: Int = 0) = DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond)

/** ********** Joda LocalDate ********** */

/** String 必须满足 pattern */
private fun String.localDateJoda(pattern: String) = this.datetimeStringUtc(pattern).localDateJodaUtc

/** String 必须满足 UTC */
private val String.localDateJodaUtc get() = this.datetimeJodaUtc.toLocalDate()

/** ********** Joda LocalTime ********** */

/** String 必须满足 pattern */
private fun String.localTimeJoda(pattern: String) = this.datetimeStringUtc(pattern).localTimeJodaUtc

/** String 必须满足 UTC */
private val String.localTimeJodaUtc get() = this.datetimeJodaUtc.toLocalTime()

/** ********** Joda LocalDateTime ********** */

/** String 必须满足 pattern */
private fun String.localDatetimeJoda(pattern: String) = this.datetimeStringUtc(pattern).localDatetimeJodaUtc

/** String 必须满足 UTC */
private val String.localDatetimeJodaUtc get() = this.datetimeJodaUtc.toLocalDateTime()

/** ******************** Checker ******************** */

/** ********** 日期时间 String ?= 此刻 ********** */

/** String 必须满足 pattern */
@JvmName("equalNow_")
fun String.equalNow(pattern: String) = equalNow(this, pattern)
fun equalNow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalNowUtc

/** String 必须满足 UTC */
val String.equalNowUtc get() = this.datetimeJodaUtc.isEqualNow

/** ********** 日期时间 String ?< 此刻 ********** */

/** String 必须满足 pattern */
@JvmName("beforeNow_")
fun String.beforeNow(pattern: String) = beforeNow(this, pattern)
fun beforeNow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).beforeNowUtc

/** String 必须满足 UTC */
val String.beforeNowUtc get() = this.datetimeJodaUtc.isBeforeNow

/** ********** 日期时间 String ?> 此刻 ********** */

/** String 必须满足 pattern */
@JvmName("afterNow_")
fun String.afterNow(pattern: String) = afterNow(this, pattern)
fun afterNow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).afterNowUtc

/** String 必须满足 UTC */
val String.afterNowUtc get() = this.datetimeJodaUtc.isAfterNow

/** ********** 两个日期时间 String ? 同一日期 ********** */

/** String 必须满足 pattern */
@JvmName("equalDate_")
@JvmOverloads
fun String.equalDate(datetimeStringB: String, patternA: String, patternB: String = patternA) = equalDate(this, datetimeStringB, patternA, patternB)

@JvmOverloads
fun equalDate(datetimeStringA: String, datetimeStringB: String, patternA: String, patternB: String = patternA) = datetimeStringA.datetimeStringUtc(patternA).equalDateUtc(datetimeStringB.datetimeStringUtc(patternB))

/** String 必须满足 UTC */
@JvmName("equalDateUtc_")
fun String.equalDateUtc(datetimeStringUtc: String) = equalDateUtc(this, datetimeStringUtc)
fun equalDateUtc(datetimeStringUtcA: String, datetimeStringUtcB: String) = datetimeStringUtcA.localDateJodaUtc == datetimeStringUtcB.localDateJodaUtc

/** ********** 两个日期时间 String ? 同一时间 ********** */

/** String 必须满足 pattern */
@JvmName("equalTime_")
@JvmOverloads
fun String.equalTime(datetimeStringB: String, patternA: String, patternB: String = patternA) = equalTime(this, datetimeStringB, patternA, patternB)

@JvmOverloads
fun equalTime(datetimeStringA: String, datetimeStringB: String, patternA: String, patternB: String = patternA) = datetimeStringA.datetimeStringUtc(patternA).equalTimeUtc(datetimeStringB.datetimeStringUtc(patternB))

/** String 必须满足 UTC */
@JvmName("equalTimeUtc_")
fun String.equalTimeUtc(datetimeStringUtc: String) = equalTimeUtc(this, datetimeStringUtc)
fun equalTimeUtc(datetimeStringUtcA: String, datetimeStringUtcB: String) = datetimeStringUtcA.localTimeJodaUtc == datetimeStringUtcB.localTimeJodaUtc

/** ********** 日期时间 String ?= 今年 ********** */

/** ********** 日期时间 String ?= 今月 ********** */

/** ********** 日期时间 String ?= 今天 ********** */

/** String 必须满足 pattern */
@JvmName("equalToday_")
fun String.equalToday(pattern: String) = equalToday(this, pattern)
fun equalToday(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalTodayUtc

/** String 必须满足 UTC */
val String.equalTodayUtc get() = equalTodayUtc(this)
fun equalTodayUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateUtc(currentDatetimeStringUtc)

/** ********** 日期时间 String ?= 昨天 ********** */

/** String 必须满足 pattern */
@JvmName("equalYesterday_")
fun String.equalYesterday(pattern: String) = equalYesterday(this, pattern)
fun equalYesterday(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalYesterdayUtc

/** String 必须满足 UTC */
val String.equalYesterdayUtc get() = equalYesterdayUtc(this)
fun equalYesterdayUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateUtc(currentDatetimeJoda.minusDays(1).datetimeStringUtc)

/** ********** 日期时间 String ?= 前天 ********** */

/** String 必须满足 pattern */
@JvmName("equalDayBeforeYesterday_")
fun String.equalDayBeforeYesterday(pattern: String) = equalDayBeforeYesterday(this, pattern)
fun equalDayBeforeYesterday(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalDayBeforeYesterdayUtc

/** String 必须满足 UTC */
val String.equalDayBeforeYesterdayUtc get() = equalDayBeforeYesterdayUtc(this)
fun equalDayBeforeYesterdayUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateUtc(currentDatetimeJoda.minusDays(2).datetimeStringUtc)

/** ********** 日期时间 String ?= 明天 ********** */

/** String 必须满足 pattern */
@JvmName("equalTomorrow_")
fun String.equalTomorrow(pattern: String) = equalTomorrow(this, pattern)
fun equalTomorrow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalTomorrowUtc

/** String 必须满足 UTC */
val String.equalTomorrowUtc get() = equalTomorrowUtc(this)
fun equalTomorrowUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateUtc(currentDatetimeJoda.plusDays(1).datetimeStringUtc)

/** ********** 日期时间 String ?= 后天 ********** */

/** String 必须满足 pattern */
@JvmName("equalDayAfterTomorrow_")
fun String.equalDayAfterTomorrow(pattern: String) = equalDayAfterTomorrow(this, pattern)
fun equalDayAfterTomorrow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalDayAfterTomorrowUtc

/** String 必须满足 UTC */
val String.equalDayAfterTomorrowUtc get() = equalDayAfterTomorrowUtc(this)
fun equalDayAfterTomorrowUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateUtc(currentDatetimeJoda.plusDays(2).datetimeStringUtc)


/** ********** 两个日期时间 String ? 同一年份 ********** */

/** ********** 两个日期时间 String ? 同一月份 ********** */

/** ********** 两个日期时间 String ? 同一日份 ********** */

/** ******************** Converter ******************** */

/** ********** 日期时间 Joda -> 日期时间毫秒数 ********** */

private val DateTime.datetimeMillis get() = this.millis

/** ********** 日期时间 Joda -> 日期时间 String UTC 格式 ********** */

private val DateTime.datetimeStringUtc get() = this.toString()
// private val DateTime.datetimeStringUtc get() = this.datetimeString(PATTERN_DATETIME_UTC)

/** ********** 日期时间 Joda -> 日期时间 String pattern 格式 ********** */

private fun DateTime.datetimeString(pattern: String) = this.toString(pattern)

/** ********** 日期时间 String -> 日期时间毫秒数 ********** */

/** String 必须满足 pattern */
@JvmName("datetimeMillis_")
fun String.datetimeMillis(pattern: String) = datetimeMillis(this, pattern)
fun datetimeMillis(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).datetimeMillisUtc

/** String 必须满足 UTC */
val String.datetimeMillisUtc get() = datetimeMillisUtc(this)
fun datetimeMillisUtc(datetimeStringUtc: String) = datetimeStringUtc.datetimeJodaUtc.datetimeMillis

/** ********** 日期时间 String -> 日期时间 String pattern 格式 ********** */

/** String 必须满足 pattern */
@JvmName("datetimeString_")
fun String.datetimeString(patternFrom: String, patternTo: String) = datetimeString(this, patternFrom, patternTo)
fun datetimeString(datetimeString: String, patternFrom: String, patternTo: String) = datetimeString.datetimeJoda(patternFrom).datetimeString(patternTo)

/** String 必须满足 UTC */
// @JvmName("datetimeString_")
// fun String.datetimeString(pattern: String) = datetimeString(this, pattern)
// fun datetimeString(datetimeStringUtc: String, pattern: String) = datetimeStringUtc.datetimeJodaUtc.datetimeString(pattern)
// fun datetimeString(datetimeStringUtc: String, pattern: String) = datetimeStringUtc.datetimeJodaUtc.datetimeString(DateTimeFormat.forPattern(pattern))

/** ********** 日期时间 String -> 日期时间 String UTC 格式 ********** */

/**  */
@JvmName("datetimeStringUtc_")
fun String.datetimeStringUtc(pattern: String) = datetimeStringUtc(this, pattern)
fun datetimeStringUtc(datetimeString: String, pattern: String) = datetimeString.datetimeString(pattern, PATTERN_DATETIME_UTC)


/** ******************** Processor ******************** */

/** ********** 日期时间 String 加减天数 ********** */

// fun String.plusMinusDays(days: Int, pattern: String) = this.
