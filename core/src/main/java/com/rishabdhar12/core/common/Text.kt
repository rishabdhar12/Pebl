package com.rishabdhar12.core.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    fontSize: Dp = 14.dp,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Unspecified,
    letterSpacing: Dp = 0.dp,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = fontSize.value.sp,
        color = color,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing.value.sp,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall.copy(
            lineHeight = 20.sp

        )
    )
}