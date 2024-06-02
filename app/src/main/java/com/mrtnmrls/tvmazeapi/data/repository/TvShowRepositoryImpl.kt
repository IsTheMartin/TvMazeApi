package com.mrtnmrls.tvmazeapi.data.repository

import com.mrtnmrls.tvmazeapi.data.db.TvShowDao
import com.mrtnmrls.tvmazeapi.data.mapper.toDomain
import com.mrtnmrls.tvmazeapi.data.mapper.toEntity
import com.mrtnmrls.tvmazeapi.data.model.remote.ShowResponse
import com.mrtnmrls.tvmazeapi.data.network.ApiService
import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.TvShow
import com.mrtnmrls.tvmazeapi.domain.repository.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val tvShowDao: TvShowDao
) : TvShowRepository {

    override suspend fun getShowById(showId: Int): NetworkResultState<TvShow> {
        val tvShow = tvShowDao.getShowById(showId)
        if (tvShow == null) {
            return getTvShowFromService(showId)
        }
        return NetworkResultState.Success(tvShow.toDomain())
    }

    private suspend fun getTvShowFromService(showId: Int): NetworkResultState<TvShow> {
        try {
            val tvShowResponse = apiService.getShowById(showId)
            val bodyResponse = tvShowResponse.body()
            if (tvShowResponse.isSuccessful.not()) {
                return NetworkResultState.Error(tvShowResponse.code(), tvShowResponse.message())
            }
            if (bodyResponse == null) {
                return NetworkResultState.Error(tvShowResponse.code(), "Null response")
            }
            insertTvShow(bodyResponse)
            val show = getTvShowById(showId)
            if (show != null) {
                return NetworkResultState.Success(show)
            }
            return NetworkResultState.Error(0, "No show found in DB")
        } catch (e: Exception) {
            return NetworkResultState.Error(0, e.message)
        }
    }

    private fun getTvShowById(showId: Int): TvShow? {
        return tvShowDao.getShowById(showId)?.toDomain()
    }

    private suspend fun insertTvShow(bodyResponse: ShowResponse) {
        val entity = bodyResponse.toEntity()
        tvShowDao.insertShow(entity)
    }

}