package com.githubyss.mobile.common.kit.app.compose_ui.comui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.githubyss.mobile.common.res.common.dimen.SideNone
import com.githubyss.mobile.common.res.common.dimen.SideNormal
import com.githubyss.mobile.common.res.page.pageBgLightGray


@Composable
fun PageSideMarginPadding(
    background: Color = Color.pageBgLightGray,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    paddingHorizontal: Dp = Dp.SideNormal,
    paddingVertical: Dp = Dp.SideNone,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
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
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    content: @Composable ColumnScope.() -> Unit,
) {
    PageSideMarginPadding(
        background = background,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        paddingHorizontal = Dp.SideNone,
        paddingVertical = Dp.SideNone,
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
    paddingHorizontal: Dp = Dp.SideNormal,
    paddingVertical: Dp = Dp.SideNone,
    content: @Composable ColumnScope.() -> Unit,
) {
    PageSideMarginPadding(
        background = background,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        marginHorizontal = Dp.SideNone,
        marginVertical = Dp.SideNone,
        content = content,
    )
}