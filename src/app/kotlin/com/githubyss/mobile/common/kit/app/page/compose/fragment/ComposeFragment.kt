package com.githubyss.mobile.common.kit.app.page.compose.fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.app.compose_ui.InfoDisplay
import com.githubyss.mobile.common.kit.app.compose_ui.comui.PageSidePadding
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeFragment
import com.githubyss.mobile.common.kit.util.getStringFromRes
import com.githubyss.mobile.common.res.common.dimen.SpaceNormal


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

    @Composable
    override fun Content() {
        PageSidePadding(
            verticalArrangement = Arrangement.Center,
            paddingVertical = Dp.SpaceNormal,
        ) {
            InfoDisplay(title = getStringFromRes(R.string.comkit_compose))
        }
    }
}