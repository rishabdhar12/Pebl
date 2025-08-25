package com.rishabdhar12.pebl.room_db_service

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rishabdhar12.feature_auth.local.dao.UserDao
import com.rishabdhar12.feature_auth.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}