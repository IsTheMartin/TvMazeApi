package com.mrtnmrls.tvmazeapi.di

import android.content.Context
import androidx.room.Room
import com.mrtnmrls.tvmazeapi.data.db.AppDatabase
import com.mrtnmrls.tvmazeapi.data.db.CurrentTvScheduleDao
import com.mrtnmrls.tvmazeapi.data.db.TableUpdateDao
import com.mrtnmrls.tvmazeapi.data.db.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

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
    fun provideTvShow(db: AppDatabase): TvShowDao {
        return db.tvShowDao()
    }

}