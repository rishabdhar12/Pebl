package com.rishabdhar12.core.common

import android.graphics.Paint
import android.provider.CalendarContract
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rishabdhar12.core.common.strings.PeblColors
import androidx.compose.material3.ButtonDefaults

@Composable
fun CustomAlertDialog(
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        containerColor = PeblColors.textColor,
        icon = {
            Icon(icon,
                contentDescription = "Example Icon",
                tint = Color(0xFF780606),
                modifier = Modifier.size(70.dp))
        },
        title = {
            CustomText(text = dialogTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 18.dp,
                color = Color.Red
                )
        },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

            CustomText(text = dialogText,
                fontWeight = FontWeight.Normal,
                fontSize = 14.dp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            }
        },

        onDismissRequest = {
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { onConfirmation()},
                    modifier = Modifier
                        .width(80.dp)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PeblColors.buttonColor,
                        contentColor = PeblColors.textColor,
                    ),
                    shape = RoundedCornerShape(12.dp), // same rounded style
                ) {
                    CustomText(
                        text = "OK",
                        letterSpacing = 1.dp,
                        fontSize = 12.dp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PeblColors.backgroundColor,
//                        style = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        },

    )

}

@Preview(showBackground = true)
@Composable
fun AlertDialogExamplePreview() {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomAlertDialog(
            onConfirmation = { /* Confirm Action */ showDialog = false },
            dialogTitle = "Error Occurred",
            dialogText = "Something went wrong. Please try again.",
            icon = Icons.Default.Warning
        )
    }
}
