package com.githubyss.mobile.common.kit.app.page.compose

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarActivity
import com.githubyss.mobile.common.kit.util.ResourceUtils


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

    private val TITLE = ResourceUtils.getString(R.string.comkit_compose_toolbar_title)
    private var count: MutableState<Int> = mutableStateOf(0)
    private var title: MutableState<String> = mutableStateOf(TITLE)


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        Toolbar(title.value)
    }

    @Composable
    override fun Content() {
        ComposePageContent {
            InfoDisplay()
            ChangeTitleButton()
            CounterButtonByCountOutside()
            CounterButtonByCountInside()
        }
    }

    @Composable
    private fun InfoDisplay() {
        ComposeDisplay(title = ResourceUtils.getString(R.string.comkit_compose_toolbar))
    }

    @Composable
    private fun ChangeTitleButton() {
        Button(
            onClick = { runOnUiThread { title.value = "$TITLE ${count.value}" } }
        ) {
            Text(text = "Change Title", fontSize = 18.sp)
        }
    }

    @Composable
    private fun CounterButtonByCountOutside() {
        Button(
            onClick = { runOnUiThread { count.value++ } }
        ) {
            Text(text = "Counter Outside : ${count.value}", fontSize = 18.sp)
        }
    }

    @Composable
    private fun CounterButtonByCountInside() {
        var count by remember { mutableStateOf(0) }
        Button(
            onClick = { runOnUiThread { count++ } }
        ) {
            Text(text = "Counter Inside : $count", fontSize = 18.sp)
        }
    }
}
