package com.githubyss.mobile.common.kit.util

import com.githubyss.mobile.common.kit.enumeration.MemoryUnit
import java.io.*
import java.util.*


/**
 * StreamUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/08 14:40:23
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "StreamUtils"
private const val BUFFER_SIZE = 8 * MemoryUnit.KB


/** ****************************** Functions ****************************** */

/** ******************** Converter ******************** */

/**
 * Input stream to output stream.
 *
 * @param `is` The input stream.
 * @return The output stream.
 */
fun input2BytesOutput(`is`: InputStream?): ByteArrayOutputStream? {
    `is` ?: return null

    return try {
        val baos = ByteArrayOutputStream()
        val bytes = ByteArray(BUFFER_SIZE)
        var len: Int
        while (`is`.read(bytes, 0, BUFFER_SIZE)
                .also { len = it } != -1) {
            baos.write(bytes, 0, len)
        }
        baos
    }
    catch (e: IOException) {
        e.printStackTrace()
        null
    }
    finally {
        try {
            `is`.close()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

/**
 * Output stream to input stream.
 *
 * @param os The output stream.
 * @return The input stream.
 */
fun output2BytesInput(os: OutputStream?): ByteArrayInputStream? {
    os ?: return null

    return ByteArrayInputStream(output2Bytes(os))
}

/**
 * Input stream to bytes.
 *
 * @param `is` The input stream.
 * @return The bytes.
 */
fun input2Bytes(`is`: InputStream?): ByteArray? {
    `is` ?: return null

    return input2BytesOutput(`is`)?.toByteArray()
}

/**
 * Bytes to input stream.
 *
 * @param bytes The bytes.
 * @return The input stream.
 */
fun bytes2Input(bytes: ByteArray?): InputStream? {
    bytes ?: return null
    if (isEmpty(bytes)) return null

    return ByteArrayInputStream(bytes)
}

/**
 * Output stream to bytes.
 *
 * @param os The output stream.
 * @return The bytes.
 */
fun output2Bytes(os: OutputStream?): ByteArray? {
    os ?: return null

    return (os as ByteArrayOutputStream).toByteArray()
}

/**
 * Bytes to output stream.
 *
 * @param bytes The bytes.
 * @return The output stream.
 */
fun bytes2Output(bytes: ByteArray?): OutputStream? {
    bytes ?: return null
    if (isEmpty(bytes)) return null

    var baos: ByteArrayOutputStream? = null
    return try {
        baos = ByteArrayOutputStream()
        baos.write(bytes ?: return null)
        baos
    }
    catch (e: IOException) {
        e.printStackTrace()
        null
    }
    finally {
        try {
            baos?.close()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

/**
 * Input stream to list.
 *
 * @param `is` The input stream.
 * @return The list.
 */
fun input2List(`is`: InputStream?, charsetName: String?): List<String>? {
    `is` ?: return null

    var reader: BufferedReader? = null
    return try {
        val list: MutableList<String> = ArrayList()
        reader = if (isSpace(charsetName)) {
            BufferedReader(InputStreamReader(`is`))
        }
        else {
            BufferedReader(InputStreamReader(`is`, charsetName ?: return null))
        }
        var line: String
        while (reader.readLine()
                .also { line = it } != null) {
            list.add(line)
        }
        list
    }
    catch (e: IOException) {
        e.printStackTrace()
        null
    }
    finally {
        try {
            reader?.close()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

/**
 * Input stream to string.
 *
 * @param `is`        The input stream.
 * @param charsetName The name of charset.
 * @return The string.
 */
fun input2String(`is`: InputStream?, charsetName: String?): String {
    `is` ?: return ""

    return try {
        val baos = input2BytesOutput(`is`) ?: return ""
        baos.toString(charsetName ?: return "")
    }
    catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        ""
    }
}

/**
 * String to input stream.
 *
 * @param string      The string.
 * @param charsetName The name of charset.
 * @return The input stream.
 */
fun string2Input(string: String?, charsetName: String?): InputStream? {
    string ?: return null
    charsetName ?: return null
    if (isSpace(string)) return null
    if (isSpace(charsetName)) return null

    return try {
        ByteArrayInputStream(string.toByteArray(charset(charsetName)))
    }
    catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        null
    }
}

/**
 * Output stream to string.
 *
 * @param os      The output stream.
 * @param charsetName The name of charset.
 * @return The string
 */
fun output2String(os: OutputStream?, charsetName: String?): String {
    os ?: return ""
    charsetName ?: return ""
    if (isSpace(charsetName)) return ""

    return try {
        String(output2Bytes(os) ?: ByteArray(0), charset(charsetName))
    }
    catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        ""
    }
}

/**
 * String to output stream.
 *
 * @param string      The string.
 * @param charsetName The name of charset.
 * @return The output stream.
 */
fun string2Output(string: String?, charsetName: String?): OutputStream? {
    string ?: return null
    charsetName ?: return null
    if (isSpace(string)) return null
    if (isSpace(charsetName)) return null

    return try {
        bytes2Output(string.toByteArray(charset(charsetName)))
    }
    catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
        null
    }
}

/** ********** writeFileFromInput ********** */

fun writeFileFromInput(filePath: String?, `is`: InputStream?, append: Boolean = false): Boolean {
    return writeFileFromInput(getFileByPath(filePath), `is`, append)
}

fun writeFileFromInput(file: File?, `is`: InputStream?, append: Boolean = false): Boolean {
    file ?: return false
    `is` ?: return false

    var os: OutputStream? = null
    return try {
        os = BufferedOutputStream(FileOutputStream(file, append))
        val data = ByteArray(BUFFER_SIZE)
        var len: Int
        while (`is`.read(data, 0, BUFFER_SIZE)
                .also { len = it } != -1) {
            os.write(data, 0, len)
        }
        true
    }
    catch (e: IOException) {
        e.printStackTrace()
        false
    }
    finally {
        try {
            `is`.close()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        try {
            os?.close()
        }
        catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
