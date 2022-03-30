package com.githubyss.mobile.common.kit.base.activity_fragment.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Page()
            }
        }
    }


    /** ****************************** Abstract ****************************** */

    @Composable
    abstract fun Page()

    @Composable
    abstract fun Content()
}
