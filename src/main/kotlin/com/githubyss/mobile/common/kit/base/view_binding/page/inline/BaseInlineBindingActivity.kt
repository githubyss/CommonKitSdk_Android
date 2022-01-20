package com.githubyss.mobile.common.kit.base.view_binding.page.inline

import com.githubyss.mobile.common.kit.base.view_binding.page.base.BaseActivity
import com.githubyss.mobile.common.res.databinding.ComresActivityBaseBinding


/**
 * BaseInlineBindingActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/07/20 16:50:32
 */
abstract class BaseInlineBindingActivity : BaseActivity(0) {

    /** ****************************** Properties ****************************** */

    val binding by inflate<ComresActivityBaseBinding>()
}
