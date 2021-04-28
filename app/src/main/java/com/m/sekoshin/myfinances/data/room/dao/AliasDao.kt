package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*
import com.m.sekoshin.myfinances.data.room.entity.AliasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AliasDao : EntityDao<AliasEntity> {
    @Query("SELECT * FROM aliases_table")
    fun getAliases(): Flow<List<AliasEntity>>
}