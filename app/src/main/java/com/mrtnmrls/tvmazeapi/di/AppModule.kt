package com.mrtnmrls.tvmazeapi.di

import android.content.Context
import androidx.room.Room
import com.mrtnmrls.tvmazeapi.data.db.AppDatabase
import com.mrtnmrls.tvmazeapi.data.db.CurrentTvScheduleDao
import com.mrtnmrls.tvmazeapi.data.db.TableUpdateDao
import com.mrtnmrls.tvmazeapi.data.network.ApiService
import com.mrtnmrls.tvmazeapi.data.repository.CurrentTvScheduleLocalRepository
import com.mrtnmrls.tvmazeapi.data.repository.TelevisionScheduleRepositoryImpl
import com.mrtnmrls.tvmazeapi.domain.repository.TelevisionScheduleRepository
import com.mrtnmrls.tvmazeapi.domain.usecase.GetCurrentTvScheduleUseCase
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

    private const val BASE_URL = "https://api.tvmaze.com/"

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "tv-maze-api"
        ).build()
    }

    @Provides
    fun provideCurrentTvScheduleLocal(db: AppDatabase): CurrentTvScheduleDao {
        return db.currentTvScheduleDao()
    }

    @Provides
    fun provideTableUpdate(db: AppDatabase): TableUpdateDao {
        return db.tableUpdateDao()
    }

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
    fun provideGetCurrentTvScheduleUseCase(
        televisionScheduleRepository: TelevisionScheduleRepository
    ): GetCurrentTvScheduleUseCase {
        return GetCurrentTvScheduleUseCase(televisionScheduleRepository)
    }
}