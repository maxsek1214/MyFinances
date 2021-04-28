package com.m.sekoshin.myfinances.data.room.dao

import androidx.room.*
import com.m.sekoshin.myfinances.data.room.entity.SourceRecipientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SourceRecipientDao : EntityDao<SourceRecipientEntity> {
    @Query("SELECT * FROM sourceRecipients_table")
    fun getSourceRecipients(): Flow<List<SourceRecipientEntity>>

    @Query("SELECT * FROM sourceRecipients_table WHERE id == :id")
    fun getSourceRecipient(id: Int): Flow<SourceRecipientEntity>
}