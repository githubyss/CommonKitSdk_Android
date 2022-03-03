package com.githubyss.mobile.common.kit.base.activity_fragment.compose

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import com.githubyss.mobile.common.kit.util.ResourceUtils


/**
 * BaseComposeToolbarActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/24 15:57:42
 */
abstract class BaseComposeToolbarActivity : RootComposeActivity() {

    /** ****************************** Override ****************************** */

    @Composable
    override fun Page() {
        Toolbar()
        Content()
    }


    /** ****************************** Open ****************************** */

    @Composable
    open fun Toolbar() {
    }


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    @Composable
    protected fun Toolbar(titleResId: Int) {
        ComposeToolbar(ResourceUtils.getString(titleResId))
    }

    /** Setup Toolbar text by String. */
    @Composable
    protected fun Toolbar(titleString: String) {
        ComposeToolbar(titleString)
    }

    @Composable
    fun ComposeToolbar(title: String) {
        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = { onBackPressed() }) {
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
    }
}
