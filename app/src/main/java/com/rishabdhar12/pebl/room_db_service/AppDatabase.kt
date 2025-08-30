package com.rishabdhar12.pebl.room_db_service

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rishabdhar12.feature_auth.local.dao.UserDao
import com.rishabdhar12.feature_auth.local.entity.UserEntity
import com.rishabdhar12.feature_categories.local.dao.CategoriesDao
import com.rishabdhar12.feature_categories.local.entity.SelectedCategoryEntity
import com.rishabdhar12.utils.Converters

@Database(entities = [UserEntity::class, SelectedCategoryEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun categoriesDao(): CategoriesDao
}