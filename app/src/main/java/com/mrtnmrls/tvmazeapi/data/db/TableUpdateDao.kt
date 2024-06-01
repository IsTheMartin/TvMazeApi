package com.mrtnmrls.tvmazeapi.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrtnmrls.tvmazeapi.data.model.local.TableUpdateEntity

@Dao
interface TableUpdateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpdate(tableUpdate: TableUpdateEntity)

    @Query("DELETE FROM table_update")
    suspend fun deleteAllUpdates()

    @Query("SELECT * FROM table_update WHERE tableName = :tableName")
    suspend fun getUpdateByTableName(tableName: String): TableUpdateEntity?

    @Query("DELETE FROM table_update WHERE tableName = :tableName")
    suspend fun deleteUpdateById(tableName: String)

}