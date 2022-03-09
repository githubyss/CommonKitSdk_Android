package com.githubyss.mobile.common.kit.app.page.compose.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.util.getColorFromRes


@Composable
inline fun ComposePageContent(content: @Composable ColumnScope.() -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(getColorFromRes(R.color.comres_layoutBg_lightGray)))
            // .verticalScroll(rememberScrollState())
            .padding(horizontal = 14.dp),
    )
}

@Composable
fun ComposeDisplay(title: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color(getColorFromRes(R.color.comres_color_999999)), shape = RoundedCornerShape(5.dp)),
    ) {
        Text(
            text = title,
            color = Color(getColorFromRes(R.color.comres_color_black)),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )
    }
}