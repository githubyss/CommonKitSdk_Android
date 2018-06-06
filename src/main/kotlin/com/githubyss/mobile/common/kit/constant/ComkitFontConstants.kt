package com.githubyss.mobile.common.kit.constant

import android.graphics.Typeface

/**
 * ComkitFontConstants.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitFontConstants {
    object FontPath {
        val SOURCE_CODE_PRO_REGULAR = "font/source_code_pro_regular.ttf"

        val MONOSPACE_DEFAULT = ComkitFontConstants.FontPath.SOURCE_CODE_PRO_REGULAR
    }

    object FontTypeface {
        val SANS_SERIF = Typeface.SANS_SERIF
        val SERIF = Typeface.SERIF
        val MONOSPACE = Typeface.MONOSPACE
    }

    object FontStyle {
        val NORMAL = Typeface.NORMAL
        val BOLD = Typeface.BOLD
        val ITALIC = Typeface.ITALIC
        val BOLD_ITALIC = Typeface.BOLD_ITALIC
    }
}
