package com.mrtnmrls.tvmazeapi.data.repository

import com.mrtnmrls.tvmazeapi.data.db.AppDatabase
import com.mrtnmrls.tvmazeapi.data.db.CurrentTvScheduleDao
import com.mrtnmrls.tvmazeapi.data.db.TableUpdateDao
import com.mrtnmrls.tvmazeapi.data.model.local.CurrentTvScheduleEntity
import com.mrtnmrls.tvmazeapi.data.model.local.TableUpdateEntity
import javax.inject.Inject

class CurrentTvScheduleLocalRepository @Inject constructor(
    private val currentTvScheduleDao: CurrentTvScheduleDao,
    private val tableUpdateDao: TableUpdateDao
) {

    suspend fun getAllSchedules(): List<CurrentTvScheduleEntity> {
        return currentTvScheduleDao.getAllSchedules()
    }

    suspend fun replaceSchedules(scheduleEntities: List<CurrentTvScheduleEntity>) {
        currentTvScheduleDao.deleteAllSchedules()
        currentTvScheduleDao.insertSchedules(scheduleEntities)
    }

    suspend fun insertTableUpdate() {
        val currentTime = System.currentTimeMillis()
        tableUpdateDao.deleteUpdateById(CURRENT_TV_SCHEDULE_TABLE)
        tableUpdateDao.insertUpdate(TableUpdateEntity(CURRENT_TV_SCHEDULE_TABLE, currentTime))
    }

    suspend fun shouldUpdateCurrentTvScheduleTable(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdate = tableUpdateDao.getUpdateByTableName(CURRENT_TV_SCHEDULE_TABLE)?.lastUpdate ?: 0L
        if (lastUpdate == 0L) {
            return true
        }
        return lastUpdate + TIME_INTERVAL_FOR_UPDATES < currentTime
    }

    companion object {
        private const val TIME_INTERVAL_FOR_UPDATES = 1000 * 60 * 10L // 10 minutes
        private const val CURRENT_TV_SCHEDULE_TABLE = "currentTvSchedule"
    }

}