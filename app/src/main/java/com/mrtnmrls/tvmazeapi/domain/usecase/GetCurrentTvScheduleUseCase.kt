package com.mrtnmrls.tvmazeapi.domain.usecase

import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.CurrentTvSchedule
import com.mrtnmrls.tvmazeapi.domain.repository.TelevisionScheduleRepository
import javax.inject.Inject

class GetCurrentTvScheduleUseCase @Inject constructor(
    private val tvScheduleRepository: TelevisionScheduleRepository
) {

    suspend operator fun invoke(): NetworkResultState<List<CurrentTvSchedule>> {
        return tvScheduleRepository.getCurrentSchedule()
    }

}