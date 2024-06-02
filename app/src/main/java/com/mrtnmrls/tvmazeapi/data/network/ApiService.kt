package com.mrtnmrls.tvmazeapi.data.network

import com.mrtnmrls.tvmazeapi.data.model.remote.ShowResponse
import com.mrtnmrls.tvmazeapi.data.model.remote.TvScheduleResponse
import com.mrtnmrls.tvmazeapi.data.model.remote.TvShowQueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("schedule")
    suspend fun getCurrentTelevisionSchedule(): Response<TvScheduleResponse>

    @GET("shows/{showId}")
    suspend fun getShowById(@Path("showId") showId: Int): Response<ShowResponse>

    @GET("search/shows")
    suspend fun getShowByName(@Query("q") showName: String): Response<TvShowQueryResponse>
}