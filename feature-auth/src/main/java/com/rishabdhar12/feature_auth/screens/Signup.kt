package com.rishabdhar12.feature_auth.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.rishabdhar12.core.common.CustomAlertDialog
import com.rishabdhar12.core.common.CustomLoadingButton
import com.rishabdhar12.core.common.CustomOutlinedTextField
import com.rishabdhar12.core.common.CustomText
import com.rishabdhar12.core.common.TopBarWrapper
import com.rishabdhar12.core.common.strings.PeblColors
import com.rishabdhar12.core.routes.Routes
import com.rishabdhar12.feature_auth.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier = Modifier, viewModel: AuthViewModel = hiltViewModel()) {
    var fullNameFieldValue by viewModel.fullNameInput
    val fullNameErrorMessage by viewModel.fullNameErrorMessage

    var mobileFieldValue by viewModel.mobileInput
    val mobileErrorMessage by viewModel.mobileErrorMessage

    val emailFieldValue by viewModel.emailInput
    val emailErrorMessage by viewModel.emailErrorMessage

    var passwordFieldValue by viewModel.passwordInput
    var  passwordErrorMessage by viewModel.passwordErrorMessage

    val isLoading by viewModel.isLoading.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()
    val authError by viewModel.authError

    var enabled by rememberSaveable { mutableStateOf(true) }


    val isSignedUp by viewModel.isSignedUp.collectAsState()

    LaunchedEffect(isSignedUp) {
        if (isSignedUp == true) {
            navController.navigate(Routes.SelectCategoriesRoute) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

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
                text = "Sign Up",
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
                textFieldValue = fullNameFieldValue,
                onValueChange =viewModel::onFullNameChanged,
                labelText1 = "Enter Full Name",
                labelText2 = "Full Name",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            )
            if(fullNameErrorMessage != null) {
                CustomText(
                    text = fullNameErrorMessage ?: "",
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
                textFieldValue = mobileFieldValue,
                onValueChange = viewModel::onPhoneNumberChanged,
                labelText1 = "Enter Phone Number",
                labelText2 = "Phone Number",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            )

            if(mobileErrorMessage != null) {
                CustomText(
                    text = mobileErrorMessage ?: "",
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
                textFieldValue = emailFieldValue,
                onValueChange = viewModel::onEmailChanged,
                labelText1 = "Enter Email",
                labelText2 = "Email",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
            )
            if(emailErrorMessage != null) {
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
                labelText1 = "Enter Password",
                labelText2 = "Password",
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                visualTransformation = PasswordVisualTransformation(),
            )
            if(passwordErrorMessage != null) {
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



            CustomLoadingButton (
                text = "SIGNUP",
                isLoading = isLoading,
                onClick = {
                    if(fullNameErrorMessage == null &&
                        mobileErrorMessage == null &&
                        emailErrorMessage == null &&
                        passwordErrorMessage == null) {

                        viewModel.signUpUser()
                    }
                }
            )


            if(showDialog){
                CustomAlertDialog(
                    onConfirmation = {
                        viewModel.showDialog.value = false
                    },
                    dialogTitle = "Error",
                    dialogText = authError.toString(),
                    icon = Icons.Default.Warning,
                )
            }

        }
    }
}
