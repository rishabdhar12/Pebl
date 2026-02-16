package com.rishabdhar12.feature_categories.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rishabdhar12.core.common.CustomOutlinedTextField
import com.rishabdhar12.core.common.CustomText
import com.rishabdhar12.core.common.TopBarWrapper
import com.rishabdhar12.core.common.strings.PeblColors
import com.rishabdhar12.core.routes.Routes
import com.rishabdhar12.feature_categories.viewmodels.CategoriesViewModel

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetAllocationScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
//    viewModel: CategoriesViewModel = hiltViewModel()

) {

    val parentEntry = remember {
        navController.getBackStackEntry(Routes.CategoriesGraph)
    }

    val viewModel: CategoriesViewModel = hiltViewModel(parentEntry)

    val selectedCategories = viewModel.selectedCategoriesList

    LaunchedEffect(Unit) {
        Log.i("Length", "${selectedCategories.size}")
    }

    var expanded by remember { mutableStateOf(false) }
    var selectedCurrency by remember { mutableStateOf("USD") }
    var searchQuery by remember { mutableStateOf("") }

    val allCurrencies = listOf(
        "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN", "BAM",
        "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BOV", "BRL", "BSD",
        "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHE", "CHF", "CHW", "CLF", "CLP",
        "CNY", "COP", "COU", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP",
        "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GHS", "GIP",
        "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR", "ILS",
        "INR", "IQD", "IRR", "ISK", "JMD", "JOD", "JPY", "KES", "KGS", "KHR", "KMF",
        "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LRD", "LSL", "LYD",
        "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK",
        "MXN", "MXV", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR",
        "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB",
        "RWF", "SAR", "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SLL", "SOS",
        "SRD", "SSP", "STN", "SVC", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP",
        "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "USN", "UYW", "UYI", "UYU",
        "UZS", "VED", "VES", "VND", "VUV", "WST", "XAF", "XAG", "XAU", "XBA", "XBB",
        "XBC", "XBD", "XCD", "XDR", "XOF", "XPD", "XPF", "XPT", "XSU", "XTS", "XUA",
        "XXX", "YER", "ZAR", "ZMW", "ZWL"
    )

    // TODO: fetch this list from remote config, {"currency": "USD", "symbol": "$"} in this format.

    val filteredCurrencies = allCurrencies.filter {
        it.contains(searchQuery, ignoreCase = true)
    }

    TopBarWrapper(
        showBackButton = true,
        onBackClick = { navController.popBackStack() },
        title = "",
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {
            CustomText(
                text = "Let's allocate some money",
                fontWeight = FontWeight.Bold,
                fontSize = 24.dp,
                color = PeblColors.textColor,
                letterSpacing = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.CenterStart)
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF3E3D53), RoundedCornerShape(12.dp))
            ) {
                Column(
                    modifier = Modifier
                        .menuAnchor()
                        .padding(14.dp)
                        .fillMaxWidth()
                ) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomText(
                            text = "Selected currency",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.dp,
                            color = PeblColors.textColor,
                        )
                        CustomText(
                            text = selectedCurrency,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.dp,
                            color = PeblColors.textColor,
                        )
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .height(400.dp)
                        .padding(top = 8.dp)
                        .background(Color(0xFF1E1E1E))
                ) {
                    CustomOutlinedTextField(
                        textFieldValue = searchQuery,
                        onValueChange = { searchQuery = it },
                        labelText1 = "Search currency",
                        labelText2 = "Currency",
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    )

                    filteredCurrencies.forEach { currency ->
                        DropdownMenuItem(
                            text = {
                                CustomText(
                                    text = currency,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 14.dp,
                                    color = PeblColors.textColor,
                                )
                            },
                            onClick = {
                                selectedCurrency = currency
                                expanded = false
                                searchQuery = ""
                            }
                        )
                    }
                }
            }

            selectedCategories.forEach { entity ->
                CustomText(
                text = entity.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.dp,
                color = PeblColors.textColor,
                letterSpacing = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.CenterStart)
            )
            }
        }
    }
}
