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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.util.getColorFromRes
import com.githubyss.mobile.common.res.common.color.xFF999999
import com.githubyss.mobile.common.res.common.dimen.CornerRadiusMini
import com.githubyss.mobile.common.res.common.dimen.FontSizeBig


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
            .background(color = Color.xFF999999, shape = RoundedCornerShape(Dp.CornerRadiusMini)),
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontSize = TextUnit.FontSizeBig,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        )
    }
}
