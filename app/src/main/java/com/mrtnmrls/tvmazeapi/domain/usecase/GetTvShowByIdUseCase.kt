package com.mrtnmrls.tvmazeapi.domain.usecase

import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.TvShow
import com.mrtnmrls.tvmazeapi.domain.repository.TvShowRepository
import javax.inject.Inject

class GetTvShowByIdUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {

    suspend operator fun invoke(showId: Int): NetworkResultState<TvShow> {
        return tvShowRepository.getShowById(showId)
    }

}