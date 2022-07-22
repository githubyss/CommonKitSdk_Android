package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root.RootInlineBindingFragment


/**
 * BaseInlineBindingFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 16:50:43
 */
abstract class BaseInlineBindingFragment<B : ViewDataBinding>(@LayoutRes layoutId: Int) : RootInlineBindingFragment<B>(layoutId)
