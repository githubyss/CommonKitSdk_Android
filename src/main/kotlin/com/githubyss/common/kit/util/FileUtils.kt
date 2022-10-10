package com.githubyss.common.kit.util

import java.io.*
import java.net.URL
import java.security.DigestInputStream
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.net.ssl.HttpsURLConnection


/**
 * FileUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/03 16:44:19
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "FileUtils"

private val LINE_SEP = System.getProperty("line.separator")


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/**
 * Return the file by path.
 *
 * @param filePath The path of file.
 * @return the file
 */
fun getFileByPath(filePath: String?): File? {
    filePath ?: return null
    if (isSpace(filePath)) return null

    return File(filePath)
}

/** ********** getFileLastModified ********** */

/**
 * Return the time that the file was last modified.
 *
 * @param filePath The path of file.
 * @return the time that the file was last modified
 */
fun getFileLastModified(filePath: String?): Long {
    return getFileLastModified(getFileByPath(filePath))
}

/**
 * Return the time that the file was last modified.
 *
 * @param file The file.
 * @return the time that the file was last modified
 */
fun getFileLastModified(file: File?): Long {
    file ?: return -1

    return file.lastModified()
}

/** ********** getFileCharsetSimple ********** */

/**
 * Return the charset of file simply.
 *
 * @param filePath The path of file.
 * @return the charset of file simply
 */
fun getFileCharsetSimple(filePath: String?): String {
    return getFileCharsetSimple(getFileByPath(filePath))
}

/**
 * Return the charset of file simply.
 *
 * @param file The file.
 * @return the charset of file simply
 */
fun getFileCharsetSimple(file: File?): String {
    file ?: return ""

    var p = 0
    var `is`: InputStream? = null
    try {
        `is` = BufferedInputStream(FileInputStream(file))
        p = (`is`.read() shl 8) + `is`.read()
    }
    catch (e: IOException) {
        logE(TAG, t = e)
    }
    finally {
        try {
            `is`?.close()
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
    }
    return when (p) {
        0xefbb -> "UTF-8"
        0xfffe -> "Unicode"
        0xfeff -> "UTF-16BE"
        else -> "GBK"
    }
}

/** ********** getFileLines ********** */

/**
 * Return the number of lines of file.
 *
 * @param filePath The path of file.
 * @return the number of lines of file
 */
fun getFileLines(filePath: String?): Int {
    return getFileLines(getFileByPath(filePath))
}

/**
 * Return the number of lines of file.
 *
 * @param file The file.
 * @return the number of lines of file
 */
fun getFileLines(file: File?): Int {
    file ?: return -1

    var count = 1
    var `is`: InputStream? = null
    try {
        `is` = BufferedInputStream(FileInputStream(file))
        val buffer = ByteArray(1024)
        var readChars: Int
        if (LINE_SEP?.endsWith("\n") ?: return -1) {
            while (`is`.read(buffer, 0, 1024).also { readChars = it } != -1) {
                for (i in 0 until readChars) {
                    if (buffer[i].toChar() == '\n') ++count
                }
            }
        }
        else {
            while (`is`.read(buffer, 0, 1024).also { readChars = it } != -1) {
                for (i in 0 until readChars) {
                    if (buffer[i].toChar() == '\r') ++count
                }
            }
        }
    }
    catch (e: IOException) {
        logE(TAG, t = e)
    }
    finally {
        try {
            `is`?.close()
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
    }
    return count
}

/** ********** getDirSize ********** */

/**
 * Return the size of directory.
 *
 * @param dirPath The path of directory.
 * @return the size of directory
 */
fun getDirSize(dirPath: String?): String {
    return getDirSize(getFileByPath(dirPath))
}

/**
 * Return the size of directory.
 *
 * @param dir The directory.
 * @return the size of directory
 */
fun getDirSize(dir: File?): String {
    val len = getDirLength(dir)
    if (len == -1L) return ""
    return byte2FitMemorySize(len)
}

/** ********** getFileSize ********** */

/**
 * Return the length of file.
 *
 * @param filePath The path of file.
 * @return the length of file
 */
fun getFileSize(filePath: String?): String {
    val len = getFileLength(filePath)
    if (len == -1L) return ""
    return byte2FitMemorySize(len)
}

/**
 * Return the length of file.
 *
 * @param file The file.
 * @return the length of file
 */
fun getFileSize(file: File?): String {
    val len = getFileLength(file)
    if (len == -1L) return ""
    return byte2FitMemorySize(len)
}

/** ********** getDirLength ********** */

/**
 * Return the length of directory.
 *
 * @param dirPath The path of directory.
 * @return the length of directory
 */
fun getDirLength(dirPath: String?): Long {
    return getDirLength(getFileByPath(dirPath))
}

/**
 * Return the length of directory.
 *
 * @param dir The directory.
 * @return the length of directory
 */
fun getDirLength(dir: File?): Long {
    dir ?: return -1
    if (!isDir(dir)) return -1

    val files = dir.listFiles() ?: return -1
    var len: Long = 0
    if (files.isNotEmpty()) {
        for (file in files) {
            len += if (file.isDirectory) {
                getDirLength(file)
            }
            else {
                file.length()
            }
        }
    }
    return len
}

/** ********** getFileLength ********** */

/**
 * Return the length of file.
 *
 * @param filePath The path of file.
 * @return the length of file
 */
fun getFileLength(filePath: String?): Long {
    filePath ?: return -1

    val isURL = filePath.matches(Regex("[a-zA-z]+://[^\\s]*"))
    if (isURL) {
        try {
            val conn = URL(filePath).openConnection() as HttpsURLConnection
            conn.setRequestProperty("Accept-Encoding", "identity")
            conn.connect()
            return if (conn.responseCode == 200) {
                conn.contentLength.toLong()
            }
            else -1
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
    }
    return getFileLength(getFileByPath(filePath))
}

/**
 * Return the length of file.
 *
 * @param file The file.
 * @return the length of file
 */
fun getFileLength(file: File?): Long {
    file ?: return -1
    if (!isFile(file)) return -1

    return file.length()
}

/** ********** getFileMD5ToString ********** */

/**
 * Return the MD5 of file.
 *
 * @param filePath The path of file.
 * @return the md5 of file
 */
fun getFileMD5ToString(filePath: String?): String {
    filePath ?: return ""

    val file = if (isSpace(filePath)) null else File(filePath)
    return getFileMD5ToString(file)
}

/**
 * Return the MD5 of file.
 *
 * @param file The file.
 * @return the md5 of file
 */
fun getFileMD5ToString(file: File?): String {
    return bytes2HexString(getFileMD5(file))
}

/** ********** getFileMD5 ********** */

/**
 * Return the MD5 of file.
 *
 * @param filePath The path of file.
 * @return the md5 of file
 */
fun getFileMD5(filePath: String?): ByteArray? {
    return getFileMD5(getFileByPath(filePath))
}

/**
 * Return the MD5 of file.
 *
 * @param file The file.
 * @return the md5 of file
 */
fun getFileMD5(file: File?): ByteArray? {
    file ?: return null

    var dis: DigestInputStream? = null
    try {
        val fis = FileInputStream(file)
        var md = MessageDigest.getInstance("MD5")
        dis = DigestInputStream(fis, md)
        val buffer = ByteArray(1024 * 256)
        while (true) {
            if (dis.read(buffer) <= 0) break
        }
        md = dis.messageDigest
        return md.digest()
    }
    catch (e: NoSuchAlgorithmException) {
        logE(TAG, t = e)
    }
    catch (e: IOException) {
        logE(TAG, t = e)
    }
    finally {
        try {
            dis?.close()
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
    }
    return null
}

/** ********** getDirName ********** */

/**
 * Return the file's path of directory.
 *
 * @param dirPath The path of file.
 * @return the file's path of directory
 */
fun getDirName(dirPath: String?): String {
    dirPath ?: return ""
    if (isSpace(dirPath)) return ""

    val lastSep = dirPath.lastIndexOf(File.separator)
    return if (lastSep == -1) "" else dirPath.substring(0, lastSep + 1)
}

/**
 * Return the file's path of directory.
 *
 * @param dir The file.
 * @return the file's path of directory
 */
fun getDirName(dir: File?): String {
    dir ?: return ""

    return getDirName(dir.absolutePath)
}

/** ********** getFileName ********** */

/**
 * Return the name of file.
 *
 * @param filePath The path of file.
 * @return the name of file
 */
fun getFileName(filePath: String?): String {
    filePath ?: return ""
    if (isSpace(filePath)) return ""

    val lastSep = filePath.lastIndexOf(File.separator)
    return if (lastSep == -1) filePath else filePath.substring(lastSep + 1)
}

/**
 * Return the name of file.
 *
 * @param file The file.
 * @return the name of file
 */
fun getFileName(file: File?): String {
    file ?: return ""

    return getFileName(file.absolutePath)
}

/** ********** getFileNameNoExtension ********** */

/**
 * Return the name of file without extension.
 *
 * @param filePath The path of file.
 * @return the name of file without extension
 */
fun getFileNameNoExtension(filePath: String?): String {
    filePath ?: return ""
    if (isSpace(filePath)) return ""

    val lastPoi = filePath.lastIndexOf('.')
    val lastSep = filePath.lastIndexOf(File.separator)
    if (lastSep == -1) {
        return if (lastPoi == -1) filePath else filePath.substring(0, lastPoi)
    }
    return if (lastPoi == -1 || lastSep > lastPoi) {
        filePath.substring(lastSep + 1)
    }
    else filePath.substring(lastSep + 1, lastPoi)
}

/**
 * Return the name of file without extension.
 *
 * @param file The file.
 * @return the name of file without extension
 */
fun getFileNameNoExtension(file: File?): String {
    file ?: return ""

    return getFileNameNoExtension(file.path)
}

/** ********** getFileExtension ********** */

/**
 * Return the extension of file.
 *
 * @param filePath The path of file.
 * @return the extension of file
 */
fun getFileExtension(filePath: String?): String {
    filePath ?: return ""
    if (isSpace(filePath)) return ""

    val lastPoi = filePath.lastIndexOf('.')
    val lastSep = filePath.lastIndexOf(File.separator)
    return if (lastPoi == -1 || lastSep >= lastPoi) "" else filePath.substring(lastPoi + 1)
}

/**
 * Return the extension of file.
 *
 * @param file The file.
 * @return the extension of file
 */
fun getFileExtension(file: File?): String {
    file ?: return ""

    return getFileExtension(file.path)
}

/** ******************** Checker ******************** */

/** ********** isFileExists ********** */

/**
 * Return whether the file exists.
 *
 * @param filePath The path of file.
 * @return `true`: yes<br></br>`false`: no
 */
fun isFileExists(filePath: String?): Boolean {
    return isFileExists(getFileByPath(filePath))
}

/**
 * Return whether the file exists.
 *
 * @param file The file.
 * @return `true`: yes<br></br>`false`: no
 */
fun isFileExists(file: File?): Boolean {
    return file != null && file.exists()
}

/** ********** isDir ********** */

/**
 * Return whether it is a directory.
 *
 * @param dirPath The path of directory.
 * @return `true`: yes<br></br>`false`: no
 */
fun isDir(dirPath: String?): Boolean {
    return isDir(getFileByPath(dirPath))
}

/**
 * Return whether it is a directory.
 *
 * @param dir The file.
 * @return `true`: yes<br></br>`false`: no
 */
fun isDir(dir: File?): Boolean {
    return dir != null && dir.exists() && dir.isDirectory
}

/** ********** isFile ********** */

/**
 * Return whether it is a file.
 *
 * @param filePath The path of file.
 * @return `true`: yes<br></br>`false`: no
 */
fun isFile(filePath: String?): Boolean {
    return isFile(getFileByPath(filePath))
}

/**
 * Return whether it is a file.
 *
 * @param file The file.
 * @return `true`: yes<br></br>`false`: no
 */
fun isFile(file: File?): Boolean {
    return file != null && file.exists() && file.isFile
}

/** ******************** Processor ******************** */

/** ********** createOrExists ********** */

/**
 * Create a directory if it doesn't exist, otherwise do nothing.
 *
 * @param dirPath The path of directory.
 * @return `true`: exists or creates successfully<br></br>`false`: otherwise
 */
fun createOrExistsDir(dirPath: String?): Boolean {
    return createOrExistsDir(getFileByPath(dirPath))
}

/**
 * Create a directory if it doesn't exist, otherwise do nothing.
 *
 * @param dir The file.
 * @return `true`: exists or creates successfully<br></br>`false`: otherwise
 */
fun createOrExistsDir(dir: File?): Boolean {
    return dir != null && if (dir.exists()) dir.isDirectory else dir.mkdirs()
}

/**
 * Create a file if it doesn't exist, otherwise do nothing.
 *
 * @param filePath The path of file.
 * @return `true`: exists or creates successfully<br></br>`false`: otherwise
 */
fun createOrExistsFile(filePath: String?): Boolean {
    return createOrExistsFile(getFileByPath(filePath))
}

/**
 * Create a file if it doesn't exist, otherwise do nothing.
 *
 * @param file The file.
 * @return `true`: exists or creates successfully<br></br>`false`: otherwise
 */
fun createOrExistsFile(file: File?): Boolean {
    file ?: return false
    if (file.exists()) return file.isFile
    if (!createOrExistsDir(file.parentFile)) return false

    return try {
        file.createNewFile()
    }
    catch (e: IOException) {
        logE(TAG, t = e)
        false
    }
}

/** ********** createByDeleteOld ********** */

/**
 * Create a file if it doesn't exist, otherwise delete old file before creating.
 *
 * @param filePath The path of file.
 * @return `true`: success<br></br>`false`: fail
 */
fun createFileByDeleteOldFile(filePath: String?): Boolean {
    return createFileByDeleteOldFile(getFileByPath(filePath))
}

/**
 * Create a file if it doesn't exist, otherwise delete old file before creating.
 *
 * @param file The file.
 * @return `true`: success<br></br>`false`: fail
 */
fun createFileByDeleteOldFile(file: File?): Boolean {
    file ?: return false
    // file exists and unsuccessfully delete then return false
    if (file.exists() && !file.delete()) return false
    if (!createOrExistsDir(file.parentFile)) return false

    return try {
        file.createNewFile()
    }
    catch (e: IOException) {
        logE(TAG, t = e)
        false
    }
}

/** ********** rename ********** */

/**
 * Rename the file.
 *
 * @param filePath The path of file.
 * @param newName  The new name of file.
 * @return `true`: success<br></br>`false`: fail
 */
fun rename(filePath: String?, newName: String?): Boolean {
    return rename(getFileByPath(filePath), newName)
}

/**
 * Rename the file.
 *
 * @param file    The file.
 * @param newName The new name of file.
 * @return `true`: success<br></br>`false`: fail
 */
fun rename(file: File?, newName: String?): Boolean {
    // file is null then return false
    file ?: return false
    // file doesn't exist then return false
    if (!file.exists()) return false
    // the new name is space then return false
    if (isSpace(newName)) return false
    // the new name equals old name then return true
    if (newName == file.name) return true
    // the new name of file exists then return false
    val newFile = File(file.parent + File.separator + newName)
    return !newFile.exists() && file.renameTo(newFile)
}

/** ********** copy ********** */

/**
 * Copy the directory.
 *
 * @param srcDirPath  The path of source directory.
 * @param destDirPath The path of destination directory.
 * @param listener    The replace listener.
 * @return `true`: success<br></br>`false`: fail
 */
fun copyDir(srcDirPath: String?, destDirPath: String?, listener: OnReplaceListener? = object : OnReplaceListener {
    override fun onReplace(): Boolean {
        return true
    }
}): Boolean {
    return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener)
}

/**
 * Copy the directory.
 *
 * @param srcDir   The source directory.
 * @param destDir  The destination directory.
 * @param listener The replace listener.
 * @return `true`: success<br></br>`false`: fail
 */
fun copyDir(srcDir: File?, destDir: File?, listener: OnReplaceListener? = object : OnReplaceListener {
    override fun onReplace(): Boolean {
        return true
    }
}): Boolean {
    return copyOrMoveDir(srcDir, destDir, false, listener)
}

/**
 * Copy the file.
 *
 * @param srcFilePath  The path of source file.
 * @param destFilePath The path of destination file.
 * @param listener     The replace listener.
 * @return `true`: success<br></br>`false`: fail
 */
fun copyFile(srcFilePath: String?, destFilePath: String?, listener: OnReplaceListener? = object : OnReplaceListener {
    override fun onReplace(): Boolean {
        return true
    }
}): Boolean {
    return copyFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), listener)
}

/**
 * Copy the file.
 *
 * @param srcFile  The source file.
 * @param destFile The destination file.
 * @param listener The replace listener.
 * @return `true`: success<br></br>`false`: fail
 */
fun copyFile(srcFile: File?, destFile: File?, listener: OnReplaceListener? = object : OnReplaceListener {
    override fun onReplace(): Boolean {
        return true
    }
}): Boolean {
    return copyOrMoveFile(srcFile, destFile, false, listener)
}

/** ********** move ********** */

/**
 * Move the directory.
 *
 * @param srcDirPath  The path of source directory.
 * @param destDirPath The path of destination directory.
 * @param listener    The replace listener.
 * @return `true`: success<br></br>`false`: fail
 */
fun moveDir(srcDirPath: String?, destDirPath: String?, listener: OnReplaceListener? = object : OnReplaceListener {
    override fun onReplace(): Boolean {
        return true
    }
}): Boolean {
    return moveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener)
}

/**
 * Move the directory.
 *
 * @param srcDir   The source directory.
 * @param destDir  The destination directory.
 * @param listener The replace listener.
 * @return `true`: success<br></br>`false`: fail
 */
fun moveDir(srcDir: File?, destDir: File?, listener: OnReplaceListener?): Boolean {
    return copyOrMoveDir(srcDir, destDir, true, listener)
}

/**
 * Move the file.
 *
 * @param srcFilePath  The path of source file.
 * @param destFilePath The path of destination file.
 * @param listener     The replace listener.
 * @return `true`: success<br></br>`false`: fail
 */
fun moveFile(srcFilePath: String?, destFilePath: String?, listener: OnReplaceListener? = object : OnReplaceListener {
    override fun onReplace(): Boolean {
        return true
    }
}): Boolean {
    return moveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), listener)
}

/**
 * Move the file.
 *
 * @param srcFile  The source file.
 * @param destFile The destination file.
 * @param listener The replace listener.
 * @return `true`: success<br></br>`false`: fail
 */
fun moveFile(srcFile: File?, destFile: File?, listener: OnReplaceListener?): Boolean {
    return copyOrMoveFile(srcFile, destFile, true, listener)
}

/** ********** copyOrMove ********** */

fun copyOrMoveDir(srcDirPath: String?, destDir: File?, isMove: Boolean, listener: OnReplaceListener?): Boolean {
    return copyOrMoveDir(getFileByPath(srcDirPath), destDir, isMove, listener)
}

fun copyOrMoveDir(srcDir: File?, destDir: File?, isMove: Boolean, listener: OnReplaceListener?): Boolean {
    srcDir ?: return false
    destDir ?: return false

    // destDir's path locate in srcDir's path then return false
    val srcPath = srcDir.path + File.separator
    val destPath = destDir.path + File.separator
    if (destPath.contains(srcPath)) return false
    if (!srcDir.exists() || !srcDir.isDirectory) return false
    if (destDir.exists()) {
        if (listener == null || listener.onReplace()) { // require delete the old directory
            if (!deleteAllInDir(destDir)) { // unsuccessfully delete then return false
                return false
            }
        }
        else {
            return true
        }
    }
    if (!createOrExistsDir(destDir)) return false
    val files = srcDir.listFiles() ?: return false
    for (file in files) {
        val oneDestFile = File(destPath + file.name)
        if (file.isFile) {
            if (!copyOrMoveFile(file, oneDestFile, isMove, listener)) return false
        }
        else if (file.isDirectory) {
            if (!copyOrMoveDir(file, oneDestFile, isMove, listener)) return false
        }
    }
    return !isMove || deleteDir(srcDir)
}

fun copyOrMoveFile(srcFilePath: String?, destFile: File?, isMove: Boolean, listener: OnReplaceListener?): Boolean {
    return copyOrMoveFile(getFileByPath(srcFilePath), destFile, isMove, listener)
}

fun copyOrMoveFile(srcFile: File?, destFile: File?, isMove: Boolean, listener: OnReplaceListener?): Boolean {
    srcFile ?: return false
    destFile ?: return false

    // srcFile equals destFile then return false
    if (srcFile == destFile) return false
    // srcFile doesn't exist or isn't a file then return false
    if (!srcFile.exists() || !srcFile.isFile) return false
    if (destFile.exists()) {
        if (listener == null || listener.onReplace()) { // require delete the old file
            if (!destFile.delete()) { // unsuccessfully delete then return false
                return false
            }
        }
        else {
            return true
        }
    }
    return if (!createOrExistsDir(destFile.parentFile)) false
    else try {
        writeFileFromInput(destFile, FileInputStream(srcFile)) && !(isMove && !deleteFile(srcFile))
    }
    catch (e: FileNotFoundException) {
        logE(TAG, t = e)
        false
    }
}

/** ********** delete ********** */

/**
 * Delete the directory.
 *
 * @param path The path of file.
 * @return `true`: success<br></br>`false`: fail
 */
fun delete(path: String?): Boolean {
    return delete(getFileByPath(path))
}

/**
 * Delete the directory.
 *
 * @param file The file.
 * @return `true`: success<br></br>`false`: fail
 */
fun delete(file: File?): Boolean {
    file ?: return false

    return if (file.isDirectory) deleteDir(file) else deleteFile(file)
}

/**
 * Delete the directory.
 *
 * @param dirPath The path of directory.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteDir(dirPath: String?): Boolean {
    return deleteDir(getFileByPath(dirPath))
}

/**
 * Delete the directory.
 *
 * @param dir The directory.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteDir(dir: File?): Boolean {
    dir ?: return false
    // dir doesn't exist then return true
    if (!dir.exists()) return true
    // dir isn't a directory then return false
    if (!dir.isDirectory) return false
    val files = dir.listFiles()
    if (files != null && files.isNotEmpty()) {
        for (file in files) {
            if (file.isFile) {
                if (!file.delete()) return false
            }
            else if (file.isDirectory) {
                if (!deleteDir(file)) return false
            }
        }
    }
    return dir.delete()
}

/**
 * Delete the file.
 *
 * @param srcFilePath The path of source file.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteFile(srcFilePath: String?): Boolean {
    return deleteFile(getFileByPath(srcFilePath))
}

/**
 * Delete the file.
 *
 * @param file The file.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteFile(file: File?): Boolean {
    return file != null && (!file.exists() || file.isFile && file.delete())
}

/**
 * Delete the all in directory.
 *
 * @param dirPath The path of directory.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteAllInDir(dirPath: String?): Boolean {
    return deleteAllInDir(getFileByPath(dirPath))
}

/**
 * Delete the all in directory.
 *
 * @param dir The directory.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteAllInDir(dir: File?): Boolean {
    return deleteFilesInDirWithFilter(dir, FileFilter { true })
}

/**
 * Delete all files in directory.
 *
 * @param dirPath The path of directory.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteFilesInDir(dirPath: String?): Boolean {
    return deleteFilesInDir(getFileByPath(dirPath))
}

/**
 * Delete all files in directory.
 *
 * @param dir The directory.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteFilesInDir(dir: File?): Boolean {
    return deleteFilesInDirWithFilter(dir, FileFilter { pathname -> pathname.isFile })
}

/**
 * Delete all files that satisfy the filter in directory.
 *
 * @param dirPath The path of directory.
 * @param filter  The filter.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteFilesInDirWithFilter(dirPath: String?, filter: FileFilter?): Boolean {
    return deleteFilesInDirWithFilter(getFileByPath(dirPath), filter)
}

/**
 * Delete all files that satisfy the filter in directory.
 *
 * @param dir    The directory.
 * @param filter The filter.
 * @return `true`: success<br></br>`false`: fail
 */
fun deleteFilesInDirWithFilter(dir: File?, filter: FileFilter?): Boolean {
    dir ?: return false
    // dir doesn't exist then return true
    if (!dir.exists()) return true
    // dir isn't a directory then return false
    if (!dir.isDirectory) return false
    val files = dir.listFiles()
    if (files != null && files.isNotEmpty()) {
        for (file in files) {
            if (filter?.accept(file) ?: return false) {
                if (file.isFile) {
                    if (!file.delete()) return false
                }
                else if (file.isDirectory) {
                    if (!deleteDir(file)) return false
                }
            }
        }
    }
    return true
}

/** ********** list ********** */

/**
 * Return the files in directory.
 *
 * @param dirPath     The path of directory.
 * @param isRecursive True to traverse subdirectories, false otherwise.
 * @return the files in directory
 */
fun listFilesInDir(dirPath: String?, isRecursive: Boolean = false): List<File?>? {
    return listFilesInDir(getFileByPath(dirPath), isRecursive)
}

/**
 * Return the files in directory.
 *
 * @param dir         The directory.
 * @param isRecursive True to traverse subdirectories, false otherwise.
 * @return the files in directory
 */
fun listFilesInDir(dir: File?, isRecursive: Boolean = false): List<File?>? {
    return listFilesInDirWithFilter(dir, FileFilter { true }, isRecursive)
}

/**
 * Return the files that satisfy the filter in directory.
 *
 * @param dirPath     The path of directory.
 * @param filter      The filter.
 * @param isRecursive True to traverse subdirectories, false otherwise.
 * @return the files that satisfy the filter in directory
 */
fun listFilesInDirWithFilter(dirPath: String?, filter: FileFilter?, isRecursive: Boolean = false): List<File?>? {
    return listFilesInDirWithFilter(getFileByPath(dirPath), filter, isRecursive)
}

/**
 * Return the files that satisfy the filter in directory.
 *
 * @param dir         The directory.
 * @param filter      The filter.
 * @param isRecursive True to traverse subdirectories, false otherwise.
 * @return the files that satisfy the filter in directory
 */
fun listFilesInDirWithFilter(dir: File?, filter: FileFilter?, isRecursive: Boolean = false): List<File?>? {
    dir ?: return null
    if (!isDir(dir)) return null

    val list: MutableList<File?> = ArrayList()
    val files = dir?.listFiles()
    if (files != null && files.isNotEmpty()) {
        for (file in files) {
            if (filter?.accept(file) ?: return null) {
                list.add(file)
            }
            if (isRecursive && file.isDirectory) {
                list.addAll(listFilesInDirWithFilter(file, filter, true) ?: return null)
            }
        }
    }
    return list
}


/** ****************************** Interface ****************************** */

interface OnReplaceListener {
    fun onReplace(): Boolean
}
