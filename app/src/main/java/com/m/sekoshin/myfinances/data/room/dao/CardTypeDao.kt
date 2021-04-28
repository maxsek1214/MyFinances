package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*
import com.m.sekoshin.myfinances.data.room.entity.CardTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardTypeDao : EntityDao<CardTypeEntity> {
    @Query("SELECT * FROM cardTypes_table")
    fun getCardTypes(): Flow<List<CardTypeEntity>>
}