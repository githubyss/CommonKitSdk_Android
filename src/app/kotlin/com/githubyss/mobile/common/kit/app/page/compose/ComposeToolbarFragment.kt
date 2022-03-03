package com.githubyss.mobile.common.kit.app.page.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.base.compose.activity_fragment.BaseComposeToolbarFragment


/**
 * ComposeToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/24 16:04:32
 */
class ComposeToolbarFragment : BaseComposeToolbarFragment() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = ComposeToolbarFragment::class.java.simpleName
    }


    /** ****************************** Override ****************************** */

    @Preview
    @Composable
    override fun Content() {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            TopAppBar(
                title = { Text("这是标题") },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Share, null)
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Settings, null)
                    }
                }
            )
            Text("Compose")
        }
    }

    @Composable
    override fun Toolbar() {
        Toolbar(titleResId = R.string.comkit_compose_toolbar_title)
    }

    override fun setupUi() {
    }

    override fun observeViewModel() {
    }

    override fun removeViewModelObserver() {
    }


    /** ****************************** Functions ****************************** */


}
