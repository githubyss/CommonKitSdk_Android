package com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline

import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.binding_inline_root.inflate
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity
import com.githubyss.mobile.common.kit.databinding.ComkitActivityBaseBinding


/**
 * BaseInlineBindingActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 16:50:32
 */
abstract class BaseInlineBindingActivity : BaseActivity(R.layout.comkit_activity_base) {

    /** ****************************** Properties ****************************** */

    val binding by inflate<ComkitActivityBaseBinding>()
}
