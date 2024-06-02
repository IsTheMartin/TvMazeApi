package com.mrtnmrls.tvmazeapi.data.mapper

import com.mrtnmrls.tvmazeapi.data.model.local.TvShowEntity
import com.mrtnmrls.tvmazeapi.data.model.remote.ShowResponse
import com.mrtnmrls.tvmazeapi.domain.model.TvShow

fun ShowResponse.toDomain() = TvShow(
    id = id,
    url = url,
    name = name,
    type = type,
    language = language,
    runtime = runtime,
    officialSite = officialSite,
    mediumImageUrl = image?.medium.orEmpty(),
    originalImageUrl = image?.original.orEmpty(),
    summary = summary,
    imdb = externals.imdb,
)

fun ShowResponse.toEntity() = TvShowEntity(
    id = id,
    url = url,
    name = name,
    type = type,
    language = language,
    runtime = runtime,
    officialSite = officialSite,
    mediumImageUrl = image?.medium.orEmpty(),
    originalImageUrl = image?.original.orEmpty(),
    summary = summary,
    imdb = externals.imdb,
)

fun TvShowEntity.toDomain() = TvShow(
    id = id,
    url = url,
    name = name,
    type = type,
    language = language,
    runtime = runtime,
    officialSite = officialSite,
    mediumImageUrl = mediumImageUrl,
    originalImageUrl = originalImageUrl,
    summary = summary,
    imdb = imdb,
)