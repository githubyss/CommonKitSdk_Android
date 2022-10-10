package com.githubyss.common.kit.util

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.security.*
import java.security.spec.AlgorithmParameterSpec
import java.security.spec.InvalidKeySpecException
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.*
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


/**
 * EncryptUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 15:13:54
 */

/** ****************************** Properties ****************************** */

private const val TAG: String = "EncryptUtils"


/** ****************************** Functions ****************************** */

/** ******************** Hash encryption ******************** */

/** ********** MD2 ********** */

/**
 * Return the hex string of MD2 encryption.
 *
 * @param data The data.
 * @return the hex string of MD2 encryption
 */
fun encryptMD2ToString(data: String?): String {
    data ?: return ""
    if (isEmpty(data)) return ""

    return encryptMD2ToString(data.toByteArray())
}

/**
 * Return the hex string of MD2 encryption.
 *
 * @param data The data.
 * @return the hex string of MD2 encryption
 */
fun encryptMD2ToString(data: ByteArray?): String {
    return bytes2HexString(encryptMD2(data))
}

/**
 * Return the bytes of MD2 encryption.
 *
 * @param data The data.
 * @return the bytes of MD2 encryption
 */
fun encryptMD2(data: ByteArray?): ByteArray? {
    return hashTemplate(data, "MD2")
}

/** ********** MD5 ********** */

/**
 * Return the hex string of MD5 encryption.
 *
 * @param data The data.
 * @return the hex string of MD5 encryption
 */
fun encryptMD5ToString(data: String?): String {
    data ?: return ""
    if (isEmpty(data)) return ""

    return encryptMD5ToString(data.toByteArray())
}

/**
 * Return the hex string of MD5 encryption.
 *
 * @param data The data.
 * @param salt The salt.
 * @return the hex string of MD5 encryption
 */
fun encryptMD5ToString(data: String?, salt: String?): String {
    return when {
        data == null && salt == null -> ""
        data != null && salt == null -> bytes2HexString(encryptMD5(data.toByteArray()))
        data == null && salt != null -> bytes2HexString(encryptMD5(salt.toByteArray()))
        data != null && salt != null -> bytes2HexString(encryptMD5((data + salt).toByteArray()))
        else -> ""
    }
}

/**
 * Return the hex string of MD5 encryption.
 *
 * @param data The data.
 * @return the hex string of MD5 encryption
 */
fun encryptMD5ToString(data: ByteArray?): String {
    return bytes2HexString(encryptMD5(data))
}

/**
 * Return the hex string of MD5 encryption.
 *
 * @param data The data.
 * @param salt The salt.
 * @return the hex string of MD5 encryption
 */
fun encryptMD5ToString(data: ByteArray?, salt: ByteArray?): String {
    return when {
        data == null && salt == null -> ""
        data != null && salt == null -> bytes2HexString(encryptMD5(data))
        data == null && salt != null -> bytes2HexString(encryptMD5(salt))
        data != null && salt != null -> {
            val dataSalt = ByteArray(data.size + salt.size)
            System.arraycopy(data, 0, dataSalt, 0, data.size)
            System.arraycopy(salt, 0, dataSalt, data.size, salt.size)
            bytes2HexString(encryptMD5(dataSalt))
        }
        else -> ""
    }
}

/**
 * Return the bytes of MD5 encryption.
 *
 * @param data The data.
 * @return the bytes of MD5 encryption
 */
fun encryptMD5(data: ByteArray?): ByteArray? {
    return hashTemplate(data, "MD5")
}

/** ********** MD5File ********** */

/**
 * Return the hex string of file's MD5 encryption.
 *
 * @param filePath The path of file.
 * @return the hex string of file's MD5 encryption
 */
fun encryptMD5File2String(filePath: String?): String {
    filePath ?: return ""

    val file = if (isSpace(filePath)) null else File(filePath)
    return encryptMD5File2String(file)
}

/**
 * Return the bytes of file's MD5 encryption.
 *
 * @param filePath The path of file.
 * @return the bytes of file's MD5 encryption
 */
fun encryptMD5File(filePath: String?): ByteArray? {
    filePath ?: return null

    val file = if (isSpace(filePath)) null else File(filePath)
    return encryptMD5File(file)
}

/**
 * Return the hex string of file's MD5 encryption.
 *
 * @param file The file.
 * @return the hex string of file's MD5 encryption
 */
fun encryptMD5File2String(file: File?): String {
    return bytes2HexString(encryptMD5File(file))
}

/**
 * Return the bytes of file's MD5 encryption.
 *
 * @param file The file.
 * @return the bytes of file's MD5 encryption
 */
fun encryptMD5File(file: File?): ByteArray? {
    file ?: return null

    var fis: FileInputStream? = null
    val digestInputStream: DigestInputStream
    return try {
        fis = FileInputStream(file)
        var md = MessageDigest.getInstance("MD5")
        digestInputStream = DigestInputStream(fis, md)
        val buffer = ByteArray(256 * 1024)
        while (true) {
            if (digestInputStream.read(buffer) <= 0) break
        }
        md = digestInputStream.messageDigest
        md.digest()
    }
    catch (e: NoSuchAlgorithmException) {
        logE(TAG, t = e)
        null
    }
    catch (e: IOException) {
        logE(TAG, t = e)
        null
    }
    finally {
        try {
            fis?.close()
        }
        catch (e: IOException) {
            logE(TAG, t = e)
        }
    }
}

/** ********** SHA1 ********** */

/**
 * Return the hex string of SHA1 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA1 encryption
 */
fun encryptSHA1ToString(data: String?): String {
    data ?: return ""
    if (isEmpty(data)) return ""

    return encryptSHA1ToString(data.toByteArray())
}

/**
 * Return the hex string of SHA1 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA1 encryption
 */
fun encryptSHA1ToString(data: ByteArray?): String {
    return bytes2HexString(encryptSHA1(data))
}

/**
 * Return the bytes of SHA1 encryption.
 *
 * @param data The data.
 * @return the bytes of SHA1 encryption
 */
fun encryptSHA1(data: ByteArray?): ByteArray? {
    return hashTemplate(data, "SHA-1")
}

/** ********** SHA1 ********** */

/**
 * Return the hex string of SHA224 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA224 encryption
 */
fun encryptSHA224ToString(data: String?): String {
    data ?: return ""
    if (isEmpty(data)) return ""

    return encryptSHA224ToString(data.toByteArray())
}

/**
 * Return the hex string of SHA224 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA224 encryption
 */
fun encryptSHA224ToString(data: ByteArray?): String {
    return bytes2HexString(encryptSHA224(data))
}

/**
 * Return the bytes of SHA224 encryption.
 *
 * @param data The data.
 * @return the bytes of SHA224 encryption
 */
fun encryptSHA224(data: ByteArray?): ByteArray? {
    return hashTemplate(data, "SHA224")
}

/** ********** SHA256 ********** */

/**
 * Return the hex string of SHA256 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA256 encryption
 */
fun encryptSHA256ToString(data: String?): String {
    data ?: return ""
    if (isEmpty(data)) return ""

    return encryptSHA256ToString(data.toByteArray())
}

/**
 * Return the hex string of SHA256 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA256 encryption
 */
fun encryptSHA256ToString(data: ByteArray?): String {
    return bytes2HexString(encryptSHA256(data))
}

/**
 * Return the bytes of SHA256 encryption.
 *
 * @param data The data.
 * @return the bytes of SHA256 encryption
 */
fun encryptSHA256(data: ByteArray?): ByteArray? {
    return hashTemplate(data, "SHA-256")
}

/** ********** SHA384 ********** */

/**
 * Return the hex string of SHA384 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA384 encryption
 */
fun encryptSHA384ToString(data: String?): String {
    data ?: return ""
    if (isEmpty(data)) return ""

    return encryptSHA384ToString(data.toByteArray())
}

/**
 * Return the hex string of SHA384 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA384 encryption
 */
fun encryptSHA384ToString(data: ByteArray?): String {
    return bytes2HexString(encryptSHA384(data))
}

/**
 * Return the bytes of SHA384 encryption.
 *
 * @param data The data.
 * @return the bytes of SHA384 encryption
 */
fun encryptSHA384(data: ByteArray?): ByteArray? {
    return hashTemplate(data, "SHA-384")
}

/** ********** SHA512 ********** */

/**
 * Return the hex string of SHA512 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA512 encryption
 */
fun encryptSHA512ToString(data: String?): String {
    data ?: return ""
    if (isEmpty(data)) return ""

    return encryptSHA512ToString(data.toByteArray())
}

/**
 * Return the hex string of SHA512 encryption.
 *
 * @param data The data.
 * @return the hex string of SHA512 encryption
 */
fun encryptSHA512ToString(data: ByteArray?): String {
    return bytes2HexString(encryptSHA512(data))
}

/**
 * Return the bytes of SHA512 encryption.
 *
 * @param data The data.
 * @return the bytes of SHA512 encryption
 */
fun encryptSHA512(data: ByteArray?): ByteArray? {
    return hashTemplate(data, "SHA-512")
}

/** ******************** Hmac encryption ******************** */

/** ********** HmacMD5 ********** */

/**
 * Return the hex string of HmacMD5 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacMD5 encryption
 */
fun encryptHmacMD5ToString(data: String?, key: String?): String {
    data ?: return ""
    key ?: return ""
    if (isEmpty(data)) return ""
    if (isEmpty(key)) return ""

    return encryptHmacMD5ToString(data.toByteArray(), key.toByteArray())
}

/**
 * Return the hex string of HmacMD5 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacMD5 encryption
 */
fun encryptHmacMD5ToString(data: ByteArray?, key: ByteArray?): String {
    return bytes2HexString(encryptHmacMD5(data, key))
}

/**
 * Return the bytes of HmacMD5 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the bytes of HmacMD5 encryption
 */
fun encryptHmacMD5(data: ByteArray?, key: ByteArray?): ByteArray? {
    return hmacTemplate(data, key, "HmacMD5")
}

/** ********** HmacSHA1 ********** */

/**
 * Return the hex string of HmacSHA1 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA1 encryption
 */
fun encryptHmacSHA1ToString(data: String?, key: String?): String {
    data ?: return ""
    key ?: return ""
    if (isEmpty(data)) return ""
    if (isEmpty(key)) return ""

    return encryptHmacSHA1ToString(data.toByteArray(), key.toByteArray())
}

/**
 * Return the hex string of HmacSHA1 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA1 encryption
 */
fun encryptHmacSHA1ToString(data: ByteArray?, key: ByteArray?): String {
    return bytes2HexString(encryptHmacSHA1(data, key))
}

/**
 * Return the bytes of HmacSHA1 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the bytes of HmacSHA1 encryption
 */
fun encryptHmacSHA1(data: ByteArray?, key: ByteArray?): ByteArray? {
    return hmacTemplate(data, key, "HmacSHA1")
}

/** ********** HmacSHA224 ********** */

/**
 * Return the hex string of HmacSHA224 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA224 encryption
 */
fun encryptHmacSHA224ToString(data: String?, key: String?): String {
    data ?: return ""
    key ?: return ""
    if (isEmpty(data)) return ""
    if (isEmpty(key)) return ""

    return encryptHmacSHA224ToString(data.toByteArray(), key.toByteArray())
}

/**
 * Return the hex string of HmacSHA224 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA224 encryption
 */
fun encryptHmacSHA224ToString(data: ByteArray?, key: ByteArray?): String {
    return bytes2HexString(encryptHmacSHA224(data, key))
}

/**
 * Return the bytes of HmacSHA224 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the bytes of HmacSHA224 encryption
 */
fun encryptHmacSHA224(data: ByteArray?, key: ByteArray?): ByteArray? {
    return hmacTemplate(data, key, "HmacSHA224")
}

/** ********** HmacSHA256 ********** */

/**
 * Return the hex string of HmacSHA256 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA256 encryption
 */
fun encryptHmacSHA256ToString(data: String?, key: String?): String {
    data ?: return ""
    key ?: return ""
    if (isEmpty(data)) return ""
    if (isEmpty(key)) return ""

    return encryptHmacSHA256ToString(data.toByteArray(), key.toByteArray())
}

/**
 * Return the hex string of HmacSHA256 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA256 encryption
 */
fun encryptHmacSHA256ToString(data: ByteArray?, key: ByteArray?): String {
    return bytes2HexString(encryptHmacSHA256(data, key))
}

/**
 * Return the bytes of HmacSHA256 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the bytes of HmacSHA256 encryption
 */
fun encryptHmacSHA256(data: ByteArray?, key: ByteArray?): ByteArray? {
    return hmacTemplate(data, key, "HmacSHA256")
}

/** ********** HmacSHA384 ********** */

/**
 * Return the hex string of HmacSHA384 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA384 encryption
 */
fun encryptHmacSHA384ToString(data: String?, key: String?): String {
    data ?: return ""
    key ?: return ""
    if (isEmpty(data)) return ""
    if (isEmpty(key)) return ""

    return encryptHmacSHA384ToString(data.toByteArray(), key.toByteArray())
}

/**
 * Return the hex string of HmacSHA384 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA384 encryption
 */
fun encryptHmacSHA384ToString(data: ByteArray?, key: ByteArray?): String {
    return bytes2HexString(encryptHmacSHA384(data, key))
}

/**
 * Return the bytes of HmacSHA384 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the bytes of HmacSHA384 encryption
 */
fun encryptHmacSHA384(data: ByteArray?, key: ByteArray?): ByteArray? {
    return hmacTemplate(data, key, "HmacSHA384")
}

/** ********** HmacSHA512 ********** */

/**
 * Return the hex string of HmacSHA512 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA512 encryption
 */
fun encryptHmacSHA512ToString(data: String?, key: String?): String {
    data ?: return ""
    key ?: return ""
    if (isEmpty(data)) return ""
    if (isEmpty(key)) return ""

    return encryptHmacSHA512ToString(data.toByteArray(), key.toByteArray())
}

/**
 * Return the hex string of HmacSHA512 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the hex string of HmacSHA512 encryption
 */
fun encryptHmacSHA512ToString(data: ByteArray?, key: ByteArray?): String {
    return bytes2HexString(encryptHmacSHA512(data, key))
}

/**
 * Return the bytes of HmacSHA512 encryption.
 *
 * @param data The data.
 * @param key  The key.
 * @return the bytes of HmacSHA512 encryption
 */
fun encryptHmacSHA512(data: ByteArray?, key: ByteArray?): ByteArray? {
    return hmacTemplate(data, key, "HmacSHA512")
}

/** ******************** DES encryption ******************** */

/** ********** DES ********** */

/**
 * Return the Base64-encode bytes of DES encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the Base64-encode bytes of DES encryption
 */
fun encryptDES2Base64(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return base64Encode(encryptDES(data, key, transformation, iv))
}

/**
 * Return the hex string of DES encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the hex string of DES encryption
 */
fun encryptDES2HexString(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): String {
    return bytes2HexString(encryptDES(data, key, transformation, iv))
}

/**
 * Return the bytes of DES encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of DES encryption
 */
fun encryptDES(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return symmetricTemplate(data, key, "DES", transformation, iv, true)
}

/**
 * Return the bytes of DES decryption for Base64-encode bytes.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of DES decryption for Base64-encode bytes
 */
fun decryptBase64DES(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return decryptDES(base64Decode(data), key, transformation, iv)
}

/**
 * Return the bytes of DES decryption for hex string.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of DES decryption for hex string
 */
fun decryptHexStringDES(data: String?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return decryptDES(hexString2Bytes(data), key, transformation, iv)
}

/**
 * Return the bytes of DES decryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of DES decryption
 */
fun decryptDES(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return symmetricTemplate(data, key, "DES", transformation, iv, false)
}

/** ******************** 3DES encryption ******************** */

/** ********** 3DES ********** */

/**
 * Return the Base64-encode bytes of 3DES encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the Base64-encode bytes of 3DES encryption
 */
fun encrypt3DES2Base64(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return base64Encode(encrypt3DES(data, key, transformation, iv))
}

/**
 * Return the hex string of 3DES encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the hex string of 3DES encryption
 */
fun encrypt3DES2HexString(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): String? {
    return bytes2HexString(encrypt3DES(data, key, transformation, iv))
}

/**
 * Return the bytes of 3DES encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of 3DES encryption
 */
fun encrypt3DES(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return symmetricTemplate(data, key, "DESede", transformation, iv, true)
}

/**
 * Return the bytes of 3DES decryption for Base64-encode bytes.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of 3DES decryption for Base64-encode bytes
 */
fun decryptBase64_3DES(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return decrypt3DES(base64Decode(data), key, transformation, iv)
}

/**
 * Return the bytes of 3DES decryption for hex string.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of 3DES decryption for hex string
 */
fun decryptHexString3DES(data: String?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return decrypt3DES(hexString2Bytes(data), key, transformation, iv)
}

/**
 * Return the bytes of 3DES decryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of 3DES decryption
 */
fun decrypt3DES(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return symmetricTemplate(data, key, "DESede", transformation, iv, false)
}

/** ******************** AES encryption ******************** */

/** ********** AES ********** */

/**
 * Return the Base64-encode bytes of AES encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the Base64-encode bytes of AES encryption
 */
fun encryptAES2Base64(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return base64Encode(encryptAES(data, key, transformation, iv))
}

/**
 * Return the hex string of AES encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the hex string of AES encryption
 */
fun encryptAES2HexString(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): String? {
    return bytes2HexString(encryptAES(data, key, transformation, iv))
}

/**
 * Return the bytes of AES encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of AES encryption
 */
fun encryptAES(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return symmetricTemplate(data, key, "AES", transformation, iv, true)
}

/**
 * Return the bytes of AES decryption for Base64-encode bytes.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of AES decryption for Base64-encode bytes
 */
fun decryptBase64AES(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return decryptAES(base64Decode(data), key, transformation, iv)
}

/**
 * Return the bytes of AES decryption for hex string.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of AES decryption for hex string
 */
fun decryptHexStringAES(data: String?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return decryptAES(hexString2Bytes(data), key, transformation, iv)
}

/**
 * Return the bytes of AES decryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param iv             The buffer with the IV. The contents of the
 * buffer are copied to protect against subsequent modification.
 * @return the bytes of AES decryption
 */
fun decryptAES(data: ByteArray?, key: ByteArray?, transformation: String?, iv: ByteArray?): ByteArray? {
    return symmetricTemplate(data, key, "AES", transformation, iv, false)
}

/** ******************** RSA encryption ******************** */

/** ********** RSA ********** */

/**
 * Return the Base64-encode bytes of RSA encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param isPublicKey    True to use public key, false to use private key.
 * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
 * @return the Base64-encode bytes of RSA encryption
 */
fun encryptRSA2Base64(data: ByteArray?, key: ByteArray?, isPublicKey: Boolean, transformation: String?): ByteArray? {
    return base64Encode(encryptRSA(data, key, isPublicKey, transformation))
}

/**
 * Return the hex string of RSA encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param isPublicKey    True to use public key, false to use private key.
 * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
 * @return the hex string of RSA encryption
 */
fun encryptRSA2HexString(data: ByteArray?, key: ByteArray?, isPublicKey: Boolean, transformation: String?): String? {
    return bytes2HexString(encryptRSA(data, key, isPublicKey, transformation))
}

/**
 * Return the bytes of RSA encryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param isPublicKey    True to use public key, false to use private key.
 * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
 * @return the bytes of RSA encryption
 */
fun encryptRSA(data: ByteArray?, key: ByteArray?, isPublicKey: Boolean, transformation: String?): ByteArray? {
    return rsaTemplate(data, key, isPublicKey, transformation, true)
}

/**
 * Return the bytes of RSA decryption for Base64-encode bytes.
 *
 * @param data           The data.
 * @param key            The key.
 * @param isPublicKey    True to use public key, false to use private key.
 * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
 * @return the bytes of RSA decryption for Base64-encode bytes
 */
fun decryptBase64RSA(data: ByteArray?, key: ByteArray?, isPublicKey: Boolean, transformation: String?): ByteArray? {
    return decryptRSA(base64Decode(data), key, isPublicKey, transformation)
}

/**
 * Return the bytes of RSA decryption for hex string.
 *
 * @param data           The data.
 * @param key            The key.
 * @param isPublicKey    True to use public key, false to use private key.
 * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
 * @return the bytes of RSA decryption for hex string
 */
fun decryptHexStringRSA(data: String?, key: ByteArray?, isPublicKey: Boolean, transformation: String?): ByteArray? {
    return decryptRSA(hexString2Bytes(data), key, isPublicKey, transformation)
}

/**
 * Return the bytes of RSA decryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param isPublicKey    True to use public key, false to use private key.
 * @param transformation The name of the transformation, e.g., *RSA/CBC/PKCS1Padding*.
 * @return the bytes of RSA decryption
 */
fun decryptRSA(data: ByteArray?, key: ByteArray?, isPublicKey: Boolean, transformation: String?): ByteArray? {
    return rsaTemplate(data, key, isPublicKey, transformation, false)
}


/** ****************************** Private ****************************** */

/** ********** Template ********** */

/**
 * Return the bytes of hash encryption.
 *
 * @param data      The data.
 * @param algorithm The name of hash encryption.
 * @return the bytes of hash encryption
 */
fun hashTemplate(data: ByteArray?, algorithm: String): ByteArray? {
    data ?: return null
    if (isEmptyOrNull(data)) return null

    return try {
        val messageDigest = MessageDigest.getInstance(algorithm)
        messageDigest.update(data)
        messageDigest.digest()
    }
    catch (e: NoSuchAlgorithmException) {
        logE(TAG, t = e)
        null
    }
}

/**
 * Return the bytes of hmac encryption.
 *
 * @param data      The data.
 * @param key       The key.
 * @param algorithm The name of hmac encryption.
 * @return the bytes of hmac encryption
 */
private fun hmacTemplate(data: ByteArray?, key: ByteArray?, algorithm: String): ByteArray? {
    data ?: return null
    key ?: return null
    if (isEmptyOrNull(data)) return null
    if (isEmptyOrNull(key)) return null

    return try {
        val secretKey = SecretKeySpec(key, algorithm)
        val mac = Mac.getInstance(algorithm)
        mac.init(secretKey)
        mac.doFinal(data)
    }
    catch (e: InvalidKeyException) {
        logE(TAG, t = e)
        null
    }
    catch (e: NoSuchAlgorithmException) {
        logE(TAG, t = e)
        null
    }
}

/**
 * Return the bytes of symmetric encryption or decryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param algorithm      The name of algorithm.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS5Padding*.
 * @param isEncrypt      True to encrypt, false otherwise.
 * @return the bytes of symmetric encryption or decryption
 */
private fun symmetricTemplate(data: ByteArray?, key: ByteArray?, algorithm: String, transformation: String?, iv: ByteArray?, isEncrypt: Boolean): ByteArray? {
    data ?: return null
    key ?: return null
    if (isEmptyOrNull(data)) return null
    if (isEmptyOrNull(key)) return null

    return try {
        val secretKey: SecretKey
        secretKey = if ("DES" == algorithm) {
            val desKey = DESKeySpec(key)
            val keyFactory = SecretKeyFactory.getInstance(algorithm)
            keyFactory.generateSecret(desKey)
        }
        else {
            SecretKeySpec(key, algorithm)
        }
        val cipher = Cipher.getInstance(transformation)
        if (iv == null || iv.isEmpty()) {
            cipher.init(if (isEncrypt) Cipher.ENCRYPT_MODE else Cipher.DECRYPT_MODE, secretKey)
        }
        else {
            val params: AlgorithmParameterSpec = IvParameterSpec(iv)
            cipher.init(if (isEncrypt) Cipher.ENCRYPT_MODE else Cipher.DECRYPT_MODE, secretKey, params)
        }
        cipher.doFinal(data)
    }
    catch (e: Throwable) {
        logE(TAG, t = e)
        null
    }
}

/**
 * Return the bytes of RSA encryption or decryption.
 *
 * @param data           The data.
 * @param key            The key.
 * @param isPublicKey    True to use public key, false to use private key.
 * @param transformation The name of the transformation, e.g., *DES/CBC/PKCS1Padding*.
 * @param isEncrypt      True to encrypt, false otherwise.
 * @return the bytes of RSA encryption or decryption
 */
private fun rsaTemplate(data: ByteArray?, key: ByteArray?, isPublicKey: Boolean, transformation: String?, isEncrypt: Boolean): ByteArray? {
    data ?: return null
    key ?: return null
    if (isEmptyOrNull(data)) return null
    if (isEmptyOrNull(key)) return null

    try {
        val rsaKey: Key?
        rsaKey = if (isPublicKey) {
            val keySpec = X509EncodedKeySpec(key)
            KeyFactory.getInstance("RSA").generatePublic(keySpec)
        }
        else {
            val keySpec = PKCS8EncodedKeySpec(key)
            KeyFactory.getInstance("RSA").generatePrivate(keySpec)
        }
        if (rsaKey == null) return null
        val cipher = Cipher.getInstance(transformation)
        cipher.init(if (isEncrypt) Cipher.ENCRYPT_MODE else Cipher.DECRYPT_MODE, rsaKey)
        val len = data.size
        val maxLen = if (isEncrypt) 117 else 128
        val count = len / maxLen
        return if (count > 0) {
            var ret: ByteArray? = ByteArray(0)
            var buff = ByteArray(maxLen)
            var index = 0
            for (i in 0 until count) {
                System.arraycopy(data, index, buff, 0, maxLen)
                ret = joins(ret, cipher.doFinal(buff))
                index += maxLen
            }
            if (index != len) {
                val restLen = len - index
                buff = ByteArray(restLen)
                System.arraycopy(data, index, buff, 0, restLen)
                ret = joins(ret, cipher.doFinal(buff))
            }
            ret
        }
        else {
            cipher.doFinal(data)
        }
    }
    catch (e: NoSuchAlgorithmException) {
        logE(TAG, t = e)
    }
    catch (e: NoSuchPaddingException) {
        logE(TAG, t = e)
    }
    catch (e: InvalidKeyException) {
        logE(TAG, t = e)
    }
    catch (e: BadPaddingException) {
        logE(TAG, t = e)
    }
    catch (e: IllegalBlockSizeException) {
        logE(TAG, t = e)
    }
    catch (e: InvalidKeySpecException) {
        logE(TAG, t = e)
    }
    return null
}


/** ********** joins ********** */

private fun joins(prefix: ByteArray?, suffix: ByteArray?): ByteArray? {
    prefix ?: return null
    suffix ?: return null

    val ret = ByteArray(prefix.size + suffix.size)
    System.arraycopy(prefix, 0, ret, 0, prefix.size)
    System.arraycopy(suffix, 0, ret, prefix.size, suffix.size)
    return ret
}
