package com.githubyss.mobile.common.kit.base.compose.activity_fragment

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.githubyss.mobile.common.kit.util.ResourceUtils


/**
 * BaseReflectBindingToolbarFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/09 14:35:12
 */
abstract class BaseComposeToolbarFragment : RootComposeFragment() {

    /** ****************************** Override ****************************** */

    @Composable
    override fun Content() {
        Toolbar()
    }


    /** ****************************** Open ****************************** */

    @Composable
    open fun Toolbar() {
    }


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    @Composable
    protected fun Toolbar(titleResId: Int) {
        TopAppBar(title = { Text(text = ResourceUtils.getString(titleResId)) })
    }

    /** Setup Toolbar text by String. */
    @Composable
    protected fun Toolbar(titleString: String) {
        TopAppBar(title = { Text(text = titleString) })
    }
}
