package com.rishabdhar12.feature_auth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rishabdhar12.core.common.AssetImage
import com.rishabdhar12.core.common.CustomButton
import com.rishabdhar12.core.common.CustomText
import com.rishabdhar12.core.common.strings.PeblColors
import com.rishabdhar12.core.common.strings.PeblIcons
import com.rishabdhar12.core.routes.Routes

@Composable
fun LoginOrSignupScreen(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.wrapContentSize()
        ) {
            AssetImage(
                PeblIcons.Logo,
                modifier = Modifier.size(130.dp)
            )


            Spacer(modifier = Modifier.height(24.dp))

            CustomButton(
                content = {

                    CustomText(
                        text = "Log In",
                        letterSpacing = 1.dp,
                        fontSize = 16.dp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PeblColors.backgroundColor,
                        modifier = Modifier
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp),
                shape = RoundedCornerShape(30.dp),
                onClick = {
                    navController.navigate(Routes.LoginRoute)
                }
            )

            CustomButton(
                content = {

                    CustomText(
                        text = "Sign Up",
                        letterSpacing = 1.dp,
                        fontSize = 16.dp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PeblColors.backgroundColor,
                        modifier = Modifier
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(48.dp),
                shape = RoundedCornerShape(30.dp),
                containerColor = Color.White,
                onClick = {
                    navController.navigate(Routes.SignupRoute)
                }
            )

        }
    }
}
