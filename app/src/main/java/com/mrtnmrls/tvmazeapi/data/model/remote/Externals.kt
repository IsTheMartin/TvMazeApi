package com.mrtnmrls.tvmazeapi.data.model.remote


import com.google.gson.annotations.SerializedName

data class Externals(
    @SerializedName("tvrage")
    val tvrage: Int,
    @SerializedName("thetvdb")
    val thetvdb: Int,
    @SerializedName("imdb")
    val imdb: String
)