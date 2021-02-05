package com.githubyss.mobile.common.kit.viewoperator

import android.widget.TextView
import com.githubyss.mobile.common.kit.util.LogcatUtils
import kotlin.concurrent.thread


/**
 * TypewriteUtils
 *
 * @author Ace Yan
 * @github githubyss
 */
object TypewriteUtils {
    fun textViewTypewriteBySetting(textView: TextView, string: String, delayMillis: Long, startIdx: Int, canAutoScrollBottom: Boolean) {
        textView.text = ""
        thread {
            kotlin.run {
                try {
                    for (idx in startIdx..string.length) {
                        val str = string.substring(0, idx)
                        textView.post {
                            textView.text = str
                            if (canAutoScrollBottom) {
                                textViewAutoScrollBottom(textView)
                            }
                        }
                        Thread.sleep(delayMillis)
                    }
                } catch (exception: InterruptedException) {
                    LogcatUtils.e(t = exception)
                }
            }
        }
    }
    
    fun textViewTypewriteByAppending(textView: TextView, string: String, delayMillis: Long, startIdx: Int, canAutoScrollBottom: Boolean) {
        val stringBuilder = StringBuilder(string)
        
        textView.text = ""
        thread {
            kotlin.run {
                try {
                    for (idx in startIdx until string.length) {
                        val char = stringBuilder[idx]
                        textView.post {
                            textView.append(char.toString())
                            if (canAutoScrollBottom) {
                                textViewAutoScrollBottom(textView)
                            }
                        }
                        Thread.sleep(delayMillis)
                    }
                } catch (exception: InterruptedException) {
                    LogcatUtils.e(t = exception)
                }
            }
        }
    }
    
    fun textViewAutoScrollBottom(textView: TextView) {
        val offsetHeight = textView.lineCount * textView.lineHeight
        val actualHeight = textView.height - (textView.paddingTop + textView.paddingBottom)
        if (offsetHeight > actualHeight) {
            textView.scrollTo(0, offsetHeight - actualHeight)
        }
    }
}
