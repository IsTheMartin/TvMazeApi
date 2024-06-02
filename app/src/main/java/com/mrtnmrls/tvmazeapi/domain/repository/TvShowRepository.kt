package com.mrtnmrls.tvmazeapi.domain.repository

import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.TvShow

interface TvShowRepository {

    suspend fun getShowById(showId: Int): NetworkResultState<TvShow>

}