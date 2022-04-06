package com.githubyss.mobile.common.kit.app.page.compose_button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.res.button_click.compose.ButtonClickBlue
import com.githubyss.mobile.common.res.button_click.compose.ButtonClickBlueMargin
import com.githubyss.mobile.common.res.button_click.compose.ButtonClickBlueWeightHorizontal
import com.githubyss.mobile.common.res.common.dimen.SideNano
import com.githubyss.mobile.common.res.common.dimen.SideNormal
import com.githubyss.mobile.common.res.layout.compose.LayoutWeightHorizontal
import com.githubyss.mobile.common.res.page.compose.PageSidePadding


/**
 * ComposeButtonFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/06 10:14:17
 */
class ComposeButtonFragment : BaseComposeToolbarFragment() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = ComposeButtonFragment::class.java.simpleName
    }

    private val composeButtonVm: ComposeButtonViewModel by viewModels()


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        Toolbar(composeButtonVm.title)
    }

    @Preview
    @Composable
    override fun Content() {
        PageSidePadding(
            paddingVertical = Dp.SideNormal
        ) {
            Buttons()
        }
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun Buttons() {
        ButtonClickBlue(
            text = "XXXXXXXXXXXXXXXXXXXXX",
            outsidePaddingHorizontal = Dp.SideNano,
            outsidePaddingVertical = Dp.SideNano
        )
        ButtonClickBlueMargin(
            text = "XXXXXXXXXXXXXXXXXXXXX"
        )
        ButtonClickBlue(
            text = "XXXXXXXXXXXXXXXXXXXXX",
            outsidePaddingHorizontal = Dp.SideNano,
            outsidePaddingVertical = Dp.SideNano,
            isFillMaxWidth = true
        )
        ButtonClickBlueMargin(
            text = "XXXXXXXXXXXXXXXXXXXXX",
            isFillMaxWidth = true
        )
        ButtonClickBlueWeightHorizontal(
            text = "XXXXXXXXXXXXXXXXXXXXX"
        )
        LayoutWeightHorizontal {
            ButtonClickBlueWeightHorizontal(
                text = "XXXXXXXXXXX",
                modifier = Modifier.weight(1F)
            )
            ButtonClickBlueWeightHorizontal(
                text = "YYYYYYYYYYYYYYYYYYYYY",
                modifier = Modifier.weight(1F)
            )
        }
    }
}