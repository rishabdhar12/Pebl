package com.rishabdhar12.feature_auth.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rishabdhar12.core.common.AssetImage
import com.rishabdhar12.core.common.CustomText
import com.rishabdhar12.core.common.strings.PeblColors
import com.rishabdhar12.core.common.strings.PeblIcons
import com.rishabdhar12.core.routes.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, modifier: Modifier = Modifier) {

    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Routes.LoginOrSignUpRoute) {
            popUpTo(Routes.SplashRoute) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AssetImage(
                PeblIcons.Logo,
                modifier = Modifier.size(130.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            CustomText(
                text = "Thriftly",
                fontSize = 30.dp,
                fontWeight = FontWeight.ExtraBold,

                letterSpacing = 1.2.dp,
                color = PeblColors.buttonColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)

            )

            Spacer(modifier = Modifier.height(2.dp))

            CustomText(
                text = "Take control of your finances",
                fontSize = 14.dp,
                color = PeblColors.textColor,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)
            )
        }
    }
}