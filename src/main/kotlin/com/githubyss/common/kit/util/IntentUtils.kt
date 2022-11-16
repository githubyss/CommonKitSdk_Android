package com.githubyss.common.kit.util

import android.Manifest.permission
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.annotation.RequiresPermission
import androidx.core.content.FileProvider
import com.githubyss.common.base.application.BaseApplicationHolder
import java.io.File
import java.util.*


/**
 * IntentUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 15:15:34
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "IntentUtils"


/** ****************************** Functions ****************************** */

/** ******************** Getter ******************** */

/**
 * Return the intent of install app.
 *
 * Target APIs greater than 25 must hold
 * `<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />`
 *
 * @param filePath  The path of file.
 * @param isNewTask True to add flag of new task, false otherwise.
 * @param context   The context.
 * @return the intent of install app
 */
fun getInstallAppIntent(filePath: String?, isNewTask: Boolean = false, context: Context? = BaseApplicationHolder.getApp()): Intent? {
    filePath ?: return null
    context ?: return null

    return getInstallAppIntent(getFileByPath(filePath), isNewTask, context)
}

/**
 * Return the intent of install app.
 *
 * Target APIs greater than 25 must hold
 * `<uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />`
 *
 * @param file      The file.
 * @param isNewTask True to add flag of new task, false otherwise.
 * @param context   The context.
 * @return the intent of install app
 */
fun getInstallAppIntent(file: File?, isNewTask: Boolean = false, context: Context? = BaseApplicationHolder.getApp()): Intent? {
    file ?: return null
    context ?: return null

    val intent = Intent(Intent.ACTION_VIEW)
    val data: Uri
    val type = "application/vnd.android.package-archive"
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
        data = Uri.fromFile(file)
    }
    else {
        intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        val authority = context.packageName.toString() + ".utilcode.provider"
        data = FileProvider.getUriForFile(context, authority, file)
    }
    // context.grantUriPermission(context.packageName, data, Intent.FLAG_GRANT_READ_URI_PERMISSION)
    intent.setDataAndType(data, type)
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of uninstall app.
 *
 * @param packageName The name of the package.
 * @param isNewTask   True to add flag of new task, false otherwise.
 * @return the intent of uninstall app
 */
fun getUninstallAppIntent(packageName: String?, isNewTask: Boolean = false): Intent? {
    packageName ?: return null

    val intent = Intent(Intent.ACTION_DELETE)
    intent.data = Uri.parse("package:$packageName")
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of launch app.
 *
 * @param packageName The name of the package.
 * @param isNewTask   True to add flag of new task, false otherwise.
 * @param context     The context.
 * @return the intent of launch app
 */
fun getLaunchAppIntent(packageName: String?, isNewTask: Boolean = false, context: Context? = BaseApplicationHolder.getApp()): Intent? {
    packageName ?: return null
    context ?: return null

    val intent = context.packageManager.getLaunchIntentForPackage(packageName) ?: return null
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of launch app details settings.
 *
 * @param packageName The name of the package.
 * @param isNewTask   True to add flag of new task, false otherwise.
 * @return the intent of launch app details settings
 */
fun getLaunchAppDetailsSettingsIntent(packageName: String?, isNewTask: Boolean = false): Intent? {
    packageName ?: return null

    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:$packageName")
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of share text.
 *
 * @param content   The content.
 * @param isNewTask True to add flag of new task, false otherwise.
 * @return the intent of share text
 */
fun getShareTextIntent(content: String?, isNewTask: Boolean = false): Intent? {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, content)
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of share image.
 *
 * @param content   The content.
 * @param imagePath The path of image.
 * @param isNewTask True to add flag of new task, false otherwise.
 * @return the intent of share image
 */
fun getShareImageIntent(content: String?, imagePath: String?, isNewTask: Boolean = false): Intent? {
    imagePath ?: return null
    if (imagePath.isEmpty()) return null

    return getShareImageIntent(content, File(imagePath), isNewTask)
}

/**
 * Return the intent of share image.
 *
 * @param content   The content.
 * @param image     The file of image.
 * @param isNewTask True to add flag of new task, false otherwise.
 * @return the intent of share image
 */
fun getShareImageIntent(content: String?, image: File?, isNewTask: Boolean = false): Intent? {
    image ?: return null
    if (!image.isFile) return null

    return getShareImageIntent(content, file2Uri(image), isNewTask)
}

/**
 * Return the intent of share image.
 *
 * @param content   The content.
 * @param uri       The uri of image.
 * @param isNewTask True to add flag of new task, false otherwise.
 * @return the intent of share image
 */
fun getShareImageIntent(content: String?, uri: Uri?, isNewTask: Boolean = false): Intent? {
    val intent = Intent(Intent.ACTION_SEND)
    intent.putExtra(Intent.EXTRA_TEXT, content)
    intent.putExtra(Intent.EXTRA_STREAM, uri)
    intent.type = "image/*"
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of share images.
 *
 * @param content    The content.
 * @param imagePaths The paths of images.
 * @param isNewTask  True to add flag of new task, false otherwise.
 * @return the intent of share images
 */
fun getShareImageIntent(content: String?, imagePaths: LinkedList<String?>?, isNewTask: Boolean = false): Intent? {
    imagePaths ?: return null
    if (imagePaths.isEmpty()) return null

    val files: MutableList<File> = ArrayList()
    for (imagePath in imagePaths) {
        files.add(File(imagePath))
    }
    return getShareImageIntent(content, files, isNewTask)
}

/**
 * Return the intent of share images.
 *
 * @param content   The content.
 * @param images    The files of images.
 * @param isNewTask True to add flag of new task, false otherwise.
 * @return the intent of share images
 */
fun getShareImageIntent(content: String?, images: List<File>?, isNewTask: Boolean = false): Intent? {
    images ?: return null
    if (images.isEmpty()) return null

    val uris = ArrayList<Uri>()
    for (image in images) {
        if (!image.isFile) continue
        uris.add(file2Uri(image) ?: return null)
    }
    return getShareImageIntent(content, uris, isNewTask)
}

/**
 * Return the intent of share images.
 *
 * @param content   The content.
 * @param uris      The uris of image.
 * @param isNewTask True to add flag of new task, false otherwise.
 * @return the intent of share image
 */
fun getShareImageIntent(content: String?, uris: ArrayList<Uri>?, isNewTask: Boolean = false): Intent? {
    val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
    intent.putExtra(Intent.EXTRA_TEXT, content)
    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris)
    intent.type = "image/*"
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of component.
 *
 * @param packageName The name of the package.
 * @param className   The name of class.
 * @param bundle      The Bundle of extras to add to this intent.
 * @param isNewTask   True to add flag of new task, false otherwise.
 * @return the intent of component
 */
fun getComponentIntent(packageName: String?, className: String?, bundle: Bundle? = null, isNewTask: Boolean = false): Intent? {
    packageName ?: return null
    className ?: return null

    val intent = Intent(Intent.ACTION_VIEW)
    if (bundle != null) intent.putExtras(bundle)
    val cn = ComponentName(packageName, className)
    intent.component = cn
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of shutdown.
 *
 * Requires root permission
 * or hold `android:sharedUserId="android.uid.system"`,
 * `<uses-permission android:name="android.permission.SHUTDOWN/>`
 * in manifest.
 *
 * @param isNewTask True to add flag of new task, false otherwise.
 * @return the intent of shutdown
 */
fun getShutdownIntent(isNewTask: Boolean = false): Intent? {
    val intent = Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN")
    intent.putExtra("android.intent.extra.KEY_CONFIRM", false)
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of dial.
 *
 * @param phoneNumber The phone number.
 * @param isNewTask   True to add flag of new task, false otherwise.
 * @return the intent of dial
 */
fun getDialIntent(phoneNumber: String?, isNewTask: Boolean = false): Intent? {
    phoneNumber ?: return null

    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of call.
 *
 * Must hold `<uses-permission android:name="android.permission.CALL_PHONE" />`
 *
 * @param phoneNumber The phone number.
 * @param isNewTask   True to add flag of new task, false otherwise.
 * @return the intent of call
 */
@RequiresPermission(permission.CALL_PHONE)
fun getCallIntent(phoneNumber: String?, isNewTask: Boolean = false): Intent? {
    phoneNumber ?: return null

    val intent = Intent("android.intent.action.CALL", Uri.parse("tel:$phoneNumber"))
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of send SMS.
 *
 * @param phoneNumber The phone number.
 * @param content     The content of SMS.
 * @param isNewTask   True to add flag of new task, false otherwise.
 * @return the intent of send SMS
 */
fun getSendSmsIntent(phoneNumber: String?, content: String?, isNewTask: Boolean = false): Intent? {
    phoneNumber ?: return null

    val uri = Uri.parse("smsto:$phoneNumber")
    val intent = Intent(Intent.ACTION_SENDTO, uri)
    intent.putExtra("sms_body", content)
    return getIntent(intent, isNewTask)
}

/**
 * Return the intent of capture.
 *
 * @param outUri    The uri of output.
 * @param isNewTask True to add flag of new task, false otherwise.
 * @return the intent of capture
 */
fun getCaptureIntent(outUri: Uri?, isNewTask: Boolean = false): Intent? {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    intent.putExtra(MediaStore.EXTRA_OUTPUT, outUri)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    return getIntent(intent, isNewTask)
}

/**  */
@JvmName("getIntent1")
fun getIntent(intent: Intent?, isNewTask: Boolean = false) = if (isNewTask) intent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) else intent
fun Intent.getIntent(isNewTask: Boolean = false) = getIntent(this, isNewTask)

/**
 * Return the intent of given clazz.
 *
 * @param context
 * @param clazz
 * @return
 */
fun getIntent(context: Context?, clazz: Class<*>) = Intent(context, clazz)

/**
 * Return the intent of given action.
 *
 * @param action
 * @param uri
 * @return
 */
fun getIntent(action: String, uri: Uri? = null) = Intent(action, uri)

/**
 * Return the intent of given T target.
 *
 * @param T The target extend T (Context or BroadcastReceiver).
 * @param context The context.
 * @return
 */
@JvmName("getIntent1")
inline fun <reified T : Any> getIntent(context: Context?) = when {
    extendsClass<T>("android.content.Context") -> getIntent(context, T::class.java)
    extendsClass<T>("android.content.BroadcastReceiver") -> getIntent(context, T::class.java)
    else -> null
}

inline fun <reified T : Any> Context?.getIntent() = getIntent<T>(this)

/**
 * Return the pending intent of given Context target.
 *
 * @param C The target extend Context.
 * @param context
 * @param requestCode
 * @param extra
 * @return
 */
@JvmName("getPendingIntent1")
inline fun <reified C : Context> getPendingIntent(context: Context?, requestCode: Int = 0, extra: String = "") = getPendingIntent<C>(context, getIntent<C>(context), requestCode, extra)
inline fun <reified C : Context> Context?.getPendingIntent(requestCode: Int = 0, extra: String = "") = getPendingIntent<C>(this, requestCode, extra)

/**
 * Return the pending intent of given BroadcastReceiver target with action.
 *
 * @param B The target extend BroadcastReceiver.
 * @param context
 * @param action
 * @param requestCode
 * @param extra
 * @return
 */
@JvmName("getPendingIntent1")
inline fun <reified B : BroadcastReceiver> getPendingIntent(context: Context?, action: String, requestCode: Int = 0, extra: String = "") = getPendingIntent<B>(context, getIntent<B>(context)?.apply { this.action = action }, requestCode, extra)
inline fun <reified B : BroadcastReceiver> Context?.getPendingIntent(action: String, requestCode: Int = 0, extra: String = "") = getPendingIntent<B>(this, action, requestCode, extra)

/**
 * Return the pending intent of given T target.
 *
 * @param T The target extend T (Context or BroadcastReceiver)
 * @param context
 * @param intent
 * @param requestCode
 * @param extra
 * @return
 */
@JvmName("getPendingIntent1")
inline fun <reified T : Any> getPendingIntent(context: Context?, intent: Intent?, requestCode: Int = 0, extra: String = ""): PendingIntent? {
    context ?: return null
    intent ?: return null

    intent.apply {
        /** 使用 FLAG_ACTIVITY_CLEAR_TOP 会将该启动的 activity 上层 activity 弹出栈，确保该 activity 能显示在顶层 */
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        /** 这是为了让 intent 能够带上 extras 数据一起传递，否则在 intent 的比较的过程中会被忽略掉。*/
        data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
        /** 传入参数。*/
        putExtra("extra", extra)
    }

    val pendingIntentFlag = when {
        Build.VERSION.SDK_INT >= 31 -> PendingIntent.FLAG_MUTABLE
        else -> PendingIntent.FLAG_UPDATE_CURRENT
    }

    return when {
        extendsClass<T>("android.content.Context") -> PendingIntent.getActivity(context, requestCode, intent, pendingIntentFlag)
        extendsClass<T>("android.content.BroadcastReceiver") -> PendingIntent.getBroadcast(context, requestCode, intent, pendingIntentFlag)
        else -> null
    }
}

inline fun <reified T : Any> Context?.getPendingIntent(intent: Intent?, requestCode: Int = 0, extra: String = "") = getPendingIntent<T>(this, intent, requestCode, extra)


/** ******************** Checker ******************** */

/**
 * Return whether the intent is available.
 *
 * @param intent  The intent.
 * @param context The context.
 * @return `true`: yes<br></br>`false`: no
 */
fun isIntentAvailable(intent: Intent?, context: Context? = BaseApplicationHolder.getApp()): Boolean {
    intent ?: return false
    context ?: return false

    return context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size > 0
}
