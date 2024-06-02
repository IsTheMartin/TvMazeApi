package com.mrtnmrls.tvmazeapi.ui.states

import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.CurrentTvSchedule
import com.mrtnmrls.tvmazeapi.domain.model.TvShow

data class UiState(
    val currentTvScheduleState: NetworkResultState<List<CurrentTvSchedule>> = NetworkResultState.Loading(),
    val showIdSelected: Int? = null,
    val tvShowState: NetworkResultState<TvShow> = NetworkResultState.Loading(),
    val isSearching: Boolean = false,
    val searchText: String = "",
    val tvShowsQueryResult: NetworkResultState<List<TvShow>> = NetworkResultState.Loading()
)