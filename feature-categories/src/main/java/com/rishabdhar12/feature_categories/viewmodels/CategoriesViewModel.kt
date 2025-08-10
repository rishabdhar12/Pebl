package com.rishabdhar12.feature_categories.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rishabdhar12.feature_categories.repo.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
): ViewModel() {

    fun getCategoriesFromRemote() {
        viewModelScope.launch {
            val result = categoriesRepository.getCategoriesFromRemoteConfig()

            result.fold(
                onSuccess = {
                    Log.i("Remote config categories: ", result.toString())
                },

                 onFailure = { e ->
                     Log.i("Remote config categories: ", e.localizedMessage)
                }
            )
        }
    }
}