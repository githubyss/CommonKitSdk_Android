package com.githubyss.mobile.common.kit.base.activity_fragment.compose

import androidx.compose.runtime.Composable
import com.githubyss.mobile.common.kit.util.getStringFromRes


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
    abstract fun Toolbar()


    /** ****************************** Functions ****************************** */

    /** Setup Toolbar text by ResId. */
    @Composable
    protected fun Toolbar(titleResId: Int) {
        BaseToolbar(getStringFromRes(titleResId))
    }

    /** Setup Toolbar text by String. */
    @Composable
    protected fun Toolbar(titleString: String) {
        BaseToolbar(titleString)
    }
}
