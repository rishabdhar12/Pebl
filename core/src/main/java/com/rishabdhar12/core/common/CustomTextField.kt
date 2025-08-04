package com.rishabdhar12.core.common

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rishabdhar12.core.common.strings.PeblColors

@Composable
fun CustomOutlinedTextField(
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    labelText1: String,
    labelText2: String,
    textColor: Color = PeblColors.textColor,
    fontSize: TextUnit = 16.sp,
    keyboardType: KeyboardType,
    imeAction: ImeAction = ImeAction.Done,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val displayLabel = if (isFocused || textFieldValue.isNotEmpty()) {
        labelText2
    } else {
        labelText1
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(10.dp),
        label = {
            Text(
                text = displayLabel,
                style = TextStyle(
                    fontSize = fontSize,
                )
            )
        },
        textStyle = TextStyle(
            color = textColor,
            fontSize = fontSize,
        ),
        colors = OutlinedTextFieldDefaults.colors(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        interactionSource = interactionSource,
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
    )
}
