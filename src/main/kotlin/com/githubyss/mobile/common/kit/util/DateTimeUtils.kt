@file:JvmName("DateTimeUtils")

package com.githubyss.mobile.common.kit.util

import org.joda.time.DateTime
import org.joda.time.Days
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
/** 1970-01-01T08:00:00.000+08:00 */
const val PATTERN_DATETIME_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ"

/** 1970-01-01T08:00:00.000+0800 */
const val PATTERN_DATETIME_UTC_ABBR = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
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

/**  */
val currentDatetimeMillis
    get() = System.currentTimeMillis()

/**  */
private val currentDatetimeJoda get() = DateTime.now()

/** ********** 当前日期时间 String 指定 pattern ********** */

/**
 * Get datetime string with specified format pattern.
 *
 * @param pattern The specified format pattern.
 * @return The formatted datetime string.
 */
fun currentDatetimeString(pattern: String) = currentDatetimeJoda.datetimeString(pattern)

@JvmField
var currentDatetimeStringUtc = currentDatetimeJoda.datetimeStringUtc

@JvmField
var currentDatetimeStringYmdH12msFullDividedByEnDash = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR12_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")

@JvmField
var currentDatetimeStringYmdH24msFullDividedByEnDash = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR24_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH12msMillisFullDividedByEnDash = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR12_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH24msMillisFullDividedByEnDash = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_EN_DASH $PATTERN_TIME_HOUR24_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")

var currentDatetimeStringYmdH12msFullDividedByDot = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR12_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH24msFullDividedByDot = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR24_MINUTE_SECOND_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH12msMillisFullDividedByDot = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR12_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")
var currentDatetimeStringYmdH24msMillisFullDividedByDot = currentDatetimeString("$PATTERN_DATE_YEAR_MONTH_DAY_FULL_DIVIDED_BY_DOT $PATTERN_TIME_HOUR24_MINUTE_SECOND_MILLIS_FULL_DIVIDED_BY_COLON")

/** ******************** Checker ******************** */

/** ********** 日期时间 String ?= 此刻 ********** */

/** String 必须满足 pattern */
@JvmName("equalNow_")
fun String.equalNow(pattern: String) = equalNow(this, pattern)
fun equalNow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalNowForUtc

/** String 必须满足 UTC */
val String.equalNowForUtc get() = this.datetimeJodaForUtc.isEqualNow

/** ********** 日期时间 String ?< 此刻 ********** */

/** String 必须满足 pattern */
@JvmName("beforeNow_")
fun String.beforeNow(pattern: String) = beforeNow(this, pattern)
fun beforeNow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).beforeNowForUtc

/** String 必须满足 UTC */
val String.beforeNowForUtc get() = this.datetimeJodaForUtc.isBeforeNow

/** ********** 日期时间 String ?> 此刻 ********** */

/** String 必须满足 pattern */
@JvmName("afterNow_")
fun String.afterNow(pattern: String) = afterNow(this, pattern)
fun afterNow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).afterNowForUtc

/** String 必须满足 UTC */
val String.afterNowForUtc get() = this.datetimeJodaForUtc.isAfterNow

/** ********** 两个日期时间 String ? 同一日期 ********** */

/** String 必须满足 pattern */
@JvmName("equalDate_")
@JvmOverloads
fun String.equalDate(datetimeStringB: String, patternA: String, patternB: String = patternA) = equalDate(this, datetimeStringB, patternA, patternB)

@JvmOverloads
fun equalDate(datetimeStringA: String, datetimeStringB: String, patternA: String, patternB: String = patternA) = datetimeStringA.datetimeStringUtc(patternA).equalDateForUtc(datetimeStringB.datetimeStringUtc(patternB))

/** String 必须满足 UTC */
@JvmName("equalDateForUtc_")
fun String.equalDateForUtc(datetimeStringUtc: String) = equalDateForUtc(this, datetimeStringUtc)
fun equalDateForUtc(datetimeStringUtcA: String, datetimeStringUtcB: String) = datetimeStringUtcA.localDateJodaForUtc == datetimeStringUtcB.localDateJodaForUtc

/** ********** 两个日期时间 String ? 同一时间 ********** */

/** String 必须满足 pattern */
@JvmName("equalTime_")
@JvmOverloads
fun String.equalTime(datetimeStringB: String, patternA: String, patternB: String = patternA) = equalTime(this, datetimeStringB, patternA, patternB)

@JvmOverloads
fun equalTime(datetimeStringA: String, datetimeStringB: String, patternA: String, patternB: String = patternA) = datetimeStringA.datetimeStringUtc(patternA).equalTimeForUtc(datetimeStringB.datetimeStringUtc(patternB))

/** String 必须满足 UTC */
@JvmName("equalTimeForUtc_")
fun String.equalTimeForUtc(datetimeStringUtc: String) = equalTimeForUtc(this, datetimeStringUtc)
fun equalTimeForUtc(datetimeStringUtcA: String, datetimeStringUtcB: String) = datetimeStringUtcA.localTimeJodaForUtc == datetimeStringUtcB.localTimeJodaForUtc

/** ********** 日期时间 String ?= 今年 ********** */

/** ********** 日期时间 String ?= 今月 ********** */

/** ********** 日期时间 String ?= 今天 ********** */

/** String 必须满足 pattern */
@JvmName("equalToday_")
fun String.equalToday(pattern: String) = equalToday(this, pattern)
fun equalToday(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalTodayForUtc

/** String 必须满足 UTC */
val String.equalTodayForUtc get() = equalTodayForUtc(this)
fun equalTodayForUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateForUtc(currentDatetimeStringUtc)

/** ********** 日期时间 String ?= 昨天 ********** */

/** String 必须满足 pattern */
@JvmName("equalYesterday_")
fun String.equalYesterday(pattern: String) = equalYesterday(this, pattern)
fun equalYesterday(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalYesterdayForUtc

/** String 必须满足 UTC */
val String.equalYesterdayForUtc get() = equalYesterdayForUtc(this)
fun equalYesterdayForUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateForUtc(currentDatetimeJoda.minusDays(1).datetimeStringUtc)

/** ********** 日期时间 String ?= 前天 ********** */

/** String 必须满足 pattern */
@JvmName("equalDayBeforeYesterday_")
fun String.equalDayBeforeYesterday(pattern: String) = equalDayBeforeYesterday(this, pattern)
fun equalDayBeforeYesterday(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalDayBeforeYesterdayForUtc

/** String 必须满足 UTC */
val String.equalDayBeforeYesterdayForUtc get() = equalDayBeforeYesterdayForUtc(this)
fun equalDayBeforeYesterdayForUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateForUtc(currentDatetimeJoda.minusDays(2).datetimeStringUtc)

/** ********** 日期时间 String ?= 明天 ********** */

/** String 必须满足 pattern */
@JvmName("equalTomorrow_")
fun String.equalTomorrow(pattern: String) = equalTomorrow(this, pattern)
fun equalTomorrow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalTomorrowForUtc

/** String 必须满足 UTC */
val String.equalTomorrowForUtc get() = equalTomorrowForUtc(this)
fun equalTomorrowForUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateForUtc(currentDatetimeJoda.plusDays(1).datetimeStringUtc)

/** ********** 日期时间 String ?= 后天 ********** */

/** String 必须满足 pattern */
@JvmName("equalDayAfterTomorrow_")
fun String.equalDayAfterTomorrow(pattern: String) = equalDayAfterTomorrow(this, pattern)
fun equalDayAfterTomorrow(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).equalDayAfterTomorrowForUtc

/** String 必须满足 UTC */
val String.equalDayAfterTomorrowForUtc get() = equalDayAfterTomorrowForUtc(this)
fun equalDayAfterTomorrowForUtc(datetimeStringUtc: String) = datetimeStringUtc.equalDateForUtc(currentDatetimeJoda.plusDays(2).datetimeStringUtc)

/** ********** 日期时间 String ?> 一个月（30天） ********** */

/** String 必须满足 pattern */
@JvmName("beforeMonth_")
fun String.beforeMonth(pattern: String) = beforeMonth(this, pattern)
fun beforeMonth(datetimeString: String, patternTo: String) = datetimeString.datetimeStringUtc(patternTo).beforeMonthForUtc

/** String 必须满足 UTC */
val String.beforeMonthForUtc get() = beforeMonthForUtc(this)
fun beforeMonthForUtc(datetimeStringUtc: String) = Days.daysBetween(datetimeStringUtc.datetimeJodaForUtc, currentDatetimeJoda).days > 30

/** ********** 两个日期时间 String ? 同一年份 ********** */

/** ********** 两个日期时间 String ? 同一月份 ********** */

/** ********** 两个日期时间 String ? 同一日份 ********** */

/** ******************** Converter ******************** */

/** ********** 日期时间 Millis -> 日期时间 Joda ********** */

/**  */
private val Long.datetimeJoda get() = datetimeJoda(this)
private fun datetimeJoda(millis: Long) = DateTime(millis)

/** ********** 日期时间 Date -> 日期时间 Joda ********** */

/**  */
private val Date.datetimeJoda get() = datetimeJoda(this)
private fun datetimeJoda(date: Date) = DateTime(date)

/** ********** 日期时间 Calendar -> 日期时间 Joda ********** */

/**  */
private val Calendar.datetimeJoda get() = datetimeJoda(this)
private fun datetimeJoda(calendar: Calendar) = DateTime(calendar)

/** ********** 年、月、日、时、分、秒、毫秒 -> 日期时间 Joda ********** */

/**  */
private fun datetimeJoda(year: Int, monthOfYear: Int, dayOfMonth: Int, hourOfDay: Int, minuteOfHour: Int, secondOfMinute: Int = 0, millisOfSecond: Int = 0) = DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute, millisOfSecond)

/** ********** 日期时间 String -> 日期时间 Joda ********** */

/** String 必须满足 pattern */
@JvmName("jodaDatetime_")
private fun String.datetimeJoda(pattern: String) = datetimeJoda(this, pattern)
private fun datetimeJoda(datetimeString: String, pattern: String) = DateTime.parse(datetimeString, DateTimeFormat.forPattern(pattern))

/** String 必须满足 UTC */
private val String.datetimeJodaForUtc get() = datetimeJodaForUtc(this)
private fun datetimeJodaForUtc(datetimeString: String) = DateTime(datetimeString)

/** ********** 日期时间 String -> Joda LocalDate ********** */

/** String 必须满足 pattern */
private fun String.localDateJoda(pattern: String) = this.datetimeStringUtc(pattern).localDateJodaForUtc

/** String 必须满足 UTC */
private val String.localDateJodaForUtc get() = this.datetimeJodaForUtc.toLocalDate()

/** ********** 日期时间 String -> Joda LocalTime ********** */

/** String 必须满足 pattern */
private fun String.localTimeJoda(pattern: String) = this.datetimeStringUtc(pattern).localTimeJodaForUtc

/** String 必须满足 UTC */
private val String.localTimeJodaForUtc get() = this.datetimeJodaForUtc.toLocalTime()

/** ********** 日期时间 String -> Joda LocalDateTime ********** */

/** String 必须满足 pattern */
private fun String.localDatetimeJoda(pattern: String) = this.datetimeStringUtc(pattern).localDatetimeJodaForUtc

/** String 必须满足 UTC */
private val String.localDatetimeJodaForUtc get() = this.datetimeJodaForUtc.toLocalDateTime()

/** ********** 日期时间 Joda -> 日期时间 Millis ********** */

/**  */
private val DateTime.datetimeMillis get() = this.millis

/** ********** 日期时间 String -> 日期时间 Millis ********** */

/** String 必须满足 pattern */
@JvmName("datetimeMillis_")
fun String.datetimeMillis(pattern: String) = datetimeMillis(this, pattern)
fun datetimeMillis(datetimeString: String, pattern: String) = datetimeString.datetimeStringUtc(pattern).datetimeMillisForUtc

/** String 必须满足 UTC */
val String.datetimeMillisForUtc get() = datetimeMillisForUtc(this)
fun datetimeMillisForUtc(datetimeStringUtc: String) = datetimeStringUtc.datetimeJodaForUtc.datetimeMillis

/** ********** 日期时间 Joda -> 日期时间 String UTC 格式 ********** */

/**  */
private val DateTime.datetimeStringUtc get() = this.toString()
// private val DateTime.datetimeStringUtc get() = this.datetimeString(PATTERN_DATETIME_UTC)

/** ********** 日期时间 Joda -> 日期时间 String pattern 格式 ********** */

/**  */
private fun DateTime.datetimeString(pattern: String) = this.toString(pattern)

/** ********** 日期时间 Millis -> 日期时间 String pattern 格式 ********** */

/**  */
@JvmName("datetimeString_")
fun Long.datetimeString(pattern: String) = datetimeString(this, pattern)
fun datetimeString(millis: Long, pattern: String) = millis.datetimeJoda.datetimeString(pattern)

/** ********** 日期时间 String -> 日期时间 String pattern 格式 ********** */

/** String 必须满足 pattern */
@JvmName("datetimeString_")
fun String.datetimeString(patternFrom: String, patternTo: String) = datetimeString(this, patternFrom, patternTo)
fun datetimeString(datetimeString: String, patternFrom: String, patternTo: String) = datetimeString.datetimeJoda(patternFrom).datetimeString(patternTo)

/** String 必须满足 UTC */
@JvmName("datetimeStringForUtc_")
fun String.datetimeStringForUtc(pattern: String) = datetimeStringForUtc(this, pattern)
fun datetimeStringForUtc(datetimeStringUtc: String, pattern: String) = datetimeStringUtc.datetimeJodaForUtc.datetimeString(pattern)
// fun datetimeString(datetimeStringUtc: String, pattern: String) = datetimeStringUtc.datetimeJodaForUtc.datetimeString(DateTimeFormat.forPattern(pattern))

/** ********** 日期时间 Millis -> 日期时间 String UTC 格式 ********** */

/**  */
val Long.datetimeStringUtc get() = datetimeStringUtc(this)
fun datetimeStringUtc(millis: Long) = millis.datetimeString(PATTERN_DATETIME_UTC)

/** ********** 日期时间 String -> 日期时间 String UTC 格式 ********** */

/**  */
@JvmName("datetimeStringUtc_")
fun String.datetimeStringUtc(pattern: String) = datetimeStringUtc(this, pattern)
fun datetimeStringUtc(datetimeString: String, pattern: String) = datetimeString.datetimeString(pattern, PATTERN_DATETIME_UTC)


/** ******************** Processor ******************** */

/** ********** 日期时间 String 加减天数 ********** */

// fun String.plusMinusDays(days: Int, pattern: String) = this.
