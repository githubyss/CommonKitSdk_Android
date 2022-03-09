package com.githubyss.mobile.common.kit.app.page.mvvm_compose

import androidx.activity.viewModels
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.compose.element.ComposeDisplay
import com.githubyss.mobile.common.kit.app.page.compose.element.ComposePageContent
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarActivity
import com.githubyss.mobile.common.kit.util.getStringFromRes


/**
 * MvvmComposeActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/08 17:46:28
 */
class MvvmComposeActivity : BaseComposeToolbarActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = MvvmComposeActivity::class.java.simpleName
    }

    private val composeVm: MvvmComposeViewModelByState by viewModels()

    private val TITLE = getStringFromRes(R.string.comkit_compose_toolbar_title)

    private var title: String by mutableStateOf(TITLE)
    // private var title: MutableState<String> = mutableStateOf(TITLE)

    private var count: Int by mutableStateOf(0)
    // private var count: MutableState<Int> = mutableStateOf(0)


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        Toolbar(title)
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
        ComposeDisplay(title = getStringFromRes(R.string.comkit_compose_toolbar))
    }

    @Composable
    private fun ChangeTitleButton() {
        Button(
            onClick = { runOnUiThread { title = "$TITLE New" } }
        ) {
            Text(text = "Change Title", fontSize = 18.sp)
        }
    }

    @Composable
    private fun ChangeTitleButtonByViewModel() {
        Button(
            onClick = { runOnUiThread { composeVm.changeTitle("$TITLE New") } }
        ) {
            Text(text = "Change Title", fontSize = 18.sp)
        }
    }

    @Composable
    private fun CounterButtonByCountOutside() {
        Button(
            onClick = { runOnUiThread { count++ } }
        ) {
            Text(text = "Counter Outside : $count", fontSize = 18.sp)
        }
    }

    @Composable
    private fun CounterButtonByCountInside() {
        var count: Int by remember { mutableStateOf(0) }
        // val count: MutableState<Int> = remember { mutableStateOf(0) }

        Button(
            onClick = {
                runOnUiThread {
                    count++
                    // count.value++
                }
            }
        ) {
            Text(text = "Counter Inside : $count", fontSize = 18.sp)
        }
    }

    @Composable
    private fun CounterButtonByCountViewModel() {
        Button(
            onClick = { runOnUiThread { composeVm.plus() } }
        ) {
            Text(text = "Counter Outside : ${composeVm.count}", fontSize = 18.sp)
        }
    }
}
