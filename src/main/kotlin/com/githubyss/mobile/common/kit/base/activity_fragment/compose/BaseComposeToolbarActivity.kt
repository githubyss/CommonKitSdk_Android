package com.githubyss.mobile.common.kit.base.activity_fragment.compose

import androidx.compose.runtime.Composable
import com.githubyss.mobile.common.kit.util.getStringFromRes


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
