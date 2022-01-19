package com.githubyss.mobile.common.kit.base.view_binding.layout.inline

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding


/**
 * BaseInlineBindingFrameLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/01/04 14:57:34
 */
abstract class BaseInlineBindingFrameLayout<B : ViewBinding> : FrameLayout {

    /** ****************************** Constructors ****************************** */

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}
