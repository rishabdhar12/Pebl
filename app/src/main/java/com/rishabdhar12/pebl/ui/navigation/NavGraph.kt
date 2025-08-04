package com.rishabdhar12.pebl.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rishabdhar12.core.routes.Routes
import com.rishabdhar12.feature_auth.presentation.screens.LoginOrSignupScreen
import com.rishabdhar12.feature_auth.presentation.screens.LoginScreen
import com.rishabdhar12.feature_auth.presentation.screens.SignUpScreen
import com.rishabdhar12.feature_auth.presentation.screens.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.SplashRoute
    ) {
        composable(Routes.SplashRoute) {
            SplashScreen(navController = navController)
        }
        composable(Routes.LoginOrSignUpRoute) {
            LoginOrSignupScreen(navController = navController)
        }
        composable(Routes.LoginRoute) {
            LoginScreen(navController = navController)
        }

        composable(Routes.SignupRoute) {
            SignUpScreen(navController = navController)
        }
    }
}