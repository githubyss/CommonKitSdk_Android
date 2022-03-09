package com.githubyss.mobile.common.kit.app.page.compose

import androidx.compose.runtime.Composable
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.compose.element.ComposeDisplay
import com.githubyss.mobile.common.kit.app.page.compose.element.ComposePageContent
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeActivity
import com.githubyss.mobile.common.kit.util.getStringFromRes


/**
 * ComposeActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/23 15:49:58
 */
class ComposeActivity : BaseComposeActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = ComposeActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    @Composable
    override fun Content() {
        ComposePageContent {
            ComposeDisplay(title = getStringFromRes(R.string.comkit_compose))
        }
    }
}
