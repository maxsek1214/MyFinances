package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionCategory(
    @Embedded
    val transactionCategoryEntity: TransactionCategoryEntity,
    @Relation(
        parentColumn = "parent_id",
        entityColumn = "id",
        entity = TransactionCategoryEntity::class
    )
    val children: List<TransactionCategoryEntity>
)