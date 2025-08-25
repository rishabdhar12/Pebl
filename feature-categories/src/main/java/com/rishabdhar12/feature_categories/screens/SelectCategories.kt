package com.rishabdhar12.feature_categories.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rishabdhar12.core.common.CustomText
import com.rishabdhar12.core.common.TopBarWrapper
import com.rishabdhar12.core.common.strings.PeblColors
import com.rishabdhar12.feature_categories.viewmodels.CategoriesViewModel

@Composable
fun SelectCategoriesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CategoriesViewModel = hiltViewModel()) {

    val scrollState = rememberScrollState()

    val categoriesState = viewModel.categoriesModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCategoriesFromRemote()
    }
    TopBarWrapper(
        showBackButton = false,
        onBackClick = {  },
        title = "",
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .padding(start = 20.dp, end = 20.dp, top = 30.dp, bottom = 20.dp)) {
            categoriesState.value?.let { categories ->
                CategorySection(
                    title = "Education",
                    items = categories.education,
                    onClick = {}
                )

                Spacer(modifier = modifier.padding(top = 20.dp))

                CategorySection(
                    title = "Housing",
                    items = categories.housing,
                    onClick = {}
                )

                Spacer(modifier = modifier.padding(top = 20.dp))

                CategorySection(
                    title = "Food",
                    items = categories.food,
                    onClick = {}
                )

                Spacer(modifier = modifier.padding(top = 20.dp))

                CategorySection(
                    title = "Transportation",
                    items = categories.transportation,
                    onClick = {}
                )

                Spacer(modifier = modifier.padding(top = 20.dp))

                CategorySection(
                    title = "Health",
                    items = categories.health,
                    onClick = {}
                )

                Spacer(modifier = modifier.padding(top = 20.dp))

                CategorySection(
                    title = "Entertainment",
                    items = categories.entertainment,
                    onClick = {}
                )

                Spacer(modifier = modifier.padding(top = 20.dp))

                CategorySection(
                    title = "Personal Care",
                    items = categories.personalCare,
                    onClick = {}
                )

                Spacer(modifier = modifier.padding(top = 20.dp))

                CategorySection(
                    title = "Financial",
                    items = categories.financial,
                    onClick = {}
                )

                Spacer(modifier = modifier.padding(top = 20.dp))

                CategorySection(
                    title = "Gifts Donations",
                    items = categories.giftsDonations,
                    onClick = {}
                )

                Spacer(modifier = modifier.padding(top = 20.dp))

                CategorySection(
                    title = "Miscellaneous",
                    items = categories.miscellaneous,
                    onClick = {}
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
    onClick: () -> Unit = {},
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
            items.forEach {  item ->
                FilterChip(
                    onClick = onClick,
                    label = {
                        Text(item)
                    },
                    selected = false,
                )
            }
        }
    }
}