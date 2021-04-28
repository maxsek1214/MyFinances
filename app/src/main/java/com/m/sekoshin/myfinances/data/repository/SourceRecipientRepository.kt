package com.m.sekoshin.myfinances.data.repository

import androidx.annotation.WorkerThread
import com.m.sekoshin.myfinances.data.room.dao.SourceRecipientDao
import com.m.sekoshin.myfinances.data.room.entity.SourceRecipientEntity
import kotlinx.coroutines.flow.Flow

class SourceRecipientRepository(private val sourceRecipientDao: SourceRecipientDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allSourceRecipients: Flow<List<SourceRecipientEntity>> = sourceRecipientDao.getSourceRecipients()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(sourceRecipientEntity: SourceRecipientEntity) {
        sourceRecipientDao.insert(sourceRecipientEntity)
    }
}