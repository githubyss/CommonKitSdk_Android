package com.githubyss.common.kit.app.z_copy.compose_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.githubyss.common.res.common.dimen.SpaceNone
import com.githubyss.common.res.common.dimen.SpaceNormal
import com.githubyss.common.res.page.pageBgLightGray


@Composable
fun PageSideMarginPadding(
    background: Color = Color.pageBgLightGray,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    paddingHorizontal: Dp = Dp.SpaceNormal,
    paddingVertical: Dp = Dp.SpaceNone,
    marginHorizontal: Dp = Dp.SpaceNone,
    marginVertical: Dp = Dp.SpaceNone,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content,
        modifier = Modifier
            // 先设置 padding，再设置长高、背景、滑动，padding 作用等同于 margin
            .padding(horizontal = marginHorizontal, vertical = marginVertical)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(background)
            .verticalScroll(rememberScrollState())
            // 先设置长高、背景、滑动，再设置 padding，padding 作用等同于 padding
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
    )
}

@Composable
fun PageSideMargin(
    background: Color = Color.pageBgLightGray,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    marginHorizontal: Dp = Dp.SpaceNone,
    marginVertical: Dp = Dp.SpaceNone,
    content: @Composable ColumnScope.() -> Unit,
) {
    PageSideMarginPadding(
        background = background,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        paddingHorizontal = Dp.SpaceNone,
        paddingVertical = Dp.SpaceNone,
        marginHorizontal = marginHorizontal,
        marginVertical = marginVertical,
        content = content,
    )
}

@Composable
fun PageSidePadding(
    background: Color = Color.pageBgLightGray,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    paddingHorizontal: Dp = Dp.SpaceNormal,
    paddingVertical: Dp = Dp.SpaceNone,
    content: @Composable ColumnScope.() -> Unit,
) {
    PageSideMarginPadding(
        background = background,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        marginHorizontal = Dp.SpaceNone,
        marginVertical = Dp.SpaceNone,
        content = content,
    )
}