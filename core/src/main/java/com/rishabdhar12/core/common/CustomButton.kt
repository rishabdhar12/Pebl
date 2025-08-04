package com.rishabdhar12.core.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rishabdhar12.core.common.strings.PeblColors

@Composable
fun CustomButton(
    modifier: Modifier,
    containerColor: Color = PeblColors.buttonColor,
//    contentColor: Color,
//    disabledContainerColor: Color,
    textColor: Color = PeblColors.backgroundColor,
    onClick: () -> Unit = {},
    text: String,
    shape: RoundedCornerShape = RoundedCornerShape(10.dp),
) {
    Button(modifier = modifier, colors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
//        contentColor = contentColor,
//        disabledContainerColor = disabledContainerColor,
    ), shape = shape, onClick = onClick) {
        CustomText(text = text,
            letterSpacing = 1.dp,
            fontSize = 16.dp,
            fontWeight = FontWeight.ExtraBold,
            color = textColor,
            modifier = Modifier)
    }
}