package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.m.sekoshin.myfinances.data.room.entity.CashTransaction
import com.m.sekoshin.myfinances.data.room.entity.CashTransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CashTransactionDao : EntityDao<CashTransactionEntity> {
    @Transaction
    @Query("SELECT * FROM cashTransactions_table")
    fun getCashTransactions(): Flow<List<CashTransaction>>

    @Transaction
    @Query("SELECT * FROM cashTransactions_table WHERE id == :id")
    fun getCashTransaction(id: Int): Flow<CashTransaction>

    @Query("SELECT * FROM cashTransactions_table WHERE id == :id")
    fun getCashTransactionEntity(id: Int): Flow<CashTransactionEntity>
}