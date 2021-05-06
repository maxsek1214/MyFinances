package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FlowOfFund(
    @Embedded
    val flowOfFundEntity: FlowOfFundEntity,
    @Relation(
        parentColumn = "cashAccount_id",
        entityColumn = "id",
        entity = CashAccountEntity::class
    )
    val cashAccountEntity: CashAccountEntity,
    @Relation(
        parentColumn = "cashTransaction_id",
        entityColumn = "id",
        entity = CashTransactionEntity::class
    )
    val cashTransactionEntity: CashTransactionEntity
)
