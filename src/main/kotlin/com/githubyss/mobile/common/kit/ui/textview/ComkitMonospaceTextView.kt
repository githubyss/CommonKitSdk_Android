package com.githubyss.mobile.common.kit.ui.textview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * ComkitMonospaceTextView.kt
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 */
class ComkitMonospaceTextView : TextView {
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        typeface = Typeface.createFromAsset(context.assets, "font/source_code_pro_regular.ttf")
    }
}
