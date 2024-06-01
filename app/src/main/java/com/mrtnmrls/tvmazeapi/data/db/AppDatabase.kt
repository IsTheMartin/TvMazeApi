package com.mrtnmrls.tvmazeapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrtnmrls.tvmazeapi.data.model.local.CurrentTvScheduleEntity
import com.mrtnmrls.tvmazeapi.data.model.local.TableUpdateEntity

@Database(entities = [CurrentTvScheduleEntity::class, TableUpdateEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currentTvScheduleDao(): CurrentTvScheduleDao
    abstract fun tableUpdateDao(): TableUpdateDao
}