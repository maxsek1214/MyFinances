package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.m.sekoshin.myfinances.data.room.entity.CashAccountFlowOfFund
import kotlinx.coroutines.flow.Flow

@Dao
interface CashAccountFlowOfFundDao {
    @Transaction
    @Query("SELECT * FROM cashAccounts_table")
    fun getCashAccountFlowOfFunds(): Flow<List<CashAccountFlowOfFund>>
}