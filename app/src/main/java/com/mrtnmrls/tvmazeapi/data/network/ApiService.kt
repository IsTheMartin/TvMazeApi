package com.mrtnmrls.tvmazeapi.data.network

import com.mrtnmrls.tvmazeapi.data.model.remote.ShowResponse
import com.mrtnmrls.tvmazeapi.data.model.remote.TvScheduleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("schedule")
    suspend fun getCurrentTelevisionSchedule(): Response<TvScheduleResponse>

    @GET("shows/{showId}")
    suspend fun getShowById(@Path("showId") showId: Int): Response<ShowResponse>
}