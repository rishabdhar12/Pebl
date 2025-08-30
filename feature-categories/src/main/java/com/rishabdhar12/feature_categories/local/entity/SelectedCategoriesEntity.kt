package com.rishabdhar12.feature_categories.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "categories")
data class SelectedCategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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