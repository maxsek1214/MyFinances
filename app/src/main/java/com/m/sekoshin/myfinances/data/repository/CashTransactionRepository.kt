package com.m.sekoshin.myfinances.data.repository

import androidx.annotation.WorkerThread
import com.m.sekoshin.myfinances.data.room.dao.*
import com.m.sekoshin.myfinances.data.room.entity.CashTransaction
import com.m.sekoshin.myfinances.data.room.entity.CashTransactionEntity
import kotlinx.coroutines.flow.Flow

class CashTransactionRepository(
    private val cashTransactionDao: CashTransactionDao,
    private val sourceRecipientDao: SourceRecipientDao,
    private val cashiersCheckDao: CashiersCheckDao,
    private val flowOfFundDao: FlowOfFundDao
) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCashTransactions: Flow<List<CashTransaction>> = cashTransactionDao.getCashTransactions()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cashTransaction: CashTransaction) {
        cashTransactionDao.insert(cashTransaction.cashTransactionEntity)
        flowOfFundDao.insertAll(cashTransaction.flowOfFundEntities)
        sourceRecipientDao.insert(cashTransaction.sourceRecipientEntity)
        cashiersCheckDao.insert(cashTransaction.cashiersCheckEntity)
    }

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cashTransactionEntity: CashTransactionEntity) {
        cashTransactionDao.insert(cashTransactionEntity)
    }
}