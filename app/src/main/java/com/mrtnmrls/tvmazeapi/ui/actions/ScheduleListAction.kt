package com.mrtnmrls.tvmazeapi.ui.actions

sealed class ScheduleListAction {
    data class OnTvShowClicked(val showId: Int): ScheduleListAction()
}