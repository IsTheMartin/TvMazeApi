package com.mrtnmrls.tvmazeapi.data.model.remote


import com.google.gson.annotations.SerializedName

data class LinksX(
    @SerializedName("self")
    val self: Self,
    @SerializedName("show")
    val show: ShowX
)