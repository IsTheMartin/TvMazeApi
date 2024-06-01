package com.mrtnmrls.tvmazeapi.data.model.remote


import com.google.gson.annotations.SerializedName

data class Previousepisode(
    @SerializedName("href")
    val href: String,
    @SerializedName("name")
    val name: String
)