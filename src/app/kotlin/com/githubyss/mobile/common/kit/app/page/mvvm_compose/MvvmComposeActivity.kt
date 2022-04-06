package com.githubyss.mobile.common.kit.app.page.mvvm_compose

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.page.mvvm_compose.view_model.MvvmComposeViewModelByState
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarActivity
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.res.button_click.compose.ButtonClickBlueMargin
import com.githubyss.mobile.common.res.common.dimen.SideNormal
import com.githubyss.mobile.common.res.page.compose.PageSidePadding


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

    private val titleDefault = getStringFromRes(R.string.comkit_compose_toolbar_title)


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        Toolbar(composeVm.title)
    }

    @Composable
    override fun Content() {
        PageSidePadding(
            paddingVertical = Dp.SideNormal,
        ) {
            ChangeTitleButton()
            CounterButtonByCountOutside()
        }
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun ChangeTitleButton() {
        ButtonClickBlueMargin(
            text = "Change Title",
            isFillMaxWidth = true,
        ) {
            composeVm.changeTitle("$titleDefault ${composeVm.count}")
        }
    }

    @Composable
    private fun CounterButtonByCountOutside() {
        ButtonClickBlueMargin(
            text = "Counter Outside : ${composeVm.count}",
            isFillMaxWidth = true,
        ) {
            composeVm.plus()
        }
    }
}