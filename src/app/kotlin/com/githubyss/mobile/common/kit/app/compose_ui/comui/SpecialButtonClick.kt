package com.githubyss.mobile.common.kit.app.compose_ui.comui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.dimen.SideNano
import com.githubyss.mobile.common.res.common.dimen.SideTiny


@Composable
fun ButtonClickBlueMargin(
    text: String,
    modifier: Modifier = Modifier,
    outsidePaddingHorizontal: Dp = Dp.SideNano,
    outsidePaddingVertical: Dp = Dp.SideNano,
    insidePaddingHorizontal: Dp = Dp.SideTiny,
    insidePaddingVertical: Dp = Dp.SideTiny,
    width: Dp = 0.dp,
    height: Dp = 60.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonClickBlue(
        text = text,
        outsidePaddingHorizontal = outsidePaddingHorizontal,
        outsidePaddingVertical = outsidePaddingVertical,
        insidePaddingHorizontal = insidePaddingHorizontal,
        insidePaddingVertical = insidePaddingVertical,
        width = width,
        height = height,
        isFillMaxWidth = isFillMaxWidth,
        isFillMaxHeight = isFillMaxHeight,
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
    )
}

@Composable
fun ButtonClickBlueWeightHorizontal(
    text: String,
    modifier: Modifier = Modifier,
    outsidePaddingHorizontal: Dp = Dp.SideNano,
    outsidePaddingVertical: Dp = Dp.SideNano,
    insidePaddingHorizontal: Dp = Dp.SideTiny,
    insidePaddingVertical: Dp = Dp.SideTiny,
    isFillMaxWidth: Boolean = true,
    height: Dp = 60.dp,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonClickBlue(
        text = text,
        outsidePaddingHorizontal = outsidePaddingHorizontal,
        outsidePaddingVertical = outsidePaddingVertical,
        insidePaddingHorizontal = insidePaddingHorizontal,
        insidePaddingVertical = insidePaddingVertical,
        isFillMaxWidth = isFillMaxWidth,
        height = height,
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
    )
}