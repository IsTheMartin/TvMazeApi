package com.mrtnmrls.tvmazeapi.data.model.remote


import com.google.gson.annotations.SerializedName

data class TvShowQueryResponseItem(
    @SerializedName("score")
    val score: Double,
    @SerializedName("show")
    val show: Show
)