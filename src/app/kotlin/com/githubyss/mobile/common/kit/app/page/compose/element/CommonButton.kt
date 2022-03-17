package com.githubyss.mobile.common.kit.app.page.compose.element

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.button.buttonClickBlueBorder
import com.githubyss.mobile.common.res.button.buttonClickBlueColors
import com.githubyss.mobile.common.res.button.buttonClickBlueFontSize
import com.githubyss.mobile.common.res.button.buttonClickBlueShape


@Composable
fun CommonButtonBlue(text: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressState = interactionSource.collectIsPressedAsState().value

    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        shape = ButtonDefaults.buttonClickBlueShape,
        border = ButtonDefaults.buttonClickBlueBorder,
        colors = ButtonDefaults.buttonClickBlueColors(pressState),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(50.dp),
    ) {
        Text(
            text = text,
            fontSize = ButtonDefaults.buttonClickBlueFontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        )
    }
}