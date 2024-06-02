package com.mrtnmrls.tvmazeapi.di

import android.content.Context
import androidx.room.Room
import com.mrtnmrls.tvmazeapi.data.db.AppDatabase
import com.mrtnmrls.tvmazeapi.data.db.CurrentTvScheduleDao
import com.mrtnmrls.tvmazeapi.data.db.TableUpdateDao
import com.mrtnmrls.tvmazeapi.data.db.TvShowDao
import com.mrtnmrls.tvmazeapi.data.network.ApiService
import com.mrtnmrls.tvmazeapi.data.repository.CurrentTvScheduleLocalRepository
import com.mrtnmrls.tvmazeapi.data.repository.TelevisionScheduleRepositoryImpl
import com.mrtnmrls.tvmazeapi.data.repository.TvShowRepositoryImpl
import com.mrtnmrls.tvmazeapi.domain.repository.TelevisionScheduleRepository
import com.mrtnmrls.tvmazeapi.domain.repository.TvShowRepository
import com.mrtnmrls.tvmazeapi.domain.usecase.GetCurrentTvScheduleUseCase
import com.mrtnmrls.tvmazeapi.domain.usecase.GetTvShowByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTvScheduleRepository(
        apiService: ApiService,
        local: CurrentTvScheduleLocalRepository
    ): TelevisionScheduleRepository {
        return TelevisionScheduleRepositoryImpl(apiService, local)
    }

    @Provides
    @Singleton
    fun provideTvShowRepository(
        apiService: ApiService,
        tvShowDao: TvShowDao
    ): TvShowRepository {
        return TvShowRepositoryImpl(apiService, tvShowDao)
    }

    @Provides
    @Singleton
    fun provideGetCurrentTvScheduleUseCase(
        televisionScheduleRepository: TelevisionScheduleRepository
    ): GetCurrentTvScheduleUseCase {
        return GetCurrentTvScheduleUseCase(televisionScheduleRepository)
    }

    @Provides
    @Singleton
    fun provideTvShowUseCase(
        tvShowRepository: TvShowRepository
    ): GetTvShowByIdUseCase {
        return GetTvShowByIdUseCase(tvShowRepository)
    }
}