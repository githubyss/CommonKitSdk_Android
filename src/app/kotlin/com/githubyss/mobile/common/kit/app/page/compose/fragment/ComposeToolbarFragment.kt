package com.githubyss.mobile.common.kit.app.page.compose.fragment

import androidx.compose.runtime.Composable
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.compose.element.ComposeDisplay
import com.githubyss.mobile.common.kit.app.page.compose.element.ComposePageContent
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.kit.util.getStringFromRes


/**
 * ComposeToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/24 16:04:32
 */
class ComposeToolbarFragment : BaseComposeToolbarFragment() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = ComposeToolbarFragment::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        Toolbar(titleResId = R.string.comkit_compose_toolbar_title)
    }

    @Composable
    override fun Content() {
        ComposePageContent {
            InfoDisplay()
        }
    }

    @Composable
    private fun InfoDisplay() {
        ComposeDisplay(title = getStringFromRes(R.string.comkit_compose_toolbar))
    }
}
