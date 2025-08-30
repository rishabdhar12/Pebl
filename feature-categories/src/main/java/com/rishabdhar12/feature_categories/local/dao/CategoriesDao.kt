package com.rishabdhar12.feature_categories.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rishabdhar12.feature_categories.local.entity.SelectedCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<SelectedCategoryEntity>)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<SelectedCategoryEntity>>
}
