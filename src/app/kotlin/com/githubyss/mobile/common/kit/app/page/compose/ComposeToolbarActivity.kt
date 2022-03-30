package com.githubyss.mobile.common.kit.app.page.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    private var title: String by mutableStateOf(getStringFromRes(R.string.comkit_compose_toolbar_title))


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        Toolbar(title)
    }

    @Composable
    override fun Content() {
        PageContent {
            InfoDisplay(title = getStringFromRes(R.string.comkit_compose_toolbar))
        }
    }

    // @Composable
    // private fun ChangeTitleButtonByViewModel() {
    //     Button(
    //         onClick = { runOnUiThread { composeVm.changeTitle("$TITLE New") } }
    //     ) {
    //         Text(text = "Change Title", fontSize = 18.sp)
    //     }
    // }
}
