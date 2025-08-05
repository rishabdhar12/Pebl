package com.rishabdhar12.core.common

import androidx.compose.foundation.layout.RowScope
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
    onClick: () -> Unit = {},
    shape: RoundedCornerShape = RoundedCornerShape(10.dp),
    content: @Composable RowScope.() -> Unit,

) {
    Button(modifier = modifier, colors = ButtonDefaults.buttonColors(
        containerColor = containerColor,
//        contentColor = contentColor,
//        disabledContainerColor = disabledContainerColor,
    ), shape = shape, onClick = onClick) {
        content()
    }
}