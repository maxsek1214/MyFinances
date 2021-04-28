package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*
import com.m.sekoshin.myfinances.data.room.entity.CashAccount
import com.m.sekoshin.myfinances.data.room.entity.CashAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CashAccountDao : EntityDao<CashAccountEntity> {
    @Transaction
    @Query("SELECT * FROM cashAccounts_table")
    fun getCashAccounts(): Flow<List<CashAccount>>

    @Transaction
    @Query("SELECT * FROM cashAccounts_table WHERE id == :id")
    fun getCashAccount(id: Int): Flow<CashAccount>

    @Query("SELECT * FROM cashAccounts_table WHERE id == :id")
    fun getCashAccountEntity(id: Int): Flow<CashAccountEntity>
}