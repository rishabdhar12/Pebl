package com.rishabdhar12.core.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import java.io.IOException

@Composable
fun AssetImage(filename: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val bitmap: Bitmap? = remember(filename) {
        try {
            val inputStream = context.assets.open(filename)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = null,
            modifier = modifier
        )
    }
}