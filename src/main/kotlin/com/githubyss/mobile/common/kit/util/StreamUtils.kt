package com.githubyss.mobile.common.kit.util

import com.githubyss.mobile.common.kit.enumeration.MemoryUnit
import java.io.*
import java.util.ArrayList


/**
 * StreamUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/08 14:40:23
 */
object StreamUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = StreamUtils::class.simpleName ?: "simpleName is null"
    private const val BUFFER_SIZE = 8 * MemoryUnit.KB
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Converter ********** ********** */
    
    /**
     * Input stream to output stream.
     *
     * @param `is` The input stream.
     * @return The output stream.
     */
    fun input2BytesOutput(`is`: InputStream?): ByteArrayOutputStream? {
        return if (`is` == null) null else try {
            val baos = ByteArrayOutputStream()
            val bytes = ByteArray(BUFFER_SIZE)
            var len: Int
            while (`is`.read(bytes, 0, BUFFER_SIZE).also { len = it } != -1) {
                baos.write(bytes, 0, len)
            }
            baos
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
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
        return if (os == null) null else ByteArrayInputStream(output2Bytes(os))
    }
    
    /**
     * Input stream to bytes.
     *
     * @param `is` The input stream.
     * @return The bytes.
     */
    fun input2Bytes(`is`: InputStream?): ByteArray? {
        return if (`is` == null) null else input2BytesOutput(`is`)?.toByteArray()
    }
    
    /**
     * Bytes to input stream.
     *
     * @param bytes The bytes.
     * @return The input stream.
     */
    fun bytes2Input(bytes: ByteArray?): InputStream? {
        return if (ArrayUtils.isEmpty(bytes)) null else ByteArrayInputStream(bytes)
    }
    
    /**
     * Output stream to bytes.
     *
     * @param os The output stream.
     * @return The bytes.
     */
    fun output2Bytes(os: OutputStream?): ByteArray? {
        return if (os == null) null else (os as ByteArrayOutputStream).toByteArray()
    }
    
    /**
     * Bytes to output stream.
     *
     * @param bytes The bytes.
     * @return The output stream.
     */
    fun bytes2Output(bytes: ByteArray?): OutputStream? {
        if (ArrayUtils.isEmpty(bytes)) return null
        var baos: ByteArrayOutputStream? = null
        return try {
            baos = ByteArrayOutputStream()
            baos.write(bytes ?: return null)
            baos
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } finally {
            try {
                baos?.close()
            } catch (e: IOException) {
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
    fun input2List(`is`: InputStream, charsetName: String?): List<String>? {
        var reader: BufferedReader? = null
        return try {
            val list: MutableList<String> = ArrayList()
            reader = if (StringUtils.isSpace(charsetName)) {
                BufferedReader(InputStreamReader(`is`))
            } else {
                BufferedReader(InputStreamReader(`is`, charsetName ?: return null))
            }
            var line: String
            while (reader.readLine().also { line = it } != null) {
                list.add(line)
            }
            list
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } finally {
            try {
                reader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    
    /**
     * Input stream to string.
     *
     * @param `is`       The input stream.
     * @param charsetName The name of charset.
     * @return The string.
     */
    fun input2String(`is`: InputStream?, charsetName: String?): String {
        return if (`is` == null || StringUtils.isSpace(charsetName)) "" else try {
            val baos = input2BytesOutput(`is`) ?: return ""
            baos.toString(charsetName ?: return "")
        } catch (e: UnsupportedEncodingException) {
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
        return if (StringUtils.isSpace(charsetName)) null else try {
            ByteArrayInputStream(string?.toByteArray(charset(charsetName ?: return null)))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            null
        }
    }
    
    /**
     * Output stream to string.
     *
     * @param output      The output stream.
     * @param charsetName The name of charset.
     * @return The string
     */
    fun output2String(output: OutputStream?, charsetName: String?): String {
        return if (output == null || StringUtils.isSpace(charsetName)) "" else try {
            String(output2Bytes(output) ?: ByteArray(0), charset(charsetName ?: return ""))
        } catch (e: UnsupportedEncodingException) {
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
        return if (StringUtils.isSpace(charsetName)) null else try {
            bytes2Output(string?.toByteArray(charset(charsetName ?: return null)))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            null
        }
    }
    
    /** ********** writeFileFromInput ********** */
    
    fun writeFileFromInput(filePath: String?, `is`: InputStream, append: Boolean = false): Boolean {
        return writeFileFromInput(FileUtils.getFileByPath(filePath), `is`, append)
    }
    
    fun writeFileFromInput(file: File?, `is`: InputStream, append: Boolean = false): Boolean {
        var os: OutputStream? = null
        return try {
            os = BufferedOutputStream(FileOutputStream(file ?: return false, append))
            val data = ByteArray(BUFFER_SIZE)
            var len: Int
            while (`is`.read(data, 0, BUFFER_SIZE).also { len = it } != -1) {
                os.write(data, 0, len)
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } finally {
            try {
                `is`.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                os?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
