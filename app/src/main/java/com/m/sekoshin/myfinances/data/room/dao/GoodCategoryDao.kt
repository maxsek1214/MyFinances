package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*
import com.m.sekoshin.myfinances.data.room.entity.GoodCategory
import com.m.sekoshin.myfinances.data.room.entity.GoodCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoodCategoryDao : EntityDao<GoodCategoryEntity> {
    @Transaction
    @Query("SELECT * FROM goodCategories_table")
    fun getGoodCategories(): Flow<List<GoodCategory>>

    @Transaction
    @Query("SELECT * FROM goodCategories_table WHERE id == :id")
    fun getGoodCategory(id: Int): Flow<GoodCategory>

    @Query("SELECT * FROM goodCategories_table WHERE id == :id")
    fun getGoodCategoryEntity(id: Int): Flow<GoodCategoryEntity>
}