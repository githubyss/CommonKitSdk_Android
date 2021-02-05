package com.githubyss.mobile.common.kit.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.Drawable
import android.view.View
import com.githubyss.mobile.common.kit.ComkitUtils
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
    private val TYPES = arrayOf("int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte")
    private val HEX_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    
    
    /** ********** ********** ********** Public ********** ********** ********** */
    
    /** ********** ********** Object, Array, List, String ********** ********** */
    
    /**
     * Object to string.
     * e.g. object2String({name=value, name=value, name=value}) returns "Object{ name=value, name=value, name=value }".
     *
     * @param object The Object.
     * @return The object string.
     */
    fun <T : Any> object2String(`object`: T?): String {
        if (`object` == null) return "Object{ object is null }"
        if (`object`.toString()
                        .startsWith(`object`.javaClass.name + "@")) {
            val stringBuilder = StringBuilder(`object`.javaClass.simpleName + "Object{ ")
            val fields = `object`.javaClass.declaredFields
            for (field in fields) {
                field.isAccessible = true
                var flag = false
                for (type in TYPES) {
                    if (field.type.name.equals(type, ignoreCase = true)) {
                        flag = true
                        var value: Any? = null
                        value = try {
                            field.get(`object`)
                        } catch (e: IllegalAccessException) {
                            e
                        } finally {
                            stringBuilder.append(String.format("%s=%s, ", field.name, value?.toString() ?: "null"))
                            break
                        }
                    }
                }
                if (!flag) {
                    stringBuilder.append(String.format("%s=%s, ", field.name, "Object"))
                }
            }
            return stringBuilder.replace(stringBuilder.length - 2, stringBuilder.length - 1, " }")
                    .toString()
        } else {
            return `object`.toString()
        }
    }
    
    /**
     * Array to string.
     * e.g. object2String([item, item, item]) returns "Array[ item, item, item ]".
     *
     * @param array The array.
     * @return The array string.
     */
    fun array2String(array: Array<*>?): String {
        if (array == null) return "Array[ array is null ]"
        val stringBuilder = StringBuilder()
        val arraySize = array.size
        for (idx in 0 until arraySize) {
            if (idx == 0) {
                stringBuilder.append("Array[ ")
            }
            stringBuilder.append(array[idx].toString())
            if (idx == (arraySize - 1)) {
                stringBuilder.append(" ]")
            } else {
                stringBuilder.append(", ")
            }
        }
        return stringBuilder.toString()
    }
    
    /**
     * List to string.
     * e.g.
     *
     * @param list The list.
     * @return The list string.
     */
    fun list2String(list: List<*>?): String {
        if (list == null) return "List[ list is null ]"
        return list.toString()
    }
    
    /** ********** ********** Bytes, Bits, Chars, Hex string ********** ********** */
    
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
        var bits: String = bits ?: return null
        
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
    
    /**
     * Bytes to hex string.
     * e.g. bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns "00A8".
     *
     * @param bytes The bytes.
     * @return The hex string.
     */
    fun bytes2HexString(bytes: ByteArray?): String {
        if (bytes == null || bytes.isEmpty()) return ""
        val bytesSize = bytes.size
        val chars = CharArray(bytesSize shl 1)
        var j = 0
        for (i in 0 until bytesSize) {
            chars[j++] = HEX_DIGITS[(bytes[i].toInt() shr 4) and 0x0f]
            chars[j++] = HEX_DIGITS[(bytes[i] and 0x0f).toInt()]
        }
        return String(chars)
    }
    
    /**
     * Hex string to bytes.
     * e.g. hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }.
     *
     * @param hexString The hex string.
     * @return The bytes.
     */
    fun hexString2Bytes(hexString: String): ByteArray? {
        if (StringUtils.isSpace(hexString)) return null
        var hexString = hexString
        var len = hexString.length
        if (len % 2 != 0) {
            hexString = "0$hexString"
            len += 1
        }
        val hexBytes = hexString.toUpperCase()
                .toCharArray()
        val ret = ByteArray(len shr 1)
        for (i in 0 until len step 2) {
            ret[i shr 1] = (hexChar2Int(hexBytes[i]) shl 4 or hexChar2Int(hexBytes[i + 1])).toByte()
        }
        return ret
    }
    
    /** ********** ********** Hex char, Int ********** ********** */
    
    /**
     * Hex char to int.
     *
     * @param hexChar The hex char.
     * @return The int.
     */
    fun hexChar2Int(hexChar: Char): Int {
        return when (hexChar) {
            in '0'..'9' -> {
                hexChar - '0'
            }
            in 'A'..'F' -> {
                hexChar - 'A' + 10
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }
    
    /**
     * Int to hex char.
     *
     * @param int The int.
     * @return The hex char.
     */
    fun int2HexChar(int: Int): Char {
        return int.toChar()
    }
    
    /** ********** ********** Memory size, Bytes,  ********** ********** */
    
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
    
    /** ********** ********** Time ********** ********** */
    
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
                stringBuilder.append(mode)
                        .append(units[i])
            }
        }
        return stringBuilder.toString()
    }
    
    /** ********** ********** Stream, Bytes, String ********** ********** */
    
    /**
     * Input stream to output stream.
     *
     * @param input The input stream.
     * @return The output stream.
     */
    fun inputStream2ByteArrayOutputStream(input: InputStream?): ByteArrayOutputStream? {
        return if (input == null) null else try {
            val baos = ByteArrayOutputStream()
            val bytes = ByteArray(MemoryUnit.KB)
            var len: Int
            while (input.read(bytes, 0, MemoryUnit.KB)
                            .also { len = it } != -1) {
                baos.write(bytes, 0, len)
            }
            baos
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } finally {
            try {
                input.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    
    /**
     * Output stream to input stream.
     *
     * @param output The output stream.
     * @return The input stream.
     */
    fun outputStream2ByteArrayInputStream(output: OutputStream?): ByteArrayInputStream? {
        return if (output == null) null else ByteArrayInputStream((output as ByteArrayOutputStream).toByteArray())
    }
    
    /**
     * Input stream to bytes.
     *
     * @param input The input stream.
     * @return The bytes.
     */
    fun inputStream2Bytes(input: InputStream?): ByteArray? {
        return if (input == null) null else inputStream2ByteArrayOutputStream(input)?.toByteArray()
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
     * @param input       The input stream.
     * @param charsetName The name of charset.
     * @return The string.
     */
    fun inputStream2String(input: InputStream?, charsetName: String): String {
        return if (input == null || StringUtils.isSpace(charsetName)) "" else try {
            val baos = inputStream2ByteArrayOutputStream(input) ?: return ""
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
    
    /** ********** ********** Bitmap, Bytes, Drawable, View ********** ********** */
    
    fun bitmap2Bytes(bitmap: Bitmap?, format: CompressFormat?): ByteArray? {
        return ImageUtils.bitmap2Bytes(bitmap, format)
    }
    
    fun bytes2Bitmap(bytes: ByteArray?): Bitmap? {
        return ImageUtils.bytes2Bitmap(bytes)
    }
    
    fun bitmap2Drawable(context: Context = ComkitUtils.getApp(), bitmap: Bitmap?): Drawable? {
        return ImageUtils.bitmap2Drawable(context, bitmap)
    }
    
    fun drawable2Bitmap(drawable: Drawable): Bitmap? {
        return ImageUtils.drawable2Bitmap(drawable)
    }
    
    fun drawable2Bytes(drawable: Drawable?, format: CompressFormat?): ByteArray? {
        return ImageUtils.drawable2Bytes(drawable, format)
    }
    
    fun bytes2Drawable(context: Context = ComkitUtils.getApp(), bytes: ByteArray?): Drawable? {
        return ImageUtils.bytes2Drawable(context, bytes)
    }
    
    fun view2Bitmap(view: View?): Bitmap? {
        return ImageUtils.view2Bitmap(view)
    }
    
    /** ********** ********** Dp, Px, Sp ********** ********** */
    
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
