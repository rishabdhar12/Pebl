package com.rishabdhar12.feature_categories.models

import com.rishabdhar12.feature_categories.local.entity.SelectedCategoryEntity
import java.time.LocalDate


data class SelectedCategoriesModel(
    val id: Int,
    val name: String,
    val categoryType: String,
    val budget: Double,
    val amountLeft: Double,
    val frequency: String,
    val reset: Int,
    val startDate: String,
    val totalDeducted: Double,
    val txnHistory: List<Int>
)

fun SelectedCategoryEntity.toModel() = SelectedCategoriesModel( id,
    name, categoryType, budget, amountLeft, frequency, reset, startDate, totalDeducted, txnHistory
)

fun SelectedCategoriesModel.toEntity() = SelectedCategoryEntity(
    id = id,
    name = name,
    categoryType = categoryType,
    budget = budget,
    amountLeft = amountLeft,
    frequency = frequency,
    reset = reset,
    startDate = startDate,
    totalDeducted = totalDeducted,
    txnHistory = txnHistory
)