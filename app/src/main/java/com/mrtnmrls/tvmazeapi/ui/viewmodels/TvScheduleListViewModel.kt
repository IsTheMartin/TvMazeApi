package com.mrtnmrls.tvmazeapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrtnmrls.tvmazeapi.domain.usecase.GetCurrentTvScheduleUseCase
import com.mrtnmrls.tvmazeapi.ui.ScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvScheduleListViewModel @Inject constructor(
    private val getCurrentTvScheduleUseCase: GetCurrentTvScheduleUseCase
): ViewModel() {

    var _uiState = MutableStateFlow(ScheduleState())
    val uiState = _uiState.asStateFlow()

    init {
        getCurrentTvSchedule()
    }

    private fun getCurrentTvSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            val scheduleResult = getCurrentTvScheduleUseCase()
            _uiState.update { it.copy(resultState = scheduleResult) }
        }
    }

}