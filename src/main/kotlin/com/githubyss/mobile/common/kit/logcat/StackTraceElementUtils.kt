package com.githubyss.mobile.common.kit.logcat


/**
 * StackTraceElementUtils
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object StackTraceElementUtils {
    fun getStackTrace(): StackTraceElement {
        return Thread.currentThread().stackTrace[4]
    }
    
    fun generateValues(element: StackTraceElement): Array<String> {
        val values = Array(2) { String() }
        
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
}
