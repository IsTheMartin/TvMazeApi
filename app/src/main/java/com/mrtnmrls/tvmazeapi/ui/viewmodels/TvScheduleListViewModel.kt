package com.mrtnmrls.tvmazeapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrtnmrls.tvmazeapi.domain.usecase.GetCurrentTvScheduleUseCase
import com.mrtnmrls.tvmazeapi.domain.usecase.GetTvShowByIdUseCase
import com.mrtnmrls.tvmazeapi.ui.UiState
import com.mrtnmrls.tvmazeapi.ui.actions.ScheduleListAction
import com.mrtnmrls.tvmazeapi.ui.actions.ScheduleListAction.OnTvShowClicked
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvScheduleListViewModel @Inject constructor(
    private val getCurrentTvScheduleUseCase: GetCurrentTvScheduleUseCase,
    private val getTvShowByIdUseCase: GetTvShowByIdUseCase
): ViewModel() {

    var _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getCurrentTvSchedule()
    }

    fun dispatchActions(action: ScheduleListAction) {
        when(action) {
            is OnTvShowClicked -> getTvShowById(action.showId)
            else -> {}
        }
    }

    private fun getCurrentTvSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            val scheduleResult = getCurrentTvScheduleUseCase()
            _uiState.update { it.copy(currentTvScheduleState = scheduleResult) }
        }
    }

    private fun getTvShowById(showId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val showResult = getTvShowByIdUseCase.invoke(showId)
            _uiState.update { it.copy(tvShowState = showResult, showIdSelected = showId) }
        }
    }

}