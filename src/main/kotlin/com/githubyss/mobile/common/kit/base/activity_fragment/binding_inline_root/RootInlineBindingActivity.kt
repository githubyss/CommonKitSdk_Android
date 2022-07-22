package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root

import androidx.databinding.ViewDataBinding
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity


/**
 * RootInlineBindingActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/07/22 13:32:12
 */
abstract class RootInlineBindingActivity<B : ViewDataBinding>() : BaseActivity() {
    lateinit var binding: B
}
