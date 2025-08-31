package com.rishabdhar12.feature_categories.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rishabdhar12.core.common.CustomText
import com.rishabdhar12.core.common.TopBarWrapper
import com.rishabdhar12.core.common.strings.PeblColors
import com.rishabdhar12.core.routes.Routes
import com.rishabdhar12.feature_categories.viewmodels.CategoriesViewModel

@Composable
fun SelectCategoriesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val categoriesState = viewModel.categoriesModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCategoriesFromRemote()
    }

    TopBarWrapper(
        showBackButton = false,
        onBackClick = { },
        title = "",
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(scrollState)
                    .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 20.dp)
            ) {
                categoriesState.value?.let { categories ->

                    CategorySection(
                        title = "Education",
                        items = categories.education,
                        onClick = { item -> viewModel.addEducation(item) },
                        isSelected = { item -> viewModel.educationList.contains(item) }
                    )

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    CategorySection(
                        title = "Housing",
                        items = categories.housing,
                        onClick = { item -> viewModel.addHousing(item) },
                        isSelected = { item -> viewModel.housingList.contains(item) }
                    )

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    CategorySection(
                        title = "Food",
                        items = categories.food,
                        onClick = { item -> viewModel.addFood(item) },
                        isSelected = { item -> viewModel.foodList.contains(item) }
                    )

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    CategorySection(
                        title = "Transportation",
                        items = categories.transportation,
                        onClick = { item -> viewModel.addTransportation(item) },
                        isSelected = { item -> viewModel.transportationList.contains(item) }
                    )

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    CategorySection(
                        title = "Health",
                        items = categories.health,
                        onClick = { item -> viewModel.addHealth(item) },
                        isSelected = { item -> viewModel.healthList.contains(item) }
                    )

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    CategorySection(
                        title = "Entertainment",
                        items = categories.entertainment,
                        onClick = { item -> viewModel.addEntertainment(item) },
                        isSelected = { item -> viewModel.entertainmentList.contains(item) }
                    )

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    CategorySection(
                        title = "Personal Care",
                        items = categories.personalCare,
                        onClick = { item -> viewModel.addPersonalCare(item) },
                        isSelected = { item -> viewModel.personalCareList.contains(item) }
                    )

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    CategorySection(
                        title = "Financial",
                        items = categories.financial,
                        onClick = { item -> viewModel.addFinancial(item) },
                        isSelected = { item -> viewModel.financialList.contains(item) }
                    )

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    CategorySection(
                        title = "Gifts Donations",
                        items = categories.giftsDonations,
                        onClick = { item -> viewModel.addGifts(item) },
                        isSelected = { item -> viewModel.giftsList.contains(item) }
                    )

                    Spacer(modifier = Modifier.padding(top = 20.dp))

                    CategorySection(
                        title = "Miscellaneous",
                        items = categories.miscellaneous,
                        onClick = { item -> viewModel.addMisc(item) },
                        isSelected = { item -> viewModel.miscList.contains(item) }
                    )
                }
            }
            FloatingActionButton(
                containerColor = if (viewModel.selectedCategoriesList.isNotEmpty()) {
                    PeblColors.buttonColor
                } else {
                    Color.Gray
                },
                onClick = {
                    if (viewModel.selectedCategoriesList.isNotEmpty()) {
//                        navController.navigate(Routes.BudgetAllocationScreen)
                        navController.navigate(Routes.BudgetAllocationScreen) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }

                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Confirm"
                )
            }
        }
    }
}

@Composable
fun CategorySection(
    modifier: Modifier = Modifier,
    title: String,
    items: List<String>,
    onClick: (String) -> Unit = {},
    isSelected: (String) -> Boolean = { false }
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        CustomText(
            text = title,
            fontWeight = FontWeight.Medium,
            fontSize = 18.dp,
            color = PeblColors.buttonColor,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.CenterStart)
        )

        FlowRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items.forEach { item ->
                FilterChip(
                    onClick = { onClick(item) },
                    label = { Text(item) },
                    selected = isSelected(item), // ✅ now correct per-item
                )
            }
        }
    }
}
