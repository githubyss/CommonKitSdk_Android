package com.githubyss.mobile.common.kit.processor

import android.text.TextUtils
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * ComkitMoneyProcessor
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitMoneyProcessor {
    
    fun yuan2Fen(yuan: String, pattern: String = "#0"): String
            = if (TextUtils.isEmpty(yuan))
        "" else DecimalFormat(pattern).format(BigDecimal(yuan).multiply(BigDecimal.valueOf(100)))


    fun fen2Yuan(fen: String, pattern: String = "#0.00"): String
            = if (TextUtils.isEmpty(fen))
        "" else DecimalFormat(pattern).format(BigDecimal(fen).divide(BigDecimal.valueOf(100)))
}
