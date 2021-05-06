package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CashTransaction(
    @Embedded
    val cashTransactionEntity: CashTransactionEntity,
    @Relation(
        parentColumn = "cashiersCheck_id",
        entityColumn = "id",
        entity = CashiersCheckEntity::class
    )
    val cashiersCheckEntity: CashiersCheckEntity,
    @Relation(
        parentColumn = "sourceRecipient_id",
        entityColumn = "id",
        entity = SourceRecipientEntity::class
    )
    val sourceRecipientEntity: SourceRecipientEntity,
    @Relation(
        parentColumn = "transactionCategory_id",
        entityColumn = "id",
        entity = TransactionCategoryEntity::class
    )
    val transactionCategoryEntity: TransactionCategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cashTransaction_id",
        entity = FlowOfFundEntity::class
    )
    val flowOfFundEntities: List<FlowOfFundEntity>
)
