package com.mrtnmrls.tvmazeapi.ui

import com.mrtnmrls.tvmazeapi.data.model.remote.TvScheduleItem
import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.CurrentTvSchedule

data class ScheduleState(
    val resultState: NetworkResultState<List<CurrentTvSchedule>> = NetworkResultState.Loading()
)