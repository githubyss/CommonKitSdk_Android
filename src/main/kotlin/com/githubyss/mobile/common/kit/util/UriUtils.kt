package com.githubyss.mobile.common.kit.util

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import com.githubyss.mobile.common.kit.ComkitApplicationConfig
import java.io.File


/**
 * UriUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 15:38:32
 */
object UriUtils {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    private val TAG = UriUtils::class.simpleName ?: "simpleName is null"
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    /** ********** ********** Getter ********** ********** */
    
    fun getFileFromUri(uri: Uri?, code: Int, selection: String? = null, selectionArgs: Array<String>? = null, context: Context? = ComkitApplicationConfig.getApp()): File? {
        uri ?: return null
        context ?: return null
        
        val cursor: Cursor? = context.contentResolver.query(uri, arrayOf("_data"), selection, selectionArgs, null)
        if (cursor == null) {
            Log.d("UriUtils", "$uri parse failed(cursor is null). -> $code")
            return null
        }
        
        return try {
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex("_data")
                if (columnIndex > -1) {
                    File(cursor.getString(columnIndex))
                } else {
                    Log.d("UriUtils", "$uri parse failed(columnIndex: $columnIndex is wrong). -> $code")
                    null
                }
            } else {
                Log.d("UriUtils", "$uri parse failed(moveToFirst return false). -> $code")
                null
            }
        } catch (e: Exception) {
            Log.d("UriUtils", "$uri parse failed. -> $code")
            null
        } finally {
            cursor.close()
        }
    }
    
    /** ********** ********** Converter ********** ********** */
    
    /**
     * File to uri.
     *
     * @param file The file.
     * @return uri
     */
    fun file2Uri(file: File?, context: Context? = ComkitApplicationConfig.getApp()): Uri? {
        file ?: return null
        context ?: return null
        
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val authority: String = context.packageName.toString() + ".utilcode.provider"
            FileProvider.getUriForFile(context, authority, file)
        } else {
            Uri.fromFile(file)
        }
    }
    
    /**
     * Uri to file.
     *
     * @param uri The uri.
     * @return file
     */
    fun uri2File(uri: Uri?, context: Context? = ComkitApplicationConfig.getApp()): File? {
        uri ?: return null
        context ?: return null
        
        LogUtils.d("UriUtils", uri.toString())
        val authority = uri.authority
        val scheme = uri.scheme
        val path = uri.path
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && path != null && path.startsWith("/external")) {
            return File(Environment.getExternalStorageDirectory().absolutePath + path.replace("/external", ""))
        }
        return if (ContentResolver.SCHEME_FILE == scheme) {
            if (path != null) return File(path)
            LogUtils.d("UriUtils", "$uri parse failed. -> 0")
            null
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, uri)) {
            if ("com.android.externalstorage.documents" == authority) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":")
                    .toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return File(Environment.getExternalStorageDirectory()
                        .toString() + "/" + split[1])
                }
                LogUtils.d("UriUtils", "$uri parse failed. -> 1")
                null
            } else if ("com.android.providers.downloads.documents" == authority) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id))
                getFileFromUri(contentUri, 2)
            } else if ("com.android.providers.media.documents" == authority) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":")
                    .toTypedArray()
                val type = split[0]
                val contentUri: Uri
                contentUri = if ("image" == type) {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                } else {
                    LogUtils.d("UriUtils", "$uri parse failed. -> 3")
                    return null
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                getFileFromUri(contentUri, 4, selection, selectionArgs)
            } else if (ContentResolver.SCHEME_CONTENT == scheme) {
                getFileFromUri(uri, 5)
            } else {
                LogUtils.d("UriUtils", "$uri parse failed. -> 6")
                null
            }
        } else if (ContentResolver.SCHEME_CONTENT == scheme) {
            getFileFromUri(uri, 7)
        } else {
            LogUtils.d("UriUtils", "$uri parse failed. -> 8")
            null
        }
    }
}
