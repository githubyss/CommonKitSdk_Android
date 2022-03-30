package com.githubyss.mobile.common.kit.base.activity_fragment.compose

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable


@Composable
fun BaseToolbar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
        // navigationIcon = {
        //     IconButton(onClick = { onBackPressed() }) {
        //         Icon(Icons.Filled.ArrowBack, null)
        //     }
        // },
        // actions = {
        //     IconButton(onClick = { }) {
        //         Icon(Icons.Filled.Share, null)
        //     }
        //     IconButton(onClick = { }) {
        //         Icon(Icons.Filled.Settings, null)
        //     }
        // }
    )
}