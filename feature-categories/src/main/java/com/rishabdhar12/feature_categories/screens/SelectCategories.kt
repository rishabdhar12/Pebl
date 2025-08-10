package com.rishabdhar12.feature_categories.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rishabdhar12.feature_categories.viewmodels.CategoriesViewModel

@Composable
fun SelectCategoriesScreen(navController: NavController, viewModel: CategoriesViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getCategoriesFromRemote()
    }
}