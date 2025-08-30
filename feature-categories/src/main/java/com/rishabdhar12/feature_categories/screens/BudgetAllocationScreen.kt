package com.rishabdhar12.feature_categories.screens

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rishabdhar12.core.common.TopBarWrapper
import com.rishabdhar12.feature_categories.viewmodels.CategoriesViewModel

@Composable
fun BudgetAllocationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CategoriesViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()


    TopBarWrapper(
        showBackButton = true,
        onBackClick = { navController.popBackStack()},
        title = "",
    ) {
    }
}