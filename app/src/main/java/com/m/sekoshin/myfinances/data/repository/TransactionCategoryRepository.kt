package com.m.sekoshin.myfinances.data.repository

import androidx.annotation.WorkerThread
import com.m.sekoshin.myfinances.data.room.dao.TransactionCategoryDao
import com.m.sekoshin.myfinances.data.room.entity.TransactionCategory
import com.m.sekoshin.myfinances.data.room.entity.TransactionCategoryEntity
import kotlinx.coroutines.flow.Flow

class TransactionCategoryRepository(private val transactionCategoryDao: TransactionCategoryDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allTransactionCategories: Flow<List<TransactionCategory>> =
        transactionCategoryDao.getTransactionCategories()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transactionCategory: TransactionCategory) {
        transactionCategoryDao.insert(transactionCategory.transactionCategoryEntity)
        transactionCategoryDao.insertAll(transactionCategory.children)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(transactionCategoryEntity: TransactionCategoryEntity) {
        transactionCategoryDao.insert(transactionCategoryEntity)
    }
}