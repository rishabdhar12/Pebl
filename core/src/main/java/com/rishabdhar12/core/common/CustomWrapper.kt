package com.rishabdhar12.core.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rishabdhar12.core.common.strings.PeblColors

@Composable
fun TopBarWrapper(
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    title: String? = null,
    rightIcon: (@Composable () -> Unit)? = null,
    content: @Composable Modifier.() -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBackButton && onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = PeblColors.textColor,
                        modifier = Modifier.size(40.dp)

                    )
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                contentAlignment = if (showBackButton) Alignment.Center else Alignment.CenterStart
            ) {
                title?.let {
                    CustomText(
                        text = it,
                    )
                }
            }

            if (rightIcon != null) {
                Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                    rightIcon()
                }
            } else {
                Spacer(modifier = Modifier.width(48.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            content(Modifier.fillMaxSize())
        }
    }
}
