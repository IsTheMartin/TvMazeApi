package com.mrtnmrls.tvmazeapi.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show")
data class TvShowEntity(
    @PrimaryKey val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val language: String?,
    val runtime: Int,
    val officialSite: String?,
    val mediumImageUrl: String?,
    val originalImageUrl: String?,
    val summary: String,
    val imdb: String?
)