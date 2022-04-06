package com.githubyss.mobile.common.kit.app.page.state_compose

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarActivity
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.res.button_click.compose.ButtonClickBlueMargin
import com.githubyss.mobile.common.res.common.dimen.SideNormal
import com.githubyss.mobile.common.res.page.compose.PageSidePadding


/**
 * StateComposeActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/03/30 16:41:43
 */
class StateComposeActivity : BaseComposeToolbarActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = StateComposeActivity::class.java.simpleName
    }

    private val titleDefault = getStringFromRes(R.string.comkit_compose_toolbar_title)

    private var title: String by mutableStateOf(titleDefault)
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
        PageSidePadding(
            paddingVertical = Dp.SideNormal,
        ) {
            ChangeTitleButton()
            CounterButtonByCountOutside()
            CounterButtonByCountInside()
        }
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun ChangeTitleButton() {
        ButtonClickBlueMargin(
            text = "Change Title",
            isFillMaxWidth = true,
        ) {
            title = "$titleDefault $count"
        }
    }

    @Composable
    private fun CounterButtonByCountOutside() {
        ButtonClickBlueMargin(
            text = "Counter Outside : $count",
            isFillMaxWidth = true,
        ) {
            count++
        }
    }

    @Composable
    private fun CounterButtonByCountInside() {
        var count: Int by remember { mutableStateOf(0) }
        // val count: MutableState<Int> = remember { mutableStateOf(0) }

        ButtonClickBlueMargin(
            text = "Counter Inside : $count",
            isFillMaxWidth = true,
        ) {
            count++
            // count.value++
        }
    }
}