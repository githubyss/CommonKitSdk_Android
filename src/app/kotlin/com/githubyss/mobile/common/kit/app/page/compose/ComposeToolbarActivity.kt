package com.githubyss.mobile.common.kit.app.page.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.compose.element.CommonButtonBlue
import com.githubyss.mobile.common.kit.app.page.compose.element.ComposeDisplay
import com.githubyss.mobile.common.kit.app.page.compose.element.ComposePageContent
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarActivity
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.kit.util.showToast


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

    private val TITLE = getStringFromRes(R.string.comkit_compose_toolbar_title)

    private var title: String by mutableStateOf(TITLE)
    // private var title: MutableState<String> = mutableStateOf(TITLE)


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        Toolbar(title)
    }

    @Preview
    @Composable
    override fun Content() {
        ComposePageContent {
            InfoDisplay()
            ChangeTitleButton()
            CommonButtonBlue(text = "蓝色按钮蓝色按钮蓝色按钮蓝色按钮蓝色按钮蓝色按钮蓝色按钮蓝色按钮蓝色按钮蓝色按钮") {
                showToast("蓝色按钮")
            }
        }
    }

    @Composable
    private fun InfoDisplay() {
        ComposeDisplay(title = getStringFromRes(R.string.comkit_compose_toolbar))
    }

    @Composable
    private fun ChangeTitleButton() {
        CommonButtonBlue(text = "Change Title", enabled = false) {
            runOnUiThread { title = "$TITLE New" }
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
