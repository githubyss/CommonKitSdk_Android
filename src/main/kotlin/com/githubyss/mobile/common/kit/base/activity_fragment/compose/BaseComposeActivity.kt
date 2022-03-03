package com.githubyss.mobile.common.kit.base.activity_fragment.compose

import androidx.compose.runtime.Composable


/**
 * BaseComposeActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/24 15:55:45
 */
abstract class BaseComposeActivity : RootComposeActivity(){

    /** ****************************** Override ****************************** */

    @Composable
    override fun Page() {
        Content()
    }
}
