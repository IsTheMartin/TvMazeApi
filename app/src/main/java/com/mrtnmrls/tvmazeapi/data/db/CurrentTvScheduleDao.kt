package com.mrtnmrls.tvmazeapi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mrtnmrls.tvmazeapi.data.model.local.CurrentTvScheduleEntity

@Dao
interface CurrentTvScheduleDao {

    @Insert
    suspend fun insertSchedules(currentTvScheduleEntities: List<CurrentTvScheduleEntity>)

    @Query("DELETE FROM current_tv_schedule")
    suspend fun deleteAllSchedules()

    @Query("SELECT * FROM current_tv_schedule")
    suspend fun getAllSchedules(): List<CurrentTvScheduleEntity>
}