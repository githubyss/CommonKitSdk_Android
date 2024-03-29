package com.githubyss.common.kit.util

import androidx.annotation.NonNull
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader


/**
 * ShellUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/04 19:34:24
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "ShellUtils"

private val LINE_SEP = System.getProperty("line.separator")


/** ****************************** Functions ****************************** */

/** ******************** Processor ******************** */

/** ********** execCmdAsync ********** */

/**
 * Execute the command asynchronously.
 *
 * @param command         The command.
 * @param isRooted        True to use root, false otherwise.
 * @param isNeedResultMsg True to return the message of result, false otherwise.
 * @param callback        The callback.
 * @return the task
 */
fun execCmdAsync(command: String?, isRooted: Boolean, isNeedResultMsg: Boolean = true, callback: TaskRunnable.Callback<CommandResult?>): TaskRunnable<CommandResult?>? {
    return execCmdAsync(arrayOf(command), isRooted, isNeedResultMsg, callback)
}

/**
 * Execute the command asynchronously.
 *
 * @param commands        The commands.
 * @param isRooted        True to use root, false otherwise.
 * @param isNeedResultMsg True to return the message of result, false otherwise.
 * @param callback        The callback.
 * @return the task
 */
fun execCmdAsync(commands: List<String?>?, isRooted: Boolean, isNeedResultMsg: Boolean = true, callback: TaskRunnable.Callback<CommandResult?>): TaskRunnable<CommandResult?>? {
    return execCmdAsync(commands?.toTypedArray(), isRooted, isNeedResultMsg, callback)
}

/**
 * Execute the command asynchronously.
 *
 * @param commands        The commands.
 * @param isRooted        True to use root, false otherwise.
 * @param isNeedResultMsg True to return the message of result, false otherwise.
 * @param callback        The callback.
 * @return the task
 */
fun execCmdAsync(commands: Array<String?>?, isRooted: Boolean, isNeedResultMsg: Boolean = true, @NonNull callback: TaskRunnable.Callback<CommandResult?>): TaskRunnable<CommandResult?>? {
    return doAsync(object : TaskRunnable<CommandResult?>(callback) {
        override fun doInBackground(): CommandResult? {
            return execCmd(commands, isRooted, isNeedResultMsg)
        }
    })
}

/** ********** execCmd ********** */

/**
 * Execute the command.
 *
 * @param command         The command.
 * @param isRooted        True to use root, false otherwise.
 * @param isNeedResultMsg True to return the message of result, false otherwise.
 * @return the single [CommandResult] instance
 */
fun execCmd(command: String?, isRooted: Boolean, isNeedResultMsg: Boolean = true): CommandResult? {
    return execCmd(arrayOf(command), isRooted, isNeedResultMsg)
}

/**
 * Execute the command.
 *
 * @param commands        The commands.
 * @param isRooted        True to use root, false otherwise.
 * @param isNeedResultMsg True to return the message of result, false otherwise.
 * @return the single [CommandResult] instance
 */
fun execCmd(commands: List<String?>?, isRooted: Boolean, isNeedResultMsg: Boolean = true): CommandResult? {
    return execCmd(commands?.toTypedArray(), isRooted, isNeedResultMsg)
}

/**
 * Execute the command.
 *
 * @param commands        The commands.
 * @param isRooted        True to use root, false otherwise.
 * @param isNeedResultMsg True to return the message of result, false otherwise.
 * @return the single [CommandResult] instance
 */
fun execCmd(commands: Array<String?>?, isRooted: Boolean, isNeedResultMsg: Boolean = true): CommandResult? {
    var result = -1
    if (commands == null || commands.isEmpty()) {
        return CommandResult(result, "", "")
    }
    var process: Process? = null
    var successResult: BufferedReader? = null
    var errorResult: BufferedReader? = null
    var successMsg: StringBuilder? = null
    var errorMsg: StringBuilder? = null
    var os: DataOutputStream? = null
    try {
        process = Runtime.getRuntime()
            .exec(if (isRooted) "su" else "sh")
        os = DataOutputStream(process.outputStream)
        for (command in commands) {
            if (command == null) continue
            os.write(command.toByteArray())
            os.writeBytes(LINE_SEP)
            os.flush()
        }
        os.writeBytes("exit$LINE_SEP")
        os.flush()
        result = process.waitFor()
        if (isNeedResultMsg) {
            successMsg = StringBuilder()
            errorMsg = StringBuilder()
            successResult = BufferedReader(InputStreamReader(process.inputStream, "UTF-8"))
            errorResult = BufferedReader(InputStreamReader(process.errorStream, "UTF-8"))
            var line: String?
            if (successResult.readLine()
                    .also { line = it } != null) {
                successMsg.append(line)
                while (successResult.readLine()
                        .also { line = it } != null) {
                    successMsg.append(LINE_SEP)
                        .append(line)
                }
            }
            if (errorResult.readLine()
                    .also { line = it } != null) {
                errorMsg.append(line)
                while (errorResult.readLine()
                        .also { line = it } != null) {
                    errorMsg.append(LINE_SEP)
                        .append(line)
                }
            }
        }
    }
    catch (e: Exception) {
        logE(TAG, t = e)
    }
    finally {
        try {
            os?.close()
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
        try {
            successResult?.close()
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
        try {
            errorResult?.close()
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
        process?.destroy()
    }
    return CommandResult(result, successMsg?.toString() ?: "", errorMsg?.toString() ?: "")
}


/** ****************************** Class ****************************** */

/**
 * The result of command.
 */
class CommandResult(var result: Int, var successMsg: String, var errorMsg: String) {
    override fun toString(): String {
        return "result: $result\nsuccessMsg: $successMsg\nerrorMsg: $errorMsg"
    }
}
