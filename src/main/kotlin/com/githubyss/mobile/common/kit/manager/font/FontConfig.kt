package com.githubyss.mobile.common.kit.manager.font

import android.graphics.Typeface


/**
 * ComkitFontConfig
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */

object FontConfig {
    
    object FontPath {
        val SOURCE_CODE_PRO_REGULAR = "font/source_code_pro_regular.ttf"
        val DIN_NEXT_LT_PRO_MEDIUM = "font/din_next_lt_pro_medium.ttf"
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
