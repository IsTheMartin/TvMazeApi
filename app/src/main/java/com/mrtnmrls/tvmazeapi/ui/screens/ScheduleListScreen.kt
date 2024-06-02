package com.mrtnmrls.tvmazeapi.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.TvShow
import com.mrtnmrls.tvmazeapi.ui.actions.ScheduleListAction
import com.mrtnmrls.tvmazeapi.ui.states.UiState
import com.mrtnmrls.tvmazeapi.ui.views.CurrentTvScheduleView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleListScreen(
    uiState: UiState,
    onAction: (ScheduleListAction) -> Unit
) {
    Scaffold(
        topBar = {
            SearchBar(
                query = uiState.searchText,
                onQueryChange = { onAction(ScheduleListAction.OnSearchTextChanged(it)) },
                onSearch = { onAction(ScheduleListAction.OnSearchTextChanged(it)) },
                active = uiState.isSearching,
                onActiveChange = { onAction(ScheduleListAction.OnToggleSearch) }
            ) {
                when (val tvShowsResult = uiState.tvShowsQueryResult) {
                    is NetworkResultState.Error -> EmptyDataView(message = tvShowsResult.message)
                    is NetworkResultState.Loading -> LoadingView()
                    is NetworkResultState.Success -> TvShowsResult(tvShows = tvShowsResult.data) {
                        onAction(ScheduleListAction.OnTvShowClicked(it))
                    }
                }

            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val scheduleResponse = uiState.currentTvScheduleState) {
                is NetworkResultState.Error -> EmptyDataView(
                    modifier = Modifier,
                    message = scheduleResponse.message
                )

                is NetworkResultState.Loading -> LoadingView()
                is NetworkResultState.Success -> CurrentTvScheduleView(scheduleResponse.data) {
                    onAction(it)
                }
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

@Composable
fun TvShowsResult(tvShows: List<TvShow>, onItemClicked: (Int) -> Unit) {
    LazyColumn {
        items(tvShows) { tvShow ->
            Column(
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        top = 4.dp,
                        end = 8.dp,
                        bottom = 4.dp
                    )
                    .fillMaxWidth()
                    .clickable { onItemClicked(tvShow.id) }
            ) {
                Text(text = tvShow.name)
                Text(text = tvShow.id.toString())
            }

        }
    }
}