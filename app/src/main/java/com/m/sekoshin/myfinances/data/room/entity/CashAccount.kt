package com.m.sekoshin.myfinances.data.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CashAccount(
        @Embedded
        val cashAccountEntity: CashAccountEntity,
        @Relation(parentColumn = "accountType_id", entityColumn = "id", entity = AccountTypeEntity::class)
        val accountTypeEntity: AccountTypeEntity,
        @Relation(parentColumn = "issuer_id", entityColumn = "id", entity = IssuerEntity::class)
        val issuerEntity: IssuerEntity,
        @Relation(parentColumn = "cardType_id", entityColumn = "id", entity = CardTypeEntity::class)
        val cardTypeEntity: CardTypeEntity
)
