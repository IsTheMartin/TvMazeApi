package com.mrtnmrls.tvmazeapi.domain.repository

import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.CurrentTvSchedule

interface TelevisionScheduleRepository {
    suspend fun getCurrentSchedule(): NetworkResultState<List<CurrentTvSchedule>>
}