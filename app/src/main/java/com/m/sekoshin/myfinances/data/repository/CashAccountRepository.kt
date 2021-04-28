package com.m.sekoshin.myfinances.data.repository

import androidx.annotation.WorkerThread
import com.m.sekoshin.myfinances.data.room.dao.CashAccountDao
import com.m.sekoshin.myfinances.data.room.entity.CashAccount
import kotlinx.coroutines.flow.Flow

class CashAccountRepository(private val cashAccountDao: CashAccountDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCashAccounts: Flow<List<CashAccount>> = cashAccountDao.getCashAccounts()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cashAccount: CashAccount) {
        cashAccountDao.insert(cashAccount.cashAccountEntity)
    }
}