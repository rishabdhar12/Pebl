package com.rishabdhar12.pebl.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.rishabdhar12.core.routes.Routes
import com.rishabdhar12.feature_auth.screens.LoginOrSignupScreen
import com.rishabdhar12.feature_auth.screens.LoginScreen
import com.rishabdhar12.feature_auth.screens.SignUpScreen
import com.rishabdhar12.feature_auth.screens.SplashScreen
import com.rishabdhar12.feature_categories.screens.BudgetAllocationScreen
import com.rishabdhar12.feature_categories.screens.SelectCategoriesScreen

/*
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

        composable(Routes.SelectCategoriesRoute) {
            SelectCategoriesScreen(navController = navController)
        }

        composable(Routes.BudgetAllocationScreen) {
            BudgetAllocationScreen(navController = navController)
        }
    }
}*/

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.SplashRoute
    ) {

        composable(Routes.SplashRoute) {
            SplashScreen(navController)
        }

        composable(Routes.LoginOrSignUpRoute) {
            LoginOrSignupScreen(navController)
        }

        composable(Routes.LoginRoute) {
            LoginScreen(navController)
        }

        composable(Routes.SignupRoute) {
            SignUpScreen(navController)
        }

        // ✅ SHARED CATEGORIES GRAPH
        navigation(
            route = Routes.CategoriesGraph,
            startDestination = Routes.SelectCategoriesRoute
        ) {

            composable(Routes.SelectCategoriesRoute) {
                SelectCategoriesScreen(navController = navController)
            }

            composable(Routes.BudgetAllocationScreen) { backStackEntry ->
                BudgetAllocationScreen(navController = navController)
            }
        }
    }
}

