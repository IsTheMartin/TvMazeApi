package com.mrtnmrls.tvmazeapi.data.model.remote


import com.google.gson.annotations.SerializedName

data class ShowX(
    @SerializedName("href")
    val href: String,
    @SerializedName("name")
    val name: String
)