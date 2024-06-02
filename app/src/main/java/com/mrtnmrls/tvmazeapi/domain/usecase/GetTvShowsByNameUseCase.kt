package com.mrtnmrls.tvmazeapi.domain.usecase

import com.mrtnmrls.tvmazeapi.data.network.NetworkResultState
import com.mrtnmrls.tvmazeapi.domain.model.TvShow
import com.mrtnmrls.tvmazeapi.domain.repository.TvShowRepository
import javax.inject.Inject

class GetTvShowsByNameUseCase @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {

    suspend operator fun invoke(name: String): NetworkResultState<List<TvShow>> {
        return tvShowRepository.getShowByName(name)
    }

}