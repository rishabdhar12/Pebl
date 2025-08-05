package com.rishabdhar12.core.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rishabdhar12.core.common.strings.PeblColors

@Composable
fun CustomLoadingButton(
    isLoading: Boolean,
    text: String,
    onClick: () -> Unit,
    textColor: Color = PeblColors.backgroundColor,
) {

    CustomButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),

        content = {
            if (isLoading) {
                CircularProgressIndicator(
                    color = PeblColors.backgroundColor,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {

                CustomText(
                    text = text,
                    letterSpacing = 1.dp,
                    fontSize = 16.dp,
                    fontWeight = FontWeight.ExtraBold,
                    color = textColor,
                    modifier = Modifier
                )

            }
        },
        onClick = { if (!isLoading) onClick() }
    )
}