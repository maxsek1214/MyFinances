package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.m.sekoshin.myfinances.data.room.entity.CashiersCheckEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CashiersCheckDao : EntityDao<CashiersCheckEntity> {
    @Query("SELECT * FROM cashiersChecks_table")
    fun getCashiersChecks(): Flow<List<CashiersCheckEntity>>

    @Query("SELECT * FROM cashiersChecks_table WHERE id == :id")
    fun getCashiersCheck(id: Int): Flow<CashiersCheckEntity>
}