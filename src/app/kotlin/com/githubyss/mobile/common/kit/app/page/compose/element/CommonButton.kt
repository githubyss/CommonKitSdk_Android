package com.githubyss.mobile.common.kit.app.page.compose.element

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.githubyss.mobile.common.kit.R
import com.githubyss.mobile.common.kit.util.getColorFromRes


@Composable
fun CommonButtonBlue(text: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressState = interactionSource.collectIsPressedAsState().value
    val normalBackgroundColor = Color(getColorFromRes(R.color.comres_buttonClickBgNormal_blue))
    val pressedBackgroundColor = Color(getColorFromRes(R.color.comres_buttonClickBgPressed_blue))
    val disabledBackgroundColor = Color(getColorFromRes(R.color.comres_buttonClickBgDisabled_blue))

    // 设置颜色
    val buttonColors = when {
        pressState -> ButtonDefaults.buttonColors(backgroundColor = pressedBackgroundColor, disabledBackgroundColor = disabledBackgroundColor)
        else -> ButtonDefaults.buttonColors(backgroundColor = normalBackgroundColor, disabledBackgroundColor = disabledBackgroundColor)
    }

    val buttonShape = RoundedCornerShape(2.dp)

    Button(
        onClick = onClick,
        colors = buttonColors,
        shape = buttonShape,
        interactionSource = interactionSource,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(50.dp),
    ) {
        Text(
            text = text,
            color = Color(getColorFromRes(R.color.comres_buttonClickText_blue)),
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        )
    }
}