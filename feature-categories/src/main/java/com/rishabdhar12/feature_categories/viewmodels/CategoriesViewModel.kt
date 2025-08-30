package com.rishabdhar12.feature_categories.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rishabdhar12.feature_categories.local.entity.SelectedCategoryEntity
import com.rishabdhar12.feature_categories.models.Categories
import com.rishabdhar12.feature_categories.models.SelectedCategoriesModel
import com.rishabdhar12.feature_categories.repo.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
): ViewModel() {

    private val _categoriesModel = MutableStateFlow<Categories?>(null)
    val categoriesModel: StateFlow<Categories?> = _categoriesModel

    private val _selectedCategoriesList = mutableStateListOf<SelectedCategoryEntity?>(null)
    val selectedCategoriesList: List<SelectedCategoryEntity?> get() = _selectedCategoriesList

    private val _educationList = mutableStateListOf<String>()
    val educationList: List<String> get() = _educationList

    private val _housingList = mutableStateListOf<String>()
    val housingList: List<String> get() = _housingList

    private val _foodList = mutableStateListOf<String>()
    val foodList: List<String> get() = _foodList

    private val _transportationList = mutableStateListOf<String>()
    val transportationList: List<String> get() = _transportationList

    private val _healthList = mutableStateListOf<String>()
    val healthList: List<String> get() = _healthList

    private val _entertainmentList = mutableStateListOf<String>()
    val entertainmentList: List<String> get() = _entertainmentList

    private val _personalCareList = mutableStateListOf<String>()
    val personalCareList: List<String> get() = _personalCareList

    private val _financialList = mutableStateListOf<String>()
    val financialList: List<String> get() = _financialList

    private val _giftsList = mutableStateListOf<String>()
    val giftsList: List<String> get() = _giftsList

    private val _miscList = mutableStateListOf<String>()
    val miscList: List<String> get() = _miscList

    fun getCategoriesFromRemote() {
        viewModelScope.launch {
            val isSuccessFul = categoriesRepository.fetchAndActivate()
            if(isSuccessFul) {
                val result = categoriesRepository.getCategoriesFromRemoteConfig()
                result.fold(
                    onSuccess = {jsonString ->
                        _selectedCategoriesList.clear()
                        val categories = Gson().fromJson(jsonString.toString(), Categories::class.java)
                        _categoriesModel.value = categories
                    },
                    onFailure = { e ->
                        Log.i("Remote config categories: ", e.localizedMessage)
                    }
                )
            }else {
                Log.i("Remote config categories: ", "Failed to fetch categories")
            }
        }
    }

    fun addEducation(value: String) {
        toggleItem(_educationList, value, category  = "Education")
    }

    fun addHousing(value: String) {
        toggleItem(_housingList, value, category = "Housing")
    }

    fun addFood(value: String) {
        toggleItem(_foodList, value, category = "Food")
    }

    fun addTransportation(value: String) {
        toggleItem(_transportationList, value, category = "Transportation")
    }

    fun addHealth(value: String) {
        toggleItem(_healthList, value, category = "Health")
    }

    fun addEntertainment(value: String) {
        toggleItem(_entertainmentList, value, category = "Entertainment")
    }

    fun addPersonalCare(value: String) {
        toggleItem(_personalCareList, value, category = "Personal Care")
    }

    fun addFinancial(value: String) {
        toggleItem(_financialList, value, category = "Financial")
    }

    fun addGifts(value: String) {
        toggleItem(_giftsList, value, category = "Gifts Donations")
    }

    fun addMisc(value: String) {
        toggleItem(_miscList, value, category = "Miscellaneous")
    }

    @SuppressLint("NewApi")
    private fun toggleItem(list: MutableList<String>, value: String, category: String) {
        if (!list.contains(value)) {
            list.add(value)

            _selectedCategoriesList.add(
                SelectedCategoryEntity(
                    name = value,
                    amountLeft = 0.00,
                    budget = 0.00,
                    categoryType = category,
                    frequency = "Monthly",
                    reset = 0,
                    startDate = LocalDate.now().toString(),
                    totalDeducted = 0.00,
                    txnHistory = mutableListOf<Int>(),
                )
            )

        } else {
            list.remove(value)

            _selectedCategoriesList.remove(
                _selectedCategoriesList.find { it?.name == value }
            )
        }
    }
}

// TODO: move to the next page with the model and add values to the list
//  insert the categories to room and fix the ui from the screenshot.