package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*
import com.m.sekoshin.myfinances.data.room.entity.Store
import com.m.sekoshin.myfinances.data.room.entity.StoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao : EntityDao<StoreEntity> {
    @Transaction
    @Query("SELECT * FROM stores_table")
    fun getStores(): Flow<List<Store>>

    @Transaction
    @Query("SELECT * FROM stores_table WHERE id == :id")
    fun getStore(id: Int): Flow<Store>

    @Query("SELECT * FROM stores_table WHERE id == :id")
    fun getStoreEntity(id: Int): Flow<StoreEntity>
}