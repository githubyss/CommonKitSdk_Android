package com.githubyss.mobile.common.kit.app.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.color.xFF999999
import com.githubyss.mobile.common.res.common.dimen.CornerRadiusMini
import com.githubyss.mobile.common.res.common.dimen.FontSizeBig
import com.githubyss.mobile.common.res.text.compose.TextColumn


@Composable
fun InfoDisplay(title: String) {
    TextColumn(
        text = title,
        textColor = Color.Black,
        fontSize = TextUnit.FontSizeBig,
        textAlign = TextAlign.Center,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.xFF999999, shape = RoundedCornerShape(Dp.CornerRadiusMini)),
    )
}