package com.githubyss.mobile.common.kit.manager.screenshot

import android.app.Application
import android.content.Context
import android.database.ContentObserver
import android.database.Cursor
import android.graphics.BitmapFactory
import android.graphics.Point
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.provider.MediaStore
import android.text.TextUtils
import androidx.annotation.RequiresApi
import com.githubyss.mobile.common.kit.logcat.LogcatUtils
import com.githubyss.mobile.common.kit.info.ScreenInfo
import com.githubyss.mobile.common.kit.processor.ComkitThreadProcessor
import com.githubyss.mobile.common.kit.processor.ComkitTimeProcessor
import java.lang.Exception
import java.lang.ref.WeakReference

/**
 * ComkitScreenshotDetectManager
 * <Description> Detect the action of screenshot.
 * <Details>
 *     Detect data change in media database to decide whether catch screenshot or not.
 *     Obtain the latest image data inserted in media database.
 *     It will be regarded as screenshot action when the image data accord with the following judgements:
 *     1. Datetime:
 *     2. Measure size: Measure size of the image is not beyond the screen size;
 *     3. File path: There are keywords in the image file path.
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComkitScreenshotDetectManager private constructor() {
    companion object {
        var instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = ComkitScreenshotDetectManager()
    }


    interface OnScreenshotDetectListener {
        fun onScreenshotDetect(path: String)
    }


    private val TABLE_MEDIA_IMAGE_COLUMNS = arrayOf(MediaStore.Images.ImageColumns.DATA, MediaStore.Images.ImageColumns.DATE_TAKEN)

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    private val TABLE_MEDIA_IMAGE_COLUMNS_AFTER_JELLY_BEAN = arrayOf(MediaStore.Images.ImageColumns.DATA, MediaStore.Images.ImageColumns.DATE_TAKEN, MediaStore.Images.ImageColumns.WIDTH, MediaStore.Images.ImageColumns.HEIGHT)

    private val PATH_KEYWORDS = arrayOf("screenshot", "screen_shot", "screen-shot", "screen shot", "screencapture", "screen_capture", "screen-capture", "screen capture", "screencap", "screen_cap", "screen-cap", "screen cap")

    private var onScreenshotDetectListener: OnScreenshotDetectListener? = null
    private var internalObserver: MediaContentObserver? = null
    private var externalObserver: MediaContentObserver? = null

    private var startDetectTime = -1L
    private var callbackPathList: MutableList<String>? = null
    private var actualScreenPoint: Point? = null
    private var uiHandler: Handler? = null


    init {
        ComkitThreadProcessor.assertMainThread()
        callbackPathList = ArrayList(10)
        uiHandler = Handler(Looper.getMainLooper())
    }


    private class MediaContentObserver constructor(private val screenshotDetectManagerWeakRef: WeakReference<ComkitScreenshotDetectManager>, private val context: Context, private val uri: Uri, handler: Handler?) : ContentObserver(handler) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            LogcatUtils.d(msg = SystemClock.elapsedRealtime().toString())
            screenshotDetectManagerWeakRef.get()?.handleOnMediaContentChange(context, uri)
        }
    }


    fun startDetect(application: Application, onScreenshotDetectListener: OnScreenshotDetectListener) {
        ComkitThreadProcessor.assertMainThread()

        actualScreenPoint = ScreenInfo.screenPointPx(application) ?: return
        callbackPathList?.clear()
        this@ComkitScreenshotDetectManager.onScreenshotDetectListener = onScreenshotDetectListener
        startDetectTime = System.currentTimeMillis()

        internalObserver = MediaContentObserver(WeakReference(this@ComkitScreenshotDetectManager), application, MediaStore.Images.Media.INTERNAL_CONTENT_URI, uiHandler)
        externalObserver = MediaContentObserver(WeakReference(this@ComkitScreenshotDetectManager), application, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, uiHandler)

        internalObserver?.let { application.contentResolver.registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI, false, it) }
        externalObserver?.let { application.contentResolver.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, false, it) }
    }

    fun stopDetect(application: Application) {
        ComkitThreadProcessor.assertMainThread()

        callbackPathList?.clear()
        startDetectTime = -1L

        try {
            internalObserver?.let { application.contentResolver.unregisterContentObserver(it) }
            internalObserver = null
        } catch (e: Exception) {
            LogcatUtils.e(msg = e.toString())
        }

        try {
            externalObserver?.let { application.contentResolver.unregisterContentObserver(it) }
            externalObserver = null
        } catch (e: Exception) {
            LogcatUtils.e(msg = e.toString())
        }
    }

    private fun handleOnMediaContentChange(context: Context, uri: Uri) {
        LogcatUtils.d(msg = SystemClock.elapsedRealtime().toString())

        try {
            val contentResolver = context.contentResolver ?: return
            var tableImageMediaCursor: Cursor? = null
            try {
                tableImageMediaCursor = contentResolver.query(uri, if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) TABLE_MEDIA_IMAGE_COLUMNS else TABLE_MEDIA_IMAGE_COLUMNS_AFTER_JELLY_BEAN, null, null, "${MediaStore.Images.ImageColumns.DATE_ADDED} desc limit 1")
            } catch (e: Exception) {
                LogcatUtils.e(msg = e.toString())
            }
            tableImageMediaCursor ?: return

            if (tableImageMediaCursor.moveToFirst()) {
                LogcatUtils.d(msg = SystemClock.elapsedRealtime().toString())

                val pathStr = tableImageMediaCursor.getString(tableImageMediaCursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
                val dateTakenLong = tableImageMediaCursor.getLong(tableImageMediaCursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN))

                var widthIndex = -1
                var heightIndex = -1
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    widthIndex = tableImageMediaCursor.getColumnIndex(MediaStore.Images.ImageColumns.WIDTH)
                    heightIndex = tableImageMediaCursor.getColumnIndex(MediaStore.Images.ImageColumns.HEIGHT)
                }

                val widthInt: Int
                val heightInt: Int
                if (widthIndex >= 0 && heightIndex >= 0) {
                    widthInt = tableImageMediaCursor.getInt(widthIndex)
                    heightInt = tableImageMediaCursor.getInt(heightIndex)
                } else {
                    val point = getImagePoint(pathStr)
                    widthInt = point.x
                    heightInt = point.y
                }

                LogcatUtils.d(msg = SystemClock.elapsedRealtime().toString())
                checkMediaData(context, pathStr, dateTakenLong, widthInt, heightInt)
            }
            tableImageMediaCursor.close()
        } catch (e: SecurityException) {
            LogcatUtils.e(msg = e.toString())
        }
    }

    private fun checkMediaData(context: Context, path: String, dateTaken: Long, width: Int, height: Int): Boolean {
        return if (checkScreenshot(context, path, dateTaken, width, height)) {
            if (!checkCallbackPath(path)) {
                LogcatUtils.d(msg = "onScreenshotDetect time: ${SystemClock.elapsedRealtime()}")
                onScreenshotDetectListener?.onScreenshotDetect(path)
                true
            } else {
                LogcatUtils.d(msg = "")
                false
            }
        } else {
            LogcatUtils.d(msg = "Media data changed, but not screenshot: " + "{time:${SystemClock.elapsedRealtime()}}\t" + "{dateTaken:$dateTaken}\t" + "{path:$path}\t" + "{width:$width, height:$height}\t")
            false
        }
    }

    private fun checkScreenshot(context: Context, path: String, dateTaken: Long, width: Int, height: Int): Boolean = checkDateTaken(dateTaken) && checkImageSize(width, height) && checkPathKeywords(path)

    private fun checkDateTaken(dateTaken: Long): Boolean {
        LogcatUtils.d(msg = "checkDateTaken(): " + "{dateTaken:$dateTaken, startDetectTime:$startDetectTime, currentTimeMillis:${System.currentTimeMillis()}}\t" + "{dateTaken-startDetectTime:${dateTaken - startDetectTime}, currentTimeMillis-dateTaken:${System.currentTimeMillis() - dateTaken}}")
        return (dateTaken > startDetectTime && (System.currentTimeMillis() - dateTaken) < ComkitTimeProcessor.secondToMillis(10))
    }

    private fun checkImageSize(width: Int, height: Int): Boolean {
        LogcatUtils.d(msg = "checkImageSize(): " + "{imageWidth:$width, imageHeight:$height}\t" + "{screenWidth:${actualScreenPoint?.x ?: -1}, screenHeight:${actualScreenPoint?.x ?: -1}}")
        return ((width <= actualScreenPoint?.x ?: -1 && height <= actualScreenPoint?.y ?: -1) || (height <= actualScreenPoint?.x ?: -1 && width <= actualScreenPoint?.y ?: -1))
    }

    private fun checkPathKeywords(path: String): Boolean {
        LogcatUtils.d(msg = "checkPathKeywords(): " + "{path:$path}")
        return (!TextUtils.isEmpty(path) && PATH_KEYWORDS.any { path.toLowerCase().contains(it) })
    }

    private fun checkCallbackPath(path: String): Boolean {
        if (callbackPathList?.contains(path) == true) return true

        /** Cache 15~20 paths. by Ace Yan */
        if ((callbackPathList?.size ?: 0) >= 20) (0 until 5).forEach { idx -> callbackPathList?.removeAt(idx) }
        callbackPathList?.add(path)
        return false
    }

    private fun getImagePoint(path: String): Point {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        return Point(options.outWidth, options.outHeight)
    }
}
