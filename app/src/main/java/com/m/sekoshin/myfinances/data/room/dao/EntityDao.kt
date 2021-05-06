package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*

@Dao
abstract interface EntityDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(t: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(t: List<T>)

    @Update
    suspend fun update(t: T)

    @Update
    suspend fun updateAll(t: List<T>)

    @Delete
    suspend fun delete(t: T)

    @Delete
    suspend fun deleteAll(t: List<T>)
}