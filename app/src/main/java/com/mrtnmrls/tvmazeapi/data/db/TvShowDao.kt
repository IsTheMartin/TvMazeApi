package com.mrtnmrls.tvmazeapi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrtnmrls.tvmazeapi.data.model.local.TvShowEntity

@Dao
interface TvShowDao {

    @Query("SELECT * FROM tv_show WHERE id = :showId")
    fun getShowById(showId: Int): TvShowEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShow(show: TvShowEntity)

}