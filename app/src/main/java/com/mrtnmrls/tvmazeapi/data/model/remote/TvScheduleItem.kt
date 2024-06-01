package com.mrtnmrls.tvmazeapi.data.model.remote


import com.google.gson.annotations.SerializedName

data class TvScheduleItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("season")
    val season: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("airdate")
    val airdate: String,
    @SerializedName("airtime")
    val airtime: String,
    @SerializedName("airstamp")
    val airstamp: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("image")
    val image: Image,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("show")
    val show: Show,
    @SerializedName("_links")
    val links: LinksX
)