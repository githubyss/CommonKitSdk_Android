package com.githubyss.mobile.common.kit.base.view_binding.layout.reflect

import android.content.Context
import android.util.AttributeSet
import androidx.viewbinding.ViewBinding


/**
 * BaseReflectBindingFrameLayout
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/01/04 13:34:00
 */
abstract class BaseReflectBindingFrameLayout<B : ViewBinding> : RootReflectBindingFrameLayout<B> {

    /** ****************************** Constructors ****************************** */

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}
