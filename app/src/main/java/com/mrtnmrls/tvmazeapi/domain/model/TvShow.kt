package com.mrtnmrls.tvmazeapi.domain.model

data class TvShow(
    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String?,
    val runtime: Int,
    val officialSite: String?,
    val mediumImageUrl: String?,
    val originalImageUrl: String?,
    val summary: String,
    val imdb: String?,
)
