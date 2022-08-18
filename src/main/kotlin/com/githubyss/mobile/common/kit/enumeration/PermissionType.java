package com.githubyss.mobile.common.kit.enumeration;

import android.Manifest;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.StringDef;


/**
 * PermissionType
 * 权限类型
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/29 14:26:23
 */
@Documented
@StringDef({PermissionType.CALENDAR, PermissionType.CAMERA, PermissionType.CONTACTS, PermissionType.LOCATION, PermissionType.MICROPHONE, PermissionType.PHONE, PermissionType.SENSORS, PermissionType.SMS, PermissionType.STORAGE})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface PermissionType {

    final String CALENDAR = Manifest.permission_group.CALENDAR;
    final String CAMERA = Manifest.permission_group.CAMERA;
    final String CONTACTS = Manifest.permission_group.CONTACTS;
    final String LOCATION = Manifest.permission_group.LOCATION;
    final String MICROPHONE = Manifest.permission_group.MICROPHONE;
    final String PHONE = Manifest.permission_group.PHONE;
    final String SENSORS = Manifest.permission_group.SENSORS;
    final String SMS = Manifest.permission_group.SMS;
    final String STORAGE = Manifest.permission_group.STORAGE;
}
