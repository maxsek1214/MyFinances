package com.m.sekoshin.myfinances.data.repository

import androidx.annotation.WorkerThread
import com.m.sekoshin.myfinances.data.room.dao.GoodCategoryDao
import com.m.sekoshin.myfinances.data.room.entity.GoodCategory
import com.m.sekoshin.myfinances.data.room.entity.GoodCategoryEntity
import kotlinx.coroutines.flow.Flow

class GoodCategoryRepository(private val goodCategoryDao: GoodCategoryDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allGoodCategories: Flow<List<GoodCategory>> = goodCategoryDao.getGoodCategories()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(goodCategory: GoodCategory) {
        goodCategoryDao.insert(goodCategory.goodCategoryEntity)
        goodCategoryDao.insertAll(goodCategory.children)
    }

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(goodCategoryEntity: GoodCategoryEntity) {
        goodCategoryDao.insert(goodCategoryEntity)
    }
}