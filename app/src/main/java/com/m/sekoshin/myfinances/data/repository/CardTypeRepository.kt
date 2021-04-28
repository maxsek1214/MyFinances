package com.m.sekoshin.myfinances.data.repository

import androidx.annotation.WorkerThread
import com.m.sekoshin.myfinances.data.room.dao.CardTypeDao
import com.m.sekoshin.myfinances.data.room.entity.CardTypeEntity
import kotlinx.coroutines.flow.Flow

class CardTypeRepository(private val cardTypeDao: CardTypeDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCardTypes: Flow<List<CardTypeEntity>> = cardTypeDao.getCardTypes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(cardTypeEntity: CardTypeEntity) {
        cardTypeDao.insert(cardTypeEntity)
    }
}