package com.githubyss.mobile.common.kit.app.page.compose

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
import com.githubyss.mobile.common.kit.util.ResourceUtils


@Composable
fun ComposePageContent(content: @Composable ColumnScope.() -> Unit) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            // .verticalScroll(rememberScrollState())
            .padding(horizontal = 14.dp)
            .background(Color(ResourceUtils.getColor(R.color.comres_layoutBg_lightGray))),
    )
}

@Composable
fun ComposeDisplay(title: String) {
    Text(
        text = title,
        color = Color(ResourceUtils.getColor(R.color.comres_color_black)),
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color(ResourceUtils.getColor(R.color.comres_color_999999)), shape = RoundedCornerShape(5.dp)),
    )
}