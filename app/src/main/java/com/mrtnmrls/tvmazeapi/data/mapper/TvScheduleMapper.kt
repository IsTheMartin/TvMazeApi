package com.mrtnmrls.tvmazeapi.data.mapper

import com.mrtnmrls.tvmazeapi.data.model.local.CurrentTvScheduleEntity
import com.mrtnmrls.tvmazeapi.data.model.remote.TvScheduleItem
import com.mrtnmrls.tvmazeapi.domain.model.CurrentTvSchedule

fun TvScheduleItem.toDomain() = CurrentTvSchedule(
    showId = show.id,
    showName = show.name,
    status = show.status,
    type = show.type,
    language = show.language.orEmpty(),
    originalImageUrl = show.image?.original.orEmpty(),
    mediumImageUrl = show.image?.medium.orEmpty(),
    episodeId = id,
    episodeName = name,
    airtime = airtime,
    runtime = runtime
)

fun TvScheduleItem.toEntity() = CurrentTvScheduleEntity(
    showId = show.id,
    showName = show.name,
    status = show.status,
    type = show.type,
    language = show.language.orEmpty(),
    originalImageUrl = show.image?.original.orEmpty(),
    mediumImageUrl = show.image?.medium.orEmpty(),
    episodeId = id,
    episodeName = name,
    airtime = airtime,
    runtime = runtime
)

fun CurrentTvScheduleEntity.toDomain() = CurrentTvSchedule(
    showId = showId,
    showName = showName,
    status = status,
    type = type,
    language = language,
    originalImageUrl = originalImageUrl,
    mediumImageUrl = mediumImageUrl,
    episodeId = episodeId,
    episodeName = episodeName,
    airtime = airtime,
    runtime = runtime
)