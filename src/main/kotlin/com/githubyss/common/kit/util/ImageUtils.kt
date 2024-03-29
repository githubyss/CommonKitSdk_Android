package com.githubyss.common.kit.util

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.Bitmap.CompressFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.RenderScript.RSMessageHandler
import android.renderscript.ScriptIntrinsicBlur
import android.view.View
import androidx.annotation.*
import androidx.annotation.IntRange
import com.githubyss.common.base.application.BaseApplicationHolder
import com.githubyss.common.kit.enumeration.VersionCode
import java.io.*


/**
 * ImageUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:00:50
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "ImageUtils"


/** ****************************** Functions ****************************** */

/** ********** getBitmap by no option ********** */

/**
 * Return bitmap.
 *
 * @param filePath The path of file.
 * @return bitmap
 */
fun getBitmap(filePath: String?): Bitmap? {
    filePath ?: return null
    if (isSpace(filePath)) return null

    return BitmapFactory.decodeFile(filePath)
}

/**
 * Return bitmap.
 *
 * @param file The file.
 * @return bitmap
 */
fun getBitmap(file: File?): Bitmap? {
    file ?: return null

    return BitmapFactory.decodeFile(file.absolutePath)
}

/**
 * Return bitmap.
 *
 * @param `is` The input stream.
 * @return bitmap
 */
fun getBitmap(`is`: InputStream?): Bitmap? {
    `is` ?: return null

    return BitmapFactory.decodeStream(`is`)
}

/**
 * Return bitmap.
 *
 * @param bytes  The data.
 * @param offset The offset.
 * @return bitmap
 */
fun getBitmap(bytes: ByteArray?, offset: Int): Bitmap? {
    bytes ?: return null
    if (bytes.isEmpty()) return null

    return BitmapFactory.decodeByteArray(bytes, offset, bytes.size)
}

/**
 * Return bitmap.
 *
 * @param fileDescriptor The file descriptor.
 * @return bitmap
 */
fun getBitmap(fileDescriptor: FileDescriptor?): Bitmap? {
    fileDescriptor ?: return null

    return BitmapFactory.decodeFileDescriptor(fileDescriptor)
}

/**
 * Return bitmap.
 *
 * @param resId The resource id.
 * @return bitmap
 */
fun getBitmap(@DrawableRes resId: Int, context: Context? = BaseApplicationHolder.getApp()): Bitmap? {
    context ?: return null

    val drawable = getDrawableFromRes(resId) ?: return null
    val canvas = Canvas()
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    canvas.setBitmap(bitmap)
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    drawable.draw(canvas)
    return bitmap
}

/** ********** getBitmap by maxWidth, maxHeight ********** */

/**
 * Return bitmap.
 *
 * @param filePath  The path of file.
 * @param maxWidth  The maximum width.
 * @param maxHeight The maximum height.
 * @return bitmap
 */
fun getBitmap(filePath: String?, maxWidth: Int, maxHeight: Int): Bitmap? {
    filePath ?: return null
    if (isSpace(filePath)) return null

    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(filePath, options)
    options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight)
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeFile(filePath, options)
}

/**
 * Return bitmap.
 *
 * @param file      The file.
 * @param maxWidth  The maximum width.
 * @param maxHeight The maximum height.
 * @return bitmap
 */
fun getBitmap(file: File?, maxWidth: Int, maxHeight: Int): Bitmap? {
    file ?: return null

    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(file.absolutePath, options)
    options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight)
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeFile(file.absolutePath, options)
}

/**
 * Return bitmap.
 *
 * @param `is` The input stream.
 * @param maxWidth    The maximum width.
 * @param maxHeight   The maximum height.
 * @return bitmap
 */
fun getBitmap(`is`: InputStream?, maxWidth: Int, maxHeight: Int): Bitmap? {
    `is` ?: return null

    val bytes = input2Byte(`is`)
    return getBitmap(bytes, 0, maxWidth, maxHeight)
}

/**
 * Return bitmap.
 *
 * @param bytes     The data.
 * @param offset    The offset.
 * @param maxWidth  The maximum width.
 * @param maxHeight The maximum height.
 * @return bitmap
 */
fun getBitmap(bytes: ByteArray?, offset: Int, maxWidth: Int, maxHeight: Int): Bitmap? {
    bytes ?: return null
    if (bytes.isEmpty()) return null

    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeByteArray(bytes, offset, bytes.size, options)
    options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight)
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeByteArray(bytes, offset, bytes.size, options)
}

/**
 * Return bitmap.
 *
 * @param fileDescriptor The file descriptor
 * @param maxWidth       The maximum width.
 * @param maxHeight      The maximum height.
 * @return bitmap
 */
fun getBitmap(fileDescriptor: FileDescriptor?, maxWidth: Int, maxHeight: Int): Bitmap? {
    fileDescriptor ?: return null

    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options)
    options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight)
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options)
}

/**
 * Return bitmap.
 *
 * @param resId     The resource id.
 * @param maxWidth  The maximum width.
 * @param maxHeight The maximum height.
 * @return bitmap
 */
fun getBitmap(@DrawableRes resId: Int, maxWidth: Int, maxHeight: Int): Bitmap? {
    val options = BitmapFactory.Options()
    val resources: Resources? = BaseApplicationHolder.getApp()?.resources
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, resId, options)
    options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight)
    options.inJustDecodeBounds = false
    return BitmapFactory.decodeResource(resources, resId, options)
}

/** ********** getImageType ********** */

/**
 * Return the type of image.
 *
 * @param filePath The path of file.
 * @return the type of image
 */
fun getImageType(filePath: String?): String {
    return getImageType(getFileByPath(filePath))
}

/**
 * Return the type of image.
 *
 * @param file The file.
 * @return the type of image
 */
fun getImageType(file: File?): String {
    file ?: return ""

    var `is`: InputStream? = null
    try {
        `is` = FileInputStream(file)
        val type = getImageType(`is`)
        if (type != null) {
            return type
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
    return getFileExtension(file.absolutePath).toUpperCase()
}

fun getImageType(`is`: InputStream?): String {
    `is` ?: return ""

    return try {
        val bytes = ByteArray(8)
        if (`is`.read(bytes, 0, 8) != -1) getImageType(bytes) else ""
    }
    catch (e: IOException) {
        logE(TAG, t = e)
        ""
    }
}

fun getImageType(bytes: ByteArray?): String {
    return when {
        isJPEG(bytes) -> "JPEG"
        isGIF(bytes) -> "GIF"
        isPNG(bytes) -> "PNG"
        isBMP(bytes) -> "BMP"
        else -> ""
    }
}

/**
 * Return the size of bitmap.
 *
 * @param filePath The path of file.
 * @return the size of bitmap
 */
fun getSize(filePath: String?): IntArray? {
    return getSize(getFileByPath(filePath))
}

/**
 * Return the size of bitmap.
 *
 * @param file The file.
 * @return the size of bitmap
 */
fun getSize(file: File?): IntArray? {
    file ?: return null

    val opts = BitmapFactory.Options()
    opts.inJustDecodeBounds = true
    BitmapFactory.decodeFile(file.absolutePath, opts)
    return intArrayOf(opts.outWidth, opts.outHeight)
}

/** ******************** Checker ******************** */

/**
 * Return whether it is a image according to the file name.
 *
 * @param file The file.
 * @return `true`: yes<br></br>`false`: no
 */
fun isImage(file: File?): Boolean {
    file ?: return false
    if (!file.exists()) return false

    return isImage(file.path)
}

/**
 * Return whether it is a image according to the file name.
 *
 * @param filePath The path of file.
 * @return `true`: yes<br></br>`false`: no
 */
fun isImage(filePath: String?): Boolean {
    filePath ?: return false

    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    return try {
        val bitmap = BitmapFactory.decodeFile(filePath, options)
        options.outWidth != -1 && options.outHeight != -1
    }
    catch (e: Exception) {
        false
    }
}

fun isJPEG(bytes: ByteArray?): Boolean {
    bytes ?: return false

    return bytes.size >= 2 && bytes[0] == 0xFF.toByte() && bytes[1] == 0xD8.toByte()
}

fun isGIF(bytes: ByteArray?): Boolean {
    bytes ?: return false

    return bytes.size >= 6 && bytes[0].equals('G') && bytes[1].equals('I') && bytes[2].equals('F') && bytes[3].equals('8') && (bytes[4].equals('7') || bytes[4].equals('9')) && bytes[5].equals('a')
}

fun isPNG(bytes: ByteArray?): Boolean {
    bytes ?: return false

    return bytes.size >= 8 && bytes[0] == 137.toByte() && bytes[1] == 80.toByte() && bytes[2] == 78.toByte() && bytes[3] == 71.toByte() && bytes[4] == 13.toByte() && bytes[5] == 10.toByte() && bytes[6] == 26.toByte() && bytes[7] == 10.toByte()
}

fun isBMP(bytes: ByteArray?): Boolean {
    bytes ?: return false

    return bytes.size >= 2 && bytes[0].equals(0x42) && bytes[1].equals(0x4d)
}

fun isEmptyBitmap(src: Bitmap?): Boolean {
    src ?: return false

    return src.width == 0 || src.height == 0
}

/** ******************** Converter ******************** */

/**
 * Bitmap to bytes.
 *
 * @param bitmap The bitmap.
 * @param format The format of bitmap.
 * @return The bytes.
 */
fun bitmap2Bytes(bitmap: Bitmap?, format: CompressFormat?): ByteArray? {
    bitmap ?: return null

    val baos = ByteArrayOutputStream()
    bitmap.compress(format, 100, baos)
    return baos.toByteArray()
}

/**
 * Bytes to bitmap.
 *
 * @param bytes The bytes.
 * @return The bitmap.
 */
fun bytes2Bitmap(bytes: ByteArray?): Bitmap? {
    bytes ?: return null
    if (bytes.isEmpty()) return null

    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}

/**
 * Bitmap to drawable.
 *
 * @param bitmap The bitmap.
 * @return The drawable.
 */
fun bitmap2Drawable(bitmap: Bitmap?, context: Context? = BaseApplicationHolder.getApp()): Drawable? {
    bitmap ?: return null

    return BitmapDrawable(getContextResources(context), bitmap)
}

/**
 * Drawable to bitmap.
 *
 * @param drawable The drawable.
 * @return The bitmap.
 */
fun drawable2Bitmap(drawable: Drawable?): Bitmap? {
    drawable ?: return null

    if (drawable is BitmapDrawable) {
        if (drawable.bitmap != null) {
            return drawable.bitmap
        }
    }
    val ret: Bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
        Bitmap.createBitmap(1, 1, if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565)
    }
    else {
        Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565)
    }
    val canvas = Canvas(ret)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return ret
}

/**
 * Drawable to bytes.
 *
 * @param drawable The drawable.
 * @param format   The format of bitmap.
 * @return The bytes.
 */
fun drawable2Bytes(drawable: Drawable?, format: CompressFormat?): ByteArray? {
    drawable ?: return null

    return bitmap2Bytes(drawable2Bitmap(drawable), format)
}

/**
 * Bytes to drawable.
 *
 * @param bytes The bytes.
 * @return The drawable.
 */
fun bytes2Drawable(bytes: ByteArray?, context: Context? = BaseApplicationHolder.getApp()): Drawable? {
    bytes ?: return null

    return bitmap2Drawable(bytes2Bitmap(bytes), context)
}

/**
 * View to bitmap.
 *
 * @param view The view.
 * @return The bitmap.
 */
fun view2Bitmap(view: View?): Bitmap? {
    view ?: return null

    val ret = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(ret)
    val bgDrawable = view.background
    if (bgDrawable != null) {
        bgDrawable.draw(canvas)
    }
    else {
        canvas.drawColor(Color.WHITE)
    }
    view.draw(canvas)
    return ret
}

/** ******************** Processor ******************** */

/** ********** draw ********** */

/**
 * Return the bitmap with the specified color.
 *
 * @param src     The source of bitmap.
 * @param color   The color.
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the bitmap with the specified color.
 */
fun drawColor(src: Bitmap?, @ColorInt color: Int, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val ret = if (recycle) src else src.copy(src.config, true)
    val canvas = Canvas(ret)
    canvas.drawColor(color, PorterDuff.Mode.DARKEN)
    return ret
}

/** ********** scale ********** */

/**
 * Return the scaled bitmap.
 *
 * @param src       The source of bitmap.
 * @param newWidth  The new width.
 * @param newHeight The new height.
 * @param recycle   True to recycle the source of bitmap, false otherwise.
 * @return the scaled bitmap
 */
fun scale(src: Bitmap?, newWidth: Int, newHeight: Int, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val ret = Bitmap.createScaledBitmap(src, newWidth, newHeight, true)
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/**
 * Return the scaled bitmap
 *
 * @param src     The source of bitmap.
 * @param scale   The scale of width and height.
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the scaled bitmap
 */
fun scale(src: Bitmap?, scale: Float, recycle: Boolean = false): Bitmap? {
    return scale(src, scale, scale, recycle)
}

/**
 * Return the scaled bitmap
 *
 * @param src         The source of bitmap.
 * @param scaleWidth  The scale of width.
 * @param scaleHeight The scale of height.
 * @param recycle     True to recycle the source of bitmap, false otherwise.
 * @return the scaled bitmap
 */
fun scale(src: Bitmap?, scaleWidth: Float, scaleHeight: Float, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val matrix = Matrix()
    matrix.setScale(scaleWidth, scaleHeight)
    var ret: Bitmap? = null
    ret = try {
        Bitmap.createBitmap(src, 0, 0, src.width, src.height, matrix, true)
    }
    catch (e: IllegalArgumentException) {
        return ret
    }
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/** ********** clip ********** */

/**
 * Return the clipped bitmap.
 *
 * @param src     The source of bitmap.
 * @param x       The x coordinate of the first pixel.
 * @param y       The y coordinate of the first pixel.
 * @param width   The width.
 * @param height  The height.
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the clipped bitmap
 */
fun clip(src: Bitmap?, x: Int, y: Int, width: Int, height: Int, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val ret = Bitmap.createBitmap(src, x, y, width, height)
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/** ********** skew ********** */

/**
 * Return the skewed bitmap.
 *
 * @param src     The source of bitmap.
 * @param kx      The skew factor of x.
 * @param ky      The skew factor of y.
 * @param px      The x coordinate of the pivot point.
 * @param py      The y coordinate of the pivot point.
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the skewed bitmap
 */
fun skew(src: Bitmap?, kx: Float, ky: Float, px: Float = 0f, py: Float = 0f, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val matrix = Matrix()
    matrix.setSkew(kx, ky, px, py)
    val ret = Bitmap.createBitmap(src, 0, 0, src.width, src.height, matrix, true)
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/** ********** rotate ********** */

/**
 * Return the rotated bitmap.
 *
 * @param src     The source of bitmap.
 * @param degrees The number of degrees.
 * @param px      The x coordinate of the pivot point.
 * @param py      The y coordinate of the pivot point.
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the rotated bitmap
 */
fun rotate(src: Bitmap?, degrees: Int, px: Float = 0f, py: Float = 0f, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    if (degrees == 0) return src
    val matrix = Matrix()
    matrix.setRotate(degrees.toFloat(), px, py)
    val ret = Bitmap.createBitmap(src, 0, 0, src.width, src.height, matrix, true)
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/**
 * Return the rotated degree.
 *
 * @param filePath The path of file.
 * @return the rotated degree
 */
fun getRotateDegree(filePath: String?): Int {
    filePath ?: return -1

    return try {
        val exifInterface = ExifInterface(filePath)
        val orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }
    }
    catch (e: IOException) {
        logE(TAG, t = e)
        -1
    }
}

/** ********** toRound ********** */

/**
 * Return the round bitmap.
 *
 * @param src         The source of bitmap.
 * @param recycle     True to recycle the source of bitmap, false otherwise.
 * @param borderSize  The size of border.
 * @param borderColor The color of border.
 * @return the round bitmap
 */
fun toRound(src: Bitmap?, @IntRange(from = 0) borderSize: Int = 0, @ColorInt borderColor: Int = 0, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val width = src.width
    val height = src.height
    val size = Math.min(width, height)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val ret = Bitmap.createBitmap(width, height, src.config)
    val center = size / 2f
    val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
    rectF.inset((width - size) / 2f, (height - size) / 2f)
    val matrix = Matrix()
    matrix.setTranslate(rectF.left, rectF.top)
    if (width != height) {
        matrix.preScale(size.toFloat() / width, size.toFloat() / height)
    }
    val shader = BitmapShader(src, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    shader.setLocalMatrix(matrix)
    paint.shader = shader
    val canvas = Canvas(ret)
    canvas.drawRoundRect(rectF, center, center, paint)
    if (borderSize > 0) {
        paint.shader = null
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderSize.toFloat()
        val radius = center - borderSize / 2f
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/**
 * Return the round corner bitmap.
 *
 * @param src         The source of bitmap.
 * @param radius      The radius of corner.
 * @param borderSize  The size of border.
 * @param borderColor The color of border.
 * @param recycle     True to recycle the source of bitmap, false otherwise.
 * @return the round corner bitmap
 */
fun toRoundCorner(src: Bitmap?, radius: Float, @IntRange(from = 0) borderSize: Int = 0, @ColorInt borderColor: Int = 0, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val width = src.width
    val height = src.height
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val ret = Bitmap.createBitmap(width, height, src.config)
    val shader = BitmapShader(src, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
    paint.shader = shader
    val canvas = Canvas(ret)
    val rectF = RectF(0f, 0f, width.toFloat(), height.toFloat())
    val halfBorderSize = borderSize / 2f
    rectF.inset(halfBorderSize, halfBorderSize)
    canvas.drawRoundRect(rectF, radius, radius, paint)
    if (borderSize > 0) {
        paint.shader = null
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = borderSize.toFloat()
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawRoundRect(rectF, radius, radius, paint)
    }
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/** ********** toAlpha ********** */

/**
 * Return the alpha bitmap.
 *
 * @param src     The source of bitmap.
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the alpha bitmap
 */
fun toAlpha(src: Bitmap?, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val ret = src.extractAlpha()
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/** ********** toGray ********** */

/**
 * Return the gray bitmap.
 *
 * @param src     The source of bitmap.
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the gray bitmap
 */
fun toGray(src: Bitmap?, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val ret = Bitmap.createBitmap(src.width, src.height, src.config)
    val canvas = Canvas(ret)
    val paint = Paint()
    val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(0f)
    val colorMatrixColorFilter = ColorMatrixColorFilter(colorMatrix)
    paint.colorFilter = colorMatrixColorFilter
    canvas.drawBitmap(src, 0f, 0f, paint)
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/** ********** addBorder ********** */

/**
 * Return the round corner bitmap with border.
 *
 * @param src          The source of bitmap.
 * @param borderSize   The size of border.
 * @param color        The color of border.
 * @param cornerRadius The radius of corner.
 * @param recycle      True to recycle the source of bitmap, false otherwise.
 * @return the round corner bitmap with border
 */
fun addCornerBorder(src: Bitmap?, @IntRange(from = 1) borderSize: Int, @ColorInt color: Int, @FloatRange(from = 0.0) cornerRadius: Float, recycle: Boolean = false): Bitmap? {
    return addBorder(src, borderSize, color, false, cornerRadius, recycle)
}

/**
 * Return the round bitmap with border.
 *
 * @param src        The source of bitmap.
 * @param borderSize The size of border.
 * @param color      The color of border.
 * @param recycle    True to recycle the source of bitmap, false otherwise.
 * @return the round bitmap with border
 */
fun addCircleBorder(src: Bitmap?, @IntRange(from = 1) borderSize: Int, @ColorInt color: Int, recycle: Boolean = false): Bitmap? {
    return addBorder(src, borderSize, color, true, 0f, recycle)
}

/**
 * Return the bitmap with border.
 *
 * @param src          The source of bitmap.
 * @param borderSize   The size of border.
 * @param color        The color of border.
 * @param isCircle     True to draw circle, false to draw corner.
 * @param cornerRadius The radius of corner.
 * @param recycle      True to recycle the source of bitmap, false otherwise.
 * @return the bitmap with border
 */
private fun addBorder(src: Bitmap?, @IntRange(from = 1) borderSize: Int, @ColorInt color: Int, isCircle: Boolean, cornerRadius: Float, recycle: Boolean): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val ret = if (recycle) src else src.copy(src.config, true)
    val width = ret.width
    val height = ret.height
    val canvas = Canvas(ret)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    paint.color = color
    paint.style = Paint.Style.STROKE
    paint.strokeWidth = borderSize.toFloat()
    if (isCircle) {
        val radius = Math.min(width, height) / 2f - borderSize / 2f
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
    }
    else {
        val halfBorderSize = borderSize shr 1
        val rectF = RectF(halfBorderSize.toFloat(), halfBorderSize.toFloat(), (width - halfBorderSize).toFloat(), (height - halfBorderSize).toFloat())
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint)
    }
    return ret
}

/** ********** addReflection ********** */

/**
 * Return the bitmap with reflection.
 *
 * @param src              The source of bitmap.
 * @param reflectionHeight The height of reflection.
 * @param recycle          True to recycle the source of bitmap, false otherwise.
 * @return the bitmap with reflection
 */
fun addReflection(src: Bitmap?, reflectionHeight: Int, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val REFLECTION_GAP = 0
    val srcWidth = src.width
    val srcHeight = src.height
    val matrix = Matrix()
    matrix.preScale(1f, -1f)
    val reflectionBitmap = Bitmap.createBitmap(src, 0, srcHeight - reflectionHeight, srcWidth, reflectionHeight, matrix, false)
    val ret = Bitmap.createBitmap(srcWidth, srcHeight + reflectionHeight, src.config)
    val canvas = Canvas(ret)
    canvas.drawBitmap(src, 0f, 0f, null)
    canvas.drawBitmap(reflectionBitmap, 0f, srcHeight + REFLECTION_GAP.toFloat(), null)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val shader = LinearGradient(0f, srcHeight.toFloat(), 0f, (ret.height + REFLECTION_GAP).toFloat(), 0x70FFFFFF, 0x00FFFFFF, Shader.TileMode.MIRROR)
    paint.shader = shader
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    canvas.drawRect(0f, srcHeight + REFLECTION_GAP.toFloat(), srcWidth.toFloat(), ret.height.toFloat(), paint)
    if (!reflectionBitmap.isRecycled) reflectionBitmap.recycle()
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/** ********** addWatermark ********** */

/**
 * Return the bitmap with text watermarking.
 *
 * @param src      The source of bitmap.
 * @param content  The content of text.
 * @param textSize The size of text.
 * @param color    The color of text.
 * @param x        The x coordinate of the first pixel.
 * @param y        The y coordinate of the first pixel.
 * @param recycle  True to recycle the source of bitmap, false otherwise.
 * @return the bitmap with text watermarking
 */
fun addTextWatermark(src: Bitmap?, content: String?, textSize: Float, @ColorInt color: Int, x: Float, y: Float, recycle: Boolean = false): Bitmap? {
    src ?: return null
    content ?: return null
    if (isEmptyBitmap(src)) return null

    val ret = src.copy(src.config, true)
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val canvas = Canvas(ret)
    paint.color = color
    paint.textSize = textSize
    val bounds = Rect()
    paint.getTextBounds(content, 0, content.length, bounds)
    canvas.drawText(content, x, y + textSize, paint)
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/**
 * Return the bitmap with image watermarking.
 *
 * @param src       The source of bitmap.
 * @param watermark The image watermarking.
 * @param x         The x coordinate of the first pixel.
 * @param y         The y coordinate of the first pixel.
 * @param alpha     The alpha of watermark.
 * @param recycle   True to recycle the source of bitmap, false otherwise.
 * @return the bitmap with image watermarking
 */
fun addImageWatermark(src: Bitmap?, watermark: Bitmap?, x: Int, y: Int, alpha: Int, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val ret = src.copy(src.config, true)
    if (watermark != null && !isEmptyBitmap(watermark)) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val canvas = Canvas(ret)
        paint.alpha = alpha
        canvas.drawBitmap(watermark, x.toFloat(), y.toFloat(), paint)
    }
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/** ********** fastBlur ********** */

/**
 * Return the blur bitmap fast.
 *
 * zoom out, blur, zoom in
 *
 * @param src           The source of bitmap.
 * @param scale         The scale(0...1).
 * @param radius        The radius(0...25).
 * @param recycle       True to recycle the source of bitmap, false otherwise.
 * @param isReturnScale True to return the scale blur bitmap, false otherwise.
 * @return the blur bitmap
 */
fun fastBlur(src: Bitmap?, @FloatRange(from = 0.0, to = 1.0, fromInclusive = false) scale: Float, @FloatRange(from = 0.0, to = 25.0, fromInclusive = false) radius: Float, recycle: Boolean = false, isReturnScale: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val width = src.width
    val height = src.height
    val matrix = Matrix()
    matrix.setScale(scale, scale)
    var scaleBitmap = Bitmap.createBitmap(src, 0, 0, src.width, src.height, matrix, true)
    val paint = Paint(Paint.FILTER_BITMAP_FLAG or Paint.ANTI_ALIAS_FLAG)
    val canvas = Canvas()
    val filter = PorterDuffColorFilter(Color.TRANSPARENT, PorterDuff.Mode.SRC_ATOP)
    paint.colorFilter = filter
    canvas.scale(scale, scale)
    canvas.drawBitmap(scaleBitmap, 0f, 0f, paint)
    if (Build.VERSION.SDK_INT >= VersionCode.JELLY_BEAN_MR1) {
        scaleBitmap = renderScriptBlur(scaleBitmap, radius, recycle)
    }
    else {
        scaleBitmap = stackBlur(scaleBitmap, radius.toInt(), recycle)
    }
    if (scale == 1f || isReturnScale) {
        if (recycle && !src.isRecycled && scaleBitmap != src) src.recycle()
        return scaleBitmap
    }
    val ret = Bitmap.createScaledBitmap(scaleBitmap, width, height, true)
    if (!scaleBitmap.isRecycled) scaleBitmap.recycle()
    if (recycle && !src.isRecycled && ret != src) src.recycle()
    return ret
}

/**
 * Return the blur bitmap using render script.
 *
 * @param src     The source of bitmap.
 * @param radius  The radius(0...25).
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the blur bitmap
 */
@RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
fun renderScriptBlur(src: Bitmap?, @FloatRange(from = 0.0, to = 25.0, fromInclusive = false) radius: Float, recycle: Boolean = false): Bitmap? {
    src ?: return null

    var rs: RenderScript? = null
    val ret = if (recycle) src else src.copy(src.config, true)
    try {
        rs = RenderScript.create(BaseApplicationHolder.getApp())
        rs.messageHandler = RSMessageHandler()
        val input = Allocation.createFromBitmap(rs, ret, Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT)
        val output = Allocation.createTyped(rs, input.type)
        val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        blurScript.setInput(input)
        blurScript.setRadius(radius)
        blurScript.forEach(output)
        output.copyTo(ret)
    }
    finally {
        rs?.destroy()
    }
    return ret
}

/**
 * Return the blur bitmap using stack.
 *
 * @param src     The source of bitmap.
 * @param radius  The radius(0...25).
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the blur bitmap
 */
fun stackBlur(src: Bitmap?, radius: Int, recycle: Boolean = false): Bitmap? {
    src ?: return null

    var radius = radius
    val ret = if (recycle) src else src.copy(src.config, true)
    if (radius < 1) {
        radius = 1
    }
    val w = ret.width
    val h = ret.height
    val pix = IntArray(w * h)
    ret.getPixels(pix, 0, w, 0, 0, w, h)
    val wm = w - 1
    val hm = h - 1
    val wh = w * h
    val div = radius + radius + 1
    val r = IntArray(wh)
    val g = IntArray(wh)
    val b = IntArray(wh)
    var rsum: Int
    var gsum: Int
    var bsum: Int
    var x: Int
    var y: Int
    var i: Int
    var p: Int
    var yp: Int
    var yi: Int
    var yw: Int
    val vmin = IntArray(Math.max(w, h))
    var divsum = div + 1 shr 1
    divsum *= divsum
    val dv = IntArray(256 * divsum)
    i = 0
    while (i < 256 * divsum) {
        dv[i] = i / divsum
        i++
    }
    yi = 0
    yw = yi
    val stack = Array(div) { IntArray(3) }
    var stackpointer: Int
    var stackstart: Int
    var sir: IntArray
    var rbs: Int
    val r1 = radius + 1
    var routsum: Int
    var goutsum: Int
    var boutsum: Int
    var rinsum: Int
    var ginsum: Int
    var binsum: Int
    y = 0
    while (y < h) {
        bsum = 0
        gsum = bsum
        rsum = gsum
        boutsum = rsum
        goutsum = boutsum
        routsum = goutsum
        binsum = routsum
        ginsum = binsum
        rinsum = ginsum
        i = -radius
        while (i <= radius) {
            p = pix[yi + Math.min(wm, Math.max(i, 0))]
            sir = stack[i + radius]
            sir[0] = p and 0xff0000 shr 16
            sir[1] = p and 0x00ff00 shr 8
            sir[2] = p and 0x0000ff
            rbs = r1 - Math.abs(i)
            rsum += sir[0] * rbs
            gsum += sir[1] * rbs
            bsum += sir[2] * rbs
            if (i > 0) {
                rinsum += sir[0]
                ginsum += sir[1]
                binsum += sir[2]
            }
            else {
                routsum += sir[0]
                goutsum += sir[1]
                boutsum += sir[2]
            }
            i++
        }
        stackpointer = radius
        x = 0
        while (x < w) {
            r[yi] = dv[rsum]
            g[yi] = dv[gsum]
            b[yi] = dv[bsum]
            rsum -= routsum
            gsum -= goutsum
            bsum -= boutsum
            stackstart = stackpointer - radius + div
            sir = stack[stackstart % div]
            routsum -= sir[0]
            goutsum -= sir[1]
            boutsum -= sir[2]
            if (y == 0) {
                vmin[x] = Math.min(x + radius + 1, wm)
            }
            p = pix[yw + vmin[x]]
            sir[0] = p and 0xff0000 shr 16
            sir[1] = p and 0x00ff00 shr 8
            sir[2] = p and 0x0000ff
            rinsum += sir[0]
            ginsum += sir[1]
            binsum += sir[2]
            rsum += rinsum
            gsum += ginsum
            bsum += binsum
            stackpointer = (stackpointer + 1) % div
            sir = stack[stackpointer % div]
            routsum += sir[0]
            goutsum += sir[1]
            boutsum += sir[2]
            rinsum -= sir[0]
            ginsum -= sir[1]
            binsum -= sir[2]
            yi++
            x++
        }
        yw += w
        y++
    }
    x = 0
    while (x < w) {
        bsum = 0
        gsum = bsum
        rsum = gsum
        boutsum = rsum
        goutsum = boutsum
        routsum = goutsum
        binsum = routsum
        ginsum = binsum
        rinsum = ginsum
        yp = -radius * w
        i = -radius
        while (i <= radius) {
            yi = Math.max(0, yp) + x
            sir = stack[i + radius]
            sir[0] = r[yi]
            sir[1] = g[yi]
            sir[2] = b[yi]
            rbs = r1 - Math.abs(i)
            rsum += r[yi] * rbs
            gsum += g[yi] * rbs
            bsum += b[yi] * rbs
            if (i > 0) {
                rinsum += sir[0]
                ginsum += sir[1]
                binsum += sir[2]
            }
            else {
                routsum += sir[0]
                goutsum += sir[1]
                boutsum += sir[2]
            }
            if (i < hm) {
                yp += w
            }
            i++
        }
        yi = x
        stackpointer = radius
        y = 0
        while (y < h) {
            // Preserve alpha channel: ( 0xff000000 & pix[yi] )
            pix[yi] = -0x1000000 and pix[yi] or (dv[rsum] shl 16) or (dv[gsum] shl 8) or dv[bsum]
            rsum -= routsum
            gsum -= goutsum
            bsum -= boutsum
            stackstart = stackpointer - radius + div
            sir = stack[stackstart % div]
            routsum -= sir[0]
            goutsum -= sir[1]
            boutsum -= sir[2]
            if (x == 0) {
                vmin[y] = Math.min(y + r1, hm) * w
            }
            p = x + vmin[y]
            sir[0] = r[p]
            sir[1] = g[p]
            sir[2] = b[p]
            rinsum += sir[0]
            ginsum += sir[1]
            binsum += sir[2]
            rsum += rinsum
            gsum += ginsum
            bsum += binsum
            stackpointer = (stackpointer + 1) % div
            sir = stack[stackpointer]
            routsum += sir[0]
            goutsum += sir[1]
            boutsum += sir[2]
            rinsum -= sir[0]
            ginsum -= sir[1]
            binsum -= sir[2]
            yi += w
            y++
        }
        x++
    }
    ret.setPixels(pix, 0, w, 0, 0, w, h)
    return ret
}

/** ********** save ********** */

/**
 * Save the bitmap.
 *
 * @param src      The source of bitmap.
 * @param filePath The path of file.
 * @param format   The format of the image.
 * @param recycle  True to recycle the source of bitmap, false otherwise.
 * @return `true`: success<br></br>`false`: fail
 */
fun save(src: Bitmap?, filePath: String?, format: CompressFormat?, recycle: Boolean = false): Boolean {
    return save(src, getFileByPath(filePath), format, recycle)
}

/**
 * Save the bitmap.
 *
 * @param src     The source of bitmap.
 * @param file    The file.
 * @param format  The format of the image.
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return `true`: success<br></br>`false`: fail
 */
fun save(src: Bitmap?, file: File?, format: CompressFormat?, recycle: Boolean = false): Boolean {
    src ?: return false
    file ?: return false
    if (isEmptyBitmap(src) || !createFileByDeleteOldFile(file)) return false

    var os: OutputStream? = null
    var ret = false
    try {
        os = BufferedOutputStream(FileOutputStream(file))
        ret = src.compress(format, 100, os)
        if (recycle && !src.isRecycled) src.recycle()
    }
    catch (e: IOException) {
        logE(TAG, t = e)
    }
    finally {
        try {
            os?.close()
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
    }
    return ret
}

/** ********** compressByScale ********** */

/**
 * Return the compressed bitmap using scale.
 *
 * @param src       The source of bitmap.
 * @param newWidth  The new width.
 * @param newHeight The new height.
 * @param recycle   True to recycle the source of bitmap, false otherwise.
 * @return the compressed bitmap
 */
fun compressByScale(src: Bitmap?, newWidth: Int, newHeight: Int, recycle: Boolean = false): Bitmap? {
    return scale(src, newWidth, newHeight, recycle)
}

/**
 * Return the compressed bitmap using scale.
 *
 * @param src         The source of bitmap.
 * @param scaleWidth  The scale of width.
 * @param scaleHeight The scale of height.
 * @param recycle     True to recycle the source of bitmap, false otherwise.
 * @return the compressed bitmap
 */
fun compressByScale(src: Bitmap?, scaleWidth: Float, scaleHeight: Float, recycle: Boolean = false): Bitmap? {
    return scale(src, scaleWidth, scaleHeight, recycle)
}

/** ********** compressByQuality ********** */

/**
 * Return the compressed bitmap using quality.
 *
 * @param src     The source of bitmap.
 * @param quality The quality.
 * @param recycle True to recycle the source of bitmap, false otherwise.
 * @return the compressed bitmap
 */
fun compressByQuality(src: Bitmap?, @IntRange(from = 0, to = 100) quality: Int, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val baos = ByteArrayOutputStream()
    src.compress(CompressFormat.JPEG, quality, baos)
    val bytes = baos.toByteArray()
    if (recycle && !src.isRecycled) src.recycle()
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}

/**
 * Return the compressed bitmap using quality.
 *
 * @param src         The source of bitmap.
 * @param maxByteSize The maximum size of byte.
 * @param recycle     True to recycle the source of bitmap, false otherwise.
 * @return the compressed bitmap
 */
fun compressByQuality(src: Bitmap?, maxByteSize: Long, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src) || maxByteSize <= 0) return null

    val baos = ByteArrayOutputStream()
    src.compress(CompressFormat.JPEG, 100, baos)
    val bytes: ByteArray
    if (baos.size() <= maxByteSize) {
        bytes = baos.toByteArray()
    }
    else {
        baos.reset()
        src.compress(CompressFormat.JPEG, 0, baos)
        if (baos.size() >= maxByteSize) {
            bytes = baos.toByteArray()
        }
        else {
            // find the best quality using binary search
            var st = 0
            var end = 100
            var mid = 0
            while (st < end) {
                mid = (st + end) / 2
                baos.reset()
                src.compress(CompressFormat.JPEG, mid, baos)
                val len = baos.size()
                if (len.toLong() == maxByteSize) {
                    break
                }
                else if (len > maxByteSize) {
                    end = mid - 1
                }
                else {
                    st = mid + 1
                }
            }
            if (end == mid - 1) {
                baos.reset()
                src.compress(CompressFormat.JPEG, st, baos)
            }
            bytes = baos.toByteArray()
        }
    }
    if (recycle && !src.isRecycled) src.recycle()
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}

/** ********** compressBySampleSize ********** */

/**
 * Return the compressed bitmap using sample size.
 *
 * @param src        The source of bitmap.
 * @param sampleSize The sample size.
 * @param recycle    True to recycle the source of bitmap, false otherwise.
 * @return the compressed bitmap
 */
fun compressBySampleSize(src: Bitmap?, sampleSize: Int, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val options = BitmapFactory.Options()
    options.inSampleSize = sampleSize
    val baos = ByteArrayOutputStream()
    src.compress(CompressFormat.JPEG, 100, baos)
    val bytes = baos.toByteArray()
    if (recycle && !src.isRecycled) src.recycle()
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)
}

/**
 * Return the compressed bitmap using sample size.
 *
 * @param src       The source of bitmap.
 * @param maxWidth  The maximum width.
 * @param maxHeight The maximum height.
 * @param recycle   True to recycle the source of bitmap, false otherwise.
 * @return the compressed bitmap
 */
fun compressBySampleSize(src: Bitmap?, maxWidth: Int, maxHeight: Int, recycle: Boolean = false): Bitmap? {
    src ?: return null
    if (isEmptyBitmap(src)) return null

    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    val baos = ByteArrayOutputStream()
    src.compress(CompressFormat.JPEG, 100, baos)
    val bytes = baos.toByteArray()
    BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)
    options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight)
    options.inJustDecodeBounds = false
    if (recycle && !src.isRecycled) src.recycle()
    return BitmapFactory.decodeByteArray(bytes, 0, bytes.size, options)
}


/** ****************************** Private ****************************** */

/**
 * Return the sample size.
 *
 * @param options   The options.
 * @param maxWidth  The maximum width.
 * @param maxHeight The maximum height.
 * @return the sample size
 */
private fun calculateInSampleSize(options: BitmapFactory.Options?, maxWidth: Int, maxHeight: Int): Int {
    options ?: return -1

    var height = options.outHeight
    var width = options.outWidth
    var inSampleSize = 1
    while (height > maxHeight || width > maxWidth) {
        height = height shr 1
        width = width shr 1
        inSampleSize = inSampleSize shl 1
    }
    // if (width > maxHeight && width > maxWidth) {
    //     inSampleSize = (int) Math.round(options.outWidth * 1.0 / maxWidth);
    // } else if (width < height && height > maxHeight) {
    //     inSampleSize = (int) Math.round(options.outHeight * 1.0 / maxWidth);
    // }
    return inSampleSize
}

private fun input2Byte(`is`: InputStream?): ByteArray? {
    `is` ?: return null

    return try {
        val os = ByteArrayOutputStream()
        val b = ByteArray(1024)
        var len: Int
        while (`is`.read(b, 0, 1024).also { len = it } != -1) {
            os.write(b, 0, len)
        }
        os.toByteArray()
    }
    catch (e: IOException) {
        logE(TAG, t = e)
        null
    }
    finally {
        try {
            `is`.close()
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
    }
}
