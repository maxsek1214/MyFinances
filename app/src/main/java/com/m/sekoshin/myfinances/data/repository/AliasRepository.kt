package com.m.sekoshin.myfinances.data.repository

import androidx.annotation.WorkerThread
import com.m.sekoshin.myfinances.data.room.dao.AliasDao
import com.m.sekoshin.myfinances.data.room.entity.AliasEntity
import kotlinx.coroutines.flow.Flow

class AliasRepository(private val aliasDao: AliasDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allAliases: Flow<List<AliasEntity>> = aliasDao.getAliases()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(aliasEntity: AliasEntity) {
        aliasDao.insert(aliasEntity)
    }
}