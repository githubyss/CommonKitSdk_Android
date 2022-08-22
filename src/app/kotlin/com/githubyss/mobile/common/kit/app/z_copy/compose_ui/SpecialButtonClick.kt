package com.githubyss.mobile.common.kit.app.z_copy.compose_ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.kit.app.z_copy.compose_ui.ButtonClickBlue
import com.githubyss.mobile.common.res.common.dimen.SpaceNano
import com.githubyss.mobile.common.res.common.dimen.SpaceTiny


@Composable
fun ButtonClickBlueMargin(
    text: String,
    modifier: Modifier = Modifier,
    outsidePaddingHorizontal: Dp = Dp.SpaceNano,
    outsidePaddingVertical: Dp = Dp.SpaceNano,
    insidePaddingHorizontal: Dp = Dp.SpaceTiny,
    insidePaddingVertical: Dp = Dp.SpaceTiny,
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
    outsidePaddingHorizontal: Dp = Dp.SpaceNano,
    outsidePaddingVertical: Dp = Dp.SpaceNano,
    insidePaddingHorizontal: Dp = Dp.SpaceTiny,
    insidePaddingVertical: Dp = Dp.SpaceTiny,
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