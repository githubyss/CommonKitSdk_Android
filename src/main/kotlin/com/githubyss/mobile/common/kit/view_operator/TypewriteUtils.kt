package com.githubyss.mobile.common.kit.view_operator

import android.widget.TextView
import com.githubyss.mobile.common.kit.util.LogUtils
import kotlin.concurrent.thread


/**
 * TypewriteUtils
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/05 10:16:32
 */
object TypewriteUtils {
    
    /** ****************************** Properties ****************************** */
    
    private val TAG: String = TypewriteUtils::class.java.simpleName
    private val DELAY_MILLIS_LONG = 500L
    
    
    /** ****************************** Functions ****************************** */
    
    fun textViewTypewriteBySetting(textView: TextView?, string: String?, delayMillis: Long = DELAY_MILLIS_LONG, startIdx: Int = 0, canAutoScrollBottom: Boolean = true) {
        textView ?: return
        string ?: return
        
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
                    LogUtils.e(t = exception)
                }
            }
        }
    }
    
    fun textViewTypewriteByAppending(textView: TextView?, string: String?, delayMillis: Long = DELAY_MILLIS_LONG, startIdx: Int = 0, canAutoScrollBottom: Boolean = true) {
        textView ?: return
        string ?: return
        
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
                    LogUtils.e(t = exception)
                }
            }
        }
    }
    
    fun textViewAutoScrollBottom(textView: TextView?) {
        textView ?: return
        
        val offsetHeight = textView.lineCount * textView.lineHeight
        val actualHeight = textView.height - (textView.paddingTop + textView.paddingBottom)
        if (offsetHeight > actualHeight) {
            textView.scrollTo(0, offsetHeight - actualHeight)
        }
    }
}
