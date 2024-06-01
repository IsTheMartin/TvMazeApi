package com.mrtnmrls.tvmazeapi.data.model.remote


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("average")
    val average: Any
)