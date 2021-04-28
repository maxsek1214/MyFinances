package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.m.sekoshin.myfinances.data.room.entity.FlowOfFund
import com.m.sekoshin.myfinances.data.room.entity.FlowOfFundEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlowOfFundDao : EntityDao<FlowOfFundEntity> {
    @Transaction
    @Query("SELECT * FROM flowOfFunds_table")
    fun getFlowOfFunds(): Flow<List<FlowOfFund>>
}