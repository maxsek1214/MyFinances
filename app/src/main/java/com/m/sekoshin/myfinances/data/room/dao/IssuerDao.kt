package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*
import com.m.sekoshin.myfinances.data.room.entity.IssuerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IssuerDao : EntityDao<IssuerEntity> {
    @Query("SELECT * FROM issuers_table")
    fun getIssuers(): Flow<List<IssuerEntity>>

    @Query("SELECT * FROM issuers_table WHERE id == :id")
    fun getIssuer(id: Int): Flow<IssuerEntity>
}