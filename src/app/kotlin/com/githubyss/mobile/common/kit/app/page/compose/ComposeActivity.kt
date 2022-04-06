package com.githubyss.mobile.common.kit.app.page.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.element.InfoDisplay
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeActivity
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.res.common.dimen.SideNormal
import com.githubyss.mobile.common.res.page.compose.PageSidePadding


/**
 * ComposeActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/23 15:49:58
 */
class ComposeActivity : BaseComposeActivity() {

    /** ****************************** Properties ****************************** */

    companion object {
        private val TAG: String = ComposeActivity::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    @Composable
    override fun Content() {
        PageSidePadding(
            background = Color.Black,
            verticalArrangement = Arrangement.Center,
            paddingVertical = Dp.SideNormal,
        ) {
            InfoDisplay(title = getStringFromRes(R.string.comkit_compose))
        }
    }
}
