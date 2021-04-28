package com.m.sekoshin.myfinances.data.repository

import androidx.annotation.WorkerThread
import com.m.sekoshin.myfinances.data.room.dao.AccountTypeDao
import com.m.sekoshin.myfinances.data.room.entity.AccountTypeEntity
import kotlinx.coroutines.flow.Flow

class AccountTypeRepository(private val accountTypeDao: AccountTypeDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allAccountTypes: Flow<List<AccountTypeEntity>> = accountTypeDao.getAccountTypes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(accountTypeEntity: AccountTypeEntity) {
        accountTypeDao.insert(accountTypeEntity)
    }
}