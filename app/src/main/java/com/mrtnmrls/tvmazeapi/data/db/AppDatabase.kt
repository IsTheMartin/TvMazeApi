package com.mrtnmrls.tvmazeapi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mrtnmrls.tvmazeapi.data.model.local.CurrentTvScheduleEntity
import com.mrtnmrls.tvmazeapi.data.model.local.TableUpdateEntity
import com.mrtnmrls.tvmazeapi.data.model.local.TvShowEntity

@Database(
    entities = [
        CurrentTvScheduleEntity::class,
        TableUpdateEntity::class,
        TvShowEntity::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currentTvScheduleDao(): CurrentTvScheduleDao
    abstract fun tableUpdateDao(): TableUpdateDao
    abstract fun tvShowDao(): TvShowDao
}