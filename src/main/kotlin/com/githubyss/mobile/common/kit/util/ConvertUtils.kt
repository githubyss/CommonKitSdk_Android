package com.githubyss.mobile.common.kit.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import com.githubyss.mobile.common.kit.enumeration.MemoryUnit
import com.githubyss.mobile.common.kit.enumeration.TimeUnit
import java.io.*
import kotlin.experimental.and
import kotlin.math.min


/**
 * ConvertUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 09:16:11
 */
object ConvertUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = ConvertUtils::class.simpleName ?: "simpleName is null"
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** 2String ********** */
    
    fun null2Length0(s: String?): String {
        return StringUtils.null2Length0(s)
    }
    
    fun <T : Any> object2String(`object`: T?): String {
        return StringUtils.object2String(`object`)
    }
    
    fun array2String(array: Array<*>?): String {
        return StringUtils.array2String(array)
    }
    
    fun list2String(list: List<*>?): String {
        return StringUtils.list2String(list)
    }
    
    /** ********** Bytes, Bits, Chars, HexString ********** */
    
    /**
     * Bytes to bits.
     *
     * @param bytes The bytes.
     * @return The bits.
     */
    fun bytes2Bits(bytes: ByteArray?): String {
        if (bytes == null || bytes.isEmpty()) return ""
        val stringBuilder = StringBuilder()
        for (byte in bytes) {
            for (j in 7 downTo 0) {
                stringBuilder.append(if (((byte.toInt() shr j) and 0x01) == 0) '0' else '1')
            }
        }
        return stringBuilder.toString()
    }
    
    /**
     * Bits to bytes.
     *
     * @param bits The bits.
     * @return The bytes.
     */
    fun bits2Bytes(bits: String?): ByteArray? {
        if (StringUtils.isSpace(bits)) return null
        var bits = bits ?: return null
        
        val bitsLenMod = bits.length % 8
        var bytesSize = bits.length / 8
        
        // add "0" until length to 8 times
        if (bitsLenMod != 0) {
            for (i in bitsLenMod..7) {
                bits = "0$bits"
            }
            bytesSize++
        }
        val ret = ByteArray(bytesSize)
        for (i in 0 until bytesSize) {
            for (j in 0..7) {
                ret[i] = (ret[i].toInt() shl 1).toByte()
                ret[i] = (ret[i].toInt() or bits[i * 8 + j] - '0').toByte()
            }
        }
        return ret
    }
    
    /**
     * Bytes to chars.
     *
     * @param bytes The bytes.
     * @return The chars.
     */
    fun bytes2Chars(bytes: ByteArray?): CharArray? {
        if (bytes == null || bytes.isEmpty()) return null
        val bytesSize = bytes.size
        val ret = CharArray(bytesSize)
        for (i in 0 until bytesSize) {
            ret[i] = (bytes[i] and 0xff.toByte()).toChar()
        }
        return ret
    }
    
    /**
     * Chars to bytes.
     *
     * @param chars The chars.
     * @return The bytes.
     */
    fun chars2Bytes(chars: CharArray?): ByteArray? {
        if (chars == null || chars.isEmpty()) return null
        val charsSize = chars.size
        val ret = ByteArray(charsSize)
        for (i in 0 until charsSize) {
            ret[i] = chars[i].toByte()
        }
        return ret
    }
    
    fun bytes2HexString(bytes: ByteArray?): String {
        return StringUtils.bytes2HexString(bytes)
    }
    
    /**
     * Hex string to bytes.
     * e.g. hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }.
     *
     * @param hexString The hex string.
     * @return The bytes.
     */
    fun hexString2Bytes(hexString: String?): ByteArray? {
        if (StringUtils.isSpace(hexString)) return null
        var hexString = hexString ?: return null
        var len = hexString.length
        if (len % 2 != 0) {
            hexString = "0$hexString"
            len += 1
        }
        val hexBytes = hexString.toUpperCase().toCharArray()
        val ret = ByteArray(len shr 1)
        for (i in 0 until len step 2) {
            ret[i shr 1] = (hex2Dec(hexBytes[i]) shl 4 or hex2Dec(hexBytes[i + 1])).toByte()
        }
        return ret
    }
    
    /** ********** Bin, Oct, Dec, Hex ********** */
    
    fun dec2Hex(dec: Int): Char {
        return NumberUtils.dec2Hex(dec)
    }
    
    fun hex2Dec(hex: Char): Int {
        return NumberUtils.hex2Dec(hex)
    }
    
    /** ********** Memory size, Bytes,  ********** */
    
    /**
     * Size of memory in unit to size of byte.
     *
     * @param memorySize Size of memory.
     * @param unit       The unit of memory size.
     *                   <ul>
     *                   <li>{@link MemoryUnit#BYTE}</li>
     *                   <li>{@link MemoryUnit#KB}</li>
     *                   <li>{@link MemoryUnit#MB}</li>
     *                   <li>{@link MemoryUnit#GB}</li>
     *                   </ul>
     * @return Size of byte.
     */
    fun memorySize2Byte(memorySize: Long, @MemoryUnit unit: Int): Long {
        return if (memorySize < 0) -1 else memorySize * unit
    }
    
    /**
     * Size of byte to size of memory in unit.
     *
     * @param byteSize Size of byte.
     * @param unit     The unit of memory size.
     *                 <ul>
     *                 <li>{@link MemoryUnit#BYTE}</li>
     *                 <li>{@link MemoryUnit#KB}</li>
     *                 <li>{@link MemoryUnit#MB}</li>
     *                 <li>{@link MemoryUnit#GB}</li>
     *                 </ul>
     * @return Size of memory in unit.
     */
    fun byte2MemorySize(byteSize: Long, @MemoryUnit unit: Int): Double {
        return if (byteSize < 0) (-1).toDouble() else byteSize.toDouble() / unit
    }
    
    /**
     * Size of byte to fit size of memory.
     * To three decimal places.
     *
     * @param byteSize Size of byte.
     * @return Fit size of memory.
     */
    @SuppressLint("DefaultLocale")
    fun byte2FitMemorySize(byteSize: Long): String {
        return when {
            byteSize < 0 -> {
                "shouldn't be less than zero!"
            }
            byteSize < MemoryUnit.KB -> {
                String.format("%.3fB", byteSize.toDouble())
            }
            byteSize < MemoryUnit.MB -> {
                String.format("%.3fKB", byteSize.toDouble() / MemoryUnit.KB)
            }
            byteSize < MemoryUnit.GB -> {
                String.format("%.3fMB", byteSize.toDouble() / MemoryUnit.MB)
            }
            else -> {
                String.format("%.3fGB", byteSize.toDouble() / MemoryUnit.GB)
            }
        }
    }
    
    /** ********** Time ********** */
    
    /**
     * Time span in unit to milliseconds.
     *
     * @param timeSpan The time span.
     * @param unit     The unit of time span.
     *                 <ul>
     *                 <li>{@link TimeUnit#MSEC}</li>
     *                 <li>{@link TimeUnit#SEC }</li>
     *                 <li>{@link TimeUnit#MIN }</li>
     *                 <li>{@link TimeUnit#HOUR}</li>
     *                 <li>{@link TimeUnit#DAY }</li>
     *                 </ul>
     * @return The milliseconds.
     */
    fun timeSpan2Millis(timeSpan: Long, @TimeUnit unit: Int): Long {
        return timeSpan * unit
    }
    
    /**
     * Milliseconds to time span in unit.
     *
     * @param millis The milliseconds.
     * @param unit   The unit of time span.
     *               <ul>
     *               <li>{@link TimeUnit#MSEC}</li>
     *               <li>{@link TimeUnit#SEC }</li>
     *               <li>{@link TimeUnit#MIN }</li>
     *               <li>{@link TimeUnit#HOUR}</li>
     *               <li>{@link TimeUnit#DAY }</li>
     *               </ul>
     * @return The time span in unit.
     */
    fun millis2TimeSpan(millis: Long, @TimeUnit unit: Int): Long {
        return millis / unit
    }
    
    /**
     * Milliseconds to fit time span.
     *
     * @param millis    The milliseconds.
     *                  <p>millis <= 0, return ""</p>
     * @param precision The precision of time span.
     *                  <ul>
     *                  <li>precision <= 0, return ""</li>
     *                  <li>precision = 1, return 天</li>
     *                  <li>precision = 2, return 天, 小时</li>
     *                  <li>precision = 3, return 天, 小时, 分钟</li>
     *                  <li>precision = 4, return 天, 小时, 分钟, 秒</li>
     *                  <li>precision >= 5，return 天, 小时, 分钟, 秒, 毫秒</li>
     *                  </ul>
     * @return The fit time span.
     */
    @SuppressLint("DefaultLocale")
    fun millis2FitTimeSpan(millis: Long, precision: Int): String {
        var millis = millis
        var precision = precision
        if (millis <= 0 || precision <= 0) return ""
        val stringBuilder = StringBuilder()
        val units = arrayOf("天", "小时", "分钟", "秒", "毫秒")
        val unitLens = intArrayOf(TimeUnit.DAY, TimeUnit.HOUR, TimeUnit.MIN, TimeUnit.SEC, TimeUnit.MSEC)
        precision = min(precision, units.size)
        for (i in 0 until precision) {
            if (millis >= unitLens[i]) {
                val mode = millis / unitLens[i]
                millis -= mode * unitLens[i]
                stringBuilder.append(mode).append(units[i])
            }
        }
        return stringBuilder.toString()
    }
    
    /** ********** Stream, Bytes, String ********** */
    
    /**
     * Input stream to output stream.
     *
     * @param `is` The input stream.
     * @return The output stream.
     */
    fun inputStream2ByteArrayOutputStream(`is`: InputStream?): ByteArrayOutputStream? {
        return if (`is` == null) null else try {
            val baos = ByteArrayOutputStream()
            val bytes = ByteArray(MemoryUnit.KB)
            var len: Int
            while (`is`.read(bytes, 0, MemoryUnit.KB).also { len = it } != -1) {
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
    fun outputStream2ByteArrayInputStream(os: OutputStream?): ByteArrayInputStream? {
        return if (os == null) null else ByteArrayInputStream((os as ByteArrayOutputStream).toByteArray())
    }
    
    /**
     * Input stream to bytes.
     *
     * @param `is` The input stream.
     * @return The bytes.
     */
    fun inputStream2Bytes(`is`: InputStream?): ByteArray? {
        return if (`is` == null) null else inputStream2ByteArrayOutputStream(`is`)?.toByteArray()
    }
    
    /**
     * Bytes to input stream.
     *
     * @param bytes The bytes.
     * @return The input stream.
     */
    fun bytes2InputStream(bytes: ByteArray?): InputStream? {
        return if (bytes == null || bytes.isEmpty()) null else ByteArrayInputStream(bytes)
    }
    
    /**
     * Output stream to bytes.
     *
     * @param output The output stream.
     * @return The bytes.
     */
    fun outputStream2Bytes(output: OutputStream?): ByteArray? {
        return if (output == null) null else (output as ByteArrayOutputStream).toByteArray()
    }
    
    /**
     * Bytes to output stream.
     *
     * @param bytes The bytes.
     * @return The output stream.
     */
    fun bytes2OutputStream(bytes: ByteArray?): OutputStream? {
        if (bytes == null || bytes.isEmpty()) return null
        var baos: ByteArrayOutputStream? = null
        return try {
            baos = ByteArrayOutputStream()
            baos.write(bytes)
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
     * Input stream to string.
     *
     * @param `is`       The input stream.
     * @param charsetName The name of charset.
     * @return The string.
     */
    fun inputStream2String(`is`: InputStream?, charsetName: String): String {
        return if (`is` == null || StringUtils.isSpace(charsetName)) "" else try {
            val baos = inputStream2ByteArrayOutputStream(`is`) ?: return ""
            baos.toString(charsetName)
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
    fun string2InputStream(string: String, charsetName: String): InputStream? {
        return if (StringUtils.isSpace(charsetName)) null else try {
            ByteArrayInputStream(string.toByteArray(charset(charsetName)))
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
    fun outputStream2String(output: OutputStream?, charsetName: String): String {
        return if (output == null || StringUtils.isSpace(charsetName)) "" else try {
            String(outputStream2Bytes(output) ?: ByteArray(0), charset(charsetName))
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
    fun string2OutputStream(string: String, charsetName: String): OutputStream? {
        return if (StringUtils.isSpace(charsetName)) null else try {
            bytes2OutputStream(string.toByteArray(charset(charsetName)))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            null
        }
    }
    
    /** ********** File, Uri ********** */
    
    fun file2Uri(file: File?): Uri? {
        return UriUtils.file2Uri(file)
    }
    
    fun uri2File(uri: Uri?): File? {
        return UriUtils.uri2File(uri)
    }
    
    /** ********** Bitmap, Bytes, Drawable, View ********** */
    
    fun bitmap2Bytes(bitmap: Bitmap?, format: CompressFormat?): ByteArray? {
        return ImageUtils.bitmap2Bytes(bitmap, format)
    }
    
    fun bytes2Bitmap(bytes: ByteArray?): Bitmap? {
        return ImageUtils.bytes2Bitmap(bytes)
    }
    
    fun bitmap2Drawable(context: Context = ComkitApplicationConfig.getApp(), bitmap: Bitmap?): Drawable? {
        return ImageUtils.bitmap2Drawable(context, bitmap)
    }
    
    fun drawable2Bitmap(drawable: Drawable): Bitmap? {
        return ImageUtils.drawable2Bitmap(drawable)
    }
    
    fun drawable2Bytes(drawable: Drawable?, format: CompressFormat?): ByteArray? {
        return ImageUtils.drawable2Bytes(drawable, format)
    }
    
    fun bytes2Drawable(context: Context = ComkitApplicationConfig.getApp(), bytes: ByteArray?): Drawable? {
        return ImageUtils.bytes2Drawable(context, bytes)
    }
    
    fun view2Bitmap(view: View?): Bitmap? {
        return ImageUtils.view2Bitmap(view)
    }
    
    /** ********** Dp, Px, Sp ********** */
    
    fun dp2Px(dpValue: Float): Int? {
        return ScreenUtils.dp2Px(dpValue)
    }
    
    fun px2Dp(pxValue: Float): Int? {
        return ScreenUtils.px2Dp(pxValue)
    }
    
    fun sp2Px(spValue: Float): Int? {
        return ScreenUtils.sp2Px(spValue)
    }
    
    fun px2Sp(pxValue: Float): Int? {
        return ScreenUtils.px2Sp(pxValue)
    }
}
