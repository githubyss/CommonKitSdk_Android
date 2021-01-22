package com.githubyss.mobile.common.kit.converter

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import com.githubyss.mobile.common.kit.ComkitApplication
import com.githubyss.mobile.common.kit.enumeration.MemoryUnit
import com.githubyss.mobile.common.kit.enumeration.TimeUnit
import com.githubyss.mobile.common.kit.info.ScreenInfo
import com.githubyss.mobile.common.kit.processor.StringUtils
import java.io.*
import kotlin.experimental.and
import kotlin.math.min


/**
 * ConvertUtils
 * <Description> Converter
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 09:16:11
 */
object ConvertUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val types = arrayOf("int", "java.lang.String", "boolean", "char", "float", "double", "long", "short", "byte")
    private val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
    
    
    /** ********** ********** ********** Public ********** ********** ********** */
    
    /** ********** ********** Object, Array, List, String ********** ********** */
    
    /**
     * <Description> Object to string.
     * <Details> e.g. object2String({name=value, name=value, name=value}) returns "Object{ name=value, name=value, name=value }".
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
                for (type in types) {
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
     * <Description> Array to string.
     * <Details> e.g. object2String([item, item, item]) returns "Array[ item, item, item ]".
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
     * <Description> List to string.
     * <Details> e.g.
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
     * <Description> Bytes to bits.
     * <Details>
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
     * <Description> Bits to bytes.
     * <Details>
     *
     * @param bits The bits.
     * @return The bytes.
     */
    fun bits2Bytes(bits: String): ByteArray? {
        if (StringUtils.isSpace(bits)) return null
        var bits = bits
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
     * <Description> Bytes to chars.
     * <Details>
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
     * <Description> Chars to bytes.
     * <Details>
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
     * <Description> Bytes to hex string.
     * <Details> e.g. bytes2HexString(new byte[] { 0, (byte) 0xa8 }) returns "00A8".
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
            chars[j++] = hexDigits[(bytes[i].toInt() shr 4) and 0x0f]
            chars[j++] = hexDigits[(bytes[i] and 0x0f).toInt()]
        }
        return String(chars)
    }
    
    /**
     * <Description> Hex string to bytes.
     * <Details> e.g. hexString2Bytes("00A8") returns { 0, (byte) 0xA8 }.
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
     * <Description> Hex char to int.
     * <Details>
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
     * <Description> Int to hex char.
     * <Details>
     *
     * @param int The int.
     * @return The hex char.
     */
    fun int2HexChar(int: Int): Char {
        return int.toChar()
    }
    
    /** ********** ********** Memory size, Bytes,  ********** ********** */
    
    /**
     * <Description> Size of memory in unit to size of byte.
     * <Details>
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
     * <Description> Size of byte to size of memory in unit.
     * <Details>
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
     * <Description> Size of byte to fit size of memory.
     * <Details> To three decimal places.
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
     * <Description> Time span in unit to milliseconds.
     * <Details>
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
     * <Description> Milliseconds to time span in unit.
     * <Details>
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
     * <Description> Milliseconds to fit time span.
     * <Details>
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
     * <Description> Input stream to output stream.
     * <Details>
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
     * <Description> Output stream to input stream.
     * <Details>
     *
     * @param output The output stream.
     * @return The input stream.
     */
    fun outputStream2ByteArrayInputStream(output: OutputStream?): ByteArrayInputStream? {
        return if (output == null) null else ByteArrayInputStream((output as ByteArrayOutputStream).toByteArray())
    }
    
    /**
     * <Description> Input stream to bytes.
     * <Details>
     *
     * @param input The input stream.
     * @return The bytes.
     */
    fun inputStream2Bytes(input: InputStream?): ByteArray? {
        return if (input == null) null else inputStream2ByteArrayOutputStream(input)?.toByteArray()
    }
    
    /**
     * <Description> Bytes to input stream.
     * <Details>
     *
     * @param bytes The bytes.
     * @return The input stream.
     */
    fun bytes2InputStream(bytes: ByteArray?): InputStream? {
        return if (bytes == null || bytes.isEmpty()) null else ByteArrayInputStream(bytes)
    }
    
    /**
     * <Description> Output stream to bytes.
     * <Details>
     *
     * @param output The output stream.
     * @return The bytes.
     */
    fun outputStream2Bytes(output: OutputStream?): ByteArray? {
        return if (output == null) null else (output as ByteArrayOutputStream).toByteArray()
    }
    
    /**
     * <Description> Bytes to output stream.
     * <Details>
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
     * <Description> Input stream to string.
     * <Details>
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
     * <Description> String to input stream.
     * <Details>
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
     * <Description>  Output stream to string.
     * <Details>
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
     * <Description> String to output stream.
     * <Details>
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
    
    /**
     * <Description> Bitmap to bytes.
     * <Details>
     *
     * @param bitmap The bitmap.
     * @param format The format of bitmap.
     * @return The bytes.
     */
    fun bitmap2Bytes(bitmap: Bitmap?, format: CompressFormat?): ByteArray? {
        if (bitmap == null) return null
        val baos = ByteArrayOutputStream()
        bitmap.compress(format, 100, baos)
        return baos.toByteArray()
    }
    
    /**
     * <Description> Bytes to bitmap.
     * <Details>
     *
     * @param bytes The bytes.
     * @return The bitmap.
     */
    fun bytes2Bitmap(bytes: ByteArray?): Bitmap? {
        return if (bytes == null || bytes.isEmpty()) null else BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
    
    /**
     * <Description> Bitmap to drawable.
     * <Details>
     *
     * @param bitmap The bitmap.
     * @return The drawable.
     */
    fun bitmap2Drawable(bitmap: Bitmap?): Drawable? {
        return if (bitmap == null) null else BitmapDrawable(ComkitApplication.instance.application?.resources, bitmap)
    }
    
    /**
     * <Description> Drawable to bitmap.
     * <Details>
     *
     * @param drawable The drawable.
     * @return The bitmap.
     */
    fun drawable2Bitmap(drawable: Drawable): Bitmap? {
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }
        val ret: Bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(1, 1, if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565)
        } else {
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565)
        }
        val canvas = Canvas(ret)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return ret
    }
    
    /**
     * <Description> Drawable to bytes.
     * <Details>
     *
     * @param drawable The drawable.
     * @param format   The format of bitmap.
     * @return The bytes.
     */
    fun drawable2Bytes(drawable: Drawable?, format: CompressFormat?): ByteArray? {
        return if (drawable == null) null else bitmap2Bytes(drawable2Bitmap(drawable), format)
    }
    
    /**
     * <Description> Bytes to drawable.
     * <Details>
     *
     * @param bytes The bytes.
     * @return The drawable.
     */
    fun bytes2Drawable(bytes: ByteArray?): Drawable? {
        return if (bytes == null) null else bitmap2Drawable(bytes2Bitmap(bytes))
    }
    
    /**
     * <Description> View to bitmap.
     * <Details>
     *
     * @param view The view.
     * @return The bitmap.
     */
    fun view2Bitmap(view: View?): Bitmap? {
        if (view == null) return null
        val ret = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(ret)
        val bgDrawable = view.background
        if (bgDrawable != null) {
            bgDrawable.draw(canvas)
        } else {
            canvas.drawColor(Color.WHITE)
        }
        view.draw(canvas)
        return ret
    }
    
    /** ********** ********** Dp, Px, Sp ********** ********** */
    
    /**
     * <Description> Value of dp to value of px.
     * <Details>
     *
     * @param dpValue The value of dp.
     * @return The value of px.
     */
    fun dp2Px(dpValue: Float): Int {
        return ScreenInfo.dp2Px(dpValue)
    }
    
    /**
     * <Description>  Value of px to value of dp.
     * <Details>
     *
     * @param pxValue The value of px.
     * @return The value of dp.
     */
    fun px2Dp(pxValue: Float): Int {
        return ScreenInfo.px2Dp(pxValue)
    }
    
    /**
     * <Description> Value of sp to value of px.
     * <Details>
     *
     * @param spValue The value of sp.
     * @return The value of px.
     */
    fun sp2Px(spValue: Float): Int {
        return ScreenInfo.sp2Px(spValue)
    }
    
    /**
     * <Description> Value of px to value of sp.
     * <Details>
     *
     * @param pxValue The value of px.
     * @return The value of sp.
     */
    fun px2Sp(pxValue: Float): Int {
        return ScreenInfo.px2Sp(pxValue)
    }
}
