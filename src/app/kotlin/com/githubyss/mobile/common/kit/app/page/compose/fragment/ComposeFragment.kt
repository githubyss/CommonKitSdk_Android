package com.githubyss.mobile.common.kit.app.page.compose.fragment

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.element.InfoDisplay
import com.githubyss.mobile.common.kit.app.element.PageContent
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeFragment
import com.githubyss.mobile.common.kit.util.getStringFromRes


/**
 * ComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/22 15:26:25
 */
class ComposeFragment : BaseComposeFragment() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = ComposeFragment::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    @Preview
    @Composable
    override fun Content() {
        PageContent {
            InfoDisplay(title = getStringFromRes(R.string.comkit_compose))
        }
    }
}
