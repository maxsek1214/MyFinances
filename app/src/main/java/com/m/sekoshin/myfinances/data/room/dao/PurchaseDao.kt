package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.m.sekoshin.myfinances.data.room.entity.Purchase
import com.m.sekoshin.myfinances.data.room.entity.PurchaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseDao : EntityDao<PurchaseEntity> {
    @Transaction
    @Query("SELECT * FROM purchases_table")
    fun getPurchases(): Flow<List<Purchase>>

    @Transaction
    @Query("SELECT * FROM purchases_table WHERE id == :id")
    fun getPurchase(id: Int): Flow<Purchase>

    @Query("SELECT * FROM purchases_table WHERE id == :id")
    fun getPurchaseEntity(id: Int): Flow<PurchaseEntity>
}