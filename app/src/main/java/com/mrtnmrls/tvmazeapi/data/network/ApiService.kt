package com.mrtnmrls.tvmazeapi.data.network

import com.mrtnmrls.tvmazeapi.data.model.remote.TvScheduleResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("schedule")
    suspend fun getCurrentTelevisionSchedule(): Response<TvScheduleResponse>
}