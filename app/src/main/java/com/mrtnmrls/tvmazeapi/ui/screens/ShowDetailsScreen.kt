package com.mrtnmrls.tvmazeapi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.TvShow
import com.mrtnmrls.tvmazeapi.ui.theme.DarkBrown
import com.mrtnmrls.tvmazeapi.ui.views.TvShowDetailsView

@Composable
fun ShowDetailsScreen(tvShowResponse: NetworkResultState<TvShow>) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(DarkBrown)) {
        when (tvShowResponse) {
            is NetworkResultState.Error -> EmptyDataView(message = tvShowResponse.message)
            is NetworkResultState.Loading -> LoadingView()
            is NetworkResultState.Success -> TvShowDetailsView(tvShow = tvShowResponse.data)
        }
    }
}