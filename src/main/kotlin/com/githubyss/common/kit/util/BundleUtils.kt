package com.githubyss.common.kit.util

import android.os.Binder
import android.os.Bundle
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
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

fun <V> Bundle.putBundleValue(key: String, value: V) {
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
        else -> throw Exception("Unsupported value type.")
    }
}

inline fun <reified V> Bundle.getBundleValue(key: String): V {
    return when (V::class) {
        Byte::class -> this.getByte(key) as V
        ByteArray::class -> this.getByteArray(key) as V
        Short::class -> this.getShort(key) as V
        ShortArray::class -> this.getShortArray(key) as V
        Int::class -> this.getInt(key) as V
        IntArray::class -> this.getIntArray(key) as V
        Long::class -> this.getLong(key) as V
        LongArray::class -> this.getLongArray(key) as V
        Float::class -> this.getFloat(key) as V
        FloatArray::class -> this.getFloatArray(key) as V
        Double::class -> this.getDouble(key) as V
        DoubleArray::class -> this.getDoubleArray(key) as V
        Char::class -> this.getChar(key) as V
        CharArray::class -> this.getCharArray(key) as V
        String::class -> this.getString(key) as V
        CharSequence::class -> this.getCharSequence(key) as V
        Boolean::class -> this.getBoolean(key) as V
        BooleanArray::class -> this.getBooleanArray(key) as V
        Binder::class -> this.getBinder(key) as V
        Size::class -> this.getSize(key) as V
        SizeF::class -> this.getSizeF(key) as V
        Serializable::class -> this.getSerializable(key) as V
        Parcelable::class -> this.getParcelable<Parcelable>(key) as V
        Bundle::class -> this.getBundle(key) as V
        else -> throw Exception("Unsupported value type.")
    }
}

inline fun <reified V> Bundle.getBundleValueBasic(key: String): V {
    return when {
        byteArrayOf() is V -> this.getByteArray(key) as V
        shortArrayOf() is V -> this.getShortArray(key) as V
        0 is V -> this.getInt(key) as V
        intArrayOf() is V -> this.getIntArray(key) as V
        0L is V -> this.getLong(key) as V
        longArrayOf() is V -> this.getLongArray(key) as V
        0.0F is V -> this.getFloat(key) as V
        floatArrayOf() is V -> this.getFloatArray(key) as V
        0.0 is V -> this.getDouble(key) as V
        doubleArrayOf() is V -> this.getDoubleArray(key) as V
        '0' is V -> this.getChar(key) as V
        charArrayOf() is V -> this.getCharArray(key) as V
        "0" is V -> this.getString(key) as V
        true is V -> this.getBoolean(key) as V
        booleanArrayOf() is V -> this.getBooleanArray(key) as V
        else -> throw Exception("Unsupported value type.")
    }
}