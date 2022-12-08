package com.githubyss.common.kit.util

import android.os.Binder
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import com.githubyss.common.kit.constant.VALUE_TYPE_UNSUPPORTED_ERROR
import java.io.Serializable


/**
 * BundleUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/15 10:00:28
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "BundleUtils"


/** ****************************** Functions ****************************** */

fun <V> Bundle.putBundleValue(key: String, value: V) =
    when (value) {
        is Byte -> this.putByte(key, value)
        is ByteArray -> this.putByteArray(key, value)
        is Short -> this.putShort(key, value)
        is ShortArray -> this.putShortArray(key, value)
        is Int -> this.putInt(key, value)
        is IntArray -> this.putIntArray(key, value)
        is Long -> this.putLong(key, value)
        is LongArray -> this.putLongArray(key, value)
        is Float -> this.putFloat(key, value)
        is FloatArray -> this.putFloatArray(key, value)
        is Double -> this.putDouble(key, value)
        is DoubleArray -> this.putDoubleArray(key, value)
        is Char -> this.putChar(key, value)
        is CharArray -> this.putCharArray(key, value)
        is String -> this.putString(key, value)
        is CharSequence -> this.putCharSequence(key, value)
        is Boolean -> this.putBoolean(key, value)
        is BooleanArray -> this.putBooleanArray(key, value)
        is Binder -> this.putBinder(key, value)
        is Size -> this.putSize(key, value)
        is SizeF -> this.putSizeF(key, value)
        is Serializable -> this.putSerializable(key, value)
        is Parcelable -> this.putParcelable(key, value)
        is Bundle -> this.putBundle(key, value)
        else -> throw Exception(VALUE_TYPE_UNSUPPORTED_ERROR)
    }


inline fun <reified V> Bundle.getBundleValue(key: String) =
    when (V::class) {
        Byte::class -> this.getByte(key)
        ByteArray::class -> this.getByteArray(key)
        Short::class -> this.getShort(key)
        ShortArray::class -> this.getShortArray(key)
        Int::class -> this.getInt(key)
        IntArray::class -> this.getIntArray(key)
        Long::class -> this.getLong(key)
        LongArray::class -> this.getLongArray(key)
        Float::class -> this.getFloat(key)
        FloatArray::class -> this.getFloatArray(key)
        Double::class -> this.getDouble(key)
        DoubleArray::class -> this.getDoubleArray(key)
        Char::class -> this.getChar(key)
        CharArray::class -> this.getCharArray(key)
        String::class -> this.getString(key)
        CharSequence::class -> this.getCharSequence(key)
        Boolean::class -> this.getBoolean(key)
        BooleanArray::class -> this.getBooleanArray(key)
        Binder::class -> this.getBinder(key)
        Size::class -> this.getSize(key)
        SizeF::class -> this.getSizeF(key)
        Serializable::class -> this.getSerializable(key)
        Parcelable::class -> this.getParcelable<Parcelable>(key)
        Bundle::class -> this.getBundle(key)
        else -> throw Exception(VALUE_TYPE_UNSUPPORTED_ERROR)
    } as V

inline fun <reified V> Bundle.getBundleValueBasic(key: String) =
    when {
        0.toByte() is V -> this.getByte(key)
        byteArrayOf() is V -> this.getByteArray(key)
        0.toShort() is V -> this.getShort(key)
        shortArrayOf() is V -> this.getShortArray(key)
        0.toInt() is V -> this.getInt(key)
        intArrayOf() is V -> this.getIntArray(key)
        0.toLong() is V -> this.getLong(key)
        longArrayOf() is V -> this.getLongArray(key)
        0.0.toFloat() is V -> this.getFloat(key)
        floatArrayOf() is V -> this.getFloatArray(key)
        0.0.toDouble() is V -> this.getDouble(key)
        doubleArrayOf() is V -> this.getDoubleArray(key)
        '0'.toChar() is V -> this.getChar(key)
        charArrayOf() is V -> this.getCharArray(key)
        "0".toString() is V -> this.getString(key)
        object : CharSequence {
            override val length: Int
                get() = TODO("Not yet implemented")

            override fun get(index: Int): Char {
                TODO("Not yet implemented")
            }

            override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
                TODO("Not yet implemented")
            }
        } is V -> this.getCharSequence(key)
        true is V -> this.getBoolean(key)
        booleanArrayOf() is V -> this.getBooleanArray(key)
        Binder() is V -> this.getBinder(key)
        Size(0, 0) is V -> this.getSize(key)
        SizeF(0.0F, 0.0F) is V -> this.getSizeF(key)
        object : Serializable {} is V -> this.getSerializable(key)
        object : Parcelable {
            override fun describeContents(): Int {
                TODO("Not yet implemented")
            }

            override fun writeToParcel(dest: Parcel?, flags: Int) {
                TODO("Not yet implemented")
            }
        } is V -> this.getParcelable<Parcelable>(key)
        Bundle() is V -> this.getBundle(key)
        else -> throw Exception(VALUE_TYPE_UNSUPPORTED_ERROR)
    } as V
