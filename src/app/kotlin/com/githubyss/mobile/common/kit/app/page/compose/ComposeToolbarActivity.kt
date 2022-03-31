package com.githubyss.mobile.common.kit.app.page.compose

import androidx.compose.runtime.Composable
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.element.InfoDisplay
import com.githubyss.mobile.common.kit.app.element.PageContent
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarActivity
import com.githubyss.mobile.common.kit.util.getStringFromRes


/**
 * ComposeToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/24 16:03:36
 */
class ComposeToolbarActivity : BaseComposeToolbarActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = ComposeToolbarActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        Toolbar(R.string.comkit_compose_toolbar_title)
    }

    @Composable
    override fun Content() {
        PageContent {
            InfoDisplay(title = getStringFromRes(R.string.comkit_compose_toolbar))
        }
    }
}
