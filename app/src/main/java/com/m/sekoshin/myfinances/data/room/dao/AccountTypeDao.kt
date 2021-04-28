package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*
import com.m.sekoshin.myfinances.data.room.entity.AccountTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountTypeDao : EntityDao<AccountTypeEntity> {
    @Query("SELECT * FROM accountTypes_table")
    fun getAccountTypes(): Flow<List<AccountTypeEntity>>
}