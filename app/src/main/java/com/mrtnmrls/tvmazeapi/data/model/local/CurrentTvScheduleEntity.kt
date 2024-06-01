package com.mrtnmrls.tvmazeapi.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_tv_schedule")
data class CurrentTvScheduleEntity(
    val showId: Int,
    val showName: String,
    val status: String,
    val type: String,
    val language: String,
    val originalImageUrl: String,
    val mediumImageUrl: String,
    @PrimaryKey val episodeId: Int,
    val episodeName: String,
    val airtime: String,
    val runtime: Int,
)