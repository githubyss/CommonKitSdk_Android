package com.githubyss.mobile.common.kit.app.z_copy.compose_ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp


@Composable
fun TopNavigationBar(
    text: String,
    modifier: Modifier = Modifier,
    // textColor: Color = Color.Unspecified,
    // textBg: Color = Color.Unspecified,
    // fontSize: TextUnit = TextUnit.Unspecified,
    // fontStyle: FontStyle? = null,
    // fontWeight: FontWeight? = null,
    // fontFamily: FontFamily? = null,
    // letterSpacing: TextUnit = TextUnit.Unspecified,
    // textDecoration: TextDecoration? = null,
    // textAlign: TextAlign? = null,
    // lineHeight: TextUnit = TextUnit.Unspecified,
    // overflow: TextOverflow = TextOverflow.Clip,
    // softWrap: Boolean = true,
    // maxLines: Int = 1,
    // onTextLayout: (TextLayoutResult) -> Unit = {},
    // style: TextStyle = LocalTextStyle.current,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    navigation: @Composable (() -> Unit)? = null,
    onNavigationClick: (() -> Unit)? = null,
) {
    TopActionBar(
        text = text,
        // textColor = textColor,
        // textBg = textBg,
        // fontSize = fontSize,
        // fontStyle = fontStyle,
        // fontWeight = fontWeight,
        // fontFamily = fontFamily,
        // letterSpacing = letterSpacing,
        // textDecoration = textDecoration,
        // textAlign = textAlign,
        // lineHeight = lineHeight,
        // overflow = overflow,
        // softWrap = softWrap,
        // maxLines = maxLines,
        // onTextLayout = onTextLayout,
        // style = style,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        navigation = navigation,
        onNavigationClick = onNavigationClick,
        actions = {},
        modifier = modifier,
    )
}

@Composable
fun TopActionBar(
    text: String,
    modifier: Modifier = Modifier,
    // textColor: Color = Color.Unspecified,
    // textBg: Color = Color.Unspecified,
    // fontSize: TextUnit = TextUnit.Unspecified,
    // fontStyle: FontStyle? = null,
    // fontWeight: FontWeight? = null,
    // fontFamily: FontFamily? = null,
    // letterSpacing: TextUnit = TextUnit.Unspecified,
    // textDecoration: TextDecoration? = null,
    // textAlign: TextAlign? = null,
    // lineHeight: TextUnit = TextUnit.Unspecified,
    // overflow: TextOverflow = TextOverflow.Clip,
    // softWrap: Boolean = true,
    // maxLines: Int = 1,
    // onTextLayout: (TextLayoutResult) -> Unit = {},
    // style: TextStyle = LocalTextStyle.current,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    navigation: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    onNavigationClick: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(
                text = text,
                // color = textColor,
                // fontSize = fontSize,
                // fontStyle = fontStyle,
                // fontWeight = fontWeight,
                // fontFamily = fontFamily,
                // letterSpacing = letterSpacing,
                // textDecoration = textDecoration,
                // textAlign = textAlign,
                // lineHeight = lineHeight,
                // overflow = overflow,
                // softWrap = softWrap,
                // maxLines = maxLines,
                // onTextLayout = onTextLayout,
                // style = style,
                // modifier = Modifier
                //     .wrapContentWidth()
                //     .wrapContentHeight()
                //     .background(textBg)
            )
        },
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        navigationIcon = when {
            navigation != null -> {
                navigation
            }
            onNavigationClick != null -> {
                {
                    IconButton(onClick = onNavigationClick) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            }
            else -> {
                null
            }
        },
        actions = when {
            actions != { } -> {
                actions
            }
            else -> {
                {
                    // IconButton(onClick = { }) {
                    //     Icon(Icons.Filled.Share, null)
                    // }
                    // IconButton(onClick = { }) {
                    //     Icon(Icons.Filled.Settings, null)
                    // }
                }
            }
        },
        modifier = modifier,
    )
}