package com.rishabdhar12.feature_auth.presentation.screens

import android.R.attr.enabled
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rishabdhar12.core.common.CustomButton
import com.rishabdhar12.core.common.CustomOutlinedTextField
import com.rishabdhar12.core.common.CustomText
import com.rishabdhar12.core.common.TopBarWrapper
import com.rishabdhar12.core.common.strings.PeblColors
import com.rishabdhar12.feature_auth.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier, viewModel: AuthViewModel = hiltViewModel()) {

    val emailFieldValue by viewModel.emailInput
    val emailErrorMessage by viewModel.emailErrorMessage

    var passwordFieldValue by viewModel.passwordInput
    var passwordErrorMessage by viewModel.passwordErrorMessage

    var enabled by rememberSaveable { mutableStateOf(true) }

    val authState by viewModel.authState.collectAsState()

    TopBarWrapper(
        showBackButton = true,
        onBackClick = { navController.popBackStack() },
        title = "",
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth().fillMaxHeight()
                .padding(start = 20.dp, end = 20.dp, top = 80.dp)
        ) {
            CustomText(
                text = "Login",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.dp,
                color = PeblColors.textColor,
                letterSpacing = 1.7.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.CenterStart)
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            CustomOutlinedTextField(
                textFieldValue = emailFieldValue,
                onValueChange = viewModel::onEmailChanged,
                labelText1 = "Enter email",
                labelText2 = "Email",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
            )

            if (emailErrorMessage != null) {
                CustomText(
                    text = emailErrorMessage ?: "",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.dp,
                    color = Color.Red,
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.CenterStart)
                )
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))

            CustomOutlinedTextField(
                textFieldValue = passwordFieldValue,
                onValueChange = viewModel::onPasswordChanged,
                labelText1 = "Enter password",
                labelText2 = "Password",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                visualTransformation = PasswordVisualTransformation(),
            )

            if (passwordErrorMessage != null) {
                CustomText(
                    text = passwordErrorMessage ?: "",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.dp,
                    color = Color.Red,
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.CenterStart)
                )
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))



            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
//                contentColor = Color.White,
//                disabledContainerColor = lightGray,
                text = "LOGIN",
                onClick = {
                    viewModel.signUpUser()
                }
            )

            Spacer(modifier = Modifier.padding(top = 12.dp))


            CustomText(
                text = "Forgot Password?",
                fontWeight = FontWeight.Normal,
                fontSize = 12.dp,
                color = PeblColors.textColor,
                modifier = Modifier
                    .clickable(enabled = enabled) {
                    },
            )

//            authState?.let {
//                if (it.isSuccess) {
//                    CustomText("Success!", color = Color.Green)
//                } else {
//                    CustomText("Error: ${it.exceptionOrNull()?.message}", color = Color.Red)
//                }
//            }

            // TODO: fetch proper error message and display as custom alert dialog
        }
    }
}