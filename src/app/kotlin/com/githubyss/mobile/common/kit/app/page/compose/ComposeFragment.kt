package com.githubyss.mobile.common.kit.app.page.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeFragment


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
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Compose")
        }
    }

    override fun setupUi() {
    }

    override fun observeViewModel() {
    }

    override fun removeViewModelObserver() {
    }


    /** ****************************** Functions ****************************** */


}
