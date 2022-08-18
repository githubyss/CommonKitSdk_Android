package com.githubyss.mobile.common.kit.util


/**
 * ArrayUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/19 17:29:37
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG: String = "ArrayUtils"


/** ****************************** Functions ****************************** */

/** ******************** Checker ******************** */

/**
 * Return whether the array is null or 0-length.
 *
 * @param array The array.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(array: Array<*>?) = array.isEmptyOrNull()
fun Array<*>?.isEmptyOrNull() = this.isNullOrEmpty()

/**
 * Return whether the bytes is null or 0-length.
 *
 * @param bytes The bytes.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(bytes: ByteArray?) = bytes.isEmptyOrNull()
fun ByteArray?.isEmptyOrNull() = this?.isEmpty() ?: true

@JvmName("isEmptyNonNull_")
fun isEmptyNonNull(bytes: ByteArray) = bytes.isEmptyNonNull()
fun ByteArray.isEmptyNonNull() = this.isEmpty()

/**
 * Return whether the chars is null or 0-length.
 *
 * @param chars The chars.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(chars: CharArray?) = chars.isEmptyOrNull()
fun CharArray?.isEmptyOrNull() = this?.isEmpty() ?: true

/**
 * Return whether the shorts is null or 0-length.
 *
 * @param shorts The shorts.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(shorts: ShortArray?) = shorts.isEmptyOrNull()
fun ShortArray?.isEmptyOrNull() = this?.isEmpty() ?: true

/**
 * Return whether the ints is null or 0-length.
 *
 * @param ints The ints.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(ints: IntArray?) = ints.isEmptyOrNull()
fun IntArray?.isEmptyOrNull() = this?.isEmpty() ?: true

/**
 * Return whether the longs is null or 0-length.
 *
 * @param longs The longs.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(longs: LongArray?) = longs.isEmptyOrNull()
fun LongArray?.isEmptyOrNull() = this?.isEmpty() ?: true

/**
 * Return whether the floats is null or 0-length.
 *
 * @param floats The floats.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(floats: FloatArray?) = floats.isEmptyOrNull()
fun FloatArray?.isEmptyOrNull() = this?.isEmpty() ?: true

/**
 * Return whether the doubles is null or 0-length.
 *
 * @param doubles The doubles.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(doubles: DoubleArray?) = doubles.isEmptyOrNull()
fun DoubleArray?.isEmptyOrNull() = this?.isEmpty() ?: true

/**
 * Return whether the booleans is null or 0-length.
 *
 * @param booleans The booleans.
 * @return {@code true}: yes, {@code false}: no.
 */
@JvmName("isEmptyOrNull_")
fun isEmptyOrNull(booleans: BooleanArray?) = booleans.isEmptyOrNull()
fun BooleanArray?.isEmptyOrNull() = this?.isEmpty() ?: true
