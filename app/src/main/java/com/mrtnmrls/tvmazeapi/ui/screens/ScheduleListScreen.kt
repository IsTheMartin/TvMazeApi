package com.mrtnmrls.tvmazeapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.CurrentTvSchedule
import com.mrtnmrls.tvmazeapi.ui.views.CurrentTvScheduleView

@Composable
fun ScheduleListScreen(
    scheduleResponse: NetworkResultState<List<CurrentTvSchedule>>
) {
    Scaffold { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (scheduleResponse) {
                is NetworkResultState.Error -> EmptyDataView(
                    modifier = Modifier,
                    message = scheduleResponse.message
                )
                is NetworkResultState.Loading -> LoadingView()
                is NetworkResultState.Success -> CurrentTvScheduleView(scheduleResponse.data)
            }
        }
    }
}

@Composable
internal fun EmptyDataView(modifier: Modifier = Modifier, message: String?) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(message.orEmpty())
    }
}

@Composable
internal fun LoadingView(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Loading ...")
    }
}