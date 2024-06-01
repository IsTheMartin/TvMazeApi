package com.mrtnmrls.tvmazeapi.data.repository

import com.mrtnmrls.tvmazeapi.data.mapper.toDomain
import com.mrtnmrls.tvmazeapi.data.mapper.toEntity
import com.mrtnmrls.tvmazeapi.data.model.remote.TvScheduleResponse
import com.mrtnmrls.tvmazeapi.data.network.ApiService
import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.CurrentTvSchedule
import com.mrtnmrls.tvmazeapi.domain.repository.TelevisionScheduleRepository
import javax.inject.Inject


class TelevisionScheduleRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val local: CurrentTvScheduleLocalRepository
): TelevisionScheduleRepository {

    override suspend fun getCurrentSchedule(): NetworkResultState<List<CurrentTvSchedule>> {
        val localSchedules = local.getAllSchedules()
        println("MRTN > should update by time? ${local.shouldUpdateCurrentTvScheduleTable()}")
        if (localSchedules.isEmpty() || local.shouldUpdateCurrentTvScheduleTable()) {
            return getSchedulesFromService()
        }
        return NetworkResultState.Success(localSchedules.map { it.toDomain() })
    }

    private suspend fun getSchedulesFromService(): NetworkResultState<List<CurrentTvSchedule>> {
        try {
            val tvScheduleResponse = apiService.getCurrentTelevisionSchedule()
            val bodyResponse = tvScheduleResponse.body()
            if (tvScheduleResponse.isSuccessful.not()) {
                return NetworkResultState.Error(tvScheduleResponse.code(), tvScheduleResponse.errorBody()?.string())
            }
            if (bodyResponse.isNullOrEmpty()) {
                return NetworkResultState.Error(tvScheduleResponse.code(), "Empty response")
            }
            insertSchedules(bodyResponse)
            return NetworkResultState.Success(getSchedules())
        } catch (e: Exception) {
            return NetworkResultState.Error(0, e.message)
        }
    }

    private suspend fun insertSchedules(bodyResponse: TvScheduleResponse) {
        if (local.shouldUpdateCurrentTvScheduleTable()) {
            val entities = bodyResponse.map { it.toEntity() }
            local.replaceSchedules(entities)
            local.insertTableUpdate()
        }
    }

    private suspend fun getSchedules(): List<CurrentTvSchedule> {
        return local.getAllSchedules().map { it.toDomain() }
    }
}