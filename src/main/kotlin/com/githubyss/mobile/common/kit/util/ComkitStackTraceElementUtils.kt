package com.githubyss.mobile.common.kit.util

/**
 * ComkitStackTraceElementUtils.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitStackTraceElementUtils {
    fun getStackTrace(): StackTraceElement {
        return Thread.currentThread().stackTrace[4]
    }

    fun generateValues(element: StackTraceElement): Array<String> {
        var values = Array(2, { String() })

        var traceElement = element
        var stringBuilder = StringBuilder()
        val className = traceElement.className
        val fileName = traceElement.fileName

        stringBuilder.append(className.substring(className.lastIndexOf(".") + 1))
                .append(".")
                .append(traceElement.methodName)
                .append(" (").append(fileName).append(":").append(traceElement.lineNumber).append(") ")

        val tag = stringBuilder.toString()

        values[0] = tag
        values[1] = fileName
        return values
    }
}
