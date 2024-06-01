package com.mrtnmrls.tvmazeapi.domain.model

import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class CurrentTvSchedule(
    val showId: Int,
    val showName: String,
    val status: String,
    val type: String,
    val language: String,
    val originalImageUrl: String,
    val mediumImageUrl: String,
    val episodeId: Int,
    val episodeName: String,
    val airtime: String,
    val runtime: Int,
) {

    val scheduledTime: String get() {
        val time = LocalTime.parse(airtime, DateTimeFormatter.ofPattern("HH:mm"))
        val newTime = time.plusMinutes(runtime.toLong())
        return newTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    /*fun getScheduledTime(): String {
        val time = LocalTime.parse(airtime, DateTimeFormatter.ofPattern("HH:mm"))
        val newTime = time.plusMinutes(runtime.toLong())
        return newTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    }*/
}
