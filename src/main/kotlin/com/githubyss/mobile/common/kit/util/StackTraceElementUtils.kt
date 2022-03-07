package com.githubyss.mobile.common.kit.util


/**
 * StackTraceElementUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:16:00
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "StackTraceElementUtils"


/** ****************************** Functions ****************************** */

/**
 *
 *
 * @param
 * @return
 */
fun getStackTrace(): StackTraceElement {
    return Thread.currentThread().stackTrace[4]
}

/**
 *
 *
 * @param element
 * @return
 */
fun generateValues(element: StackTraceElement?): Array<String> {
    val values = Array(2) { String() }

    element ?: return values

    val stringBuilder = StringBuilder()
    val className = element.className
    val fileName = element.fileName

    stringBuilder.append(className.substring(className.lastIndexOf("") + 1))
        .append("")
        .append(element.methodName)
        .append(" (")
        .append(fileName)
        .append(":")
        .append(element.lineNumber)
        .append(") ")

    val tag = stringBuilder.toString()

    values[0] = tag
    values[1] = fileName
    return values
}
