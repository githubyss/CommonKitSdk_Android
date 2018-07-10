package com.githubyss.mobile.common.kit.font

import android.graphics.Typeface

/**
 * ComkitFontConfig
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
object ComkitFontConfig {
    object FontPath {
        val SOURCE_CODE_PRO_REGULAR = "font/source_code_pro_regular.ttf"

        val MONOSPACE_DEFAULT = SOURCE_CODE_PRO_REGULAR
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
