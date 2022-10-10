package com.githubyss.common.kit.util

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.net.URLDecoder
import java.net.URLEncoder


/**
 * EncodeUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/16 15:29:21
 */

/** ****************************** Properties ****************************** */

/**  */
private const val TAG = "EncodeUtils"


/** ****************************** Functions ****************************** */


/**
 * Return the urlencoded string.
 *
 * @param input       The input.
 * @param charsetName The name of charset.
 * @return the urlencoded string
 */
@JvmOverloads
fun urlEncode(input: String?, charsetName: String? = "UTF-8"): String {
    if (isEmpty(input)) return ""

    return try {
        URLEncoder.encode(input, charsetName)
    }
    catch (e: UnsupportedEncodingException) {
        throw AssertionError(e)
    }
}

/**
 * Return the string of decode urlencoded string.
 *
 * @param input       The input.
 * @param charsetName The name of charset.
 * @return the string of decode urlencoded string
 */
@JvmOverloads
fun urlDecode(input: String?, charsetName: String? = "UTF-8"): String {
    if (isEmpty(input)) return ""

    return try {
        URLDecoder.decode(input, charsetName)
    }
    catch (e: UnsupportedEncodingException) {
        throw AssertionError(e)
    }
}

/**
 * Return Base64-encode bytes.
 *
 * @param input The input.
 * @return Base64-encode bytes
 */
fun base64Encode(input: String?): ByteArray {
    return base64Encode(input?.toByteArray())
}

/**
 * Return Base64-encode bytes.
 *
 * @param input The input.
 * @return Base64-encode bytes
 */
fun base64Encode(input: ByteArray?): ByteArray {
    if (isEmptyOrNull(input)) return ByteArray(0)
    return Base64.encode(input, Base64.NO_WRAP)
}

/**
 * Return Base64-encode string.
 *
 * @param input The input.
 * @return Base64-encode string
 */
fun base64Encode2String(input: ByteArray?): String {
    if (isEmptyOrNull(input)) return ""
    return Base64.encodeToString(input, Base64.NO_WRAP)
}

/**
 * Return the bytes of decode Base64-encode string.
 *
 * @param input The input.
 * @return the string of decode Base64-encode string
 */
fun base64Decode(input: String?): ByteArray {
    return base64Decode(input?.toByteArray())
}

/**
 * Return the bytes of decode Base64-encode bytes.
 *
 * @param input The input.
 * @return the bytes of decode Base64-encode bytes
 */
fun base64Decode(input: ByteArray?): ByteArray {
    if (isEmptyOrNull(input)) return ByteArray(0)
    return Base64.decode(input, Base64.NO_WRAP)
}

/**
 * Return html-encode string.
 *
 * @param input The input.
 * @return html-encode string
 */
fun htmlEncode(input: CharSequence?): String {
    input ?: return ""
    if (isEmptyNonNull(input)) return ""

    val sb = StringBuilder()
    var c: Char
    var i = 0
    val len = input.length
    while (i < len) {
        c = input[i]
        when (c) {
            '<' -> sb.append("&lt;") //$NON-NLS-1$
            '>' -> sb.append("&gt;") //$NON-NLS-1$
            '&' -> sb.append("&amp;") //$NON-NLS-1$
            '\'' ->                     //http://www.w3.org/TR/xhtml1
                // The named character reference &apos; (the apostrophe, U+0027) was
                // introduced in XML 1.0 but does not appear in HTML. Authors should
                // therefore use &#39; instead of &apos; to work as expected in HTML 4
                // user agents.
                sb.append("&#39;") //$NON-NLS-1$
            '"' -> sb.append("&quot;") //$NON-NLS-1$
            else -> sb.append(c)
        }
        i++
    }
    return sb.toString()
}
