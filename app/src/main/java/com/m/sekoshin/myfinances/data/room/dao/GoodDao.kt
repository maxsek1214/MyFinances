package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.m.sekoshin.myfinances.data.room.entity.Good
import com.m.sekoshin.myfinances.data.room.entity.GoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoodDao : EntityDao<GoodEntity> {
    @Transaction
    @Query("SELECT * FROM goods_table")
    fun getGoods(): Flow<List<Good>>

    @Transaction
    @Query("SELECT * FROM goods_table WHERE id == :id")
    fun getGood(id: Int): Flow<Good>

    @Query("SELECT * FROM goods_table WHERE id == :id")
    fun getGoodEntity(id: Int): Flow<GoodEntity>
}