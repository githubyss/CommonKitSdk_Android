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
 * 转换
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 09:16:11
 */
object ConvertUtils {
    
    /** ****************************** Properties ****************************** */
    
    private val TAG: String = ConvertUtils::class.java.simpleName
    
    
    /** ****************************** Functions ****************************** */
    
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
        bytes ?: return ""
        if (bytes.isEmpty()) return ""
        
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
        bits ?: return null
        if (StringUtils.isSpace(bits)) return null
        
        var bits: String = bits
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
        bytes ?: return null
        if (bytes.isEmpty()) return null
        
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
        chars ?: return null
        if (chars.isEmpty()) return null
        
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
        hexString ?: return null
        if (StringUtils.isSpace(hexString)) return null
        
        var hexString: String = hexString
        var len = hexString.length
        if (len % 2 != 0) {
            hexString = "0$hexString"
            len += 1
        }
        val hexBytes = hexString.toUpperCase()
            .toCharArray()
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
     *                 <li>{@link TimeUnit#MILLISECOND}</li>
     *                 <li>{@link TimeUnit#SECOND }</li>
     *                 <li>{@link TimeUnit#MINUTE }</li>
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
     *               <li>{@link TimeUnit#MILLISECOND}</li>
     *               <li>{@link TimeUnit#SECOND }</li>
     *               <li>{@link TimeUnit#MINUTE }</li>
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
        var millis: Long = millis
        var precision: Int = precision
        if (millis <= 0 || precision <= 0) return ""
        val stringBuilder = StringBuilder()
        val units = arrayOf("天", "小时", "分钟", "秒", "毫秒")
        val unitLens = intArrayOf(TimeUnit.DAY, TimeUnit.HOUR, TimeUnit.MINUTE, TimeUnit.SECOND, TimeUnit.MILLISECOND)
        precision = min(precision, units.size)
        for (i in 0 until precision) {
            if (millis >= unitLens[i]) {
                val mode = millis / unitLens[i]
                millis -= mode * unitLens[i]
                stringBuilder.append(mode)
                    .append(units[i])
            }
        }
        return stringBuilder.toString()
    }
    
    /** ********** Stream, Bytes, List, String ********** */
    
    fun input2BytesOutput(`is`: InputStream?): ByteArrayOutputStream? {
        return StreamUtils.input2BytesOutput(`is`)
    }
    
    fun output2BytesInput(os: OutputStream?): ByteArrayInputStream? {
        return StreamUtils.output2BytesInput(os)
    }
    
    fun input2Bytes(`is`: InputStream?): ByteArray? {
        return StreamUtils.input2Bytes(`is`)
    }
    
    fun bytes2Input(bytes: ByteArray?): InputStream? {
        return StreamUtils.bytes2Input(bytes)
    }
    
    fun output2Bytes(os: OutputStream?): ByteArray? {
        return StreamUtils.output2Bytes(os)
    }
    
    fun bytes2Output(bytes: ByteArray?): OutputStream? {
        return StreamUtils.bytes2Output(bytes)
    }
    
    fun input2List(`is`: InputStream, charsetName: String?): List<String>? {
        return StreamUtils.input2List(`is`, charsetName)
    }
    
    fun input2String(`is`: InputStream?, charsetName: String?): String {
        return StreamUtils.input2String(`is`, charsetName)
    }
    
    fun string2Input(string: String?, charsetName: String?): InputStream? {
        return StreamUtils.string2Input(string, charsetName)
    }
    
    fun output2String(output: OutputStream?, charsetName: String?): String {
        return StreamUtils.output2String(output, charsetName)
    }
    
    fun string2Output(string: String?, charsetName: String?): OutputStream? {
        return StreamUtils.string2Output(string, charsetName)
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
    
    fun bitmap2Drawable(context: Context? = ComkitApplicationConfig.getApp(), bitmap: Bitmap?): Drawable? {
        return ImageUtils.bitmap2Drawable(bitmap, context)
    }
    
    fun drawable2Bitmap(drawable: Drawable): Bitmap? {
        return ImageUtils.drawable2Bitmap(drawable)
    }
    
    fun drawable2Bytes(drawable: Drawable?, format: CompressFormat?): ByteArray? {
        return ImageUtils.drawable2Bytes(drawable, format)
    }
    
    fun bytes2Drawable(context: Context? = ComkitApplicationConfig.getApp(), bytes: ByteArray?): Drawable? {
        return ImageUtils.bytes2Drawable(bytes, context)
    }
    
    fun view2Bitmap(view: View?): Bitmap? {
        return ImageUtils.view2Bitmap(view)
    }
    
    /** ********** Dp, Px, Sp ********** */
    
    fun dp2Px(dpValue: Float): Int {
        return ScreenUtils.dp2Px(dpValue)
    }
    
    fun px2Dp(pxValue: Float): Int {
        return ScreenUtils.px2Dp(pxValue)
    }
    
    fun sp2Px(spValue: Float): Int {
        return ScreenUtils.sp2Px(spValue)
    }
    
    fun px2Sp(pxValue: Float): Int {
        return ScreenUtils.px2Sp(pxValue)
    }
}
