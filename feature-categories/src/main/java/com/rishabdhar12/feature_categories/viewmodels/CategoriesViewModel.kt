package com.rishabdhar12.feature_categories.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rishabdhar12.feature_categories.models.Categories
import com.rishabdhar12.feature_categories.repo.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
): ViewModel() {

    private val _categoriesModel = MutableStateFlow<Categories?>(null)
    val categoriesModel: StateFlow<Categories?> = _categoriesModel

    private val _educationList = mutableStateListOf<String>()
    val educationList: List<String> get() = _educationList

    fun getCategoriesFromRemote() {
        viewModelScope.launch {
            val isSuccessFul = categoriesRepository.fetchAndActivate()
            if(isSuccessFul) {
                val result = categoriesRepository.getCategoriesFromRemoteConfig()
                result.fold(
                    onSuccess = {jsonString ->
//                        Log.i("Remote config categories: ", result.toString())

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
        if(!_educationList.contains(value)) {
            _educationList.add(value)
        } else {
            _educationList.remove(value)
        }
    }
}