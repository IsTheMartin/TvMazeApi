package com.mrtnmrls.tvmazeapi.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_update")
data class TableUpdateEntity(
    @PrimaryKey val tableName: String,
    val lastUpdate: Long
)
