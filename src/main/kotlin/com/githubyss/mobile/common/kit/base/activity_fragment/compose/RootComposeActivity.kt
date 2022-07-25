package com.githubyss.mobile.common.kit.base.activity_fragment.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.githubyss.mobile.common.kit.base.activity_fragment.classical.BaseActivity


/**
 * RootComposeActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/02/24 15:43:29
 */
abstract class RootComposeActivity : BaseActivity(0) {

    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Page()
        }
    }


    /** ****************************** Abstract ****************************** */

    /**  */
    @Composable
    abstract fun Page()

    /**  */
    @Composable
    abstract fun Content()
}