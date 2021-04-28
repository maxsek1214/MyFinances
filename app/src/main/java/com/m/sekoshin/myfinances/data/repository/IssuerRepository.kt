package com.m.sekoshin.myfinances.data.repository

import androidx.annotation.WorkerThread
import com.m.sekoshin.myfinances.data.room.dao.IssuerDao
import com.m.sekoshin.myfinances.data.room.entity.IssuerEntity
import kotlinx.coroutines.flow.Flow

class IssuerRepository(private val issuerDao: IssuerDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allIssuers: Flow<List<IssuerEntity>> = issuerDao.getIssuers()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(issuerEntity: IssuerEntity) {
        issuerDao.insert(issuerEntity)
    }
}