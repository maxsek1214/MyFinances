package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*
import com.m.sekoshin.myfinances.data.room.entity.TransactionCategory
import com.m.sekoshin.myfinances.data.room.entity.TransactionCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionCategoryDao : EntityDao<TransactionCategoryEntity> {
    @Transaction
    @Query("SELECT * FROM transactionCategories_table")
    fun getTransactionCategories(): Flow<List<TransactionCategory>>

    @Transaction
    @Query("SELECT * FROM transactionCategories_table WHERE id == :id")
    fun getTransactionCategory(id: Int): Flow<TransactionCategory>

    @Query("SELECT * FROM transactionCategories_table WHERE id == :id")
    fun getTransactionCategoryEntity(id: Int): Flow<TransactionCategoryEntity>

    @Insert
    fun insertEntity(transactionCategoryEntity: TransactionCategoryEntity): Long
}