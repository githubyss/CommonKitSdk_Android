package com.githubyss.mobile.common.kit.manager.permission

import android.Manifest
import android.os.Build
import com.githubyss.mobile.common.kit.enumeration.PermissionType


/**
 * PermissionInfo
 * <Description> 权限工具
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/29 14:37:58
 */
object PermissionManager {
    
    private val GROUP_CALENDAR = arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)
    private val GROUP_CAMERA = arrayOf(Manifest.permission.CAMERA)
    private val GROUP_CONTACTS = arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS)
    private val GROUP_LOCATION = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    private val GROUP_MICROPHONE = arrayOf(Manifest.permission.RECORD_AUDIO)
    private val GROUP_PHONE = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.ADD_VOICEMAIL, Manifest.permission.USE_SIP, Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.ANSWER_PHONE_CALLS)
    private val GROUP_PHONE_BELOW_O = arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.ADD_VOICEMAIL, Manifest.permission.USE_SIP, Manifest.permission.PROCESS_OUTGOING_CALLS)
    private val GROUP_SENSORS = arrayOf(Manifest.permission.BODY_SENSORS)
    private val GROUP_SMS = arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_WAP_PUSH, Manifest.permission.RECEIVE_MMS)
    private val GROUP_STORAGE = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    
    fun getPermissions(@PermissionType permission: String): Array<String> {
        when (permission) {
            PermissionType.CALENDAR -> return GROUP_CALENDAR
            PermissionType.CAMERA -> return GROUP_CAMERA
            PermissionType.CONTACTS -> return GROUP_CONTACTS
            PermissionType.LOCATION -> return GROUP_LOCATION
            PermissionType.MICROPHONE -> return GROUP_MICROPHONE
            PermissionType.PHONE -> return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                GROUP_PHONE_BELOW_O
            } else {
                GROUP_PHONE
            }
            PermissionType.SENSORS -> return GROUP_SENSORS
            PermissionType.SMS -> return GROUP_SMS
            PermissionType.STORAGE -> return GROUP_STORAGE
        }
        return arrayOf(permission)
    }
}
