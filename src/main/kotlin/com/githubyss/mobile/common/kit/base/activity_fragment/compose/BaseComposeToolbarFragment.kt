package com.githubyss.mobile.common.kit.base.activity_fragment.compose

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable


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
        TopAppBar(title = { Text(text = getString(titleResId)) })
    }

    /** Setup Toolbar text by String. */
    @Composable
    protected fun Toolbar(titleString: String) {
        TopAppBar(title = { Text(text = titleString) })
    }
}
